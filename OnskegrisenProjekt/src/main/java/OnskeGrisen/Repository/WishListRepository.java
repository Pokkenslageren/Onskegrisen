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

    public void createWishList(String wishListOwner, String wishListName, String wishListDescription) {
        String query = "INSERT INTO user_wishlists (user_wishlists_owner, user_wishlists_name, wishlist_description) VALUES (?, ?, ?)";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");;
            conn = DriverManager.getConnection(database, dbUsername, dbPassword);
            if(conn == null) {
                System.out.println("Connection not established");
            }
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, wishListOwner);
            pstmt.setString(2, wishListName);
            pstmt.setString(3, wishListDescription);
            pstmt.executeUpdate();
            pstmt.close();
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public WishList readWishListByName(User user, String wishListName) {
        String query = "SELECT * FROM user_wishlists WHERE user_wishlists_name = ?";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");;
            conn = DriverManager.getConnection(database, dbUsername, dbPassword);
            if(conn == null) {
                System.out.println("Connection not established");
            }
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, wishListName);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                String readWishListOwner = rs.getString("user_wishlists_owner");
                String readWishListName = rs.getString("user_wishlists_name");
                String readWishListDescription = rs.getString("user_wishlists_description");
                user.getWishLists().add(new WishList(readWishListOwner, readWishListName, readWishListDescription));
                //TODO - Sus metode
                return user.getWishLists().get(user.getWishLists().indexOf(readWishListName));

            }
            rs.close();

        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void updateWishList(User user, String newWishListName, String newWishListDescription, String wishListName) {
        String query = "UPDATE user_wishlists SET user_wishlists_name = ?, wishlist_description = ? WHERE user_wishlists_name = ? AND user_wishlists_owner = ?";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");;
            conn = DriverManager.getConnection(database, dbUsername, dbPassword);
            if(conn == null) {
                System.out.println("Connection not established");
            }
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, newWishListName);
            pstmt.setString(2, newWishListDescription);
            pstmt.setString(3, wishListName);
            pstmt.setString(4, user.getUsername());
            pstmt.executeUpdate();
            pstmt.close();

        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteWishList(User user, String wishListName) {
        String query = "DELETE FROM user_wishlists WHERE user_wishlists_name = ?";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");;
            conn = DriverManager.getConnection(database, dbUsername, dbPassword);
            if(conn == null) {
                System.out.println("Connection not established");
            }
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, wishListName);
            pstmt.executeUpdate();
            pstmt.close();

        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
