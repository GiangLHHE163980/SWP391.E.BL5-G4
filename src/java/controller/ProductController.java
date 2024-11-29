package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.product.IProductService;
import service.product.ProductService;

/**
 * ProductController Servlet
 */
public class ProductController extends HttpServlet {

    private final IProductService productService = new ProductService();

    /**
     * Xử lý hành động "showFullProduct".
     */
    private void showFullProduct(HttpServletRequest request, HttpServletResponse response) {
        try {
            // Lấy danh sách sản phẩm từ service
            request.setAttribute("productList", productService.findAll());
            // Điều hướng đến view
            getRequestDispatch(request, response, "productManagement.jsp");
        } catch (Exception e) {
            // Log lỗi và chuyển đến trang lỗi
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, e);
            getRequestDispatch(request, response, "error.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "default"; // Gán giá trị mặc định nếu không có action
        }

        switch (action) {
            case "showFullProduct":
                showFullProduct(request, response);
                break;
            case "showAllProduct":
                // TODO: Thêm logic xử lý nếu cần
                break;
            default:
                // Chuyển đến trang lỗi nếu action không hợp lệ
                getRequestDispatch(request, response, "error.jsp");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "default";
        }

        switch (action) {
            case "createProduct":
                // TODO: Xử lý tạo sản phẩm
                break;
            case "addProduct":
                // TODO: Xử lý thêm sản phẩm
                break;
            default:
                getRequestDispatch(request, response, "error.jsp");
                break;
        }
    }

    /**
     * Hàm hỗ trợ điều hướng.
     */
    private void getRequestDispatch(HttpServletRequest request, HttpServletResponse response, String view) {
        try {
            RequestDispatcher rd = request.getRequestDispatcher(view);
            rd.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
