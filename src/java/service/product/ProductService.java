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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.InsuranceCompany;
import model.InsuranceProduct;

/**
 *
 * @author Lenovo
 */
public class ProductService implements IProductService{
     private final Connection connection = DBContext.getConnection();

    private static final String GET_ALL_PRODUCT = "SELECT \n" +
"    ip.ProductID,\n" +
"    ip.ProductName,\n" +
"    ip.InsuranceType,\n" +
"    ip.Cost,\n" +
"    ip.Description,\n" +
"    ip.Conditions,\n" +
"    ic.CompanyName\n" +
"FROM InsuranceProducts ip\n" +
"JOIN InsuranceCompanies ic ON ip.CompanyID = ic.CompanyID;";

  private static final String GET_PRODUCT_BY_NAME = "SELECT \n" +
    "    ip.ProductID,\n" +
    "    ip.ProductName,\n" +
    "    ip.InsuranceType,\n" +
    "    ip.Cost,\n" +
    "    ip.Description,\n" +
    "    ip.Conditions,\n" +
    "    ic.CompanyName\n" +
    "FROM InsuranceProducts ip\n" +
    "JOIN InsuranceCompanies ic ON ip.CompanyID = ic.CompanyID\n" +
    "WHERE ip.ProductName LIKE ?"; 

    
    
public List<InsuranceProduct> find(String query, Object... args) {
    List<InsuranceProduct> list = new ArrayList<>();
    try (PreparedStatement pre = connection.prepareStatement(query)) {
        // Gán tham số truy vấn
        for (int i = 0; i < args.length; i++) {
            pre.setObject(i + 1, args[i]);
        }

        // Thực thi truy vấn
        try (ResultSet rs = pre.executeQuery()) {
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


     @Override
      public List<InsuranceProduct> findAll() {
        String query = GET_ALL_PRODUCT;
        return find(query);
    }
      
     @Override
       public List<InsuranceProduct> getProductByName(String searchName) {
        String query = GET_PRODUCT_BY_NAME;
//        return find(query,"Bảo hiểm Y tế ABC");
        return find(query,"%"+searchName+"%");
    }
      
          public static void main(String[] args) {
        ProductService dao = new ProductService();
        List<InsuranceProduct> pList = dao.getProductByName("Bảo hiểm Y tế ABC");
        for (InsuranceProduct o : pList) {
            System.out.println(o);
        }
       
    }


}
