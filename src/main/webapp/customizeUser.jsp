<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="restaurant.model.Customer" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customize Your Account - D&J's Restaurant</title>
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
                    <li><a href="<%= request.getContextPath() %>/OrderServlet">Order History</a></li>
                    <li><a href="<%= request.getContextPath() %>/MenuServlet">Menu</a></li>
                    <li><a href="<%= request.getContextPath() %>/ReservationsServlet">Reservations</a></li>
                    <li><a href="<%= request.getContextPath() %>/about.jsp">About Us</a></li>
                    <li><a href="<%= request.getContextPath() %>/contact.jsp">Contact</a></li>
                    <li><a href="<%= request.getContextPath() %>/CustomizeUserServlet">Profile</a></li>
                    <li><a href="<%= request.getContextPath() %>/logout">Logout</a></li>
                </ul>
            </nav>
        </div>
    </header>

    <main class="customize-user-container">
        <!-- cuostomize User Form -->
        <section class="customize-user-form">
            <h1>Customize Your Account</h1>
            <h2>Edit your details or delete your account</h2>

            <!-- display success or error message if present -->
            <c:if test="${not empty message}">
                <p class="error-message">${message}</p>
            </c:if>

            <form action="<%= request.getContextPath() + "/CustomizeUserServlet" %>" method="post">
                <!-- dsplays current user's details -->
                <label for="name">Full Name</label>
                <input type="text" id="name" name="name" value="<%= request.getAttribute("userName") %>" required><br>

                <label for="email">Email</label>
                <input type="email" id="email" name="email" value="<%= request.getAttribute("userEmail") %>" required><br>

                <label for="new-password">New Password</label>
                <input type="password" id="new-password" name="new-password"><br>

                <label for="confirm-password">Confirm New Password</label>
                <input type="password" id="confirm-password" name="confirm-password"><br>

                <button type="submit">Update Account</button>
            </form>


            <form action="<%= request.getContextPath() + "/dashboard.jsp" %>" method="get">
                <button type="submit" class="back-button">Back to Dashboard</button>
            </form>
        </section>
    </main>
</body>

</html>

