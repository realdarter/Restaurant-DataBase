-- Step 1: Delete all existing data to reset the database
DELETE FROM OrderDetails;
DELETE FROM Orders;
DELETE FROM Reservations;
DELETE FROM Customers;
DELETE FROM MenuItems;
DELETE FROM Tables;

INSERT INTO Tables (TableID, Capacity, Status) VALUES
(1, 2, 'Available'),
(2, 3, 'Reserved'),
(3, 4, 'Available'),
(4, 5, 'Available'),
(5, 6, 'Reserved'),
(6, 2, 'Available'),
(7, 3, 'Available'),
(8, 4, 'Reserved'),
(9, 5, 'Available'),
(10, 6, 'Reserved'),
(11, 2, 'Available'),
(12, 3, 'Available'),
(13, 4, 'Reserved'),
(14, 5, 'Available'),
(15, 6, 'Reserved');

INSERT INTO Customers (CustomerID, Name, ContactInfo, Username, Password) VALUES
(1, 'Customer1', 'Contact1@mail.com', 'user1', 'password1'),
(2, 'Customer2', 'Contact2@mail.com', 'user2', 'password2'),
(3, 'Customer3', 'Contact3@mail.com', 'user3', 'password3'),
(4, 'Customer4', 'Contact4@mail.com', 'user4', 'password4'),
(5, 'Customer5', 'Contact5@mail.com', 'user5', 'password5'),
(6, 'Customer6', 'Contact6@mail.com', 'user6', 'password6'),
(7, 'Customer7', 'Contact7@mail.com', 'user7', 'password7'),
(8, 'Customer8', 'Contact8@mail.com', 'user8', 'password8'),
(9, 'Customer9', 'Contact9@mail.com', 'user9', 'password9'),
(10, 'Customer10', 'Contact10@mail.com', 'user10', 'password10'),
(11, 'Customer11', 'Contact11@mail.com', 'user11', 'password11'),
(12, 'Customer12', 'Contact12@mail.com', 'user12', 'password12'),
(13, 'Customer13', 'Contact13@mail.com', 'user13', 'password13'),
(14, 'Customer14', 'Contact14@mail.com', 'user14', 'password14'),
(15, 'Customer15', 'Contact15@mail.com', 'user15', 'password15');

INSERT INTO MenuItems (ItemID, Name, Price, Category) VALUES
(1, 'Cheeseburger', 9.99, 'Burgers'),
(2, 'Veggie Burger', 8.49, 'Burgers'),
(3, 'Classic Caesar Salad', 7.99, 'Salads'),
(4, 'Greek Salad', 8.99, 'Salads'),
(5, 'Margherita Pizza', 12.99, 'Pizzas'),
(6, 'Pepperoni Pizza', 13.99, 'Pizzas'),
(7, 'BBQ Chicken Pizza', 14.49, 'Pizzas'),
(8, 'Chicken Tenders', 6.99, 'Appetizers'),
(9, 'Mozzarella Sticks', 7.49, 'Appetizers'),
(10, 'Garlic Bread', 4.99, 'Appetizers'),
(11, 'Spaghetti Bolognese', 14.99, 'Pasta'),
(12, 'Fettuccine Alfredo', 15.49, 'Pasta'),
(13, 'Lasagna', 16.99, 'Pasta'),
(14, 'Grilled Salmon', 18.99, 'Entrees'),
(15, 'Ribeye Steak', 22.99, 'Entrees'),
(16, 'Gorilla Chinese Food', 29.99, 'Specialty'),
(17, 'Dog Meat (Traditional Chinese)', 50.00, 'Specialty'),
(18, 'Fried Tarantulas', 12.99, 'Exotic'),
(19, 'Century Eggs', 5.99, 'Exotic'),
(20, 'Stinky Tofu', 8.99, 'Exotic'),
(21, 'Cat Meat Stew', 35.00, 'Exotic'),
(22, 'Deep Fried Snakes', 22.00, 'Exotic'),
(23, 'Roasted Bat Wings', 18.99, 'Exotic'),
(24, 'Boiled Dog Tail', 45.00, 'Exotic'),
(25, 'Fried Scorpions', 10.99, 'Exotic'),
(26, 'Chocolate-Covered Grasshoppers', 5.49, 'Exotic'),
(27, 'Caviar of Worms', 25.00, 'Exotic'),
(28, 'Camel Hump Steaks', 50.00, 'Exotic'),
(29, 'Tuna Eye Soup', 8.99, 'Exotic'),
(30, 'Cricket Flour Bread', 4.99, 'Exotic');

INSERT INTO Orders (OrderID, CustomerID, TableID, OrderTime) VALUES
(1, 1, 1, '2024-12-01 12:00:00'),
(2, 1, 2, '2024-12-02 12:00:00'),
(3, 2, 3, '2024-12-01 12:00:00'),
(4, 2, 4, '2024-12-02 12:00:00'),
(5, 3, 5, '2024-12-01 12:00:00'),
(6, 3, 6, '2024-12-02 12:00:00'),
(7, 4, 7, '2024-12-01 12:00:00'),
(8, 4, 8, '2024-12-02 12:00:00'),
(9, 5, 9, '2024-12-01 12:00:00'),
(10, 5, 10, '2024-12-02 12:00:00'),
(11, 6, 11, '2024-12-01 12:00:00'),
(12, 6, 12, '2024-12-02 12:00:00'),
(13, 7, 13, '2024-12-01 12:00:00'),
(14, 7, 14, '2024-12-02 12:00:00'),
(15, 8, 15, '2024-12-01 12:00:00');

INSERT INTO OrderDetails (OrderDetailID, OrderID, ItemID, Quantity) VALUES
(1, 1, 1, 2), (2, 1, 2, 2), (3, 1, 3, 2),
(4, 2, 4, 2), (5, 2, 5, 2), (6, 2, 6, 2),
(7, 3, 7, 2), (8, 3, 8, 2), (9, 3, 9, 2),
(10, 4, 10, 2), (11, 4, 11, 2), (12, 4, 12, 2),
(13, 5, 13, 2), (14, 5, 14, 2), (15, 5, 15, 2),
(16, 6, 16, 2), (17, 6, 17, 2), (18, 6, 18, 2),
(19, 7, 19, 2), (20, 7, 20, 2), (21, 7, 21, 2),
(22, 8, 22, 2), (23, 8, 23, 2), (24, 8, 24, 2),
(25, 9, 25, 2), (26, 9, 26, 2), (27, 9, 27, 2);

INSERT INTO Reservations (ReservationID, CustomerID, TableID, ReservationTime) VALUES
(1, 1, 1, '2024-12-02 18:00:00'),
(2, 2, 2, '2024-12-03 18:00:00'),
(3, 3, 3, '2024-12-04 18:00:00'),
(4, 4, 4, '2024-12-05 18:00:00'),
(5, 5, 5, '2024-12-06 18:00:00'),
(6, 6, 6, '2024-12-07 18:00:00'),
(7, 7, 7, '2024-12-08 18:00:00'),
(8, 8, 8, '2024-12-09 18:00:00'),
(9, 9, 9, '2024-12-10 18:00:00'),
(10, 10, 10, '2024-12-11 18:00:00'),
(11, 11, 11, '2024-12-12 18:00:00'),
(12, 12, 12, '2024-12-13 18:00:00'),
(13, 13, 13, '2024-12-14 18:00:00'),
(14, 14, 14, '2024-12-15 18:00:00'),
(15, 15, 15, '2024-12-16 18:00:00');
