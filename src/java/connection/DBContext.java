/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tuana
 */
public class DBContext {

    public static Connection conn;

    private static String jdbcURL = "jdbc:sqlserver://localhost:1433;databaseName=SWP391_G5_V3";
    private static String jdbcUsername = "sa";
    private static String jdbcPassword = "123";

    public static Connection getConnection() {
        if (conn == null) {
            try {
                //load driver và đăng kí nó với ứng dụng (Đăng ký gọi phương thức Class.forName("driverName")
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                //tạo kết nối (connection)
                conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }
    
   public static void main(String[] args) {
        try (Connection connection = DBContext.getConnection()) {
            if (connection != null) {
                System.out.println("Connection successful!");
            } else {
                System.out.println("Connection failed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
    

