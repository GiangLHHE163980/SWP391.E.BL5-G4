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
        HttpSession session = request.getSession();
        Integer userId = null;

// Get userId from request parameter
        String userIdParam = request.getParameter("userId");

        if (userIdParam != null) {
            try {
                userId = Integer.parseInt(userIdParam); // Parse the userId from the URL parameter
                session.setAttribute("userId", userId); // Store userId in session
            } catch (NumberFormatException e) {
                // Handle the case where the userId parameter is invalid (optional)
                response.sendRedirect("error.jsp"); // Redirect to error page or handle appropriately
                return;
            }
        } else {
            // Redirect to error page if userId is not provided in the URL
            response.sendRedirect("error.jsp");
            return;
        }
        // Fetch insurance cards for the given user ID
        List<InsuranceCard> insuranceCards = getInsuranceCardsByUser(userId);

        // If insurance cards are found, forward to the JSP page, otherwise redirect to error page
        if (insuranceCards != null && !insuranceCards.isEmpty()) {
            request.setAttribute("insuranceCards", insuranceCards);
            RequestDispatcher dispatcher = request.getRequestDispatcher("listInsuranceCards.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("error.jsp"); // Redirect to error page if no cards found
        }
    }

    private List<InsuranceCard> getInsuranceCardsByUser(int userId) {
        List<InsuranceCard> insuranceCards = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBContext.getConnection(); // Ensure DBContext is configured properly

            // SQL query to fetch insurance cards by user ID
            String sql = "SELECT ic.CardID, ic.CardNumber, ic.StartDate, ic.EndDate, ic.Status, ic.CreatedAt, ic.UpdatedAt, "
                    + "u.UserID, u.FullName, ip.ProductID, ip.ProductName "
                    + "FROM InsuranceCards ic "
                    + "JOIN Users u ON ic.UserID = u.UserID "
                    + "JOIN InsuranceProducts ip ON ic.ProductID = ip.ProductID "
                    + "WHERE u.UserID = ?";

            pstmt = conn.prepareStatement(sql);
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
            e.printStackTrace(); // Log the error (you can also redirect to an error page if needed)
        } finally {
            // Close resources
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Log error related to closing resources
            }
        }
        return insuranceCards;
    }
}
