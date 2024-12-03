package org.testleaf.factory.designpattern.objectpool;

import org.openqa.selenium.WebDriver;
import org.testleaf.designpattern.objectpool.webdriver.WebDriverPool;

public class WebDriverTest {

    private static WebDriverPool pool;

    public static void main(String[] args) {
        pool = new WebDriverPool();
        test1();
        test2();
        pool.closeBrowser();
    }

    public static void test1(){
        WebDriver driver = pool.getDriver();
        driver.get("https://www.google.com/");
        driver.manage().window().maximize();
        pool.releaseDriver(driver);
    }

    public static void test2(){
        WebDriver driver = pool.getDriver();
        driver.get("https://expense.certify.com/Login.aspx");
        driver.manage().window().maximize();
        pool.releaseDriver(driver);
    }

}
