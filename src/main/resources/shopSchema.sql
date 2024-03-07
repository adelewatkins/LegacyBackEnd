DROP TABLE `item` CASCADE;
DROP TABLE `cart` CASCADE;




CREATE TABLE `cart` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR
);

CREATE TABLE `item` (
`cart_id` INT,
`id` INT PRIMARY KEY AUTO_INCREMENT,
`price` DOUBLE,
`quantity` INT,
`name` VARCHAR,
FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`)
);