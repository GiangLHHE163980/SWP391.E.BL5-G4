package model;

import java.util.Date;

public class User {

    private int userID;
    private String fullName;
    private String email;
    private String username;
    private String passwordHash;
    private String phoneNumber;
    private String address;
    private String avatar;
    private boolean isActive;
    private boolean gender;
    private Date createdAt;
    private Date updatedAt;
    
    public User() {
    }

    public User(int userID, String fullName, String email, String username, String passwordHash, String phoneNumber, String address, String avatar, boolean isActive, boolean gender, Date createdAt, Date updatedAt) {
        this.userID = userID;
        this.fullName = fullName;
        this.email = email;
        this.username=username;
        this.passwordHash = passwordHash;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.avatar = avatar;
        this.isActive = isActive;
        this.gender = gender;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public User(String fullName, String email, String username, String passwordHash, String phoneNumber, String address, String avatar, boolean isActive, boolean gender, Date createdAt) {
        this.fullName = fullName;
        this.email = email;
        this.username=username;
        this.passwordHash = passwordHash;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.avatar = avatar;
        this.isActive = isActive;
        this.gender = gender;
        this.createdAt = createdAt;
    }
    public User(int userID, String username,boolean isActive){
        this.userID = userID;
        this.username=username;
        this.isActive = isActive;
    }
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", avatar='" + avatar + '\'' +
                ", isActive=" + isActive +
                ", gender='" + gender + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
