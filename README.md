# Restaurant Reservation and Order System

## Overview

The **Restaurant Reservation and Order System** is an all-in-one solution designed to handle restaurant operations such as table reservations, customer orders, menu management, and customer profile management. The system stores critical data in a **MySQL** database to enhance both restaurant staff efficiency and customer experience. It integrates a **Java** backend with **MySQL** through the **MySQL Connector/J** (JDBC driver) for seamless database connectivity. The frontend is built using **HTML**, **CSS**, and **JavaScript** to create a responsive and user-friendly interface. The server-side application is deployed using **Tomcat**. Initially, we encountered challenges with database connectivity and Tomcat configuration, but by following tutorials on setting up Tomcat in Eclipse and connecting MySQL with Java, we successfully overcame these issues. These steps ensured smooth functionality between the frontend, backend, and database, leading to a stable and efficient system.

## Features

### Customer Features
- **Account Management**: Users can create and manage accounts, including login and profile editing (email, username).
- **Table Reservations**: Customers can make, update, and cancel reservations based on real-time table availability.
- **Menu Browsing**: View the full menu, with real-time updates on availability and pricing.
- **Order Management**: Customers can place, modify, or cancel orders directly from their profile.
- **Order Customization**: Customers can select menu items, adjust quantities, and customize orders.

### Staff Features
- **Reservation Management**: Staff can assign customers to available tables, manage reservations, and update table statuses (e.g., "Available", "Reserved", "In Service").
- **Order Processing**: Process customer orders, update item details, and modify order statuses (e.g., preparing, served).
- **Menu Management**: Add, update, or remove menu items and adjust their prices.
- **Report Generation**: View reports on menu performance and peak reservation times to help manage restaurant operations efficiently.

### Owner Features
- **Role Management**: Owners can manage user roles (e.g., customer, staff, admin) and their associated permissions.
- **Comprehensive Reporting**: Owners can generate detailed reports on overall restaurant performance, including sales, orders, and reservations.
- **Menu and Pricing Management**: Owners can modify menu items, categories, and pricing to meet customer demand and optimize restaurant operations.

## Technologies

- **Database Management**: MySQL
- **Frontend**: HTML, CSS, JavaScript (Responsive UI)
- **Backend**: Java (Using Tomcat as the web server)
- **Database Connectivity**: MySQL Connector/J (JDBC driver for Java)
- **Server Management**: Apache Tomcat
- **IDE**: Eclipse (for development and running the web application)

## Setup Instructions

### Prerequisites

Before you run the project, ensure you have the following software installed:

1. **MySQL Database Server** (or equivalent)
2. **Eclipse IDE** for Java (or IntelliJ IDEA)
3. **Tomcat Server** (for hosting the application)
4. **MySQL Connector/J** library (for Java-based database connectivity)
5. **Tomcat JDBC Driver (Jakarta)**

### Step 1: Clone the Repository

Clone the repository to your local machine:

```bash
git clone [repository-url](https://github.com/realdarter/Restaurant-DataBase)
```

### Step 2: Set Up the Database

1. Open **MySQL Workbench** (or any other database management tool) and create a new database.
2. Import the provided **SQL Schema** file to set up tables and relationships (you can find the SQL schema in the "Appendices" section of the project documentation).

### Step 3: Configure Database Connectivity

