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
 * @author Dell
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 15 // 15 MB
)
@WebServlet(name = "ManagerStaffController", urlPatterns = {"/manage-staff"})
public class ManagerStaffController extends HttpServlet {

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
                case "add":
                    // Redirect to Add Customer page
                    RequestDispatcher dispatcherAdd = request.getRequestDispatcher("/addStaff.jsp");
                    dispatcherAdd.forward(request, response);
                    break;

                case "edit":
                    // Handle edit
                    int editID = Integer.parseInt(request.getParameter("id"));
                    User editUser = userService.getUserById(editID);
                    request.setAttribute("user", editUser);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/updateStaff.jsp");
                    dispatcher.forward(request, response);
                    break;

                case "delete":
                    // Handle delete
                    int deleteID = Integer.parseInt(request.getParameter("id"));
                    userService.deleteUser(deleteID);
                    response.sendRedirect("manage-customer");
                    break;

                default:
                    String name = request.getParameter("name");
                    name = name != null ? name : "";
                    String status = request.getParameter("status");
                    String roleName = "Staff";
                    String pageParam = request.getParameter("page");
                    int page = (pageParam != null && !pageParam.isEmpty()) ? Integer.parseInt(pageParam) : 1;
                    int pageSize = 1;
                    List<User> users = userService.getUsersByNameAndStatusRole(name, status, page, pageSize, roleName);
                    int totalUsers = userService.countUsersByNameAndStatusAndRole(name, status, roleName);
                    int totalPages = (int) Math.ceil((double) totalUsers / pageSize);

                    request.setAttribute("users", users);
                    request.setAttribute("totalPages", totalPages);
                    request.setAttribute("currentPage", page);
                    request.getRequestDispatcher("staff_list.jsp").forward(request, response);
                    break;
            }
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
                System.out.println(user.getAvatar());
                int userID = Integer.parseInt(request.getParameter("userID"));
                user.setUserID(userID);

                editUser = userService.getUserById(userID);
                isAction = userService.updateUser(user);
            } else {
                if (nameOfAvatar != null) {
                    String namePathSaveDB = filePath + nameOfAvatar;
                    user.setAvatar(namePathSaveDB);
                } else {
                    user.setAvatar("./image/default-avatar.jpg");
                }
                isAction = userService.createUser(user, "Staff");
            }
            response.sendRedirect("manage-staff?isAction=" + isAction);
        } catch (Exception e) {
            if (action.equals("add")) {
                request.setAttribute("message", e.getMessage());
                request.getRequestDispatcher("addStaff.jsp").forward(request, response);
            } else {
                request.setAttribute("message", e.getMessage());
                request.setAttribute("user", editUser);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/updateStaff.jsp");
                dispatcher.forward(request, response);
            }
        }
    }

}
