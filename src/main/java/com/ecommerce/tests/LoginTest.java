package com.ecommerce.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() {

        return new Object[][] {
            {"standard_user", "secret_sauce", "valid"},
            {"standard_user", "wrong_password", "invalid"},
            {"locked_out_user", "secret_sauce", "locked"}
        };
    }

    @Test(dataProvider = "loginData")
    public void loginTest(String username, String password, String type) {

        WebDriverWait wait = new WebDriverWait(
            driver, Duration.ofSeconds(10)
        );

        // Wait for username field
        wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.id("user-name")
            )
        ).sendKeys(username);

        // Enter password
        driver.findElement(By.id("password")).sendKeys(password);

        // Wait until login button is clickable
        wait.until(
            ExpectedConditions.elementToBeClickable(
                By.id("login-button")
            )
        ).click();

        if (type.equals("valid")) {

            wait.until(
                ExpectedConditions.urlContains("inventory.html")
            );

            Assert.assertTrue(
                driver.getCurrentUrl().contains("inventory.html")
            );

            System.out.println("Valid Login: PASS");

        } else {

            String errorMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("[data-test='error']")
                )
            ).getText();

            Assert.assertTrue(
                errorMessage.length() > 0,
                "Error message not displayed"
            );

            System.out.println(type + " Login: PASS");
            System.out.println("Error: " + errorMessage);
        }
    }
    
    
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}