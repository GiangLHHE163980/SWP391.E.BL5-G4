package model;

import java.util.Date;

public class InsuranceCard {

    private int cardID;
    private String cardNumber;
    private User user;
    private InsuranceProduct insuranceProduct;
    private Date startDate;
    private Date endDate;
    private String status;
    private boolean isHandicapped; // New field
    private Date createdAt;
    private Date updatedAt;
    private Boolean isHandicapped;
    

    public InsuranceCard() {
    }

    public InsuranceCard(int cardID, String cardNumber, User user, InsuranceProduct insuranceProduct, Date startDate, Date endDate, String status, boolean isHandicapped, Date createdAt, Date updatedAt) {
        this.cardID = cardID;
        this.cardNumber = cardNumber;
        this.user = user;
        this.insuranceProduct = insuranceProduct;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.isHandicapped = isHandicapped; // Initialize new field
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public InsuranceCard(String cardNumber, User user, InsuranceProduct insuranceProduct, Date startDate, Date endDate, String status, boolean isHandicapped, Date createdAt) {
        this.cardNumber = cardNumber;
        this.user = user;
        this.insuranceProduct = insuranceProduct;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.isHandicapped = isHandicapped; // Initialize new field
        this.createdAt = createdAt;
    }

    public InsuranceCard(String cardNumber, String status, boolean isHandicapped) {
        this.cardNumber = cardNumber;
        this.status = status;
        this.isHandicapped = isHandicapped; // Initialize new field
    }
    
    public InsuranceCard(String cardNumber, String status) {
         this.cardNumber = cardNumber;
         this.status = status;
    }

    public InsuranceCard(int CardId, User user, InsuranceProduct product, String cardStatus, boolean isHandicapped) {
       this.cardID = CardId;
       this.user = user;
       this.insuranceProduct = product;
       this.status = cardStatus;
       this.isHandicapped = isHandicapped;
    }

    public Boolean getIsHandicapped() {
        return isHandicapped;
    }

    public void setIsHandicapped(Boolean isHandicapped) {
        this.isHandicapped = isHandicapped;
    }
    
    
    public int getCardID() {
        return cardID;
    }

    public void setCardID(int cardID) {
        this.cardID = cardID;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public InsuranceProduct getInsuranceProduct() {
        return insuranceProduct;
    }

    public void setInsuranceProduct(InsuranceProduct insuranceProduct) {
        this.insuranceProduct = insuranceProduct;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isHandicapped() {
        return isHandicapped;
    }

    public void setHandicapped(boolean isHandicapped) {
        this.isHandicapped = isHandicapped;
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
        return "InsuranceCard{" +
            "cardID='" + cardID + '\'' +
            ", status='" + status + '\'' +
                ", insuranceProduct='" + insuranceProduct + '\'' +
                ", user='" + user + '\'' +
                ", isHandicapped='" + isHandicapped + '\'' +
            '}';
    }
}
