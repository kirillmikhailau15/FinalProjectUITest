package mavenizer.helpers;

import org.openqa.selenium.By;

import java.io.InputStream;
import java.util.Properties;

public class LocatorHelper {
    private static Properties locators;

    static {
        locators = new Properties();
        InputStream is = LocatorHelper.class.getResourceAsStream("/mavenizer.properties");
        try {
            locators.load(is);
        } catch (Exception e) {
            System.out.println("Error from property file");
        }
    }

    public static By getLocator(String locator) throws Exception {
        String value = locators.getProperty(locator);

        String[] result = value.split("=",2);
        LocatorType locatorType = LocatorType.valueOf(result[0]);
        String selector = result[1];
        switch (locatorType) {
            case id: return By.id(selector);
            case css: return By.cssSelector(selector);
            case xpath: return By.xpath(selector);
            case tagName: return By.tagName(selector);
            case linkText: return By.linkText(selector);
            case className: return By.className(selector);
            default: throw new Exception("No such locators");
        }
    }



}
