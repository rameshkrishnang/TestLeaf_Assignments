package org.testleaf.factory.designpattern.objectpool.pageObjectTest;

import org.testleaf.designpattern.objectpool.pageobject.objectpool.PageObjectPool;
import org.testleaf.designpattern.objectpool.pageobject.base.ProjectSpecificMethod;
import org.testleaf.designpattern.objectpool.pageobject.pages.FindLeadPage;
import org.testleaf.designpattern.objectpool.pageobject.pages.LoginPage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class TC_004_DeleteLead extends ProjectSpecificMethod {

    PageObjectPool pageObjectPool = new PageObjectPool();

    @BeforeTest
    public void setData() {
        dataSheetName = "DeleteLead";
    }

    @Test(dataProvider = "fetchData")
    public void runDeleteLead(String uName, String pass, String ph) throws InterruptedException {
        LoginPage lp = (LoginPage) pageObjectPool.getPage(LoginPage.class);
        FindLeadPage flp = (FindLeadPage) pageObjectPool.getPage(FindLeadPage.class);
        String firstLead = new LoginPage().enterUsername(uName).enterPassword(pass).clickLogin().clickCrmsfa().clickLeadsTab().clickfindLeadLink().clickPhoneTab().enterPhoneNumber(ph).clickFindLeadButton().getFirstLead();
        flp.clickDeleteFirstLead().clickfindLeadLink().clickLeadIDTab().enterLeadID(firstLead).clickFindLeadButton().verifydeletedLeadID();

    }

    @AfterTest
    public void tearDown() {
        pageObjectPool.clearPool();
    }
}
