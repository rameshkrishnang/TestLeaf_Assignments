package org.testleaf.factory.designpattern.objectpool.pageObjectTest;

import org.testleaf.designpattern.objectpool.pageobject.objectpool.PageObjectPool;
import org.testleaf.designpattern.objectpool.pageobject.base.ProjectSpecificMethod;
import org.testleaf.designpattern.objectpool.pageobject.pages.LoginPage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class TC_001_CreateLead extends ProjectSpecificMethod {

    PageObjectPool pageObjectPool = new PageObjectPool();

    @BeforeTest
    public void setData() {
        dataSheetName = "CreateLead";
    }

    @Test(dataProvider = "fetchData")
    public void runCreateLead(String uName, String pass, String cName, String fName, String lName, String ph) {
        LoginPage lp = (LoginPage) pageObjectPool.getPage(LoginPage.class);
        lp.enterUsername(uName).enterPassword(pass).clickLogin().clickCrmsfa().clickLeadsTab().clickCreateLeadLink().enterCompanyName(cName).enterFirstName(fName).enterLastName(lName).enterPhno(ph).clickSubmit().verifyLead();

    }

    @AfterTest
    public void tearDown() {
        pageObjectPool.clearPool();
    }
}
