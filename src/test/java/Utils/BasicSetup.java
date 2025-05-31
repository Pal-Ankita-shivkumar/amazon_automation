package Utils;


import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import io.cucumber.java.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;

public class BasicSetup {
    private static WebDriver driver;


    @Before
    public void initialiseDriver(Scenario scenario) {
        ExtentTest test = ExtentReportLogger.getInstance().createTest("Scenario: " + scenario.getName());
        ExtentReportLogger.setTest(test);
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
            options.addArguments("--start-maximized");
            options.addArguments("--disable-blink-features=AutomationControlled");
            options.addArguments("--disable-popup-blocking");
            options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
            options.setExperimentalOption("useAutomationExtension", false);
            driver = new ChromeDriver(options);
            ((JavascriptExecutor) driver).executeScript("Object.defineProperty(navigator, 'webdriver', {get: () => undefined})");

        }
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void openurl(String url) {
        getDriver().get(url);

    }

    // @After
    public void closeDriver() {
        if (driver != null) {
            driver.quit();
        }

    }

    @After
    public void afterScenario(Scenario scenario) throws IOException {
        WebDriver driver = getDriver();
        try {
            if (scenario.isFailed()) {
                String path = ExtentReportLogger.captureScreenshot(driver, scenario.getName().replaceAll(" ", "_"));
                ExtentCucumberAdapter.addTestStepLog("❌ Scenario failed: " + scenario.getName());
                ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(path);
            } else {
                ExtentCucumberAdapter.addTestStepLog("✅ Scenario passed: " + scenario.getName());
            }
        } finally {
            closeDriver();
            ExtentReportLogger.flush();
        }


    }
}
