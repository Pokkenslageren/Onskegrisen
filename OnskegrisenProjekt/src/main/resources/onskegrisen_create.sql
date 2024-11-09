-- DROP database `onskegrisen`;

CREATE DATABASE IF NOT EXISTS `onskegrisen`;

USE `onskegrisen`;

CREATE TABLE IF NOT EXISTS `users`(
`user_name` varchar(50) NOT NULL,
`user_password` varchar(50) NOT NULL,
`number_of_wishlists` int NOT NULL,
PRIMARY KEY (`user_name`),
-- FOREIGN KEY (`user_name`, `user_password`) REFERENCES login(`login_name`, `login_password`)
);

CREATE TABLE IF NOT EXISTS `user_wishlist` (
`user_wishlist_owner`varchar(50) NOT NULL,
`user_wishlist_name` varchar(50) NOT NULL,
`wishlist_description` varchar(400),
PRIMARY KEY (`user_wishlist_name`),
-- FOREIGN KEY (`user_wishlists_name`) REFERENCES users(`user_name`)
FOREIGN KEY (`user_wishlist_owner`) REFERENCES users(`user_name`)
);

CREATE TABLE IF NOT EXISTS `wish` (
`wishlist_owner` varchar(50) NOT NULL,
`wishlist_name`varchar(50) NOT NULL,
`wish_title` varchar(50) NOT NULL,
`wish_description` varchar(50) NOT NULL,
`wish_price` double NOT NULL,
`wish_link` varchar(200),
`is_reserved` boolean NOT NULL,
PRIMARY KEY (`wish_title`, `wishlist_name`),
-- FOREIGN KEY (`wishlist_id`, `wishlist_name`) REFERENCES wishlist (`user_wishlist_id`, `user_wishlist_name`)
FOREIGN KEY (`wishlist_name`) REFERENCES user_wishlist(`user_wishlist_name`)

);