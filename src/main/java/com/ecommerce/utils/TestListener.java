package com.ecommerce.utils;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.ecommerce.base.BaseTest;

public class TestListener implements ITestListener {

    @Override
    public void onTestSuccess(ITestResult result) {

        System.out.println(
            "TEST PASSED: " + result.getName()
        );
    }

    @Override
    public void onTestFailure(ITestResult result) {

        System.out.println(
            "TEST FAILED: " + result.getName()
        );

        Object testClass = result.getInstance();

        if (testClass instanceof BaseTest) {

            BaseTest baseTest = (BaseTest) testClass;

            ScreenshotUtil.capture(
                baseTest.getDriver(),
                result.getName()
            );
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        System.out.println(
            "TEST SKIPPED: " + result.getName()
        );
    }
}