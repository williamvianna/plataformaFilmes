package runner;

import Utils.RestUtils;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        tags = "",
        glue = "steps",
        plugin = {"json:target/reports/cucumberReports.json", "pretty"},
        snippets = CucumberOptions.SnippetType.CAMELCASE
)

public class RunnerTest {

    @BeforeClass
    public static void beforeClass() throws Exception {
        RestUtils.setBaseURI("http://localhost:8181/");
    }
}
