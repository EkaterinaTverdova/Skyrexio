package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.ProductsPage;
import pages.CartPage;
import utils.TestListener;

import java.time.Duration;

@Listeners({AllureTestNg.class, TestListener.class})
public class BaseTest {
    public WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;

    @Step("Настройка браузера {browser}")
    @Parameters({"browser"})
    @BeforeMethod
    public void setup(@Optional("chrome") String browser, ITestContext context) {
        ChromeOptions options = new ChromeOptions();
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            options.addArguments("start-maximized");
            options.addArguments("--guest");
            //options.addArguments("headless");
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase(("edge"))) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
            //browser.manage().window().maximize();
        } else if (browser.equalsIgnoreCase(("firefox"))) {
            WebDriverManager.firefoxdriver();
            FirefoxOptions optionsFirefox = new FirefoxOptions();
            optionsFirefox.addArguments("--window-size=600,900");
            optionsFirefox.addArguments("headless");
            driver = new FirefoxDriver(optionsFirefox);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
        context.setAttribute("driver", driver);

        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
    }

    @Step("Закрытие браузера")
    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }
}
