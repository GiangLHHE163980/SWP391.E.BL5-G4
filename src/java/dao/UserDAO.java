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
import java.sql.Date;

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
                + "WHERE r.RoleName = ?";

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

    public List<User> getUsersByNameAndStatusRole(String name, String status, int page, int pageSize, String roleName) throws SQLException {
        List<User> users = new ArrayList<>();
        String query = "SELECT u.Username,u.Birthday, u.UserID, u.FullName, u.Email, u.PhoneNumber, u.Address, u.Avatar, u.IsActive "
                + "FROM Users u "
                + "JOIN UserRoles ur ON u.UserID = ur.UserID "
                + "JOIN Roles r ON ur.RoleID = r.RoleID "
                + "WHERE u.FullName LIKE ? "
                + (status != null && !status.isEmpty() ? " AND u.IsActive = ? " : "")
                + (roleName != null && !roleName.isEmpty() ? " AND r.RoleName = ? " : "")
                + "ORDER BY u.UserID " // Cần có ORDER BY khi sử dụng OFFSET-FETCH
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";  // OFFSET và FETCH cho phân trang

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, "%" + name + "%");  // Lọc theo tên
            int paramIndex = 2;  // Đánh dấu chỉ mục tham số tiếp theo

            // Lọc theo trạng thái
            if (status != null && !status.isEmpty()) {
                stmt.setBoolean(paramIndex++, status.equals("active"));
            }

            // Lọc theo vai trò
            if (roleName != null && !roleName.isEmpty()) {
                stmt.setString(paramIndex++, roleName);
            }

            // Tính toán OFFSET và FETCH cho phân trang
            stmt.setInt(paramIndex++, (page - 1) * pageSize);  // OFFSET: tính toán số bản ghi bỏ qua
            stmt.setInt(paramIndex, pageSize);  // FETCH: số bản ghi cần lấy

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
                user.setUsername(rs.getString("Username"));
                Date sqlDateOfBirth = rs.getDate("Birthday");
                if (sqlDateOfBirth != null) {
                    user.setBirthday(new java.util.Date(sqlDateOfBirth.getTime()));
                }
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return users;
    }

    public int countUsersByNameAndStatusAndRole(String name, String status, String roleName) throws SQLException {
        String query = "SELECT COUNT(*) FROM Users u "
                + "JOIN UserRoles ur ON u.UserID = ur.UserID "
                + "JOIN Roles r ON ur.RoleID = r.RoleID "
                + "WHERE u.FullName LIKE ? "
                + (status != null && !status.isEmpty() ? " AND u.IsActive = ? " : "")
                + (roleName != null && !roleName.isEmpty() ? " AND r.RoleName = ? " : "");

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, "%" + name + "%");  // Lọc theo tên
            int paramIndex = 2;

            // Lọc theo trạng thái
            if (status != null && !status.isEmpty()) {
                stmt.setBoolean(paramIndex++, status.equals("active"));
            }

            // Lọc theo vai trò
            if (roleName != null && !roleName.isEmpty()) {
                stmt.setString(paramIndex++, roleName);
            }

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);  // Trả về số lượng người dùng
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
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
        String query = "INSERT INTO Users (Username, FullName, Email, PasswordHash, PhoneNumber, Address, Avatar, IsActive, Birthday, Sex, CreatedAt, UpdatedAt) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, GETDATE(), GETDATE())";

        try (PreparedStatement stmt = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            // Thêm người dùng vào bảng Users
            stmt.setString(1, user.getUsername()); // Thêm trường Username
            stmt.setString(2, user.getFullName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPasswordHash());
            stmt.setString(5, user.getPhoneNumber());
            stmt.setString(6, user.getAddress());
            stmt.setString(7, user.getAvatar());
            stmt.setBoolean(8, user.getIsActive());
            stmt.setDate(9, user.getBirthday() != null ? new Date(user.getBirthday().getTime()) : null); // Đặt DateOfBirth
            stmt.setString(10, user.getSex());  // Đặt giá trị Gender

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
                user.setPhoneNumber(rs.getString("PhoneNumber"));
                user.setPasswordHash(rs.getString("PasswordHash"));
                user.setAddress(rs.getString("Address"));
                user.setAvatar(rs.getString("Avatar"));
                user.setIsActive(rs.getBoolean("IsActive"));
                user.setUsername(rs.getString("Username"));
                user.setUsername(rs.getString("Username"));
                user.setSex(rs.getString("Sex"));
                user.setSocialSecurityNumber(rs.getInt("SocialSecurityNumber"));
                Date sqlDateOfBirth = rs.getDate("Birthday");
                user.setBirthday(new java.util.Date(sqlDateOfBirth.getTime()));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateUser(User user) {
        String query = "UPDATE Users SET FullName = ?, Email = ?, PasswordHash = ?, PhoneNumber = ?, Address = ?, Avatar = ?, IsActive = ?, "
                + "Birthday = ?, Sex = ?, UpdatedAt = GETDATE() WHERE UserID = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, user.getFullName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPasswordHash());
            stmt.setString(4, user.getPhoneNumber());
            stmt.setString(5, user.getAddress());
            stmt.setString(6, user.getAvatar());
            stmt.setBoolean(7, user.getIsActive());
            stmt.setDate(8, user.getBirthday() != null ? new Date(user.getBirthday().getTime()) : null); // Đặt DateOfBirth
            stmt.setString(9, user.getSex());
            stmt.setInt(10, user.getUserID());
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
    
    public boolean isEmailExists(String email, int id) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Users WHERE Email = ? and UserID != ?")) {
            statement.setString(1, email);
            statement.setInt(2, id);
            try ( ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); 
            }
        } catch (SQLException e) {
            System.out.println("Check email exists: " + e);
        }
        return false;
    }
    
    public boolean isPhoneExists(String phone, int id) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Users WHERE PhoneNumber = ? and UserID != ?")) {
            statement.setString(1, phone);
            statement.setInt(2, id);
            try ( ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); 
            }
        } catch (SQLException e) {
            System.out.println("Check email exists: " + e);
        }
        return false;
    }
    
    public boolean isUsernameExists(String username, int id) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Users WHERE Username = ? and UserID != ?")) {
            statement.setString(1, username);
            statement.setInt(2, id);
            try ( ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); 
            }
        } catch (SQLException e) {
            System.out.println("Check email exists: " + e);
        }
        return false;
    }
}
