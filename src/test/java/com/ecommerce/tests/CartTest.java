package com.ecommerce.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pages.LoginPage;
import com.ecommerce.pages.ProductPage;
import com.ecommerce.pages.CartPage;

public class CartTest extends BaseTest {

    CartPage cartPage;

    @BeforeMethod
    public void prepareCart() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("standard_user", "secret_sauce");

        ProductPage productPage = new ProductPage(driver);

        productPage.addBackpackToCart();

        productPage.openCart();

        cartPage = new CartPage(driver);
    }

    @Test
    public void verifyCartProduct() {

        Assert.assertEquals(
            cartPage.getCartItemCount(),
            1,
            "Cart should contain one product"
        );

        Assert.assertEquals(
            cartPage.getProductName(),
            "Sauce Labs Backpack"
        );

        System.out.println("Cart Product Test: PASS");
    }

    @Test
    public void verifyRemoveProduct() {

        cartPage.removeProduct();

        Assert.assertEquals(
            cartPage.getCartItemCount(),
            0,
            "Product was not removed"
        );

        System.out.println("Remove Product Test: PASS");
    }

    @Test
    public void verifyContinueShopping() {

        cartPage.continueShopping();

        Assert.assertTrue(
            driver.getCurrentUrl().contains("inventory.html"),
            "Continue Shopping navigation failed"
        );

        System.out.println("Continue Shopping Test: PASS");
    }
}