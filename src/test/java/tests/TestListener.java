package tests;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.AllureUtils;

import java.io.File;


public class TestListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
        System.out.println("Test " + result.getName() + " started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
        System.out.println("Test " + result.getName() + " finished");
    }

    @Override
    @Attachment(value = "screenshot", type = "image/png")
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        System.out.println("Test " + result.getName() + " failed");
        WebDriver driver = (WebDriver) result.getTestContext().getAttribute("driver");
        AllureUtils.attachScreenshot(driver);

    }
}

