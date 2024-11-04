package Model;
import java.util.*;

public class Wish {
    private String wishName, wishDescription;
    private double wishPrice;
    private String wishLink;
    private boolean isReserved;

    public Wish(String wishName, String wishDescription, double wishPrice, boolean isReserved, String wishLink) {
        this.wishName = wishName;
        this.wishDescription = wishDescription;
        this.wishPrice = wishPrice;
        this.isReserved = false;
        this.wishLink = wishLink;
    }

    public String getWishName() {
        return wishName;
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

    public void setWishName(String wishName) {
        this.wishName = wishName;
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
