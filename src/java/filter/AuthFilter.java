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
import java.util.List;
import model.User;
import service.account.AccountService;
import service.account.IAccountService;

/**
 *
 * @author TH Computer
 */
@WebFilter("/*")
public class AuthFilter implements Filter {

    private static IAccountService accountService = new AccountService();

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
        if (uri.endsWith("login") || uri.endsWith("register") || uri.endsWith("homepage") || uri.endsWith("403")
                || (uri.contains("ProductController") && (queryString != null && queryString.contains("showAllProduct")))
                || uri.contains("productDetail") || uri.endsWith("logout")
                || uri.endsWith("forgetPassword") || uri.endsWith("confirmChangePassword") || uri.endsWith("sendemail")) {
            chain.doFilter(request, response); // Bỏ qua filter
            return;
        }

        // Kiểm tra đăng nhập
        HttpSession session = req.getSession(false);
        boolean loggedIn = (session != null && session.getAttribute("user") != null);

        if (loggedIn) {
            User user = (User) session.getAttribute("user");
            List<String> roles = accountService.getUserRoles(user.getUserID());

            //Path của Admin
            if (uri.contains("manage-customer")
                    || uri.contains("manage-customer") && queryString != null && queryString.contains("action=add")
                    || uri.contains("manage-customer") && queryString != null && queryString.contains("action=edit")
                    || uri.contains("profileAdmin")
                    || uri.contains("profileAdmin") && queryString != null && queryString.contains("action=edit")                    
                    || uri.contains("manage-staff")
                    || uri.contains("manage-staff") && queryString != null && queryString.contains("action=add")
                    || uri.contains("manage-staff") && queryString != null && queryString.contains("action=edit")
                    || uri.contains("BlogManageController")
                    || uri.contains("BlogManageController") && queryString != null && queryString.contains("action=add")
                    || uri.contains("BlogManageController") && queryString != null && queryString.contains("action=edit")
                    || uri.contains("BlogManageController") && queryString != null && queryString.contains("action=viewDetail")
                    || uri.contains("SliderController")
                    || uri.contains("slider-form.jsp")
                    || uri.contains("SliderController") && queryString != null && queryString.contains("action=edit")) {
                if (roles.contains("Admin")) {
                    chain.doFilter(request, response); // Cho phép quyền truy cập
                } else {
                    res.sendRedirect("403");
                }
            }
            //Path của Staff
            if (uri.contains("ProductController") && queryString != null && queryString.contains("action=showEditPage")
                    || uri.contains("HomePageForStaffController") && queryString != null && queryString.contains("action=homepageForStaff")
                    || uri.contains("CustomerForStaffController") && queryString != null && queryString.contains("action=showFullCustomerInfo")
                    || uri.contains("ProductController") && queryString != null && queryString.contains("action=showViewPage")
                    || uri.contains("ProductController") && queryString != null && queryString.contains("action=showAddPage")
                    || uri.contains("ProductController") && queryString != null && queryString.contains("action=showFullProduct")
                    || uri.contains("ProductController") && queryString != null && queryString.contains("action=deleteProduct")
                    || uri.contains("ProductController") && queryString != null && queryString.contains("action=editProduct")
                    || uri.contains("ProductController") && queryString != null && queryString.contains("action=addProduct")
                    || uri.contains("CustomerForStaffController") && queryString != null && queryString.contains("action=showFullRequestCardInfo")
                    || uri.contains("CustomerForStaffController") && queryString != null && queryString.contains("action=updateInsuranceRequestStatus")
                    || uri.contains("CustomerForStaffController") && queryString != null && queryString.contains("action=updateCardStatus")
                    || uri.contains("CustomerForStaffController") && queryString != null && queryString.contains("action=showAllCardRequest")
                    || uri.contains("CustomerForStaffController") && queryString != null && queryString.contains("action=showAllCustomer")
                    || uri.contains("CustomerForStaffController") && queryString != null && queryString.contains("action=updateCardStatusByCardId")) {
                if (roles.contains("Staff") || roles.contains("Admin")) {
                    chain.doFilter(request, response); // Cho phép quyền truy cập
                } else {
                    res.sendRedirect("403");
                }
            }
        } else {
            res.sendRedirect("account/login"); // Chuyển hướng đến trang đăng nhập
        }
    }

    @Override
    public void destroy() {
        // Nếu cần thiết, bạn có thể dọn dẹp tài nguyên ở đây
    }
}
