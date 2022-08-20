import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestCase1 {
    WebDriver driver = new ChromeDriver();

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/bin/chromedriver");
        driver.get("https://demoqa.com/text-box");
        System.out.println("Otvaram sajt");
        driver.manage().window().minimize();
    }
    @Test(description = "Checking link", priority = 1)
    public void linkcheck(){
        String header = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[1]/div")).getText();
        Assert.assertEquals(header, "Text Box");
    }
    @Test(description = "Checking Full name box", priority = 2)
    public void full_name_box_check(){
        driver.navigate().refresh();
        driver.findElement(By.id("userName")).sendKeys("Petar Petrovic");
        driver.findElement(By.xpath("//*[@id=\"submit\"]")).click();
        String text = driver.findElement(By.xpath("//*[@id=\"name\"]")).getText();
        Assert.assertEquals(text,"Name:Petar Petrovic");
    }

    @Test(description = "Checking full name box with forbiden characters", priority = 3)
    public void full_name_box_check_f(){
        driver.navigate().refresh();
        driver.findElement(By.id("userName")).sendKeys("Petar Pet^ovic");
        driver.findElement(By.xpath("//*[@id=\"submit\"]")).click();
        String text = driver.findElement(By.xpath("//*[@id=\"name\"]")).getText();
        if(text.equals("Name:Petar Pet^ovic"))
        {
            Assert.fail("Test nije prosao");
            System.out.println("Test nije prosao");
        }
        else{
            Assert.assertTrue(true);
            System.out.println("Test je prosao");
        }
    }
    @Test(description = "Checking mailbox without @", priority = 4)
    public void mail_box_check(){
        driver.navigate().refresh();
        WebElement mailbox = driver.findElement(By.id("userEmail"));
        mailbox.sendKeys("petar232");
        driver.findElement(By.xpath("//*[@id=\"submit\"]")).click();
        String klasa = mailbox.getAttribute("class");
        System.out.println(klasa);
        Assert.assertEquals(klasa, "mr-sm-2 field-error form-control");
    }
    @Test(description = "Checking mailbox with @", priority = 5)
    public void mail_box_checkr(){
        driver.navigate().refresh();
        WebElement mailbox = driver.findElement(By.id("userEmail"));
        mailbox.sendKeys("petar.petrovic@gmail.com");
        driver.findElement(By.xpath("//*[@id=\"submit\"]")).click();
        String klasa = mailbox.getAttribute("class");
        System.out.println(klasa);
        Assert.assertEquals(klasa, "mr-sm-2 form-control");

    }
    @Test(description = "Checking Current adress", priority = 6)
    public void currentadress_test(){
        driver.navigate().refresh();
        driver.findElement(By.id("currentAddress")).sendKeys("Petra Petrovica br.65");
        driver.findElement(By.xpath("//*[@id=\"submit\"]")).click();
        String tekst = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[2]/form/div[6]/div/p")).getText();
        Assert.assertEquals(tekst, "Current Address :Petra Petrovica br.65");
    }
    @Test(description = "Checking Permanent Address", priority = 7)
    public void permanentadress_test(){
        driver.navigate().refresh();
        driver.findElement(By.id("permanentAddress")).sendKeys("Lazara Lazarevica br.55");
        driver.findElement(By.xpath("//*[@id=\"submit\"]")).click();
        String tekst = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[2]/form/div[6]/div/p")).getText();
        Assert.assertEquals(tekst, "Permananet Address :Lazara Lazarevica br.55");
    }
    @Test(description = "Whole page test", priority = 8)
    public void wholepage(){
        driver.navigate().refresh();
        WebElement mailbox = driver.findElement(By.id("userEmail"));
        driver.findElement(By.id("userName")).sendKeys("Petar Petrovic");
        mailbox.sendKeys("petar.petrovic@gmail.com");
        driver.findElement(By.id("currentAddress")).sendKeys("Petra Petrovica br.65");
        driver.findElement(By.id("permanentAddress")).sendKeys("Lazara Lazarevica br.55");
        driver.findElement(By.xpath("//*[@id=\"submit\"]")).click();
        String ceotekst = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[2]/form/div[6]")).getText();
        Assert.assertEquals(ceotekst,"Name:Petar Petrovic\n" +
                "Email:petar.petrovic@gmail.com\n" +
                "Current Address :Petra Petrovica br.65\n" +
                "Permananet Address :Lazara Lazarevica br.55");
        driver.quit();
    }
}
