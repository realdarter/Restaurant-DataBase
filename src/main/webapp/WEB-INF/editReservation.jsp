<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<!-- editReservation.jsp -->
<%@ page import="restaurant.model.Reservation" %>
<%@ page import="restaurant.model.Table" %> <!-- Import Table class -->
<%@ page import="java.util.List" %> <!-- Import List class -->
<%@ page import="java.time.format.DateTimeFormatter" %>

<% 
    Reservation reservation = (Reservation) request.getAttribute("reservationToEdit");  // Corrected attribute name
    List<Table> availableTables = (List<Table>) request.getAttribute("availableTables");  // Retrieve available tables
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
%>

<h2>Edit Reservation</h2>
<form action="<%= request.getContextPath() %>/ReservationsServlet" method="POST">
    <input type="hidden" name="reservation_id" value="<%= reservation.getReservationID() %>">

    <label for="reservation-date">Date:</label>
    <input type="date" id="reservation-date" name="reservation_date" value="<%= reservation.getReservationTime().toLocalDate().toString() %>" required><br>

    <label for="reservation-time">Time:</label>
    <input type="time" id="reservation-time" name="reservation_time" value="<%= reservation.getReservationTime().toLocalTime().toString() %>" required><br>

    <label for="table-id">Select Table:</label>
    <select id="table-id" name="table_id" required>
        <% 
            // Loop through available tables and pre-select the current table
            for (Table table : availableTables) {
        %>
            <option value="<%= table.getTableID() %>" <%= table.getTableID() == reservation.getTableID() ? "selected" : "" %>>
                Table <%= table.getTableID() %> - Capacity: <%= table.getCapacity() %>
            </option>
        <% } %>
    </select><br>

    <button type="submit">Update Reservation</button>
</form>

</html>
