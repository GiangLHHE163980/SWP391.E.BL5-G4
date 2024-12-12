package controller;

import connection.DBContext;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.InsuranceCard;
import model.User;
import model.InsuranceProduct;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import service.insurance.IInsuranceCardsService;
import service.insurance.InsuranceCardsService;

@WebServlet(name = "InsuranceCardServlet", urlPatterns = {"/insuranceCards"})
public class InsuranceCardsController extends HttpServlet {

    private static IInsuranceCardsService insuranceService = new InsuranceCardsService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Get the session without creating a new one

        if (session != null) {
            Integer userId = (Integer) session.getAttribute("userID"); // Get userID from session

            if (userId != null) {
                List<InsuranceCard> insuranceCards = insuranceService.getInsuranceCardsByUser(userId); // Fetch insurance cards using userID

                request.setAttribute("insuranceCards", insuranceCards); // Pass insurance cards to the request
                RequestDispatcher dispatcher = request.getRequestDispatcher("listInsuranceCards.jsp");
                dispatcher.forward(request, response);
            } else {
                // Redirect to login page if userID is not found in session
                response.sendRedirect("account/login");
            }
        } else {
            // Redirect to login page if session does not exist
            response.sendRedirect("account/login");
        }
    }
}
