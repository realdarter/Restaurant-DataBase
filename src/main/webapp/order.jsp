<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="restaurant.model.MenuItem" %>
<%@ page import="restaurant.model.Order" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order - D&J's Restaurant</title>
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
                <li><a href="<%= request.getContextPath() %>/MenuServlet">Menu</a></li>
                <li><a href="<%= request.getContextPath() %>/OrderServlet">Orders</a></li>
                <li><a href="<%= request.getContextPath() %>/logout">Logout</a></li>
            </ul>
        </nav>
    </div>
</header>

<main class="order-container">
    <section class="place-order">
        <h2>Place an Order</h2>
        <form action="<%= request.getContextPath() %>/OrderServlet" method="post">
            <div class="menu-items">
                <% 
                List<MenuItem> menuItems = (List<MenuItem>) request.getAttribute("menuItems");
                if (menuItems != null && !menuItems.isEmpty()) {
                    for (MenuItem item : menuItems) {
                %>
                    <label>
                        <!-- Change value to item ID, name is for display only -->
                        <input type="checkbox" name="itemID" value="<%= item.getItemID() %>">
                        <span class="item-name"><%= item.getName() %></span>
                        <span class="price">$<%= item.getPrice() %></span>
                    </label><br>
                <% 
                    }
                } else { 
                %>
                    <p>No menu items available to order.</p>
                <% } %>
            </div>
            
            <!-- Add a quantity field for each selected item -->
            <div class="quantity-container">
                <label for="quantity">Quantity:</label>
                <input type="number" name="quantity" id="quantity" min="1" value="1">
            </div>

            <button type="submit">Place Order</button>
        </form>
    </section>

    <section class="past-orders">
        <h2>Your Past Orders</h2>
        <% 
        List<Order> orders = (List<Order>) request.getAttribute("orders");
        if (orders != null && !orders.isEmpty()) {
            for (Order order : orders) {
        %>
            <div class="order">
                <h3>Order #<%= order.getOrderID() %> - <%= order.getOrderTime() %></h3>
                <ul>
                    <% 
                    // Assuming getItems() returns a list of menu item names for this order.
                    for (String item : order.getItems()) { 
                    %>
                        <li><%= item %></li>
                    <% } %>
                </ul>
            </div>
        <% 
            }
        } else { 
        %>
            <p>No past orders found.</p>
        <% } %>
    </section>
</main>

<footer>
    <p>&copy; 2024 D&J's Restaurant. All Rights Not Reserved.</p>
</footer>
</body>
</html>
