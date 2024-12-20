-- Create Tables
CREATE TABLE IF NOT EXISTS Tables (
    TableID INT AUTO_INCREMENT PRIMARY KEY, 
    Capacity INT NOT NULL, 
    Status ENUM('Available', 'Reserved', 'Occupied') NOT NULL
);

CREATE TABLE IF NOT EXISTS Customers (
    CustomerID INT AUTO_INCREMENT PRIMARY KEY, 
    Username VARCHAR(100) NOT NULL UNIQUE,  -- for username
    Name VARCHAR(100) NOT NULL, 
    ContactInfo VARCHAR(255) NOT NULL, 
    Password VARCHAR(255) NOT NULL  -- for user password
);

CREATE TABLE IF NOT EXISTS Reservations (
    ReservationID INT AUTO_INCREMENT PRIMARY KEY, 
    CustomerID INT NOT NULL, 
    TableID INT NOT NULL, 
    ReservationTime DATETIME NOT NULL, 
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID), 
    FOREIGN KEY (TableID) REFERENCES Tables(TableID)
);

CREATE TABLE IF NOT EXISTS Orders (
    OrderID INT AUTO_INCREMENT PRIMARY KEY, 
    CustomerID INT NOT NULL, 
    TableID INT NOT NULL, 
    OrderTime DATETIME NOT NULL, 
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID), 
    FOREIGN KEY (TableID) REFERENCES Tables(TableID)
);

CREATE TABLE IF NOT EXISTS MenuItems (
    ItemID INT AUTO_INCREMENT PRIMARY KEY, 
    Name VARCHAR(100) NOT NULL, 
    Price DECIMAL(10, 2) NOT NULL, 
    Category VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS OrderDetails (
    OrderDetailID INT AUTO_INCREMENT PRIMARY KEY, 
    OrderID INT NOT NULL, 
    ItemID INT NOT NULL, 
    Quantity INT NOT NULL, 
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID), 
    FOREIGN KEY (ItemID) REFERENCES MenuItems(ItemID)
);
