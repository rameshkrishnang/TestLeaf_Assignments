package org.testleaf.factory.designpattern.decorator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testleaf.designpattern.decorator.RetryElementActions;
import org.testleaf.designpattern.decorator.WebElementActions;

public class RetryElementActionsTest {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.get("https://practicetestautomation.com/practice-test-login/");

        WebElement textField_Username = driver.findElement(By.id("username"));
        WebElement textField_Password = driver.findElement(By.id("password"));
        WebElement button_Submit = driver.findElement(By.id("submit"));

        // Create a RetryElementActions instance with 3 retries
        WebElementActions actions = new RetryElementActions(3);

        // Use retry logic to send keys to a text field
        actions.sendKeys(textField_Username, "student");
        actions.sendKeys(textField_Password, "Password123");

        // Use retry logic to click the button
        actions.click(button_Submit);

        // Perform other actions without retry logic
        String loginSuccessText = driver.findElement(By.xpath("//h1[@class='post-title']")).getText();
        WebElement button_Logout = driver.findElement(By.xpath("//a[text()='Log out']"));
        boolean isButtonDisplayed = actions.isDisplayed(button_Logout);

        System.out.println("Login Success text: " + loginSuccessText);
        System.out.println("Is button displayed? " + isButtonDisplayed);

        driver.quit();
    }
}
