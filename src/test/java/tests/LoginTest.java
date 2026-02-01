package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {
    @Test
    public void correctLoginTest() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        assertTrue(productsPage.isTitleDisplayed(), "Заголовок не виден");
        assertEquals(productsPage.checkTitleName(), "Products", "Не верный заголовок");
    }

    @DataProvider
    public Object[][] incorrectLoginData() {
        return new Object[][]{
                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."},
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"},
                {"Standard_user", "secret_sauce", "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Test(dataProvider = "incorrectLoginData")
    public void incorrectLoginTest(String user, String password, String errorMsg) {
        loginPage.open();
        loginPage.login(user, password);

        assertTrue(loginPage.isErrorDisplayed(), "Нет сообщения об ошибке");
        assertEquals(loginPage.getErrorText(), errorMsg, "Не верное текст сообщения об ошибке");
    }
}
