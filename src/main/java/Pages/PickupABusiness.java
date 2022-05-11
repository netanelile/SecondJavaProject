package Pages;

import Unitls.BasePage;
import Unitls.DriverSingleton;
import org.openqa.selenium.By;

public class PickupABusiness extends BasePage {

    public void clickOnABusiness(String businessToPick){
        clickElement(By.partialLinkText(businessToPick));
    }

    public void enterPrice(String price) {
        sendKeysToElement(By.xpath("//*[contains(@placeholder,'הכנס סכום')]"), price);
        DriverSingleton.getDriverInstance().findElement(By.xpath("//*[contains(@placeholder,'הכנס סכום')]")).submit();
    }
}
