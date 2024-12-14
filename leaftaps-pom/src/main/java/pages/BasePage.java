package pages;

import com.testleaf.web.browser.BrowserCookie;
import com.testleaf.web.browser.SeBrowserImpl;
import data.RedisDataEngine;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import base.ProjectHooks;
import config.ConfigurationManager;
import data.CombinedDataEngine;
import data.LeadInfo;

import java.util.List;
import java.util.Set;

public class BasePage extends ProjectHooks{
	
	protected LeadInfo leadInfo;
	protected List<LeadInfo> multipleLeadInfo;

	@BeforeMethod
	public void setup() {
		leadInfo = CombinedDataEngine.fetchData();
		multipleLeadInfo = CombinedDataEngine.fetchMultipleData();
		getBrowser().navigateTo(ConfigurationManager.configuration().url());

		// If retry, load a fresh data set
		if (getRetryContext().isRetry()) {
			leadInfo = CombinedDataEngine.fetchRetryData();
			multipleLeadInfo = CombinedDataEngine.fetchMultipleRetryData();
		}

		// Check if cookies are present in Redis
		if (RedisDataEngine.jedis.exists("Selenium:Cookies")) {
			// Load cookies from Redis and add them to the browser
			Set<BrowserCookie> cookies = RedisDataEngine.loadCookiesFromRedis();
			getBrowser().addCookies(cookies);

			getBrowser().navigateTo(ConfigurationManager.configuration().url()); // Refresh to ensure session is restored
		} else {
			// Perform login
			new LoginPage()
					.enterUsername(ConfigurationManager.configuration().userName())
					.enterPassword(ConfigurationManager.configuration().password())
					.clickLogin()
					.clickCrmsfa()
					.clickLeadsTab();

			// Save cookies to Redis
			Set<BrowserCookie> browserCookies = getBrowser().getCookies();
			RedisDataEngine.saveCookiesToRedis(browserCookies);
		}
	}
	
	@AfterMethod
	public void tearDown() {
		new HomePage()
		.clickOpentaps()
		.clickLogout();

		// Clear all cookies in the browser
		getBrowser().clearAllCookies();

		// Optionally clear Redis cookies
		RedisDataEngine.clearCookiesFromRedis();
	}

}
