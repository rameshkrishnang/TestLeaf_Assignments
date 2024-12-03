package org.testleaf.designpattern.decorator;

import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

public class RetryElementActions extends ElementActions {

    private final int maxRetries;

    public RetryElementActions(int maxRetries) {
        this.maxRetries = maxRetries;
    }

    @Override
    public void click(WebElement ele) {
        int attempt = 0;
        while (attempt < maxRetries) {
            try {
                super.click(ele);
                return;
            } catch (WebDriverException e) {
                System.out.println("Click attempt " + (attempt + 1) + " failed. Retrying...");
                attempt++;
                if (attempt == maxRetries) {
                    throw e;
                }
            }
        }
    }

    @Override
    public void sendKeys(WebElement ele, CharSequence text) {
        int attempt = 0;
        while (attempt < maxRetries) {
            try {
                super.sendKeys(ele, text);
                return;
            } catch (WebDriverException e) {
                System.out.println("SendKeys attempt " + (attempt + 1) + " failed. Retrying...");
                attempt++;
                if (attempt == maxRetries) {
                    throw e;
                }
            }
        }
    }

    // No retry logic for other methods, so we delegate them to the parent class
    @Override
    public String getText(WebElement ele) {
        return super.getText(ele);
    }

    @Override
    public boolean isDisplayed(WebElement ele) {
        return super.isDisplayed(ele);
    }

    @Override
    public boolean isEnabled(WebElement ele) {
        return super.isEnabled(ele);
    }

    @Override
    public boolean isSelected(WebElement ele) {
        return super.isSelected(ele);
    }
}
