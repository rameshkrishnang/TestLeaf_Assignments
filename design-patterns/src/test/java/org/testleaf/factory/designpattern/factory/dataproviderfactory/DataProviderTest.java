package org.testleaf.factory.designpattern.factory.dataproviderfactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testleaf.designpattern.factory.browserfactory.BrowserFactory;
import org.testleaf.designpattern.factory.browserfactory.BrowserType;
import org.testleaf.designpattern.factory.dataproviderfactory.DataProviderFactory;
import org.testleaf.designpattern.factory.dataproviderfactory.DataSourceType;
import org.testleaf.designpattern.factory.dataproviderfactory.TestDataProvider;

import java.util.Map;

public class DataProviderTest {

    public static void main(String[] args) {
        // Retrieve data from Excel
        TestDataProvider dataProvider = DataProviderFactory.getDataProvider(DataSourceType.EXCEL);
        Map<String, String> credentials = dataProvider.getTestData();

        String username = credentials.get("username");
        String password = credentials.get("password");

        WebDriver driver = BrowserFactory.launchBrowserWithUrl(BrowserType.EDGE);
        BrowserFactory.launchUrl(driver);
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("submit")).click();
        BrowserFactory.closeBrowser(driver);
    }
}