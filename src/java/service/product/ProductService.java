/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.product;

import connection.DBContext;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.InsuranceCompany;
import model.InsuranceProduct;

/**
 *
 * @author Lenovo
 */
public class ProductService implements IProductService {

    private final Connection connection = DBContext.getConnection();
    // Get all product
    private static final String GET_ALL_PRODUCT = "SELECT \n"
            + "    ip.ProductID,\n"
            + "    ip.ProductName,\n"
            + "    ip.InsuranceType,\n"
            + "    ip.Cost,\n"
            + "    ip.Description,\n"
            + "    ip.Conditions,\n"
            + "    ic.CompanyName\n"
            + "FROM InsuranceProducts ip\n"
            + "JOIN InsuranceCompanies ic ON ip.CompanyID = ic.CompanyID;";
    // Search by product name
    private static final String GET_PRODUCT_BY_NAME = "SELECT \n"
            + "    ip.ProductID,\n"
            + "    ip.ProductName,\n"
            + "    ip.InsuranceType,\n"
            + "    ip.Cost,\n"
            + "    ip.Description,\n"
            + "    ip.Conditions,\n"
            + "    ic.CompanyName\n"
            + "FROM InsuranceProducts ip\n"
            + "JOIN InsuranceCompanies ic ON ip.CompanyID = ic.CompanyID\n"
            + "WHERE ip.ProductName LIKE ?";

    // Insert new company
    private static final String INSERT_COMPANY = "INSERT INTO InsuranceCompanies (CompanyName, Address, ContactInfo) VALUES (?, ?, ?)";
    // Insert new product
    private static final String INSERT_PRODUCT = "INSERT INTO InsuranceProducts (CompanyID, ProductName, InsuranceType, Cost, Description, Conditions) VALUES (?, ?, ?, ?, ?, ?)";
    // Update company info
    private static final String UPDATE_COMPANY = "UPDATE InsuranceCompanies SET CompanyName = ?, Address = ?, ContactInfo = ? WHERE CompanyID = ?";
// Update product info
    private static final String UPDATE_PRODUCT = "UPDATE InsuranceProducts SET ProductName = ?, InsuranceType = ?, Cost = ?, Description = ?, Conditions = ? WHERE ProductID = ?";
    // find product by ID
    // Update product info
    private static final String GET_PRODUCT_BY_ID
            = "SELECT \n"
            + "    ip.ProductID,\n"
            + "    ip.CompanyID,\n"
            + // Thêm cột CompanyID
            "    ip.ProductName,\n"
            + "    ip.InsuranceType,\n"
            + "    ip.Cost,\n"
            + "    ip.Description,\n"
            + "    ip.Conditions,\n"
            + "    ic.CompanyName,\n"
            + "    ic.Address,\n"
            + "    ic.ContactInfo\n"
            + "FROM InsuranceProducts ip\n"
            + "JOIN InsuranceCompanies ic ON ip.CompanyID = ic.CompanyID\n"
            + "WHERE ip.ProductID = ?";

    private static final String DELETE_PRODUCT_AND_COMPANY
            = "DELETE FROM Documents\n" //-- Xóa các tài liệu liên quan đến Claims
            + "WHERE ClaimID IN (SELECT ClaimID FROM Claims WHERE CardID IN (SELECT CardID FROM InsuranceCards WHERE ProductID = ?));"
            + //-- Xóa các yêu cầu bảo hiểm liên quan đến InsuranceCards
            "DELETE FROM Claims\n"
            + "WHERE CardID IN (SELECT CardID FROM InsuranceCards WHERE ProductID = ?)"
            //-- Xóa các thẻ bảo hiểm liên quan đến InsuranceProducts
            + "DELETE FROM InsuranceCards\n"
            + "WHERE ProductID = ?;"
            //-- Xóa các sản phẩm bảo hiểm
            + "   DELETE FROM InsuranceProducts\n"
            + "WHERE ProductID = ?; "
            // -- Xóa các công ty bảo hiểm nếu không còn sản phẩm bảo hiểm nào
            + "  DELETE FROM InsuranceCompanies\n"
            + "WHERE CompanyID NOT IN (SELECT CompanyID FROM InsuranceProducts);";

