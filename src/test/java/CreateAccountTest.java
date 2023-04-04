import mavenizer.helpers.TypeOfCreatingUser;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.*;
import mavenizer.objectPO.CreateAccountPage;
import mavenizer.objectPO.MainPage;

public class CreateAccountTest extends BaseTest {


    @BeforeMethod
    public void getURL() {
        driver.get("https://litecart.stqa.ru/en/create_account");
    }


    @Description("Check form is showed")
    @Test
    public void createAccountFormIsShow() {
        CreateAccountPage createAccountPage = new CreateAccountPage(driver);
        Assert.assertTrue(createAccountPage.getCreateAccountForm().isEnabled());
    }

    @Description("Don't enter anything")
    @Test(enabled = false)
    public void pressCreateAccountWithoutSetInfo() {
        CreateAccountPage createAccountPage = new CreateAccountPage(driver);
        createAccountPage.createAccountButtonClick();
        String expectedMessage = "Заполните это поле.";
        Assert.assertEquals(createAccountPage.getFirstNameField().getDomProperty("validationMessage"),expectedMessage);
    }


    @Description("Is field zoneCode disabled")
    @Test
    public void zoneCodeIsDisable() {
        CreateAccountPage createAccountPage = new CreateAccountPage(driver);
        //добавить wait, который ждет загрузки формы
        Assert.assertFalse(createAccountPage.getZoneCode().isDisplayed());
    }

    @Description("Changing phone after change country")
    @Test
    public void changePhoneZone() {
        CreateAccountPage createAccountPage = new CreateAccountPage(driver);
        createAccountPage.enterBelarusAtCountryInput();
        String expectedPhoneCode = "+375";
        Assert.assertEquals(createAccountPage.getPhoneCodeFromField(),expectedPhoneCode);

    }




    @Description("Password don't match")
    @Test
    public void setDifferentPasswords() {
        CreateAccountPage createAccountPage = new CreateAccountPage(driver);
        createAccountPage.fillUserForm(TypeOfCreatingUser.FULL_ATTRIBUTES);
        createAccountPage.setConfirmPassword("different");
        createAccountPage.createAccountButtonClick();

        String expectedMessage = "The passwords did not match.";
        Assert.assertEquals(createAccountPage.getErrorMessageAfterCreating(),expectedMessage);
    }

    @Description("Email is exist")
    @Test
    public void setExistEmail() {
        CreateAccountPage createAccountPage = new CreateAccountPage(driver);
        createAccountPage.fillUserForm(TypeOfCreatingUser.FULL_ATTRIBUTES);
        createAccountPage.getEmailAddress().clear();
        createAccountPage.setEmailField("nniaha@yandex.ru");
        createAccountPage.createAccountButtonClick();

        String expectedMessage = "The email address already exists in our customer database. Please login or select a different email address.";
        Assert.assertEquals(createAccountPage.getErrorMessageAfterCreating(),expectedMessage);
    }


    @Description("Not valid email")
    @Test(enabled = false)
    public void setNotValidEmail() {
        CreateAccountPage createAccountPage = new CreateAccountPage(driver);
        createAccountPage.fillUserForm(TypeOfCreatingUser.FULL_ATTRIBUTES);
        createAccountPage.getEmailAddress().clear();
        createAccountPage.setEmailField("ru");
        createAccountPage.createAccountButtonClick();

        String expectedMessage = "Адрес электронной почты должен содержать символ \"@\". В адресе \"ru\" отсутствует символ \"@\".";
        Assert.assertEquals(createAccountPage.getEmailAddress().getDomProperty("validationMessage"),expectedMessage);
    }



    @Description("Creating account with full list of attrs")
    @Test
    public void createUserWithFullAttributesByFunc() {
        CreateAccountPage createAccountPage = new CreateAccountPage(driver);
        MainPage mainPage = new MainPage(driver);
        createAccountPage.fillUserForm(TypeOfCreatingUser.FULL_ATTRIBUTES);
        createAccountPage.createAccountButtonClick();

        String expectedMessage = "Your customer account has been created.";
        Assert.assertEquals(mainPage.getSuccessMessageTextAfterRegistration(),expectedMessage);
    }

    @Description("Creating account with only required attrs")
    @Test
    public void createUserOnlyRequiredAttributesByFunc() {
        CreateAccountPage createAccountPage = new CreateAccountPage(driver);
        MainPage mainPage = new MainPage(driver);
        createAccountPage.fillUserForm(TypeOfCreatingUser.ONLY_REQUIRED_ATTRIBUTES);
        createAccountPage.createAccountButtonClick();

        String expectedMessage = "Your customer account has been created.";
        Assert.assertEquals(mainPage.getSuccessMessageTextAfterRegistration(),expectedMessage);
    }

}

