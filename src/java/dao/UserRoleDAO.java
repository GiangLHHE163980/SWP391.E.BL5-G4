/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dao;

import connection.DBContext;
import java.sql.*;

public class UserRoleDAO {
    private Connection connection;

    public UserRoleDAO() {
        DBContext db = new DBContext();
        this.connection = db.getConnection();
    }

    // Phương thức gán vai trò cho người dùng
    public boolean assignRoleToUser(int userID, int roleID) {
        String query = "INSERT INTO UserRoles (UserID, RoleID) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userID);
            stmt.setInt(2, roleID);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Phương thức xóa vai trò của người dùng
    public boolean removeRoleFromUser(int userID, int roleID) {
        String query = "DELETE FROM UserRoles WHERE UserID = ? AND RoleID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userID);
            stmt.setInt(2, roleID);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Phương thức xóa vai trò của người dùng
    public boolean removeRoleFromUserID(int userID) {
        String query = "DELETE FROM UserRoles WHERE UserID = ? ";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userID);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
