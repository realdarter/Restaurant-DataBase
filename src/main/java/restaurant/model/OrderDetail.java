package restaurant.model;

public class OrderDetail {

    private int OrderDetailID;  // Added unique ID for each order detail
    private int OrderID;
    private int ItemID;
    private int Quantity;

    // Constructor to initialize order details
    public OrderDetail(int OrderDetailID, int OrderID, int ItemID, int Quantity) {
        this.OrderDetailID = OrderDetailID;
        this.OrderID = OrderID;
        this.ItemID = ItemID;
        this.Quantity = Quantity;
    }

    // Getter and Setters
    public int getOrderDetailID() {
        return OrderDetailID;
    }

    public void setOrderDetailID(int OrderDetailID) {
        this.OrderDetailID = OrderDetailID;
    }

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
        return "OrderDetail [OrderDetailID=" + OrderDetailID + ", OrderID=" + OrderID + ", ItemID=" + ItemID + ", Quantity=" + Quantity + "]";
    }
}
