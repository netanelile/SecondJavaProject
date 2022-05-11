package Pages;

import Pages.HomeScreenOptions.Event;
import Unitls.BasePage;
import org.openqa.selenium.By;

public class GiftPurchase extends BasePage {

    public void purchaseAgift(String receiverName, Event event, String greetingText, String imagePath, String Email, String senderName){
        clickOnForMySelf();
        enterGiftReciverName(receiverName);
        picAnEvent(event);
        enterGreeting(greetingText);
//        addImage(imagePath);
        submit();
        clickNowRadioButton();
        clickEmailButton();
        enterEmail(Email);
        enterSenderName(senderName);
        submit();
    }

    private void enterSenderName(String senderName) {
        scroll(250, 350);
        String locator = "//*[contains(@placeholder,'שם שולח המתנה')]";
        sendKeysToElement(By.xpath(locator), senderName);
//        assertEqualString(By.xpath(locator), senderName);//todo: fix
    }

    private void enterEmail(String email) {
        String locator = "email";
        sendKeysToElement(By.id(locator), email);
//        assertEqualString(By.xpath(locator), email);//todo: fix - try get attribute or get value

    }

    private void clickEmailButton() {
        clickElementFromList(By.className("toggle-icon"), 1);
    }

    private void clickNowRadioButton() {
        clickElement(By.xpath("//div[@gtm='עכשיו']"));

    }

    private void addImage(String imagePath) {//todo: make it work!
        scroll(250, 350);
//        clickElement(By.className("bm-caption-2"));//ember1798, ember1381, ember1382, svjuh5nj5a, ember1798, ember1799
        sendKeysToElement(By.className("thumbnail"), imagePath);
//        JavascriptExecutor jse = (JavascriptExecutor)DriverSingleton.getDriverInstance();
//        jse.executeScript("arguments[0].value='"+ imagePath +"';", DriverSingleton.getDriverInstance().findElement(By.id("ember1381")));
//        jse.executeScript("document.getElementByClass('bm-media-upload').setAttribute('value', '"+imagePath+"')");
    }

    private void enterGreeting(String greetingText) {
        clickElement(By.className("textarea-clear-all"));
        sendKeysToElement(By.className("parsley-success"), greetingText);
    }

    private void picAnEvent(Event event) {
        clickElement(By.className("selected-name"));
        clickElement(By.xpath("//*[contains(text(),'" + chooseEventOptions(event) + "')]"));
    }

    private void enterGiftReciverName(String receiverName) {
        sendKeysToElement(By.id("friendName"), receiverName);
    }

    private void clickOnForMySelf() {
        clickElement(By.xpath("//div[@gtm='למישהו אחר']"));
    }

    private String chooseEventOptions(Event event){
        return switch (event) {
            case BIRTHDAY -> "יום הולדת";
            case BIRTH -> "לידה";
            case THANKS -> "תודה";
            case GET_WELL_SOON -> "החלמה מהירה";
            case FAREWELL -> "פרידה";
            case HINA -> "חינה";
            case WEDDING -> "חתונה";
            case BAR_MITZVA -> "בר מצווה";
            case BAT_MITZVA -> "בת מצווה";
            case QUARANTINE -> "בידוד";
            case BECAUSE_I_WANT_TO -> "סתם כי בא לי לפנק";
            case NEW_HOUSE -> "כניסה לבית חדש";
            case ANNIVERSARY -> "יום נישואין";
            case GOOD_LUCK -> "בהצלחה";
            case GIFT_TO_EMPLOYEES -> "מתנה לעובדים";
            default -> null;
        };
    }
}
