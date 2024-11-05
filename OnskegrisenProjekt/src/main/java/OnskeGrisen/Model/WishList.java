package OnskeGrisen.Model;
import java.util.*;

public class WishList {
    private String wishListOwner;
    private String wishListName;
    private String wishListDescription;
    private ArrayList<Wish> wishList;

    public WishList(String wishListOwner, String wishListName, String wishListDescription) {
        this.wishListOwner = wishListOwner;
        this.wishListName = wishListName;
        this.wishListDescription = wishListDescription;
        this.wishList = new ArrayList<>();

    }

    public String getWishListOwner() {
        return wishListOwner;
    }
    public String getWishListName() {
        return wishListName;
    }
    public String getWishListDescription() {
        return wishListDescription;
    }
    public ArrayList<Wish> getWishList() {
        return wishList;
    }

    public void setWishListOwner(String wishListOwner) {
        this.wishListOwner = wishListOwner;
    }
    public void setWishListName(String wishListName) {
        this.wishListName = wishListName;
    }
    public void setWishListDescription(String wishListDescription) {
        this.wishListDescription = wishListDescription;
    }
    public void setWishList(ArrayList<Wish> wishList) {
        this.wishList = wishList;
    }
}
