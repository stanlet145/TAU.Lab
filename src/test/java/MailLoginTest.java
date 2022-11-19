import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class MailLoginTest {
    private WebDriver webDriver;
    private String url;
    private String userName;
    private String userPassword;


    @Before
    public void setUp() {
        webDriver = new EdgeDriver();
        url = "https://profil.wp.pl/login/login.html";
        userName = "adas.adam2012@wp.pl";
        userPassword = "!Alj1234%";

    }

    @Test
    public void test() throws InterruptedException {
        webDriver.get(url);
        webDriver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[3]/div/button[2]"))
                .click();
        webDriver.
                findElement(By.id("login"))
                .sendKeys(userName);
        webDriver.findElement(By.id("password"))
                .sendKeys(userPassword);
        webDriver.findElement(By.xpath("//button[@type='submit']"))
                .click();
        Thread.sleep(1000);
        Assert.assertTrue(webDriver.findElement(By.xpath("//*[@id=\"folder-1\"]/div[2]")).isDisplayed());
    }

    @After
    public void tearDown() {
        webDriver.quit();
    }
}
