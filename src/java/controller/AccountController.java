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
import jakarta.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.account.AccountService;
import service.account.IAccountService;
import model.User;
import sender.SendEmail;
/**
 *
 * @author Lenovo
 */
public class AccountController extends HttpServlet {
   
    
    private static IAccountService accountService = new AccountService();
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AccountController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AccountController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String path = request.getServletPath();
        if ("/account/login".equals(path)) {
            request.getRequestDispatcher("/account/login.jsp").forward(request, response);
        }else if ("/account/register".equals(path)) {
            request.getRequestDispatcher("/account/register.jsp").forward(request, response);
        } else if ("/account/logout".equals(path)) {
        // Xử lý đăng xuất
        HttpSession session = request.getSession();
        session.invalidate();  // Hủy session
        response.sendRedirect(request.getContextPath()); 
    }
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
         String path = request.getServletPath();
         if ("/account/login".equals(path)) {
                login(request, response);
        }else if ("/account/register".equals(path)) {
            String email = request.getParameter("email");

    // Kiểm tra nếu gửi mã xác thực
            if ("true".equals(request.getParameter("sendVerificationCode"))) {
            // Tạo mã xác thực ngẫu nhiên
                    String verificationCode = SendEmail.getRandomCode();

                    // Gửi email chứa mã xác thực
                    SendEmail.sendEmail(email, verificationCode);

                    // Lưu mã xác thực vào session để kiểm tra khi người dùng nhập mã
                    HttpSession session = request.getSession();
                    session.setAttribute("verificationCode", verificationCode);

                    // Gửi phản hồi về kết quả gửi email
                    response.getWriter().write("Mã xác thực đã được gửi đến email của bạn.");
            }
        }
    }
    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    private void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            // Gọi AccountService để kiểm tra đăng nhập
            User user = accountService.login(username, password);

            if (user != null) {
                // Kiểm tra trạng thái tài khoản
                if (user.getIsActive()) {
                    // Đăng nhập thành công, lưu thông tin vào session
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                    response.sendRedirect(request.getContextPath());  // Chuyển hướng đến trang chính
                } else{
                    // Tài khoản bị ban, thông báo cho người dùng
                    request.setAttribute("error", "Tài khoản của bạn đã bị ban.");
                    request.getRequestDispatcher("/account/login.jsp").forward(request, response);
                }
            } else {
                // Tên đăng nhập hoặc mật khẩu sai
                request.setAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng.");
                request.getRequestDispatcher("/account/login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Đã có lỗi xảy ra, vui lòng thử lại sau.");
            request.getRequestDispatcher("/account/login.jsp").forward(request, response);
        }
    }
}
