package com.ecommerce.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstSeleniumTest {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://www.saucedemo.com/");

        // Enter Username
        driver.findElement(By.id("user-name"))
              .sendKeys("standard_user");

        // Enter Password
        driver.findElement(By.id("password"))
              .sendKeys("secret_sauce");

        // Click Login
        driver.findElement(By.id("login-button"))
              .click();

        // Print page title
        System.out.println("Page Title: " + driver.getTitle());

        // Print current URL
        System.out.println("Current URL: " + driver.getCurrentUrl());

        driver.quit();
    }
}