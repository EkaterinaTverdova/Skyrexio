package tests;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;
import static user.UserFactory.withStandartPermission;

public class CartTest extends BaseTest {
    List<String> goodsList = new ArrayList<>(
            List.of("Sauce Labs Backpack", "Sauce Labs Fleece Jacket", "Sauce Labs Onesie"));

    @Test
    public void checkGoodsAddade() {
        loginPage.open();
        loginPage.login(withStandartPermission());
        assertEquals(cartPage.checkTitleName(), "Products");

        for (String goods : goodsList) {
            productsPage.addGoodsToCart(goods);
        }

        productsPage.switchToCart();

        assertEquals(cartPage.checkTitleName(), "Your Cart");

        assertFalse(cartPage.getProductsNames().isEmpty());
        assertEquals(cartPage.getProductsNames().size(), goodsList.size());
        for (String goods : goodsList) {
            assertTrue(cartPage.getProductsNames().contains(goods));
        }
    }
}
