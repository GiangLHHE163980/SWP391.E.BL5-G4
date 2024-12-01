package controller;

import connection.DBContext;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.InsuranceCard;
import model.User;
import model.InsuranceProduct;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "InsuranceCardServlet", urlPatterns = {"/insuranceCards"})
public class InsuranceCardsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<InsuranceCard> insuranceCards = getAllInsuranceCards();
        
        if (insuranceCards != null) {
            request.setAttribute("insuranceCards", insuranceCards);
            RequestDispatcher dispatcher = request.getRequestDispatcher("viewInsuranceCards.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("error.jsp");
        }
    }

    private List<InsuranceCard> getAllInsuranceCards() {
        List<InsuranceCard> insuranceCards = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBContext.getConnection(); // Ensure DBContext is configured for your database
            String sql = "SELECT ic.*, u.UserID, u.FullName, ip.ProductID, ip.ProductName " +
                         "FROM InsuranceCard ic " +
                         "JOIN User u ON ic.UserID = u.UserID " +
                         "JOIN InsuranceProduct ip ON ic.ProductID = ip.ProductID";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

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

                insuranceCards.add(card);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return insuranceCards;
    }
}
