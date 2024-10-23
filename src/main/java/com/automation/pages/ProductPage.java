package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class ProductPage {
    WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    private final By addToCartButton = By.xpath("//button[text()='Add to cart']");
    private final By cartButton = By.className("shopping_cart_link");
    private final By productSortDropdown = By.className("product_sort_container");
    private final By inventoryItems = By.className("inventory_item_price");

    // Add a product to the cart
    public void addProductToCart() {
        driver.findElement(addToCartButton).click();
    }

    // Open the shopping cart
    public void openCart() {
        driver.findElement(cartButton).click();
    }

    // Sort products by a specified option
    public void sortProductsBy(String option) {
        Select sortDropdown = new Select(driver.findElement(productSortDropdown));
        sortDropdown.selectByVisibleText(option);
    }

    // Get a list of product prices
    public List<WebElement> getProductPrices() {
        return driver.findElements(inventoryItems);
    }
}

