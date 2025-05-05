import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_11_Popup {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    @Test
    public void TC_01_Fixed_Popup() {
        driver.get("https://ngoaingu24h.vn/");
        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@role ='dialog']//h2[text()='Đăng nhập']")).isDisplayed());
        sleepInSecond(3);
        driver.findElement(By.xpath("//input[@placeholder = 'Tài khoản đăng nhập']")).sendKeys("automationfc");
        driver.findElement(By.xpath("//input[@placeholder = 'Mật khẩu']")).sendKeys("automationfc");
        sleepInSecond(2);
        driver.findElement(By.xpath("//div[@role = 'dialog']//button[text()='Đăng nhập']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id = 'notistack-snackbar' and contains(string() , 'Bạn đã nhập sai tài khoản hoặc mật khẩu!')]")).isDisplayed());
        sleepInSecond(5);
        driver.findElement(By.xpath("//h2[text() = 'Đăng nhập']//button")).click();
        Assert.assertFalse(driver.findElement(By.cssSelector("div[role ='dialog'] h2")).isDisplayed());
    }

    @Test
    public void TC_02_Fixed_Popup_Not_IN_DOM() {
        driver.get("https://tiki.vn/");
        driver.findElement(By.xpath("//span[text ()= 'Tài khoản']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()= 'Đăng nhập hoặc Tạo tài khoản']")).isDisplayed());

    }

    @Test
    public void TC_03() {
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
