package OnskeGrisen.Repository;
import OnskeGrisen.Model.Wish;
import OnskeGrisen.Model.WishList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
        String query = "INSERT INTO user_wishlists VALUES (?, ?, ?)";

        try {
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


        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public WishList readWishListByName(String wishListName) {
        String query = "SELECT * FROM user_wishlists WHERE user_wishlists_name = ?";
    }
}
