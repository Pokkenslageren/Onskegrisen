package Model;
import java.util.*;

public class WishList {
    private int wishListId;
    private String wishListName;
    private String wishListDescription;
    private ArrayList<Wish> wishList;
    Random rdm = new Random();

    public WishList(String wishListName, String wishListDescription) {
        this.wishListId = rdm.nextInt(100);
        this.wishListName = wishListName;
        this.wishListDescription = wishListDescription;
        this.wishList = new ArrayList<>();
    }

    public int getWishListId() {
        return wishListId;
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

    public void setWishListId(int wishListId) {
        this.wishListId = wishListId;
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
