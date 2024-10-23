package com.automation.utils;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.openqa.selenium.WebDriver;

public class ScreenshotOnFailureExtension implements TestExecutionExceptionHandler {

    private WebDriver driver;

    // This method is used to pass the WebDriver for each test method
    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        if (driver != null) {
            // Capture and attach screenshot when test fails
            AllureHelper.attachScreenshot(driver);
        }
        // Re-throw the exception to make sure the test is marked as failed
        throw throwable;
    }
}
