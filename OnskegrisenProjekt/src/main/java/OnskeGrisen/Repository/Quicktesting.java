package OnskeGrisen.Repository;

import OnskeGrisen.Model.User;

public class Quicktesting {

    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();

        User testuser = new User("James","mus");
        userRepository.createUser(testuser);
        //userRepository.updateUser(testuser,"James");
        //userRepository.deleteUser(testuser);

        //System.out.println(userRepository.readUserByUsername("Juller").getPassword());
    }
}
