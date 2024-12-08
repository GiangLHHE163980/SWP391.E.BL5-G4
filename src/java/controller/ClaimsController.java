package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import connection.DBContext;
import jakarta.servlet.annotation.WebServlet;
import model.User;
import service.user.IUserService;
import service.user.UserService;

@WebServlet(name = "ClaimsController", urlPatterns = {"/claimsInfo"})

public class ClaimsController extends HttpServlet {
    private static IUserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get ClaimID from the request parameter
        int claimID = Integer.parseInt(request.getParameter("claimID"));

        try {
            // Use DBContext to establish a connection
            Connection connection = DBContext.getConnection();

            // Query for claim details
            String claimQuery = "SELECT * FROM Claims WHERE ClaimID = ?";
            PreparedStatement claimStmt = connection.prepareStatement(claimQuery);
            claimStmt.setInt(1, claimID);
            ResultSet claimRS = claimStmt.executeQuery();

            if (claimRS.next()) {
                // Extract claim details
                int cardID = claimRS.getInt("CardID");
                int userID = claimRS.getInt("UserID");
                String status = claimRS.getString("Status");
                String reason = claimRS.getString("Reason");

                // Use UserService to fetch user details
                User user = userService.getUserById(userID);

                // Extract user details
                String fullName = user.getFullName();
                String phoneNumber = user.getPhoneNumber();

                // Query for card and product details
                String cardQuery = "SELECT * FROM InsuranceCards WHERE CardID = ?";
                PreparedStatement cardStmt = connection.prepareStatement(cardQuery);
                cardStmt.setInt(1, cardID);
                ResultSet cardRS = cardStmt.executeQuery();

                int productID = 0;
                String cardStatus = "";
                if (cardRS.next()) {
                    productID = cardRS.getInt("ProductID");
                    cardStatus = cardRS.getString("Status");
                }

                String productName = "";
                String productDescription = "";
                String productConditions = "";
                if (productID != 0) {
                    String productQuery = "SELECT * FROM InsuranceProducts WHERE ProductID = ?";
                    PreparedStatement productStmt = connection.prepareStatement(productQuery);
                    productStmt.setInt(1, productID);
                    ResultSet productRS = productStmt.executeQuery();

                    if (productRS.next()) {
                        productName = productRS.getString("ProductName");
                        productDescription = productRS.getString("Description");
                        productConditions = productRS.getString("Conditions");
                    }
                }

                // Set attributes for JSP
                request.setAttribute("claimID", claimID);
                request.setAttribute("status", status);
                request.setAttribute("reason", reason);
                request.setAttribute("userFullName", fullName);
                request.setAttribute("userPhoneNumber", phoneNumber);
                request.setAttribute("cardStatus", cardStatus);
                request.setAttribute("productName", productName);
                request.setAttribute("productDescription", productDescription);
                request.setAttribute("productConditions", productConditions);

                // Forward to the JSP
                RequestDispatcher dispatcher = request.getRequestDispatcher("viewClaimsInfo.jsp");
                dispatcher.forward(request, response);
            } else {
                // Handle case where claim does not exist
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Claim not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Error retrieving claim details: " + e.getMessage());
        }
    }
}
