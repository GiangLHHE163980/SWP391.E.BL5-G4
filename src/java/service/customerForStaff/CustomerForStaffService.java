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
            + "WHERE r.RoleName = 'Customer'\n"
            + "  AND (ic.Status IN ('Active', 'Expired', 'Revoked'));";

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

    private static final String UPDATE_INSURANCE_CARD_STATUS_BY_USER_ID = "UPDATE InsuranceCards "
            + "SET Status = ? "
            + "FROM InsuranceCards ic "
            + "JOIN Users u ON ic.UserID = u.UserID "
            + "JOIN UserRoles ur ON u.UserID = ur.UserID "
            + "JOIN Roles r ON ur.RoleID = r.RoleID "
            + "WHERE r.RoleName = 'Customer' "
            + "AND u.UserID = ? ";

    private static final String Get_ALL_REQUEST_INSURANCE_CARD = "SELECT \n"
            + "    ic.CardID,\n"
            + "    u.FullName, \n"
            + "    u.Birthday, \n"
            + "    u.Sex, \n"
            + "    ip.ProductName, \n"
            + "    ip.Cost, \n"
            + "    ic.Status,\n"
            + "    ic.isHandicapped\n"
            + "FROM \n"
            + "    InsuranceCards ic\n"
            + "JOIN \n"
            + "    Users u ON ic.UserID = u.UserID\n"
            + "JOIN \n"
            + "    UserRoles ur ON u.UserID = ur.UserID\n"
            + "JOIN \n"
            + "    Roles r ON ur.RoleID = r.RoleID\n"
            + "JOIN \n"
            + "    InsuranceProducts ip ON ic.ProductID = ip.ProductID\n"
            + "WHERE \n"
            + "    ic.Status = 'Pending'\n"
            + "    AND r.RoleName = 'Customer';";

    private static final String Get_Request_INSURANCE_CARD_BY_CARD_ID = "SELECT \n"
            + "    ic.CardID,\n"
            + "    u.FullName, \n"
            + "    u.Birthday,\n"
            + "    u.SocialSecurityNumber,\n"
            + "    u.PhoneNumber,\n"
            + "    u.Email,\n"
            + "    u.Sex, \n"
            + "    ip.ProductName, \n"
            + "    ip.Cost, \n"
            + "    ic.Status,\n"
            + "    ic.isHandicapped,\n"
            + "    icc.CompanyID,\n"
            + "    icc.CompanyName,\n"
            + "    icc.Address AS CompanyAddress,\n"
            + "    icc.ContactInfo AS CompanyContactInfo\n"
            + "FROM \n"
            + "    InsuranceCards ic\n"
            + "JOIN \n"
            + "    Users u ON ic.UserID = u.UserID\n"
            + "JOIN \n"
            + "    InsuranceProducts ip ON ic.ProductID = ip.ProductID\n"
            + "JOIN \n"
            + "    InsuranceCompanies icc ON ip.CompanyID = icc.CompanyID\n"
            + "WHERE \n"
            + "    ic.Status = 'Pending'\n"
            + "    AND ic.CardID = ?; ";

    private static final String UPDATE_INSURANCE_CARD_STATUS_BY_CARD_ID = "UPDATE ic\n"
            + "SET ic.Status = ?, -- Thay trạng thái mong muốn\n"
            + "    ic.UpdatedAt = GETDATE() -- Cập nhật thời gian\n"
            + "FROM InsuranceCards ic\n"
            + "JOIN Users u ON ic.UserID = u.UserID\n"
            + "JOIN UserRoles ur ON u.UserID = ur.UserID\n"
            + "JOIN Roles r ON ur.RoleID = r.RoleID\n"
            + "WHERE r.RoleName = 'Customer' \n"
            + "  AND u.IsActive = 1         \n"
            + "  AND ic.CardID = ?;          ";

    @Override
    public void updateInsuranceCardStatusByCardId(String newStatus, int cardID) {
        try ( PreparedStatement ps = connection.prepareStatement(UPDATE_INSURANCE_CARD_STATUS_BY_CARD_ID)) {
            ps.setString(1, newStatus); // Cập nhật trạng thái mới (ví dụ 'Pending', 'Approved', ...)
            ps.setInt(2, cardID); // Truyền vào userID
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Request status updated successfully.");
            } else {
                System.out.println("No records found for update.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public InsuranceCard findCardRequestbyId(int cardId) {  // Thêm tham số cardId để truy vấn theo thẻ bảo hiểm
        InsuranceCard card = null;  // Khởi tạo biến card

        String query = Get_Request_INSURANCE_CARD_BY_CARD_ID;

        try ( PreparedStatement pre = connection.prepareStatement(query)) {
            pre.setInt(1, cardId);  // Đặt giá trị CardID vào câu lệnh chuẩn bị

            // Thực thi truy vấn
            try ( ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
                    // Lấy thông tin từ kết quả truy vấn

                    // Lấy thông tin người dùng
                    String fullName = rs.getString("FullName");
                    Date birthday = rs.getDate("Birthday");
                    String sex = rs.getString("Sex");
                    int socialSecurityNumber = rs.getInt("socialSecurityNumber");
                    String phoneNumber = rs.getString("phoneNumber");
                    String email = rs.getString("email");
                    User user = new User(fullName, birthday, sex, socialSecurityNumber, phoneNumber, email);

                    // lấy thông tin company
                    // Lấy thông tin công ty bảo hiểm
                    int companyID = rs.getInt("CompanyID"); // Thêm dòng này để lấy CompanyID
                    String companyName = rs.getString("CompanyName");
                    String address = rs.getString("CompanyAddress");
                    String contactInfo = rs.getString("CompanyContactInfo");

                    // Tạo đối tượng InsuranceCompany
                    InsuranceCompany insuranceCompany = new InsuranceCompany(companyID, companyName, address, contactInfo);

                    // Lấy thông tin sản phẩm bảo hiểm
                    String productName = rs.getString("ProductName");
                    BigDecimal cost = rs.getBigDecimal("Cost");
                    InsuranceProduct product = new InsuranceProduct(productName, cost, insuranceCompany);

                    // Lấy thông tin thẻ bảo hiểm
                    String cardStatus = rs.getString("Status");
                    boolean isHandicapped = rs.getBoolean("isHandicapped");
                    card = new InsuranceCard(cardId, user, product, cardStatus, isHandicapped);  // Tạo đối tượng InsuranceCard
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL Error while executing query: " + query);
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Unexpected Error: " + e.getMessage());
            e.printStackTrace();
        }

        return card;  // Trả về 1 đối tượng InsuranceCard (có thể null nếu không tìm thấy)
    }

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
        try ( PreparedStatement ps = connection.prepareStatement(UPDATE_INSURANCE_CARD_STATUS_BY_USER_ID)) {
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
        System.out.println(dao.findCardRequestbyId(6));
        // Kiểm tra lại sản phẩm sau khi xóa
//        List<InsuranceCard> pList = (List<InsuranceCard>) dao.findAllCardRequest();
//        for (InsuranceCard o : pList) {
//            System.out.println(o);
//        }
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
