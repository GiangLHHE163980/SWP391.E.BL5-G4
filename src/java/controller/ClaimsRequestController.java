package controller;

import connection.DBContext;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.Claim;
import model.User;
import model.InsuranceCard;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "ClaimsRequestController", urlPatterns = {"/ClaimsRequestController"})
public class ClaimsRequestController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cardID = Integer.parseInt(request.getParameter("cardID"));
        try {
            Connection connection = DBContext.getConnection();
            String cardQuery = "SELECT * FROM InsuranceCards WHERE CardID = ?";
            PreparedStatement cardStmt = connection.prepareStatement(cardQuery);
            cardStmt.setInt(1, cardID);
            ResultSet cardRS = cardStmt.executeQuery();

            int productID = 0;
            String cardStatus = "";
            int userID = 0;
            if (cardRS.next()) {
                productID = cardRS.getInt("ProductID");
                cardStatus = cardRS.getString("Status");
                userID = cardRS.getInt("UserID");
            }

            String productName = "";
            String productDescription = "";
            String productConditions = "";
            String productType = "";
            if (productID != 0) {
                String productQuery = "SELECT * FROM InsuranceProducts WHERE ProductID = ?";
                PreparedStatement productStmt = connection.prepareStatement(productQuery);
                productStmt.setInt(1, productID);
                ResultSet productRS = productStmt.executeQuery();

                if (productRS.next()) {
                    productName = productRS.getString("ProductName");
                    productDescription = productRS.getString("Description");
                    productConditions = productRS.getString("Conditions");
                    productType = productRS.getString("InsuranceType");
                }
            }

            // Set attributes for JSP
            request.setAttribute("cardID", cardID);
            request.setAttribute("userID", userID);
            request.setAttribute("productType", productType);
            request.setAttribute("cardStatus", cardStatus);
            request.setAttribute("productName", productName);
            request.setAttribute("productDescription", productDescription);
            request.setAttribute("productConditions", productConditions);

            // Forward to the JSP
            RequestDispatcher dispatcher = request.getRequestDispatcher("claimRequest.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ClaimsRequestController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Retrieve form data
            int userID = Integer.parseInt(request.getParameter("userID"));
            int cardID = Integer.parseInt(request.getParameter("cardID"));
            String claimType = request.getParameter("claimType");
            String claimReason = request.getParameter("claimReason");

            // Database connection
            Connection connection = DBContext.getConnection();

            // SQL query to insert a new claim
            String insertQuery = "INSERT INTO Claims (CardID, UserID, ClaimType, Status, Reason, SubmittedAt) "
                    + "VALUES (?, ?, ?, 'Pending', ?, GETDATE())";

            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, cardID);
            preparedStatement.setInt(2, userID);
            preparedStatement.setString(3, claimType);
            preparedStatement.setString(4, claimReason);

            // Execute the insert query
            int rowsAffected = preparedStatement.executeUpdate();

            // Check if the insertion was successful
            if (rowsAffected > 0) {
                // Redirect or forward to a success page
               
                response.sendRedirect("claimList?userID=" + userID);
            } else {
                // Redirect or forward to an error page
                request.setAttribute("message", "Failed to submit the claim.");
                request.getRequestDispatcher("errorPage1.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("message", "An error occurred while processing your request.");
           
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("message", "Invalid data provided.");
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        }
    }

}
