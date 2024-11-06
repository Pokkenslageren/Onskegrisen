package OnskeGrisen.Repository;

import OnskeGrisen.Model.User;
import OnskeGrisen.Model.Wish;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class WishRepository {

    String database = "jdbc:mysql://localhost:3306/onskegrisen";
    String dbUsername = "root";
    String dbPassword = "";
    Connection conn;

    public WishRepository(){}

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
            System.out.println(e.getMessage());
        }
    }

    public Wish readWishByTitle(String wishTitle){
        String query = "SELECT * WHERE wish_title = ?";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(database,dbUsername,dbPassword);
            if (conn == null) {
                System.out.println("Connection not established");
            }
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1,wishTitle);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                String readWishListOwner = rs.getString("wishlist_owner");
                String readWishListName = rs.getString("wishlist_name");
                String readWishTitle = rs.getString("wishlist_title");
                String readWishDescription = rs.getString("wishlist_description");
                double readWishPrice = rs.getDouble("wish_price");
                String readWishLink = rs.getString("wish_link");
                boolean isReserved = rs.getBoolean("is_reserved");
                return new Wish(readWishListOwner, readWishListName, readWishTitle, readWishDescription, readWishPrice, readWishLink, isReserved);
            }
        }
        catch (Exception e){
            e.getMessage();
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
            e.getMessage();
        }
    }

    public void deleteWish(Wish wish, String wishTitle){
        String query = "DELETE FROM wish WHERE wish_title = ?";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");;
            conn = DriverManager.getConnection(database, dbUsername, dbPassword);
            if(conn == null) {
                System.out.println("Connection not established");
            }
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1,wishTitle);
            pstmt.executeUpdate();
            pstmt.close();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
