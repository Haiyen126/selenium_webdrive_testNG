import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_02_BaiTap_Xpath {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }
    @Test
    public void TC_01_Register_empty_data() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        driver.findElement(By.cssSelector("button[type='submit']")).click();
        Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email");
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập lại địa chỉ email");
        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Vui lòng nhập mật khẩu");
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Vui lòng nhập lại mật khẩu");
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Vui lòng nhập số điện thoại.");
    }

    @Test
    public void TC_02_Register_invalid_email() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.id("txtFirstname")).sendKeys("TestABC");
        driver.findElement(By.id("txtEmail")).sendKeys("TestABC");
        driver.findElement(By.id("txtCEmail")).sendKeys("TestABC");
        driver.findElement(By.id("txtPassword")).sendKeys("Test@123");
        driver.findElement(By.id("txtCPassword")).sendKeys("Test@123");
        driver.findElement(By.id("txtPhone")).sendKeys("03983837373");
        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email hợp lệ");
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Email nhập lại không đúng");
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
