package OnskeGrisen.Repository;

import OnskeGrisen.Model.User;
import java.sql.*;

import java.util.*;

public class UserRepository {

    ArrayList<User> users = new ArrayList<>();
    String database = "jdbc:mysql://localhost:3306/onskegrisen";
    String dbUsername = "root";
    String dbPassword = "root";
    Connection conn;


    public void createUser(User user) {

/*        String wantedUserName = user.getUsername();
        if (readUserByUsername(wantedUserName).getUsername().equals(wantedUserName)) {
            System.out.println("Username is not available.");

        } else {*/
            String query = "INSERT INTO `users` (user_name, user_password, number_of_wishlists) VALUES (?, ?, ?);";

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(database, dbUsername, dbPassword);
                if (conn == null) {
                    System.out.println("connection not established.");
                }
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, user.getUsername());
                pstmt.setString(2, user.getPassword());
                pstmt.setInt(3,0);
                pstmt.executeUpdate();
                pstmt.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        //}
    }


    public User readUserByUsername(String userName) {
        String query = "SELECT * FROM users WHERE user_name = ?;";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(database, dbUsername, dbPassword);
            ArrayList<User> tempUserList = new ArrayList<>();
            if (conn == null) {
                System.out.println("connection not established.");
            }
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, userName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String readUserName = rs.getString("user_name");
                String readPassword = rs.getString("user_password");
                //users.add(new User(readUserName, readPassword));
                //return users.get(users.indexOf(userName));
                tempUserList.add(new User(readUserName,readPassword));
                //return users.get(users.size());
                return tempUserList.get(0);
            }
            rs.close();

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public void updateUser(User user, String newUserName) {
        String query = "UPDATE users SET user_name = ? WHERE user_name = ?;";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(database, dbUsername, dbPassword);
            if (conn == null) {
                System.out.println("connection not established.");
            }
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, newUserName);
            pstmt.setString(2, user.getUsername());
            pstmt.executeUpdate();
            pstmt.close();

        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }

    public void deleteUser(String username) {
        String constraintsOffQuery = "SET foreign_key_checks = 0;";
        String query = "DELETE FROM users WHERE user_name = ?;";
        String constraintsOnQuery = "SET foreign_key_checks = 1;";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(database, dbUsername, dbPassword);
            if (conn == null) {
                System.out.println("Connection not established.");
            }
            PreparedStatement pstmtoff = conn.prepareStatement(constraintsOffQuery);
            PreparedStatement pstmton = conn.prepareStatement(constraintsOnQuery);
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username);
            pstmtoff.executeUpdate();
            pstmt.executeUpdate();
            pstmton.executeUpdate();
            pstmt.close();

        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }

    public ArrayList<User> readAllUsers() {

        String query = "SELECT * FROM users;";
        ArrayList<User> tempUserList = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(database, dbUsername, dbPassword);

            if (conn == null) {
                System.out.println("Connection not established.");
            }
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            // Loop through the result set and add each user to the list
            while (rs.next()) {
                String readUserName = rs.getString("user_name");
                String readPassword = rs.getString("user_password");
                tempUserList.add(new User(readUserName, readPassword));
            }

            // Close resources after looping
            rs.close();
            pstmt.close();
            conn.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
        return tempUserList; // Return the list after all users have been added
    }

}