    private static final String SELECT_TOP_PRODUCT = "SELECT TOP (?) ip.ProductID, ip.ProductName, ip.InsuranceType, ip.Cost, ip.Description, ip.Conditions, ip.Avatar "
            + "FROM InsuranceProducts ip";
    
    // Query mới để lấy sản phẩm theo tên kèm theo Avatar
    private static final String GET_PRODUCT_BY_NAME_WITH_AVATAR = "SELECT \n" +
            "    ip.ProductID,\n" +
            "    ip.ProductName,\n" +
            "    ip.InsuranceType,\n" +
            "    ip.Cost,\n" +
            "    ip.Description,\n" +
            "    ip.Conditions,\n" +
            "    ip.Avatar,\n" +
            "    ic.CompanyName\n" +
            "FROM InsuranceProducts ip\n" +
            "JOIN InsuranceCompanies ic ON ip.CompanyID = ic.CompanyID\n" +
            "WHERE ip.ProductName LIKE ?";
    
        private static final String GET_ALL_PRODUCTS_WITH_AVATAR = "SELECT \n" +
            "    ip.ProductID,\n" +
            "    ip.ProductName,\n" +
            "    ip.InsuranceType,\n" +
            "    ip.Cost,\n" +
            "    ip.Description,\n" +
            "    ip.Conditions,\n" +
            "    ip.Avatar,\n" +
            "    ic.CompanyName\n" +
            "FROM InsuranceProducts ip\n" +
            "JOIN InsuranceCompanies ic ON ip.CompanyID = ic.CompanyID";
        
        private static final String GET_DISTINCT_INSURANCE_TYPES = 
            "SELECT DISTINCT InsuranceType, NULL AS ProductID, NULL AS ProductName, " +
            "NULL AS Cost, NULL AS Description, NULL AS Conditions, NULL AS Avatar, NULL AS CompanyName " +
            "FROM InsuranceProducts";

        private static final String GET_PRODUCTS_BY_CATEGORY = "SELECT \n" +
                "    ip.ProductID,\n" +
                "    ip.ProductName,\n" +
                "    ip.InsuranceType,\n" +
                "    ip.Cost,\n" +
                "    ip.Description,\n" +
                "    ip.Conditions,\n" +
                "    ip.Avatar,\n" +
                "    ic.CompanyName\n" +
                "FROM InsuranceProducts ip\n" +
                "JOIN InsuranceCompanies ic ON ip.CompanyID = ic.CompanyID\n" +
                "WHERE ip.InsuranceType = ?";
        
        private static final String GET_PRODUCT_BY_NAME_AND_CATEGORY = "SELECT \n" +
            "    ip.ProductID,\n" +
            "    ip.ProductName,\n" +
            "    ip.InsuranceType,\n" +
            "    ip.Cost,\n" +
            "    ip.Description,\n" +
            "    ip.Conditions,\n" +
            "    ip.Avatar,\n" +
            "    ic.CompanyName\n" +
            "FROM InsuranceProducts ip\n" +
            "JOIN InsuranceCompanies ic ON ip.CompanyID = ic.CompanyID\n" +
            "WHERE ip.ProductName LIKE ? AND ip.InsuranceType = ?";

