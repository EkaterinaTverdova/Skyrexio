package tests;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class CartTest extends BaseTest {
    List<String> goodsList = new ArrayList<>(
            List.of("Sauce Labs Backpack", "Sauce Labs Fleece Jacket", "Sauce Labs Onesie"));

    @Test
    public void checkGoodsAddade() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(productsPage.checkTitleName(), "Products");

        for (int i = 0; i < goodsList.size(); i++) {
            productsPage.addGoodsToCart(goodsList.get(i));
        }

        productsPage.switchToCart();
        assertEquals(cartPage.checkTitleName(), "Your Cart");
        assertEquals(cartPage.getProductsNames().size(), goodsList.size());
        assertFalse(cartPage.getProductsNames().isEmpty());
        for (int i = 0; i < goodsList.size(); i++) {
            assertTrue(cartPage.getProductsNames().contains(goodsList.get(i)));
        }
    }
}
