package tests;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static enums.TitleNaming.CART;
import static enums.TitleNaming.PRODUCTS;
import static org.testng.Assert.*;
import static user.UserFactory.withStandartPermission;

public class CartTest extends BaseTest {
    List<String> goodsList = new ArrayList<>(
            List.of("Sauce Labs Backpack", "Sauce Labs Fleece Jacket", "Sauce Labs Onesie"));

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Отображение товаров в корзине")
    public void checkGoodsAddade() {
        loginPage
                .open()
                .login(withStandartPermission());
        assertEquals(cartPage.checkTitleName(), PRODUCTS.getDisplayName());

        for (String goods : goodsList) {
            productsPage.addGoodsToCart(goods);
        }

        productsPage.switchToCart();

        assertEquals(cartPage.checkTitleName(), CART.getDisplayName());
        assertFalse(cartPage.getProductsNames().isEmpty());
        assertEquals(cartPage.getProductsNames().size(), goodsList.size());
        for (String goods : goodsList) {
            assertTrue(cartPage.getProductsNames().contains(goods));
        }
    }
}
