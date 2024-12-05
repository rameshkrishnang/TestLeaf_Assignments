package testcases;

import com.testleaf.web.api.ResponseAPI;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.HomePage;

public class TC001_CreateLead extends BasePage {

    @Test
    public void runCreateLead() {

        String firstName = "Ramesh";
        String lastName = "Krishnan";
        String companyName = "Altimetrik";

        String body = "{\n" + "    \"FirstName\": \"" + firstName + "\",\n" + "    \"LastName\": \"" + lastName + "\",\n" + "    \"Company\": \"" + companyName + "\"\n" + "}";

        ResponseAPI response = getAPI().post(apiEndPoint + "/Lead", token, body);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());

        String firstLead = new HomePage().searchApps("Leads").clickLeads().getFirstLead();

        System.out.println(firstLead);
    }
}
