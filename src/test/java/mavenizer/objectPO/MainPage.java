package mavenizer.objectPO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    WebDriver driver;

    private By successMessage = By.cssSelector("[class='notice success']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getSuccessMessageTextAfterRegistration() {
        return driver.findElement(successMessage).getText();
    }
}
