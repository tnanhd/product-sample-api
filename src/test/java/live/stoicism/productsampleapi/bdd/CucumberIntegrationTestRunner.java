package live.stoicism.productsampleapi.bdd;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        plugin = {"pretty"},
        glue = {"live.stoicism.productsampleapi.bdd.cucumberglue"}
)
public class CucumberIntegrationTestRunner {
    // TODO: Consider adding cucumber-report [https://gitlab.com/jamietanna/cucumber-reporting-plugin]
}
