package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
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
@WebServlet(name="ProductController", urlPatterns={"/ProductController"})
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
            getRequestDispatch(request, response, "/product/productManagement.jsp");
        } catch (Exception e) {
            // Log lỗi và chuyển đến trang lỗi
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, e);
            getRequestDispatch(request, response, "error.jsp");
        }
    }

    private void showViewPage(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("product_id"));
//        System.out.println("ahihihihiihihi" + productService.findById(id));
        Object product = productService.findById(id);
        if (product == null) {
            System.out.println("Product not found for ID: " + id);
        } else {
            System.out.println("Product found: " + product);
        }
        request.setAttribute("productList", product);
        getRequestDispatch(request, response, "product/viewProduct.jsp");
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
        getRequestDispatch(request, response, "/product/editProduct.jsp");
    }

    private void editProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
    try {
        // Lấy productId từ request và validate
        int productId;
        try {
            productId = Integer.parseInt(request.getParameter("productId"));
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Product ID");
            return;
        }
        System.out.println("Editing product with ID: " + productId);

        // Lấy các tham số khác từ request và validate
        String company_name = request.getParameter("provider");
        String address = request.getParameter("address");
        String contact_info = request.getParameter("contactInfo");
        String product_name = request.getParameter("productName");
        String insurance_type = request.getParameter("insuranceType");
        String description = request.getParameter("benefits");
        String conditions = request.getParameter("conditions");
        String price = request.getParameter("cost");
        int company_id;

        // Validate các tham số bắt buộc
        if (company_name == null || company_name.isEmpty()) {
            response.getWriter().println("Company name is required.");
            return;
        }
        if (address == null || address.isEmpty()) {
            response.getWriter().println("Address is required.");
            return;
        }
        if (contact_info == null || contact_info.isEmpty()) {
            response.getWriter().println("Contact information is required.");
            return;
        }
        if (product_name == null || product_name.isEmpty()) {
            response.getWriter().println("Product name is required.");
            return;
        }
        if (insurance_type == null || insurance_type.isEmpty()) {
            response.getWriter().println("Insurance type is required.");
            return;
        }
        if (description == null || description.isEmpty()) {
            response.getWriter().println("Description is required.");
            return;
        }
        if (conditions == null || conditions.isEmpty()) {
            response.getWriter().println("Conditions are required.");
            return;
        }

        // Validate company_id
        try {
            company_id = Integer.parseInt(request.getParameter("companyID"));
        } catch (NumberFormatException e) {
            response.getWriter().println("Invalid Company ID.");
            return;
        }

        // Validate price and convert to BigDecimal
        BigDecimal cost = null;
        if (price != null && !price.isEmpty()) {
            try {
                cost = new BigDecimal(price);
                if (cost.compareTo(BigDecimal.ZERO) <= 0) {
                    response.getWriter().println("Price must be greater than zero.");
                    return;
                }
            } catch (NumberFormatException e) {
                response.getWriter().println("Invalid price format.");
                return;
            }
        } else {
            response.getWriter().println("Price is required.");
            return;
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

    } catch (Exception e) {
        e.printStackTrace();
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your request.");
    }
}


    private void showAddPage(HttpServletRequest request, HttpServletResponse response) {
        getRequestDispatch(request, response, "product/addProduct.jsp");
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

    // Validate inputs
    if (company_name == null || company_name.isEmpty()) {
        response.getWriter().println("Company name is required.");
        return;
    }
    if (address == null || address.isEmpty()) {
        response.getWriter().println("Address is required.");
        return;
    }
    if (contact_info == null || contact_info.isEmpty()) {
        response.getWriter().println("Contact information is required.");
        return;
    }
    if (product_name == null || product_name.isEmpty()) {
        response.getWriter().println("Product name is required.");
        return;
    }
    if (insurance_type == null || insurance_type.isEmpty()) {
        response.getWriter().println("Insurance type is required.");
        return;
    }
    if (description == null || description.isEmpty()) {
        response.getWriter().println("Description is required.");
        return;
    }
    if (conditions == null || conditions.isEmpty()) {
        response.getWriter().println("Conditions are required.");
        return;
    }

    // Convert String to BigDecimal for price validation
    if (price != null && !price.isEmpty()) {
        try {
            cost = new BigDecimal(price);
            if (cost.compareTo(BigDecimal.ZERO) <= 0) {
                response.getWriter().println("Price must be greater than zero.");
                return;
            }
        } catch (NumberFormatException e) {
            response.getWriter().println("Invalid price format.");
            return;
        }
    } else {
        response.getWriter().println("Price is required.");
        return;
    }

    // Create InsuranceCompany object
    InsuranceCompany company = new InsuranceCompany();
    company.setCompanyName(company_name);
    company.setAddress(address);
    company.setContactInfo(contact_info);

    // Create InsuranceProduct object
    InsuranceProduct product = new InsuranceProduct();
    product.setProductName(product_name);
    product.setInsuranceType(insurance_type);
    product.setCost(cost);
    product.setDescription(description);
    product.setConditions(conditions);

    // Add the product and company to the database
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

        // Tìm kiếm và lọc category
        if (searchQuery != null && !searchQuery.trim().isEmpty() && category != null && !category.trim().isEmpty()) {
            if (sortBy != null && !sortBy.trim().isEmpty()) {
                products = productService.getProductByNameAndCategoryWithSort(searchQuery, category, sortBy);
            } else {
                products = productService.getProductByNameAndCategory(searchQuery, category);
            }
            request.setAttribute("selectedCategory", category);
            // Tìm kiếm không phân loại và sắp xếp 
        } else if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            if (sortBy != null && !sortBy.trim().isEmpty()) {
                products = productService.getProductByNameWithAvatarAndSort(searchQuery, sortBy);
            } else {
                products = productService.getProductByNameWithAvatar(searchQuery);
            }
            // Lọc category và sắp xếp
        } else if (category != null && !category.trim().isEmpty()) {
            if (sortBy != null && !sortBy.trim().isEmpty()) {
                products = productService.getProductsByCategoryWithSort(category, sortBy);
            } else {
                products = productService.getProductsByCategory(category);
            }
            request.setAttribute("selectedCategory", category);
        } else {
            // Trường hợp không có tìm kiếm và sắp xếp, hiển thị tất cả sản phẩm mà không sắp xếp
            if (sortBy == null || sortBy.trim().isEmpty()) {
                products = productService.getAllProducts();
            } else {
                // Hiển thị tất cả sản phẩm có sắp xếp
                products = productService.getAllProductsWithSort(sortBy);
            }
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
            case "showViewPage":
                showViewPage(request, response);
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
