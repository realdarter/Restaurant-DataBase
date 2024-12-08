package restaurant.db;

import java.sql.SQLException;
import java.util.List;
import restaurant.model.Customer;

public class TestCustomerDAO {

    public static void main(String[] args) {
        try {
            CustomerDAO customerDAO = new CustomerDAO();
            
            // Step 1: Add 3 customers
            Customer customer1 = new Customer(0, "Alice", "alice@example.com", "alice", "1234");
            Customer customer2 = new Customer(0, "Bob", "bob@example.com", "bob", "1234");
            Customer customer3 = new Customer(0, "Charlie", "charlie@example.com", "charlie", "1234");

            try {
                customerDAO.addCustomer(customer1);
                customerDAO.addCustomer(customer2);
                customerDAO.addCustomer(customer3);
                System.out.println("3 customers added!");
            } catch (SQLException e) {
                System.out.println("Error adding customer: " + e.getMessage());
            }

            // Step 2: Display all customers
            System.out.println("\nDisplaying all customers:");
            List<Customer> customers = customerDAO.getAllCustomers();
            for (Customer customer : customers) {
                System.out.println("ID: " + customer.getCustomerId() + ", Name: " + customer.getName() + ", Contact Info: " + customer.getContactInfo());
            }

            // Step 3: Delete all 3 customers
            System.out.println("\nDeleting all customers...");
            for (Customer customer : customers) {
            	try {
            		customerDAO.deleteCustomer(customer.getCustomerId());
            	} catch (SQLException e) {
            		System.out.println("Error adding customer: " + e.getMessage());
                }
            }
            System.out.println("All customers deleted!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
