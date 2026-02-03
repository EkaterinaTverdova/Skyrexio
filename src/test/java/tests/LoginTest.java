package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import user.User;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.*;

public class LoginTest extends BaseTest {
    @Test
    public void correctLoginTest() {
        loginPage.open();
        loginPage.login(withStandartPermission());

        assertTrue(productsPage.isTitleDisplayed(), "Заголовок не виден");
        assertEquals(productsPage.checkTitleName(), "Products", "Не верный заголовок");
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

    @Test(dataProvider = "incorrectLoginData")
    public void incorrectLoginTest(User user, String errorMsg) {
        loginPage.open();
        loginPage.login(user);

        assertTrue(loginPage.isErrorDisplayed(), "Нет сообщения об ошибке");
        assertEquals(loginPage.getErrorText(), errorMsg, "Не верное текст сообщения об ошибке");
    }
}
