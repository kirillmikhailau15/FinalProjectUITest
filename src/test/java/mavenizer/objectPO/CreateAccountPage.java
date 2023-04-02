package mavenizer.objectPO;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import mavenizer.helpers.TypeOfCreatingUser;
import mavenizer.dto.UserAccount;
import org.apache.log4j.Logger;


public class CreateAccountPage {
    WebDriver driver;
    Logger logger = Logger.getLogger(CreateAccountPage.class);

    public CreateAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    /*Elements*/
    private By taxIdField = By.cssSelector(".content [name='tax_id']");
    private By companyField = By.cssSelector(".content [name='company']");
    private By firstNameField = By.cssSelector(".content [name='firstname']");
    private By lastNameField = By.cssSelector(".content [name='lastname']");
    private By address1Field = By.cssSelector(".content [name='address1']");
    private By address2Field = By.cssSelector(".content [name='address2']");
    private By postCodeField = By.cssSelector(".content [name='postcode']");
    private By cityField = By.cssSelector(".content [name='city']");
    private By findCountryButton = By.cssSelector(".select2-selection__rendered");
    private By foundCountryInput = By.cssSelector(".select2-search__field");
    private By zoneCodeField = By.cssSelector(".content [name='zone_code']");
    private By emailField = By.cssSelector(".content [name='email']");
    private By phoneField = By.cssSelector(".content [name='phone']");
    private By desiredPasswordField = By.cssSelector(".content [name='password']");
    private By confirmPasswordField = By.cssSelector(".content [name='confirmed_password']");
    private By createAccountButton = By.cssSelector(".content [name='create_account']");
    private By createAccountForm = By.cssSelector("[name=\"customer_form\"]");
    private By errorMessage = By.cssSelector("#notices .notice");


    /*Getters*/
    public WebElement getCreateAccountForm() {
        return driver.findElement(createAccountForm);
    }
    public WebElement getFirstNameField() {
        return driver.findElement(firstNameField);
    }
    public WebElement getZoneCode() {
        return driver.findElement(zoneCodeField);
    }
    public String getErrorMessageAfterCreating() {
        return driver.findElement(errorMessage).getText();
    }
    public WebElement getEmailAddress() {
        return driver.findElement(emailField);
    }
    public String getPhoneCodeFromField() {
        return driver.findElement(phoneField).getAttribute("placeholder");
    }





    /*Setters*/
    public void setTaxIdField(String TaxID) {
        driver.findElement(taxIdField).sendKeys(TaxID);
    }

    public void setCompanyField(String companyName) {
        driver.findElement(companyField).sendKeys(companyName);
    }
    public void setFirstNameField(String firstName) {
        driver.findElement(firstNameField).sendKeys(firstName);
    }
    public void setLastNameField(String lastName) {
        driver.findElement(lastNameField).sendKeys(lastName);
    }
    public void setAddress1Field(String address1) {
        driver.findElement(address1Field).sendKeys(address1);
    }
    public void setAddress2Field(String address2) {
        driver.findElement(address2Field).sendKeys(address2);
    }
    public void setPostCodeField(String postCode) {
        driver.findElement(postCodeField).sendKeys(postCode);
    }
    public void setCityField(String cityName) {
        driver.findElement(cityField).sendKeys(cityName);
    }
    public void setEmailField(String email) {
        driver.findElement(emailField).sendKeys(email);
    }
    public void setPhoneField(String phone) {
        driver.findElement(phoneField).sendKeys(phone);
    }
    public void setDesiredPassword(String desiredPassword) {
        driver.findElement(desiredPasswordField).sendKeys(desiredPassword);
    }
    public void setConfirmPassword(String confirmPassword) {
        driver.findElement(confirmPasswordField).sendKeys(confirmPassword);
    }

    /*Motions*/
    public void createAccountButtonClick() {
        driver.findElement(createAccountButton).click();
    }
    public void enterBelarusAtCountryInput() {
        driver.findElement(findCountryButton).click();
        driver.findElement(foundCountryInput).sendKeys("Belarus" + Keys.ENTER);
        logger.info("Select country - Belarus");
    }


    @Step("Fill user form")
    public void fillUserForm(TypeOfCreatingUser type) {
        UserAccount user = new UserAccount(type);
        setTaxIdField(user.getTaxID());
        setCompanyField(user.getCompany());
        setFirstNameField(user.getFirstname());
        setLastNameField(user.getLastname());
        setAddress1Field(user.getAddress1());
        setAddress2Field(user.getAddress2());
        setPostCodeField(user.getPostcode());
        setCityField(user.getCity());
        setEmailField(user.getEmail());
        setPhoneField(user.getPhone());

        enterBelarusAtCountryInput();

        setDesiredPassword("123");
        setConfirmPassword("123");

    }



}