        private static final String SORT_BY_COST_ASC = " ORDER BY ip.Cost ASC";
        private static final String SORT_BY_COST_DESC = " ORDER BY ip.Cost DESC";
        private static final String SORT_BY_DATE_NEWEST = " ORDER BY ip.CreatedAt DESC";
        private static final String SORT_BY_DATE_OLDEST = " ORDER BY ip.CreatedAt ASC";
    //delete product and company
    @Override
    public void delete(int id) {
        String query = DELETE_PRODUCT_AND_COMPANY;
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.setInt(2, id);
            ps.setInt(3, id);
            ps.setInt(4, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    //edit product and company by ID
    @Override
    public void editCompanyAndProduct(int productId, InsuranceCompany c, InsuranceProduct p) {
        String queryCompany = UPDATE_COMPANY;  // declare update query for company
        String queryProduct = UPDATE_PRODUCT;  // declare update query for product
        Connection conn = null;

        try {
            conn = connection; // Assuming `connection` is your database connection
            conn.setAutoCommit(false); // Start transaction

            // Update company info
            PreparedStatement psCompany = conn.prepareStatement(queryCompany);
            psCompany.setString(1, c.getCompanyName());
            psCompany.setString(2, c.getAddress());
            psCompany.setString(3, c.getContactInfo());
            psCompany.setInt(4, p.getCompanyID());  // Using the CompanyID from the product (product object)
            psCompany.executeUpdate();

            // Update product info
            PreparedStatement psProduct = conn.prepareStatement(queryProduct);
            psProduct.setString(1, p.getProductName());
            psProduct.setString(2, p.getInsuranceType());
            psProduct.setBigDecimal(3, p.getCost());
            psProduct.setString(4, p.getDescription());
            psProduct.setString(5, p.getConditions());
            psProduct.setInt(6, productId);  // Use the productId to identify the product
            psProduct.executeUpdate();

            // Commit transaction if everything is successful
            conn.commit();

        } catch (SQLException ex) {
            if (conn != null) {
                try {
                    conn.rollback();  // Rollback in case of error
                } catch (SQLException rollbackEx) {
                    Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, rollbackEx);
                }
            }
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);  // Reset the auto-commit mode to default
                } catch (SQLException ex) {
                    Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void addCompanyAndProduct(InsuranceCompany c, InsuranceProduct p) {
        String queryCompany = INSERT_COMPANY; // declare query
        String queryProduct = INSERT_PRODUCT;// declare query
        Connection conn = null;

        try {
            conn = connection; // Assuming `connection` is your database connection
            conn.setAutoCommit(false); // Bắt đầu transaction

            // Thêm công ty bảo hiểm
            PreparedStatement psCompany = conn.prepareStatement(queryCompany, Statement.RETURN_GENERATED_KEYS);
            psCompany.setString(1, c.getCompanyName());
            psCompany.setString(2, c.getAddress());
            psCompany.setString(3, c.getContactInfo());
            psCompany.executeUpdate();

            // Lấy CompanyID vừa được tạo
            ResultSet rs = psCompany.getGeneratedKeys();
            int companyId = 0;
            if (rs.next()) {
                companyId = rs.getInt(1); // Lấy IDENTITY từ kết quả
            }

            // Thêm sản phẩm bảo hiểm
            PreparedStatement psProduct = conn.prepareStatement(queryProduct);
            psProduct.setInt(1, companyId);
            psProduct.setString(2, p.getProductName());
            psProduct.setString(3, p.getInsuranceType());
            psProduct.setBigDecimal(4, p.getCost());
            psProduct.setString(5, p.getDescription());
            psProduct.setString(6, p.getConditions());
            psProduct.executeUpdate();

            // Commit transaction nếu tất cả đều thành công
            conn.commit();

        } catch (SQLException ex) {
            if (conn != null) {
                try {
                    conn.rollback(); // Rollback nếu có lỗi
                } catch (SQLException rollbackEx) {
                    Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, rollbackEx);
                }
            }
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true); // Trả lại trạng thái ban đầu
                } catch (SQLException ex) {
                    Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public List<InsuranceProduct> find(String query, Object... args) {
        List<InsuranceProduct> list = new ArrayList<>();
        try ( PreparedStatement pre = connection.prepareStatement(query)) {
            // Gán tham số truy vấn
            for (int i = 0; i < args.length; i++) {
                pre.setObject(i + 1, args[i]);
            }

            // Thực thi truy vấn
            try ( ResultSet rs = pre.executeQuery()) {
                while (rs.next()) {
                    // Lấy thông tin công ty bảo hiểm
                    String companyName = rs.getString("CompanyName");

                    // Tạo đối tượng InsuranceCompany (với chỉ CompanyName)
                    InsuranceCompany insuranceCompany = new InsuranceCompany(companyName);

                    // Lấy thông tin sản phẩm bảo hiểm
                    int productID = rs.getInt("ProductID");
                    String productName = rs.getString("ProductName");
                    String insuranceType = rs.getString("InsuranceType");
                    String description = rs.getString("Description");
                    BigDecimal cost = rs.getBigDecimal("Cost");
                    String conditions = rs.getString("Conditions");

                    // Tạo đối tượng InsuranceProduct
                    InsuranceProduct product = new InsuranceProduct(
                            productID,
                            insuranceCompany,
                            productName,
                            insuranceType,
                            description,
                            cost,
                            conditions
                    );

                    // Thêm sản phẩm vào danh sách
                    list.add(product);
                }
            }
        } catch (Exception e) {
            System.err.println("Error while executing query: " + query);
            e.printStackTrace();
        }

        return list;
    }

    public List<InsuranceProduct> findByProductId(String query, Object... args) {
        List<InsuranceProduct> list = new ArrayList<>();
        try ( PreparedStatement pre = connection.prepareStatement(query)) {
            // Gán tham số truy vấn
            for (int i = 0; i < args.length; i++) {
                pre.setObject(i + 1, args[i]);
            }

            // Thực thi truy vấn
            try ( ResultSet rs = pre.executeQuery()) {
                while (rs.next()) {
                    // Lấy thông tin công ty bảo hiểm
                    int companyID = rs.getInt("CompanyID"); // Thêm dòng này để lấy CompanyID
                    String companyName = rs.getString("CompanyName");
                    String address = rs.getString("Address");
                    String contactInfo = rs.getString("ContactInfo");

                    // Tạo đối tượng InsuranceCompany
                    InsuranceCompany insuranceCompany = new InsuranceCompany(companyID, companyName, address, contactInfo);

                    // Lấy thông tin sản phẩm bảo hiểm
                    int productID = rs.getInt("ProductID");
                    String productName = rs.getString("ProductName");
                    String insuranceType = rs.getString("InsuranceType");
                    String description = rs.getString("Description");
                    BigDecimal cost = rs.getBigDecimal("Cost");
                    String conditions = rs.getString("Conditions");

                    // Tạo đối tượng InsuranceProduct
                    InsuranceProduct product = new InsuranceProduct(
                            productID,
                            insuranceCompany,
                            productName,
                            insuranceType,
                            description,
                            cost,
                            conditions
                    );

                    // Thêm sản phẩm vào danh sách
                    list.add(product);
                }
            }
        } catch (Exception e) {
            System.err.println("Error while executing query: " + query);
            e.printStackTrace();
        }

        return list;
    }
    
 

    @Override
    public Object findById(int id) {
        String query = GET_PRODUCT_BY_ID;
        return findByProductId(query, id);
    }

    //get all product
    @Override
    public List<InsuranceProduct> findAll() {
        String query = GET_ALL_PRODUCT;
        return find(query);
    }

    // find product by product name
    @Override
    public List<InsuranceProduct> getProductByName(String searchName) {
        String query = GET_PRODUCT_BY_NAME;
//        return find(query,"Bảo hiểm Y tế ABC");
        return find(query, "%" + searchName + "%");
    }
    @Override
    public List<InsuranceProduct> getTopProducts(int limit) {
    List<InsuranceProduct> products = new ArrayList<>();

    try (PreparedStatement ps = connection.prepareStatement(SELECT_TOP_PRODUCT)) {
        ps.setInt(1, limit);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            InsuranceProduct product = new InsuranceProduct();
            product.setProductID(rs.getInt("ProductID"));
            product.setProductName(rs.getString("ProductName"));
            product.setInsuranceType(rs.getString("InsuranceType"));
            product.setCost(rs.getBigDecimal("Cost"));
            product.setDescription(rs.getString("Description"));
            product.setConditions(rs.getString("Conditions"));
            product.setAvatar(rs.getString("Avatar"));
            products.add(product);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return products;
}
    
    @Override
    public List<InsuranceProduct> getProductByNameWithAvatar(String searchName) {
        String query = GET_PRODUCT_BY_NAME_WITH_AVATAR;
        return find(query, "%" + searchName + "%");
    }
    
    @Override
    public List<InsuranceProduct> getAllProducts() {
        String query = GET_ALL_PRODUCTS_WITH_AVATAR;
        return find(query);
    }
    
    @Override
    public List<InsuranceProduct> getDistinctInsuranceTypes() {
        String query = GET_DISTINCT_INSURANCE_TYPES;
        return find(query);
    }
    
    @Override
     public List<InsuranceProduct> getProductsByCategory(String category) {
        String query = GET_PRODUCTS_BY_CATEGORY;
        return find(query, category);
    }
     
    @Override
    public List<InsuranceProduct> getProductByNameAndCategory(String searchName, String category) {
        String query = GET_PRODUCT_BY_NAME_AND_CATEGORY;
        return find(query, "%" + searchName + "%", category);
    }
    
    @Override
    public List<InsuranceProduct> getAllProductsWithSort(String sortBy) {
    // Khởi tạo query cơ bản
        StringBuilder query = new StringBuilder(GET_ALL_PRODUCTS_WITH_AVATAR);

        // Thêm điều kiện sắp xếp dựa vào giá trị của sortBy
        if ("costAsc".equals(sortBy)) {
            query.append(SORT_BY_COST_ASC);
        } else if ("costDesc".equals(sortBy)) {
            query.append(SORT_BY_COST_DESC);
        } else if ("dateNewest".equals(sortBy)) {
            query.append(SORT_BY_DATE_NEWEST);
        } else if ("dateOldest".equals(sortBy)) {
            query.append(SORT_BY_DATE_OLDEST);
        }

        // Thực thi query và trả về danh sách sản phẩm
        return find(query.toString());
    }
    
    @Override
    public List<InsuranceProduct> getProductByNameAndCategoryWithSort(String searchName, String category, String sortBy) {
        StringBuilder query = new StringBuilder(GET_PRODUCT_BY_NAME_AND_CATEGORY);

        if ("costAsc".equals(sortBy)) {
            query.append(SORT_BY_COST_ASC);
        } else if ("costDesc".equals(sortBy)) {
            query.append(SORT_BY_COST_DESC);
        } else if ("dateNewest".equals(sortBy)) {
            query.append(SORT_BY_DATE_NEWEST);
        } else if ("dateOldest".equals(sortBy)) {
            query.append(SORT_BY_DATE_OLDEST);
        }

        return find(query.toString(), "%" + searchName + "%", category);
    }

    @Override
    public List<InsuranceProduct> getProductByNameWithAvatarAndSort(String searchName, String sortBy) {
        StringBuilder query = new StringBuilder(GET_PRODUCT_BY_NAME_WITH_AVATAR);

        if ("costAsc".equals(sortBy)) {
            query.append(SORT_BY_COST_ASC);
        } else if ("costDesc".equals(sortBy)) {
            query.append(SORT_BY_COST_DESC);
        } else if ("dateNewest".equals(sortBy)) {
            query.append(SORT_BY_DATE_NEWEST);
        } else if ("dateOldest".equals(sortBy)) {
            query.append(SORT_BY_DATE_OLDEST);
        }

        return find(query.toString(), "%" + searchName + "%");
    }

    @Override
    public List<InsuranceProduct> getProductsByCategoryWithSort(String category, String sortBy) {
        StringBuilder query = new StringBuilder(GET_PRODUCTS_BY_CATEGORY);

        if ("costAsc".equals(sortBy)) {
            query.append(SORT_BY_COST_ASC);
        } else if ("costDesc".equals(sortBy)) {
            query.append(SORT_BY_COST_DESC);
        } else if ("dateNewest".equals(sortBy)) {
            query.append(SORT_BY_DATE_NEWEST);
        } else if ("dateOldest".equals(sortBy)) {
            query.append(SORT_BY_DATE_OLDEST);
        }

        return find(query.toString(), category);
    }
//    //Test delete product and company
//    public static void main(String[] args) {
//        // Khởi tạo ProductService (DAO)
//        ProductService dao = new ProductService();
//
//        // Gọi phương thức delete để xóa sản phẩm với ProductID = 1
//        dao.delete(2);
//
//        // Kiểm tra lại sản phẩm sau khi xóa
//        List<InsuranceProduct> pList = (List<InsuranceProduct>) dao.findById(1);
//            for (InsuranceProduct o : pList) {
//            System.out.println(o);
//        }
//    }

//    //Test search product by name
//    public static void main(String[] args) {
//        ProductService dao = new ProductService();
//        List<InsuranceProduct> pList = (List<InsuranceProduct>) dao.findById(1);
//        for (InsuranceProduct o : pList) {
//            System.out.println(o);
//        }
//
//    }
    // test insert data
//    public static void main(String[] args) {
//        // Khởi tạo dịch vụ
//        ProductService dao = new ProductService();
//
//        // Tạo dữ liệu mẫu cho công ty bảo hiểm
//        InsuranceCompany company = new InsuranceCompany();
//        company.setCompanyName("ABC Insurance");
//        company.setAddress("123 Main Street");
//        company.setContactInfo("contact@abcinsurance.com");
//
//        // Tạo dữ liệu mẫu cho sản phẩm bảo hiểm
//        InsuranceProduct product = new InsuranceProduct();
//        product.setProductName("Bảo hiểm Y tế ABC");
//        product.setInsuranceType("Y tế");
//        product.setCost(new BigDecimal("5000000")); // Chi phí 5 triệu
//        product.setDescription("Bảo hiểm y tế toàn diện cho cá nhân");
//        product.setConditions("Không áp dụng cho bệnh đã có từ trước");
//
//        // Thêm dữ liệu vào database
//        dao.addCompanyAndProduct(company, product);
//
//        // Truy vấn để kiểm tra
//        System.out.println("Dữ liệu sản phẩm sau khi chèn:");
//        List<InsuranceProduct> pList = dao.getProductByName("Bảo hiểm Y tế ABC");
//        for (InsuranceProduct o : pList) {
//            System.out.println(o);
//        }
//    }
// // data test edit
//    public static void main(String[] args) {
//        // Khởi tạo dịch vụ
//        ProductService dao = new ProductService();
//
//        // Tạo dữ liệu mẫu cho công ty bảo hiểm
//        InsuranceCompany company = new InsuranceCompany();
//        company.setCompanyName("ABC Insurance 666");
//        company.setAddress("123 Main Street");
//        company.setContactInfo("contact@abcinsurance.com");
//
//        // Tạo dữ liệu mẫu cho sản phẩm bảo hiểm
//        InsuranceProduct product = new InsuranceProduct();
//        product.setCompanyID(1);
//        product.setProductName("Bảo hiểm Y tế ABC");
//        product.setInsuranceType("Y tế");
//        product.setCost(new BigDecimal("5000000")); // Chi phí 5 triệu
//        product.setDescription("Bảo hiểm y tế toàn diện cho cá nhân");
//        product.setConditions("Không áp dụng cho bệnh đã có từ trước");
//        
//
//        // Thêm dữ liệu vào database
//        dao.editCompanyAndProduct(1,company, product);
//
//        // Truy vấn để kiểm tra
//        System.out.println("Dữ liệu sản phẩm sau khi chèn:");
//        List<InsuranceProduct> pList = dao.getProductByName("Bảo hiểm Y tế ABC");
//        for (InsuranceProduct o : pList) {
//            System.out.println(o);
//        }
//    }
}
