package model;

public class InsuranceCompany {
    private int companyID;
    private String companyName;
    private String address;
    private String contactInfo;

    // Constructor chỉ với companyName
    public InsuranceCompany(String companyName) {
        this.companyName = companyName;
    }

    // Constructor đầy đủ (nếu cần)
    public InsuranceCompany( String companyName, String address, String contactInfo) {
        this.companyName = companyName;
        this.address = address;
        this.contactInfo = contactInfo;
    }

    public InsuranceCompany() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Getter và Setter
    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    @Override
    public String toString() {
        return "InsuranceCompany{" +
                "companyID=" + companyID +
                ", companyName='" + companyName + '\'' +
                ", address='" + address + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                '}';
    }
}
