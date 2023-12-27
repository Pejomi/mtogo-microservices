
-- Drop table if exists
DROP TABLE IF EXISTS couriers;

-- Create table
CREATE TABLE couriers
(
    id        BIGINT AUTO_INCREMENT PRIMARY KEY,
    vehicle   TEXT,
    firstname TEXT,
    lastname  TEXT,
    country   TEXT,
    zip_code   TEXT,
    phone     TEXT,
    email     TEXT,
    active    BOOLEAN
);




INSERT INTO couriers (vehicle, firstname, lastname, country, zip_code, phone, email, active)
VALUES ('Bike', 'Allan', 'Andersen', 'Denmark', '2500', '11223344', 'Allan@Andersen.dk', true),
       ('Car', 'Bent', 'Bentsen', 'Denmark', '2730', '22334455', 'Bent@Bentsen.dk', true),
       ('Bike', 'Carl', 'Carlsen', 'Denmark', '3000', '33445566', 'Carl@Carlsen.dk', true),
       ('Car', 'Dennis', 'Dennisen', 'Denmark', '2800', '44556677', 'Dennis@Dennisen.dk', true),
       ('Bike', 'Eva', 'Eriksen', 'Denmark', '2500', '55667788', 'Eva@Eriksen.dk', true),
       ('Car', 'Frederik', 'Frederiksen', 'Denmark', '2730', '66778899', 'Frederik@Frederiksen.dk', true),
       ('Bike', 'Greta', 'Gretasen', 'Denmark', '3000', '77889900', 'Greta@Gretasen.dk', true),
       ('Car', 'Henrik', 'Henriksen', 'Denmark', '2800', '88990011', 'Henrik@Henriksen.dk', true),
       ('Bike', 'Ingrid', 'Isaksen', 'Denmark', '2500', '00112233', 'Ingrid@Isaksen.dk', true),
       ('Car', 'Jesper', 'Jespersen', 'Denmark', '2730', '11223344', 'Jesper@Jespersen.dk', true),
       ('Bike', 'Karen', 'Karlsen', 'Denmark', '3000', '22334455', 'Karen@Karlsen.dk', true),
       ('Car', 'Lars', 'Larsen', 'Denmark', '2800', '33445566', 'Lars@Larsen.dk', true),
       ('Bike', 'Mia', 'Mikkelsen', 'Denmark', '2500', '44556677', 'Mia@Mikkelsen.dk', true),
       ('Car', 'Niels', 'Nielsen', 'Denmark', '2730', '55667788', 'Niels@Nielsen.dk', true),
       ('Bike', 'Oscar', 'Olsensson', 'Denmark', '3000', '66778899', 'Oscar@Olsensson.dk', true),
       ('Car', 'Pia', 'Petersen', 'Denmark', '2800', '77889900', 'Pia@Petersen.dk', true),
       ('Bike', 'Quentin', 'Quentinsen', 'Denmark', '2500', '88990011', 'Quentin@Quentinsen.dk', true),
       ('Car', 'Rita', 'Ritason', 'Denmark', '2730', '00112233', 'Rita@Ritason.dk', true),
       ('Bike', 'Sven', 'Svensen', 'Denmark', '3000', '11223344', 'Sven@Svensen.dk', true),
       ('Car', 'Tina', 'Tinadatter', 'Denmark', '2800', '22334455', 'Tina@Tinadatter.dk', true),
       ('Bike', 'Ulf', 'Ulfsson', 'Denmark', '2500', '33445566', 'Ulf@Ulfsson.dk', true),
       ('Car', 'Vera', 'Veradatter', 'Denmark', '2730', '44556677', 'Vera@Veradatter.dk', true),
       ('Bike', 'William', 'Williamson', 'Denmark', '3000', '55667788', 'William@Williamson.dk', true),
       ('Car', 'Xena', 'Xenasen', 'Denmark', '2800', '66778899', 'Xena@Xenasen.dk', true),
       ('Bike', 'Yvonne', 'Yvonnedatter', 'Denmark', '2500', '77889900', 'Yvonne@Yvonnedatter.dk', true),
       ('Car', 'Zack', 'Zacksson', 'Denmark', '2730', '88990011', 'Zack@Zacksson.dk', true),
       ('Bike', 'Alice', 'Andersdatter', 'Denmark', '3000', '00112233', 'Alice@Andersdatter.dk', true),
       ('Car', 'Bob', 'Bobsen', 'Denmark', '2800', '11223344', 'Bob@Bobsen.dk', true),
       ('Bike', 'Cathy', 'Cathysen', 'Denmark', '2500', '22334455', 'Cathy@Cathysen.dk', true),
       ('Car', 'David', 'Davidson', 'Denmark', '2730', '33445566', 'David@Davidson.dk', true),
       ('Bike', 'Erik', 'Eriksson', 'Denmark', '3000', '44556677', 'Erik@Eriksson.dk', true),
       ('Car', 'Frida', 'Fridadatter', 'Denmark', '2800', '55667788', 'Frida@Fridadatter.dk', true),
       ('Bike', 'George', 'Georgesson', 'Denmark', '2500', '66778899', 'George@Georgesson.dk', true),
       ('Car', 'Hanna', 'Hannadatter', 'Denmark', '2730', '77889900', 'Hanna@Hannadatter.dk', true),
       ('Bike', 'Ivan', 'Ivansson', 'Denmark', '3000', '88990011', 'Ivan@Ivansson.dk', true),
       ('Car', 'Jasmine', 'Jasminedatter', 'Denmark', '2800', '00112233', 'Jasmine@Jasminedatter.dk', true),
       ('Bike', 'Kevin', 'Kevinsen', 'Denmark', '2500', '11223344', 'Kevin@Kevinsen.dk', true),
       ('Car', 'Laura', 'Lauradatter', 'Denmark', '2730', '22334455', 'Laura@Lauradatter.dk', true),
       ('Bike', 'Michael', 'Michaelsen', 'Denmark', '3000', '33445566', 'Michael@Michaelsen.dk', true),
       ('Car', 'Nina', 'Ninadatter', 'Denmark', '2800', '44556677', 'Nina@Ninadatter.dk', true),
       ('Bike', 'Olaf', 'Olafsson', 'Denmark', '2500', '55667788', 'Olaf@Olafsson.dk', true),
       ('Car', 'Paula', 'Pauladatter', 'Denmark', '2730', '66778899', 'Paula@Pauladatter.dk', true),
       ('Bike', 'Quincy', 'Quincysen', 'Denmark', '3000', '77889900', 'Quincy@Quincysen.dk', true);



