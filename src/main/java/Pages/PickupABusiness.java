package Pages;

import Uitls.BasePage;
import Uitls.DriverSingleton;
import org.openqa.selenium.By;

/**
 * The Search result screen. where the user can pic up a business that maches his search result from the home screen page.
 */
public class PickupABusiness extends BasePage {

    /**
     * The system will choose a business by the business actual display name.
     * @param businessToPick Business name as it displayed in the search results page.
     */
    public void clickOnABusiness(String businessToPick){
        test.info("Click on a business:" + businessToPick);
        clickElement(By.partialLinkText(businessToPick));
    }

    /**
     * In some of the business, the user can choose a price that will be the price worth of the gift he wants to send.
     * And enter on the 'Enter Sum' button
     * @param price number of a price the user wish to spent
     */
    public void enterPrice(String price) {
        test.info("Enter a Price:" + price);
        sendKeysToElement(By.xpath("//*[contains(@placeholder,'הכנס סכום')]"), price);
        DriverSingleton.getDriverInstance().findElement(By.xpath("//*[contains(@placeholder,'הכנס סכום')]")).submit();
    }
}
