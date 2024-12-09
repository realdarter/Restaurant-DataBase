package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

import restaurant.db.CustomerDAO;  // Assuming you're using the CustomerDAO for database operations
import restaurant.model.Customer;  // Assuming you're using the Customer model class

@WebServlet("/CustomizeUserServlet")
public class CustomizeUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CustomerDAO customerDAO;

    public CustomizeUserServlet() {
        super();
        customerDAO = new CustomerDAO(); // DAO for handling Customer data
    }

    // Handle GET request to load the user's current information into the form
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer customerId = (Integer) session.getAttribute("userID");

        if (customerId == null) {
            response.sendRedirect("login.jsp"); // Redirect to login if the user is not logged in
            return;
        }

        try {
            // Fetch current customer details from the database
            Customer customer = customerDAO.getCustomerById(customerId);
            request.setAttribute("userName", customer.getName());
            request.setAttribute("userEmail", customer.getContactInfo()); // Assuming ContactInfo is email

            // Forward to the customizeUser.jsp page with the user's current details
            request.getRequestDispatcher("/customizeUser.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Error fetching user details", e);
        }
    }

    // Handle POST request to update the user's details
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer customerId = (Integer) session.getAttribute("userID");

        if (customerId == null) {
            response.sendRedirect("login.jsp"); // Redirect to login if the user is not logged in
            return;
        }

        // Retrieve the form parameters
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String newPassword = request.getParameter("new-password");
        String confirmPassword = request.getParameter("confirm-password");

        try {
            // Fetch the existing customer from the database
            Customer customer = customerDAO.getCustomerById(customerId);

            // If a new password is provided, validate it and update
            if (newPassword != null && !newPassword.isEmpty()) {
                if (newPassword.equals(confirmPassword)) {
                    customer.setPassword(newPassword); // Update password
                } else {
                    request.setAttribute("message", "Passwords do not match.");
                    request.getRequestDispatcher("/customizeUser.jsp").forward(request, response);
                    return;
                }
            }

            // Update the name and email
            customer.setName(name);
            customer.setContactInfo(email);

            // Update the customer in the database
            customerDAO.updateCustomer(customer);

            // Redirect to the profile page or success page after update
            response.sendRedirect("dashboard.jsp");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Error updating user details", e);
        }
    }
}
