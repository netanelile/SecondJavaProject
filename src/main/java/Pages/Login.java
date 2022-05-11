package Pages;

import Unitls.BasePage;
import org.openqa.selenium.By;

public class Login extends BasePage {

    public void loginToBuyMe(String email, String pass){
        clickOnEntryRegistration();
        login_EnterEmail(email);
        login_EnterPassword(pass);
        login_ClickLoginToBuyMeButton();
    }

    private void clickOnEntryRegistration(){
        clickElement(By.className("notSigned"));
    }

    private void login_EnterEmail(String email) {
        sendKeysToElement(By.id("ember1768"), email);
    }

    private void login_EnterPassword(String pass) {
        sendKeysToElement(By.id("ember1775"), pass);
    }

    private void login_ClickLoginToBuyMeButton(){
        clickElement(By.id("ember1784"));
    }
}
