/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.insurance;

import connection.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.InsuranceCard;
import model.InsuranceProduct;
import model.User;

/**
 *
 * @author Admin
 */
public class InsuranceCardsService implements IInsuranceCardsService {

    @Override
    public List<InsuranceCard> getInsuranceCardsByUser(int userId) {
        List<InsuranceCard> insuranceCards = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DBContext.getConnection(); // Ensure DBContext is configured properly

            // SQL query to fetch insurance cards by user ID
            String sql = "SELECT ic.CardID, ic.CardNumber, ic.StartDate, ic.EndDate, ic.Status, ic.CreatedAt, ic.UpdatedAt, "
                    + "u.UserID, u.FullName, ip.ProductID, ip.ProductName "
                    + "FROM InsuranceCards ic "
                    + "JOIN Users u ON ic.UserID = u.UserID "
                    + "JOIN InsuranceProducts ip ON ic.ProductID = ip.ProductID "
                    + "WHERE u.UserID = ?";

            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, userId); // Set the userId parameter in the query
            rs = pstmt.executeQuery();

            // Process the ResultSet and map to InsuranceCard, User, and InsuranceProduct objects
            while (rs.next()) {
                // Populate User
                User user = new User();
                user.setUserID(rs.getInt("UserID"));
                user.setFullName(rs.getString("FullName"));

                // Populate InsuranceProduct
                InsuranceProduct product = new InsuranceProduct();
                product.setProductID(rs.getInt("ProductID"));
                product.setProductName(rs.getString("ProductName"));

                // Populate InsuranceCard
                InsuranceCard card = new InsuranceCard();
                card.setCardID(rs.getInt("CardID"));
                card.setCardNumber(rs.getString("CardNumber"));
                card.setUser(user);
                card.setInsuranceProduct(product);
                card.setStartDate(rs.getDate("StartDate"));
                card.setEndDate(rs.getDate("EndDate"));
                card.setStatus(rs.getString("Status"));
                card.setCreatedAt(rs.getTimestamp("CreatedAt"));
                card.setUpdatedAt(rs.getTimestamp("UpdatedAt"));

                // Add the card to the list
                insuranceCards.add(card);
            }
        } catch (SQLException e) {
            System.err.println("An error occurred: " + e.getMessage()); // Print the exact error message
            e.printStackTrace(System.err); // Print the stack trace to standard error (console)

            // Log the error (you can also redirect to an error page if needed)    
        } 
        return insuranceCards;
    }

    @Override
    public void add(InsuranceCard t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<InsuranceCard> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public InsuranceCard findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(int id, InsuranceCard t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
