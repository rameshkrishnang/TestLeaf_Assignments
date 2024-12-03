package org.testleaf.factory.designpattern.objectpool.pageObjectTest;

import org.testleaf.designpattern.objectpool.pageobject.objectpool.PageObjectPool;
import org.testleaf.designpattern.objectpool.pageobject.base.ProjectSpecificMethod;
import org.testleaf.designpattern.objectpool.pageobject.pages.LoginPage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TC_003_DuplicateLead extends ProjectSpecificMethod {

    PageObjectPool pageObjectPool = new PageObjectPool();

    @BeforeTest
    public void setData() {
        dataSheetName = "DuplicateLead";
    }

    @Test(dataProvider = "fetchData")
    public void runDuplicateLead(String uName, String pass, String ph) throws InterruptedException {
        LoginPage lp = (LoginPage) pageObjectPool.getPage(LoginPage.class);
        lp.enterUsername(uName).enterPassword(pass).clickLogin().clickCrmsfa().clickLeadsTab().clickfindLeadLink().clickPhoneTab().enterPhoneNumber(ph).clickFindLeadButton().clickFirstLead().clickDuplicateButton().clickCreateDuplicate().verifyLead();

    }

    @AfterTest
    public void tearDown() {
        pageObjectPool.clearPool();
    }
}
