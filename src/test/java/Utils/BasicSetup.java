package Utils;


import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BasicSetup {
    private static WebDriver driver;


@Before
    public void initialiseDriver() {
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

        }
    }
    public static WebDriver getDriver() {
        return driver;
        }

    public static void openurl(String url) {
        getDriver().get(url);

    }

//@After
    public  void closeDriver() {
        if (driver != null) {
            driver.quit();
        }

}
}
