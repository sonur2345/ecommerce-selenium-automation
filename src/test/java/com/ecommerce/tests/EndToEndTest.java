package com.ecommerce.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pages.LoginPage;
import com.ecommerce.pages.ProductPage;
import com.ecommerce.pages.CartPage;
import com.ecommerce.pages.CheckoutPage;

public class EndToEndTest extends BaseTest {

    @Test
    public void completePurchaseTest() {

        // Step 1: Login
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("standard_user", "secret_sauce");

        Assert.assertTrue(
            driver.getCurrentUrl().contains("inventory.html"),
            "Login failed"
        );

        System.out.println("Step 1: Login PASS");

        // Step 2: Add product
        ProductPage productPage = new ProductPage(driver);

        productPage.addBackpackToCart();

        Assert.assertEquals(
            productPage.getCartCount(),
            "1"
        );

        System.out.println("Step 2: Add to Cart PASS");

        // Step 3: Open Cart
        productPage.openCart();

        CartPage cartPage = new CartPage(driver);

        Assert.assertEquals(
            cartPage.getProductName(),
            "Sauce Labs Backpack"
        );

        System.out.println("Step 3: Cart Verification PASS");

        // Step 4: Checkout
        CheckoutPage checkoutPage = new CheckoutPage(driver);

     // Click Checkout
     checkoutPage.clickCheckout();

     // Wait for Checkout Information page
     WebDriverWait wait = new WebDriverWait(
         driver, Duration.ofSeconds(10)
     );

     wait.until(
         ExpectedConditions.visibilityOfElementLocated(
             By.id("first-name")
         )
     );

     // Enter Customer Details
     checkoutPage.enterCustomerDetails(
         "Sonu",
         "Kushwaha",
         "492001"
     );

        checkoutPage.clickContinue();

        // Step 5: Finish Order
        checkoutPage.clickFinish();

        Assert.assertEquals(
            checkoutPage.getConfirmationMessage(),
            "Thank you for your order!"
        );

        System.out.println("Step 4: Order Confirmation PASS");
    }
}
