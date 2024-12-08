package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import restaurant.db.CustomerDAO;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private CustomerDAO customerDAO = new CustomerDAO(); // Assuming CustomerDAO is implemented

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            if (customerDAO.verifyCustomerCredentials(username, password)) {
                // Credentials are valid, create a session
            	int userID = customerDAO.getCustomerIdByUsername(username);
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                session.setAttribute("password", password);
                session.setAttribute("userID", userID);
                System.out.println("userID: " + userID);
                // Redirect to a dashboard or welcome page
                response.sendRedirect(request.getContextPath() + "/dashboard.jsp");
                System.out.println("User succesfully logged in: " + username);
            } else {
                // Invalid credentials, send back to login page with an error
                request.setAttribute("message", "Invalid username or password.");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                System.out.println("User unsuccesfully logged in: " + username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("message", "An error occurred while processing your login.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }
}
