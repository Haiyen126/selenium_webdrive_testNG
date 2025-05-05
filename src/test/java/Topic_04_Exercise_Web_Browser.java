import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_04_Exercise_Web_Browser {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("http://live.techpanda.org/");
    }
    @Test
    public void TC_01_VerifyURL() {
        driver.findElement(By.xpath("//div[@class ='footer-container']//a[contains(text(),'My Account')]")).click();
        driver.getCurrentUrl();
        Assert.assertEquals(driver.getCurrentUrl(), "https://live.techpanda.org/index.php/customer/account/login/");
        driver.findElement(By.xpath("//span/span[contains(text(),'Create an Account')]")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://live.techpanda.org/index.php/customer/account/create/");

    }

    @Test
    public void TC_02_VerifyTitle() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class ='footer-container']//a[contains(text(),'My Account')]")).click();
        Assert.assertEquals(driver.getTitle(),"Customer Login");

    }

    @Test
    public void TC_03_GetPageSource() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class ='footer-container']//a[contains(text(),'My Account')]")).click();
        Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }

    public void sleepInSecond(long timeoutInSecond){
        try {
            Thread.sleep(timeoutInSecond*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
