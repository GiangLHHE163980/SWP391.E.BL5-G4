/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.homepageForStaff;

import connection.DBContext;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.InsuranceCompany;
import model.InsuranceProduct;

/**
 *
 * @author Lenovo
 */
public class HomepageForStaffService implements IHomepageForStaffService {

    private final Connection connection = DBContext.getConnection();

    private static final String GET_NUMBER_OF_CUSTOMER = "SELECT COUNT(*) AS CustomerCount\n"
            + "FROM Users u\n"
            + "JOIN UserRoles ur ON u.UserID = ur.UserID\n"
            + "JOIN Roles r ON ur.RoleID = r.RoleID\n"
            + "WHERE r.RoleName = 'Customer'";
    private static final String GET_NUMBER_OF_INSURANCECARD = "SELECT COUNT(*) FROM InsuranceProducts";
     private static final String GET_NUMBER_OF_REQUEST = "SELECT COUNT(*) FROM Claims";

    @Override
    public int getCountOfCustomer() {
        String query = GET_NUMBER_OF_CUSTOMER;
        int count = 0;

        // Sử dụng try-with-resources để tự động đóng tài nguyên
        try ( PreparedStatement pre = connection.prepareStatement(query)) {
            try ( ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt(1);  // Cột đầu tiên chứa kết quả COUNT(*)
                }
            } catch (SQLException ex) {
                Logger.getLogger(HomepageForStaffService.class.getName()).log(Level.SEVERE, "Error executing query", ex);
                // Nếu có lỗi, ném lại để bên gọi có thể xử lý tiếp
                throw new RuntimeException("Error executing query to count customers", ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HomepageForStaffService.class.getName()).log(Level.SEVERE, "Error preparing statement", ex);
            // Ném lại ngoại lệ để bên gọi có thể xử lý tiếp
            throw new RuntimeException("Error preparing SQL statement", ex);
        }

        return count;
    }
    
        @Override
    public int getCountOfInsuranceCard() {
        String query = GET_NUMBER_OF_INSURANCECARD;
        int count = 0;

        // Sử dụng try-with-resources để tự động đóng tài nguyên
        try ( PreparedStatement pre = connection.prepareStatement(query)) {
            try ( ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt(1);  // Cột đầu tiên chứa kết quả COUNT(*)
                }
            } catch (SQLException ex) {
                Logger.getLogger(HomepageForStaffService.class.getName()).log(Level.SEVERE, "Error executing query", ex);
                // Nếu có lỗi, ném lại để bên gọi có thể xử lý tiếp
                throw new RuntimeException("Error executing query to count customers", ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HomepageForStaffService.class.getName()).log(Level.SEVERE, "Error preparing statement", ex);
            // Ném lại ngoại lệ để bên gọi có thể xử lý tiếp
            throw new RuntimeException("Error preparing SQL statement", ex);
        }

        return count;
    }
    
            @Override
    public int getCountOfRequest() {
        String query = GET_NUMBER_OF_REQUEST;
        int count = 0;

        // Sử dụng try-with-resources để tự động đóng tài nguyên
        try ( PreparedStatement pre = connection.prepareStatement(query)) {
            try ( ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt(1);  // Cột đầu tiên chứa kết quả COUNT(*)
                }
            } catch (SQLException ex) {
                Logger.getLogger(HomepageForStaffService.class.getName()).log(Level.SEVERE, "Error executing query", ex);
                // Nếu có lỗi, ném lại để bên gọi có thể xử lý tiếp
                throw new RuntimeException("Error executing query to count customers", ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HomepageForStaffService.class.getName()).log(Level.SEVERE, "Error preparing statement", ex);
            // Ném lại ngoại lệ để bên gọi có thể xử lý tiếp
            throw new RuntimeException("Error preparing SQL statement", ex);
        }

        return count;
    }

    //Test data
    public static void main(String[] args) {
        // Khởi tạo đối tượng HomepageForStaffService (giả sử đã triển khai IHomepageForStaffService)
        IHomepageForStaffService dao = new HomepageForStaffService();

        try {
            // Gọi phương thức getCountOfCustomer để lấy số lượng khách hàng
            int customerCount = dao.getCountOfRequest();
            // In ra số lượng khách hàng
            System.out.println("Total number of customers: " + customerCount);
        } catch (Exception e) {
            // Nếu có lỗi xảy ra trong quá trình gọi hàm, in lỗi ra console
            System.err.println("An error occurred while retrieving customer count: " + e.getMessage());
        }
    }

}
