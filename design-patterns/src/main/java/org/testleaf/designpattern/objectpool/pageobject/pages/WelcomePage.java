package org.testleaf.designpattern.objectpool.pageobject.pages;

import org.openqa.selenium.By;
import org.testleaf.designpattern.objectpool.pageobject.objectpool.PageObjectPool;
import org.testleaf.designpattern.objectpool.pageobject.base.ProjectSpecificMethod;

public class WelcomePage extends ProjectSpecificMethod {

	PageObjectPool pageObjectPool = new PageObjectPool();

	public WelcomePage verifyTitle() {
		System.out.println(getDriver().getTitle());
		return this;
	}

	public HomePage clickCrmsfa() {
		getDriver().findElement(By.linkText("CRM/SFA")).click();
		return (HomePage) pageObjectPool.getPage(HomePage.class) ;
	}

}
