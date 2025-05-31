package Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class ExtentReportLogger {
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    private static ExtentReports extent;
    public static String base64Image;

    public static void logInfo(String message) {
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, message);
    }

    public static void logInfo1(String message) {
        getTest().log(Status.INFO, message);
        ;
    }

    public static void logPass(String message) {
        ExtentCucumberAdapter.getCurrentStep().log(Status.PASS, message);
    }

    public static void logFail(String message) {
        ExtentCucumberAdapter.getCurrentStep().log(Status.FAIL, message);
    }

    public static ExtentTest getTest() {
        return extentTest.get();
    }

    public static void setTest(ExtentTest test) {
        extentTest.set(test);
    }

    private static void createInstance(String filePath) {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(filePath);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    public static ExtentReports getInstance() {
        if (extent == null) {
            createInstance("test-output/extent.html");
        }
        return extent;
    }

    public static void logSkip(String message) {
        ExtentCucumberAdapter.getCurrentStep().log(Status.SKIP, message);
    }

    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String path = System.getProperty("user.dir") + "/screenshots/" + screenshotName + "_" + timestamp + ".png";

        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            byte[] fileContent = Files.readAllBytes(src.toPath());
            base64Image = Base64.getEncoder().encodeToString(fileContent);
            FileUtils.copyFile(src, new File(path));
        } catch (IOException e) {
            System.out.println("⚠️ Screenshot capture failed: " + e.getMessage());
        }
        return path;
    }

    /*public static void attachScreenshot(WebDriver driver, ExtentTest test, String screenshotName, String stepMessage) {
        String path = captureScreenshot(driver, screenshotName);
        test.info(stepMessage,
                MediaEntityBuilder.createScreenCaptureFromPath(path).build());
    }*/
    public static void flush() {
        ExtentReportLogger.getInstance().flush();
    }
}