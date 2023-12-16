-- Drop table if exists
DROP TABLE IF EXISTS restaurants;

-- Create table
CREATE TABLE restaurants
(
    id               BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id          BIGINT,
    name             TEXT,
    street           TEXT,
    city             TEXT,
    zip_code         TEXT,
    phone            TEXT,
    country          TEXT,
    homepage         TEXT,
    restaurant_state TEXT
);

-- Insert data
INSERT INTO restaurants (name, user_id, street, city, zip_code, phone, country, homepage, restaurant_state)
VALUES
    ('Cafe Delight', 1, '123 Main Street', 'Valby', '2500', '+1234567890', 'Denmark', 'http://www.cafedelight.com',
     'ACTIVE'),
    ('Pizzeria Express', 2, '456 Pizza Avenue', 'Valby', '2500', '+9876543210', 'Denmark',
     'http://www.pizzeriaexpress.com', 'ACTIVE'),
    ('Sushi Haven', 3, '789 Sushi Lane', 'Valby', '2500', '+1122334455', 'Denmark', 'http://www.sushihaven.com',
     'PENDING'),
    ('Burger Bistro', 4, '321 Burger Street', 'Herlev', '2730', '+9988776655', 'Denmark',
     'http://www.burgerbistro.com', 'ACTIVE'),
    ('Mexican Fiesta', 5, '567 Taco Boulevard', 'Herlev', '2730', '+1122334455', 'Denmark',
     'http://www.mexicanfiesta.com', 'ACTIVE'),
    ('Fine Dining Palace', 6, '876 Gourmet Road', 'Herlev', '2730', '+5544332211', 'Denmark',
     'http://www.finediningpalace.com', 'PENDING'),
    ('Vegetarian Delights', 7, '234 Veggie Street', 'Helsingør ', '3000', '+9988776655', 'Denmark',
     'http://www.vegetariandelights.com', 'ACTIVE'),
    ('Seafood Sensation', 8, '876 Seafood Lane', 'Helsingør ', '3000', '+1122334455', 'Denmark',
     'http://www.seafoodsensation.com', 'ACTIVE'),
    ('BBQ Bliss', 9, '456 BBQ Road', 'Helsingør ', '3000', '+9988776655', 'Denmark', 'http://www.bbqbliss.com',
     'PENDING'),
    ('Ethnic Eats', 10, '789 Global Street', 'Lyngby', '2800', '+5544332211', 'Denmark', 'http://www.ethniceats.com',
     'ACTIVE'),
    ('Vegetarian Delights 2', 11, '345 Veggie Avenue', 'Lyngby', '2800', '+9988776655', 'Denmark',
     'http://www.vegetariandelights2.com', 'ACTIVE'),
    ('Italian Bistro', 12, '567 Pasta Lane', 'Lyngby', '2800', '+1122334455', 'Denmark',
     'http://www.italianbistro.com', 'PENDING');
