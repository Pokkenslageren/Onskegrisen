-- DROP database `onskegrisen`;

CREATE DATABASE IF NOT EXISTS `onskegrisen`;

USE `onskegrisen`;

CREATE TABLE IF NOT EXISTS `login` (
`login_id` INT NOT NULL AUTO_INCREMENT, 
`login_name` varchar(50) NOT NULL,
`login_password` varchar(50) NOT NULL,
PRIMARY KEY (`login_id`)
);

CREATE TABLE IF NOT EXISTS `users`(
`user_id` INT NOT NULL AUTO_INCREMENT, 
`user_name` varchar(50) NOT NULL,
`user_password` varchar(50) NOT NULL,
PRIMARY KEY (`user_id`),
-- FOREIGN KEY (`user_name`, `user_password`) REFERENCES login(`login_name`, `login_password`)
FOREIGN KEY (`user_id`) REFERENCES login(`login_id`)
);

CREATE TABLE IF NOT EXISTS `user_wishlists` (
`user_wishlists_id` INT NOT NULL AUTO_INCREMENT, 
`user_wishlists_name` varchar(50) NOT NULL,
`number_of_wishlists` int NOT NULL,
PRIMARY KEY (`user_wishlists_id`),
-- FOREIGN KEY (`user_wishlists_name`) REFERENCES users(`user_name`)
FOREIGN KEY (`user_wishlists_id`) REFERENCES users(`user_id`)
);

CREATE TABLE IF NOT EXISTS `wishlist` (
`wishlist_id` INT NOT NULL AUTO_INCREMENT, 
`wishlist_name` varchar(50) NOT NULL,
`is_reserved` boolean NOT NULL,
PRIMARY KEY (`wishlist_id`),
-- FOREIGN KEY (`wishlist_id`, `wishlist_name`) REFERENCES wishlist (`user_wishlist_id`, `user_wishlist_name`)
FOREIGN KEY (`wishlist_id`) REFERENCES user_wishlists(`user_wishlists_id`)

);