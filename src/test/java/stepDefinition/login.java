    package stepDefinition;

    import Utils.BasicSetup;
    import io.cucumber.java.en.Given;
    import io.cucumber.java.en.When;
    import org.openqa.selenium.By;
    import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.WebElement;
    import org.openqa.selenium.support.ui.ExpectedConditions;
    import org.openqa.selenium.support.ui.WebDriverWait;
    import org.testng.Assert;

    import java.time.Duration;

    public class login {
    WebDriver driver;
        WebDriverWait wait;
        //WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        @Given("Open the browser and login into amazon {string}")
        public void openTheBrowserAndLoginIntoamazon(String url) throws InterruptedException {
            driver = BasicSetup.getDriver();
            BasicSetup.openurl(url);
            wait=new WebDriverWait(driver, Duration.ofSeconds(10));
            boolean homepageloaded=wait. until(ExpectedConditions.urlContains("amazon"));
            int maxRetries = 3;
            for (int i = 0; i < maxRetries; i++) {
                Thread.sleep(2000);
                BasicSetup.openurl(url);
                if (driver.getTitle().contains("Online Shopping")) {
                    break; // CAPTCHA didn't appear
                }
                System.out.println("CAPTCHA detected. Retrying...");
                Thread.sleep(2000); // wait before retry
            }
            Assert.assertTrue(homepageloaded,"Browser is not opened");

        }

        @When("User logs in with user google account")
        public void userLogsInWithUserGoogleAccount() {
            wait=new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Mobile Number']")));
            WebElement ele=driver.findElement(By.xpath("//input[contains(@placeholder,'Enter Mobile Number')]"));
            wait.until(d->ele.isDisplayed());
            ele.click();
            ele.sendKeys("9759517749");
            driver.findElement(By.xpath("//button[contains(@class,'capText font16')]/span")).click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
            driver.switchTo().activeElement();
            driver.findElement(By.xpath(".//input[contains(@placeholder,'Enter here')]")).click();

        }

        /*@Given("Open the browser and login into MMT {string}")
        public void openTheBrowserAndLoginIntoMMT(String arg0) {
        }*/
    }
