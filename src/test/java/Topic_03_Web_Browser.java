import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class Topic_03_Web_Browser {
    public static void main(String[] args) {
        WebDriver driver = null;

        //Mở ra 1 url
        driver.get("https://demo.nopcommerce.com/");

        //Lấy ra url
        String url = driver.getCurrentUrl();

        //lấy ra tiêu đề tab đang truy cập
        String title = driver.getTitle();

        //lây html code của page hiện tại

        driver.getPageSource();

        //Xử lý tab//windown

        driver.getWindowHandle();
        driver.getWindowHandles();

        //back lại trang
        driver.navigate().back();
        //refesh lại trang
        driver.navigate().refresh();

        //Chờ element load trong xx thời gian
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        driver.manage().window().maximize();

        driver.manage().window().fullscreen();

        driver.manage().window().getPosition();

        driver.quit(); // đóng trình duyệt

        driver.close(); // đóng tab


        driver.switchTo().alert();

        driver.switchTo().window("");

        driver.switchTo().frame(1);


    }

}
