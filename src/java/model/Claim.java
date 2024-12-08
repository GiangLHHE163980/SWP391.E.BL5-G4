package model;

import java.util.Date;

public class Claim {

    private int claimID;
    private InsuranceCard insuranceCard;
    private User user;
    private String claimType;
    private String status;
    private String reason;
    private Date submittedAt;
    private Date processedAt;

    public Claim() {
    }

    public Claim(int claimID, InsuranceCard insuranceCard, User user, String claimType, String status, String reason, Date submittedAt, Date processedAt) {
        this.claimID = claimID;
        this.insuranceCard = insuranceCard;
        this.user = user;
        this.claimType = claimType;
        this.status = status;
        this.reason = reason;
        this.submittedAt = submittedAt;
        this.processedAt = processedAt;
    }

    public Claim(InsuranceCard insuranceCard, User user, String claimType, String status, String reason, Date submittedAt) {
        this.insuranceCard = insuranceCard;
        this.user = user;
        this.claimType = claimType;
        this.status = status;
        this.reason = reason;
        this.submittedAt = submittedAt;
    }

 public Claim(int claimId, String status, String reason, String productName) {
        this.claimID = claimId;
        this.status = status;
        this.reason = reason;
//        this.productName = productName;
    }

    public Claim(int claimId, String claimStatus, String reason) {
        this.claimID = claimId;
        this.status = claimStatus;
        this.reason = reason;
    }

    public int getClaimID() {
        return claimID;
    }

    public void setClaimID(int claimID) {
        this.claimID = claimID;
    }

    public InsuranceCard getInsuranceCard() {
        return insuranceCard;
    }

    public void setInsuranceCard(InsuranceCard insuranceCard) {
        this.insuranceCard = insuranceCard;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getClaimType() {
        return claimType;
    }

    public void setClaimType(String claimType) {
        this.claimType = claimType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(Date submittedAt) {
        this.submittedAt = submittedAt;
    }

    public Date getProcessedAt() {
        return processedAt;
    }

    public void setProcessedAt(Date processedAt) {
        this.processedAt = processedAt;
    }

    @Override
    public String toString() {
        return "Claim{" +
                "claimID=" + claimID +
                ", insuranceCard=" + insuranceCard +
                ", user=" + user +
                ", claimType='" + claimType + '\'' +
                ", status='" + status + '\'' +
                ", reason='" + reason + '\'' +
                ", submittedAt=" + submittedAt +
                ", processedAt=" + processedAt +
                '}';
    }
}
