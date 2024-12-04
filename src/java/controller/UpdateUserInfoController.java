/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import connection.DBContext;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.User;
import service.user.IUserService;
import service.user.UserService;

/**
 *
 * @author Admin
 */
@WebServlet(name = "UpdateUserInfoController", urlPatterns = {"/updateUserInfo"})
public class UpdateUserInfoController extends HttpServlet {
private static IUserService userService = new UserService();
private static final String UPLOAD_DIR = "uploads";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateUserInfoController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateUserInfoController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
            User user = userService.getUserById(userID); // Fetch user details using userID
            
            if (user != null) {
                request.setAttribute("user", user);
                RequestDispatcher dispatcher = request.getRequestDispatcher("editPersonalInfo.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendRedirect("error.jsp"); // Redirect if user is not found
            }
        } else {
            response.sendRedirect("error.jsp"); // Redirect to error page if no userID in session
        }
    }
    

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userID = (Integer) session.getAttribute("userID");
          response.setContentType("text/html;charset=UTF-8");

        // Retrieve user inputs
        String fullName = request.getParameter("fullName");
        String birthday = request.getParameter("birthday");
        String phoneNumber = request.getParameter("phoneNumber");
        String sex = request.getParameter("sex");
        String address = request.getParameter("address");

        // Check if fullName is provided
        if (fullName == null || fullName.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Full name is required.");
            request.getRequestDispatcher("editPersonalInfo.jsp").forward(request, response);
            return;
        }

        // Handle avatar upload
        Part avatarPart = request.getPart("avatar");
        String avatarFileName = null;

        if (avatarPart != null && avatarPart.getSize() > 0) {
            String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            avatarFileName = new File(avatarPart.getSubmittedFileName()).getName();
            String filePath = uploadPath + File.separator + avatarFileName;
            avatarPart.write(filePath);
        }

        // Assume user ID is stored in session
        int userId = (int) request.getSession().getAttribute("userId");

        // Connect to the database and update user information
        try (Connection connection = DBContext.getConnection()) {
            // Fetch existing user information
            String selectSql = "SELECT birthday, phone_number, sex, address, avatar FROM users WHERE user_id = ?";
            PreparedStatement selectStatement = connection.prepareStatement(selectSql);
            selectStatement.setInt(1, userId);
            ResultSet resultSet = selectStatement.executeQuery();

            String existingBirthday = null, existingPhoneNumber = null, existingSex = null, existingAddress = null, existingAvatar = null;

            if (resultSet.next()) {
                existingBirthday = resultSet.getString("birthday");
                existingPhoneNumber = resultSet.getString("phone_number");
                existingSex = resultSet.getString("sex");
                existingAddress = resultSet.getString("address");
                existingAvatar = resultSet.getString("avatar");
            }

            // Update user information
            String updateSql = "UPDATE users SET full_name = ?, birthday = ?, phone_number = ?, sex = ?, address = ?, avatar = ? WHERE user_id = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateSql);

            updateStatement.setString(1, fullName);
            updateStatement.setString(2, birthday != null && !birthday.trim().isEmpty() ? birthday : existingBirthday);
            updateStatement.setString(3, phoneNumber != null && !phoneNumber.trim().isEmpty() ? phoneNumber : existingPhoneNumber);
            updateStatement.setString(4, sex != null && !sex.trim().isEmpty() ? sex : existingSex);
            updateStatement.setString(5, address != null && !address.trim().isEmpty() ? address : existingAddress);
            updateStatement.setString(6, avatarFileName != null ? UPLOAD_DIR + "/" + avatarFileName : existingAvatar);
            updateStatement.setInt(7, userId);

            int rowsUpdated = updateStatement.executeUpdate();

            if (rowsUpdated > 0) {
                response.sendRedirect("userInfo?userID=" + userID);
            } else {
                request.setAttribute("errorMessage", "Failed to update information.");
                request.getRequestDispatcher("userInfo?userID=" + userID).forward(request, response);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred while updating your information.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
