package database;

import java.time.LocalDateTime;

public class Reservations {

    private int ReservationID;
    private int CustomerID;
    private int TableID;
    private LocalDateTime ReservationTime;

    // Constructor to initialize reservation details
    public Reservations(int ReservationID, int CustomerID, int TableID, LocalDateTime ReservationTime) {
        this.ReservationID = ReservationID;
        this.CustomerID = CustomerID;
        this.TableID = TableID;
        this.ReservationTime = ReservationTime;
    }

    // getters and setters
    public int getReservationID() {
        return ReservationID;
    }

    public void setReservationID(int ReservationID) {
        this.ReservationID = ReservationID;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int CustomerID) {
        this.CustomerID = CustomerID;
    }

    public int getTableID() {
        return TableID;
    }

    public void setTableID(int TableID) {
        this.TableID = TableID;
    }

    public LocalDateTime getReservationTime() {
        return ReservationTime;
    }

    public void setReservationTime(LocalDateTime ReservationTime) {
        this.ReservationTime = ReservationTime;
    }

    @Override
    public String toString() {
        return "Reservations [ReservationID=" + ReservationID + ", CustomerID=" + CustomerID + 
               ", TableID=" + TableID + ", ReservationTime=" + ReservationTime + "]";
    }
}
