package org.testleaf.designpattern.decorator;

import org.openqa.selenium.WebElement;

public interface WebElementActions {

    void click(WebElement ele);

    void sendKeys(WebElement ele, CharSequence text);

    String getText(WebElement ele);

    boolean isDisplayed(WebElement ele);

    boolean isEnabled(WebElement ele);

    boolean isSelected(WebElement ele);

}
