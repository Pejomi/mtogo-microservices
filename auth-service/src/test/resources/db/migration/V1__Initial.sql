-- Drop table if exists
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;

-- Create table
CREATE TABLE roles
(
    id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    name TEXT
);

CREATE TABLE users
(
    id               BIGINT AUTO_INCREMENT PRIMARY KEY,
    email            TEXT,
    password         TEXT,
    created_at       TIMESTAMP,
);

CREATE TABLE users_roles
(
    user_id BIGINT,
    role_id BIGINT,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (role_id) REFERENCES roles (id)
);

-- Insert data
INSERT INTO roles (name)
VALUES ('CONSUMER'),
       ('RESTAURANT');