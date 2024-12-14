package testcases;

import org.openqa.selenium.Alert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.LeadsPage;

public class TC005_MergeLead extends BasePage {

    @Test()
    public void runEditLead() throws InterruptedException {
        new LeadsPage()
                .clickMergeLeadLink()
                .enterFromLead(multipleLeadInfo.get(0).getLeadId())
                .enterToLead(multipleLeadInfo.get(1).getLeadId())
                .clickMergeButton()
                .clickFindLeadsLink()
                .searchLeadID(multipleLeadInfo.get(0).getLeadId())
                .verifyDeletedLeadID();

    }
}
