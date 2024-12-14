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
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Claim;
import model.InsuranceCard;
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

    // Hàm định dạng ngày
    private String formatDate(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(date);
    }

    private void showAllCardRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
//       List<InsuranceCard> cardRequests = dao.findAllCardRequest(); // Giả sử dao đã được khởi tạo
//        if (cardRequests == null || cardRequests.isEmpty()) {
//            // Nếu danh sách null hoặc rỗng, gửi trực tiếp về JSP
//            request.setAttribute("listR", cardRequests);
//            getRequestDispatch(request, response, "/customerstaff/CardRequestList.jsp");
//        } else {
//            // Format ngày trong danh sách
//            for (InsuranceCard cardRequest : cardRequests) {
//                if (cardRequest.getStartDate() != null) {
//                    // Định dạng ngày startDate
//                    String formattedStartDate = formatDate((Date) cardRequest.getDate());
//                    cardRequest.setFormattedDate(formattedStartDate);
//                }
//            }
//
//            // Gửi danh sách sau khi format về JSP
//            request.setAttribute("listR", cardRequests);
//            getRequestDispatch(request, response, "/customerstaff/CardRequestList.jsp");
//        }

        if (dao.findAllCardRequest() != null) {
            request.setAttribute("listR", dao.findAllCardRequest());
            getRequestDispatch(request, response, "/customerstaff/CardRequestList.jsp");
        } else {
            response.sendRedirect("/HomePageForStaffController?action=homepageForStaff");
        }

    }

    private void showFullRequestCardInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int rId = Integer.parseInt(request.getParameter("request_id"));
        Object card = dao.findCardRequestbyId(rId);
        if (card == null) {
            System.out.println("Product not found for ID: " + rId);
            response.sendRedirect("/CustomerForStaffController?action=showAllCardRequest");
        } else {
            System.out.println("Product found: " + card);
            request.setAttribute("listRC", card);
        }
        getRequestDispatch(request, response, "/customerstaff/CardRequestDetail.jsp");

    }

    private void showFullCustomerInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int uId = Integer.parseInt(request.getParameter("CustomerId"));
            int cId = Integer.parseInt(request.getParameter("CardId"));

            // Lấy danh sách yêu cầu bảo hiểm
            List<Claim> claims = dao.findAllClaim(uId, cId);

            // Kiểm tra nếu không có yêu cầu bảo hiểm
            if (claims == null || claims.isEmpty()) {
                // Gửi thông báo nếu không có yêu cầu bảo hiểm
                request.setAttribute("errorMessage", "Không tìm thấy yêu cầu bảo hiểm cho khách hàng và thẻ bảo hiểm này.");
                // Forward đến trang hiển thị lỗi hoặc thông báo
                getRequestDispatch(request, response, "/customerstaff/CustomerDetailForStaff.jsp");
            } else {
                // Nếu có yêu cầu bảo hiểm, tiếp tục gán vào request
                request.setAttribute("listCl", claims);
                getRequestDispatch(request, response, "/customerstaff/CustomerDetailForStaff.jsp");
            }
        } catch (NumberFormatException e) {
            // Xử lý lỗi nếu ID không hợp lệ
            System.out.println("Error parsing CustomerId or CardId: " + e.getMessage());
            response.sendRedirect("ProductController?action=showFullProduct");
        }
    }


    private void updateCardStatusByCardId(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int rId = Integer.parseInt(request.getParameter("request_id"));
        String cardStatus = request.getParameter("cardStatus");
        dao.updateInsuranceCardStatusByCardId(cardStatus, rId);

        response.sendRedirect("/SWP391_E_BL5_G4/CustomerForStaffController?action=showAllCardRequest");

    }

    private void updateCardStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int uId = Integer.parseInt(request.getParameter("userID"));
        String cardStatus = request.getParameter("cardStatus");
        dao.updateInsuranceCardStatusByUserId(cardStatus, uId);
        Object customer = dao.findCustomerInforById(uId);
        if (customer == null) {
            System.out.println("Product not found for ID: " + uId);
            response.sendRedirect("ProductController?action=showFullProduct");
        } else {
            System.out.println("Product found: " + customer);
            request.setAttribute("listCI", customer);
        }
        getRequestDispatch(request, response, "/customerstaff/CustomerDetailForStaff.jsp");

    }

    private void updateInsuranceRequestStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int cId = Integer.parseInt(request.getParameter("claimID"));
        String claimStatus = request.getParameter("claimStatus");
        dao.updateInsuranceRequestStatusByUserId(claimStatus, cId);
        showFullCustomerInfo(request, response);

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
            case "showAllCardRequest":
                showAllCardRequest(request, response);
                break;
            case "showFullRequestCardInfo":
                showFullRequestCardInfo(request, response);
                break;
            case "updateCardStatusByCardId":
                updateCardStatusByCardId(request, response);
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
