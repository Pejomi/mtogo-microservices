-- Drop table if exists
DROP TABLE IF EXISTS menu_items;
DROP TABLE IF EXISTS menus;

-- Create tables
CREATE TABLE menus
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    restaurant_id BIGINT
);

CREATE TABLE menu_items
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        TEXT,
    price       DECIMAL(10, 2),
    menu_id     BIGINT, FOREIGN KEY (menu_id) REFERENCES menus(id)
);

-- Insert data
INSERT INTO menus (id, restaurant_id)
VALUES (1,1),
       (2,1),
       (3,2),
       (4,3),
       (5,4),
       (6,5),
       (7,6),
       (8,7),
       (9,8),
       (10,9),
       (11,10),
       (12,11);


INSERT INTO menu_items (name, price, menu_id)
VALUES ('Soup', 15.00, 1),
       ('Pasta', 40.00, 1),
       ('Pizza', 50.00, 2),
       ('Sushi', 60.00, 3),
       ('Taco', 30.00, 4),
       ('Burger', 30.00, 4),
       ('Cola', 10.00, 5),
       ('Beer', 20.00, 5),
       ('Wine', 30.00, 6),
       ('Salad', 20.00, 7),
       ('Fish', 50.00, 8),
       ('Steak', 60.00, 9),
       ('Chicken', 40.00, 10),
       ('Vegetarian', 30.00, 11),
       ('Lasagna', 40.00, 11);
