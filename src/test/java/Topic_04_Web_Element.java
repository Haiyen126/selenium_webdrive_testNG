import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_04_Web_Element {

    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
    }
    @Test
    public void TC_01_Web_Element() {

        // tìm 1 element
        driver.findElement(By.xpath(""));
        WebElement searchTextbox = driver.findElement(By.xpath("//input[@id = 'small-searchterms']"));

        // tìm nhiều elemnet
        driver.findElements(By.xpath(""));

        List<WebElement> listtextbox = driver.findElements(By.xpath("//div[@class ='inputs']/input[not (@type='checkbox')]"));

        //textbox/ textArea/ editable dropdown
        searchTextbox.clear();
        searchTextbox.sendKeys();

        //button/ link/ radio/ checkbox/ custom dropdown
        searchTextbox.click();

        //Các hàm có tiền tố là get đều trả về dữ liệu
        searchTextbox.getAttribute("ID"); // get gia trị của attribute

        //lấy ra giá trị các thuộc tính css => dùng để test GUI
        searchTextbox.getCssValue("background-color");

        //lấy ra tọa đô của element so với page hiện tại (get góc bên ngoài element)
        searchTextbox.getLocation();
        // lấy ra kích thước của elemnet( chiều rộng x chiều cao)=> get góc bên trong element
        searchTextbox.getSize();
        //lấy ra cả location và size
        searchTextbox.getRect();
        // chụp lỗi => để đưa vào report
        searchTextbox.getScreenshotAs(OutputType.FILE);

        searchTextbox.getTagName();
        searchTextbox.getText();

        // Các hàm có tiền tố is thì trả về kiểu boolean( true/false)
        searchTextbox.isDisplayed();
        searchTextbox.isEnabled();
        searchTextbox.isSelected();

        // thay cho action Enter
        // dùng trong formTopic_00_Template
        searchTextbox.submit();
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
