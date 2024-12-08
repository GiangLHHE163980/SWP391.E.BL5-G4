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
    private Date createdAt;
    private Date updatedAt;
    

    public InsuranceCard() {
    }

    public InsuranceCard(int cardID, String cardNumber, User user, InsuranceProduct insuranceProduct, Date startDate, Date endDate, String status, Date createdAt, Date updatedAt) {
        this.cardID = cardID;
        this.cardNumber = cardNumber;
        this.user = user;
        this.insuranceProduct = insuranceProduct;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public InsuranceCard(String cardNumber, User user, InsuranceProduct insuranceProduct, Date startDate, Date endDate, String status, Date createdAt) {
        this.cardNumber = cardNumber;
        this.user = user;
        this.insuranceProduct = insuranceProduct;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.createdAt = createdAt;
    }

    public InsuranceCard(String cardNumber, String status) {
         this.cardNumber = cardNumber;
         this.status = status;
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
            "cardNumber='" + cardNumber + '\'' +
            ", status='" + status + '\'' +
            '}';
    }
}
