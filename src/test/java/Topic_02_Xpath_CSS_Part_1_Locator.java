import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_02_Xpath_CSS_Part_1_Locator {
    // Biến driver đại diện cho selenium WebDriver
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        //Mở trình duyệt Firefox lên
        driver = new FirefoxDriver();
        // Thời gian để chờ cho element đươ tìm thấy trong 1 khoảng thời gian (30s)
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        //mở AUT( application under testing)/SAT( system under testing)=> trang register
        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
    }
    @Test
    public void TC_01_ID() {
        //Nhập giá trị vào Firstname textbox
        driver.findElement(By.id("FirstName")).sendKeys("Automation Test");
        sleepInSecond(3);
        //Click vào male radio button
        driver.findElement(By.id("gender-male")).click();
        sleepInSecond(3);
    }

    @Test
    public void TC_02_Class() {
        //Refesh page
        driver.navigate().refresh();
        driver.findElement(By.className("search-box-text ")).sendKeys("Macbook");
        sleepInSecond(3);
        driver.findElement(By.className("search-box-button")).click();
    }

    @Test
    public void TC_03_Name() {
        //trở lại trang register
        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
        sleepInSecond(3);
        driver.findElement(By.name("Email")).sendKeys("a@gmail.com");
        driver.findElement(By.name("Newsletter")).click();

    }

    @Test
    public void TC_04_TagName() {
        System.out.println("Sum link: "+ driver.findElements(By.tagName("a")).size());
        System.out.println("Sum input: "+ driver.findElements(By.tagName("input")).size());
        ;
    }

    @Test
    public void TC_05_LinkText() {
    }

    @Test
    public void TC_06_Partial_LinkText() {
    }

    @Test
    public void TC_07_Css() {
    }

    @Test
    public void TC_08_Xpath() {
    }


    @AfterClass
    public void afterClass(){
        driver.quit();
    }
    public void sleepInSecond(long timeoutInSecond) {
        try {
            Thread.sleep(timeoutInSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
