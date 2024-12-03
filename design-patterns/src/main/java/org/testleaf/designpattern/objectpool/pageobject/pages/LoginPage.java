package org.testleaf.designpattern.objectpool.pageobject.pages;

import org.openqa.selenium.By;
import org.testleaf.designpattern.objectpool.pageobject.objectpool.PageObjectPool;
import org.testleaf.designpattern.objectpool.pageobject.base.ProjectSpecificMethod;

public class LoginPage extends ProjectSpecificMethod {

	PageObjectPool pageObjectPool = new PageObjectPool();

	public LoginPage enterUsername(String uname) {
		// Use getDriver() to access the WebDriver instance
		getDriver().findElement(By.id("username")).sendKeys(uname);
		return this;
	}

	public LoginPage enterPassword(String Pass) {
		getDriver().findElement(By.id("password")).sendKeys(Pass);
		return this;
	}

	public WelcomePage clickLogin() {
		getDriver().findElement(By.className("decorativeSubmit")).click();
		return (WelcomePage) pageObjectPool.getPage(WelcomePage.class);
	}
}
