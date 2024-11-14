USE `onskegrisen`;

INSERT INTO `users` (`user_name`, `user_password`, `number_of_wishlists`) VALUES
    ('Kalle', 'kallekode123', '2'),
    ('Julius', 'jullekode123', '2'),
    ('Kasper', 'kasperkode123', '3'),
    ('Magnus', 'magnuskode123', '3')
;

INSERT INTO `user_wishlist` (`user_wishlist_owner`, `user_wishlist_name`, `wishlist_description`) VALUES
    ('Kalle', 'Kalles juleønsker 2024', 'Dette er mine juleønsker for 2024'),
    ('Kalle', 'Kalles fødselsdag', 'Mine fødselsdagsønsker i år.'),
    ('Julius', 'Julles juleønsker 24', 'Glædelig jul.'),
    ('Julius', 'Julius og Signes bryllup', 'Her er vores ønsker til vores bryllup.'),
    ('Kasper', 'Kaspers sommerfest 24', 'Her er hvad jeg ønsker mig til min sommerfest.'),
    ('Kasper', 'Kaspers juleønsker 24', 'Her er hvad jeg ønsker mig til jul.'),
    ('Kasper', 'Kaspers fødselsdag 24', 'Her er hvad jeg ønsker mig til min fødselsdag.'),
    ('Magnus', 'Magnus julefrokost', 'Ønsker til min julefrokost'),
    ('Magnus', 'Magnus jul 24', 'Ønsker til jul'),
    ('Magnus', 'Magnus fødselsdag', 'Ønsker til min fødselsdag.')
;

 -- set foreign_key_checks = 0;
-- set foreign_key_checks = 1;
INSERT INTO `wish` (`wishlist_owner`, `wishlist_name`, `wish_title`, `wish_description`, `wish_price`, `wish_link`, `is_reserved` ) VALUES
    ('Kalle', 'Kalles juleønsker 2024', 'Computer', 'En brugt macbook', '3000', 'https://www.apple.com', '0'),
    ('Kalle', 'Kalles juleønsker 2024', 'Støvsuger', 'En dyson', '4000', 'https://www.dyson.dk', '0'),
    ('Kalle', 'Kalles fødselsdag', 'Biograf tur', 'En biograftur til den nye film', '200', 'https://kino.dk', '0'),
    ('Kalle', 'Kalles fødselsdag', 'Høretelefoner', 'Nye høretelefoner', '500', 'https://www.elgiganten.dk', '0'),
    ('Kalle', 'Kalles fødselsdag', 'Hoodie', 'En sort hoodie', '450', 'https://www.magasin.dk/herre/', '0'),

    ('Julius', 'Julles juleønsker 24', 'Sweatshirt', 'En brun sweatshirt', '600', 'https://www.magasin.dk/herre/', '0'),
    ('Julius', 'Julles juleønsker 24', 'Abonnement', 'Et abonnement til Anders And blade', '200', 'https://www.mitblad.dk/anders-and/ABZRAN/?gad_source=1&gbraid=0AAAAADuA2b7RgUD-DGlOc4d_zG2Xhfui5&gclid=CjwKCAiAudG5BhAREiwAWMlSjA4RNRqBfd-BxXDTAOynuWwK_NAg6O7NIynSIHnOCtKYaEpPkqUsyhoCMXMQAvD_BwE&gclsrc=aw.ds', '0'),
    ('Julius', 'Julles juleønsker 24', 'Julesweater', 'En fed julesweater', '400', 'https://sillysanta.dk/collections/julesweater?tw_source=google&tw_adid=619474370824&tw_campaign=6773307331&gad_source=1&gbraid=0AAAAACw3lPnv9fWTgNGrKMkI8eveBSGBj&gclid=CjwKCAiAudG5BhAREiwAWMlSjAUfGbWkAa7Dq8aImDzukIRNjYElF3DsfUYPVP50jQtEJn4_HKyoGRoCcs8QAvD_BwE', '0'),
    ('Julius', 'Julius og Signes bryllup', 'Rejse', 'En lille rejse', '2500', 'https://www.spies.dk/rejser', '0'),
    ('Julius', 'Julius og Signes bryllup', 'Kaffemaskine', 'En ny kaffemaskine', '2250', 'https://www.nespresso.com/dk/da/kaffemaskine?gad_source=1&gbraid=0AAAAADyepLg8Z4z37B-vnoDNoJ4JQJaGQ&gclid=CjwKCAiAudG5BhAREiwAWMlSjOo3L1FxODPUT9UBU_o9ZWGitCfzmTNH__RK68vZuXkic0OxlmZPfBoCVwYQAvD_BwE&gclsrc=aw.ds', '0'),

    ('Kasper', 'Kaspers sommerfest 2024', 'Grill', 'Grillmeister', '8500', 'https://shorturl.at/LfrcB', '0'),
    ('Kasper', 'Kaspers juleønsker 2024', 'Playstation', 'Sony', '6500', 'https://shorturl.at/7QV1z', '0'),
    ('Kasper', 'Kaspers juleønsker 2024', 'Sofabord', 'Et sofabord fra IKEA', '450', 'https://www.ikea.dk', '0'),
    ('Kasper', 'Kaspers fødselsdag 2024', 'Fladskærms-tv', 'Philips 55', '2500', 'https://shorturl.at/l2eIr', '0'),
    ('Kasper', 'Kaspers fødselsdag 2024', 'Kontorstol', 'En ny kontorstol fra Jysk', '750', 'https://jysk.dk', '0'),
    ('Kasper', 'Kaspers fødselsdag 2024', 'Gardiner', 'Nye gule gardiner fra Jysk', '1750', 'https://jysk.dk', '0'),

    ('Magnus', 'Magnus julefrokost', 'Kaffemaskine', 'Philips', '2150', 'https://shorturl.at/Modrf', '0'),
    ('Magnus', 'Magnus jul 24', 'Sko', 'Nike', '475', 'https://shorturl.at/LcGS0', '0'),
    ('Magnus', 'Magnus jul 24', 'Trøje', 'Fra adidas', '550', 'https://www.adidas.dk', '0'),
    ('Magnus', 'Magnus fødselsdag', 'Jakke', 'Jack & Jones', '800', 'https://www.jackjones.com/da-dk/product/12256887_2042/haette-jakke?gad_source=1&gclid=EAIaIQobChMI6MO9qIfZiQMVCXJHAR18yRhiEAQYBiABEgJ9p_D_BwE', '0'),
    ('Magnus', 'Magnus fødselsdag', 'Reol', 'En ny reol fra jysk', '350', 'https://jysk.dk', '0')
;