package controller;

import connection.DBContext;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import service.user.IUserService;
import service.user.UserService;

// URL mapping for this servlet
@WebServlet(name = "RequestInsuranceController", urlPatterns = "/requestInsuranceController")
public class RequestInsuranceController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static IUserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);
        int userID;
        User user = (User) session.getAttribute("user");// Get the current session without creating a new one
        if (session == null || user == null) {
            response.sendRedirect("account/login"); // Redirect to login if not authenticated
            return;
        } else {
            userID = user.getUserID();
            session.setAttribute("userID", userID);
        }
        int productId = Integer.parseInt(request.getParameter("productID"));
        Connection conn = null;
        try {
            conn = DBContext.getConnection();

            // Validation: Check if the user already has a card for the specified ProductID
            String validationQuery = "SELECT * FROM InsuranceCards WHERE UserID = ? AND ProductID = ?";
            PreparedStatement validationStmt = conn.prepareStatement(validationQuery);
            validationStmt.setInt(1, userID);
            validationStmt.setInt(2, productId);
            ResultSet validationRs = validationStmt.executeQuery();

            if (validationRs.next()) {
                // If a record is found, the user already has a card for this ProductID
                out.println("<p style='color:red;'>You already have an insurance card for this product. You cannot request another one.</p>");

                return; // Stop further processing
            }
            // Fetch user info using UserService
            user = userService.getUserById(userID);

            // Fetch product info from the database
            String productQuery = "SELECT * FROM InsuranceProducts WHERE ProductID = ?";
            PreparedStatement productStmt = conn.prepareStatement(productQuery);
            productStmt.setInt(1, productId);
            ResultSet productRs = productStmt.executeQuery();

            String productName = "", insuranceType = "", description = "", conditions = "";
            double cost = 0;
            if (productRs.next()) {
                productName = productRs.getString("ProductName");
                insuranceType = productRs.getString("InsuranceType");
                description = productRs.getString("Description");
                conditions = productRs.getString("Conditions");
                cost = productRs.getDouble("Cost");
            }

            // Forward data to JSP for rendering
            request.setAttribute("userName", user != null ? user.getFullName() : "");
            request.setAttribute("email", user != null ? user.getEmail() : "");
            request.setAttribute("phone", user != null ? user.getPhoneNumber() : "");
            request.setAttribute("productName", productName);
            request.setAttribute("insuranceType", insuranceType);
            request.setAttribute("description", description);
            request.setAttribute("conditions", conditions);
            request.setAttribute("cost", cost);
            request.getRequestDispatcher("requestInsurance.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<p>Error fetching data: " + e.getMessage() + "</p>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        // Capture form data
        String userName = request.getParameter("userName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String gender = request.getParameter("gender");
        String dob = request.getParameter("dob");
        String address = request.getParameter("address");
        int idCard = Integer.parseInt(request.getParameter("idCard"));
//        int userId = Integer.parseInt(request.getParameter("userID"));
        int productId = Integer.parseInt(request.getParameter("productID"));
        boolean isHandicapped = "yes".equalsIgnoreCase(request.getParameter("isHandicapped"));
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        try {
            // Retrieve product details for confirmation (if needed)
            Connection conn = DBContext.getConnection();
            String productQuery = "SELECT * FROM InsuranceProducts WHERE ProductID = ?";
            PreparedStatement productStmt = conn.prepareStatement(productQuery);
            productStmt.setInt(1, productId);
            ResultSet productRs = productStmt.executeQuery();

            String productName = "", insuranceType = "", description = "", conditions = "";
            double cost = 0;
            if (productRs.next()) {
                productName = productRs.getString("ProductName");
                insuranceType = productRs.getString("InsuranceType");
                description = productRs.getString("Description");
                conditions = productRs.getString("Conditions");
                cost = productRs.getDouble("Cost");
            }

            // Pass all collected data to the confirmation page
            request.setAttribute("userName", userName);
            request.setAttribute("email", email);
            request.setAttribute("phone", phone);
            request.setAttribute("gender", gender);
            request.setAttribute("dob", dob);
            request.setAttribute("address", address);
            request.setAttribute("idCard", idCard);
//            request.setAttribute("userID", userId);
            request.setAttribute("productID", productId);
            request.setAttribute("productName", productName);
            request.setAttribute("insuranceType", insuranceType);
            request.setAttribute("description", description);
            request.setAttribute("conditions", conditions);
            request.setAttribute("cost", cost);
            request.setAttribute("isHandicapped", isHandicapped);
            request.setAttribute("startDate", startDate);
            request.setAttribute("endDate", endDate);

            // Forward to confirmation page
            request.getRequestDispatcher("confirmationPage.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("<p>Error processing request: " + e.getMessage() + "</p>");
        }
    }
}
