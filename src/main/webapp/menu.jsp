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
                <li><a href="<%= request.getContextPath() %>/about.jsp">About Us</a></li>
                <li><a href="<%= request.getContextPath() %>/contact.jsp">Contact</a></li>
            </ul>
        </nav>
    </div>
</header>

<main class="menu-container">
    <section class="menu">
        <h2>Our Menu</h2>

        <!-- Loop through menu items dynamically using Java -->
        <div class="menu-category">
		<% 
		    List<restaurant.model.MenuItem> menuItems = (List<restaurant.model.MenuItem>) request.getAttribute("menuItems");
		    if (menuItems != null && !menuItems.isEmpty()) {
		        for (restaurant.model.MenuItem item : menuItems) {
		%>
		        <ul>
		            <li>
		                <span class="item-name"><%= item.getName() %></span>
		                <span class="price">$<%= item.getPrice() %></span>
		            </li>
		        </ul>
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
