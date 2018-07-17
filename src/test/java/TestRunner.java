import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src\test\resources\feature",
        glue = {"classpath:com.qa.sg"},
        plugin = {"pretty", "html:target/cucumber-html-report"},
        tags = {"@AcceptanceCriteria2"}

)

public class TestRunner {
}
