package Pages;

import Uitls.BasePage;
import org.openqa.selenium.By;

/**
 * Registration page that allows anyone to create a user for Buyme Website. By giving First name, Email & Password
 */
public class Registration extends BasePage {

    /**
     * This function is fill up all the necessary details to create user:
     * @param firstName  The user's First Name
     * @param email The user's Email address
     * @param pass The user's Password. This parameter will be use twice: at enter password field
     *             and Re-Enter password field.
     */
    public void registerToBuyMe(String firstName, String email, String pass) {
        clickOnEntryRegistration();
        clickOnRegistration();
        registration_EnterFirstName_AndAssert(firstName, "value");
        registration_EnterEmail_AndAssert(email, "value");
        registration_EnterPassword(pass);
        registration_EnterPasswordValidation(pass);
        submit();
    }

    /**
     * From the homepage, The system will click on Entry/Registration button
     */
    private void clickOnEntryRegistration(){
        test.info("Click on Entry / Registration");
        clickElement(By.className("notSigned"));
//        clickElement(Constants.EntryRegistration_Button); //Todo: check why it doesn't work from constant
    }

    /**
     * From the Login Form, The system will lick on the Registration Link
     */
    private void clickOnRegistration(){
        test.info("Click on Registration link");
        clickElement(By.xpath("//*[@id=\"ember952\"]/div/div[1]/div/div/div[3]/div[1]/span"));
    }

    /**
     * At the Registration Form, The System will enter the User's first Name and assert that the data that has been
     * entered to the text box is correct.
     * The assertion will be done using the textbook tag name. Because getText() doesn't work. getAttribute() function
     * Has to be used instead.
     * @param firstName The user's first name
     * @param TagName The name of the 'first name' text box's tag
     */
    private void registration_EnterFirstName_AndAssert(String firstName, String TagName) {
        test.info("Enter First Name ("+ firstName +") And Assert");
        sendKeysToElement(By.xpath("//*[contains(@placeholder,'שם פרטי')]"), firstName);
        assertEqualsString(By.xpath("//*[contains(@placeholder,'שם פרטי')]"), TagName, firstName);
    }

    /**
     * At the Registration Form, The System will enter the User's Email address and assert that the data that has been
     * entered to the text box is correct.
     * The assertion will be done using the textbook tag name. Because getText() doesn't work. getAttribute() function
     * Has to be used instead.
     * @param email The user's Email address
     * @param TagName The name of the 'Email address' text box's tag
     */
    private void registration_EnterEmail_AndAssert(String email, String TagName) {
        test.info("Enter Email ("+ email +") And Assert");
        sendKeysToElement(By.xpath("//*[contains(@placeholder,'מייל')]"), email);
        assertEqualsString(By.xpath("//*[contains(@placeholder,'מייל')]"), TagName, email);
    }

    /**
     * At the Registration Form, The System will enter the User's password to the Enter parody textbook
     * @param pass The User's password
     */
    private void registration_EnterPassword(String pass){
        test.info("Enter password: " + pass);
        sendKeysToElement(By.xpath("//*[contains(@placeholder,'סיסמה')]"), pass);
    }

    /**
     * At the Registration Form, The System will enter the User's password to the Re-Enter parody textbook
     * @param pass The User's password
     */
    private void  registration_EnterPasswordValidation(String pass){
        test.info("Re-Enter password" + pass);
        sendKeysToElement(By.xpath("//*[contains(@placeholder,'אימות סיסמה')]"), pass);
    }

}
