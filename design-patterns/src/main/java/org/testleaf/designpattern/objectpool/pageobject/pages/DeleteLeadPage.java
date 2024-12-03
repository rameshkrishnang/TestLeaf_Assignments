package org.testleaf.designpattern.objectpool.pageobject.pages;

import org.openqa.selenium.By;
import org.testleaf.designpattern.objectpool.pageobject.objectpool.PageObjectPool;
import org.testleaf.designpattern.objectpool.pageobject.base.ProjectSpecificMethod;

public class DeleteLeadPage extends ProjectSpecificMethod {

    PageObjectPool pageObjectPool = new PageObjectPool();

    public DeleteLeadPage clickPhoneTab() {
        getDriver().findElement(By.xpath("//span[text()='Phone']")).click();
        return this;
    }

    public DeleteLeadPage enterPhoneNumber() {
        getDriver().findElement(By.xpath("//input[@name='phoneNumber']")).sendKeys("99");
        return this;
    }

    public DeleteLeadPage clcikFindLeadButton() {
        getDriver().findElement(By.xpath("//button[text()='Find Leads']")).click();
        return this;
    }

    public ViewLeadPage clickFirstResultID() {
        getDriver().findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a")).click();
        return (ViewLeadPage) pageObjectPool.getPage(ViewLeadPage.class);
    }
}
