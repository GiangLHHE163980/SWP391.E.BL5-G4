package model;

import java.math.BigDecimal;
import java.sql.Date;

public class InsuranceProduct {

    private int productID;
    private InsuranceCompany insuranceCompany;  // Giả sử InsuranceCompany là một đối tượng đã được định nghĩa ở đâu đó
    private String productName;
    private String insuranceType;  // Loại bảo hiểm (Y tế, Xe, Nhân thọ, ...)
    private String description;
    private BigDecimal cost;  // Đổi thành BigDecimal để lưu trữ chi phí chính xác
    private String conditions;
    private Date createdAt;  // Sử dụng java.sql.Date hoặc java.sql.Timestamp
    private Date updatedAt;  // Sử dụng java.sql.Date hoặc java.sql.Timestamp
    private String avatar;

    public InsuranceProduct() {
    }

    // Constructor với tất cả các tham số
    public InsuranceProduct(int productID, InsuranceCompany insuranceCompany, String productName, String insuranceType, String description, BigDecimal cost, String conditions, Date createdAt, Date updatedAt) {
        this.productID = productID;
        this.insuranceCompany = insuranceCompany;
        this.productName = productName;
        this.insuranceType = insuranceType;
        this.description = description;
        this.cost = cost;
        this.conditions = conditions;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    
        public InsuranceProduct(int productID, InsuranceCompany insuranceCompany, String productName, String insuranceType, String description, BigDecimal cost, String conditions) {
        this.productID = productID;
        this.insuranceCompany = insuranceCompany;
        this.productName = productName;
        this.insuranceType = insuranceType;
        this.description = description;
        this.cost = cost;
        this.conditions = conditions;
  
    }
        
    public InsuranceProduct(InsuranceCompany insuranceCompany, String productName, String insuranceType, String description, BigDecimal cost, String conditions) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public InsuranceProduct(String productID, InsuranceCompany insuranceCompany, String productName, String insuranceType, String description, BigDecimal cost, String conditions) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }





    // Getters và Setters
    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public InsuranceCompany getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(InsuranceCompany insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(String insuranceType) {
        this.insuranceType = insuranceType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "InsuranceProduct{" +
                "productID=" + productID +
                ", insuranceCompany=" + insuranceCompany +
                ", productName='" + productName + '\'' +
                ", insuranceType='" + insuranceType + '\'' +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                ", conditions='" + conditions + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public int getCompanyID() {
      return insuranceCompany.getCompanyID();
    }

 public void setCompanyID(int companyID) {
    if (this.insuranceCompany == null) {
        this.insuranceCompany = new InsuranceCompany(); // Khởi tạo đối tượng nếu null
    }
    this.insuranceCompany.setCompanyID(companyID); // Gán giá trị cho companyID của InsuranceCompany
}

}
