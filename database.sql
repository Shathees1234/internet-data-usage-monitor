CREATE DATABASE data_monitor;

USE data_monitor;

CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50),
    password VARCHAR(50),
    data_limit DOUBLE
);

CREATE TABLE usage (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    usage_date DATE,
    data_used DOUBLE,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

INSERT INTO users(username,password,data_limit)
VALUES ('shathees','1234',2000);
