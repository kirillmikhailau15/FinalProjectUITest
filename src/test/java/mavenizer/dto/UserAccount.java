package mavenizer.dto;


import mavenizer.helpers.TypeOfCreatingUser;
import org.apache.log4j.Logger;

public class UserAccount {

    Logger logger = Logger.getLogger(UserAccount.class);

    public static int countUsers = 0;

    private String taxID = "200000";
    private String company = "Company";
    private String firstname = "firstname";
    private String lastname = "lastname";
    private String address1 = "add1";
    private String address2 = "add2";
    private String postcode = "204050";
    private String city = "Minsk";
    private String phone = "+375290001101";
    private String email;
    private String email_main_part = "test@testqa.ru";


    public UserAccount(TypeOfCreatingUser type) {
        if (type == TypeOfCreatingUser.FULL_ATTRIBUTES) {
            setEmail(Math.random() + email_main_part);
            logger.debug("Full attributes conditional");
        }  else if (type == TypeOfCreatingUser.ONLY_REQUIRED_ATTRIBUTES) {
            setTaxID(" ");
            setCompany(" ");
            setAddress2(" ");
            setEmail(Math.random() + email_main_part);
            logger.debug("Only required conditional");
        } else if (type == TypeOfCreatingUser.NEED_ONE_REQUIRED) {
            setFirstname(" ");
            setEmail(Math.random() + email_main_part);
            logger.debug("Need attr conditional");
        }
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setTaxID(String taxID) {
        this.taxID = taxID;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public static int getCountUsers() {
        return countUsers;
    }

    public String getTaxID() {
        return taxID;
    }

    public String getCompany() {
        return company;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getCity() {
        return city;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
}
