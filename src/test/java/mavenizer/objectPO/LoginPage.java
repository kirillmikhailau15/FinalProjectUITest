package mavenizer.objectPO;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class LoginPage {
    public WebDriver driver;
    Logger logger = Logger.getLogger(LoginPage.class);

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }


    /*Elements*/
    private By emailField = By.cssSelector("[name=\"email\"]");
    private By passField = By.cssSelector("[name=\"password\"]");
    private By rememberMeCheckBox = By.cssSelector("[name=\"remember_me\"]");
    private By loginButton = By.cssSelector("[name=\"login\"]");
    private By lostPassButton = By.cssSelector("[name=\"lost_password\"]");
    //Substitute by LocatorHelper
    private By newCustomerLinkButton = By.linkText("New customers click here");
    private By lostPasswordErrorMessage = By.cssSelector("[class='notice errors']");
    private By lostPasswordSuccessMessage = By.cssSelector("[class='notice success']");
    private By logoutButton = By.linkText("Logout");





    /*Getters*/
    public String getErrorMessageText() {
        return driver.findElement(lostPasswordErrorMessage).getText();
    }

    public String getSuccessMessageText() {
        return driver.findElement(lostPasswordSuccessMessage).getText();
    }
    public WebElement getEmailField() {
        return driver.findElement(emailField);
    }

    public WebElement getPasswordField() {
        return driver.findElement(passField);
    }
    public WebElement getLoginButton() {
        return driver.findElement(loginButton);
    }
    public WebElement getLostPassButton() {
        return driver.findElement(lostPassButton);
    }
    public WebElement getLogoutLink() {
        return driver.findElement(logoutButton);
    }



    /*Setters*/
    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void setPass(String password) {
        driver.findElement(passField).sendKeys(password);
    }

    public void setFullInformation(String email, String password) {
        setEmail(email);
        setPass(password);
        logger.info("Attempt to login with creditionals: email - " + email + "; password - " + password);
    }



    /*Motions*/
    @Step("Click rememberMe button")
    public void clickRememberMeButton() {
        driver.findElement(rememberMeCheckBox).click();
    }

    @Step("Click login button")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void clickLostPasswordButton() {
        driver.findElement(lostPassButton).click();
    }

    @Step("Click newCustomer button")
    public void clickNewCustomerButton() throws Exception {
        driver.findElement(newCustomerLinkButton).click();
        //driver.findElement(LocatorHelper.getLocator("LoginPage.newCustomerLinkButton")).click();
    }

    @Step("Attempt to login")
    public void attemptToLogin(String email,String password, Boolean pressRememberMe) {
        setFullInformation(email,password);
        if (pressRememberMe) {
            clickRememberMeButton();
        }
        clickLoginButton();

    }


}
