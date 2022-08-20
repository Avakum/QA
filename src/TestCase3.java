import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestCase3 {
    WebDriver driver = new ChromeDriver();
    Actions action = new Actions(driver);
    String klasa = null;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/bin/chromedriver");
        driver.get("https://webdriveruniversity.com/To-Do-List/index.html");
        System.out.println("Otvaram sajt");
    }

    @Test(priority = 1)
    public void websitecheck() {
        String text = driver.findElement(By.xpath("//*[@id=\"container\"]/h1")).getText();
        System.out.println(text + " " + "ISPRAVAN");
        Assert.assertEquals(text, "TO-DO LIST");
    }

    @Test(priority = 2)
    public void clicktest() throws InterruptedException {
        driver.findElement(By.id("plus-icon")).click();
        do {
            TimeUnit.SECONDS.sleep(1);
            klasa = driver.findElement(By.xpath("//*[@id=\"container\"]/input")).getAttribute("style");
        }
        while (klasa.equals("display: none"));
        System.out.println(klasa);
        Assert.assertEquals(klasa, "display: none;");
    }

    @Test(priority = 3)
    public void text_add() {
        driver.navigate().refresh();
        driver.findElement(By.xpath("//*[@id=\"container\"]/input")).sendKeys("Check tests", Keys.ENTER);
        String text = driver.findElement(By.xpath("//*[@id=\"container\"]/ul/li[4]")).getText();
        Assert.assertEquals(text, "Check tests");
    }

    @Test(priority = 4)
    public void text_click() {
        driver.findElement(By.xpath("//*[@id=\"container\"]/ul/li[4]")).click();
        String klasa = driver.findElement(By.xpath("//*[@id=\"container\"]/ul/li[4]")).getAttribute("class");
        System.out.println(klasa);
        Assert.assertEquals(klasa, "completed");
    }
    @Test(priority = 5)
    public void delete() throws InterruptedException {
        WebElement container = driver.findElement(By.xpath("//*[@id=\"container\"]/ul/li[4]"));
        action.moveToElement(container);
        driver.findElement(By.xpath("//*[@id=\"container\"]/ul/li[4]/span/i")).click();
        TimeUnit.SECONDS.sleep(2);
        WebElement deleteLink = null;
        try {
            deleteLink = driver.findElement(By.xpath("//*[@id=\"container\"]/ul/li[4]"));
        } catch (NoSuchElementException e) {

        }
        Assert.assertTrue(deleteLink == null);
        driver.quit();
    }
}
