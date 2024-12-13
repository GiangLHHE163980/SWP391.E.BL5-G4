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
        HttpSession session = request.getSession(false); // Fetch the existing session, do not create a new one

        if (session == null || session.getAttribute("userID") == null) {
            // Redirect to login if the session or userID does not exist
            response.sendRedirect("account/login");
            return;
        }

        // Fetch the userID from the session
        int userID = (int) session.getAttribute("userID");

        // Retrieve the user object from the service
        User user = userService.getUserById(userID);

        if (user != null) {
            // Pass the user object to the JSP
            request.setAttribute("user", user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("viewPersonalInfo.jsp");
            dispatcher.forward(request, response);
        } else {
            // Redirect to an error page if the user is not found
            response.sendRedirect("error.jsp");
        }
    }
}
