package pages;

import com.testleaf.constants.LocatorType;
import org.openqa.selenium.Alert;

public class MergeLeadPage extends BasePage {

	public MergeLeadPage clickFromLead() throws InterruptedException {
		getBrowser().locateLink(LocatorType.XPATH, "(//img[@alt='Lookup'])[1]").click();
		return this;
	}

	public MergeLeadPage clickToLead() throws InterruptedException {
		getBrowser().locateLink(LocatorType.XPATH, "(//img[@alt='Lookup'])[2]").click();
		return this;

	}

	public MergeLeadPage clickFindleadsButton() throws InterruptedException {
		getBrowser().locateButton(LocatorType.XPATH, "//button[text()='Find Leads']").click();
		return this;
	}

	public MergeLeadPage clickToFindleadsButton() throws InterruptedException {
		//switchToOriginalWindow(0);
		getBrowser().locateButton(LocatorType.XPATH, "//button[text()='Find Leads']").click();
		return this;
	}

	public MergeLeadPage clickFirstLead() throws InterruptedException {
		Thread.sleep(500);
		getBrowser().locateLink(LocatorType.XPATH, "(//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a)[1]").click();
		return this;
	}

	public MergeLeadPage enterFirstName(String firstName) throws InterruptedException {
		//switchToNewWindow(1);
		getBrowser().locateEdit(LocatorType.XPATH, "(//input[@name='firstName'])[1]").type(firstName);
		return this;
	}

	public MergeLeadPage getLeadID() throws InterruptedException {
		leadID = getBrowser().locateElement(LocatorType.XPATH, "(//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a)[1]/div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a").getText();
		System.out.println(leadID);
		//switchToOriginalWindow(0);
		return this;
	}

	public LeadsPage clickMergeButton() {
		getBrowser().locateLink(LocatorType.XPATH, "//a[text()='Merge']").click();
		getBrowser().clickOkOnAlert();
		return new LeadsPage();
	}

	public MergeLeadPage enterFromLead(String fromLead) {
		getBrowser().locateEdit(LocatorType.XPATH, "//input[@id='ComboBox_partyIdFrom']").type(fromLead);
		return this;
	}

	public MergeLeadPage enterToLead(String toLead) {
		getBrowser().locateEdit(LocatorType.XPATH, "//input[@id='ComboBox_partyIdTo']").type(toLead);
		return this;
	}

}
