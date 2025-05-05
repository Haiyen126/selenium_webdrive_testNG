import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_04_Execise_Web_Element {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    @Test
    public void TC_01_isDisplayed() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
       WebElement emailTextbox = driver.findElement(By.xpath("//input[@id='email']"));
       if(emailTextbox.isDisplayed() == true){
           emailTextbox.sendKeys("Automation Test");
           System.out.println("email textbox is displayed");
       }else {
           System.out.println("email textbox is not displayed");
       }
        WebElement eduTextbox = driver.findElement(By.xpath("//textarea[@id='edu']"));
        if(eduTextbox.isDisplayed() == true){
            eduTextbox.sendKeys("Automation Test");
            System.out.println("edu textbox is displayed");
        }else {
            System.out.println("edu textbox is not displayed");
        }
        WebElement under18Radio = driver.findElement(By.xpath("//input[@id=\'under_18\']"));
        if(under18Radio.isDisplayed() == true){
            under18Radio.click();
            System.out.println("under18 radio is displayed");
        }else {
            System.out.println("under18 radio is not displayed");
        }

        WebElement userName5 = driver.findElement(By.xpath("//h5[contains(text(),\"User5\")]"));
        if(userName5.isDisplayed() == true){
            userName5.click();
            System.out.println("userName 5  is displayed");
        }else {
            System.out.println("userName 5  is not displayed");
        }

    }

    @Test
    public void TC_02_isDisplayed_Advand() {
        if(isElementDisplay(By.xpath("//input[@id='email']"))){
            sendKeyTex(By.xpath("//input[@id='email']"));
        }
    }
    By onecharactors = By.cssSelector(".number-char.completed");
    @Test
    public void TC_03_Register_Validate() {
        driver.get("https://login.mailchimp.com/signup/");
        driver.findElement(By.id("email")).sendKeys("abc@gmail.com");
        driver.findElement(By.id("new_password")).sendKeys("1");
        Assert.assertTrue(driver.findElement(onecharactors).isDisplayed());
    }


    @AfterClass
    public void afterClass(){
        driver.quit();
    }

    public boolean isElementDisplay(By by){
        if(driver.findElement(by).isDisplayed()){
            System.out.println(by +"is displayed");
            return true;
        }else{
            System.out.println(by+"is not displayed");
            return false;

        }

    }
    public void sendKeyTex(By by){
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys("Automation Tester");
    }
    public void sleepInSecond(long timeoutInSecond){
        try {
            Thread.sleep(timeoutInSecond*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
