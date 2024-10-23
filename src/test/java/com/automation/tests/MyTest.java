package com.automation.tests;

import com.automation.pages.LoginPage;
import com.automation.utils.ConfigReader;
import com.automation.utils.DriverFactory;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyTest {
    private static final By usernameField = By.id(ConfigReader.getProperty("login.usernameField"));
    private static final By passwordField = By.id(ConfigReader.getProperty("login.passwordField"));
    private static final By loginButton = By.id(ConfigReader.getProperty("login.loginButton"));
    private static final By errorMessage = By.cssSelector(ConfigReader.getProperty("login.errorMessage"));

    @Test
    public void myTest() {
        WebDriver driver = DriverFactory.initializeDriver(ConfigReader.getProperty("browser"));
        driver.get(ConfigReader.getProperty("baseUrl"));
        driver.findElement(usernameField).sendKeys(ConfigReader.getProperty("username"));
        driver.findElement(passwordField).sendKeys(ConfigReader.getProperty("password")+"abc");
        driver.findElement(loginButton).click();
        assertEquals(LoginPage.getErrorMessage(), driver.findElement(errorMessage).getText());
    }
}
