DROP TABLE IF EXISTS `order_items`;
CREATE TABLE `order_items`
(
    `id`           bigint AUTO_INCREMENT PRIMARY KEY,
    `menu_item_id` bigint,
    `price`        decimal(19, 2),
    `quantity`     int,
    `order_id`     bigint
);

INSERT INTO `order_items` (`menu_item_id`, `price`, `quantity`)
VALUES (1000, 50, 2),
       (1001, 45, 1),
       (1002, 55, 2);

DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`
(
    `id`             bigint AUTO_INCREMENT PRIMARY KEY,
    `consumer_id`    bigint,
    `restaurant_id`  bigint,
    `order_state`    varchar(255),
    `price`          decimal(19, 2),
    `order_items_id` bigint,
    FOREIGN KEY (`order_items_id`) REFERENCES `order_items` (`id`)
);

INSERT INTO `orders` (`consumer_id`, `restaurant_id`, `order_state`, `price`, `order_items_id`)
VALUES (1, 1, 'CREATED', 100, 1),
       (2, 2, 'CREATED', 45, 2),
       (3, 3, 'CREATED', 110, 3);
