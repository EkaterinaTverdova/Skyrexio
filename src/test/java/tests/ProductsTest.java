package tests;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class ProductsTest extends BaseTest {
    @Test
    public void checkGoodsAddade() {
        List<String> goodsList =  new ArrayList<>(
                List.of("Sauce Labs Backpack", "Sauce Labs Fleece Jacket", "Sauce Labs Onesie"));

        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.isTitleDisplayed();
        for (int i = 0; i < goodsList.size(); i++) {
            productsPage.addGoodsToCart(goodsList.get(i));
        }

        assertEquals(productsPage.checkCounterValue(), String.valueOf(goodsList.size()));
        assertEquals(productsPage.checkCounterColor(), "rgba(226, 35, 26, 1)");
    }
}
