package Uitls;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.io.FileUtils;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Duration;

/**
 * The Core function that are inharited across this projects such as:
 * Reports generater
 * Clear last session's failed tests images directory
 * Take screenshot
 * Get data from XML file
 * Get Browser Data form XML file
 * Scroll up
 * Get an Element
 * Get an Element from a list
 * CLick on Element
 * CLick on Element from a list
 * Get attribute from tag name
 * Assert that element string content is equal to a certain String
 */
public class BasePage {

    public static ExtentReports extent;
    // creates a toggle for the given test, adds all log events under it
    public static ExtentTest test;
    public static String timeNow = String.valueOf(System.currentTimeMillis()); //Generate string of the current
                                    // date&Time with millisecond. its use to generate unique usernames & Images names.
    private static String xmlFilePath = "src/main/resources/data.xml"; //XML path that direct to URL & Browsertype
    public static String ImagesPath = "src/main/resources/ScreenShots/"; // Path that indicate where the failed Test's images will be saved
    public static WebDriverWait WAIT = new WebDriverWait(DriverSingleton.getDriverInstance(),
            Duration.ofSeconds(Constants.WAIT_DURATION)); //Implicit wait

    /**
     * get the Browser Type to be used in the test as defined in the XML
     * @return
     */
    public static String getBrowser(){
        try {
            String browserType = getData("browserType");
            return browserType;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This function get the tag name and return the tag content from the XML
     * @param tagName XML's tag name
     * @return String content of the chosen tag
     * @throws Exception
     */
    private static String getData (String tagName) throws Exception {
        File fXmlFile = new File(xmlFilePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);
        doc.getDocumentElement().normalize();
        return doc.getElementsByTagName(tagName).item(0).getTextContent();
    }

    /**
     * Reprot generator function
     * @param key  user.dir
     * @param path where to save the HTML report file
     * @param testName the name of the test that will be display in the report file
     * @param testDescription the description of the test that will be display at the report file
     */
    protected void ExtentReports(String key, String path, String testName, String testDescription) {
        String cwd = System.getProperty(key);
        extent = new ExtentReports();
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(cwd + path);
        extent.attachReporter(htmlReporter);
        // name your test and add description
        test = extent.createTest(testName, testDescription);
    }

    /**
     * General function that click on element by locator
     * @param locator locator af the element to click on
     */
    public void clickElement(By locator) {
        getWebElement(locator).click();
    }

    /**
     * general function that clicks on element from inside a listby locator and the index of the specific element
     * @param locator the element locator
     * @param index the element index
     */
    public void clickElementFromList(By locator, int index){
        getWebElement(locator, index).click();
    }

    /**
     * This function send text to a cretin element by Locator
     * @param locator Locator to send the text to
     * @param text the text to send the element
     */
    public void sendKeysToElement(By locator, String text) {
        try {
            test.info("Send keys: " + text);
            getWebElement(locator).sendKeys(text);
        }catch (Exception e){
            test.fail("Failed to enter keys: " + text + "\n" +e);
        }
    }

    /**
     * This function return the text that inside an element by locator and tag name.
     * @param locator element's locator
     * @param name the tag that's hold the value of the text that inside the element
     * @return the text that inside the element
     */
    public String getAttributeFromElement(By locator, String name) {
        return getWebElement(locator).getAttribute(name);
    }

    /**
     * This function assert if text of a certain element is equal to an expected text
     * @param locator element's locator
     * @param name the tag that's hold the value of the text that inside the element
     * @param expected give the reason what when wrong. if the element not found
     *                 or the actual and the expected results do not match
     */
    public void assertEqualsString(By locator, String name, String expected) {
        String actual = null;
        try{
            actual =  getAttributeFromElement(locator, name);
            if (actual.equalsIgnoreCase(expected)){
                test.pass("Assert Equal sting Pass: String: " + getAttributeFromElement(locator, name) + " Is equal to: " + expected);
            }else{
                test.fail("Assertion field! \n" +
                                "Expected element did not match the actual \n" +
                                "Found: " + actual + "\n" +
                                "Expected: " + expected,
                        MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(timeNow, ImagesPath)).build());
            }
        }catch (Exception e){
            test.fail("Assertion field! \n" +
                    "Expected element did not match the acutal \n" +
                    "Found: " + actual + "\n" +
                    "Expected: " + expected + "\n" + e,
                    MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(timeNow, ImagesPath)).build());
        }
    }

