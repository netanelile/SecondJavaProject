package Pages;

import Pages.HomeScreenOptions.Category;
import Pages.HomeScreenOptions.PriceOptions;
import Pages.HomeScreenOptions.Region;
import Uitls.BasePage;
import org.openqa.selenium.By;

/**
 * This class contains teps functions for BuyMe Home screen - The first screen when entering the website.
 */
public class HomeScreen extends BasePage {

    /**
     * Open the Price Drop Menu,
     * Click on a specific option from the Price Drop Menu
     * @param option Price possible option taken from the Price Enum
     */
    public void clickOnPriceOptions(PriceOptions option) {
        test.info("Click on Price Options: " + choosePriceOptions(option));
        clickElementFromList(By.className("selected-name"), 0);
        clickElement(By.xpath("//*[contains(text(),'" + choosePriceOptions(option) + "')]"));
    }

    /**
     * Open the Region Drop Menu,
     * Click on a specific option from the Region Drop Menu
     * @param option Region possible option taken from the Region Enum
     */
    public void clickOnRegionOptions(Region option) {
        test.info("Click on Price Options: " + chooseRegionOptions(option));
        clickElementFromList(By.className("selected-name"), 1);
        clickElement(By.xpath("//*[contains(text(),'" + chooseRegionOptions(option) + "')]"));
    }

    /**
     * Open the Categories Drop Menu,
     * Click on a specific option from the Categories Drop Menu
     * @param option Category possible option taken from the Category Enum
     */
    public void clickOnCategoryOptions(Category option) {
        test.info("Click on Price Options: " + chooseCategoryOptions(option));
        clickElementFromList(By.className("selected-name"), 2);
        clickElement(By.xpath("//*[contains(text(),'" + chooseCategoryOptions(option) + "')]"));
    }

    /**
     * After complete choosing of the possible option that helps the user to find a gif, he can Click on the 'Find be a
     * gift' button that will forward him to the search results page.
     * This function perform a Click on Find me a Gift Button
     */
    public void clickFindMeGiftButton() {
        test.info("Click on Find Me a Gift Button");
        clickElement(By.xpath("//a[contains(@href, 'https://buyme.co.il/search')]"));
    }

    /**
     * Price Range picker. When choosing an Enum Price that represent the content of the drop menu,
     * The system will return the actual element value of the chosen option.
     * @param option Price rage to choose from the Enum (tha represent the content of the 'Price' drop menu)
     * @return The String of the Actual Event so the system will know on which element to click.
     */
    private String choosePriceOptions(PriceOptions option){
        return switch (option) {
            case till99 -> "עד 99 ש\"ח";
            case between100_199 -> "100-199 ש\"ח";
            case between200_299 -> "200-299 ש\"ח";
            case between300_499 -> "300-499 ש\"ח";
            case between500_750 -> "500-750 ש\"ח";
            case above750 -> "מעל 750 ש\"ח";
        };
    }

    /**
     * Region picker. When choosing an Enum Region that represent the content of the drop menu,
     * The system will return the actual element value of the chosen option.
     * @param option Region to choose from the Enum (tha represent the content of the 'Region' drop menu)
     * @return The String of the Actual Event so the system will know on which element to click.
     */
    private String chooseRegionOptions(Region option){
        return switch (option) {
            case TEL_AVIV -> "ת\"א והסביבה";
            case CENTER -> "מרכז";
            case NORTH -> "צפון";
            case SOUTH -> "דרום";
            case JERUSALEM -> "ירושלים";
            case HAIFA -> "חיפה";
            case SHARON -> "שרון";
            case EILAT -> "אילת";
            case NATIONAL -> "פריסה ארצית";
        };
    }

    /**
     * Categories picker. When choosing an Enum Category that represent the content of the drop menu,
     * The system will return the actual element value of the chosen option.
     * @param option Category to choose from the Enum (tha represent the content of the 'Categories' drop menu)
     * @return The String of the Actual Event so the system will know on which element to click.
     */
    private String chooseCategoryOptions(Category option){
        return switch (option) {
            case BEST2022 -> "המתנות האהובות של 2022";
            case GIFT_TO_HOUSE -> "מתנות עד הבית";
            case GIFT_CARD_FASHION -> "גיפט קארד למותגי אופנה";
            case GIFT_CARD_BREAKFAST -> "גיפט קארד לארוחת בוקר ובתי קפה";
            case GIFT_CARD_SHEFF -> "גיפט קארד למסעדות שף";
            case GIFT_CARD_MOTHER_AND_BABY -> "גיפט קארד למתנות ליולדת וצעצועים";
            case GIFT_CARD_SPA -> "גיפט קארד לבתי ספא";
            case GIFT_CARD_RESTAURANT -> "גיפט קארד למסעדות";
            case GIFT_CARD_LOCAL_GIFT -> "מתנה מקומית";
            case GIFT_CARD_CULINARY -> "גיפט קארד למתנות קולינריות";
            case GIFT_CARD_HOME_KITCHEN_GADGETS -> "גיפט קארד לבית, מטבח וגאדג'טים";
            case GIFT_CARD_HEALTH_SPORT_EXTREME -> "גיפט קארד לבריאות, ספורט ואקסטרים";
            case GIFT_CARD_MUTUAL_EXPERIENCES -> "חוויות משותפות";
            case GIFT_CARD_VOCATIONS_HOTELS -> "נופש ומלונות";
            case GIFT_CARD_CULTURE -> "גיפט קארד לתרבות ופנאי";
            case GIFT_CARD_BEAUTY -> "גיפט קארד ליופי וטיפוח";
            case GIFT_CARD_ESCAPE_ROOMS -> "גיפט קארד לחדרי בריחה";
            case GIFT_CARD_WORKSHOP -> "סדנאות והעשרה";
        };
    }

}
