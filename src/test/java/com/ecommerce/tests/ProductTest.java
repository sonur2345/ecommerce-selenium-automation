package com.ecommerce.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pages.LoginPage;
import com.ecommerce.pages.ProductPage;

public class ProductTest extends BaseTest {

    ProductPage productPage;

    @BeforeMethod
    public void loginToApplication() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("standard_user", "secret_sauce");

        productPage = new ProductPage(driver);
    }

    @Test
    public void verifyProductsPage() {

        Assert.assertEquals(
            productPage.getPageTitle(),
            "Products"
        );

        Assert.assertEquals(
            productPage.getProductCount(),
            6
        );

        System.out.println("Products Page Test: PASS");
    }

    @Test
    public void verifyProductSorting() {

        productPage.sortProducts("za");

        System.out.println("Sorting Z-A: PASS");
    }

    @Test
    public void verifyAddToCart() {

        productPage.addBackpackToCart();

        Assert.assertEquals(
            productPage.getCartCount(),
            "1"
        );

        System.out.println("Add To Cart Test: PASS");
    }
}