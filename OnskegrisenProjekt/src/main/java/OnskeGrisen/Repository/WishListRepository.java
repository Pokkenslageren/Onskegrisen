package OnskeGrisen.Repository;
import OnskeGrisen.Model.Wish;
import OnskeGrisen.Model.WishList;
import OnskeGrisen.Model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class WishListRepository {

    ArrayList<Wish> wishes = new ArrayList<>();
    String database = "jdbc:mysql://localhost:3306/onskegrisen";
    String dbUsername = "root";
    String dbPassword = "";
    Connection conn;

    public WishListRepository() {

    }

    public void createWishList(String userWishListOwner, String userWishListName, String wishListDescription) {
        String query = "INSERT INTO user_wishlists (user_wishlists_owner, user_wishlists_name, wishlist_description) VALUES (?, ?, ?)";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(database, dbUsername, dbPassword);
            if(conn == null) {
                System.out.println("Connection not established");
            }
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, userWishListOwner);
            pstmt.setString(2, userWishListName);
            pstmt.setString(3, wishListDescription);
            pstmt.executeUpdate();
            pstmt.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public WishList readWishListByName(User user, String userWishListName) {
        String query = "SELECT * FROM user_wishlists WHERE user_wishlist_owner = ? AND user_wishlist_name = ?";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(database, dbUsername, dbPassword);
            if(conn == null) {
                System.out.println("Connection not established");
            }
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, userWishListName);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String readUserWishListOwner = rs.getString("user_wishlist_owner");
                String readUserWishListName = rs.getString("user_wishlist_name");
                String readWishListDescription = rs.getString("wishlist_description");

                return new WishList(readUserWishListOwner, readUserWishListName, readWishListDescription);

            }
            rs.close();
            pstmt.close();
            conn.close();

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateWishList(User user, String newUserWishListName, String newWishListDescription, String userWishListName) {
        String query = "UPDATE user_wishlists SET user_wishlists_name = ?, wishlist_description = ? WHERE user_wishlists_name = ? AND user_wishlists_owner = ?";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(database, dbUsername, dbPassword);
            if(conn == null) {
                System.out.println("Connection not established");
            }
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, newUserWishListName);
            pstmt.setString(2, newWishListDescription);
            pstmt.setString(3, userWishListName);
            pstmt.setString(4, user.getUsername());
            pstmt.executeUpdate();
            pstmt.close();

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteWishList(User user, String userWishListName) {
        String query = "DELETE FROM user_wishlists WHERE user_wishlists_name = ? AND user_wishlists_owner = ?";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(database, dbUsername, dbPassword);
            if(conn == null) {
                System.out.println("Connection not established");
            }
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, userWishListName);
            pstmt.setString(2, user.getUsername());
            pstmt.executeUpdate();
            pstmt.close();

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
