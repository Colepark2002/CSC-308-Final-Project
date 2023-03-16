package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import main.java.UserAccountDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class DBTests {

  private static Connection conn;

  public void connect() throws SQLException {

    try {
      System.out.println("Connecting to database...");
      String db_url = "jdbc:mysql://us-east.connect.psdb.cloud/tutor_database?sslMode=VERIFY_IDENTITY";
      String db_user = "iclc7hopn03jo4dnefaq";
      String db_password = "pscale_pw_hV1NQ465waoiY2mUeitXKOhSr6XrytNU13LIGiTBiuc";
      conn = DriverManager.getConnection(db_url, db_user, db_password);
      conn.setAutoCommit(false);
      conn.commit();
      System.out.println("Connection valid");

    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  private static void disconnect() throws SQLException {

    System.out.println("Closing database connection");
    conn.rollback();
    conn.close();
  }

  @Test
  void checkInvalidUserTest() {
    UserAccountDB db = new UserAccountDB();
    connect();
    Boolean result = db.checkUserLogin("DNE", "test");
    Assertions.assertEquals(false, result);
    disconnect();
  }

  @Test
  void checkValidUserTest() {
    UserAccountDB db = new UserAccountDB();
    connect();
    Boolean result = db.checkUserLogin("testUser", "1234");
    Assertions.assertEquals(true, result);
    disconnect();
  }

  // @Test
  // void addUserTest() {
  // UserAccountDB db = new UserAccountDB();
  // connect();
  // db.addUser("addedUser", "temp");
  // Boolean result = db.checkUserLogin("addedUser", "temp");
  // Assertions.assertEquals(true, result);
  // disconnect();

  // }

  @Test
  void getProfTest1() {
    UserAccountDB db = new UserAccountDB();
    connect();
    Integer result = db.getProficiency("testUser");
    Assertions.assertEquals(0, result);
    disconnect();
  }

  @Test
  void getProfTest2() {
    UserAccountDB db = new UserAccountDB();
    connect();
    Integer result = db.getProficiency("lallen");
    Assertions.assertEquals(3, result);
    disconnect();
  }

  @Test
  void setProfTest() {
    UserAccountDB db = new UserAccountDB();
    connect();
    db.setProficiency("lallen", 555);
    Integer result = db.getProficiency("lallen");
    Assertions.assertEquals(555, result);
    disconnect();
  }
}