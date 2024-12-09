<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Contact Us - D&J's Restaurant</title>
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

<main>
    <section id="contact-form">
        <h2>Contact Us</h2>
        <p>If you have any questions, feel free to reach out to us using the contact form below:</p>
        
        <form action="<%= request.getContextPath() %>/ContactServlet" method="POST">
            <label for="name">Your Name:</label><br>
            <input type="text" id="name" name="name" required><br><br>
            
            <label for="email">Your Email:</label><br>
            <input type="email" id="email" name="email" required><br><br>
            
            <label for="message">Your Message:</label><br>
            <textarea id="message" name="message" rows="4" required></textarea><br><br>
            
            <button type="submit">Send Message</button>
        </form>
    </section>

    <section id="contact-info">
        <h2>Our Contact Information</h2>
        <p>If you prefer, you can contact us directly using the following details:</p>
        <p>
            <strong>Location:</strong>  1 Washington Sq, San Jose, CA 95192 <br>
            <strong>Phone:</strong> (888) 888 - 8888 <br>
            <strong>Email:</strong> D&J@restaurant.com
        </p>
    </section>
</main>

<footer>
    <p>&copy; 2024 D&J's Restaurant. All Rights Not Reserved.</p>
</footer>

</body>
</html>
