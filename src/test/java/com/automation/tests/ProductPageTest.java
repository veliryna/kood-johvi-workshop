package com.automation.tests;

import com.automation.pages.ProductPage;
import com.automation.utils.ConfigReader;
import com.automation.utils.DriverFactory;
import com.automation.utils.ScreenshotOnFailureExtension;
import io.qameta.allure.Description;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import static org.junit.jupiter.api.Assertions.fail;

@ExtendWith(ScreenshotOnFailureExtension.class)
public class ProductPageTest {

    private static final Logger logger = LoggerFactory.getLogger(ProductPageTest.class);
    private WebDriver driver;
    private ProductPage productPage;
    private ScreenshotOnFailureExtension screenshotExtension;

    @BeforeEach
    public void setUp() {
        String browser = ConfigReader.getProperty("browser");
        driver = DriverFactory.initializeDriver(browser);
        driver.get(ConfigReader.getProperty("baseUrl"));
        screenshotExtension = new ScreenshotOnFailureExtension();
        screenshotExtension.setDriver(driver);
        loginAsStandardUser();
        productPage = new ProductPage(driver);
    }

    private void loginAsStandardUser() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }

    @Test
    @Description("Test Case: Verify that a user can add a product to the shopping cart.")
    public void testAddProductToCart() {
        logger.info("Product page Test");
        productPage.addProductToCart();
        productPage.openCart();
        WebElement cartItem = driver.findElement(By.className("inventory_item_name"));
        Assertions.assertTrue(cartItem.getText().contains("Sauce Labs"), "The product was not added to the cart.");
    }

    @Test
    @Description("Test Case: Verify that the product sorting functionality works correctly (Price low to high).")
    public void testSortProductsByPriceLowToHigh() {
        productPage.sortProductsBy("Price (low to high)");
        List<Double> prices = productPage.getProductPrices().stream()
                .map(priceElement -> Double.parseDouble(priceElement.getText().replace("$", "")))
                .toList();
        for (int i = 0; i < prices.size() - 1; i++) {
            if (prices.get(i) > prices.get(i + 1)) {
                fail("Products are not sorted by price in ascending order.");
            }
        }
    }

    @AfterEach
    public void tearDown() {
        DriverFactory.quitDriver(driver);
    }
}
