package model;

import java.util.Date;

public class InsuranceProduct {

    private int productID;
    private InsuranceCompany insuranceCompany;
    private String productName;
    private String description;
    private double cost;
    private String conditions;
    private Date createdAt;
    private Date updatedAt;

    public InsuranceProduct() {
    }

    public InsuranceProduct(int productID, InsuranceCompany insuranceCompany, String productName, String description, double cost, String conditions, Date createdAt, Date updatedAt) {
        this.productID = productID;
        this.insuranceCompany = insuranceCompany;
        this.productName = productName;
        this.description = description;
        this.cost = cost;
        this.conditions = conditions;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public InsuranceProduct(InsuranceCompany insuranceCompany, String productName, String description, double cost, String conditions, Date createdAt) {
        this.insuranceCompany = insuranceCompany;
        this.productName = productName;
        this.description = description;
        this.cost = cost;
        this.conditions = conditions;
        this.createdAt = createdAt;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
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

    @Override
    public String toString() {
        return "InsuranceProduct{" +
                "productID=" + productID +
                ", insuranceCompany=" + insuranceCompany +
                ", productName='" + productName + '\'' +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                ", conditions='" + conditions + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
