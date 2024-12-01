/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.DBContext;
import model.User;
import java.sql.*;
import java.util.*;
import model.Role;

public class UserDAO {

    private Connection connection;

    public UserDAO() {
        DBContext db = new DBContext();
        this.connection = db.getConnection();
    }

    public List<User> getUsersByRole(String roleName) {
        List<User> users = new ArrayList<>();
        String query = "SELECT u.UserID, u.FullName, u.Email, u.PhoneNumber, u.Address, u.Avatar, u.IsActive, u.CreatedAt, u.UpdatedAt, r.RoleName "
                + "FROM Users u "
                + "JOIN UserRoles ur ON u.UserID = ur.UserID "
                + "JOIN Roles r ON ur.RoleID = r.RoleID "
                + "WHERE r.RoleName = ? AND u.IsActive = 1";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, roleName);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUserID(rs.getInt("UserID"));
                user.setFullName(rs.getString("FullName"));
                user.setEmail(rs.getString("Email"));
                user.setPhoneNumber(rs.getString("PhoneNumber"));
                user.setAddress(rs.getString("Address"));
                user.setAvatar(rs.getString("Avatar"));
                user.setIsActive(rs.getBoolean("IsActive"));
                user.setCreatedAt(rs.getTimestamp("CreatedAt"));
                user.setUpdatedAt(rs.getTimestamp("UpdatedAt"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM Users";

        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                User user = new User();
                user.setUserID(rs.getInt("UserID"));
                user.setFullName(rs.getString("FullName"));
                user.setEmail(rs.getString("Email"));
                user.setPasswordHash(rs.getString("PasswordHash"));
                user.setPhoneNumber(rs.getString("PhoneNumber"));
                user.setAddress(rs.getString("Address"));
                user.setAvatar(rs.getString("Avatar"));
                user.setIsActive(rs.getBoolean("IsActive"));
                user.setCreatedAt(rs.getTimestamp("CreatedAt"));
                user.setUpdatedAt(rs.getTimestamp("UpdatedAt"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public boolean addUser(User user, String roleName) {
        // Câu lệnh SQL để thêm người dùng vào bảng Users
        String query = "INSERT INTO Users (FullName, Email, PasswordHash, PhoneNumber, Address, Avatar, IsActive) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            // Thêm người dùng vào bảng Users
            stmt.setString(1, user.getFullName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPasswordHash());
            stmt.setString(4, user.getPhoneNumber());
            stmt.setString(5, user.getAddress());
            stmt.setString(6, user.getAvatar());
            stmt.setBoolean(7, user.getIsActive());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    RoleDAO roleDao = new RoleDAO();
                    Role role = roleDao.getRoleByName(roleName);
                    if (role != null) {
                        int userID = generatedKeys.getInt(1);
                        String roleQuery = "INSERT INTO UserRoles (UserID, RoleID) VALUES (?, ?)";
                        try (PreparedStatement roleStmt = connection.prepareStatement(roleQuery)) {
                            roleStmt.setInt(1, userID);
                            roleStmt.setInt(2, role.getRoleID());
                            int roleRowsAffected = roleStmt.executeUpdate();
                            return roleRowsAffected > 0;
                        }
                    }

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public User getUserById(int userId) {
        String query = "SELECT * FROM Users WHERE UserID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUserID(rs.getInt("UserID"));
                user.setFullName(rs.getString("FullName"));
                user.setEmail(rs.getString("Email"));
                user.setPasswordHash(rs.getString("PasswordHash"));
                user.setPhoneNumber(rs.getString("PhoneNumber"));
                user.setAddress(rs.getString("Address"));
                user.setAvatar(rs.getString("Avatar"));
                user.setIsActive(rs.getBoolean("IsActive"));
                user.setCreatedAt(rs.getTimestamp("CreatedAt"));
                user.setUpdatedAt(rs.getTimestamp("UpdatedAt"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateUser(User user) {
        String query = "UPDATE Users SET FullName = ?, Email = ?, PasswordHash = ?, PhoneNumber = ?, Address = ?, Avatar = ?, IsActive = ?, UpdatedAt = GETDATE() WHERE UserID = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, user.getFullName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPasswordHash());
            stmt.setString(4, user.getPhoneNumber());
            stmt.setString(5, user.getAddress());
            stmt.setString(6, user.getAvatar());
            stmt.setBoolean(7, user.getIsActive());
            stmt.setInt(8, user.getUserID());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteUser(int userId) {
        String query = "DELETE FROM Users WHERE UserID = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            UserRoleDAO userRoleDao = new UserRoleDAO();
            userRoleDao.removeRoleFromUserID(userId);
            stmt.setInt(1, userId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
