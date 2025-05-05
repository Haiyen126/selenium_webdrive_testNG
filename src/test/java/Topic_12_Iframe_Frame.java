import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Topic_12_Iframe_Frame {
    WebDriver driver;
    Select select1;
    Select select2;
    JavascriptExecutor jsExecutor;
    WebDriverWait empliciWait;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        jsExecutor = (JavascriptExecutor) driver;
        empliciWait = new WebDriverWait(driver, Duration.ofSeconds(20));

    }
    @Test
    public void TC_01_Iframe() {
        driver.get("https://toidicodedao.com/");
        sleepInSecond(3);
        driver.switchTo().frame(driver.findElement(By.cssSelector("div.fb-page iframe")));
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Tôi đi code dạo']")).isDisplayed());
        sleepInSecond(2);
        Assert.assertTrue(driver.findElement(By.xpath("//div[text()='402,054 followers']")).isDisplayed());
    }

    @Test
    public void TC_02_Iframe_02() {
        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");
        sleepInSecond(2);
        if(driver.findElement(By.xpath("//div[@role ='dialog']")).isDisplayed()){
            driver.findElement(By.xpath("//button[contains(@class, 'osano-cm-close')]")).click();
        }
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector("div#formTemplateContainer>iframe")));
        sleepInSecond(1);
//        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector("div#formTemplateContainer>iframe")));
        empliciWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("div#formTemplateContainer iframe")));
        sleepInSecond(4);
        select1 = new Select(driver.findElement(By.id("RESULT_RadioButton-2")));
        select1.selectByVisibleText("Freshman");
        sleepInSecond(3);
        select2 = new Select(driver.findElement(By.id("RESULT_RadioButton-3")));
        select2.selectByVisibleText("South Dorm");
        sleepInSecond(2);
        if(!driver.findElement(By.xpath("//label[contains(string(), 'Male')]/preceding-sibling::input")).isSelected()){
            driver.findElement(By.xpath("//label[contains(string(), 'Male')]/preceding-sibling::input")).click();
        }

    }

    @Test
    public void TC_03_Frame_03() {
        driver.get("https://netbanking.hdfcbank.com/netbanking/");
        driver.switchTo().frame(driver.findElement(By.name("login_page")));
        sleepInSecond(3);
        driver.findElement(By.xpath("//div[@class ='inputfield ibvt loginData']/input")).sendKeys("0394994");
        driver.findElement(By.xpath("//a[text()='CONTINUE']")).click();
        driver.switchTo().defaultContent();
        sleepInSecond(10);
        Assert.assertTrue(driver.findElement(By.id("keyboard")).isDisplayed());
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
