package database;

import java.sql.SQLException;
import java.util.List;

public class CustomerDAOTest {

    public static void main(String[] args) {
        try {
            CustomerDAO customerDAO = new CustomerDAO();
            
            // Step 1: Add 3 customers
            Customer customer1 = new Customer(0, "Alice", "alice@example.com");
            Customer customer2 = new Customer(0, "Bob", "bob@example.com");
            Customer customer3 = new Customer(0, "Charlie", "charlie@example.com");

            customerDAO.addCustomer(customer1);
            customerDAO.addCustomer(customer2);
            customerDAO.addCustomer(customer3);

            System.out.println("3 customers added!");

            // Step 2: Display all customers
            System.out.println("\nDisplaying all customers:");
            List<Customer> customers = customerDAO.getAllCustomers();
            for (Customer customer : customers) {
                System.out.println("ID: " + customer.getCustomerId() + ", Name: " + customer.getName() + ", Contact Info: " + customer.getContactInfo());
            }

            // Step 3: Delete all 3 customers
            System.out.println("\nDeleting all customers...");
            for (Customer customer : customers) {
            	customerDAO.deleteCustomer(customer.getCustomerId());
            }
            System.out.println("All customers deleted!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
