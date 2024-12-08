package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
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

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) {

        try {
            int id = Integer.parseInt(request.getParameter("product_id"));
            System.out.println("deleteProduct::::::" + productService.findById(id));
            productService.delete(id);
            response.sendRedirect("ProductController?action=showFullProduct");
        } catch (IOException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showEditPage(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("product_id"));
//        System.out.println("ahihihihiihihi" + productService.findById(id));
        Object product = productService.findById(id);
        if (product == null) {
            System.out.println("Product not found for ID: " + id);
        } else {
            System.out.println("Product found: " + product);
        }
        request.setAttribute("productList", product);
        getRequestDispatch(request, response, "editProduct.jsp");
    }

    private void editProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // Lấy productId từ request
            int productId = Integer.parseInt(request.getParameter("productId"));
            System.out.println("Editing product with ID: " + productId);

            // Lấy các tham số khác từ request
            String company_name = request.getParameter("provider");
            String address = request.getParameter("address");
            String contact_info = request.getParameter("contactInfo");
            String product_name = request.getParameter("productName");
            String insurance_type = request.getParameter("insuranceType");
            String description = request.getParameter("benefits");
            String conditions = request.getParameter("conditions");
            String price = request.getParameter("cost");
            int company_id = Integer.parseInt(request.getParameter("companyID"));

            // Chuyển đổi giá thành BigDecimal
            BigDecimal cost = null;
            if (price != null && !price.isEmpty()) {
                try {
                    cost = new BigDecimal(price);
                    System.out.println("Giá trị Cost: " + cost);
                } catch (NumberFormatException e) {
                    System.out.println("Lỗi: Giá trị không hợp lệ để chuyển đổi sang BigDecimal.");
                }
            } else {
                System.out.println("Tham số 'price' không tồn tại hoặc bị rỗng.");
            }

            // Tạo dữ liệu công ty bảo hiểm
            InsuranceCompany company = new InsuranceCompany();
            company.setCompanyName(company_name);
            company.setAddress(address);
            company.setContactInfo(contact_info);

            // Tạo dữ liệu sản phẩm bảo hiểm
            InsuranceProduct product = new InsuranceProduct();
            product.setCompanyID(company_id);
            product.setProductName(product_name);
            product.setInsuranceType(insurance_type);
            product.setCost(cost);
            product.setDescription(description);
            product.setConditions(conditions);

            // Cập nhật dữ liệu vào database
            productService.editCompanyAndProduct(productId, company, product);

            // Redirect sau khi cập nhật thành công
            response.sendRedirect("ProductController?action=showFullProduct");
        } catch (NumberFormatException e) {
            System.out.println("Invalid Product ID: " + request.getParameter("productId"));
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Product ID");
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

    private void showAllProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchQuery = request.getParameter("searchQuery");
        String category = request.getParameter("category");
        String sortBy = request.getParameter("sortBy");

        List<InsuranceProduct> products;

        // Lấy danh sách các loại bảo hiểm
        List<InsuranceProduct> insuranceTypes = productService.getDistinctInsuranceTypes();
        request.setAttribute("insuranceTypes", insuranceTypes);

        if (searchQuery != null && !searchQuery.trim().isEmpty() && category != null && !category.trim().isEmpty()) {
            // Tìm kiếm trong danh mục
            products = productService.getProductByNameAndCategory(searchQuery, category);
            request.setAttribute("selectedCategory", category);
        } else if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            // Tìm kiếm không phân loại
            products = productService.getProductByNameWithAvatar(searchQuery);
        } else if (category != null && !category.trim().isEmpty()) {
            // Lọc theo danh mục
            products = productService.getProductsByCategory(category);
            request.setAttribute("selectedCategory", category);
        } else {
            // Hiển thị tất cả sản phẩm
            products = productService.getAllProductsWithSort(sortBy);
        }

        request.setAttribute("products", products);
        request.setAttribute("selectedSortBy", sortBy); // Lưu giá trị sortBy để hiển thị lại trên giao diện

        RequestDispatcher dispatcher = request.getRequestDispatcher("listInsuranceCardForGuest.jsp");
        dispatcher.forward(request, response);
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
            case "showEditPage":
                showEditPage(request, response);
                break;
            case "deleteProduct":
                deleteProduct(request, response);
                break;
            case "showAllProduct":
                showAllProduct(request, response);
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
            case "editProduct":
                editProduct(request, response);
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
