package OnskeGrisen.Service;

import OnskeGrisen.Model.User;
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

    public ArrayList<User> getUserList() {
        return userRepository.getUsers();

    }

    public void registerUser(User user) {
        userRepository.createUser(user);
    }
}
