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
    String dbPassword = "Illcosby91";
    Connection conn;


    /**
     * Creates a new wishlist entry in the database table 'user-wishlist'
     * @param userWishListOwner
     * @param userWishListName
     * @param wishListDescription
     */
    public void createWishList(String userWishListOwner, String userWishListName, String wishListDescription) {
        String query = "INSERT INTO user_wishlist (user_wishlist_owner, user_wishlist_name, wishlist_description) VALUES (?, ?, ?)";

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

    /**
     * Reads a User and a wishlist name and returns a wishlist description
     * @param user
     * @param userWishListName
     * @return A wishlist description associated with the params
     */
    public String readWishListDescription(User user, String userWishListName) {
        String query = "SELECT * FROM user_wishlist WHERE user_wishlist_owner = ? AND user_wishlist_name = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(database, dbUsername, dbPassword);
            if (conn == null) {
                System.out.println("Connection not established");
            }
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, userWishListName);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                return rs.getString("wishlist_description");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Reads a user and a wishlistname and return an associated wishlist entry
     * @param user
     * @param userWishListName
     * @return A wishlist entry
     */
    public WishList readWishListByName(User user, String userWishListName) {
        String query = "SELECT * FROM user_wishlist WHERE user_wishlist_owner = ? AND user_wishlist_name = ?";

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

    /**
     * Updates a wishlist entry based on the params
     * @param user
     * @param newUserWishListName
     * @param newWishListDescription
     * @param userWishListName
     */
    public void updateWishList(User user, String newUserWishListName, String newWishListDescription, String userWishListName) {
        String query = "UPDATE user_wishlist SET user_wishlist_name = ?, wishlist_description = ? WHERE user_wishlist_name = ? AND user_wishlist_owner = ?";

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

    /**
     * Deletes a wishlist entry basen on username and wishlistname
     * @param userName
     * @param userWishListName
     */
    public void deleteWishList(String userName, String userWishListName) {
        String constraintsOffQuery = "SET foreign_key_checks = 0;";
        String constraintsOnQuery = "SET foreign_key_checks = 1;";
        String query = "DELETE FROM user_wishlist WHERE user_wishlist_name = ? AND user_wishlist_owner = ?";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(database, dbUsername, dbPassword);
            if(conn == null) {
                System.out.println("Connection not established");
            }
            PreparedStatement pstmtoff = conn.prepareStatement(constraintsOffQuery);
            PreparedStatement pstmton = conn.prepareStatement(constraintsOnQuery);
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, userWishListName);
            pstmt.setString(2, userName);
            pstmtoff.executeUpdate();
            pstmt.executeUpdate();
            pstmton.executeUpdate();
            pstmt.close();

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the owner of an arraylist of wishlists
     * @param user
     */
    public void setOwnerWishlists(User user){
        String query = "SELECT * FROM user_wishlist WHERE user_wishlist_owner = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(database, dbUsername, dbPassword);
            if (conn == null) {
                System.out.println("Connection not established");
            }
            ArrayList<WishList> allWishLists = new ArrayList<>();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, user.getUsername());
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                String readUserWishListOwner = rs.getString("user_wishlist_owner");
                String readUserWishListName = rs.getString("user_wishlist_name");
                String readWishListDescription = rs.getString("wishlist_description");
                allWishLists.add(new WishList(readUserWishListOwner,readUserWishListName,readWishListDescription));
            }
            user.setWishLists(allWishLists);
            pstmt.close();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
