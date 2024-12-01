package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.InsuranceCompany;
import model.InsuranceProduct;
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
            String searchname = request.getParameter("search");
            request.setAttribute("search", searchname);
            if (searchname != null) {
                request.setAttribute("productList", productService.getProductByName(searchname));
            } else {
                // Lấy danh sách sản phẩm từ service
                request.setAttribute("productList", productService.findAll());
            }
            // Điều hướng đến view
            getRequestDispatch(request, response, "productManagement.jsp");
        } catch (Exception e) {
            // Log lỗi và chuyển đến trang lỗi
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, e);
            getRequestDispatch(request, response, "error.jsp");
        }
    }

    private void showAddPage(HttpServletRequest request, HttpServletResponse response) {
        getRequestDispatch(request, response, "addOrEditProduct.jsp");
    }

    // add new product with company
    private void addProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String company_name = request.getParameter("provider");
        String address = request.getParameter("address");
        String contact_info = request.getParameter("contactInfo");
        String product_name = request.getParameter("productName");
        String insurance_type = request.getParameter("insuranceType");
        String description = request.getParameter("benefits");
        String conditions = request.getParameter("conditions");
        String price = request.getParameter("cost"); // Lấy chuỗi từ request
        BigDecimal cost = null;
        // convert String to BigDecimal
        if (price != null && !price.isEmpty()) {
            try {
                cost = new BigDecimal(price); // Chuyển từ String sang BigDecimal
                System.out.println("Giá trị Cost: " + cost);
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Giá trị không hợp lệ để chuyển đổi sang BigDecimal.");
            }
        } else {
            System.out.println("Tham số 'price' không tồn tại hoặc bị rỗng.");
        }

        //convert to add product
         // Tạo dữ liệu mẫu cho công ty bảo hiểm
        InsuranceCompany company = new InsuranceCompany();
        company.setCompanyName(company_name);
        company.setAddress(address);
        company.setContactInfo(contact_info);

        // Tạo dữ liệu mẫu cho sản phẩm bảo hiểm
        InsuranceProduct product = new InsuranceProduct();
        product.setProductName(product_name);
        product.setInsuranceType(insurance_type);
        product.setCost(cost); // Chi phí 5 triệu
        product.setDescription(description);
        product.setConditions(conditions);

        // Thêm dữ liệu vào database
        productService.addCompanyAndProduct(company, product);

        response.sendRedirect("ProductController?action=showFullProduct");

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
            case "showAddPage":
                showAddPage(request, response);
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
             case "addProduct":
                addProduct(request, response);
                break;
            case "createProduct":
                // TODO: Xử lý tạo sản phẩm
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
