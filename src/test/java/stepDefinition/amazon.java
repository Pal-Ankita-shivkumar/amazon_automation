package stepDefinition;

import Utils.amazonUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class amazon {
    amazonUtil amazonutil = new amazonUtil();

    @When("User click on {string} from dropdown menu and search for {string}")
    public void userClickOnFromDropdownMenuAndTypeIPhone(String category, String searchvalue) throws InterruptedException {
        amazonutil.searchiPhone(category, searchvalue);

    }

    @And("User search for {string} and validate all suggestions")
    public void userSearchForAndValidateAllSuggestions(String searchvalue) {
        amazonutil.validatesuggestion(searchvalue);
    }

    @Then("Type again {string} and click on {string} variant")
    public void typeAgainAndClickOnVariant(String searchagain, String variant) {
        amazonutil.typeagainAndClickonspecificvariant(searchagain, variant);
    }

    @And("click on search product and validate new tab is opened")
    public void clickOnSearchProductAndValidateNewTabIsOpened() {
        amazonutil.validateNewtabisOpened();
    }

    @Then("Navigate to next tab and click on Visit the Apple Store link appears below Apple iPhone \"13\" \\(\"128\" GB) variant")
    public void navigateToNextTabAndClickOnVisitTheAppleStoreLinkAppearsBelowAppleIPhoneGBVariant() {
        amazonutil.visitAppleStore();
    }


    @And("Hover over the watch and verify quick look is displayed for the watch")
    public void hoverOverTheWatchAndVerifyQuickLookIsDisplayedForTheWatch() {
        amazonutil.hoverAndDisplayquickTool();
    }

    @And("Verify the newly opened modal is related to same product")
    public void verifyTheNewlyOpenedModalIsRelatedToSameProduct() {
        amazonutil.verifyOpenedModalwindow();
    }

    @And("Click on Apple Watch dropdown and select Apple Watch SE GPS + Cellular")
    public void clickOnAppleWatchDropdownAndSelectAppleWatchSEGPSCellular() {
        amazonutil.clickApplewatchDropdownAndselect();
    }







 /*WebDriver driver;
 Actions actions = new Actions(driver);
 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


 public  void searchiPhone(){
     driver= BasicSetup.getDriver();
     driver.findElement(By.xpath("//*[contains(@id,'nav-search-dropdown-card')]/div/select/option[text()='Electronics']"));
     WebElement searchbox=driver.findElement(By.xpath("//*[contains(@class,'nav-search-field ')]/input"));
     searchbox.sendKeys("iPhone 13");
     wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.autocomplete-results-container")));
     List<WebElement> suggestedrows=driver.findElements(By.cssSelector("div.s-suggestion"));
     System.out.println("Validating suggestion:");
     for(WebElement option:suggestedrows){
         String optiontext=option.getText();
         System.out.println(optiontext);
         if (!optiontext.toLowerCase().contains("iphone 13")) {
             throw new AssertionError("Non-matching suggestion: " + optiontext);
         }

     }
     searchbox.clear();
     searchbox.sendKeys("iPhone 13 128GB");
     wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.s-suggestion")));
     List<WebElement> variantSuggestions = driver.findElements(By.cssSelector("div.s-suggestion"));

     for (WebElement suggestion : variantSuggestions) {
         if (suggestion.getText().toLowerCase().contains("128gb")) {
             suggestion.click();
             break;
         }
     }

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
     WebElement appleStoreLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Visit the Apple Store")));
     appleStoreLink.click();

     WebElement watchDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Apple Watch']")));
     actions.moveToElement(watchDropdown).click().perform();
     WebElement seWatch = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'SE (GPS + Cellular)')]")));
     seWatch.click();

     WebElement watchImage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("img.s-image")));
     actions.moveToElement(watchImage).perform();

     WebElement quickLookModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Quick look')]")));
     if (quickLookModal.isDisplayed()) {
         System.out.println("Quick Look is displayed on hover.");
     }
     quickLookModal.click();
     WebElement modalTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
     if (!modalTitle.getText().toLowerCase().contains("se") && !modalTitle.getText().toLowerCase().contains("apple watch")) {
         throw new AssertionError("Modal does not match the hovered product.");
     } else {
         System.out.println("Modal product matches Quick Look product.");
     }

 }*/
}
