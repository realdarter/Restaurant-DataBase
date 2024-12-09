package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import restaurant.db.ReservationsDAO;
import restaurant.db.TableDAO;
import restaurant.model.Reservation;
import restaurant.model.Table;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/editReservation")
public class EditReservationsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ReservationsDAO reservationDAO = new ReservationsDAO(); // Assuming ReservationDAO is implemented
    private TableDAO tableDAO = new TableDAO();  // Added TableDAO for table availability

    public EditReservationsServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Retrieve the reservation ID from the request (could be from URL or session)
        	String reservationIDStr = request.getParameter("reservationID");
        	int reservationID = 0;

        	if (reservationIDStr != null && !reservationIDStr.isEmpty()) {
        	    try {
        	        reservationID = Integer.parseInt(reservationIDStr);
        	    } catch (NumberFormatException e) {
        	        // Handle the error if the reservationID is not a valid integer
        	        e.printStackTrace();
        	        // Optionally, set an error message to the request
        	        request.setAttribute("message", "Invalid reservation ID format.");
        	        request.getRequestDispatcher("/error.jsp").forward(request, response);
        	        return;
        	    }
        	}
            // Fetch the reservation from the database using the reservation ID
            Reservation reservation = ReservationsDAO.getReservationByID(reservationID);

            // Check if the reservation exists
            if (reservation != null) {
                // Set the reservation and available tables as attributes in the request
                request.setAttribute("reservation", reservation);
                List<Table> availableTables = tableDAO.getAvailableTables();
                request.setAttribute("availableTables", availableTables);

                // Forward to the JSP page
                request.getRequestDispatcher("/editReservation.jsp").forward(request, response);
            } else {
                // Handle case where reservation is not found
                request.setAttribute("message", "Reservation not found.");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("message", "Error occurred while retrieving reservation.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        // Handle the delete action
        if ("delete".equals(action)) {
            int reservationId = Integer.parseInt(request.getParameter("reservation_id"));

            try {
                Reservation reservation = reservationDAO.getReservationByID(reservationId);
                if (reservation != null) {
                    reservationDAO.deleteReservation(reservationId);  // Assuming you have a delete method
                    request.setAttribute("message", "Reservation deleted successfully.");
                    request.getRequestDispatcher("/reservationConfirmation.jsp").forward(request, response);
                } else {
                    request.setAttribute("message", "Reservation not found.");
                    request.getRequestDispatcher("/error.jsp").forward(request, response);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("message", "Error occurred while deleting reservation.");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            }
        } else {
            // Handle the update reservation action
            int reservationId = Integer.parseInt(request.getParameter("reservation_id"));
            String newDate = request.getParameter("reservation_date");
            String newTime = request.getParameter("reservation_time");
            int tableId = Integer.parseInt(request.getParameter("table_id"));

            try {
                String dateTimeStr = newDate + "T" + newTime;
                LocalDateTime newReservationTime = LocalDateTime.parse(dateTimeStr);

                Reservation reservation = reservationDAO.getReservationByID(reservationId);

                if (reservation != null) {
                    reservation.setReservationTime(newReservationTime);
                    reservation.setTableID(tableId);

                    reservationDAO.updateReservation(reservation);

                    request.setAttribute("message", "Reservation updated successfully.");
                    request.getRequestDispatcher("/reservationConfirmation.jsp").forward(request, response);
                } else {
                    request.setAttribute("message", "Reservation not found.");
                    request.getRequestDispatcher("/error.jsp").forward(request, response);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("message", "Error updating reservation.");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("message", "Invalid date or time format.");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            }
        }
    }
}
