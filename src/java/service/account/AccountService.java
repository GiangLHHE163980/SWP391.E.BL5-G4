/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.account;

import connection.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import model.User;

/**
 *
 * @author Lenovo
 */
public class AccountService implements IAccountService{
    
    private static final Connection connection = DBContext.getConnection();
    
    private final String FIND_Users_BY_USERNAME_AND_PASSWORD = "SELECT * FROM Users WHERE Username = ? AND PasswordHash = ?";
    
    private final String INSERT_Users ="INSERT INTO Users (FullName,Username, PasswordHash, Email) VALUES (?,?, ?, ?)";
    
    private final String INSERT_USER_ROLES = "INSERT INTO UserRoles (UserID, RoleID) VALUES (?, ?)";
    
    private final String UPDATE_PASSWORD_BY_EMAIL = "UPDATE Users SET PasswordHash = ? WHERE Email = ?";

    private final String SELECT_COUNT_Username_Exit="SELECT COUNT(*) FROM Users WHERE Username = ?";
    
    private final String SELECT_COUNT_Email_Exit="SELECT COUNT(*) FROM Users WHERE Email = ?";
    
    @Override
    public void add(User user) {
    try (PreparedStatement ps = connection.prepareStatement(INSERT_Users, Statement.RETURN_GENERATED_KEYS)) {
        // Thêm User vào bảng Users
        ps.setString(1, user.getFullName());
        ps.setString(2, user.getUsername());
        ps.setString(3, user.getPasswordHash());
        ps.setString(4, user.getEmail());
        ps.executeUpdate();

        // Lấy userId vừa được thêm
        try (ResultSet rs = ps.getGeneratedKeys()) {
            if (rs.next()) {
                int userId = rs.getInt(1); // Lấy giá trị của cột auto_increment
                
                // Gán roleId = 2 cho user mới
                try (PreparedStatement psRole = connection.prepareStatement(INSERT_USER_ROLES)) {
                    psRole.setInt(1, userId);
                    psRole.setInt(2, 2); // RoleID = 2
                    psRole.executeUpdate();
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    @Override
    public List<User> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public User findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(int id, User t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    @Override
    public User login(String username, String password) {
        User user = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_Users_BY_USERNAME_AND_PASSWORD);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int userId = resultSet.getInt("UserID");
                String dbUsername = resultSet.getString("Username");
                String dbPassword = resultSet.getString("PasswordHash");
                boolean status = resultSet.getBoolean("IsActive");

                // Kiểm tra nếu tài khoản bị ban
                user = new User(userId, dbUsername, status);
             }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;  // Trả về user nếu tài khoản hợp lệ
    }
    @Override
    public void updatePasswordByEmail(String email, String newPassword) {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_PASSWORD_BY_EMAIL)) {
            ps.setString(1, newPassword);
            ps.setString(2, email);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public boolean isUsernameExists(String username) {
        try ( PreparedStatement ps = connection.prepareStatement(SELECT_COUNT_Username_Exit)) {
            ps.setString(1, username);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    public boolean isEmailExists(String email) {
        try ( PreparedStatement ps = connection.prepareStatement(SELECT_COUNT_Email_Exit)) {
            ps.setString(1, email);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
