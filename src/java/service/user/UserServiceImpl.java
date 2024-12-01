package service.user;

import dao.UserDAO;
import model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserServiceImpl {

    private UserDAO userDAO;

    public UserServiceImpl() {
        userDAO = new UserDAO();
    }

    // Validate email format
    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // Validate phone number format (example: (123) 456-7890)
    private boolean isValidPhoneNumber(String phoneNumber) {
        String phoneRegex = "^\\+?\\d{10,15}$";  // example regex for phone numbers with international support
        Pattern pattern = Pattern.compile(phoneRegex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    // Validate user object
    public String validateUser(User user) {
        if (user.getFullName() == null || user.getFullName().trim().isEmpty()) {
            return "Full name is required.";
        }
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            return "Email is required.";
        } else if (!isValidEmail(user.getEmail())) {
            return "Invalid email format.";
        }
        if (user.getPasswordHash() == null || user.getPasswordHash().trim().isEmpty()) {
            return "Password is required.";
        }
        if (user.getPhoneNumber() == null || user.getPhoneNumber().trim().isEmpty()) {
            return "Phone number is required.";
        } else if (!isValidPhoneNumber(user.getPhoneNumber())) {
            return "Invalid phone number format.";
        }
        if (user.getAddress() == null || user.getAddress().trim().isEmpty()) {
            return "Address is required.";
        }
        return null; // No validation errors
    }

    public boolean createUser(User user, String roleName) throws SQLException {
        String validationMessage = validateUser(user);
        if (validationMessage != null) {
            throw new IllegalArgumentException(validationMessage);
        }
        return userDAO.addUser(user, roleName);
    }

    public List<User> getAllUsers() throws SQLException {
        return userDAO.findAll();
    }
    
     public List<User> getUsersByRole(String roleName) throws SQLException {
        return userDAO.getUsersByRole(roleName);
    }

    public User getUserById(int userID) throws SQLException {
        return userDAO.getUserById(userID);
    }

    public boolean updateUser(User user) throws SQLException {
        String validationMessage = validateUser(user);
        if (validationMessage != null) {
            throw new IllegalArgumentException(validationMessage);
        }
        return userDAO.updateUser(user);
    }

    public boolean deleteUser(int userID) throws SQLException {
        return userDAO.deleteUser(userID);
    }
}
