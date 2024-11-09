package OnskeGrisen.Model;
import java.util.*;

public class WishList {
    private String userWishListOwner;
    private String userWishListName;
    private String wishListDescription;
    private ArrayList<Wish> wishList;

    public WishList(){};
    public WishList(String userWishListOwner, String userWishListName, String wishListDescription) {
        this.userWishListOwner = userWishListOwner;
        this.userWishListName = userWishListName;
        this.wishListDescription = wishListDescription;
        this.wishList = new ArrayList<>();

    }

    public String getUserWishListOwner() {
        return userWishListOwner;
    }
    public String getUserWishListName() {
        return userWishListName;
    }
    public String getWishListDescription() {
        return wishListDescription;
    }
    public ArrayList<Wish> getWishList() {
        return wishList;
    }

    public void setUserWishListOwner(String userWishListOwner) {
        this.userWishListOwner = userWishListOwner;
    }
    public void setUserWishListName(String userWishListName) {
        this.userWishListName = userWishListName;
    }
    public void setWishListDescription(String wishListDescription) {
        this.wishListDescription = wishListDescription;
    }
    public void setWishList(ArrayList<Wish> wishList) {
        this.wishList = wishList;
    }
}
