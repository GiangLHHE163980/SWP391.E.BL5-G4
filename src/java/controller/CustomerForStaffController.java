/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import service.customerForStaff.CustomerForStaffService;
import service.customerForStaff.ICustomerForStaffService;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "CustomerForStaffController", urlPatterns = {"/CustomerForStaffController"})
public class CustomerForStaffController extends HttpServlet {

    ICustomerForStaffService dao = new CustomerForStaffService();

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
            out.println("<title>Servlet CustomerForStaffController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CustomerForStaffController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private void showAllCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (dao.findAllCustomers() != null) {
            request.setAttribute("listC", dao.findAllCustomers());
            getRequestDispatch(request, response, "/customerstaff/listCustomerForStaff.jsp");
        } else {
            response.sendRedirect("ProductController?action=showFullProduct");
        }

    }


    private void showFullCustomerInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int uId = Integer.parseInt(request.getParameter("CustomerId"));
        Object customer = dao.findCustomerInforById(uId);
        if(customer == null){
            System.out.println("Product not found for ID: " + uId);
             response.sendRedirect("ProductController?action=showFullProduct");
        }else {
             System.out.println("Product found: " + customer);
              request.setAttribute("listCI", customer);
        }
        getRequestDispatch(request, response, "/customerstaff/CustomerDetailForStaff.jsp");

    }
    
        private void updateCardStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int uId = Integer.parseInt(request.getParameter("userID"));
         String cardStatus = request.getParameter("cardStatus");
         dao.updateInsuranceCardStatusByUserId(cardStatus, uId);
        Object customer = dao.findCustomerInforById(uId);
        if(customer == null){
            System.out.println("Product not found for ID: " + uId);
             response.sendRedirect("ProductController?action=showFullProduct");
        }else {
             System.out.println("Product found: " + customer);
              request.setAttribute("listCI", customer);
        }
        getRequestDispatch(request, response, "/customerstaff/CustomerDetailForStaff.jsp");

    }
        
        private void updateInsuranceRequestStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int uId = Integer.parseInt(request.getParameter("userID"));
        String claimStatus = request.getParameter("claimStatus");
        
        dao.updateInsuranceRequestStatusByUserId(claimStatus, uId);
        Object customer = dao.findCustomerInforById(uId);
        if(customer == null){
            System.out.println("Product not found for ID: " + uId);
             response.sendRedirect("ProductController?action=showFullProduct");
        }else {
             System.out.println("Product found: " + customer);
              request.setAttribute("listCI", customer);
        }
        getRequestDispatch(request, response, "/customerstaff/CustomerDetailForStaff.jsp");

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
        String action = request.getParameter("action");
        if (action == null) {
            action = "default"; // Gán giá trị mặc định nếu không có action
        }

        switch (action) {
            case "showAllCustomer":
                showAllCustomer(request, response);
                break;
            case "showFullCustomerInfo":
                showFullCustomerInfo(request, response);
                break;
              case "updateInsuranceRequestStatus":
                updateInsuranceRequestStatus(request, response);
                break;
            default:
                // Chuyển đến trang lỗi nếu action không hợp lệ
                getRequestDispatch(request, response, "error.jsp");
                break;
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
             String action = request.getParameter("action");
        if (action == null) {
            action = "default";
        }

        switch (action) {
            case "updateCardStatus":
                updateCardStatus(request, response);
                break;
            case "updateInsuranceRequestStatus":
                updateInsuranceRequestStatus(request, response);
                break; 
            default:
                getRequestDispatch(request, response, "error.jsp");
                break;
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void getRequestDispatch(HttpServletRequest request, HttpServletResponse response, String view) {
        try {
            RequestDispatcher rd = request.getRequestDispatcher(view);
            rd.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
