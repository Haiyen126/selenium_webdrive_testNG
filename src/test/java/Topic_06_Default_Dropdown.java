import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_06_Default_Dropdown {
    WebDriver driver;
    Select select;
    List<String> allitemExpect;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://egov.danang.gov.vn/reg");
        allitemExpect = new ArrayList<>(Arrays.asList("aaaa","bbbb"));
    }
    @Test
    public void TC_01() {
        select = new Select(driver.findElement(By.id("gioiTinh")));
        select.selectByVisibleText("aaaa");
        select.getFirstSelectedOption().getText();
        select.getOptions().size();
        select.isMultiple();

        List<WebElement> allitem =  select.getOptions();
        List<String> allitemActual = new ArrayList<>();
        //Duyệt qua tất cả item có trg list
        for(WebElement item: allitem){
            allitemActual.add(item.getText());
//            System.out.println(item.getText());
        }
        Assert.assertEquals(allitemActual,allitemExpect);

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

    public void sleepInSecond(long timeoutInSecond){
        try {
            Thread.sleep(timeoutInSecond*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
