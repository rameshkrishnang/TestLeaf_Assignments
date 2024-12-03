package org.testleaf.designpattern.objectpool.pageobject.pages;

import org.openqa.selenium.By;
import org.testleaf.designpattern.objectpool.pageobject.objectpool.PageObjectPool;
import org.testleaf.designpattern.objectpool.pageobject.base.ProjectSpecificMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MergeLeadPage extends ProjectSpecificMethod {

	PageObjectPool pageObjectPool = new PageObjectPool();

    public MergeLeadPage clickFromLead() {
        getDriver().findElement(By.xpath("//img[@alt='Lookup']")).click();
        return this;
    }

    public MergeLeadPage clickToLead() {
        getDriver().findElement(By.xpath("(//img[@alt='Lookup'])[2]")).click();
        return this;

    }

    public MergeLeadPage clickFindleadsButton() {
        getDriver().findElement(By.xpath("//button[text()='Find Leads']")).click();
        return this;
    }

    public MergeLeadPage clickToFindleadsButton() {
        switchToOriginalWindow(0);
        getDriver().findElement(By.xpath("//button[text()='Find Leads']")).click();
        return this;
    }

    public MergeLeadPage clickFirstLead() throws InterruptedException {
        Thread.sleep(500);
        getDriver().findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a")).click();
        return this;
    }

    public MergeLeadPage enterFirstName(String firstName) {
        switchToNewWindow(1);
        getDriver().findElement(By.xpath("//input[@name='firstName']")).sendKeys(firstName);
        return this;
    }

    public MergeLeadPage getLeadID() {
        leadID = getDriver().findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a")).getText();
        System.out.println(leadID);
        switchToOriginalWindow(0);
        return this;
    }

    public void switchToNewWindow(int index) {
        Set<String> windows = getDriver().getWindowHandles();
        List<String> handles = new ArrayList<>(windows);
        String exWindow = handles.get(index);
        getDriver().switchTo().window(exWindow);
    }

    public void switchToOriginalWindow(int index) {
        Set<String> windows = getDriver().getWindowHandles();
        List<String> handles = new ArrayList<>(windows);
        String exWindow = handles.get(index);
        getDriver().switchTo().window(exWindow);
    }

    public LeadsPage clickMergeButton() {
        getDriver().findElement(By.xpath("//a[text()='Merge']")).click();
        getDriver().switchTo().alert().accept();
        return (LeadsPage) pageObjectPool.getPage(LeadsPage.class);
    }

}
