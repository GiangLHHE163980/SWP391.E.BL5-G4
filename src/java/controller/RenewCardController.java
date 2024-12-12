/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import connection.DBContext;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
@WebServlet(name = "RenewCardController", urlPatterns = {"/RenewCardController"})
public class RenewCardController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RenewCardController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RenewCardController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
            RequestDispatcher dispatcher = request.getRequestDispatcher("renewCard.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ClaimsRequestController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

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

        // Retrieve form parameters
        int userID = Integer.parseInt(request.getParameter("userID"));
        int cardID = Integer.parseInt(request.getParameter("cardID"));

        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");

        try {
            // Database connection
            Connection connection = DBContext.getConnection();

            // SQL query to update card validity
            String updateQuery = "UPDATE InsuranceCards SET StartDate = ?, EndDate = ?, Status = ? WHERE CardID = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setDate(1, Date.valueOf(startDateStr));
            preparedStatement.setDate(2, Date.valueOf(endDateStr));
            preparedStatement.setString(3, "Pending Renew");
            preparedStatement.setInt(4, cardID);

            // Execute the update
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                // Success: Redirect to a confirmation page
                
                response.sendRedirect("insuranceCards?userID=" + userID);
            } else {
                // Failure: Redirect to an error page
                request.setAttribute("message", "Không thể gia hạn gói bảo hiểm.");
                request.getRequestDispatcher("errorPage1.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
