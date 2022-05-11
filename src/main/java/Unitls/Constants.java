package Unitls;

import org.openqa.selenium.By;

public class Constants extends BasePage{
    public static final String CHROMEDRIVER_PATH = "/Users/nsananes/Downloads/chromedriver";
    public static final String BUY_ME_URL = "https://buyme.co.il/";

    public static final int WAIT_DURATION = 10;

    public static By EntryRegistration_Button = By.className("notSigned");  //todo: ask: is this a right way? cuz it doesn't work
    public static By Registration_Button = By.className("text-link theme");
    public static By FirstName_TextBox = By.id("ember1795");
    public static By Email_TextBox = By.id("ember1801");
    public static By Password_TextBox = By.id("ember1808");
    public static By PasswordValidation_TextBox = By.id("ember1815");
    public static By RegisterToBuyMe_Button = By.id("ember1822");


}
