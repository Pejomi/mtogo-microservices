-- Inserting data for the "menus" table
INSERT INTO menus (restaurant_id) VALUES
                                      (1), (2), (3), (4), (5), (6), (7), (8), (9), (10), (11), (12);

-- Inserting data for the "menuItems" table
INSERT INTO menu_items (name, price, menu_id) VALUES
-- Menu for Restaurant 1
('Pasta Carbonara', 119.99, 1),
('Caprese Salad', 89.99, 1),
('Tiramisu', 69.99, 1),
('Margherita Pizza', 99.99, 1),
('Spaghetti Bolognese', 129.99, 1),
('Garlic Bread', 49.99, 1),
('Minestrone Soup', 59.99, 1),
('Ravioli al Pomodoro', 109.99, 1),
('Chocolate Cake', 79.99, 1),
('Espresso', 29.99, 1),

-- Menu for Restaurant 2
('BBQ Bacon Burger', 139.99, 2),
('Chicken Wings', 99.99, 2),
('Caesar Salad', 89.99, 2),
('Pepperoni Pizza', 149.99, 2),
('Cheese Fries', 59.99, 2),
('Mushroom Swiss Burger', 129.99, 2),
('Onion Rings', 49.99, 2),
('Vegetarian Wrap', 109.99, 2),
('Chocolate Shake', 69.99, 2),
('Iced Tea', 29.99, 2),

-- Menu for Restaurant 3
('Dragon Roll', 169.99, 3),
('Edamame', 59.99, 3),
('Tempura Udon', 119.99, 3),
('Salmon Sashimi', 149.99, 3),
('California Roll', 99.99, 3),
('Miso Soup', 49.99, 3),
('Sushi Platter', 189.99, 3),
('Rainbow Roll', 179.99, 3),
('Green Tea Ice Cream', 59.99, 3),
('Matcha Latte', 39.99, 3),

-- Menu for Restaurant 4
('Classic Burger', 109.99, 4),
('Fish and Chips', 139.99, 4),
('Caesar Salad', 89.99, 4),
('Chicken Quesadilla', 119.99, 4),
('Onion Rings', 49.99, 4),
('Grilled Salmon', 159.99, 4),
('Sweet Potato Fries', 59.99, 4),
('Chocolate Brownie Sundae', 79.99, 4),
('Iced Coffee', 39.99, 4),
('Lemonade', 29.99, 4),

-- Menu for Restaurant 5
('Enchiladas', 129.99, 5),
('Taco Platter', 109.99, 5),
('Guacamole and Chips', 69.99, 5),
('Queso Dip', 79.99, 5),
('Burrito Bowl', 119.99, 5),
('Churros', 59.99, 5),
('Sangria', 89.99, 5),
('Mexican Street Corn', 49.99, 5),
('Flan', 69.99, 5),
('Horchata', 39.99, 5),

-- Menu for Restaurant 6
('Filet Mignon', 249.99, 6),
('Lobster Bisque', 149.99, 6),
('Caesar Salad', 109.99, 6),
('Salmon Wellington', 189.99, 6),
('Truffle Mac and Cheese', 129.99, 6),
('Creme Brulee', 89.99, 6),
('Wagyu Beef Sushi', 209.99, 6),
('Foie Gras Pate', 169.99, 6),
('Chocolate Souffle', 99.99, 6),
('Red Wine Flight', 129.99, 6),

-- Menu for Restaurant 7
('Vegetarian Platter', 139.99, 7),
('Quinoa Salad', 99.99, 7),
('Falafel Wrap', 89.99, 7),
('Hummus and Pita', 59.99, 7),
('Veggie Burger', 119.99, 7),
('Baba Ganoush', 69.99, 7),
('Greek Salad', 79.99, 7),
('Lentil Soup', 49.99, 7),
('Fruit Smoothie', 69.99, 7),
('Turkish Coffee', 39.99, 7),

-- Menu for Restaurant 8
('Grilled Sea Bass', 229.99, 8),
('Shrimp Scampi', 189.99, 8),
('Caesar Salad', 129.99, 8),
('Lobster Roll', 169.99, 8),
('Clam Chowder', 109.99, 8),
('Key Lime Pie', 89.99, 8),
('Oysters Rockefeller', 249.99, 8),
('Crab Cake Sliders', 149.99, 8),
('Mango Sorbet', 69.99, 8),
('Pina Colada', 99.99, 8),

-- Menu for Restaurant 9
('BBQ Ribs', 169.99, 9),
('Brisket Platter', 189.99, 9),
('Caesar Salad', 109.99, 9),
('Smoked Chicken Wings', 129.99, 9),
('Mac and Cheese', 89.99, 9),
('Cornbread Muffins', 49.99, 9),
('Pulled Pork Sandwich', 149.99, 9),
('Baked Beans', 69.99, 9),
('Sweet Potato Pie', 79.99, 9),
('Arnold Palmer', 39.99, 9),

-- Menu for Restaurant 10
('Sushi Boat', 269.99, 10),
('Tempura Shrimp', 149.99, 10),
('Miso Soup', 69.99, 10),
('Dragon Roll', 189.99, 10),
('Spicy Tuna Roll', 169.99, 10),
('Green Tea Ice Cream', 89.99, 10),
('Edamame', 59.99, 10),
('Salmon Sashimi', 209.99, 10),
('California Roll', 129.99, 10),
('Sake Bomb', 79.99, 10),

-- Menu for Restaurant 11
('Vegetarian Platter', 139.99, 11),
('Quinoa Salad', 99.99, 11),
('Falafel Wrap', 89.99, 11),
('Hummus and Pita', 59.99, 11),
('Veggie Burger', 119.99, 11),
('Baba Ganoush', 69.99, 11),
('Greek Salad', 79.99, 11),
('Lentil Soup', 49.99, 11),
('Fruit Smoothie', 69.99, 11),
('Turkish Coffee', 39.99, 11),

-- Menu for Restaurant 12
('Seafood Paella', 189.99, 12),
('Garlic Shrimp', 159.99, 12),
('Bruschetta', 79.99, 12),
('Lobster Bisque', 129.99, 12),
('Grilled Octopus', 169.99, 12),
('Caesar Salad', 89.99, 12),
('Tuna Tartare', 149.99, 12),
('Chocolate Mousse', 69.99, 12),
('White Sangria', 99.99, 12),
('Espresso Martini', 109.99, 12);