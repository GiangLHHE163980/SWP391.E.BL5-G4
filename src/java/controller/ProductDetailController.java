/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.InsuranceProduct;
import model.User;
import service.product.IProductService;
import service.product.ProductService;

/**
 *
 * @author TH Computer
 */
@WebServlet(name = "ProductDetailController", urlPatterns = {"/productDetail"})
public class ProductDetailController extends HttpServlet{
    
    private IProductService productService = new ProductService();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String productIdParam = request.getParameter("ProductID");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        try {
                int productId = Integer.parseInt(productIdParam);
                
                // Lấy thông tin sản phẩm từ cơ sở dữ liệu
                InsuranceProduct product = productService.getProductWithAvatarById(productId);
                
                if (product != null) {
                    // Lấy các sản phẩm liên quan có cùng InsuranceType
                    List<InsuranceProduct> relatedProducts = productService.getProductsByType(product.getInsuranceType(), productId);
                
                     // Kiểm tra trạng thái của thẻ bảo hiểm
                boolean canJoin = false;
                if (user != null) {
                    // Nếu người dùng đã đăng nhập, kiểm tra trạng thái thẻ bảo hiểm
                    canJoin = productService.hasInsuranceCard(user.getUserID(), productId);
                } else {
                    // Nếu người dùng chưa đăng nhập, hiển thị nút "Tham gia"
                    canJoin = true;
                }
                // Đặt thông tin sản phẩm và sản phẩm liên quan vào request
                    request.setAttribute("product", product);
                    request.setAttribute("canJoin", canJoin);
                    request.setAttribute("relatedProducts", relatedProducts);

                    // Chuyển hướng đến trang chi tiết sản phẩm
                    request.getRequestDispatcher("/productDetail.jsp").forward(request, response);
                }
            } catch (NumberFormatException e) {
                Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, e);
                getRequestDispatch(request, response, "error.jsp");
            }
    }
    private void getRequestDispatch(HttpServletRequest request, HttpServletResponse response, String view) {
        try {
            RequestDispatcher rd = request.getRequestDispatcher(view);
            rd.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
