<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>  <!-- Import List class -->

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menu - D&J's Restaurant</title>
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
			    <li><a href="<%= request.getContextPath() %>/CustomizeUserServlet">Profile</a></li>
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

<main class="menu-container">
    <section class="menu">
        <h2>Our Menu</h2>

        <!-- Search and Sort Form -->
        <form action="<%= request.getContextPath() %>/MenuServlet" method="get">
            <input type="text" name="search" placeholder="Search for a dish..."
                   value="<%= request.getParameter("search") != null ? request.getParameter("search") : "" %>">
            <button type="submit">Search</button>

            <select name="sortBy" onchange="this.form.submit()">
                <option value="">Sort By</option>
                <option value="price" <%= "price".equals(request.getParameter("sortBy")) ? "selected" : "" %>>Price</option>
                <option value="category" <%= "category".equals(request.getParameter("sortBy")) ? "selected" : "" %>>Category</option>
            </select>
        </form>

        <!-- Display Menu Items -->
        <div class="menu-category">
            <% 
            List<restaurant.model.MenuItem> menuItems = (List<restaurant.model.MenuItem>) request.getAttribute("menuItems");
            if (menuItems != null && !menuItems.isEmpty()) {
                for (restaurant.model.MenuItem item : menuItems) {
            %>
                <div class="menu-item">
                    <span class="name"><%= item.getName() %></span>
                    <span class="price">$<%= item.getPrice() %></span>
                    <span class="category"><%= item.getCategory() %></span>
                </div>
            <% 
                }
            } else { 
            %>
                <p>No menu items available at the moment.</p>
            <% } %>
        </div>
    </section>
</main>


<footer>
    <p>&copy; 2024 D&J's Restaurant. All Rights Not Reserved.</p>
</footer>
</body>
</html>
