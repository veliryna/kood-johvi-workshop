package com.automation.pages;

import com.automation.utils.ConfigReader;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.automation.utils.DriverFactory.driver;

public class LoginPage {
    private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);
    private static final By usernameField = By.id(ConfigReader.getProperty("login.usernameField"));
    private static final By passwordField = By.id(ConfigReader.getProperty("login.passwordField"));
    private static final By loginButton = By.id(ConfigReader.getProperty("login.loginButton"));
    private static final By errorMessage = By.cssSelector(ConfigReader.getProperty("login.errorMessage"));

    public static void login(String username, String password) {
        logger.info("Logging into sauce demo");
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    public static String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }
}
