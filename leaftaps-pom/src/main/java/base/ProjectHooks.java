package base;

import com.testleaf.constants.BrowserTestEngine;
import com.testleaf.constants.BrowserType;
import com.testleaf.drivers.manager.DriverManager;
import com.testleaf.web.browser.Browser;
import org.testng.annotations.*;
import utility.ReadExcel;

import java.io.IOException;

public class ProjectHooks {

    public String leadID;
    public String dataSheetName;
    public static Browser browser;

    @BeforeMethod
    @Parameters({"browserEngine", "browserType"})
    public void preCondition(@Optional("SELENIUM") String browserEngineParam, @Optional("CHROME") String browserTypeParam) {
        BrowserTestEngine browserEngine = BrowserTestEngine.valueOf(browserEngineParam.toUpperCase());
        BrowserType browserType = BrowserType.valueOf(browserTypeParam.toUpperCase());

        // Get the browser instance from DriverManager
        browser = DriverManager.getBrowser(browserEngine, browserType);

        // Navigate to the URL and maximize
        browser.navigateTo("http://leaftaps.com/opentaps/");
        browser.maximize();
    }

    @AfterMethod
    public void postCondition() {
        // Close the browser
        browser.closeBrowser();

    }

    @DataProvider(name = "fetchData")
    public String[][] fetchData() throws IOException {
        return ReadExcel.readExcel(dataSheetName);
    }
}
