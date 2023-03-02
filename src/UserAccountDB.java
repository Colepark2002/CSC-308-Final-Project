import java.sql.*;

/**
 * Database to store user account information
 * 
 * @author Lauren Allen
 * @version 1.0
 */

public class UserAccountDB {

  private static Connection conn;

  public static void main() throws SQLException {

    // UserAccountDB db = new UserAccountDB();

    try {
      connect();

    } finally {
      if (conn != null) {
        System.out.println("Cannot Connect");
        disconnect();

      }
    }
  }

  private static void connect() throws SQLException {

    System.out.println("Connecting to database...");
    String db_url = "jdbc:mysql://10.144.63.183:3306/mysqljdbc";
    String db_user = "tutor_app";
    String db_password = "tutor_connect";
    conn = DriverManager.getConnection(db_url, db_user, db_password);
    System.out.println("Connection vaild");
  }

  private static void disconnect() throws SQLException {

    System.out.println("Closing database connection");
    conn.close();
  }

  /**
   * Verifies account login information exists and password is correct
   */
  public boolean checkUserLogin(String givenUsername, String givenPassword) throws SQLException {

    String db_url = "jdbc:mysql://10.144.63.183:3306/mysqljdbc";
    String db_user = "tutor_app";
    String db_password = "tutor_connect";

    try (Connection conn = DriverManager.getConnection(db_url, db_user, db_password)) {

      String selectQuery = "SELECT Username, Password FROM accounts WHERE Username = givenUsername AND Password = givenPassword";

      try (Statement stmt = conn.createStatement()) {
        ResultSet result = stmt.executeQuery(selectQuery);
        while (result.next()) {
          String resultUsername = result.getString(2);
          String resultPassword = result.getString(3);
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

    String db_url = "jdbc:mysql://10.144.63.183:3306/mysqljdbc";
    String db_user = "tutor_app";
    String db_password = "tutor_connect";

    try (Connection conn = DriverManager.getConnection(db_url, db_user, db_password)) {

      String insertquery = "INSERT INTO accounts VALUES (id, Username, Password, proficiency)";

      try (PreparedStatement pstmt = conn.prepareStatement(insertquery)) {
        pstmt.setString(1, null);
        pstmt.setString(2, username);
        pstmt.setString(3, password);
        pstmt.setInt(4, 0);

        pstmt.executeUpdate();
        System.out.println("Added user account");
      }
      conn.commit();
    }
  }

}
