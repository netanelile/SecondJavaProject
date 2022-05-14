import Pages.*;
import Pages.HomeScreenOptions.Category;
import Pages.HomeScreenOptions.Event;
import Pages.HomeScreenOptions.PriceOptions;
import Pages.HomeScreenOptions.Region;
import Uitls.BasePage;
import Uitls.DriverSingleton;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BuyMeTest extends BasePage {
    //Reporter variables
    String key = "user.dir";
    String reportPath = "/src/main/resources/extent.html";
    String testName = "Buy me Test";
    String testDescription = "Register to buy me, find a gift and purchase it";
    String screenShotFolder = "src/main/resources/ScreenShots";
    //Tests Variables
    String firstname = "Netanel";
    String eMail = "Netanel2@"+timeNow+".com"; //create new user for each test
    String password = "Aa123456";
    String expectedURL = "https://buyme.co.il/search?budget=1&category=8&region=11";
    String businessToPick = "The Streets - רשת בתי קפה";
    String receiverName = "Prices Zelda";
    String greetingText = "Happy Birthday!";
    String imagePath = "/Users/nsananes/Dropbox/אושרי ונתנאלילה/קוד נתנאל/SecondJavaProject/src/main/resources/img.png";

    /**
     * @BeforeClass
     * Delete old Screenshots' folder
     * initiate reports
     * open BuyMe website
    */
   @BeforeClass
   public void beforeClass() {

       clearScreenShotsFolder(screenShotFolder);
       ExtentReports(key, reportPath, testName, testDescription);
       // log results
       test.log(Status.INFO, "@Before class");

       enterBuyMeWebsite();
       test.log(Status.PASS, "Entered Buy Me");
   }

    /**
     *@AfterClass
     * Take last screen screenshot and add it to the report file
     * Clise the Browser
     * Write all the tests data into the report file
     */
   @AfterClass
   public static void afterClass() {
        test.log(Status.INFO, "@After test " + "After test method");
        test.log(Status.INFO, "Last page image:", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(timeNow, ImagesPath)).build());
//        DriverSingleton.getDriverInstance().quit();
        // build and flush report
        extent.flush();
    }

    /**
     *@Test
     * First Test:
     * Register to BuyMe website by username, Email(each run generate new Email address automatically) & Password
     */
    @Test(priority=1)
    public void registrationScreen_Test() {
        Registration registration = new Registration();

        registration.registerToBuyMe(firstname, eMail, password);

        test.log(Status.PASS, "Successful Registration");
    }

    /**
     *@Test
     * Second Test:
     * Fill Price range from drop menu
     * Fill Region option from drop menu
     * Fill Gift Category from drop menu
     * Click on Find me a Gift Button
     */
    @Test(priority=2)
    public void homeScreen_Test() {
        HomeScreen hs = new HomeScreen();

        hs.clickOnPriceOptions(PriceOptions.till99);
        hs.clickOnRegionOptions(Region.CENTER);
        hs.clickOnCategoryOptions(Category.GIFT_CARD_BREAKFAST);
        hs.clickFindMeGiftButton();

        test.log(Status.PASS, "Gift was found!");
    }

    /**
     *@Test
     * Third Test:
     * When getting to the search result screen due to a success run of the second test:
     * Assert correct website URL
     * Click on a specific business by its display name
     * Enter a price for a gift from the chosen business
     * And enter on the 'Enter Sum' button
     */
    @Test(priority=3)
    public void picBusiness_Test() {
        PickupABusiness pab = new PickupABusiness();

        Assert.assertEquals(DriverSingleton.getDriverInstance().getCurrentUrl(), expectedURL);
        pab.clickOnABusiness(businessToPick);
        pab.enterPrice("300");
        test.log(Status.PASS, "Business was Pickede");
    }

    /**
     *@Test
     * Forth Test:
     * When getting to the search Purchase screen:
     * First stage - To whom:
     * Enter for whom the gift: choose 'For someone else'
     * Enter the receiver name
     * Enter celebration reason e.g birthday
     * Enter a greeting for the receiver
     * Upload image by path
     * Click Continue
     * Second stage - How to send the gift:
     * When to send - choose 'Now'
     * How to send - choose 'Email'
     * From whom the gift? - enter the name of the gift's sender
     * Click on Continue to Payment
     */
    @Test(priority=4)
    public void purchaseGift_Test() {
        GiftPurchase gp = new GiftPurchase();
        gp.purchaseAgift(receiverName, Event.BIRTHDAY, greetingText, imagePath, eMail, firstname);
        test.log(Status.PASS, "Purchased s Gift");
    }

}
