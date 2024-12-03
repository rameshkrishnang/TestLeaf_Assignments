package org.testleaf.designpattern.objectpool.pageobject.pages;

import org.openqa.selenium.By;
import org.testleaf.designpattern.objectpool.pageobject.objectpool.PageObjectPool;
import org.testleaf.designpattern.objectpool.pageobject.base.ProjectSpecificMethod;

public class HomePage extends ProjectSpecificMethod {

	PageObjectPool pageObjectPool = new PageObjectPool();

	public LeadsPage clickLeadsTab() {
		getDriver() .findElement(By.linkText("Leads")).click();
		return (LeadsPage) pageObjectPool.getPage(LeadsPage.class);
	}
}
