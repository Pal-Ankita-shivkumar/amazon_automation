package Utils;

import io.cucumber.java.Scenario;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class amazonUtil {

 WebDriver driver=BasicSetup.getDriver();
 Actions actions = new Actions(driver);
 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


 public  void searchiPhone(String category,String searchval) throws InterruptedException {
     //driver = BasicSetup.getDriver();
     driver.findElement(By.xpath("//*[contains(@id,'nav-search-dropdown-card')]/div/select/option[text()=" + "'"+category +"'"+ "]"));
     WebElement searchbox = driver.findElement(By.xpath("//*[contains(@class,'nav-search-field ')]/input"));
     searchbox.sendKeys(searchval);
    // ExtentReportManager.logPass("Expected Iphone is searched");

 }
 public void validatesuggestion(String searchvalue) {
     wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.autocomplete-results-container")));
     List<WebElement> suggestedrows = driver.findElements(By.cssSelector("div.s-suggestion"));
     System.out.println("Validating suggestion:");
     for (WebElement option : suggestedrows) {
         String optiontext = option.getText();
         System.out.println(optiontext);
         if (!optiontext.toLowerCase().contains(searchvalue)) {
             throw new AssertionError("Non-matching suggestion: " + optiontext);
         }

     }
 }
  public void typeagainAndClickonspecificvariant(String secondsearchval,String variant) {
     driver.navigate().refresh();
      WebElement searchbox = driver.findElement(By.xpath("//*[contains(@class,'nav-search-field ')]/input"));
      searchbox.clear();
      searchbox.sendKeys(secondsearchval);
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.s-suggestion")));
      List<WebElement> variantSuggestions = driver.findElements(By.cssSelector("div.s-suggestion"));

      for (WebElement suggestion : variantSuggestions) {
         //wait.until(ExpectedConditions.visibilityOfElementLocated())
          if (suggestion.getText().toLowerCase().contains(variant)) {
              suggestion.click();
              break;
          }
      }
  }
   public void validateNewtabisOpened() {
       wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.a-size-medium")));
       WebElement firstProduct = driver.findElement(By.cssSelector("span.a-size-medium"));
       String parentWindow = driver.getWindowHandle();
       firstProduct.click();

       ArrayList<String> windows = new ArrayList<>(driver.getWindowHandles());
       for (String window : windows) {
           if (!window.equals(parentWindow)) {
               driver.switchTo().window(window);
               break;
           }
       }
   }
   public void visitAppleStore() {
       WebElement appleStoreLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"nav-area\"]/div/div/div/nav/ol/li/a/span[text()='Apple']")));
       appleStoreLink.click();
   }
   public void clickApplewatchDropdownAndselect() {

       WebElement watchDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Apple Watch']")));
       //Actions actions = new Actions(driver);
       actions.moveToElement(watchDropdown).click().perform();
       System.out.println("Watch dropdown is clicked");
       //WebElement watchdropdownwindow= driver.findElement(By.xpath());
       //Assert.assertTrue();
       WebElement modalTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[contains(@class,'Navigation__navList__HrEra level2 Navigation__isOpen__aMUHj Navigation__subnav__CkMUI')]/ul")));
       System.out.println("Modal window is displayed");
       WebElement seWatch = driver.findElement(By.xpath(".//div[contains(@class,'Navigation__navList__HrEra level2 Navigation__isOpen__aMUHj Navigation__subnav__CkMUI')]/ul/li/a[contains(@href,'/stores/page/60C149FF-5A1A-413A-BDD7-43C7D37C6B26')]"));
       ///a/span[text()='Apple Watch SE (GPS + Cellular)']")));
       seWatch.click();
       System.out.println("Expected watch is selected");
   }
   public void hoverAndDisplayquickTool() {

       WebElement watchImage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"4k2av5ugzs\"]/div/div/div/div/div/button")));
       //Actions actions = new Actions(driver);
       actions.moveToElement(watchImage).perform();

       WebElement quickLookModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Quick look')]")));
       if (quickLookModal.isDisplayed()) {
           System.out.println("Quick Look is displayed on hover.");
       }
       quickLookModal.click();
   }
   public void verifyOpenedModalwindow(){
       wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[contains(@class,'ProductShowcase__showcase__vGSAb')]")));
     WebElement modalTitle =driver.findElement(By.xpath("//*[contains(@id,'asin-title')]/a"));
     if (!modalTitle.getText().toLowerCase().contains("GPS + Cellular") && !modalTitle.getText().toLowerCase().contains("apple watch")) {
         throw new AssertionError("Modal does not match the hovered product.");
     } else {
         System.out.println("Modal product matches Quick Look product.");
     }

 }
}
