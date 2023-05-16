package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

//plugin= {"pretty", "html:target/cucumber-reports.html"}

@RunWith(Cucumber.class)
@CucumberOptions(
		features=".//Features/",
		glue="stepDefinitions",
		dryRun=false,
		monochrome = false,		
		plugin= {"pretty", "html:test-output.html"},
		tags= "@Sanity or @Regression"
		)

public class TestRun {

}
