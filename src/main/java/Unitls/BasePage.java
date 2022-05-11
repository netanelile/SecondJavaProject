package Unitls;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {

    public static WebDriverWait WAIT = new WebDriverWait(DriverSingleton.getDriverInstance(),
            Duration.ofSeconds(Constants.WAIT_DURATION));

    public void clickElement(By locator) {
        getWebElement(locator).click();
    }

    public void clickElementFromList(By locator, int index){
        getWebElement(locator, index).click();
    }

    public void sendKeysToElement(By locator, String text) {
        getWebElement(locator).sendKeys(text);
    }

    public String getTextFromElement(By locator) {
        return getWebElement(locator).getText();
    }

    public boolean isElementDisplay(By locator){
        return getWebElement(locator).isDisplayed();
    }

    public void enterBuyMeWebsite(){
        DriverSingleton.getDriverInstance().get(Constants.BUY_ME_URL);
        DriverSingleton.getDriverInstance().manage().window().maximize();
    }

    public void scroll (int x, int y){
        JavascriptExecutor js = (JavascriptExecutor)  DriverSingleton.getDriverInstance();
        js.executeScript("javascript:window.scrollBy("+x+","+y+")");
    }

    public void submit(){
        clickElement(By.xpath("//button[@type='submit']"));
    }

    private  WebElement getWebElement(By locator) {
        return WAIT.until(ExpectedConditions
                .visibilityOf(DriverSingleton
                        .getDriverInstance()
                        .findElement(locator)));
    }

    private  WebElement getWebElement(By locator, int index) {
        return WAIT.until(ExpectedConditions
                .visibilityOf(DriverSingleton
                        .getDriverInstance()
                        .findElements(locator).get(index)));
    }
}
