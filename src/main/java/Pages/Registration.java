package Pages;

import Unitls.BasePage;
import Unitls.Constants;
import org.openqa.selenium.By;

public class Registration extends BasePage {
//    By EntryRegistration_Button = By.className("notSigned");
    public void registerToBuyMe(String firstName, String email, String pass){
        clickOnEntryRegistration();
        clickOnRegistration();
        registration_EnterFirstName(firstName);
        registration_EnterEmail(email);
        registration_EnterPassword(pass);
        registration_EnterPasswordValidation(pass);
        submit();
    }

    private void clickOnEntryRegistration(){
//        clickElement(By.className("notSigned"));
        clickElement(Constants.EntryRegistration_Button);
    }

    private void clickOnRegistration(){
        clickElement(By.xpath("//*[@id=\"ember952\"]/div/div[1]/div/div/div[3]/div[1]/span"));
    }

    private void  registration_EnterFirstName(String firstName){
        sendKeysToElement(By.xpath("//*[contains(@placeholder,'שם פרטי')]"), firstName);
//        assertEqualString(By.id("ember1795"), firstName);
    }

    private void registration_EnterEmail(String email){
        sendKeysToElement(By.xpath("//*[contains(@placeholder,'מייל)]"), email);
//        assertEqualString(By.id("ember1801"), email);
    }

    private void registration_EnterPassword(String pass){
        sendKeysToElement(By.xpath("//*[contains(@placeholder,'סיסמה')]"), pass);
//        assertEqualString(By.id("ember1808"), pass);
    }

    private void  registration_EnterPasswordValidation(String pass){
        sendKeysToElement(By.xpath("//*[contains(@placeholder,'אימות סיסמה')]"), pass);
//        assertEqualString(By.id("ember1815", pass);
    }

}
