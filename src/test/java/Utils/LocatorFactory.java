package Utils;

import org.openqa.selenium.By;

public class LocatorFactory {

    public static String categorydropdown = "//*[contains(@id,'nav-search-dropdown-card')]/div/select/option[text()='%s']";
    /// /*[contains(@id,'nav-search-dropdown-card')]/div/select/option[text()=" + "'"+category +"'"+ "]
    public static By searchbox = By.xpath("//*[contains(@class,'nav-search-field ')]/input");
    public static By autocompleteContainer = By.cssSelector("div.autocomplete-results-container");
    public static By suggestiondropdown = By.cssSelector("div.s-suggestion");
    public static By FirstProductCss = By.cssSelector("span.a-size-medium");
    public static By appleStoreLink = By.xpath("//*[@id=\"nav-area\"]/div/div/div/nav/ol/li/a/span[text()='Apple']");
    public static By appleIndiastorelink = By.xpath(".//span[contains(@class,'_c3Rvc_cta_nEVi4')]/span[1]/span[1]/span[contains(text(),'Apple India')]");
    public static By applewatchmenuxpath = By.xpath("//span[text()='Apple Watch']");
    public static By applewatchdropdownxpath = By.xpath(".//div[contains(@class,'Navigation__navList__HrEra level2 Navigation__isOpen__aMUHj Navigation__subnav__CkMUI')]/ul");
    public static By AppleWatchSEGPS_Cellular = By.xpath(".//div[contains(@class,'Navigation__navList__HrEra level2 Navigation__isOpen__aMUHj Navigation__subnav__CkMUI')]/ul/li/a[contains(@href,'/stores/page/60C149FF-5A1A-413A-BDD7-43C7D37C6B26')]");
    public static By WatchImagexpath = By.xpath("//*[@id=\"4k2av5ugzs\"]/div/div/div/div/div/div");
    public static By watchImagequicklookbuttonxpath = By.xpath("//*[@id=\"4k2av5ugzs\"]/div/div/div/div/div/button");
    public static By quickLookModal = By.xpath("//span[contains(text(),'Quick look')]");
    public static By Firstwatchpopupshow = By.xpath(".//div[contains(@class,'ProductShowcase__showcase__vGSAb')]");
    public static By watchmodalTitle = By.xpath("//*[contains(@id,'asin-title')]/a");

}
