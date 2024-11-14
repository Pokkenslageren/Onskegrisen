package OnskeGrisen.Repository;

import OnskeGrisen.Model.Wish;
import OnskeGrisen.Model.WishList;
import OnskeGrisen.Model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class WishRepository {

    String database = "jdbc:mysql://localhost:3306/onskegrisen";
    String dbUsername = "root";
    String dbPassword = "Illcosby91";
    Connection conn;


    public void createWish(String wishListOwner, String wishListName, String wishTitle, String wishDescription, double wishPrice, String wishLink, boolean isReserved) {
        String query = "INSERT INTO wish (wishlist_owner, wishlist_name, wish_title, wish_description, wish_price, wish_link, is_reserved) VALUES(?,?,?,?,?,?,?)";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(database, dbUsername, dbPassword);
            if (conn == null) {
                System.out.println("Connection not established");
            }
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1,wishListOwner);
            pstmt.setString(2,wishListName);
            pstmt.setString(3,wishTitle);
            pstmt.setString(4,wishDescription);
            pstmt.setDouble(5,wishPrice);
            pstmt.setString(6,wishLink);
            pstmt.setBoolean(7,isReserved);
            pstmt.executeUpdate();
            pstmt.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Wish readWishByTitle(String wishTitle, String wishListName){
        String query = "SELECT * FROM wish WHERE wish_title = ? AND wishlist_name =?";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(database,dbUsername,dbPassword);
            if (conn == null) {
                System.out.println("Connection not established");
                return null;
            }
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1,wishTitle);
            pstmt.setString(2,wishListName);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String readWishListOwner = rs.getString("wishlist_owner");
                String readWishListName = rs.getString("wishlist_name");
                String readWishTitle = rs.getString("wish_title");
                String readWishDescription = rs.getString("wish_description");
                double readWishPrice = rs.getDouble("wish_price");
                String readWishLink = rs.getString("wish_link");
                boolean isReserved = rs.getBoolean("is_reserved");

                return new Wish(readWishListOwner, readWishListName, readWishTitle, readWishDescription, readWishPrice, readWishLink, isReserved);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void updateWish(Wish wish, String newWishTitle, String newWishDescription, double newWishPrice, String newWishLink, boolean newIsReserved){
        String query = "UPDATE wish SET wish_title = ?, wish_description = ?, wish_price = ?, wish_link = ?, is_reserved = ? WHERE wish_title = ? AND wishlist_name = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(database,dbUsername,dbPassword);
            if (conn == null) {
                System.out.println("Connection not established");
            }
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1,newWishTitle);
            pstmt.setString(2,newWishDescription);
            pstmt.setDouble(3,newWishPrice);
            pstmt.setString(4,newWishLink);
            pstmt.setBoolean(5,newIsReserved);
            pstmt.setString(6,wish.getWishTitle());
            pstmt.setString(7,wish.getWishListName());
            pstmt.executeUpdate();
            pstmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteWish(String wishListName, String wishTitle){
        String query = "DELETE FROM wish WHERE wish_title = ? AND wishlist_name = ?";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(database, dbUsername, dbPassword);
            if(conn == null) {
                System.out.println("Connection not established");
            }
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1,wishTitle);
            pstmt.setString(2,wishListName);
            pstmt.executeUpdate();
            pstmt.close();

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    /**
     * Finds all wishes in a given wishlist and puts them in the wishlist
     * @param user
     * @param wishList
     */
    public ArrayList<Wish> fetchWishesFromWishlist(User user, String wishList){
        String query = "SELECT * FROM wish WHERE wishlist_owner = ? AND wishlist_name = ?";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(database, dbUsername, dbPassword);
            if(conn == null) {
                System.out.println("Connection not established");
            }
            ArrayList<Wish> allWishesInWishlist = new ArrayList<>();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1,user.getUsername());
            pstmt.setString(2,wishList);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                String readWishListOwner = rs.getString("wishlist_owner");
                String readWishListName = rs.getString("wishlist_name");
                String readWishTitle = rs.getString("wish_title");
                String readWishDescription = rs.getString("wish_description");
                double readWishPrice = rs.getDouble("wish_price");
                String readWishLink = rs.getString("wish_link");
                boolean isReserved = rs.getBoolean("is_reserved");

                allWishesInWishlist.add(new Wish(readWishListOwner,readWishListName,readWishTitle,readWishDescription,readWishPrice,readWishLink,isReserved));
            }
            return allWishesInWishlist;


        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void reserveWish(String wishListOwner, String wishListName, String wish_title){
        String query = "UPDATE wish SET is_reserved = 1 WHERE wishlist_owner = ? AND wishlist_name = ? AND wish_title = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(database, dbUsername, dbPassword);
            if (conn == null) {
                System.out.println("Connection not established");
            }
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1,wishListOwner);
            pstmt.setString(2,wishListName);
            pstmt.setString(3,wish_title);
            pstmt.executeUpdate();
            pstmt.close();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
