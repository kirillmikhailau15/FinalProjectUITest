package mavenizer.helpers;

import org.testng.annotations.DataProvider;

public class TestNGDataProvider {

    @DataProvider(name = "error_login_without_email")
    public Object[][] getDataFromProvider() {
        return  new Object[][] {
                {"", "124"},
                {"", ""}
        };

    }


}
