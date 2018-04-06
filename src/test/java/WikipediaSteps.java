import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.startsWith;

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

        //AssertJ
        assertThat(results.getText()).doesNotContain(" usually refers to:");
        assertThat(results.getText()).startsWith(searchResult);

        //Hamcrest
        assertThat(results.getText(), not(containsString(" usually refers to:")));
        assertThat(results.getText(), startsWith(searchResult));

        //Junit
        assertFalse(results.getText().contains(searchResult + " usually refers to:"));
        assertTrue(results.getText().startsWith(searchResult));
    }
}