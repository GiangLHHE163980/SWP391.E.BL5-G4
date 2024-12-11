package controller;

import connection.DBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ConfirmInsuranceController", urlPatterns = {"/ConfirmInsuranceController"})
public class ConfirmInsuranceController extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Fetch data from the request (hidden fields in confirmation.jsp)
        String userName = request.getParameter("userName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String gender = request.getParameter("gender");
        String address = request.getParameter("address");
        java.sql.Date birthday = null;
        String dobStr = request.getParameter("dob");
        if (dobStr != null && !dobStr.trim().isEmpty()) {
            birthday = java.sql.Date.valueOf(dobStr); // Convert string to SQL date
        }
        String idCard = request.getParameter("idCard");
        int userId = Integer.parseInt(request.getParameter("userID"));
        int productId = Integer.parseInt(request.getParameter("productID"));
        boolean isHandicapped = Boolean.parseBoolean(request.getParameter("isHandicapped"));
        java.sql.Date startDate = null;
        java.sql.Date endDate = null;

        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");

        if (startDateStr != null && !startDateStr.trim().isEmpty()) {
            startDate = java.sql.Date.valueOf(startDateStr); // Convert string to SQL date
        }

        if (endDateStr != null && !endDateStr.trim().isEmpty()) {
            endDate = java.sql.Date.valueOf(endDateStr); // Convert string to SQL date
        }

        try {
            // Establish a database connection
            Connection conn = DBContext.getConnection();

            // Generate a new CardNumber
            String lastCardQuery = "SELECT TOP 1 CardNumber FROM InsuranceCards ORDER BY CardID DESC";
            PreparedStatement lastCardStmt = conn.prepareStatement(lastCardQuery);
            ResultSet lastCardRs = lastCardStmt.executeQuery();

            String newCardNumber = "CARD001"; // Default for the first card
            if (lastCardRs.next()) {
                String lastCard = lastCardRs.getString("CardNumber");
                int lastNumber = Integer.parseInt(lastCard.replace("CARD", ""));
                newCardNumber = "CARD" + String.format("%03d", lastNumber + 1);
            }

            // Insert the new Insurance Card into the database
            String insertCardQuery = "INSERT INTO InsuranceCards (CardNumber, UserID, ProductID, StartDate, EndDate, Status, isHandicapped) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement insertCardStmt = conn.prepareStatement(insertCardQuery);
            insertCardStmt.setString(1, newCardNumber);
            insertCardStmt.setInt(2, userId);
            insertCardStmt.setInt(3, productId);
            insertCardStmt.setDate(4, Date.valueOf(startDateStr));
            insertCardStmt.setDate(5, Date.valueOf(endDateStr));
            insertCardStmt.setString(6, "Pending"); // Default status
            insertCardStmt.setBoolean(7, isHandicapped);

            int rowsAffectedCard = insertCardStmt.executeUpdate();

            // Update the user information in the Users table
            String updateUserQuery = "UPDATE Users SET PhoneNumber = ?, "
                    + "               Birthday = ?, Sex = ?, SocialSecurityNumber= ?, Address = ?, UpdatedAt = GETDATE() WHERE UserID = ?";
            PreparedStatement updateUserStmt = conn.prepareStatement(updateUserQuery);

            updateUserStmt.setString(1, phone);
            updateUserStmt.setDate(2, Date.valueOf(dobStr));
            updateUserStmt.setString(3, gender);
            updateUserStmt.setString(4, idCard);
            updateUserStmt.setString(5, address);
            updateUserStmt.setInt(6, userId);

            int rowsAffectedUser = updateUserStmt.executeUpdate();

            // Provide feedback to the user
            if (rowsAffectedCard > 0 && rowsAffectedUser > 0) {
                out.println("<h2>Insurance Card Created Successfully!</h2>");
                out.println("<p>Card Number: " + newCardNumber + "</p>");
                out.println("<p>User: " + userName + "</p>");
                out.println("<p>Email: " + email + "</p>");
                out.println("<p>Phone: " + phone + "</p>");
                out.println("<p>Product ID: " + productId + "</p>");
                out.println("<p>Start Date: " + startDateStr + "</p>");
                out.println("<p>End Date: " + endDateStr + "</p>");
                out.println("<p>Handicapped: " + (isHandicapped ? "Yes" : "No") + "</p>");
                out.println("<p>Status: Pending</p>");
                out.println("<h2>User updated successfully!</h2>");
            } else {
                out.println("<h2>Error: Unable to process the request.</h2>");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<p>Error processing request: " + e.getMessage() + "</p>");
        }
    }

    @Override
    public String getServletInfo() {
        return "Handles the confirmation and creation of an Insurance Card, and updates user information.";
    }
}
