package database;

public class OrderDetails {

    private int OrderID;
    private int ItemID;
    private int Quantity;

    // Constructor to initialize order details
    public OrderDetails(int OrderID, int ItemID, int Quantity) {
        this.OrderID = OrderID;
        this.ItemID = ItemID;
        this.Quantity = Quantity;
    }

    // Getter and Setters
    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public int getItemID() {
        return ItemID;
    }

    public void setItemID(int ItemID) {
        this.ItemID = ItemID;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    @Override
    public String toString() {
        return "OrderDetails [OrderID=" + OrderID + ", ItemID=" + ItemID + ", Quantity=" + Quantity + "]";
    }
}
