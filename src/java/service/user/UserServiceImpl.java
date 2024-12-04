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

    public String validateUser(User user) {
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            return "Username is required.";
        }
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
        if (user.getSocialSecurityNumber() <= 0) {
            return "Social Security Number is required.";
        }
        if (user.getSex() == null || user.getSex().trim().isEmpty()) {
            return "Sex is required.";
        }
        if (user.getBirthday() == null) {
            return "Birthday is required.";
        }
        return null;
    }

    public boolean createUser(User user, String roleName) throws SQLException {
        String validationMessage = validateUser(user);
        if (validationMessage != null) {
            throw new IllegalArgumentException(validationMessage);
        }
        if (userDAO.isEmailExists(user.getEmail(), 0)) {
            throw new IllegalArgumentException("Email already exists.");
        }
        if (userDAO.isPhoneExists(user.getPhoneNumber(), 0)) {
            throw new IllegalArgumentException("Phone number already exists.");
        }
        if (userDAO.isUsernameExists(user.getUsername(), 0)) {
            throw new IllegalArgumentException("Username already exists.");
        }
        return userDAO.addUser(user, roleName);
    }

    public List<User> getAllUsers() throws SQLException {
        return userDAO.findAll();
    }

    public List<User> getUsersByNameAndStatusRole(String name, String status, int page, int pageSize, String roleName) throws SQLException {
        return userDAO.getUsersByNameAndStatusRole(name, status, page, pageSize, roleName);
    }

    public int countUsersByNameAndStatusAndRole(String name, String status, String roleName) throws SQLException {
        return userDAO.countUsersByNameAndStatusAndRole(name, status, roleName);
    }

    public User getUserById(int userID) throws SQLException {
        return userDAO.getUserById(userID);
    }

    public boolean updateUser(User user) throws SQLException {
        String validationMessage = validateUser(user);
        if (validationMessage != null) {
            throw new IllegalArgumentException(validationMessage);
        }
        if (userDAO.isEmailExists(user.getEmail(), user.getUserID())) {
            throw new IllegalArgumentException("Email already exists.");
        }
        if (userDAO.isPhoneExists(user.getPhoneNumber(), user.getUserID())) {
            throw new IllegalArgumentException("Phone number already exists.");
        }
        if (userDAO.isUsernameExists(user.getUsername(), user.getUserID())) {
            throw new IllegalArgumentException("Username already exists.");
        }
        return userDAO.updateUser(user);
    }

    public boolean deleteUser(int userID) throws SQLException {
        return userDAO.deleteUser(userID);
    }
}
