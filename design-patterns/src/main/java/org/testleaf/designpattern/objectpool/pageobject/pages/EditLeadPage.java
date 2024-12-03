package org.testleaf.designpattern.objectpool.pageobject.pages;

import org.openqa.selenium.By;
import org.testleaf.designpattern.objectpool.pageobject.objectpool.PageObjectPool;
import org.testleaf.designpattern.objectpool.pageobject.base.ProjectSpecificMethod;

public class EditLeadPage extends ProjectSpecificMethod {

	PageObjectPool pageObjectPool = new PageObjectPool();

	public EditLeadPage updateCompanyName(String UdateCName) {
		getDriver().findElement(By.id("updateLeadForm_companyName")).sendKeys(UdateCName);
		return this;
	}

	public ViewLeadPage clickUpdate() {
		getDriver().findElement(By.name("submitButton")).click();
		return (ViewLeadPage) pageObjectPool.getPage(ViewLeadPage.class);
	}

}