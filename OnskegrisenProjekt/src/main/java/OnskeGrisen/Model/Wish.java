package OnskeGrisen.Model;

public class Wish {
    private String wishListOwner, wishListName, wishTitle, wishDescription;
    private double wishPrice;
    private String wishLink;
    private boolean isReserved;

    public Wish(){};
    public Wish(String wishListOwner,String wishListName,String wishTitle, String wishDescription, double wishPrice, String wishLink, boolean isReserved) {
        this.wishListOwner = wishListOwner;
        this.wishListName = wishListName;
        this.wishTitle = wishTitle;
        this.wishDescription = wishDescription;
        this.wishPrice = wishPrice;
        this.wishLink = wishLink;
        this.isReserved = false;

    }

    public String getWishListOwner() { return wishListOwner; }
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

    public void setWishOwner(String wishOwner) { this.wishListOwner = wishOwner; }
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
