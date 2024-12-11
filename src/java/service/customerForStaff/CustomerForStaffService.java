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
import java.util.Date;
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
            + "    ic.Status\n"
            + "FROM Users u\n"
            + "JOIN UserRoles ur ON u.UserID = ur.UserID\n"
            + "JOIN Roles r ON ur.RoleID = r.RoleID\n"
            + "LEFT JOIN InsuranceCards ic ON u.UserID = ic.UserID\n"
            + "WHERE r.RoleName = 'Customer' AND u.UserID = ?;";

    private static final String GET_CUSTOMER_REQUEST_BY_ID = "SELECT \n"
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
            + "WHERE r.RoleName = 'Customer'\n"
            + "  AND u.UserID = ?;";

    private static final String UPDATE_INSURANCE_REQUEST_STATUS_BY_ID = "UPDATE Claims "
            + "SET Status = ? "
            + "FROM Claims c "
            + "JOIN InsuranceCards ic ON c.CardID = ic.CardID "
            + "JOIN Users u ON ic.UserID = u.UserID "
            + "JOIN UserRoles ur ON u.UserID = ur.UserID "
            + "JOIN Roles r ON ur.RoleID = r.RoleID "
            + "WHERE r.RoleName = 'Customer' "
            + "AND u.UserID = ? ";

    private static final String UPDATE_INSURANCE_CARD_STATUS_BY_ID = "UPDATE InsuranceCards "
            + "SET Status = ? "
            + "FROM InsuranceCards ic "
            + "JOIN Users u ON ic.UserID = u.UserID "
            + "JOIN UserRoles ur ON u.UserID = ur.UserID "
            + "JOIN Roles r ON ur.RoleID = r.RoleID "
            + "WHERE r.RoleName = 'Customer' "
            + "AND u.UserID = ? ";

    private static final String Get_ALL_REQUEST_INSURANCE_CARD = "SELECT \n"
            + "	ic.CardID,\n"
            + "    u.FullName, \n"
            + "    u.Birthday, \n"
            + "    u.Sex, \n"
            + "    ip.ProductName, \n"
            + "    ip.Cost, \n"
            + "    ic.Status,\n"
            + "	ic.isHandicapped\n"
            + "FROM \n"
            + "    InsuranceCards ic\n"
            + "JOIN \n"
            + "    Users u ON ic.UserID = u.UserID\n"
            + "JOIN \n"
            + "    InsuranceProducts ip ON ic.ProductID = ip.ProductID\n"
            + "WHERE \n"
            + "    ic.Status = 'Pending';\n"
            + "";

    @Override
    public void updateInsuranceRequestStatusByUserId(String newStatus, int userID) {
        try ( PreparedStatement ps = connection.prepareStatement(UPDATE_INSURANCE_REQUEST_STATUS_BY_ID)) {
            ps.setString(1, newStatus); // Cập nhật trạng thái mới (ví dụ 'Pending', 'Approved', ...)
            ps.setInt(2, userID); // Truyền vào userID
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Claims status updated successfully.");
            } else {
                System.out.println("No records found for update.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<InsuranceCard> findAllCardRequest() {
        List<InsuranceCard> cardList = new ArrayList<>();

        try ( PreparedStatement pre = connection.prepareStatement(Get_ALL_REQUEST_INSURANCE_CARD)) {
            // Thực thi truy vấn
            try ( ResultSet rs = pre.executeQuery()) {
                while (rs.next()) {
                    // Lấy thông tin từ kết quả truy vấn
                    // lấy thông tin user
                    String fullName = rs.getString("FullName");
                    Date birtday = rs.getDate("Birthday");
                    String sex = rs.getString("Sex");

                    User user = new User(fullName, birtday, sex);

                    // Lay thông tin của product
                    String productName = rs.getString("ProductName");
                    BigDecimal cost = rs.getBigDecimal("Cost");

                    InsuranceProduct product = new InsuranceProduct(productName, cost);

                    //lay thong tin card
                    int cardId = rs.getInt("CardID");
                    String cardStatus = rs.getString("Status");
                    boolean isHandicapped = rs.getBoolean("isHandicapped");
                    InsuranceCard card = new InsuranceCard(cardId, user, product, cardStatus, isHandicapped);
                    // Thêm khách hàng vào danh sách
                    cardList.add(card);
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL Error while executing query: " + GET_ALL_CUSTOMERS);
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Unexpected Error: " + e.getMessage());
            e.printStackTrace();
        }

        return cardList; // Trả về danh sách khách hàng
    }

    @Override
    public void updateInsuranceCardStatusByUserId(String newStatus, int userID) {
        try ( PreparedStatement ps = connection.prepareStatement(UPDATE_INSURANCE_CARD_STATUS_BY_ID)) {
            ps.setString(1, newStatus); // Cập nhật trạng thái mới (ví dụ 'Pending', 'Approved', ...)
            ps.setInt(2, userID); // Truyền vào userID
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Claims status updated successfully.");
            } else {
                System.out.println("No records found for update.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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

    public List<User> findByCustomerById(String query, Object... args) {
        List<User> list = new ArrayList<>();
        try ( PreparedStatement pre = connection.prepareStatement(query)) {
            // Gán tham số truy vấn
            for (int i = 0; i < args.length; i++) {
                pre.setObject(i + 1, args[i]);
            }

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
                    String cardStatus = rs.getString("CardStatus");

                    if (cardNumber != null && cardStatus != null) {
                        InsuranceCard card = new InsuranceCard(cardNumber, cardStatus);
                        customer.setInsuranceCard(card);
                    }

                    // Kiểm tra và thêm Claim
                    int claimId = rs.getInt("ClaimID");
                    String claimStatus = rs.getString("ClaimStatus");
                    String reason = rs.getString("Reason");

                    if (claimId != 0) {
                        Claim claim = new Claim(claimId, claimStatus, reason);
                        customer.addClaim(claim); // Gắn yêu cầu bảo hiểm vào User
                    }

                    // Lấy và thêm thông tin sản phẩm bảo hiểm
                    String productName = rs.getString("ProductName");
                    if (productName != null) {
                        InsuranceProduct product = new InsuranceProduct(productName);
                        customer.setInsuranceProduct(product);  // Gắn thông tin sản phẩm bảo hiểm vào User
                    }

                    // Thêm khách hàng vào danh sách
                    list.add(customer);
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL Error while executing query: " + query);
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Unexpected Error: " + e.getMessage());
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public Object findCustomerInforById(int id) {
        String query = GET_CUSTOMER_REQUEST_BY_ID;
        return findByCustomerById(query, id);
    }

    //    //Test find Customer Infor By use Id
    public static void main(String[] args) {
        // Khởi tạo ProductService (DAO)
        ICustomerForStaffService dao = new CustomerForStaffService();

        // Gọi phương thức delete để xóa sản phẩm với ProductID = 1
//        dao.updateInsuranceCardStatusByUserId("Expired", 2);
        // Kiểm tra lại sản phẩm sau khi xóa
        List<InsuranceCard> pList = (List<InsuranceCard>) dao.findAllCardRequest();
        for (InsuranceCard o : pList) {
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
}
