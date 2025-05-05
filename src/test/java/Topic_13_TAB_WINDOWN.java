import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Topic_13_TAB_WINDOWN {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    @Test
    public void TC_01_Tab_Windown_01() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        //Lấy nID cửa sổ đang Acyive
        String parentID = driver.getWindowHandle();
        SwitchWithTitle(By.xpath("//a[text()='GOOGLE']"), "Google",parentID);
        SwitchWithTitle(By.xpath("//a[text()='FACEBOOK']"),"Facebook – log in or sign up" ,parentID);
        SwitchWithTitle(By.xpath("//a[text()='TIKI']"),"Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh" ,parentID);
        SwitchWithTitle(By.xpath("//a[text()='LAZADA']"),"Lazada - Mua Sắm Hàng Chất Giá Tốt Online" ,parentID);
        closeAllTabsWithoutParent(parentID);
        System.out.println(driver.getTitle());
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
    public void SwitchWithId(String parentID){
        Set<String> allID = driver.getWindowHandles();
        for(String ID : allID){
            if(!ID.equals(parentID)){
                driver.switchTo().window(ID);
            }
        }
    }
    public void SwitchWithTitle(By by, String titleExpect, String parentID){
        driver.findElement(by).click();

        Set<String> allID = driver.getWindowHandles();
        for(String ID:allID){
            driver.switchTo().window(ID);
            driver.getTitle();
            if(driver.getTitle() == titleExpect){
                break;
            }
        }
        System.out.println(driver.getTitle());
        sleepInSecond(2);
        Assert.assertTrue(driver.getTitle().equals(titleExpect));
        sleepInSecond(4);
        driver.switchTo().window(parentID);
    }
    public void closeAllTabsWithoutParent(String parentID){
        Set<String> allID = driver.getWindowHandles();
        for(String ID : allID){
            if(!ID.equals(parentID)){
                driver.switchTo().window(ID);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
    }

    public void sleepInSecond(long timeoutInSecond){
        try {
            Thread.sleep(timeoutInSecond*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
