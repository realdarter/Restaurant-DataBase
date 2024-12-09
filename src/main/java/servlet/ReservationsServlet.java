package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import restaurant.db.ReservationsDAO;
import restaurant.db.TableDAO;
import restaurant.model.Reservation;
import restaurant.model.Table;

/**
 * Servlet implementation class ReservationsServlet
 */
@WebServlet("/ReservationsServlet")
public class ReservationsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ReservationsDAO reservationDAO;
    private TableDAO tableDAO;

    public ReservationsServlet() {
        super();
        reservationDAO = new ReservationsDAO(); // DAO for reservations
        tableDAO = new TableDAO(); // DAO for tables
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userID = (Integer) session.getAttribute("userID");

        // Check if userID is null (i.e., user is not logged in)
        if (userID == null) {
            response.sendRedirect("login.jsp");  // Redirect to login page if user is not logged in
            return;
        }

        // Handle deletion of reservation
        String deleteIdStr = request.getParameter("delete");
        if (deleteIdStr != null) {
            int reservationId = Integer.parseInt(deleteIdStr);
            try {
                Reservation reservation = reservationDAO.getReservationByID(reservationId);
                if (reservation != null && reservation.getCustomerID() == userID) {
                    reservationDAO.deleteReservation(reservationId);  // Delete the reservation
                    response.sendRedirect("ReservationsServlet");  // Redirect to refresh the reservation list
                    return;
                } else {
                    // If reservation doesn't belong to the user, you can show an error or redirect
                    response.sendRedirect("ReservationsServlet");
                    return;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new ServletException("Error deleting reservation", e);
            }
        }

        // Handle editing of reservation
        String editIdStr = request.getParameter("edit");
        if (editIdStr != null) {
            int reservationId = Integer.parseInt(editIdStr);
            try {
                Reservation reservation = reservationDAO.getReservationByID(reservationId);
                
                // Check if the reservation is null
                if (reservation == null) {
                    System.out.println("Reservation not found for ID: " + reservationId);
                    request.setAttribute("message", "Reservation not found.");
                    request.getRequestDispatcher("/error.jsp").forward(request, response);
                    return;
                }
                
                // Check if the reservation belongs to the current user
                if (reservation.getCustomerID() == userID) {
                    // Set session and request attributes
                    session.setAttribute("reservationID", reservationId);
                    List<Table> availableTables = tableDAO.getAvailableTables();
                    request.setAttribute("availableTables", availableTables);
                    request.setAttribute("reservation", reservation);
                    
                    // Forward to the edit page
                    System.out.println("Sending to editReservation.jsp");
                    request.getRequestDispatcher("/editReservation.jsp").forward(request, response);
                } else {
                    response.sendRedirect("ReservationsServlet");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new ServletException("Error fetching reservation for edit", e);
            }

        }

        try {
            // Fetch all reservations
            List<Reservation> reservations = reservationDAO.getAllReservations();

            // Separate the user's reservations from others' reservations
            List<Reservation> userReservations = new ArrayList<>();
            List<Reservation> otherReservations = new ArrayList<>();

            for (Reservation reservation : reservations) {
                if (reservation.getCustomerID() == userID) {
                    userReservations.add(reservation);
                } else {
                    otherReservations.add(reservation);
                }
            }

            // Pass both lists to the JSP
            request.setAttribute("userReservations", userReservations);   // Current user's reservations
            request.setAttribute("otherReservations", otherReservations); // Other users' reservations

            // Fetch available tables for the whole day
            List<Table> availableTables = tableDAO.getAvailableTables();
            request.setAttribute("availableTables", availableTables);  // Pass available tables to JSP

            // Forward to reservations.jsp to display the form and reservations
            request.getRequestDispatcher("/reservation.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Error fetching reservations", e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the userID from session
        HttpSession session = request.getSession();
        Integer userID = (Integer) session.getAttribute("userID");

        // If user is not logged in, redirect to login page
        if (userID == null) {
            response.sendRedirect("login.jsp");
            return;  // Exit the method, do not continue
        }

        // Retrieve form parameters for creating or updating a reservation
        String reservationIdString = request.getParameter("reservation_id");  // For editing, optional
        int reservationId = (reservationIdString != null) ? Integer.parseInt(reservationIdString) : 0; // 0 for new reservations
        String reservationDateString = request.getParameter("reservation_date");  // Format: yyyy-MM-dd
        String reservationTimeString = request.getParameter("reservation_time");  // Format: HH:mm
        LocalDate reservationDate = LocalDate.parse(reservationDateString);
        LocalTime reservationTime = LocalTime.parse(reservationTimeString);
        LocalDateTime reservationDateTime = LocalDateTime.of(reservationDate, reservationTime);
        
        int tableId = Integer.parseInt(request.getParameter("table_id"));  // Get selected table ID from the form

        // Create a new Reservation object
        Reservation reservation = new Reservation(reservationId, userID, tableId, reservationDateTime);

        try {
            if (reservationId == 0) {
                // Save the reservation to the database if it's a new reservation
                reservationDAO.addReservation(reservation);
            } else {
                // Update the reservation if reservationId is not 0
                reservationDAO.updateReservation(reservation);
            }
            response.sendRedirect("ReservationsServlet"); // Redirect to the reservation list page
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Error processing reservation", e);
        }
    }
}
