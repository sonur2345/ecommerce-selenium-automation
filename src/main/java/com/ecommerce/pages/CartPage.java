package com.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {

    WebDriver driver;

    By cartItem = By.className("cart_item");
    By productName = By.className("inventory_item_name");
    By removeButton = By.id("remove-sauce-labs-backpack");
    By continueShopping = By.id("continue-shopping");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public int getCartItemCount() {
        return driver.findElements(cartItem).size();
    }

    public String getProductName() {
        return driver.findElement(productName).getText();
    }

    public void removeProduct() {
        driver.findElement(removeButton).click();
    }

    public void continueShopping() {
        driver.findElement(continueShopping).click();
    }

}