DROP DATABASE IF EXISTS test_database;

CREATE DATABASE test_database;

grant all on test_database.* to 'testuser'@'%';

USE test_database;

DROP TABLE IF EXISTS goods;

CREATE TABLE goods (
    id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    price int NOT NULL
);

INSERT INTO goods (name, price) VALUES ("やくそう", "8");
INSERT INTO goods (name, price) VALUES ("どくけしそう", "10");
INSERT INTO goods (name, price) VALUES ("どうのつるぎ", "50");



DROP TABLE IF EXISTS sales;

CREATE TABLE sales (
    id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    goods_id int NOT NULL,
    num int NOT NULL,
    purchase_date datetime NOT NULL
);

INSERT INTO sales (goods_id, num, purchase_date) VALUES (1, 3, now());
INSERT INTO sales (goods_id, num, purchase_date) VALUES (1, 1, cast('2019-08-03' as date));
INSERT INTO sales (goods_id, num, purchase_date) VALUES (2, 100, cast('2019-01-01' as date));
INSERT INTO sales (goods_id, num, purchase_date) VALUES (3, 2, cast('2100-01-01' as date));
