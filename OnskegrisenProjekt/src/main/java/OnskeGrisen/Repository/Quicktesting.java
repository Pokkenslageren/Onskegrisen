package OnskeGrisen.Repository;

import OnskeGrisen.Model.User;

public class Quicktesting {

    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        WishListRepository wishListRepository = new WishListRepository();
        WishRepository wishRepository = new WishRepository();

        //User testuser = new User("James","mus");
        //userRepository.createUser(testuser);
        //userRepository.updateUser(testuser,"James");
        //userRepository.deleteUser(testuser);

        //System.out.println(userRepository.readUserByUsername("Juller").getPassword());

        //wishListRepository.createWishList("James","Fødselsdag","Tillykke");
        //wishRepository.createWish("James","Fødselsdag","Iphone15", "Ny telefon,", 1000.0, "fafafa", true);
        wishRepository.createWish("James","Juleønsker","Nye briller", "Så jeg kan se", 500.0, "Synoptik.dk", false);

    }
}
