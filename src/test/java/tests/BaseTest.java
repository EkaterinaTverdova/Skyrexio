package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pages.LoginPage;
import pages.ProductsPage;
import pages.CartPage;

import java.time.Duration;

public class BaseTest {
    public WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;

    @Parameters({"browser"})
    @BeforeMethod
    public void setup(@Optional("chrome") String browser) {
        if(browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions optionsChrome = new ChromeOptions();
            optionsChrome.addArguments("start-maximized");
            optionsChrome.addArguments("--guest");
            driver = new ChromeDriver(optionsChrome);
        } else if (browser.equalsIgnoreCase(("edge"))) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
            //browser.manage().window().maximize();
        } else if (browser.equalsIgnoreCase(("firefox"))) {
            WebDriverManager.firefoxdriver();
            FirefoxOptions optionsFirefox = new FirefoxOptions();
            optionsFirefox.addArguments("--window-size=600,900");
            driver = new FirefoxDriver(optionsFirefox);
        }

        //options.addArguments("headless");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));

        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }
}
