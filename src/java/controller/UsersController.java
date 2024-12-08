package controller;

import connection.DBContext;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;
import model.User;
import service.user.IUserService;
import service.user.UserService;

@WebServlet(name = "UserInfoServlet", urlPatterns = {"/userInfo"})
public class UsersController extends HttpServlet {

    private static IUserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userIdParam = request.getParameter("userID");
        int userID = Integer.parseInt(userIdParam); // Assume user ID is passed as a parameter or retrieved from session
        HttpSession session = request.getSession();
        session.setAttribute("userID", userID);
        User user = userService.getUserById(userID);

        if (user != null) {
            request.setAttribute("user", user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("viewPersonalInfo.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("error.jsp");
        }
    }
}
