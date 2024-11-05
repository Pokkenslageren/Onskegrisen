-- DROP database `onskegrisen`;

CREATE DATABASE IF NOT EXISTS `onskegrisen`;

USE `onskegrisen`;

CREATE TABLE IF NOT EXISTS `login` (
`login_name` varchar(50) NOT NULL,
`login_password` varchar(50) NOT NULL,
PRIMARY KEY (`login_name`)
);

CREATE TABLE IF NOT EXISTS `users`(
`user_name` varchar(50) NOT NULL,
`user_password` varchar(50) NOT NULL,
`number_of_wishlists` int NOT NULL,
PRIMARY KEY (`user_name`),
-- FOREIGN KEY (`user_name`, `user_password`) REFERENCES login(`login_name`, `login_password`)
FOREIGN KEY (`user_name`) REFERENCES login(`login_name`)
);

CREATE TABLE IF NOT EXISTS `user_wishlists` (
`user_wishlists_owner`varchar(50) NOT NULL,
`user_wishlists_name` varchar(50) NOT NULL,
PRIMARY KEY (`user_wishlists_name`),
-- FOREIGN KEY (`user_wishlists_name`) REFERENCES users(`user_name`)
FOREIGN KEY (`user_wishlists_owner`) REFERENCES users(`user_name`)
);

CREATE TABLE IF NOT EXISTS `wishlist` (
`wishlist_owner` varchar(50) NOT NULL,
`wishlist_name` varchar(50) NOT NULL,
`is_reserved` boolean NOT NULL,
PRIMARY KEY (`wishlist_name`),
-- FOREIGN KEY (`wishlist_id`, `wishlist_name`) REFERENCES wishlist (`user_wishlist_id`, `user_wishlist_name`)
FOREIGN KEY (`wishlist_name`) REFERENCES user_wishlists(`user_wishlists_name`)

);