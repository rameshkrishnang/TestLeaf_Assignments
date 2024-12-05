package base;

import com.testleaf.constants.BrowserTestEngine;
import com.testleaf.constants.BrowserType;
import com.testleaf.drivers.manager.DriverManager;
import com.testleaf.web.api.APIClient;
import com.testleaf.web.browser.Browser;
import org.testng.annotations.*;

public class ProjectHooks {

    private static final ThreadLocal<Browser> browser = new ThreadLocal<>();
    public String token = "00D5g00000LNGD5!AR0AQKTwGcZgZPzn.P2hQlihyT6hw78grIhho50iB328r8NPfZraydUbRxpnhtTiKmhNN8PVDoxL7jn3dOvFzHCzPXxjXibJ";
    public String apiEndPoint = "https://qeagle8-dev-ed.develop.my.salesforce.com/services/data/v58.0/sobjects";

    public static Browser getBrowser() {
        return browser.get();
    }

    public static APIClient getAPI() {
        return (APIClient) browser.get();
    }

    @BeforeMethod
    @Parameters({"browserEngine", "browserType"})
    public void preCondition(@Optional("SELENIUM") String browserEngineParam, @Optional("CHROME") String browserTypeParam) {
        BrowserTestEngine browserEngine = BrowserTestEngine.valueOf(browserEngineParam.toUpperCase());
        BrowserType browserType = BrowserType.valueOf(browserTypeParam.toUpperCase());

        // Get the browser instance from DriverManager
        Browser br = DriverManager.getBrowserWithAPI(browserEngine, browserType);
        browser.set(br);

        // Navigate to the URL and maximize
        getBrowser().navigateTo("https://qeagle8-dev-ed.develop.lightning.force.com/");
        getBrowser().maximize();
    }

    @AfterMethod
    public void postCondition() {
        // Close the browser
        getBrowser().closeBrowser();
    }

    @AfterTest
    public void postConditionTest() {
        getBrowser().quitBrowser();
    }

}
