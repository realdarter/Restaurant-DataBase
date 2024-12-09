<%@ page import="restaurant.model.Reservation" %>
<%@ page import="restaurant.model.Table" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalDateTime" %>

<% 
    Reservation reservation = (Reservation) request.getAttribute("reservation");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Reservation - D&J's Restaurant</title>
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
    <form action="<%= request.getContextPath() %>/ReservationsServlet" method="POST">
        <input type="hidden" name="reservation_id" value="<%= reservation != null ? reservation.getReservationID() : "" %>">

        <label for="reservation-date">Date:</label>
        <input type="date" id="reservation-date" name="reservation_date" value="<%= reservation != null ? reservation.getReservationTime().toLocalDate().toString() : "" %>" required><br>

        <label for="reservation-time">Time:</label>
        <input type="time" id="reservation-time" name="reservation_time" value="<%= reservation != null ? reservation.getReservationTime().toLocalTime().toString() : "" %>" required><br>

        <label for="table-id">Select Table:</label>
        <select id="table-id" name="table_id" required>
            <% 
                List<Table> availableTables = (List<Table>) request.getAttribute("availableTables");
                for (Table table : availableTables) {
            %>
                <option value="<%= table.getTableID() %>" <%= table.getTableID() == reservation.getTableID() ? "selected" : "" %> >
                    Table <%= table.getTableID() %> - Capacity: <%= table.getCapacity() %> 
                </option>
            <% } %>
        </select><br>

        <button type="submit" class="submit-button">Update Reservation</button>
    </form>

    <!-- Delete Reservation Form -->
    <form action="<%= request.getContextPath() %>/ReservationsServlet" method="POST">
        <input type="hidden" name="reservation_id" value="<%= reservation != null ? reservation.getReservationID() : "" %>">
        <button type="submit" class="delete-button" name="action" value="delete">Delete Reservation</button>
    </form>
</main>

</body>
</html>

