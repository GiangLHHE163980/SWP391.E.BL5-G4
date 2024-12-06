package model;

import java.util.Date;
import java.util.List;

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
    private Date createdAt;
    private Date updatedAt;
    private String sex; // New field
    private int socialSecurityNumber; // New field
    private Date birthday; // New field for birthday
    private InsuranceCard insuranceCard;
   

    public User(int userID, String username, boolean isActive) {
        this.userID = userID;
        this.isActive = isActive;
        this.username = username;
    }

    // Default constructor
    public User() {
    }


    // Constructor with all fields
    public User(int userID, String fullName, String email, String passwordHash, String phoneNumber, String address,
            String avatar, boolean isActive, Date createdAt, Date updatedAt, String sex, int socialSecurityNumber, Date birthday,String username) {}
    public User(int userId, String fullName, String email) {
        this.userID = userId;
        this.fullName = fullName;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Constructor with all fields
    public User(int userID, String fullName, String email, String passwordHash, String phoneNumber, String address,
            String avatar, boolean isActive, Date createdAt, Date updatedAt, String sex, int socialSecurityNumber, Date birthday) {
        this.userID = userID;
        this.fullName = fullName;
        this.email = email;
        this.username = username;
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
            String avatar, boolean isActive, Date createdAt, String sex, int socialSecurityNumber, Date birthday,String username) {
        this.fullName = fullName;
        this.email = email;
        this.username = username;
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

    public User(String username, String fullName, String email, String passwordHash, String phoneNumber, String address,
            String sex, int socialSecurityNumber, boolean isActive, Date createdAt) {
    this.username = username;
    this.fullName = fullName;
    this.email = email;
    this.passwordHash = passwordHash;
    this.phoneNumber = phoneNumber;
    this.address = address;
    this.sex = sex;
    this.socialSecurityNumber = socialSecurityNumber;
    this.isActive = isActive;
    this.createdAt = createdAt;
    this.updatedAt = createdAt; // Initialize updatedAt as same as createdAt or set it later
    this.avatar = "./image/default-avatar.jpg"; // default avatar path
    this.birthday = null; // Can be set later
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
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }

    @Override
    public String toString() {
        return "User{" +
            "userID=" + userID +
            ", fullName='" + fullName + '\'' +
            ", email='" + email + '\'' +
            ", insuranceCard=" + insuranceCard + 
            '}';
    }

    public void setInsuranceCards(List<InsuranceCard> insuranceCards) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public InsuranceCard setInsuranceCard(InsuranceCard card) {
        return this.insuranceCard;
    }

}