#### **MySQL Connector/J**
1. Download the **MySQL Connector/J** JDBC driver from [MySQL official website](https://dev.mysql.com/downloads/connector/j/).
2. Add the downloaded `mysql-connector-java-x.x.xx.jar` file to your Eclipse project:
   - Right-click the project in Eclipse > **Build Path** > **Add External Archives...**
   - Select and add the `mysql-connector-java-x.x.xx.jar` file.

#### **Tomcat JDBC Driver (Jakarta)**
1. Download the **Tomcat JDBC driver** (also known as the Jakarta package) from the [Apache Tomcat website](http://tomcat.apache.org/download-90.cgi).
2. Add the **Tomcat JDBC driver** jar to your Eclipse project:
   - Right-click the project > **Build Path** > **Add External Archives...**
   - Select and add the `tomcat-jdbc-x.x.x.jar` file.

### Step 4: Set Up Tomcat in Eclipse

1. **Download and Install Tomcat:**
   - If you haven't installed Tomcat, download it from [Apache Tomcat](https://tomcat.apache.org/download-90.cgi).
   - Unzip the Tomcat archive to a location of your choice.

2. **Add Tomcat to Eclipse:**
   - Open Eclipse and go to **Window > Preferences**.
   - Navigate to **Server > Runtime Environments**.
   - Click **Add External Servers** and select your installed version of Tomcat.
   - Set up your Tomcat server by selecting the installation directory.

3. **Link Your Project to Tomcat:**
   - Go to **Servers** tab in Eclipse, right-click, and choose **New > Server**.
   - Choose **Apache Tomcat v9.x** and set the path to your Tomcat installation directory.
   - Add your project to the server by selecting the project and clicking **Add**.

### Step 5: Configure Database Connection in Code

1. In the `DatabaseConnection` class, configure the connection to your MySQL database by specifying your MySQL database URL, username, and password. Example:

```java
public class DatabaseConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/your_database_name";
    private static final String USER = "root";
    private static final String PASSWORD = "student";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Connection failed.", e);
        }
    }
}
```

Make sure that the database URL, username, and password match your setup in MySQL.

### Step 6: Run the Application

1. **Start your Tomcat server** in Eclipse:
   - Open the **Servers** tab in Eclipse, right-click on your Tomcat server, and select **Start**.

2. **Deploy the Project**:
   - Right-click the project > **Run As** > **Run on Server**.

3. Open your browser and go to `http://localhost:8080/your-project-name` to access the application.

### Step 7: Test the Features

- **Customer Testing**: Test the ability to make reservations, place orders, modify orders, and update personal details.
- **Staff Testing**: Test reservation management, table status updates, and order processing functionality.
- **Owner Testing**: Test updating the menu, generating reports, and managing user roles and access.

## Database Schema

This project uses the following schema for data storage:

- **Tables**
  - `TableID` (Primary Key)
  - `Capacity`
  - `Status` (Available, Reserved, In Service)
  
- **Customers**
  - `CustomerID` (Primary Key)
  - `Name`
  - `Contact Info`
  - `Username`
  - `Password`
  
- **Reservations**
  - `ReservationID` (Primary Key)
  - `CustomerID` (Foreign Key)
  - `TableID` (Foreign Key)
  - `ReservationTime`
  
- **Orders**
  - `OrderID` (Primary Key)
  - `CustomerID` (Foreign Key)
  - `TableID` (Foreign Key)
  - `OrderTime`
  
- **MenuItems**
  - `ItemID` (Primary Key)
  - `Name`
  - `Price`
  - `Category`
  
- **OrderDetails**
  - `OrderID` (Foreign Key)
  - `ItemID` (Foreign Key)
  - `Quantity`

## Test Cases

- **Reservations**: Test adding, updating, and deleting reservations, ensuring table status updates correctly.
- **User Profiles**: Test updates to customer profiles and validate proper storage in the database.
- **Orders**: Test placing, modifying, and deleting orders, ensuring changes are reflected in the system.

## Error Handling

The system uses robust error handling with `try-catch` blocks for all database interactions. Any errors encountered, such as SQL exceptions, are logged for easier debugging and troubleshooting.

## Known Issues

- Limited frontend design that can be enhanced for better user experience.
- Currently lacks cross-platform support for mobile devices and tablets.

## Future Enhancements

- Improve frontend UI/UX for better aesthetics and responsiveness.
- Add mobile and tablet support for cross-platform compatibility.
- Integrate third-party services, such as delivery systems and loyalty programs.

## Authors

- Joey Lau (joey.lau@sjsu.edu)
- Daniel Bao (daniel.bao@sjsu.edu)
