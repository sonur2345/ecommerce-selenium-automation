package com.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class ProductPage {

    WebDriver driver;

    By pageTitle = By.className("title");
    By productItems = By.className("inventory_item");
    By sortDropdown = By.className("product_sort_container");
    By addToCartButton = By.id("add-to-cart-sauce-labs-backpack");
    By cartBadge = By.className("shopping_cart_badge");
    By cartIcon = By.className("shopping_cart_link");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageTitle() {
        return driver.findElement(pageTitle).getText();
    }

    public int getProductCount() {
        return driver.findElements(productItems).size();
    }

    public void sortProducts(String option) {
        Select select = new Select(driver.findElement(sortDropdown));
        select.selectByValue(option);
    }

    public void addBackpackToCart() {
        driver.findElement(addToCartButton).click();
    }

    public String getCartCount() {
        return driver.findElement(cartBadge).getText();
    }

	public void openCart() {
		driver.findElement(cartIcon).click();
		
	}
}