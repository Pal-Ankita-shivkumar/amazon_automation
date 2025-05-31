package Utils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;

import static Utils.ExtentReportLogger.*;

public class loginUtil {
    WebDriver driver;
    WebDriverWait wait;

    //WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
    @Given("Open the browser and login into amazon {string}")
    public void openTheBrowserAndLoginIntoamazon(String url) throws InterruptedException {
        driver = BasicSetup.getDriver();
        Thread.sleep(2000);
        BasicSetup.openurl(url);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        boolean homepageloaded = driver.getTitle().contains("Online Shopping");
        int maxRetries = 3;
        if (!homepageloaded) {
            for (int i = 0; i < maxRetries; i++) {
                Thread.sleep(2000);
                BasicSetup.openurl(url);
                if (driver.getTitle().contains("Online Shopping")) {
                    break; // CAPTCHA didn't appear
                }
                System.out.println("CAPTCHA detected. Retrying...");
                logInfo("CAPTCHA detected. Retrying...");
                Thread.sleep(2000); // wait before retry
            }
                    /*((JavascriptExecutor) driver).executeScript("window.open()");

                    ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
                    driver.switchTo().window(tabs.get(1));
                    driver.get(url);*/
            wait.until(ExpectedConditions.titleContains("Online Shopping"));
            homepageloaded = driver.getTitle().contains("Online Shopping");
            Assert.assertTrue(homepageloaded, "Website is not opened");
            logInfo("The website is opened: " + url);

        }
    }

    @When("User logs in with user google account")
    public void userLogsInWithUserGoogleAccount() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Mobile Number']")));
        WebElement ele = driver.findElement(By.xpath("//input[contains(@placeholder,'Enter Mobile Number')]"));
        wait.until(d -> ele.isDisplayed());
        ele.click();
        ele.sendKeys("");
        driver.findElement(By.xpath("//button[contains(@class,'capText font16')]/span")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.switchTo().activeElement();
        driver.findElement(By.xpath(".//input[contains(@placeholder,'Enter here')]")).click();

    }

        /*@Given("Open the browser and login into MMT {string}")
        public void openTheBrowserAndLoginIntoMMT(String arg0) {
        }*/
}
