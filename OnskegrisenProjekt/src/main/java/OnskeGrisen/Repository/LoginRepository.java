package OnskeGrisen.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class LoginRepository {

    String database = "jdbc:mysql://localhost:3306/onskegrisen";
    String dbUsername = "root";
    String dbPassword = "";
    Connection conn;

    public boolean login(String loginUserName, String loginPassword) {
        String query = "SELECT * FROM user WHERE user_name = ? AND user_password = ?";
        boolean isUser = false;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(database, dbUsername, dbPassword);
            if (conn == null) {
                System.out.println("Connection not established");
            }
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, loginUserName);
            pstmt.setString(2, loginPassword);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<String> credentials = new ArrayList<>();
            while (rs.next()) {
                credentials.add(rs.getString("user_name"));
                credentials.add(rs.getString("user_password"));
            }
            if (!credentials.isEmpty()) {
                isUser = true;
            }
            return isUser; // returnerer dobbelt? Skal den ind i if-statement?

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return isUser;
    }
}
