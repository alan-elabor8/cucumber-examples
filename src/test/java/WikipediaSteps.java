import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertFalse;

import static org.assertj.core.api.Assertions.assertThat;

public class WikipediaSteps {

    private WebDriver driver;

    @Before
    public void before() {
        driver = new ChromeDriver();
        driver.navigate().to("http://en.wikipedia.org");
    }

    @After
    public void after() {
        driver.quit();
    }

    @Given("^Enter search term '(.*?)'$")
    public void searchFor(String searchTerm) {
        WebElement searchField = driver.findElement(By.id("searchInput"));
        searchField.sendKeys(searchTerm);
    }

    @When("^Do search$")
    public void clickSearchButton() {
        WebElement searchButton = driver.findElement(By.id("searchButton"));
        searchButton.click();
    }

    @Then("^Single result is shown for '(.*?)'$")
    public void assertSingleResult(String searchResult) {
        WebElement results = driver
                .findElement(By.cssSelector("div#mw-content-text.mw-content-ltr p"));
        System.out.println(results.getText());
        System.out.println(results.getText().contains(searchResult + " usually refers to:"));

        assertThat(results.getText().contains(searchResult + " usually refers to:")).isFalse();
        assertFalse(results.getText().contains(searchResult + " usually refers to:"));

        assertThat(results.getText().startsWith(searchResult)).isTrue();
        assertTrue(results.getText().startsWith(searchResult));
    }
}