### Restaurant Reservation and Order System Setup Guide

## Overview

The **Restaurant Reservation and Order System** is an integrated solution designed to manage restaurant operations such as table reservations, customer orders, and menu management. The system uses a **MySQL** database, which interacts with a **Java** backend using **MySQL Connector/J** for database connectivity. The front end is built with **HTML**, **CSS**, and **JavaScript**, and the server is deployed using **Apache Tomcat**. The application also utilizes **Eclipse IDE** for development and managing the project lifecycle.

### Step-by-Step Setup Instructions

#### **Step 1: Clone the Repository**

Clone the project repository to your local machine:

```bash
git clone https://github.com/realdarter/Restaurant-DataBase
```

#### **Step 2: Set Up the MySQL Database**

1. **Open MySQL Workbench:**
   - If you don't already have MySQL Workbench, install it from [MySQL's official site](https://dev.mysql.com/downloads/workbench/).

2. **Create a New Database:**
   - Open MySQL Workbench and log into your MySQL server.
   - Create a new database for the restaurant system:
     ```sql
     CREATE DATABASE restaurant_system;
     ```

3. **Create Tables with Java Class:**
   - Instead of using raw SQL scripts to create the database schema, we'll run the `dbcreate_schema.java` file within Eclipse.
   - This approach ensures that the schema is created properly, deletes existing tables if they exist, and re-creates them from scratch.

#### **Step 3: Configure the Project and Dependencies**

1. **Add Dependencies to Eclipse:**
   - **MySQL Connector/J:** Download the MySQL JDBC driver from [MySQL Connector/J Download](https://dev.mysql.com/downloads/connector/j/), then add the JAR to the project in Eclipse:
     - Right-click the project > **Build Path** > **Add External Archives...** > Select the downloaded `mysql-connector-java-x.x.x.jar`.
   
   - **Tomcat JDBC Driver (Jakarta):** Download the Tomcat JDBC driver from [Apache Tomcat](https://tomcat.apache.org/download-90.cgi). Then add this JAR to your project using the same method as above.

#### **Step 4: Set Up Tomcat in Eclipse**

1. **Install Apache Tomcat:**
   - If you don’t have Tomcat, download and unzip it from the [Apache Tomcat download page](https://tomcat.apache.org/download-90.cgi).

2. **Link Tomcat to Eclipse:**
   - Go to **Window > Preferences**.
   - Navigate to **Server > Runtime Environments**, then click **Add External Servers** and choose the Tomcat installation directory.
   
3. **Create a New Server in Eclipse:**
   - Open the **Servers** tab in Eclipse, right-click and select **New > Server**.
   - Choose **Apache Tomcat v9.x** and set the Tomcat installation path.
   - Add your project to the server by selecting your project and clicking **Add**.

#### **Step 5: Run Schema Creation**

1. **Run `dbcreate_schema.java`:**
   - In the `src/main/java/restaurant/dbcreate_schema.java` file, this class is responsible for creating the required tables in the MySQL database. Running this Java class will ensure the proper setup of the schema.

   - **Why run this Java class?**
     - It not only creates the tables if they don't exist but also removes any previously created tables before rebuilding them.
     - This ensures that the schema is always fresh and matches the latest structure.

#### **Step 6: Initialize Data**

1. **Run `initialize_data.java`:**
   - After running `dbcreate_schema.java`, run the `initialize_data.java` class to populate the tables with sample data. This class adds 15+ items into each table (e.g., 15 customers, 15 menu items) to make the system operational for testing.
   

   - **Why use `initialize_data.java`?**
     - It's a more efficient approach for initializing the database than manually running SQL scripts.
     - It ensures the data is populated in a consistent manner across all tables with a batch insertion process.
   
   - After running this Java class, the database will have 15+ entries for each table, ready for testing.

#### **Step 7: Deploy and Run the Application**

1. **Start Tomcat in Eclipse:**
   - Open the **Servers** tab, right-click your Tomcat server, and select **Start**.

2. **Deploy the Web Application:**
   - Right-click the project > **Run As** > **Run on Server**.
   - The application should now be available at `http://localhost:8080/your-project-name` in your browser.

#### **Step 8: Test the Features**

- **Customer Testing:** Test creating accounts, making reservations, placing orders, and managing profiles.
- **Staff Testing:** Test reservation management, table status updates, and order processing.
- **Owner Testing:** Test menu updates, report generation, and user role management.

## Known Issues & Future Enhancements

- **Limited Frontend Design:** The user interface could be further enhanced for better responsiveness and aesthetics.
- **Mobile Support:** The system currently lacks optimized views for mobile devices.
- **Cross-Platform Compatibility:** Implement support for mobile/tablet devices in the future.

---

By following this setup guide, you should have a fully functional restaurant reservation and order system running in Eclipse with MySQL, Tomcat, and all necessary database setup.
