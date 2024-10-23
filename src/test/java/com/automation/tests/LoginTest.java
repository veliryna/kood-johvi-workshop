package com.automation.tests;

import com.automation.pages.LoginPage;
import com.automation.utils.AllureHelper;
import com.automation.utils.ConfigReader;
import com.automation.utils.DriverFactory;
import com.automation.utils.ScreenshotOnFailureExtension;
import io.qameta.allure.Description;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import java.util.Optional;
import static com.automation.utils.DriverFactory.driver;

@ExtendWith(ScreenshotOnFailureExtension.class)
public class LoginTest {

    @BeforeEach
    public void setup(){
        String browser = ConfigReader.getProperty("browser");
        driver = DriverFactory.initializeDriver(browser);
        driver.get(ConfigReader.getProperty("baseUrl"));
    }

    @AfterEach
    public void tearDown() {
        DriverFactory.quitDriver(driver);
    }

    @Test
    @Description("Valid login credentials")
    public void validLoginTest() {
        String username = ConfigReader.getProperty("username");
        String password = ConfigReader.getProperty("password");
        LoginPage.login(username, password);
        Optional.ofNullable(driver.getCurrentUrl())
                .ifPresentOrElse(url -> Assertions.assertTrue(url.contains("/inventory.html")),
                        () -> Assertions.fail("Current URL is null"));
        AllureHelper.attachText("Login Info", "User logged in with standard_user");
    }

    @Test
    @Description("Invalid username with valid password")
    public void invalidUsernameTest() {

        LoginPage.login("invalid_user", ConfigReader.getProperty("password"));
        Assertions.assertTrue(LoginPage.getErrorMessage().contains(ConfigReader.getProperty("invalid.credentialError")));
        AllureHelper.attachText("Login Info", "User logged in with Invalid username with valid password");
    }

    @Test
    @Description("Valid username with invalid password")
    public void invalidPasswordTest() {

        LoginPage.login(ConfigReader.getProperty("username"), "wrong_password");
        Assertions.assertTrue(LoginPage.getErrorMessage().contains(ConfigReader.getProperty("invalid.credentialError")));
        AllureHelper.attachText("Login Info", "User logged in with Valid username with invalid password");
    }

    @Test
    @Description("Blank username and password")
    public void blankLoginTest() {
        LoginPage.login("", "");
        Assertions.assertTrue(LoginPage.getErrorMessage().contains(ConfigReader.getProperty("blank.credentialError")));
        AllureHelper.attachText("Login Info", "User logged in with Blank username and password");
    }

}

