import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class FirstLoginTest {

    @Test
    public void login() {
        // открыть браузер
        // зайти на сайт https://www.saucedemo.com/

        WebDriver browser = new ChromeDriver();
        browser.get("https://www.saucedemo.com/");
        browser.findElement(By.id("user-name")).sendKeys("standard_user");
        browser.findElement(By.id("user-name")).sendKeys(Keys.CONTROL + "A");

        browser.findElement(By.id("user-name")).sendKeys(Keys.BACK_SPACE);
        browser.findElement(By.id("user-name")).sendKeys("standard_user");
        browser.findElement(By.xpath("//*[@data-test='password']")).sendKeys("secret_sauce");
        browser.findElement(By.cssSelector("[name = 'login-button']")).click();


        //browser.quit();
    }
}
