package controller;

import connection.DBContext;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;
import model.User;

@WebServlet(name = "UserInfoServlet", urlPatterns = {"/userInfo"})
public class UsersController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         String userIdParam = request.getParameter("userID");

        // If userID is provided in the URL, update session
        if (userIdParam != null) {
            try {
                int userID = Integer.parseInt(userIdParam); // Parse the userID
                HttpSession session = request.getSession();
                session.setAttribute("userID", userID); // Store userID in session
            } catch (NumberFormatException e) {
                 // Redirect to error if userID is invalid
                return;
            }
        } 

        // Retrieve userID from session
        HttpSession session = request.getSession();
        Integer userID = (Integer) session.getAttribute("userID"); // Get userID from session

        if (userID != null) {
            User user = getUserById(userID); // Fetch user details using userID
            
            if (user != null) {
                request.setAttribute("user", user);
                RequestDispatcher dispatcher = request.getRequestDispatcher("viewPersonalInfo.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendRedirect("error.jsp"); // Redirect if user is not found
            }
        } else {
            response.sendRedirect("error.jsp"); // Redirect to error page if no userID in session
        }
    }

    private User getUserById(int userID) {
        User user = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            // Get connection from your connection file
            conn = DBContext.getConnection();

            String sql = "SELECT * FROM Users WHERE UserID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userID);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                user = new User();
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
                user.setSex(rs.getString("Sex"));
                user.setSocialSecurityNumber(rs.getInt("SocialSecurityNumber"));
                user.setBirthday(rs.getDate("Birthday")); // Assuming 'Birthday' is the column name
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }
}
