package com.ecommerce.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pages.LoginPage;
import com.ecommerce.pages.ProductPage;
import com.ecommerce.pages.CartPage;
import com.ecommerce.pages.CheckoutPage;

import org.testng.annotations.Listeners;
import com.ecommerce.utils.TestListener;



@Listeners(TestListener.class)
public class CheckoutTest extends BaseTest {

    CheckoutPage checkoutPage;

    @BeforeMethod
    public void prepareCheckout() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        ProductPage productPage = new ProductPage(driver);
        productPage.addBackpackToCart();
        productPage.openCart();

        @SuppressWarnings("unused")
		CartPage cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);

        checkoutPage.clickCheckout();
    }

    @Test
    public void verifySuccessfulCheckout() {

        checkoutPage.enterCustomerDetails(
            "Sonu",
            "Kushwaha",
            "492001"
        );

        checkoutPage.clickContinue();

        checkoutPage.clickFinish();

        Assert.assertEquals(
            checkoutPage.getConfirmationMessage(),
            "Thank you for your order!"
        );

        System.out.println("Checkout Test: PASS");
    }

    @Test
    public void verifyMissingFirstName() {

        checkoutPage.enterCustomerDetails(
            "",
            "Kushwaha",
            "492001"
        );

        checkoutPage.clickContinue();

        String error = checkoutPage.getErrorMessage();

        Assert.assertTrue(
            error.contains("First Name is required"),
            "First name validation failed"
        );

        System.out.println("Missing First Name Test: PASS");
    }

    @Test
    public void verifyMissingPostalCode() {

        checkoutPage.enterCustomerDetails(
            "Sonu",
            "Kushwaha",
            ""
        );

        checkoutPage.clickContinue();

        String error = checkoutPage.getErrorMessage();

        Assert.assertTrue(
            error.contains("Postal Code is required"),
            "Postal code validation failed"
        );

        System.out.println("Missing Postal Code Test: PASS");
    }
    
//    @Test
//    public void verifyScreenshotOnFailure() {
//
//        Assert.fail("Intentional failure to test screenshot");
//    }
}