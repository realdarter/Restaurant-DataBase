package database;

import java.time.LocalDateTime;

public class Order {

    private int OrderID;
    private int CustomerID;
    private int TableID;
    private LocalDateTime OrderTime;

    // Constructor to initialize the order
    public Order(int OrderID, int CustomerID, int TableID, LocalDateTime OrderTime) {
        this.OrderID = OrderID;
        this.CustomerID = CustomerID;
        this.TableID = TableID;
        this.OrderTime = OrderTime;
    }

    // Getter and Setters
    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
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

    public LocalDateTime getOrderTime() {
        return OrderTime;
    }

    public void setOrderTime(LocalDateTime OrderTime) {
        this.OrderTime = OrderTime;
    }

    @Override
    public String toString() {
        return "Order [OrderID=" + OrderID + ", CustomerID=" + CustomerID + ", TableID=" + TableID + ", OrderTime=" + OrderTime + "]";
    }

}
