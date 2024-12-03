package org.testleaf.designpattern.decorator;

import org.openqa.selenium.WebElement;

public class ElementActions implements WebElementActions{

    @Override
    public void click(WebElement ele) {
        ele.click();
    }

    @Override
    public void sendKeys(WebElement ele, CharSequence text) {
        ele.sendKeys(text);
    }

    @Override
    public String getText(WebElement ele) {
        return ele.getText();
    }

    @Override
    public boolean isDisplayed(WebElement ele) {
        return ele.isDisplayed();
    }

    @Override
    public boolean isEnabled(WebElement ele) {
        return ele.isEnabled();
    }

    @Override
    public boolean isSelected(WebElement ele) {
        return ele.isSelected();
    }
}
