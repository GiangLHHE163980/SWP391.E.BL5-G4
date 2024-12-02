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
        }else if ("/account/logout".equals(path)) {
        // Xử lý đăng xuất
        HttpSession session = request.getSession();
        session.invalidate();  // Hủy session
        response.sendRedirect(request.getContextPath()); 
        }else if ("/account/forgetPassword".equals(path)) {
            request.getRequestDispatcher("/account/forgetPassword.jsp").forward(request, response);
        }else if ("/account/confirmChangePassword".equals(path)) {
            request.getRequestDispatcher("/account/confirmChangePassword.jsp").forward(request, response);
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
        }else if ("/account/sendemail".equals(path)) {
             String email = request.getParameter("email");
    // Kiểm tra nếu gửi mã xác thực
             sendEmail(email, request, response);
        }else if ("/account/register".equals(path)){
             register(request, response);
        }else if ("/account/forgetPassword".equals(path)) {
             forgetPassword(request, response);
        }else if ("/account/confirmChangePassword".equals(path)) {
             confirmChangePassword(request, response);
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
    //Đăng nhập
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
                    response.sendRedirect(request.getContextPath()+"homePage.jsp");  // Chuyển hướng đến trang chính
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
    //Gửi email
    private void sendEmail(String email, HttpServletRequest request, HttpServletResponse response) throws IOException{
        if (email != null && email.contains("@") && email.endsWith(".com")) {
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
        } else {
            response.getWriter().write("Email không hợp lệ. Vui lòng kiểm tra lại.");
        }
    }
    //Đăng ký
    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Lấy thông tin từ form đăng ký
    String fullName = request.getParameter("fullName");
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String confirmPassword = request.getParameter("confirmPassword");
    String email = request.getParameter("email");
    String inputVerificationCode = request.getParameter("verificationCode");

    // Kiểm tra mã xác thực
    HttpSession session = request.getSession();
    String sessionVerificationCode = (String) session.getAttribute("verificationCode");

    // Kiểm tra mật khẩu khớp
    if (!password.equals(confirmPassword)) {
        request.setAttribute("error", "Mật khẩu không khớp.");
        request.getRequestDispatcher("/account/register.jsp").forward(request, response);
        return;
    }

    // Kiểm tra mã xác thực
    if (sessionVerificationCode == null || !sessionVerificationCode.equals(inputVerificationCode)) {
        request.setAttribute("error", "Mã xác thực không đúng.");
        request.getRequestDispatcher("/account/register.jsp").forward(request, response);
        return;
    }
    // Thêm người dùng mới vào cơ sở dữ liệu
    User newUser = new User();
    newUser.setFullName(fullName);
    newUser.setUsername(username);
    newUser.setPasswordHash(password);
    newUser.setEmail(email);

    AccountService accountService = new AccountService();
    accountService.add(newUser);

    // Xóa mã xác thực khỏi session sau khi đăng ký thành công
    session.removeAttribute("verificationCode");

    // Chuyển hướng người dùng tới trang đăng ký thành công
    request.setAttribute("message", "Thay đổi mật khẩu thành công.");
    response.sendRedirect(request.getContextPath()+"/account/login");
    }
    //Quên mật khẩu
    private void forgetPassword(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String inputVerificationCode = request.getParameter("verificationCode");
        
        HttpSession session = request.getSession();
        String sessionVerificationCode = (String) session.getAttribute("verificationCode");
        //Kiểm tra mã xác thực
        if (sessionVerificationCode == null || !sessionVerificationCode.equals(inputVerificationCode)) {
            request.setAttribute("error", "Mã xác thực không đúng hoặc đã hết hạn.");
            request.getRequestDispatcher("/account/forgetPassword.jsp").forward(request, response);
        return;
        }
        String email = request.getParameter("email");
        request.setAttribute("email", email);
        session.removeAttribute("verificationCode");
        request.getRequestDispatcher("/account/confirmChangePassword.jsp").forward(request, response);
    }
    //Xác nhận thay đổi mật khẩu
    private void confirmChangePassword(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String email = request.getParameter("email");
        String newPassword = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        
        if (newPassword.equals(confirmPassword)) {
            try {
                accountService.updatePasswordByEmail(email, newPassword);
                request.setAttribute("message", "Thay đổi mật khẩu thành công.");
                response.sendRedirect(request.getContextPath()+ "/account/login");
            } catch (RuntimeException e) {
                request.setAttribute("errorMessage", e.getMessage());
                request.getRequestDispatcher("confirmChangePassword.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("errorMessage", "Mật khẩu không khớp.");
            request.getRequestDispatcher("confirmChangePassword.jsp").forward(request, response);
        }
    }
}
