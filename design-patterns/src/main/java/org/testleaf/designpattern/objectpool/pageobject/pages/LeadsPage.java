package org.testleaf.designpattern.objectpool.pageobject.pages;

import org.openqa.selenium.By;
import org.testleaf.designpattern.objectpool.pageobject.objectpool.PageObjectPool;
import org.testleaf.designpattern.objectpool.pageobject.base.ProjectSpecificMethod;

public class LeadsPage extends ProjectSpecificMethod {

	PageObjectPool pageObjectPool = new PageObjectPool();

	public CreateLeadPage clickCreateLeadLink() {
		getDriver().findElement(By.linkText("Create Lead")).click();
		return (CreateLeadPage) pageObjectPool.getPage(CreateLeadPage.class);
	}

	public FindLeadPage clickfindLeadLink() {
		getDriver().findElement(By.linkText("Find Leads")).click();
		return (FindLeadPage) pageObjectPool.getPage(FindLeadPage.class);
	}

	public MergeLeadPage clickmergeLeadLink() {
		getDriver().findElement(By.linkText("Merge Leads")).click();
		return (MergeLeadPage) pageObjectPool.getPage(MergeLeadPage.class);
	}

}
