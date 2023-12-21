DROP TABLE IF EXISTS `order_items`;
DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders`
(
    `id`            BIGINT AUTO_INCREMENT PRIMARY KEY,
    `consumer_id`   BIGINT,
    `restaurant_id` BIGINT,
    `order_state`   VARCHAR(255),
    `price`         DECIMAL(19, 2)
);

CREATE TABLE `order_items`
(
    `id`           BIGINT AUTO_INCREMENT PRIMARY KEY,
    `menu_item_id` BIGINT,
    `price`        DECIMAL(19, 2),
    `quantity`     INT,
    `order_id`     BIGINT, FOREIGN KEY (order_id) REFERENCES orders(id)
);

-- make 12 orders
INSERT INTO `orders` (`consumer_id`, `restaurant_id`, `order_state`, `price`)
VALUES (1, 1, 'CREATED', 200),
       (2, 1, 'APPROVED', 200),
       (3, 1, 'DECLINED', 100),
       (4, 2, 'CREATED', 200),
       (5, 2, 'APPROVED', 200),
       (6, 2, 'DECLINED', 100),
       (7, 3, 'CREATED', 200),
       (8, 3, 'APPROVED', 200),
       (9, 3, 'DECLINED', 100),
       (10, 4, 'CREATED', 200),
       (11, 4, 'APPROVED', 200),
       (12, 4, 'DECLINED', 100);


-- make 36 orderItems to 12 orders
INSERT INTO `order_items` (`menu_item_id`, `price`, `quantity`, `order_id`)
VALUES (1000, 50, 2, 1),
       (1001, 50, 2, 1),
       (1002, 50, 2, 1),
       (1003, 50, 2, 2),
       (1004, 50, 2, 2),
       (1005, 50, 2, 2),
       (1006, 50, 2, 3),
       (1007, 50, 2, 3),
       (1008, 50, 2, 3),
       (1009, 50, 2, 4),
       (1010, 50, 2, 4),
       (1011, 50, 2, 4),
       (1012, 50, 2, 5),
       (1013, 50, 2, 5),
       (1014, 50, 2, 5),
       (1015, 50, 2, 6),
       (1016, 50, 2, 6),
       (1017, 50, 2, 6),
       (1018, 50, 2, 7),
       (1019, 50, 2, 7),
       (1020, 50, 2, 7),
       (1021, 50, 2, 8),
       (1022, 50, 2, 8),
       (1023, 50, 2, 8),
       (1024, 50, 2, 9),
       (1025, 50, 2, 9),
       (1026, 50, 2, 9),
       (1027, 50, 2, 10),
       (1028, 50, 2, 10),
       (1029, 50, 2, 10),
       (1030, 50, 2, 11),
       (1031, 50, 2, 11),
       (1032, 50, 2, 11),
       (1033, 50, 2, 12),
       (1034, 50, 2, 12),
       (1035, 50, 2, 12);


