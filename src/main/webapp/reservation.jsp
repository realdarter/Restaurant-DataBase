<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, restaurant.model.Reservation, restaurant.model.Table" %>
<%@ page import="restaurant.db.ReservationsDAO, restaurant.db.TableDAO" %>
<%@ page import="java.time.LocalDate" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reservations - D&J's Restaurant</title>
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

<main class="reservation-page-container">
    <section class="reservation-form-container">
        <h2>Make a Reservation</h2>
        <form action="ReservationsServlet" method="POST">
            <label for="reservation-date">Date:</label>
            <input type="date" id="reservation-date" name="reservation_date" value="<%= LocalDate.now() %>" required><br>

            <label for="reservation-time">Time:</label>
            <input type="time" id="reservation-time" name="reservation_time" required><br>

            <label for="table-id">Select Table:</label>
            <select id="table-id" name="table_id" required>
                <% 
                    // Fetch the list of available tables from the servlet
                    List<Table> availableTables = (List<Table>) request.getAttribute("availableTables");
                    if (availableTables != null && !availableTables.isEmpty()) {
                        for (Table table : availableTables) {
                %>
                    <option value="<%= table.getTableID() %>">
                        Table <%= table.getTableID() %> - Capacity: <%= table.getCapacity() %> 
                    </option>
                <% 
                        }
                    } else {
                %>
                    <option value="">No tables available</option>
                <% } %>
            </select><br>

            <button type="submit">Reserve Now</button>
        </form>
    </section>

    <!-- User's Reservations -->
	<section class="existing-reservations-container">
	    <h2>Your Reservations</h2>
	    <% 
	        List<Reservation> userReservations = (List<Reservation>) request.getAttribute("userReservations");
	        if (userReservations != null && !userReservations.isEmpty()) {
	    %>
	        <ul>
	        <% for (Reservation reservation : userReservations) { %>
	            <li>
	                <%= reservation.getReservationTime() %> - Table <%= reservation.getTableID() %>
	                <a href="<%= request.getContextPath() %>/ReservationsServlet?delete=<%= reservation.getReservationID() %>">Delete</a>
	                <a href="<%= request.getContextPath() %>/ReservationsServlet?edit=<%= reservation.getReservationID() %>">Edit</a>
	            </li>
	        <% } %>
	        </ul>
	    <% } else { %>
	        <p>You have no reservations.</p>
	    <% } %>
	</section>


    <!-- Other Users' Reservations -->
    <section class="existing-reservations-container">
        <h2>Other Reservations</h2>
        <% 
            // Fetch the list of other users' reservations
            List<Reservation> otherReservations = (List<Reservation>) request.getAttribute("otherReservations");
            if (otherReservations != null && !otherReservations.isEmpty()) {
        %>
            <ul>
            <% for (Reservation reservation : otherReservations) { %>
                <li>
                    <%= reservation.getReservationTime() %> - Table <%= reservation.getTableID() %>
                </li>
            <% } %>
            </ul>
        <% } else { %>
            <p>No reservations from others.</p>
        <% } %>
    </section>
</main>

</body>
</main>

<footer>
    <p>&copy; 2024 D&J's Restaurant. All Rights Not Reserved.</p>
</footer>
</body>
</html>
