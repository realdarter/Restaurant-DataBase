package restaurant.model;

public class Table {

    private int TableID;
    private int Capacity;
    private String Status;  // Status could be "Available", "Reserved", "Occupied"

    // Constructor to initialize table details
    public Table(int TableID, int Capacity, String Status) {
        this.TableID = TableID;
        this.Capacity = Capacity;
        this.Status = Status;
    }

    // Getter and Setter
    public int getTableID() {
        return TableID;
    }

    public void setTableID(int TableID) {
        this.TableID = TableID;
    }

    public int getCapacity() {
        return Capacity;
    }

    public void setCapacity(int Capacity) {
        this.Capacity = Capacity;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    @Override
    public String toString() {
        return "Tables [TableID=" + TableID + ", Capacity=" + Capacity + ", Status=" + Status + "]";
    }
}