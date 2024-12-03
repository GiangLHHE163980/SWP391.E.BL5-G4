package controller;

import connection.DBContext;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;
import model.User;
import service.user.*;
@WebServlet(name = "UserInfoServlet", urlPatterns = {"/userInfo"})
public class UsersController extends HttpServlet {
private static IUserService userService = new UserService();

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
            User user = userService.getUserById(userID); // Fetch user details using userID
            
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

    
}
