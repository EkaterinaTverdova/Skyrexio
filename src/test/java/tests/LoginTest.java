package tests;

import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.TmsLink;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import user.User;

import static enums.TitleNaming.PRODUCTS;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.*;

public class LoginTest extends BaseTest {
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Екатерина ")
    @TmsLink("")
    @Test(description = "Авторизация пользователя с корректными данными")
    public void correctLoginTest() {
        loginPage.open();
        loginPage.login(withStandartPermission());

        assertTrue(productsPage.isTitleDisplayed(), "Заголовок не виден");
        assertEquals(productsPage.checkTitleName(), PRODUCTS.getDisplayName(), "Не верный заголовок");
    }

    @DataProvider
    public Object[][] incorrectLoginData() {
        return new Object[][]{
                {withLockedPermission(), "Epic sadface: Sorry, this user has been locked out."},
                {withEmptyLogin(), "Epic sadface: Username is required"},
                {withEmptyPassword(), "Epic sadface: Password is required"},
                {withNotExistUser(), "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Test(dataProvider = "incorrectLoginData", description = "Авторизация пользователя с некорректными данными")
    public void incorrectLoginTest(User user, String errorMsg) {
        loginPage.open();
        loginPage.login(user);

        assertTrue(loginPage.isErrorDisplayed(), "Нет сообщения об ошибке");
        assertEquals(loginPage.getErrorText(), errorMsg, "Не верное текст сообщения об ошибке");
    }
}
