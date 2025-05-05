import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_05_Textbox_TextArea {

    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("http://live.techpanda.org/");
    }
    String firstName = "ABC";
    String midleName = "DEF";
    String lastName = "khkk";
    String email = "abc" + dataRandom();
    String passWord = "1234566";
    String confirmPW ="1234566";
    @Test
    public void TC_01_Register() {
        driver.findElement(By.xpath("//div[@class = 'footer-container']//a[@title = 'My Account']")).click();
        driver.findElement(By.xpath("//span/span[contains(text(),'Create an Account')]")).click();
        driver.findElement(By.id("firstname")).sendKeys(firstName);
        driver.findElement(By.id("middlename")).sendKeys(midleName);
        driver.findElement(By.id("lastname")).sendKeys(lastName);
        driver.findElement(By.id("email_address")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(passWord);
        driver.findElement(By.id("confirmation")).sendKeys(confirmPW);
        driver.findElement(By.id("is_subscribed")).click();
        driver.findElement(By.xpath("//button[@title = \"Register\"]")).click();
        String mess = driver.findElement(By.xpath("//li[@class = \"success-msg\"]//span")).getText();
        Assert.assertEquals(mess, "Thank you for registering with Main Website Store.");
        String dataActual = driver.findElement(By.xpath("//h3[text() =\'Contact Information\']/parent::div/following-sibling::div/p")).getText();
        Assert.assertTrue(dataActual.contains(firstName + midleName +lastName + email));
    }

    @Test
    public void TC_02() {
    }

    @Test
    public void TC_03() {
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }

    public String dataRandom(){
        Random ran = new Random();

        int number =  ran.nextInt(9999);
        return  number + "@gmail.vn";
    }

    public void sleepInSecond(long timeoutInSecond){
        try {
            Thread.sleep(timeoutInSecond*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
