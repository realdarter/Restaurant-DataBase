<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - D&J's Restaurant</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/styles.css">
   
</head>
<body>
    <main class="login-container">
        <!-- Login Form -->
        <section class="login-form">
            <h1>Welcome to our Restaurant System!</h1>
            <h2>Login to Your Account</h2>

            <!-- Display success or error message if present -->
            <c:if test="${not empty message}">
                <p class="error-message">${message}</p>
            </c:if>

            <form action="<%= request.getContextPath() + "/login" %>" method="post">
                <label for="username">Username</label>
                <input type="text" id="username" name="username" required><br>

                <label for="password">Password</label>
                <input type="password" id="password" name="password" required><br>

                <button type="submit">Login</button>
            </form>

            <p>Don't have an account? Register below!</p>
        </section>

        <!-- Registration Section -->
        <section id="register-section" class="register-form">
            <h2>Create an Account</h2>
            <form action="<%= request.getContextPath() + "/register" %>" method="post">
                <!-- Name Field -->
                <label for="new-name">Full Name</label>
                <input type="text" id="new-name" name="name" required><br>

                <!-- Username Field -->
                <label for="new-username">Username</label>
                <input type="text" id="new-username" name="username" required><br>

                <!-- Email Field -->
                <label for="new-email">Email</label>
                <input type="email" id="new-email" name="email" required><br>

                <!-- Password Field -->
                <label for="new-password">Password</label>
                <input type="password" id="new-password" name="password" required><br>

                <!-- Confirm Password Field -->
                <label for="confirm-password">Confirm Password</label>
                <input type="password" id="confirm-password" name="confirm-password" required><br>

                <button type="submit">Register</button>
            </form>
        </section>
    </main>
</body>
</html>
