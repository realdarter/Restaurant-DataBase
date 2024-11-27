package database;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class TestReservationsDAO {
    public static void main(String[] args) {
        ReservationsDAO reservationsDAO = new ReservationsDAO();

        try {
            // Add a new reservation
            Reservations newReservation = new Reservations(0, 1, 5, LocalDateTime.now());
            reservationsDAO.addReservation(newReservation);

            // Get all reservations
            List<Reservations> reservations = reservationsDAO.getAllReservations();
            System.out.println("All reservations:");
            for (Reservations reservation : reservations) {
                System.out.println(reservation);
            }

            // Update a reservation (assuming ReservationID = 1)
            Reservations updatedReservation = new Reservations(1, 2, 6, LocalDateTime.now().plusHours(2));
            reservationsDAO.updateReservation(updatedReservation);

            // Get the updated reservation by ID
            Reservations reservationById = reservationsDAO.getReservationById(1);
            System.out.println("Updated reservation:");
            System.out.println(reservationById);

            // Delete a reservation (assuming ReservationID = 1)
            reservationsDAO.deleteReservation(1);

            // Verify deletion
            System.out.println("All reservations after deletion:");
            reservations = reservationsDAO.getAllReservations();
            for (Reservations reservation : reservations) {
                System.out.println(reservation);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
