package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import user.User;

public class LoginPage extends BasePage {

    private final By loginInput = By.id("user-name");
    private final By passwordInput = By.cssSelector(DATA_TEST_PATTERN.formatted("password"));
    private final By loginButton = By.cssSelector(DATA_TEST_PATTERN.formatted("login-button"));
    private final By errorMsg = By.cssSelector(DATA_TEST_PATTERN.formatted("error"));

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие браузера")
    public void open() {
        driver.get(BASE_URL);
    }

    @Step("Логин под кредами пользователя")
    public void login(User user) {
        fillLoginField(user.getEmail());
        fillPasswordField(user.getPassword());
        driver.findElement(loginButton).click();
    }

    @Step("Ввод логина {user}")
    public void fillLoginField(String user) {
        driver.findElement(loginInput).sendKeys(user);
    }

    @Step("Ввод логина {password}")
    public void fillPasswordField(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    @Step("Проверка видимости уведомления об ошибки")
    public boolean isErrorDisplayed() {
        return driver.findElement(errorMsg).isDisplayed();
    }

    @Step("Проверка текста ошибки")
    public String getErrorText() {
        return driver.findElement(errorMsg).getText();
    }
}
