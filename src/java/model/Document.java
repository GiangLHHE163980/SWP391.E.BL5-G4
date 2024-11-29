package model;

import java.util.Date;

public class Document {

    private int documentID;
    private Claim claim;
    private User user;
    private String documentName;
    private String filePath;
    private Date uploadedAt;

    public Document() {
    }

    public Document(int documentID, Claim claim, User user, String documentName, String filePath, Date uploadedAt) {
        this.documentID = documentID;
        this.claim = claim;
        this.user = user;
        this.documentName = documentName;
        this.filePath = filePath;
        this.uploadedAt = uploadedAt;
    }

    public Document(Claim claim, User user, String documentName, String filePath, Date uploadedAt) {
        this.claim = claim;
        this.user = user;
        this.documentName = documentName;
        this.filePath = filePath;
        this.uploadedAt = uploadedAt;
    }

    public int getDocumentID() {
        return documentID;
    }

    public void setDocumentID(int documentID) {
        this.documentID = documentID;
    }

    public Claim getClaim() {
        return claim;
    }

    public void setClaim(Claim claim) {
        this.claim = claim;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Date getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(Date uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    @Override
    public String toString() {
        return "Document{" +
                "documentID=" + documentID +
                ", claim=" + claim +
                ", user=" + user +
                ", documentName='" + documentName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", uploadedAt=" + uploadedAt +
                '}';
    }
}
