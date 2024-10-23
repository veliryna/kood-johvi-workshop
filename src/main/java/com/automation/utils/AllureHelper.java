package com.automation.utils;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class AllureHelper {

    @Attachment(value = "Screenshot", type = "image/png")
    public static void attachScreenshot(WebDriver driver) {
        ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    public static void attachText(String name, String content) {
        Allure.addAttachment(name, content);
    }
}
