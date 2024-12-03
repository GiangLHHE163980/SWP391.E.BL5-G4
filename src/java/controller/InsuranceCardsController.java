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
        HttpSession session = request.getSession();
         

        String userIdParam = request.getParameter("userID");

        int userId = Integer.parseInt(userIdParam); // Parse the userId from the URL parameter

        request.setAttribute("userID", userId); // Store userId in session

        List<InsuranceCard> insuranceCards = insuranceService.getInsuranceCardsByUser(userId);

        session.setAttribute("insuranceCards", insuranceCards);
        RequestDispatcher dispatcher = request.getRequestDispatcher("listInsuranceCards.jsp");
        dispatcher.forward(request, response);

    }

    
}
