/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.util.List;
import model.User;
import service.user.UserServiceImpl;
import ultil.Upload;

/**
 *
 * @author HP
 */
@WebServlet(name = "ProfileAdminController", urlPatterns = {"/profileAdmin"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 15 // 15 MB
)
public class ProfileAdminController extends HttpServlet {

    private UserServiceImpl userService;

    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl();
    }

    // Show all users
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "":
                    request.setAttribute("action", "view");
                    break;

                case "edit":
                    request.setAttribute("action", "edit");
                    break;
            }
//            int editID = Integer.parseInt(request.getParameter("id"));
            int editID = 2;
            User editUser = userService.getUserById(editID);
            request.setAttribute("user", editUser);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/updateProfileAdmin.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        User editUser = null;
        try {
            String username = request.getParameter("username");
            String fullName = request.getParameter("fullName");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String phoneNumber = request.getParameter("phoneNumber");
            String address = request.getParameter("address");
            String oldAvatar = request.getParameter("oldAvatar");
            boolean status = Integer.parseInt(request.getParameter("status")) == 1;
            

            // Additional fields
            String sex = request.getParameter("sex");
            String socialSecurityNumber = request.getParameter("socialSecurityNumber");
            String birthday = request.getParameter("birthday");

            // Handle Avatar
            Part filePart = request.getPart("avatar");
            String filePath = "./uploads/";
            String uploadPath = getServletContext().getRealPath(filePath);
            Upload upload = new Upload();
            String nameOfAvatar = upload.uploadFile(filePart, uploadPath);

            // Create a User object
            User user = new User(username, fullName, email, password, phoneNumber, address, sex, Integer.parseInt(socialSecurityNumber), status, new java.util.Date());

            if (birthday != null && !birthday.isEmpty()) {
                user.setBirthday(java.sql.Date.valueOf(birthday));  // Set the birthday
            }
            boolean isAction = false;
            if (request.getParameter("userID") != null) {
                if (nameOfAvatar == null || nameOfAvatar.equals("")) {
                    user.setAvatar(oldAvatar);
                } else {
                    user.setAvatar(filePath + nameOfAvatar);
                }
                if (password == null || password.equals("")) {
                    user.setPasswordHash(request.getParameter("oldPassword"));
                }
                int userID = Integer.parseInt(request.getParameter("userID"));
                user.setUserID(userID);

                editUser = userService.getUserById(userID);
                isAction = userService.updateUser(user);
            }
            response.sendRedirect("profileAdmin?isAction=" + isAction);
        } catch (Exception e) {
            request.setAttribute("message", e.getMessage());
            request.setAttribute("user", editUser);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/updateProfileAdmin.jsp");
            dispatcher.forward(request, response);
        }
    }

}
