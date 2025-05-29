package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features ="src/test/resources/mytest1",
        glue = {"stepDefinition","Utils"},
        plugin = {"pretty", "html:target/cucumber-report.html", "json:target/cucumber.json"},
        monochrome = true,
        tags = "@test1"


)
public class TestRunner extends AbstractTestNGCucumberTests {
}