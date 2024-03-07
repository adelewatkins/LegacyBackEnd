DROP TABLE `items`;
DROP TABLE `carts`;
CREATE TABLE `items`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
	`name` VARCHAR(255),
	`price` INT,
	`type` VARCHAR(255),
	`service_charge` INT,
		);
		CREATE TABLE `carts`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
	`name` VARCHAR(255),
	`price` INT,
	`type` VARCHAR(255),
	`service_charge` INT,
	);
	CREATE TABLE `checkout_carts` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
	`items` VARCHAR
	`total` INT
	`items_id` INT
	FOREIGN KEY (`items_id`) REFERENCES `carts`(
	); 