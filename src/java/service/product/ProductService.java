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
    
// //Test search product by name
//    public static void main(String[] args) {
//        ProductService dao = new ProductService();
//        List<InsuranceProduct> pList = dao.getProductByName("Bảo hiểm Y tế ABC");
//        for (InsuranceProduct o : pList) {
//            System.out.println(o);
//        }
//
//    }
    // test insert data
    public static void main(String[] args) {
        // Khởi tạo dịch vụ
        ProductService dao = new ProductService();

        // Tạo dữ liệu mẫu cho công ty bảo hiểm
        InsuranceCompany company = new InsuranceCompany();
        company.setCompanyName("ABC Insurance");
        company.setAddress("123 Main Street");
        company.setContactInfo("contact@abcinsurance.com");

        // Tạo dữ liệu mẫu cho sản phẩm bảo hiểm
        InsuranceProduct product = new InsuranceProduct();
        product.setProductName("Bảo hiểm Y tế ABC");
        product.setInsuranceType("Y tế");
        product.setCost(new BigDecimal("5000000")); // Chi phí 5 triệu
        product.setDescription("Bảo hiểm y tế toàn diện cho cá nhân");
        product.setConditions("Không áp dụng cho bệnh đã có từ trước");

        // Thêm dữ liệu vào database
        dao.addCompanyAndProduct(company, product);

        // Truy vấn để kiểm tra
        System.out.println("Dữ liệu sản phẩm sau khi chèn:");
        List<InsuranceProduct> pList = dao.getProductByName("Bảo hiểm Y tế ABC");
        for (InsuranceProduct o : pList) {
            System.out.println(o);
        }
    }

}
