package servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import restaurant.db.CustomerDAO;
import restaurant.model.Customer;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private CustomerDAO customerDAO = new CustomerDAO(); // Assuming CustomerDAO is implemented

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm-password");

        if (!password.equals(confirmPassword)) {
            // Passwords do not match
            request.setAttribute("message", "Passwords do not match.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        try {
            if (customerDAO.doesUsernameExist(username)) {
                // Username already exists
                request.setAttribute("message", "Username is already taken.");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                System.out.println("Could not create: " + username);
                return;
            }

            // Create a new customer
            Customer customer = new Customer(0, name, email, username, password);
            customer.setName(name);
            customer.setContactInfo(email); // Assuming email is stored as ContactInfo
            customer.setUsername(username);
            customer.setPassword(password); // Note: Consider hashing the password

            // Add customer to the database
            customerDAO.addCustomer(customer);

            // Registration successful
            request.setAttribute("message", "Registration successful! Please log in.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            
            System.out.println("Created user: " + username);

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("message", "An error occurred during registration.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }
}
