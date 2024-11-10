package OnskeGrisen.Service;

import OnskeGrisen.Model.User;
import OnskeGrisen.Model.Wish;
import OnskeGrisen.Model.WishList;
import OnskeGrisen.Repository.LoginRepository;
import OnskeGrisen.Repository.UserRepository;
import OnskeGrisen.Repository.WishListRepository;
import OnskeGrisen.Repository.WishRepository;
import org.springframework.stereotype.Service;
import java.util.*;


import java.lang.reflect.Array;

@Service
public class OnskeService {

    private final UserRepository userRepository = new UserRepository();
    private final WishRepository wishRepository = new WishRepository();
    private final WishListRepository wishListRepository = new WishListRepository();
    private final LoginRepository loginRepository = new LoginRepository();

    public ArrayList<User> readAllUsers() {
        return userRepository.readAllUsers();
    }

    public User readUser(String userName) {
        return userRepository.readUserByUsername(userName);
    }

    public void updateUser(User user, String userName) {
        userRepository.updateUser(user, userName);
    }

    public void deleteUser(String user) {
        userRepository.deleteUser(user);
    }

    public void deleteWishList(String userName, String wishListName){
        wishListRepository.deleteWishList(userName,wishListName);
    }

    public void registerUser(User user) {
        userRepository.createUser(user);
    }

    public WishList readWishlist(User user, String name){return wishListRepository.readWishListByName(user, name);}

    public void fetchOwnerWishLists(User user){
        wishListRepository.setOwnerWishlists(user);
    }

    public ArrayList<Wish> fetchWishesFromWishlist(User user, String wishList){
        return wishRepository.fetchWishesFromWishlist(user, wishList);
    }

    public void createWishList(String userWishListOwner, String userWishListName, String wishListDescription){
        wishListRepository.createWishList(userWishListOwner,userWishListName,wishListDescription);
    }

    public void createWish(String wishListOwner, String wishListName, String wishTitle, String wishDescription, double wishPrice, String wishLink, boolean isReserved){
        wishRepository.createWish(wishListOwner,wishListName,wishTitle,wishDescription,wishPrice,wishLink,isReserved);
    }

    public void reserveWish(String wishListOwner, String wishListName, String wish_title){
        wishRepository.reserveWish(wishListOwner,wishListName,wish_title);
    }
}
