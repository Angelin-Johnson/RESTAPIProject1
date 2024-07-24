package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        glue={"step.definitions"},
        dryRun=true ,   // to check whether all feature file steps have corresponding step definitions
        monochrome=true, // want console output from Cucumber in a readable format
        plugin={
               "json:build/cucumber-reports/cucumber.json",
                "json:build/cucumber-reports/rerun.txt"
        }
        )
public class TestRunner {

}
