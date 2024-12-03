package org.testleaf.designpattern.objectpool.pageobject.pages;

import org.openqa.selenium.By;
import org.testleaf.designpattern.objectpool.pageobject.objectpool.PageObjectPool;
import org.testleaf.designpattern.objectpool.pageobject.base.ProjectSpecificMethod;

public class DuplicatePage extends ProjectSpecificMethod {

    PageObjectPool pageObjectPool = new PageObjectPool();

    public ViewLeadPage clickCreateDuplicate() {
        getDriver().findElement(By.name("submitButton")).click();
        return (ViewLeadPage) pageObjectPool.getPage(ViewLeadPage.class);
    }

}