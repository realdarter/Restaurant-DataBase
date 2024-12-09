<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>About Us - D&J's Restaurant</title>
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
    <section id="our-story">
        <h2>Our Story</h2>
        <p>
            Welcome to D&J's! Nestled in the heart of San Jose, our restaurant offers an unforgettable dining experience.
            Established in 2024, D&J's has been dedicated to bringing you a unique combination of fresh flavors,
            a warm atmosphere, and friendly service. Our passion for quality food and exceptional hospitality is what drives us
            to ensure every guest leaves with a smile and a satisfied palate.
        </p>
    </section>

    <section id="our-philosophy">
        <h2>Our Philosophy</h2>
        <p>
            At D&J's Restaurant, we believe in using only the finest ingredients, sourced locally whenever possible.
            We are committed to supporting local farmers and ensuring that our dishes are made with fresh, seasonal produce.
            Every dish on our menu is crafted with care, combining classic techniques with a modern twist to deliver flavors
            that both surprise and delight.
        </p>
    </section>

    <section id="our-team">
        <h2>Meet Our Team</h2>
        <p>
            Our talented team of chefs brings years of culinary expertise to every dish they create. With a focus on creativity and precision,
            our kitchen strives to make each plate not only taste amazing but also look beautiful. Our friendly front-of-house staff is always ready
            to welcome you with a smile, ensuring that your experience is enjoyable from start to finish.
        </p>
    </section>

    <section id="ambiance">
        <h2>The Ambiance</h2>
        <p>
            Whether you're celebrating a special occasion or enjoying a casual evening out, our cozy and inviting ambiance is perfect for any event.
            Our carefully designed interior blends contemporary elements with rustic touches, making it the ideal setting for your next meal.
        </p>
    </section>
</main>

<footer>
    <p>
        We can't wait to serve you! Come by and experience the perfect blend of great food, warm hospitality, and a welcoming atmosphere at D&J's Restaurant.
    </p>
    <p>
        <strong>Location:</strong>  1 Washington Sq, San Jose, CA 95192 <br>
        <strong>Phone:</strong> (888) 888 - 8888 <br>
        <strong>Email:</strong> D&J@restaurant.com
    </p>
</footer>

</body>
</main>

<footer>
    <p>&copy; 2024 D&J's Restaurant. All Rights Not Reserved.</p>
</footer>
</body>
</html>
