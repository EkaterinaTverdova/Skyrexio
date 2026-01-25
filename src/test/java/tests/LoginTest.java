package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {
    @Test
    public void correctLoginTest() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        assertTrue(productsPage.isTitleDisplayed(), "Заголовок не виден");
        assertEquals(productsPage.getTitle(), "Products", "Не верный заголовок");
    }

    @Test
    public void lockedLoginTest() {
        loginPage.open();
        loginPage.login("locked_out_user", "secret_sauce");

        assertTrue(loginPage.isErrorDisplayed(), "Нет сообщения об ошибке");
        assertEquals(loginPage.getErrorText(), "Epic sadface: Sorry, this user has been locked out.",
                "Не верное текст сообщения об ошибке");
    }

    @Test
    public void emptyLoginTest() {
        loginPage.open();
        loginPage.login("", "secret_sauce");

        assertTrue(loginPage.isErrorDisplayed(), "Нет сообщения об ошибке");
        assertEquals(loginPage.getErrorText(), "Epic sadface: Username is required",
                "Не верное текст сообщения об ошибке");
    }

    @Test
    public void emptyPasswordTest() {
        loginPage.open();
        loginPage.login("standard_user", "");

        assertTrue(loginPage.isErrorDisplayed(), "Нет сообщения об ошибке");
        assertEquals(loginPage.getErrorText(), "Epic sadface: Password is required",
                "Не верное текст сообщения об ошибке");
    }

    @Test
    public void incorrectLoginTest() {
        loginPage.open();
        loginPage.login("Standard_user", "secret_sauce");

        assertTrue(loginPage.isErrorDisplayed(), "Нет сообщения об ошибке");
        assertEquals(loginPage.getErrorText(), "Epic sadface: Username and password do not match any user in this service",
                "Не верное текст сообщения об ошибке");
    }
}
