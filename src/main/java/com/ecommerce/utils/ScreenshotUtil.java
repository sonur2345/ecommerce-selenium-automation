package com.ecommerce.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

    public static void capture(
            WebDriver driver, String testName) {

        try {

            Files.createDirectories(
                Paths.get("screenshots")
            );

            File source = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);

            String fileName = testName.replaceAll(
                "[^a-zA-Z0-9-_]", "_"
            );

            File destination = new File(
                "screenshots/" + fileName + ".png"
            );

            Files.copy(
                source.toPath(),
                destination.toPath()
            );

            System.out.println(
                "Screenshot saved: " + destination.getAbsolutePath()
            );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
