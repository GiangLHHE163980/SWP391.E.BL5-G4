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
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.InsuranceProduct;
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
         try {
                int productId = Integer.parseInt(productIdParam);
                
                // Lấy thông tin sản phẩm từ cơ sở dữ liệu
                InsuranceProduct product = productService.getProductWithAvatarById(productId);
                
                if (product != null) {
                    // Đặt thông tin sản phẩm vào request để gửi tới JSP
                    request.setAttribute("product", product);
                    
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
