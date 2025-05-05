import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_07_Custom_Dropdown {
    WebDriver driver;
    WebDriverWait emplicitWait;
    JavascriptExecutor jsExecutor;
    String[] firstMonth = {"January","May","June","July"};

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        emplicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        // Ép kiểu tường minh
        jsExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    @Test
    public void TC_01_Jquery() {
        // Open url
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
        selectDataDropdown("//span[@id = 'number-button']//span[@class ='ui-selectmenu-icon ui-icon ui-icon-triangle-1-s' ]","//ul[@id = 'number-menu']//div","6");
        WebElement dataChoose = driver.findElement(By.xpath("//span[@id = 'number-button']//span[@class = 'ui-selectmenu-text']"));
        Assert.assertEquals(dataChoose.getText(),"6");

    }

    @Test
    public void TC_02_React() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
        selectDataDropdown("//i[@class = 'dropdown icon']", "//div[@class = 'visible menu transition']//span","Matt");
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='divider text' and text()= 'Matt']")).isDisplayed());
    }

    @Test
    public void TC_03_Vue() {
    }

    @Test
    public void TC_04_MultipleSelect() {
        //Click vào dropdown
        //=> tìm đến locator sau đó click()
        //Chọn các giá tri
        //=> Dùng vòng for duyệt qua list các giá trị các nào thỏa mãn thì click
        driver.get("https://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");
        driver.findElement(By.xpath("(//div[@class ='ms-parent multiple-select '])[1]//div[@class = 'icon-caret']")).click();
        List<WebElement> allItem = emplicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("(//div[@class ='ms-drop bottom'])[1]//span")));
        for (WebElement item : allItem) {
            for (String data : firstMonth) {
                if (item.getText().equals(data)) {
                    jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
                    sleepInSecond(4);
                    item.click();
                    List<WebElement> itemSelected = driver.findElements(By.xpath("(//div[@class ='ms-drop bottom'])[1]//li[@class ='option-level-0 selected']//input"));
                    if (itemSelected.size() == firstMonth.length) {
                        break;
                    }
                }
            }

        }
        //So sánh các giá trị đợc chọn với expect
            List<WebElement> itemSelected = driver.findElements(By.xpath("(//div[@class ='ms-drop bottom'])[1]//li[@class ='option-level-0 selected']//input"));
            int numberSize = itemSelected.size();

            String allitemSelected = driver.findElement(By.xpath("//button[@class ='ms-choice']/span")).getText();
            if (numberSize <= 3 && numberSize > 0) {
                boolean status = true;
                for (String item : firstMonth) {
                    if (!allitemSelected.contains(item)) {
                        status = false;
                        break;
                    }
                }
                Assert.assertTrue(status);
            }else if(numberSize>3 && numberSize <12 ){
                Assert.assertTrue(driver.findElement(By.xpath("//button[@class = 'ms-choice']/span[text()= '" + numberSize  + " of 12 selected']")).isDisplayed());
            }else if(numberSize >=12){
                Assert.assertTrue(driver.findElement(By.xpath("//button[@class = 'ms-choice']/span[text()='All selected'")).isDisplayed());
            }
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
    public void selectDataDropdown(String xpthParent,String xpathChild, String value){
        // click vào dropdown
        driver.findElement(By.xpath(xpthParent)).click();
        // Đợi cho các giá trị dropdown load hết
       List<WebElement> allitem = emplicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpathChild)));
        //Click chọn giá trị trong dropdown
        //+ Giá trị hiển thị trên màn hình thì click luôn
        //+ Giá trị k hiển thị trên màn hình thì scroll tới sau đó click
        for(WebElement item : allitem){
            if(item.getText().trim().equals(value) ){
                if(!item.isDisplayed()){
                        jsExecutor.executeScript("arguments[0].scrollIntoView()true;", item);
                        sleepInSecond(4);
                }
                item.click();
                break;
            }

        }

    }
    public void sleepInSecond(long timeoutInSecond){
        try {
            Thread.sleep(timeoutInSecond*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
