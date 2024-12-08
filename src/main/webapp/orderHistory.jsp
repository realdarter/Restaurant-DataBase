<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="restaurant.model.Order" %>
<%@ page import="restaurant.model.OrderDetail" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order History - D&J's Restaurant</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/styles.css">
</head>
<body>
<header>
    <div class="header-container">
        <div class="logo">
            <img src="<%= request.getContextPath() %>/static/images/logo.png" alt="D&J's Restaurant Logo">
        </div>
        <nav>
            <ul>
                <li><a href="<%= request.getContextPath() %>/dashboard.jsp">Dashboard</a></li>
			    <!--  <li><a href="<%= request.getContextPath() %>/menu.jsp">Menu</a></li> -->
			    <li><a href="<%= request.getContextPath() %>/OrderServlet">Order History</a></li>
			    <li><a href="<%= request.getContextPath() %>/MenuServlet">Menu</a></li>
			    <li><a href="<%= request.getContextPath() %>/ReservationsServlet">Reservations</a></li>
			    <li><a href="<%= request.getContextPath() %>/about.jsp">About Us</a></li>
			    <li><a href="<%= request.getContextPath() %>/contact.jsp">Contact</a></li>
			    <% 
                // Display login/logout based on session state
                String username = (String) session.getAttribute("username");
                if (username != null) { 
                %>
                    <li><a href="<%= request.getContextPath() %>/logout">Logout</a></li>
                <% } else { %>
                    <li><a href="<%= request.getContextPath() %>/login.jsp">Login</a></li>
                <% } %>
            </ul>
        </nav>
    </div>
</header>

<main class="order-history-container">
    <h2>Your Order History</h2>

    <% 
    List<Order> orders = (List<Order>) request.getAttribute("orders");
    if (orders != null && !orders.isEmpty()) {
        for (Order order : orders) {
    %>
        <div class="order">
            <h3>Order #<%= order.getOrderID() %> - <%= order.getOrderTime() %></h3>
            <ul>
                <% 
                // Fetch the names of the items ordered using the getItems method
                List<String> itemNames = order.getItems();
                if (itemNames != null && !itemNames.isEmpty()) {
                    for (String itemName : itemNames) {
                %>
                    <li><%= itemName %></li>
                <% 
                    }
                } else { 
                %>
                    <li>No items in this order.</li>
                <% } %>
            </ul>
        </div>
    <% 
        }
    } else { 
    %>
        <p>No past orders found.</p>
    <% } %>
</main>

<footer>
    <p>&copy; 2024 D&J's Restaurant. All Rights Not Reserved.</p>
</footer>

</body>
</html>
