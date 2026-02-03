package tests;

import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;
import static user.UserFactory.withStandartPermission;

public class ProductsTest extends BaseTest {
    List<String> goodsList =
            List.of("Sauce Labs Backpack", "Sauce Labs Fleece Jacket", "Sauce Labs Onesie");

    @Test
    public void checkGoodsAddade() {
        loginPage.open();
        loginPage.login(withStandartPermission());
        assertEquals(productsPage.checkTitleName(), "Products");
        assertTrue(productsPage.isTitleDisplayed());

        for (String goods : goodsList) {
            productsPage.addGoodsToCart(goods);
        }

        productsPage.addGoodsToCart(2);

        assertEquals(productsPage.checkCounterValue(), String.valueOf(goodsList.size() + 1));
        assertEquals(productsPage.checkCounterColor(), "rgba(226, 35, 26, 1)");
    }
}
