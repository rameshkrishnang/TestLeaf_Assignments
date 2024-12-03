package org.testleaf.designpattern.objectpool.pageobject.pages;

import org.openqa.selenium.By;
import org.testleaf.designpattern.objectpool.pageobject.objectpool.PageObjectPool;
import org.testleaf.designpattern.objectpool.pageobject.base.ProjectSpecificMethod;

public class ViewLeadPage extends ProjectSpecificMethod {

    PageObjectPool pageObjectPool = new PageObjectPool();

    public ViewLeadPage verifyLead() {
        String firstName = getDriver().findElement(By.id("viewLead_firstName_sp")).getText();
        if (firstName.contains(firstName)) {
            System.out.println("Verified");
        } else {
            System.out.println("Not verified");
        }
        return this;
    }

    public ViewLeadPage verifyCompanyName() {
        String firstName = getDriver().findElement(By.id("viewLead_companyName_sp")).getText();
        if (firstName.contains("Qeagle")) {
            System.out.println("Verified");
        } else {
            System.out.println("Not Verified");
        }
        return this;
    }

    public EditLeadPage clickEditButton() {
        getDriver().findElement(By.linkText("Edit")).click();
        return (EditLeadPage) pageObjectPool.getPage(EditLeadPage.class);
    }

    public DuplicatePage clickDuplicateButton() {
        getDriver().findElement(By.linkText("Duplicate Lead")).click();
        return (DuplicatePage) pageObjectPool.getPage(DuplicatePage.class);
    }

    public LeadsPage clickDeleteButton() {
        getDriver().findElement(By.linkText("Delete")).click();
        return (LeadsPage) pageObjectPool.getPage(LeadsPage.class);
    }

}
