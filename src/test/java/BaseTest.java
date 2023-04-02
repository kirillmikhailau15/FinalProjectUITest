import mavenizer.helpers.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.apache.log4j.Logger;

import java.time.Duration;

import static mavenizer.helpers.Browser.CHROME;


public class BaseTest {
    public WebDriver driver;
    Logger logger = Logger.getLogger(BaseTest.class);



    @BeforeMethod
    public void setup() {
        Browser browser = Browser.getEnumByLabel(System.getProperty("browser", CHROME.getBrowserName()));

        switch (browser) {
            case CHROME -> {driver = new ChromeDriver(); break;}
            //case FIREFOX -> driver = new FirefoxDriver();
            default -> driver = new ChromeDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(100));
        driver.get("https://litecart.stqa.ru/en/");
        logger.info("Connect is success");
    }


    @AfterMethod
    public void teardown() {
        driver.quit();
    }



}
