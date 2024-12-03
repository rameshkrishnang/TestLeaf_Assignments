package org.testleaf.factory.designpattern.objectpool.pageObjectTest;

import org.testleaf.designpattern.objectpool.pageobject.objectpool.PageObjectPool;
import org.testleaf.designpattern.objectpool.pageobject.base.ProjectSpecificMethod;
import org.testleaf.designpattern.objectpool.pageobject.pages.LoginPage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TC_002_EditLead extends ProjectSpecificMethod {

    PageObjectPool pageObjectPool = new PageObjectPool();

    @BeforeTest
    public void setData() {
        dataSheetName = "EditLead";
    }

    @Test(dataProvider = "fetchData")
    public void runEditLead(String uName, String pass, String ph, String updatecName) throws InterruptedException {
        LoginPage lp = (LoginPage) pageObjectPool.getPage(LoginPage.class);
        lp.enterUsername(uName).enterPassword(pass).clickLogin().clickCrmsfa().clickLeadsTab().clickfindLeadLink().clickPhoneTab().enterPhoneNumber(ph).clickFindLeadButton().clickFirstLead().clickEditButton().updateCompanyName(updatecName).clickUpdate().verifyCompanyName();
    }

    @AfterTest
    public void tearDown() {
        pageObjectPool.clearPool();
    }
}
