package model;

import java.util.Date;

public class AuditLog {

    private int logID;
    private User user;
    private String action;
    private Date createdAt;

    public AuditLog() {
    }

    public AuditLog(int logID, User user, String action, Date createdAt) {
        this.logID = logID;
        this.user = user;
        this.action = action;
        this.createdAt = createdAt;
    }

    public AuditLog(User user, String action, Date createdAt) {
        this.user = user;
        this.action = action;
        this.createdAt = createdAt;
    }

    public int getLogID() {
        return logID;
    }

    public void setLogID(int logID) {
        this.logID = logID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "AuditLog{" +
                "logID=" + logID +
                ", user=" + user +
                ", action='" + action + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
