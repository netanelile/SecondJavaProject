package Pages;

import Pages.HomeScreenOptions.Event;
import Uitls.BasePage;
import Uitls.DriverSingleton;
import org.openqa.selenium.By;

public class GiftPurchase extends BasePage {

    /**
     * Purchase a gitft test goes through 3 different screen fragments.
     * At the first fragment, the system click on 'Someone else option', enters Gift receiver name and assert that the
     * Correct name displayed at the drop box, pic an Event in which the gift has been sent for (like birthday),
     * The user will add a greeting, Image by URL and click on Continue button.
     *
     * At the second gragment, the system will click the 'Now' Radio button, Schoose an 'Email' option and enter email -
     * one of the 3 possible method to send the gift to the receiver.,
     * It will also enter the sender name and assert its has been inserted correctly and Click Continue to payment button.
     *
     * As for the payment and third fragment, there was no specification as for this moment.
     *
     * @param receiverName The name of gift's receiver
     * @param event the name of the event that on its behalf the gif was given (ex. birthday)
     * @param greetingText greeting text that the receiver will get from the gift sender
     * @param imagePath path of the image file that would be added to the form
     * @param Email gift sender email
     * @param senderName gift sender name
     */
    public void purchaseAgift(String receiverName, Event event, String greetingText, String imagePath, String Email, String senderName) {
        clickOnForSomeOneElse();
        enterGiftReceiverName_AndAssert(receiverName);
        picAnEvent(event);
        enterGreeting(greetingText);
        addImage(imagePath);
        ClickContinue();
        clickNowRadioButton();
        clickEmailButton();
        enterEmail(Email);
        enterSenderName_AndAssert(senderName);
        submit();
    }

    /**
     * Click on Continue button
     * Thread sleep has to be used here. the base page wait doesn't go pass the loader.
     * So it's a must to wait for this element manually
     *
     */
    private void ClickContinue() {
        try {
            Thread.sleep(10000);
        }catch (Exception e){}
        clickElement(By.xpath("//button[@gtm='המשך']"));
    }

    /**
     * The system will scroll a little to make the element in the rest of the page visible, and enter the gift sender's
     * Name & will assert that the correct name is display inside the sender name text box.
     * @param senderName gift sender name
     */
    private void enterSenderName_AndAssert(String senderName) {
        test.info("Enter Sender Name: " + senderName);
        scroll(250, 350);
        String locator = "//*[contains(@placeholder,'שם שולח המתנה')]";
        sendKeysToElement(By.xpath(locator), senderName);
        assertEqualsString(By.xpath(locator), "value", senderName);
    }

    /**
     * There are 3 different ways to send the gift receiver the link to the gift. Email is one of them.
     * In this step, the system fill the Email field after clicking on the Email option.
     * @param email gift receiver email
     */
    private void enterEmail(String email)  {
        test.info("Enter Email: " + email);
        String locator = "email";
        sendKeysToElement(By.id(locator), email);
    }

    /**
     * clicking on one of the 3 option available to send the user the gift
     * In this step, the system will click on the 'Email' button
     */
    private void clickEmailButton() {
        test.info("Click Email Button");
        clickElementFromList(By.className("toggle-icon"), 1);
    }

    /**
     * There are 2 different option of when to send the gift receiver the gift.
     * Now or on a different time.
     * In this tep, the system will click on 'Now' radio button
     */
    private void clickNowRadioButton() {
        test.info("Click Now Radio Button");
        clickElement(By.xpath("//div[@gtm='עכשיו']"));
    }

    /**
     * The system will scroll again on the second fragment to make the rest of the page element visible.
     * Then it will upload an image file by the file Path
     * @param imagePath Actual Path to the image
     */
    private void addImage(String imagePath) {
        test.info("Add Image from path: " + imagePath);
        scroll(250, 350);
        DriverSingleton.getDriverInstance().findElement(By.xpath("//input[@type='file']")).sendKeys(imagePath);
        //todo: Ask why it doesn't work from the base page
        test.info("Image added successfully");
    }

    /**
     * The system will enter a greeting from the gift sender, that the gift receiver can see when he gets the gift.
     * @param greetingText The greeting text from the receiver to the sender.
     */
    private void enterGreeting(String greetingText) {
        test.info("Enter Greeting Text: " + greetingText);
        clickElement(By.className("textarea-clear-all"));
        sendKeysToElement(By.className("parsley-success"), greetingText);
    }

    /**
     * Choose option from the Event drop box empllying the reason why the gift has been sent (e.g. Birthday)
     * @param event Even in it behalf the git was sent. The event is taken from event Emum that contains all the
     *              options inside the drop menu.
     */
    private void picAnEvent(Event event) {
        test.info("Pick an Event: " + chooseEventOptions(event));
        clickElement(By.className("selected-name"));
        clickElement(By.xpath("//*[contains(text(),'" + chooseEventOptions(event) + "')]"));
    }

    /**
     * The system will Enter the Receiver Name and assert that the data that has been entered to the text box is correct.
     * @param receiverName Receiver Name
     */
    private void enterGiftReceiverName_AndAssert(String receiverName) {
        test.info("Enter Gift Receiver Name: " + receiverName);
        sendKeysToElement(By.id("friendName"), receiverName);
        assertEqualsString(By.xpath("//input[@type='text']"), "value", receiverName);
    }

    /**
     * The system will choose For Someone else option (ot of 2 options)
     */
    private void clickOnForSomeOneElse() {
        test.info("Click on For Someone Else");
        clickElement(By.xpath("//div[@gtm='למישהו אחר']"));
    }

    /**
     * Event picker. When choosing an Enum Event that represent the content of the drop menu,
     * The system will return the actual element value of the chosen option.
     * @param option Event to choose from the Enum (tha represent the content of the 'Event' drop menu)
     * @return The String of the Actual Event so the system will know on which element to click.
     */
    private String chooseEventOptions(Event option){
        return switch (option) {
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
