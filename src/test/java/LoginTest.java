import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {
    @Test
    public void loginTest() {
        browser.findElement(By.id("user-name")).sendKeys("standard_user");
        browser.findElement(By.xpath("//*[@data-test='password']")).sendKeys("secret_sauce");
        browser.findElement(By.cssSelector("[name = 'login-button']")).click();

        assertTrue(browser.findElement(By.cssSelector("[data-test='title']"))
                .isDisplayed(), "Заголовок не виден ");

        assertEquals(browser.findElement(By.cssSelector("[data-test='title']"))
                .getText(), "Products", "Не верный заголовок");
    }

    @Test
    public void incorrectLoginTest() {
        browser.findElement(By.id("user-name")).sendKeys("locked_out_user");
        browser.findElement(By.xpath("//*[@data-test='password']")).sendKeys("secret_sauce");
        browser.findElement(By.cssSelector("[name = 'login-button']")).click();

        assertTrue(browser.findElement(By.cssSelector("[data-test='error']"))
                .isDisplayed(), "Нет сообщения об ошибке");

        assertEquals(browser.findElement(By.cssSelector("[data-test='error']"))
                        .getText(), "Epic sadface: Sorry, this user has been locked out.",
                "Не верное текст сообщения об ошибке");
    }
}
