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
@WebServlet(name = "ManagerCustomerController", urlPatterns = {"/manage-customer"})
public class ManagerCustomerController extends HttpServlet {

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
                    RequestDispatcher dispatcherAdd = request.getRequestDispatcher("/addCustomer.jsp");
                    dispatcherAdd.forward(request, response);
                    break;
                case "edit":
                    //Lấy dữ liệu user dựa vào id
                    int editID = Integer.parseInt(request.getParameter("id"));
                    User editUser = userService.getUserById(editID);
                    request.setAttribute("user", editUser);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/updateCustomer.jsp");
                    dispatcher.forward(request, response);
                    break;
                case "delete":
                    int deleteID = Integer.parseInt(request.getParameter("id"));
                    userService.deleteUser(deleteID);
                    response.sendRedirect("manage-customer");
                    break;
                default:
                    request.setAttribute("users", userService.getUsersByRole("Customer"));
                    request.getRequestDispatcher("/customer_list.jsp").forward(request, response);
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
        try {
            String fullName = request.getParameter("fullName");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String phoneNumber = request.getParameter("phoneNumber");
            String address = request.getParameter("address");
            String oldAvatar = request.getParameter("oldAvatar");
            boolean status = Integer.parseInt(request.getParameter("status")) == 1;
            //Xử lý Avatar
            Part filePart = request.getPart("avatar");
            //Đường dẫn tương đối
            String filePath = "./uploads/";
            //Đường dẫn tuyệt đối
            String uploadPath = getServletContext().getRealPath(filePath);
            Upload upload = new Upload();
            String nameOfAvatar = upload.uploadFile(filePart, uploadPath);

            User user = new User(fullName, email, password, phoneNumber, address, null, status, new java.util.Date());

            if (request.getParameter("userID") != null) {
                //Update avatar
                if (nameOfAvatar == null || nameOfAvatar.equals("")) {
                    user.setAvatar(oldAvatar); 
                } else {
                    user.setAvatar(filePath + nameOfAvatar);
                }
                if(password == null || password.equals("")) {
                    user.setPasswordHash(request.getParameter("oldPassword"));
                }
                int userID = Integer.parseInt(request.getParameter("userID"));
                user.setUserID(userID);
                userService.updateUser(user);
            } else {
                //Add new
                if (nameOfAvatar != null) {
                    String namePathSaveDB = filePath + nameOfAvatar;
                    user.setAvatar(namePathSaveDB);
                } else {
                    user.setAvatar("./image/default-avatar.jpg");
                }
                userService.createUser(user, "Customer");
            }

            response.sendRedirect("manage-customer");
        } catch (Exception e) {
            if (action.equals("add")) {
                request.setAttribute("message", e.getMessage());
                request.getRequestDispatcher("addCustomer.jsp").forward(request, response);
            } else {
                request.setAttribute("message", e.getMessage());
                this.doGet(request, response);
            }
        }
    }

}
