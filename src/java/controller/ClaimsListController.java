package controller;

import connection.DBContext;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Claim;
import model.InsuranceCard;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ClaimListController", urlPatterns = {"/claimList"})
public class ClaimsListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Get the session without creating a new one

        if (session != null) {
            Integer userID = (Integer) session.getAttribute("userID"); // Fetch userID from the session

            if (userID != null) {
                // Fetch claims using the separate method
                List<Claim> claims = fetchClaimsFromUser(userID);

                // Set the claims list as a session attribute
                session.setAttribute("claims", claims);

                // Forward to the JSP
                RequestDispatcher dispatcher = request.getRequestDispatcher("listClaims.jsp");
                dispatcher.forward(request, response);
            } else {
                // Redirect to login page if userID is missing in the session
                response.sendRedirect("account/login");
            }
        } else {
            // Redirect to login page if no session exists
            response.sendRedirect("account/login");
        }
    }

    /**
     * Fetch claims for a given user ID from the database.
     *
     * @param userID the user ID for which to fetch claims
     * @return a list of claims associated with the given user
     */
    private List<Claim> fetchClaimsFromUser(int userID) {
        List<Claim> claims = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DBContext.getConnection();
            String query = "SELECT c.ClaimID, c.ClaimType, c.Status, c.Reason, c.SubmittedAt, c.ProcessedAt, "
                    + "       ic.CardID, ic.CardNumber "
                    + "FROM Claims c "
                    + "JOIN InsuranceCards ic ON c.CardID = ic.CardID "
                    + "WHERE c.UserID = ?";

            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, userID); // Set the userId parameter in the query
            rs = pstmt.executeQuery();
            while (rs.next()) {
                // Map InsuranceCard
                InsuranceCard insuranceCard = new InsuranceCard();
                insuranceCard.setCardID(rs.getInt("CardID"));
                insuranceCard.setCardNumber(rs.getString("CardNumber"));

                // Map Claim
                Claim claim = new Claim(
                        rs.getInt("ClaimID"),
                        insuranceCard,
                        null, // User object is not fetched here
                        rs.getString("ClaimType"),
                        rs.getString("Status"),
                        rs.getString("Reason"),
                        rs.getTimestamp("SubmittedAt"),
                        rs.getTimestamp("ProcessedAt")
                );

                claims.add(claim);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return claims;
    }
}
