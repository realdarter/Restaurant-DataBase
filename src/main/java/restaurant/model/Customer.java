package restaurant.model;

public class Customer {
    private int customerId;
    private String name;
    private String contactInfo;
    private String username;  // New field for Username
    private String password;  // New field for Password

    // Constructor with username and password
    public Customer(int customerId, String name, String contactInfo, String username, String password) {
        this.customerId = customerId;
        this.name = name;
        this.contactInfo = contactInfo;
        this.username = username;
        this.password = password;
    }

    // Getters and Setters
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Customer [customerId=" + customerId + ", name=" + name + ", contactInfo=" + contactInfo + 
               ", username=" + username + ", password=" + password + "]"; //teehee password leak
    }
}
