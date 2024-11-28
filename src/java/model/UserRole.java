package model;

import java.util.Date;

public class UserRole {

    private int userRoleID;
    private User user;
    private Role role;
    private Date assignedAt;

    public UserRole() {
    }

    public UserRole(int userRoleID, User user, Role role, Date assignedAt) {
        this.userRoleID = userRoleID;
        this.user = user;
        this.role = role;
        this.assignedAt = assignedAt;
    }

    public int getUserRoleID() {
        return userRoleID;
    }

    public void setUserRoleID(int userRoleID) {
        this.userRoleID = userRoleID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Date getAssignedAt() {
        return assignedAt;
    }

    public void setAssignedAt(Date assignedAt) {
        this.assignedAt = assignedAt;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "userRoleID=" + userRoleID +
                ", user=" + user +
                ", role=" + role +
                ", assignedAt=" + assignedAt +
                '}';
    }
}
