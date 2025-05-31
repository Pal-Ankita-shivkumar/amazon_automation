package Utils;

import io.cucumber.java.Scenario;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.lang.ref.SoftReference;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static Utils.ExtentReportLogger.*;

public class amazonUtil {

    WebDriver driver = BasicSetup.getDriver();
    Actions actions = new Actions(driver);
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    //LocatorFactory factory=new LocatorFactory();

    public void searchiPhone(String category, String searchval) throws InterruptedException {
        String catdropdown = LocatorFactory.categorydropdown;
        driver.findElement(By.xpath(String.format(catdropdown, category))).click();

        WebElement searchbox = driver.findElement(LocatorFactory.searchbox);
        searchbox.sendKeys(searchval);
        logPass("Expected Iphone is searched");

    }

    public void validatesuggestion(String searchvalue) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(LocatorFactory.autocompleteContainer));
        List<WebElement> suggestedrows = driver.findElements(LocatorFactory.suggestiondropdown);
        System.out.println("Validating suggestion:");
        logInfo("Validating suggestion:");
        for (WebElement option : suggestedrows) {
            String optiontext = option.getText();
            System.out.println(optiontext);
            logInfo(optiontext);
            if (!optiontext.toLowerCase().contains(searchvalue)) {
                throw new AssertionError("Non-matching suggestion: " + optiontext);
            }

        }
    }

    public void typeagainAndClickonspecificvariant(String secondsearchval, String variant) {
        driver.navigate().refresh();
        WebElement searchbox = driver.findElement(LocatorFactory.searchbox);
        searchbox.clear();
        searchbox.sendKeys(secondsearchval);
        wait.until(ExpectedConditions.visibilityOfElementLocated(LocatorFactory.suggestiondropdown));
        List<WebElement> variantSuggestions = driver.findElements(LocatorFactory.suggestiondropdown);

        for (WebElement suggestion : variantSuggestions) {
            //wait.until(ExpectedConditions.visibilityOfElementLocated())
            if (suggestion.getText().toLowerCase().contains(variant)) {
                suggestion.click();
                logPass("The selected variant is clicked and opened");
                break;
            }
        }
    }

    public void validateNewtabisOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(LocatorFactory.FirstProductCss));
        WebElement firstProduct = driver.findElement(LocatorFactory.FirstProductCss);
        String parentWindow = driver.getWindowHandle();
        firstProduct.click();

        ArrayList<String> windows = new ArrayList<>(driver.getWindowHandles());
        SoftAssert softassert = new SoftAssert();
        softassert.assertTrue(windows.size() > 1, "New tab was not launched. The search result appeared on the same window tab.Moving forward... ");
        //System.out.println("New tab is opened to launch the searched product");
        //logPass("New tab is opened to launch the searched product");
        for (String window : windows) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                logInfo("The tab is switched");
                break;
            } else {
                logInfo("Since the new tab was not launched.Hence,wont be able to switch to new tab.");
            }
        }

    }

    public void visitAppleStore() {
        WebElement appleStoreLink = //driver.findElement(LocatorFactory.appleStoreLink);
                wait.until(ExpectedConditions.visibilityOfElementLocated(LocatorFactory.appleStoreLink));
        appleStoreLink.click();
        logInfo("Apple store link is clicked");
    }

    public void clickApplewatchDropdownAndselect() {

        WebElement watchDropdown = wait.until(ExpectedConditions.elementToBeClickable(LocatorFactory.applewatchmenuxpath));
        actions.moveToElement(watchDropdown).click().perform();
        System.out.println("Watch dropdown is clicked");
        logInfo("Watch dropdown is clicked");
        WebElement modalTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(LocatorFactory.applewatchdropdownxpath));
        System.out.println("Modal window is displayed");
        logPass("Modal window is displayed");
        WebElement seWatch = driver.findElement(LocatorFactory.AppleWatchSEGPS_Cellular);
        seWatch.click();
        System.out.println("Expected watch is selected");
        logPass("Expected watch is selected");
    }

    public void hoverAndDisplayquickTool() {

        WebElement watchImage = driver.findElement(LocatorFactory.WatchImagexpath);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", watchImage);
        logInfo("Page is scrolled to have the view of the watch");
        actions.moveToElement(watchImage).perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(LocatorFactory.watchImagequicklookbuttonxpath));
        WebElement quickLookModal = driver.findElement(LocatorFactory.quickLookModal);
        if (quickLookModal.isDisplayed()) {
            System.out.println("Quick Look is displayed on hover.");
            logPass("Quick Look is displayed on hover.");
        }
        quickLookModal.click();
    }

    public void verifyOpenedModalwindow() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(LocatorFactory.Firstwatchpopupshow));
        WebElement modalTitle = driver.findElement(LocatorFactory.watchmodalTitle);
        if (!modalTitle.getText().toLowerCase().contains("GPS + Cellular") && !modalTitle.getText().toLowerCase().contains("apple watch")) {
            throw new AssertionError("Modal does not match the hovered product.");
        } else {
            System.out.println("Modal product matches Quick Look product.");
            logPass("Modal product matches Quick Look product.");
        }

    }
}
