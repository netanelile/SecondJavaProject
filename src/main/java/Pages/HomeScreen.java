package Pages;

import Pages.HomeScreenOptions.Category;
import Pages.HomeScreenOptions.PriceOptions;
import Pages.HomeScreenOptions.Region;
import Unitls.BasePage;
import org.openqa.selenium.By;

public class HomeScreen extends BasePage {

    public void clickOnPriceOptions(PriceOptions option) {
        clickElementFromList(By.className("selected-name"), 0);
        clickElement(By.xpath("//*[contains(text(),'" + choosePriceOptions(option) + "')]"));
    }

    public void clickOnRegionOptions(Region option) {
        clickElementFromList(By.className("selected-name"), 1);
        clickElement(By.xpath("//*[contains(text(),'" + chooseRegionOptions(option) + "')]"));
    }

    public void clickOnCategoryOptions(Category option) {
        clickElementFromList(By.className("selected-name"), 2);
        clickElement(By.xpath("//*[contains(text(),'" + chooseCategoryOptions(option) + "')]"));
    }

    public void typeGiftToSearch(String giftToSearch) {
        sendKeysToElement(By.xpath("//*[contains(@placeholder,'איזו מתנה תרצו לחפש?')]"), giftToSearch);
    }

    public void clickFindMeGiftButton() {
        clickElement(By.xpath("//a[contains(@href, 'https://buyme.co.il/search')]"));
    }

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
