/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 * @author TH Computer
 */
@WebFilter("/*")
public class AuthFilter implements Filter{
        @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Nếu cần thiết, bạn có thể khởi tạo ở đây
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // Lấy URI của request
        String uri = req.getRequestURI();
        String queryString = req.getQueryString();
        
        // Các URL không yêu cầu đăng nhập
        if (uri.endsWith("login") || uri.endsWith("register") || uri.endsWith("homepage")
            || (uri.contains("ProductController") && (queryString != null && queryString.contains("showAllProduct")))
            || uri.contains("productDetail")
            || uri.endsWith("forgetPassword") || uri.endsWith("confirmChangePassword") || uri.endsWith("sendemail")      ) {
            chain.doFilter(request, response); // Bỏ qua filter
            return;
        }

        // Kiểm tra đăng nhập
        HttpSession session = req.getSession(false);
        boolean loggedIn = (session != null && session.getAttribute("user") != null);

        if (loggedIn) {
            chain.doFilter(request, response); // Cho phép tiếp tục nếu đã đăng nhập
        } else {
            res.sendRedirect("account/login"); // Chuyển hướng đến trang đăng nhập
        }
    }

    @Override
    public void destroy() {
        // Nếu cần thiết, bạn có thể dọn dẹp tài nguyên ở đây
    }
}
