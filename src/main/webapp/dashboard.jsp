<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome to D&J's Restaurant Dashboard</title>
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
			    <!--   <li><a href="<%= request.getContextPath() %>/">Dashboard</a></li> -->
			    <!--  <li><a href="<%= request.getContextPath() %>/menu.jsp">Menu</a></li> -->
			    <li><a href="<%= request.getContextPath() %>/MenuServlet">Menu</a></li>
			    <li><a href="<%= request.getContextPath() %>/ReservationsServlet">Reservations</a></li>
			    <li><a href="<%= request.getContextPath() %>/about.jsp">About Us</a></li>
			    <li><a href="<%= request.getContextPath() %>/contact.jsp">Contact</a></li>
			    <% 
			    // Display login/logout based on session state
			    String username = (String) session.getAttribute("username");
			    if (username != null) { 
			    %>
			        <li><a href="<%= request.getContextPath() %>/logout.jsp">Logout</a></li>
			    <% } else { %>
			        <li><a href="<%= request.getContextPath() %>/login.jsp">Login</a></li>
			    <% } %>
			</ul>
        </nav>
    </div>
</header>
<main>
    <h1>Welcome to the Dashboard!</h1>

    <%
        // Retrieve the username from the session
        if (username != null) {
    %>
        <p>Hello, <strong><%= username %></strong>! We're glad to have you here.</p>
    <%
        } else {
    %>
        <p>Hello, Guest! Please <a href="login.jsp">log in</a> to access your account.</p>
    <%
        }
    %>
</main>
<footer>
    <p>&copy; 2024 D&J's Restaurant. All Rights Not Reserved.</p>
    <p>Follow us on:
        <a href="#">Facebook</a> |
        <a href="#">Instagram</a> |
        <a href="#">Twitter</a>
    </p>
</footer>
</body>
</html>
