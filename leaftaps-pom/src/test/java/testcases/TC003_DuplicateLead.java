package testcases;

import data.CombinedDataEngine;
import data.LeadInfo;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.FindLeadPage;
import pages.LeadsPage;

public class TC003_DuplicateLead extends BasePage {

	@Test
	public void runDuplicateLead() {
		String firstLead = new LeadsPage()
			.clickFindLeadsLink()
			.clickPhoneTab()
			.enterPhoneNumber(leadInfo.getPhoneNumber())
			.clickFindLeadButton()
			.getFirstLead();
		
			new FindLeadPage()
			.clickFirstLead()
			.clickDuplicateButton()
			.clickCreateDuplicate()
			.verifyLead(firstLead);

	}
}
