package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {
    private static final String ADD_TO_CART_PATTERN =
            "//*[text()='%s']//ancestor::div[@class='inventory_item']" +
            "//child::button[text()='Add to cart']";
    private final By title = By.cssSelector("[data-test='title']");
    private final By cartCounter = By.cssSelector(DATA_TEST_PATTERN.formatted("shopping-cart-badge"));
    private final By titleName1 = By.xpath("//span[text()='Products']");
    private final By titleName2 = By.xpath("//span[text()='Your Cart']");
    private final By titleName3 = By.xpath("//span[text()='Checkout: Your Information']");


    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isTitleDisplayed() {
        return driver.findElement(title).isDisplayed();
    }

    public String getTitle() {
        return driver.findElement(title).getText();
    }

    public void addGoodsToCart(String goodsName) {
        By addToCart = By.xpath(ADD_TO_CART_PATTERN.formatted(goodsName));
        driver.findElement(addToCart).click();
    }

    public String checkCounterValue() {
        return driver.findElement(cartCounter).getText();
    }
}

//"Sauce Labs Backpack"