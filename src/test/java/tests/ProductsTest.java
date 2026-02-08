package tests;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

import java.util.List;

import static enums.TitleNaming.PRODUCTS;
import static org.testng.Assert.*;
import static user.UserFactory.withStandartPermission;

public class ProductsTest extends BaseTest {
    List<String> goodsList =
            List.of("Sauce Labs Backpack", "Sauce Labs Fleece Jacket", "Sauce Labs Onesie");

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Добавление товаров в корзину")
    public void checkGoodsAddade() {
        loginPage.open();
        loginPage.login(withStandartPermission());
        assertEquals(productsPage.checkTitleName(), PRODUCTS.getDisplayName());
        assertTrue(productsPage.isTitleDisplayed());

        for (String goods : goodsList) {
            productsPage.addGoodsToCart(goods);
        }

        productsPage.addGoodsToCart(2);

        assertEquals(productsPage.checkCounterValue(), String.valueOf(goodsList.size() + 1));
        assertEquals(productsPage.checkCounterColor(), "rgba(226, 35, 26, 1)");
    }
}
