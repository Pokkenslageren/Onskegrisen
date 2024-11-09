package OnskeGrisen.Model;
import java.util.*;

public class User {
    private String username, password;
    private ArrayList<WishList> wishLists;
    private int numberOfWishLists;

    public User(){};

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.wishLists = new ArrayList<>();
        this.numberOfWishLists = 0;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public ArrayList<WishList> getWishLists() {
        return wishLists;
    }
    public int getNumberOfWishLists() {
        return numberOfWishLists;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setWishLists(ArrayList<WishList> wishLists) {
        this.wishLists = wishLists;
    }
    public void setNumberOfWishLists(int numberOfWishLists) {
        this.numberOfWishLists = numberOfWishLists;
    }
}
