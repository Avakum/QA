import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class TestCase2 {
    WebDriver driver = new ChromeDriver();
    Actions actions = new Actions(driver);

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/bin/chromedriver");
        driver.get("https://demoqa.com/radio-button");
        System.out.println("Otvaram sajt");
    }
    @Test(description = "Otvaramo sajt", priority = 1)
    public void OtvaranjeRadioSajta(){
        String tekst = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[1]/div")).getText();
        Assert.assertEquals(tekst, "Radio Button");
        System.out.println("Proveravam validnost otvorenog sajta");
    }
    @Test(description = "Proveravamo Yes dugme", priority = 2)
    public void yes(){
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[2]/div[2]/label")).click();
        String yesodgovor = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div[2]/div[2]/p/span")).getText();
        Assert.assertEquals(yesodgovor, "Yes");
        System.out.println("Proveravam Yes dugme");
    }
    @Test(description = "Proveravamo Impressive dugme", priority = 3)
    public void impressive(){
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[2]/div[3]/label")).click();
        String impressiveodgovor = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div[2]/div[2]/p/span")).getText();
        Assert.assertEquals(impressiveodgovor, "Impressive");
    }
    @Test(description = "Proveravamo No dugme", priority = 4)
    public void no() throws InterruptedException{
        boolean nodugme = driver.findElement(By.id("noRadio")).isEnabled();
        Assert.assertFalse(nodugme);
        driver.quit();
    }


}
