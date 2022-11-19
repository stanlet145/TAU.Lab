import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class InvestmentFundsSearchTest {

    private WebDriver webDriver;
    private String url;
    private String searchPhrase;

    @Before
    public void setUp() {
        webDriver = new EdgeDriver();
        url = "https://www.bankmillennium.pl/";
        searchPhrase = "fundusz";
    }

    @Test
    public void isInvestmentFundRedirectUrlPresentAfterSearch() {
        webDriver.get(url);
        webDriver.findElement(By.id("search"))
                .sendKeys(searchPhrase);
        webDriver.findElement(By.id("search"))
                .sendKeys(Keys.RETURN);
        Assert.assertTrue(webDriver.findElement(By.xpath("//a[contains(@href,'https://www.bankmillennium.pl/klienci-indywidualni/inwestycje')]"))
                .isDisplayed());
    }

    @After
    public void tearDown() {
        // webDriver.quit();
    }
}
