import java.util.*;
import java.sql.*;

/**
 * Database to store user account information
 * 
 * @author Lauren Allen
 * @version 1.0
 */

public class UserAccountDB {

  public static void main() {

  }

  /**
   * Verifies account login information exists and password is correct
   */
  public boolean checkUserInfo(String givenUsername, String givenPassword) throws SQLException {

    try (Connection conn = DriverManager.getConnection(System.getenv("JDBC_URL"),
        System.getenv("JDBC_USER"),
        System.getenv("JDBC_PW"))) {

      String selectQuery = "SELECT Username, Password FROM user_accounts WHERE Username = givenUsername AND Password = givenPassword";

      try (Statement stmt = conn.createStatement()) {
        ResultSet result = stmt.executeQuery(selectQuery);
        while (result.next()) {
          String resultUsername = result.getString(1);
          String resultPassword = result.getString(2);
          if (givenUsername.equals(resultUsername) & givenPassword.equals(resultPassword)) {
            return true;
          }
        }
        return false;
      }
    }

  }

  /**
   * Inserts username and password into user_account
   */
  public void addUser(String username, String password) throws SQLException {
    try (Connection conn = DriverManager.getConnection(System.getenv("JDBC_URL"),
        System.getenv("JDBC_USER"),
        System.getenv("JDBC_PW"))) {

      String insertquery = "INSERT INTO user_accounts (Username, Password)";

      try (PreparedStatement pstmt = conn.prepareStatement(insertquery)) {
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        pstmt.executeUpdate();
        System.out.println("Added user account");
      }
      conn.commit();
    }
  }

}
