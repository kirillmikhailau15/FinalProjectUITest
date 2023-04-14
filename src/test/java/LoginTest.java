import mavenizer.helpers.TestNGDataProvider;
import mavenizer.helpers.Waits;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.log4testng.Logger;
import mavenizer.objectPO.LoginPage;


public class LoginTest extends BaseTest {
    Logger logger = Logger.getLogger(LoginTest.class);


    @Description("Click new customer button without login info")
    @Test
    public void clickNewCustomerButton() throws Exception {
        LoginPage loginPage = new LoginPage(driver);
        String expectedTitle = "Create Account | My Store";
        loginPage.clickNewCustomerButton();
        Waits.waitForJsEnds(driver,3);
        Assert.assertEquals(driver.getTitle(),expectedTitle);
    }

    @Description("Click lost pass, when user include in DB")
    @Test
    public void clickLostPassUserInDB() {
        LoginPage loginPage = new LoginPage(driver);
        String expectedMessage = "A new password has been sent to your email address.";
        loginPage.setFullInformation("kirill.mikhailau@gmail.com","124");
        loginPage.clickLostPasswordButton();
        Assert.assertEquals(loginPage.getSuccessMessageText(),expectedMessage);


    }

    @Description("Click lost pass, when user doesn't include in DB")
    @Test
    public void clickLostPassUserNotInDB() {
        LoginPage loginPage = new LoginPage(driver);
        String expectedMessage = "The email address does not exist in our database.";
        loginPage.setFullInformation("nniaha3@yandex.ru","124");
        loginPage.clickLostPasswordButton();
        Assert.assertEquals(loginPage.getErrorMessageText(),expectedMessage);

    }



    @Description("Check visibility of all fields")
    @Test
    public void allFieldsAreVisible() {
        LoginPage loginPage = new LoginPage(driver);
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(loginPage.getEmailField().isEnabled());
        softAssert.assertTrue(loginPage.getPasswordField().isEnabled());
        softAssert.assertTrue(loginPage.getLoginButton().isEnabled());
        softAssert.assertTrue(loginPage.getLostPassButton().isEnabled());

        softAssert.assertAll();
    }

    @Description("Log without inputting password")
    @Test
    public void loginWithoutPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setFullInformation("kirill.mikhailau@gmail.com","");
        loginPage.clickLoginButton();
        String expectedMessage = "You must provide both email address and password.";

        Assert.assertEquals(loginPage.getErrorMessageText(),expectedMessage);
    }

    @Description("Log with wrong email")
    @Test
    public void loginWithWrongEmail() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setFullInformation("kirill","123");
        loginPage.clickLoginButton();
        String expectedMessage = "Wrong password or the account is disabled, or does not exist";

        Assert.assertEquals(loginPage.getErrorMessageText(),expectedMessage);
    }

    @Description("Log with wrong password")
    @Test
    public void loginWithWrongPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setFullInformation("kirill.mikhailau@gmail.com","1qaz!QAZ");
        loginPage.clickLoginButton();
        String expectedMessage = "Wrong password or the account is disabled, or does not exist";

        Assert.assertEquals(loginPage.getErrorMessageText(),expectedMessage);
    }

    @Description("Log without email, include 2 situations:without email, without pass and email")
    @Test(dataProvider = "error_login_without_email", dataProviderClass = TestNGDataProvider.class)
    public void loginWithoutEmailAddress(String email, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setFullInformation(email,password);
        loginPage.clickLoginButton();
    }

    @Description("Correct params. Success login")
    @Test
    public void successLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setFullInformation("nniaha@yandex.ru", "1qaz!QAZ");
        loginPage.clickLoginButton();

        String expectedMessage = "You are now logged in as Kiryl Mikhailau.";

        Assert.assertEquals(loginPage.getSuccessMessageText(),expectedMessage);
    }

    @Description("Correct params. Success login. Other assert(for training)")
    @Test
    public void successLogin2() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setFullInformation("nniaha@yandex.ru", "1qaz!QAZ12521251");
        loginPage.clickLoginButton();

        Assert.assertTrue(loginPage.getLogoutLink().isEnabled());
    }

    @Description("Correct params. Success login with RememberMeButton")
    @Test
    public void successLoginWithRememberMeButton() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.attemptToLogin("nniaha@yandex.ru", "1qaz!QAZ",true);

        Assert.assertTrue(loginPage.getLogoutLink().isEnabled());
    }

    @Description("Check logout after login")
    @Test
    public void logoutCheck() {
        LoginPage loginPage = new LoginPage(driver);
        String expectedMessage = "You are now logged out.";
        logger.info("Attempt to login with correct params");
        loginPage.attemptToLogin("nniaha@yandex.ru", "1qaz!QAZ",true);
        //Сюда впихнуть wait, чтобы показать, что умею
        logger.info("Check that logout button is visible");
        if (loginPage.getLogoutLink().isEnabled()) {
            logger.info("button is visiable, click logout");
            loginPage.getLogoutLink().click();
            Assert.assertEquals(loginPage.getSuccessMessageText(),expectedMessage);
        }
    }









}
