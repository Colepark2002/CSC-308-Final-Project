import java.sql.*;

/**
 * Database to store user account information
 * 
 * @author Lauren Allen
 * @author Cole Park
 * @version 1.0
 */

public class UserAccountDB {

  private static Connection conn;

  public void connect() throws SQLException {

    try {
      System.out.println("Connecting to database...");
      String db_url = "jdbc:mysql://sql9.freesqldatabase.com:3306/sql9602248";
      String db_user = "sql9602248";
      String db_password = "xKH3V5Hh6f";
      conn = DriverManager.getConnection(db_url, db_user, db_password);
      System.out.println("Connection valid");

    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  private static void disconnect() throws SQLException {

    System.out.println("Closing database connection");
    conn.close();
  }

  /**
   * Verifies account login information exists and password is correct
   */
  public boolean checkUserLogin(String givenUsername, String givenPassword) throws SQLException {

    String db_url = "jdbc:mysql://sql9.freesqldatabase.com:3306/sql9602248";
    String db_user = "sql9602248";
    String db_password = "xKH3V5Hh6f";

    try (Connection conn = DriverManager.getConnection(db_url, db_user, db_password)) {

      String selectQuery = "SELECT Username, Password FROM users WHERE Username = ? AND Password = ?";

      try (PreparedStatement ps = conn.prepareStatement(selectQuery)) {
        ps.setString(1, givenUsername);
        ps.setString(2, givenPassword);
        ResultSet result = ps.executeQuery();
        while (result.next()) {
          String resultUsername = result.getString("Username");
          String resultPassword = result.getString("Password");
          if (givenUsername.equals(resultUsername) && givenPassword.equals(resultPassword)) {
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
  public boolean addUser(String username, String password) throws SQLException {

    String db_url = "jdbc:mysql://sql9.freesqldatabase.com:3306/sql9602248";
    String db_user = "sql9602248";
    String db_password = "xKH3V5Hh6f";

    try (Connection conn = DriverManager.getConnection(db_url, db_user, db_password)) {

      String insertquery = "INSERT INTO users VALUES (?, ?, ?, ?)";

      try (PreparedStatement pstmt = conn.prepareStatement(insertquery)) {
        pstmt.setString(1, null);
        pstmt.setString(2, username);
        pstmt.setString(3, password);
        pstmt.setInt(4, 0);

        pstmt.executeUpdate();
        System.out.println("Added user account");
      } catch (SQLException e) {
        System.out.println(e.getMessage());
        return false;
      }
      return true;
    }
  }

  /**
   * Gets proficiency of a given user
   */
  public Integer getProficiency(String username) throws SQLException {

    String db_url = "jdbc:mysql://sql9.freesqldatabase.com:3306/sql9602248";
    String db_user = "sql9602248";
    String db_password = "xKH3V5Hh6f";

    try (Connection conn = DriverManager.getConnection(db_url, db_user, db_password)) {

      String selectQuery = "SELECT Proficiency FROM users WHERE Username = ?";

      try (PreparedStatement ps = conn.prepareStatement(selectQuery)) {
        ps.setString(1, username);
        ResultSet result = ps.executeQuery();
        return result.getInt("Proficiency");
      }
    }
  }

  /**
   * Sets proficiency of a given user
   */
  public void setProficiency(String username, Integer proficiency) throws SQLException {

    String db_url = "jdbc:mysql://sql9.freesqldatabase.com:3306/sql9602248";
    String db_user = "sql9602248";
    String db_password = "xKH3V5Hh6f";

    try (Connection conn = DriverManager.getConnection(db_url, db_user, db_password)) {

      String updateQuery = "UPDATE table SET proficiency = ?, WHERE username = ?";

      try (PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {
        pstmt.setInt(1, proficiency);
        pstmt.setString(2, username);

        pstmt.executeUpdate();
        System.out.println("Added user account");
      } catch (SQLException e) {
        System.out.println(e.getMessage());
      }
    }
  }

}
