package Uitls;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.time.Duration;

/**
 * Selenium Driver Singleton. This driver will be initate only once in this project.
 * The type of the Browser will be determent by the XML file
 */
public class DriverSingleton extends BasePage{

    private static WebDriver driver;

    /**
     * @return driver
     */
    public static WebDriver getDriverInstance()  {

        if(driver == null){

            if(getBrowser().equals("Chrome")){
                System.setProperty("webdriver.chrome.driver", Constants.CHROMEDRIVER_PATH);
                ChromeOptions options = new ChromeOptions();
                driver = new ChromeDriver(options);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.WAIT_DURATION));
                options.addArguments("start-maximized");
            }else if(getBrowser().equals("FF")){
                System.setProperty("webdriver.decko.driver", Constants.FIRE_FOX_PATH);
                driver = new FirefoxDriver();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.WAIT_DURATION));
            }
        }
        return driver;
    }
}
