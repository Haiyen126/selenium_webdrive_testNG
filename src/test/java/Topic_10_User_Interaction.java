import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.swing.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_10_User_Interaction {
    WebDriver driver;
    Actions action;
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        action = new Actions(driver);
        jsExecutor = (JavascriptExecutor) driver;
    }
    @Test
    public void TC_01_Hover_01() {
        driver.get("https://automationfc.github.io/jquery-tooltip/");
        action.moveToElement(driver.findElement(By.xpath("//input[@id ='age']"))).perform();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class ='ui-tooltip-content']")).getText(), "We ask for your age only for statistical purposes.");
    }

    @Test
    public void TC_02_Hover_02() {
        driver.get("https://www.fahasa.com/");
        sleepInSecond(5);
        action.moveToElement(driver.findElement(By.xpath("//span[@class = 'icon_menu']"))).perform();
        sleepInSecond(3);
        action.moveToElement(driver.findElement(By.xpath("//a//span[text() = 'Sách Trong Nước']"))).perform();
        sleepInSecond(4);
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class = 'fhs_menu_title fhs_center_left']//span[text() = 'Sách Trong Nước']")).getText(),"Sách Trong Nước");
    }

    @Test
    public void TC_03_Click_Hold() {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> allitem = driver.findElements(By.xpath("//ol[@id = 'selectable']//li"));
        action.clickAndHold(allitem.get(0)).moveToElement(allitem.get(3)).release().perform();
        sleepInSecond(2);
        Assert.assertEquals(driver.findElements(By.cssSelector(".ui-selected")).size(), 4);
    }
    @Test
    public void TC_04_Click_Hold_02() {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        sleepInSecond(2);
        List<WebElement> allitem = driver.findElements(By.xpath("//ol[@id = 'selectable']//li"));
        action.keyDown(Keys.CONTROL).perform();
        action.click(allitem.get(0)).perform();
        action.click(allitem.get(4)).perform();
        action.click(allitem.get(8)).perform();
        action.click(allitem.get(10)).perform();
        Assert.assertEquals(driver.findElements(By.cssSelector(".ui-selected")).size(), 4);
    }
    @Test
    public void TC_05_Double_Click() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        sleepInSecond(5);
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[text()='Double click me']")) );
        action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
        Assert.assertEquals(driver.findElement(By.xpath("//p[@id ='demo']")).getText(),"Hello Automation Guys!");

    }
    @Test
    public void TC_06_Right_Click() {
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
        action.contextClick(driver.findElement(By.xpath("//span[text()= 'right click me']"))).perform();
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()= 'Quit']")).isDisplayed());
        sleepInSecond(3);
        action.moveToElement(driver.findElement(By.xpath("//span[text()= 'Quit']"))).perform();
        Assert.assertTrue(driver.findElement(By.cssSelector(".context-menu-icon-quit.context-menu-visible.context-menu-hover")).isDisplayed());
         sleepInSecond(2);
        action.click(driver.findElement(By.xpath("//span[text()= 'Quit']"))).perform();
        Assert.assertEquals(driver.switchTo().alert().getText(),"clicked: quit");
    }
    @Test
    public void TC_07_Drag_Drop_HTML4() {
        driver.get("https://automationfc.github.io/kendo-drag-drop/");
        WebElement small = driver.findElement(By.id("draggable"));
        WebElement big = driver.findElement(By.id("droptarget"));
        action.dragAndDrop(small,big).perform();
        Assert.assertEquals(big.getText(), "You did great!");
        Color colorString = Color.fromString(big.getCssValue("background-color"));
        String color = colorString.asHex();
        Assert.assertEquals(color, "#03a9f4");
    }
    @Test
    public void TC_08_Drag_Drop_HTML5(){

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
