import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_08_Button_Radio_Checkbox {
    WebDriver driver;
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        jsExecutor = (JavascriptExecutor) driver;
    }
    @Test
    public void TC_01_Button() {
        driver.get("https://www.fahasa.com/customer/account/create");
        driver.findElement(By.xpath("//li/a[text()='Đăng nhập']")).click();
        // Chờ button Đăng nhập hiển thị trên DOM
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement buttonLogin = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//button[@class ='fhs-btn-login']")));
        Assert.assertFalse(buttonLogin.isEnabled());
        verifyBackground(By.xpath("//button[@class ='fhs-btn-login']"),"#000000");
        driver.findElement(By.xpath("//input[@id ='login_username' ]")).sendKeys("a@gmail.com");
        driver.findElement(By.xpath("//input[@id ='login_password' ]")).sendKeys("1234567");
        sleepInSecond(1);
        Assert.assertTrue(buttonLogin.isEnabled());
        verifyBackground(By.xpath("//button[@class ='fhs-btn-login']"), "#C92127");

    }

    @Test
    public void TC_02_Default_Checkbox_Radio() {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
        sleepInSecond(5);
        WebElement banner = driver.findElement(By.xpath("//div[@id = 'onetrust-banner-sdk']"));
        if(banner.isDisplayed()){
            driver.findElement(By.xpath("//button[@id='onetrust-accept-btn-handler']")).click();
            sleepInSecond(1);
        }

        WebElement checkbox = driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input"));
        checkbox.click();
        Assert.assertTrue(checkbox.isSelected());
        sleepInSecond(1);
        checkbox.click();
        Assert.assertFalse(checkbox.isSelected());
    }

    @Test
    public void TC_03_SelectAll_CheckBox() {
        driver.get("https://automationfc.github.io/multiple-fields/");
        sleepInSecond(2);
        List<WebElement> alldataCheckbox = driver.findElements(By.xpath("//li[@data-type=\'control_checkbox\']//input"));
        for (WebElement item : alldataCheckbox) {
            if (!item.isSelected()) {
                item.click();
                sleepInSecond(1);
            }
        }
        for(WebElement item: alldataCheckbox){
            Assert.assertTrue(item.isSelected());
        }
        driver.navigate().refresh();
        driver.findElement(By.xpath("//li[@data-type=\"control_checkbox\"]//input[@value ='Gout']")).click();
        List<WebElement> dataCheckbox = driver.findElements(By.xpath("//li[@data-type=\'control_checkbox\']//input"));
        for (WebElement item : dataCheckbox){
            if(item.isSelected()){
                Assert.assertTrue(item.getAttribute("value").equals("Gout"));
            }
        }
    }

    @Test
    public void TC_04_Custom_Checkbox_Radio(){
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
        sleepInSecond(2);
        Assert.assertTrue(driver.findElement(By.xpath("//div[@data-value=\'Cần Thơ\' and @aria-checked=\'false\']")).isDisplayed());
        driver.findElement(By.xpath("//div[@data-value=\'Cần Thơ\']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@data-value=\'Cần Thơ\' and @aria-checked=\'true\']")).isDisplayed());
    }
    @AfterClass
    public void afterClass(){
        driver.quit();
    }
    public void verifyBackground(By by, String colorExpect){
        WebElement button = driver.findElement(by);
        Color buttonColorString = Color.fromString(button.getCssValue("background-color"));
        String color = buttonColorString.asHex().toUpperCase();
        Assert.assertEquals(color, colorExpect);
    }

    public void sleepInSecond(long timeoutInSecond){
        try {
            Thread.sleep(timeoutInSecond*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
