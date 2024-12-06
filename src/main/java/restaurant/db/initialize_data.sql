-- Step 1: Delete all data before initialization
DELETE FROM Reservations;
DELETE FROM OrderDetails;
DELETE FROM Orders;
DELETE FROM Customers;
DELETE FROM MenuItems;
DELETE FROM Tables;

-- Step 2: Initialize Tables
INSERT INTO Tables (TableID, Capacity, Status) VALUES
(1, 2, 'Available'),
(2, 3, 'Available'),
(3, 4, 'Reserved'),
(4, 5, 'Available'),
(5, 6, 'Available'),
(6, 2, 'Reserved'),
(7, 3, 'Available'),
(8, 4, 'Available'),
(9, 5, 'Reserved'),
(10, 6, 'Available'),
(11, 2, 'Reserved'),
(12, 3, 'Available'),
(13, 4, 'Available'),
(14, 5, 'Reserved'),
(15, 6, 'Available');

-- Print table information (You can't directly print from SQL, but can select data for verification)
SELECT TableID, Status FROM Tables;

-- Step 3: Initialize Customers
INSERT INTO Customers (CustomerID, Name, ContactInfo) VALUES
(1, 'Customer1', 'Contact1@mail.com'),
(2, 'Customer2', 'Contact2@mail.com'),
(3, 'Customer3', 'Contact3@mail.com'),
(4, 'Customer4', 'Contact4@mail.com'),
(5, 'Customer5', 'Contact5@mail.com'),
(6, 'Customer6', 'Contact6@mail.com'),
(7, 'Customer7', 'Contact7@mail.com'),
(8, 'Customer8', 'Contact8@mail.com'),
(9, 'Customer9', 'Contact9@mail.com'),
(10, 'Customer10', 'Contact10@mail.com'),
(11, 'Customer11', 'Contact11@mail.com'),
(12, 'Customer12', 'Contact12@mail.com'),
(13, 'Customer13', 'Contact13@mail.com'),
(14, 'Customer14', 'Contact14@mail.com'),
(15, 'Customer15', 'Contact15@mail.com');

-- Step 4: Initialize Menu Items
INSERT INTO MenuItems (ItemID, Name, Price, Category) VALUES
(1, 'Item1', 5.99, 'Category1'),
(2, 'Item2', 10.99, 'Category2'),
(3, 'Item3', 7.99, 'Category3'),
(4, 'Item4', 8.99, 'Category1'),
(5, 'Item5', 6.99, 'Category2'),
(6, 'Item6', 9.99, 'Category3'),
(7, 'Item7', 4.99, 'Category1'),
(8, 'Item8', 11.99, 'Category2'),
(9, 'Item9', 12.99, 'Category3'),
(10, 'Item10', 3.99, 'Category1'),
(11, 'Item11', 6.99, 'Category2'),
(12, 'Item12', 10.99, 'Category3'),
(13, 'Item13', 5.99, 'Category1'),
(14, 'Item14', 9.99, 'Category2'),
(15, 'Item15', 7.99, 'Category3');

-- Step 5: Initialize Orders
INSERT INTO Orders (CustomerID, TableID, OrderTime) VALUES
(1, 1, '2024-12-01 12:00:00'),
(2, 2, '2024-12-02 12:00:00'),
(3, 3, '2024-12-03 12:00:00'),
(4, 4, '2024-12-04 12:00:00'),
(5, 5, '2024-12-05 12:00:00'),
(6, 6, '2024-12-06 12:00:00'),
(7, 7, '2024-12-07 12:00:00'),
(8, 8, '2024-12-08 12:00:00'),
(9, 9, '2024-12-09 12:00:00'),
(10, 10, '2024-12-10 12:00:00'),
(11, 11, '2024-12-11 12:00:00'),
(12, 12, '2024-12-12 12:00:00'),
(13, 13, '2024-12-13 12:00:00'),
(14, 14, '2024-12-14 12:00:00'),
(15, 15, '2024-12-15 12:00:00');

-- Print all orders (to verify)
SELECT OrderID, CustomerID, TableID, OrderTime FROM Orders;

-- Step 6: Initialize Order Details
-- Assigning OrderDetails with random orders (can be adjusted as needed)
INSERT INTO OrderDetails (OrderID, ItemID, Quantity) VALUES
(1, 1, 2),
(2, 2, 2),
(3, 3, 2),
(4, 4, 2),
(5, 5, 2),
(6, 6, 2),
(7, 7, 2),
(8, 8, 2),
(9, 9, 2),
(10, 10, 2),
(11, 11, 2),
(12, 12, 2),
(13, 13, 2),
(14, 14, 2),
(15, 15, 2);

-- Print all order details (to verify)
SELECT OrderDetailID, OrderID, ItemID, Quantity FROM OrderDetails;

-- Step 7: Initialize Reservations
INSERT INTO Reservations (CustomerID, TableID, ReservationTime) VALUES
(1, 1, '2024-12-02 18:00:00'),
(2, 2, '2024-12-03 18:00:00'),
(3, 3, '2024-12-04 18:00:00'),
(4, 4, '2024-12-05 18:00:00'),
(5, 5, '2024-12-06 18:00:00'),
(6, 6, '2024-12-07 18:00:00'),
(7, 7, '2024-12-08 18:00:00'),
(8, 8, '2024-12-09 18:00:00'),
(9, 9, '2024-12-10 18:00:00'),
(10, 10, '2024-12-11 18:00:00'),
(11, 11, '2024-12-12 18:00:00'),
(12, 12, '2024-12-13 18:00:00'),
(13, 13, '2024-12-14 18:00:00'),
(14, 14, '2024-12-15 18:00:00'),
(15, 15, '2024-12-16 18:00:00');

-- Print all reservations (to verify)
SELECT ReservationID, CustomerID, TableID, ReservationTime FROM Reservations;

-- Final message
SELECT 'All data initialized successfully.';
