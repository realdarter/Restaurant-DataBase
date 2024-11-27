package database;

public class MenuItem {
    private int ItemID;
    private String Name;
    private float Price;
    private String Category;
    
    public MenuItem(int ItemID, String Name, float Price, String Category) {
    	this.ItemID = ItemID;
    	this.Name = Name;
    	this.Price = Price;
    	this.Category = Category;
    }
    
    //setters and getters
    public int getItemID() {
    	return ItemID;
    }
    
    public void setItemID(int ItemID) {
    	this.ItemID = ItemID;
    }
    
    public String getName() {
    	return Name;
    }
    
    public void setItemID(String Name) {
    	this.Name = Name;
    }
    
    public float getPrice() {
    	return Price;
    }
    
    public void setPrice(float Price) {
    	this.Price = Price;
    }
    
    public String getCategory() {
    	return Category;
    }
    
    public void setCategory(String Category) {
    	this.Category = Category;
    }
    
    @Override
    public String toString() {
        return "MenuItem [ItemID=" + ItemID + ", Name=" + Name + ", Price=" + Price + ", Category=" + Category + "]";
    }

}
