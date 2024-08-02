package Runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "StepDefinitions",
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber-pretty.html",
                "json:target/cucumber-reports/CucumberTestReport.json",
                "timeline:target/test-output-thread/"
        },
        tags = "@test-staffinc"
)

public class TestRunner extends AbstractTestNGCucumberTests {
        WebDriver driver;
        @BeforeSuite
        public void beforeSuite() {
                System.out.println("================ BEFORE SUITE ================");
        }

        @AfterSuite
        public void afterSuite() {
                driver.close();
                driver.quit();
        }
}
