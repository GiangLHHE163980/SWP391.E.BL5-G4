package controller;

import connection.DBContext;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.InsuranceCard;
import model.InsuranceCompany;
import model.InsuranceProduct;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "ViewInsuranceInfoServlet", urlPatterns = {"/viewInsuranceInfo"})
public class InsuranceInfoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the cardID parameter from the request
        String cardIdParam = request.getParameter("cardID");
        int cardID;

        // Get cardID from request parameter
        if (cardIdParam != null) {
            try {
                // Validate and parse cardID
                cardID = Integer.parseInt(cardIdParam);
            } catch (NumberFormatException e) {
                // Redirect to an error page if the cardID is invalid
                response.sendRedirect("error.jsp");
                return;
            }

            // Fetch insurance card details
            InsuranceCard cardDetails = getCardDetails(cardID);

            if (cardDetails != null) {
                // Forward the data to the JSP
                request.setAttribute("cardDetails", cardDetails);
                RequestDispatcher dispatcher = request.getRequestDispatcher("viewInsuranceInfo.jsp");
                dispatcher.forward(request, response);
            } else {
                // Redirect to an error page if no details are found
                response.sendRedirect("error.jsp");
            }
        } else {
            // Redirect to an error page if cardID is not provided in the URL
            response.sendRedirect("error.jsp");
        }

    }

    private InsuranceCard getCardDetails(int cardID) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        InsuranceCard card = new InsuranceCard();

        try {
            conn = DBContext.getConnection();

            // SQL query to join the three tables and fetch insurance card details
            String sql = "SELECT ic.CardID, ic.Status, ic.StartDate, ic.EndDate, "
                    + "ip.ProductID, ip.ProductName, ip.Conditions, ip.InsuranceType, ip.Description, ip.Cost, "
                    + "c.CompanyID, c.CompanyName, c.Address, c.ContactInfo "
                    + "FROM InsuranceCards ic "
                    + "JOIN InsuranceProducts ip ON ic.ProductID = ip.ProductID "
                    + "JOIN InsuranceCompanies c ON ip.CompanyID = c.CompanyID "
                    + "WHERE ic.CardID = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, cardID);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                // Populate InsuranceCompany
                InsuranceCompany company = new InsuranceCompany(
                        rs.getInt("CompanyID"),
                        rs.getString("CompanyName"),
                        rs.getString("Address"),
                        rs.getString("ContactInfo")
                );

                // Populate InsuranceProduct
                InsuranceProduct product = new InsuranceProduct();
                product.setProductID(rs.getInt("ProductID"));
                product.setProductName(rs.getString("ProductName"));
                product.setInsuranceType(rs.getString("InsuranceType"));
                product.setDescription(rs.getString("Description"));
                product.setCost(rs.getBigDecimal("Cost"));
                product.setConditions(rs.getString("Conditions"));
                product.setInsuranceCompany(company);

                // Populate InsuranceCard
                card = new InsuranceCard();
                card.setCardID(rs.getInt("CardID"));
                card.setStatus(rs.getString("Status"));
                card.setStartDate(rs.getDate("StartDate"));
                card.setEndDate(rs.getDate("EndDate"));
                card.setInsuranceProduct(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return card;
    }
}
