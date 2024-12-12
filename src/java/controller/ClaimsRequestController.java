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
            if (cardRS.next()) {
                productID = cardRS.getInt("ProductID");
                cardStatus = cardRS.getString("Status");
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
            request.setAttribute("productType",productType);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        // Fetch claim details from the request
        String insuranceCardId = request.getParameter("insuranceCardId");
        String userId = request.getParameter("userId");
        String claimType = request.getParameter("claimType");
        String reason = request.getParameter("reason");
        String submittedAtStr = request.getParameter("submittedAt");

        // Date formatting
        Date submittedAt = null;
        try {
            submittedAt = new SimpleDateFormat("yyyy-MM-dd").parse(submittedAtStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Database connection
        try (Connection connection = new DBContext().getConnection()) {
            // SQL query to insert a new claim
            String sql = "INSERT INTO Claims (insuranceCardId, userId, claimType, status, reason, submittedAt) "
                    + "VALUES (?, ?, ?, 'Pending', ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                // Set parameters
                preparedStatement.setString(1, insuranceCardId);
                preparedStatement.setString(2, userId);
                preparedStatement.setString(3, claimType);
                preparedStatement.setString(4, reason);
                preparedStatement.setDate(5, new java.sql.Date(submittedAt.getTime()));

                // Execute update
                int rowsAffected = preparedStatement.executeUpdate();

                try (PrintWriter out = response.getWriter()) {
                    if (rowsAffected > 0) {
                        out.println("<h1>Claim created successfully!</h1>");
                    } else {
                        out.println("<h1>Failed to create claim.</h1>");
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            try (PrintWriter out = response.getWriter()) {
                out.println("<h1>Error occurred while creating the claim.</h1>");
            }
        }
    }
}
