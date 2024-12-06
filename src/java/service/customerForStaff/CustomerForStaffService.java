/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.customerForStaff;

import connection.DBContext;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Claim;
import model.InsuranceCard;
import model.InsuranceCompany;
import model.InsuranceProduct;
import model.User;

/**
 *
 * @author Lenovo
 */
public class CustomerForStaffService implements ICustomerForStaffService {

    private final Connection connection = DBContext.getConnection();

    private static final String GET_ALL_CUSTOMERS = "SELECT \n"
            + "    u.UserID, \n"
            + "    u.FullName, \n"
            + "    u.Email, \n"
            + "    ic.CardNumber, \n"
            + "    ic.Status\n"
            + "FROM Users u\n"
            + "JOIN UserRoles ur ON u.UserID = ur.UserID\n"
            + "JOIN Roles r ON ur.RoleID = r.RoleID\n"
            + "LEFT JOIN InsuranceCards ic ON u.UserID = ic.UserID\n"
            + "WHERE r.RoleName = 'Customer';";
    private static final String GET_CUSTOMERS_BY_ID = "SELECT \n"
            + "    u.UserID, \n"
            + "    u.FullName, \n"
            + "    u.Email, \n"
            + "    ic.CardNumber, \n"
            + "    ic.Status AS CardStatus, \n"
            + "    c.ClaimID, \n"
            + "    c.Status AS ClaimStatus, \n"
            + "    c.Reason, \n"
            + "    ip.ProductName\n"
            + "FROM Users u\n"
            + "JOIN UserRoles ur ON u.UserID = ur.UserID\n"
            + "JOIN Roles r ON ur.RoleID = r.RoleID\n"
            + "LEFT JOIN InsuranceCards ic ON u.UserID = ic.UserID\n"
            + "LEFT JOIN Claims c ON ic.CardID = c.CardID\n"
            + "LEFT JOIN InsuranceProducts ip ON ic.ProductID = ip.ProductID\n"
            + "WHERE r.RoleName = 'Customer' AND u.UserID = ?;";
    
    



    @Override
    public List<User> findAllCustomers() {
        List<User> customers = new ArrayList<>(); // Danh sách khách hàng

        try ( PreparedStatement pre = connection.prepareStatement(GET_ALL_CUSTOMERS)) {
            // Thực thi truy vấn
            try ( ResultSet rs = pre.executeQuery()) {
                while (rs.next()) {
                    // Lấy thông tin từ kết quả truy vấn
                    int userId = rs.getInt("UserID");
                    String fullName = rs.getString("FullName");
                    String email = rs.getString("Email");

                    // Tạo đối tượng User
                    User customer = new User(userId, fullName, email);

                    // Kiểm tra và thêm InsuranceCard
                    String cardNumber = rs.getString("CardNumber");
                    String status = rs.getString("Status");

                    if (cardNumber != null && status != null) {
                        InsuranceCard card = new InsuranceCard(cardNumber, status);
                        customer.setInsuranceCard(card); // Gắn thẻ bảo hiểm vào User
                    }

                    // Thêm khách hàng vào danh sách
                    customers.add(customer);
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL Error while executing query: " + GET_ALL_CUSTOMERS);
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Unexpected Error: " + e.getMessage());
            e.printStackTrace();
        }

        return customers; // Trả về danh sách khách hàng
    }
    
    //    //Test delete product and company
    public static void main(String[] args) {
        // Khởi tạo ProductService (DAO)
        ICustomerForStaffService dao = new CustomerForStaffService();

        // Gọi phương thức delete để xóa sản phẩm với ProductID = 1
//        dao.findCustomerInforById(2);

        // Kiểm tra lại sản phẩm sau khi xóa
        List<InsuranceProduct> pList = (List<InsuranceProduct>) dao.findCustomerInforById(2);
            for (InsuranceProduct o : pList) {
            System.out.println(o);
        }
    }

//    public static void main(String[] args) {
//        // Khởi tạo ProductService (DAO)
//        ICustomerForStaffService dao = new CustomerForStaffService();
//
//        // Gọi phương thức delete để xóa sản phẩm với ProductID = 1
//        System.out.println(dao.findAllCustomers());
//
//    }

    @Override
    public Object findCustomerInforById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
