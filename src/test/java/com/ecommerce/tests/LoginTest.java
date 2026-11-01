package com.ecommerce.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pages.LoginPage;

@SuppressWarnings("unused")
public class LoginTest extends BaseTest {

    @Test
    public void validLoginTest() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("standard_user", "secret_sauce");

        Assert.assertTrue(
            driver.getCurrentUrl().contains("inventory.html"),
            "Login failed"
        );

        System.out.println("Valid Login: PASS");
    }

    @Test
    public void invalidLoginTest() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("standard_user", "wrong_password");

        String error = loginPage.getErrorMessage();

        Assert.assertTrue(
            error.length() > 0,
            "Error message not displayed"
        );

        System.out.println("Invalid Login: PASS");
    }
}