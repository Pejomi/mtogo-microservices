-- Inserting data for the "menus" table
INSERT INTO menus (restaurant_id) VALUES
                                      (1), (2), (3), (4), (5), (6), (7), (8), (9), (10), (11), (12);

-- Inserting data for the "menuItems" table
INSERT INTO menu_items (name, price, menu_id) VALUES
-- Menu for Restaurant 1
('Pasta Carbonara', 11.99, 1),
('Caprese Salad', 8.99, 1),
('Tiramisu', 6.99, 1),
('Margherita Pizza', 9.99, 1),
('Spaghetti Bolognese', 12.99, 1),
('Garlic Bread', 4.99, 1),
('Minestrone Soup', 5.99, 1),
('Ravioli al Pomodoro', 10.99, 1),
('Chocolate Cake', 7.99, 1),
('Espresso', 2.99, 1),

-- Menu for Restaurant 2
('BBQ Bacon Burger', 13.99, 2),
('Chicken Wings', 9.99, 2),
('Caesar Salad', 8.99, 2),
('Pepperoni Pizza', 14.99, 2),
('Cheese Fries', 5.99, 2),
('Mushroom Swiss Burger', 12.99, 2),
('Onion Rings', 4.99, 2),
('Vegetarian Wrap', 10.99, 2),
('Chocolate Shake', 6.99, 2),
('Iced Tea', 2.99, 2),

-- Menu for Restaurant 3
('Dragon Roll', 16.99, 3),
('Edamame', 5.99, 3),
('Tempura Udon', 11.99, 3),
('Salmon Sashimi', 14.99, 3),
('California Roll', 9.99, 3),
('Miso Soup', 4.99, 3),
('Sushi Platter', 18.99, 3),
('Rainbow Roll', 17.99, 3),
('Green Tea Ice Cream', 5.99, 3),
('Matcha Latte', 3.99, 3),

-- Menu for Restaurant 4
('Classic Burger', 10.99, 4),
('Fish and Chips', 13.99, 4),
('Caesar Salad', 8.99, 4),
('Chicken Quesadilla', 11.99, 4),
('Onion Rings', 4.99, 4),
('Grilled Salmon', 15.99, 4),
('Sweet Potato Fries', 5.99, 4),
('Chocolate Brownie Sundae', 7.99, 4),
('Iced Coffee', 3.99, 4),
('Lemonade', 2.99, 4),

-- Menu for Restaurant 5
('Enchiladas', 12.99, 5),
('Taco Platter', 10.99, 5),
('Guacamole and Chips', 6.99, 5),
('Queso Dip', 7.99, 5),
('Burrito Bowl', 11.99, 5),
('Churros', 5.99, 5),
('Sangria', 8.99, 5),
('Mexican Street Corn', 4.99, 5),
('Flan', 6.99, 5),
('Horchata', 3.99, 5),

-- Menu for Restaurant 6
('Filet Mignon', 24.99, 6),
('Lobster Bisque', 14.99, 6),
('Caesar Salad', 10.99, 6),
('Salmon Wellington', 18.99, 6),
('Truffle Mac and Cheese', 12.99, 6),
('Creme Brulee', 8.99, 6),
('Wagyu Beef Sushi', 20.99, 6),
('Foie Gras Pate', 16.99, 6),
('Chocolate Souffle', 9.99, 6),
('Red Wine Flight', 12.99, 6),

-- Menu for Restaurant 7
('Vegetarian Platter', 13.99, 7),
('Quinoa Salad', 9.99, 7),
('Falafel Wrap', 8.99, 7),
('Hummus and Pita', 5.99, 7),
('Veggie Burger', 11.99, 7),
('Baba Ganoush', 6.99, 7),
('Greek Salad', 7.99, 7),
('Lentil Soup', 4.99, 7),
('Fruit Smoothie', 6.99, 7),
('Turkish Coffee', 3.99, 7),

-- Menu for Restaurant 8
('Grilled Sea Bass', 22.99, 8),
('Shrimp Scampi', 18.99, 8),
('Caesar Salad', 12.99, 8),
('Lobster Roll', 16.99, 8),
('Clam Chowder', 10.99, 8),
('Key Lime Pie', 8.99, 8),
('Oysters Rockefeller', 24.99, 8),
('Crab Cake Sliders', 14.99, 8),
('Mango Sorbet', 6.99, 8),
('Pina Colada', 9.99, 8),

-- Menu for Restaurant 9
('BBQ Ribs', 16.99, 9),
('Brisket Platter', 18.99, 9),
('Caesar Salad', 10.99, 9),
('Smoked Chicken Wings', 12.99, 9),
('Mac and Cheese', 8.99, 9),
('Cornbread Muffins', 4.99, 9),
('Pulled Pork Sandwich', 14.99, 9),
('Baked Beans', 6.99, 9),
('Sweet Potato Pie', 7.99, 9),
('Arnold Palmer', 3.99, 9),

-- Menu for Restaurant 10
('Sushi Boat', 26.99, 10),
('Tempura Shrimp', 14.99, 10),
('Miso Soup', 6.99, 10),
('Dragon Roll', 18.99, 10),
('Spicy Tuna Roll', 16.99, 10),
('Green Tea Ice Cream', 8.99, 10),
('Edamame', 5.99, 10),
('Salmon Sashimi', 20.99, 10),
('California Roll', 12.99, 10),
('Sake Bomb', 7.99, 10),

-- Menu for Restaurant 11
('Vegetarian Platter', 13.99, 11),
('Quinoa Salad', 9.99, 11),
('Falafel Wrap', 8.99, 11),
('Hummus and Pita', 5.99, 11),
('Veggie Burger', 11.99, 11),
('Baba Ganoush', 6.99, 11),
('Greek Salad', 7.99, 11),
('Lentil Soup', 4.99, 11),
('Fruit Smoothie', 6.99, 11),
('Turkish Coffee', 3.99, 11),

-- Menu for Restaurant 12
('Seafood Paella', 18.99, 12),
('Garlic Shrimp', 15.99, 12),
('Bruschetta', 7.99, 12),
('Lobster Bisque', 12.99, 12),
('Grilled Octopus', 16.99, 12),
('Caesar Salad', 8.99, 12),
('Tuna Tartare', 14.99, 12),
('Chocolate Mousse', 6.99, 12),
('White Sangria', 9.99, 12),
('Espresso Martini', 10.99, 12);