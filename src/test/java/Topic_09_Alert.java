import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Topic_09_Alert {
    WebDriver driver;
    WebDriverWait empliciWait;
    Alert alert;
    String projectPath = System.getProperty("user.dir");
    String autoIt = projectPath +"\\autoIT\\authen_firefox.exe";
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        empliciWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    @Test
    public void TC_01_Accept_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        sleepInSecond(5);
        driver.findElement(By.xpath("//button[text()= 'Click for JS Alert']")).click();
        alert = empliciWait.until(ExpectedConditions.alertIsPresent());
        System.out.println(alert.getText());
        Assert.assertTrue(alert.getText().equals("I am a JS Alert"));
        alert.accept();
        sleepInSecond(2);
        System.out.println(driver.findElement(By.id("result")).getText());
        Assert.assertTrue(driver.findElement(By.id("result")).getText().equals("You clicked an alert successfully"));

    }

    @Test
    public void TC_02_Confirm_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        sleepInSecond(5);
        driver.findElement(By.xpath("//button[text()= 'Click for JS Confirm']")).click();
        alert = empliciWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertTrue(alert.getText().equals("I am a JS Confirm"));
        alert.dismiss();
        sleepInSecond(2);
        Assert.assertTrue(driver.findElement(By.id("result")).getText().equals("You clicked: Cancel"));
    }

    @Test
    public void TC_03_Prompt_ALert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        sleepInSecond(5);
        driver.findElement(By.xpath("//button[text()= 'Click for JS Prompt']")).click();
        alert = empliciWait.until(ExpectedConditions.alertIsPresent());
        System.out.println(alert.getText());
        Assert.assertTrue(alert.getText().equals("I am a JS prompt"));
        sleepInSecond(1);
        alert.sendKeys("automationfc");
        sleepInSecond(2);
        alert.accept();
        sleepInSecond(2);
        System.out.println(driver.findElement(By.id("result")).getText());
        Assert.assertTrue(driver.findElement(By.id("result")).getText().equals("You entered: automationfc"));
    }
    @Test
    public void TC_04_Authentication_ALert(){
       String urlAU = urlByPass("http://the-internet.herokuapp.com/basic_auth","admin","admin");
       System.out.println(urlAU);
       driver.get(urlAU);
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id ='content']//p")).getText().contains("Congratulations! You must have the proper credentials."));
    }
    @Test
    public void TC_05_Authentication_ALert_AutoIT() throws IOException {
        Runtime.getRuntime().exec(new String[]{autoIt, "admin", "admin"});
        driver.get("http://the-internet.herokuapp.com/basic_auth");

        System.out.println(autoIt);
        sleepInSecond(10);
        empliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id ='content']//p")));
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id ='content']//p")).getText().contains("Congratulations! You must have the proper credentials."));
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
    public String urlByPass(String url, String userName, String PassWord){
        String[] urlArr = url.split("//");
        return urlArr[0] +"//" + userName +":" +PassWord +"@" +urlArr[1];
    }

    public void sleepInSecond(long timeoutInSecond){
        try {
            Thread.sleep(timeoutInSecond*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
