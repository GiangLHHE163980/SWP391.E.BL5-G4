package model;

import java.util.Date;

public class User {

    private int userID;
    private String fullName;
    private String email;
    private String passwordHash;
    private String phoneNumber;
    private String address;
    private String avatar;
    private boolean isActive;
    private Date createdAt;
    private Date updatedAt;
    private String sex; // New field
    private int socialSecurityNumber; // New field
    private Date birthday; // New field for birthday

    // Default constructor
    public User() {
    }

    // Constructor with all fields
    public User(int userID, String fullName, String email, String passwordHash, String phoneNumber, String address,
                String avatar, boolean isActive, Date createdAt, Date updatedAt, String sex, int socialSecurityNumber, Date birthday) {
        this.userID = userID;
        this.fullName = fullName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.avatar = avatar;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.sex = sex;
        this.socialSecurityNumber = socialSecurityNumber;
        this.birthday = birthday; // Initialize birthday
    }

    // Constructor without userID and updatedAt (useful for creating new users)
    public User(String fullName, String email, String passwordHash, String phoneNumber, String address,
                String avatar, boolean isActive, Date createdAt, String sex, int socialSecurityNumber, Date birthday) {
        this.fullName = fullName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.avatar = avatar;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.sex = sex;
        this.socialSecurityNumber = socialSecurityNumber;
        this.birthday = birthday; // Initialize birthday
    }
    
    public User(String fullName, String email, String passwordHash, String phoneNumber, String address, String avatar, boolean isActive, Date createdAt) {
        this.fullName = fullName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.avatar = avatar;
        this.isActive = isActive;
        this.createdAt = createdAt;
    }

    // Getters and setters
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(int socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public Date getBirthday() {
        return birthday; // Getter for birthday
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday; // Setter for birthday
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", avatar='" + avatar + '\'' +
                ", isActive=" + isActive +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", sex='" + sex + '\'' +
                ", socialSecurityNumber=" + socialSecurityNumber +
                ", birthday=" + birthday + // Add birthday to toString
                '}';
    }
}
