package model;

public class InsuranceCompany {

    private int companyID;
    private String companyName;
    private String address;
    private String contactInfo;

    public InsuranceCompany() {
    }

    public InsuranceCompany(int companyID, String companyName, String address, String contactInfo) {
        this.companyID = companyID;
        this.companyName = companyName;
        this.address = address;
        this.contactInfo = contactInfo;
    }

    public InsuranceCompany(String companyName, String address, String contactInfo) {
        this.companyName = companyName;
        this.address = address;
        this.contactInfo = contactInfo;
    }

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