    /**
     * Open BuyMe Website from the URL iside the XML file
     */
    public void enterBuyMeWebsite() {
        try {
            DriverSingleton.getDriverInstance().get(getData("URL"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        test.info("Open Buy Me website");
        DriverSingleton.getDriverInstance().manage().window().maximize();
        test.info("Maximize window");
    }

    /**
     * General Scroll
     * @param x length to scroll
     * @param y hight to scroll
     */
    public void scroll (int x, int y) {
        JavascriptExecutor js = (JavascriptExecutor)  DriverSingleton.getDriverInstance();
        js.executeScript("javascript:window.scrollBy("+x+","+y+")");
        test.info("Scroll up");
    }

    /**
     * Some 'Continue buttons contains the same Submit values. So this function can be use cross pages whenever there is
     * the same type of this button
     * This method always prints an error: org.openqa.selenium.ElementClickInterceptedException:
     * Even if its execute. So its surround with Try Catch
     */
    public void submit(){
        test.info("Click Continue/Submit");
        try{
            clickElement(By.xpath("//button[@type='submit']"));
        }catch (Exception e){}

    }

    /**
     * Wait and Get an element by locator and print error message + Screenshot if the element is not found.
     * @param locator element's locator
     * @return the actual element after waiting for it
     */
    private  WebElement getWebElement(By locator) {
        try{
            return WAIT.until(ExpectedConditions
                    .elementToBeClickable(DriverSingleton
                            .getDriverInstance()
                            .findElement(locator)));
        }catch (Exception e){
            test.fail("Failed to locate Element "+ locator.toString() + e,
                    MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(timeNow, ImagesPath)).build());
        }
       return null;
    }

    /**
     * Wait and Get an element form a list by locator and index. in case the element is not found,
     * the system will print error message + Screenshot if the element is not found.
     * @param locator element's locator
     * @param index element's index
     * @return the actual element after waiting for it
     */
    private  WebElement getWebElement(By locator, int index) {
        try {
            return WAIT.until(ExpectedConditions
                    .visibilityOf(DriverSingleton
                            .getDriverInstance()
                            .findElements(locator).get(index)));
        }catch (Exception e){
            test.fail("Failed to locate Elements "+ locator.toString() +
                            "At location: " + index + e,
                    MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(timeNow, ImagesPath)).build());
        }
        return null;
    }

    /**
     * Take a screenshot, give it a name, image type & Save location
     * (if the directory doesn't exist it will generate a new one)
     * @param imageName Image name
     * @param imagesPath Image path
     * @return Image Path + image name + Image type (.png)
     */
    protected static String takeScreenShot(String imageName, String imagesPath) {
        String file = imagesPath + imageName + ".png";
        TakesScreenshot takesScreenshot = (TakesScreenshot) DriverSingleton
                .getDriverInstance();
        File screenShotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(file);
        try {
            FileUtils.copyFile(screenShotFile, destinationFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return file;
    }

    /**
     * To avoid collection lots of images from previus runs, this method delete the content of the screenshot folder
     * @param folderPath
     */
    public void clearScreenShotsFolder(String folderPath){

            Path path = Paths.get(folderPath);

        try {
            Files.walkFileTree(path,
                    new SimpleFileVisitor<>() {

                        // delete directories or folders
                        @Override
                        public FileVisitResult postVisitDirectory(Path dir,
                                                                  IOException exc)
                                throws IOException {
                            Files.delete(dir);
                            System.out.printf("Directory is deleted : %s%n", dir);
                            return FileVisitResult.CONTINUE;
                        }

                        // delete files
                        @Override
                        public FileVisitResult visitFile(Path file,
                                                         BasicFileAttributes attrs)
                                throws IOException {
                            Files.delete(file);
                            System.out.printf("File is deleted : %s%n", file);
                            return FileVisitResult.CONTINUE;
                        }
                    }
            );
        } catch (IOException e) {}

    }
}
