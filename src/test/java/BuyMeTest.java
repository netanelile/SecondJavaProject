import Pages.*;
import Pages.HomeScreenOptions.Category;
import Pages.HomeScreenOptions.Event;
import Pages.HomeScreenOptions.PriceOptions;
import Pages.HomeScreenOptions.Region;
import Unitls.BasePage;
import Unitls.DriverSingleton;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BuyMeTest extends BasePage {
    private static WebDriverWait wait;
    String firstname = "Netanel";
    String eMail = "Netanel@netanel1.com";
    String password = "Aa123456";
    String expectedURL = "https://buyme.co.il/search?budget=1&category=8&region=11";
    String businessToPick = "The Streets - רשת בתי קפה";
    String receiverName = "Prices Zelda";
    String greetingText = "Happy Birthday!";
    String imagePath = "/Users/nsananes/Dropbox/אושרי ונתנאלילה/קוד נתנאל/SecondJavaProject/src/main/resources/img.pngg";

   @BeforeClass
   public void beforeClass(){
       enterBuyMeWebsite();
   }

    @Test(priority=1)
    public void registrationScreen_Test() {
        Registration registration = new Registration();

        registration.registerToBuyMe(firstname, eMail, password);
    }

    @Test(priority=2)
    public void homeScreen_Test() {
        HomeScreen hs = new HomeScreen();

        hs.clickOnPriceOptions(PriceOptions.till99);
        hs.clickOnRegionOptions(Region.CENTER);
        hs.clickOnCategoryOptions(Category.GIFT_CARD_BREAKFAST);
        hs.clickFindMeGiftButton();
    }

    @Test(priority=3)
    public void picBusiness_Test() {
        PickupABusiness pab = new PickupABusiness();

        Assert.assertEquals(DriverSingleton.getDriverInstance().getCurrentUrl(), expectedURL);
        pab.clickOnABusiness(businessToPick);
        pab.enterPrice("300");
    }

    @Test(priority=4)
    public void purchaseAgift_Test(){
        GiftPurchase gp = new GiftPurchase();

        DriverSingleton.getDriverInstance().manage().window().maximize();
        gp.purchaseAgift(receiverName, Event.BIRTHDAY, greetingText, imagePath, eMail, firstname);
    }

}
