package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import restaurant.db.OrderDAO;
import restaurant.model.Order;
import restaurant.model.OrderDetail;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private OrderDAO orderDAO;

    public OrderServlet() {
        super();
        orderDAO = new OrderDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(false);
            Integer userID = (Integer) session.getAttribute("userID");

            if (userID == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            List<Order> orders = orderDAO.getOrdersByCustomerId(userID);
            request.setAttribute("orders", orders);
            request.getRequestDispatcher("/orderHistory.jsp").forward(request, response);
            System.out.println("Redirected User to OrderHistory.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(false);
            Integer userID = (Integer) session.getAttribute("userID");

            if (userID == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            int tableID = Integer.parseInt(request.getParameter("tableID"));
            LocalDateTime orderTime = LocalDateTime.now();

            // Get selected item IDs and quantities
            String[] itemIDs = request.getParameterValues("itemID");
            String[] quantities = request.getParameterValues("quantity");

            List<OrderDetail> orderDetails = new ArrayList<>();
            
            if (itemIDs != null && quantities != null) {
                for (int i = 0; i < itemIDs.length; i++) {
                    int itemID = Integer.parseInt(itemIDs[i]);
                    int quantity = Integer.parseInt(quantities[i]);
                    orderDetails.add(new OrderDetail(0, 0, itemID, quantity));
                }
            }

            // Create Order object, passing orderDetails list
            Order order = new Order(0, (int) userID, tableID, orderTime, orderDetails);
            orderDAO.addOrder(order, orderDetails);

            // Redirect to order confirmation page
            response.sendRedirect("orderConfirmation.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
        }
    }

}
