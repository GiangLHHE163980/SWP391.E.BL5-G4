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
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
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
        // Retrieve userID from session
        HttpSession session = request.getSession(false); // Fetch existing session without creating a new one
        if (session == null) {
            response.sendRedirect("account/login"); // Redirect to login page if session is null
            return;
        }

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
            response.sendRedirect("account/login"); // Redirect to login if userID is missing
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
        request.setCharacterEncoding("UTF-8");

        // Retrieve user ID from session
        HttpSession session = request.getSession();
        Integer userID = (Integer) session.getAttribute("userID");

        // Fetch input from form
        String fullName = request.getParameter("fullName");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        String sex = request.getParameter("sex");
        java.sql.Date birthday = null;

        // Handle birthday parsing
        String birthdayParam = request.getParameter("birthday");
        if (birthdayParam != null && !birthdayParam.trim().isEmpty()) {
            birthday = java.sql.Date.valueOf(birthdayParam); // Convert string to SQL date
        }

        // Handle avatar file upload (if any)
//            Part avatarPart = request.getPart("avatar");
//            String avatarFileName = null;
//            if (avatarPart != null && avatarPart.getSize() > 0) {
//                // Save uploaded file
//                String uploadPath = getServletContext().getRealPath("/") + "uploads/";
//                File uploadDir = new File(uploadPath);
//                if (!uploadDir.exists()) {
//                    uploadDir.mkdir();
//                }
//                avatarFileName = Paths.get(avatarPart.getSubmittedFileName()).getFileName().toString();
//                avatarPart.write(uploadPath + avatarFileName);
//            }
        // Fetch the existing user data to preserve the avatar if not updated
        User existingUser = userService.getUserById(userID); // Fetch current user data
        //String avatarToUpdate = avatarFileName != null ? "uploads/" + avatarFileName : existingUser.getAvatar();

        // Populate User object
        User user = new User();
        user.setUserID(userID);
        user.setFullName(fullName);
        user.setPhoneNumber(phoneNumber);
        user.setAddress(address);
        user.setBirthday(birthday);
        user.setSex(sex);
        //user.setAvatar(avatarToUpdate); // Keep the existing avatar if no new file uploaded

        // Call updateUser method to update database
        boolean isUpdated = updateUser(user);

        if (isUpdated) {
            // Redirect to the user info page
            response.sendRedirect("userInfo?userID=" + userID);
        } else {
            // Redirect to error page if update fails

        }

    }

    public boolean updateUser(User user) {
        String query = "UPDATE Users SET FullName = ?, PhoneNumber = ?, Address = ?, "
                + "Birthday = ?, Sex = ?, UpdatedAt = GETDATE() WHERE UserID = ?";

        try ( PreparedStatement stmt = DBContext.getConnection().prepareStatement(query)) {
            stmt.setString(1, user.getFullName());
            stmt.setString(2, user.getPhoneNumber());
            stmt.setString(3, user.getAddress());
            // stmt.setString(4, user.getAvatar());
            stmt.setDate(4, user.getBirthday() != null ? new java.sql.Date(user.getBirthday().getTime()) : null);
            stmt.setString(5, user.getSex());
            stmt.setInt(6, user.getUserID());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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
