package OnskeGrisen.Model;
import java.util.*;

public class Wish {
    private String wishOwner, wishListName, wishTitle, wishDescription;
    private double wishPrice;
    private String wishLink;
    private boolean isReserved;

    public Wish(String wishOwner,String wishListName,String wishTitle, String wishDescription, double wishPrice, boolean isReserved, String wishLink) {
        this.wishOwner = wishOwner;
        this.wishListName = wishListName;
        this.wishTitle = wishTitle;
        this.wishDescription = wishDescription;
        this.wishPrice = wishPrice;
        this.isReserved = false;
        this.wishLink = wishLink;
    }

    public String getWishOwner() { return wishOwner; }
    public String getWishListName() { return wishListName; }
    public String getWishTitle() {
        return wishTitle;
    }
    public String getWishDescription() {
        return wishDescription;
    }
    public double getWishPrice() {
        return wishPrice;
    }
    public String getWishLink() {
        return wishLink;
    }
    public boolean getIsReserved() {
        return isReserved;
    }

    public void setWishOwner(String wishOwner) { this.wishOwner = wishOwner; }
    public void setWishListName(String wishListName) { this.wishListName = wishListName; }
    public void setWishTitle(String wishTitle) {
        this.wishTitle = wishTitle;
    }
    public void setWishDescription(String wishDescription) {
        this.wishDescription = wishDescription;
    }
    public void setWishPrice(double wishPrice) {
        this.wishPrice = wishPrice;
    }
    public void setWishLink(String wishLink) {
        this.wishLink = wishLink;
    }
    public void setIsReserved(boolean isReserved) {
        this.isReserved = isReserved;
    }
}
