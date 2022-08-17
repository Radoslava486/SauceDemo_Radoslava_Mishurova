package pages;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.AllureUtils;

import java.util.List;

public class CheckoutPage extends HomePage {

    private final By checkoutHeaderContainer = By.cssSelector("span[class = title]");
    private final By cancelButton = By.id("cancel");
    private final By finishButton = By.id("finish");

    private final By inputButtonsLocator = By.cssSelector(".form_input");
    private final By continueButton = By.id("continue");



    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public String findCheckoutInfoText() {
        return driver.findElement(checkoutHeaderContainer).getText();
    }
@Step("Filling in application form")
@Attachment(value = "screenshot", type = "image/png")
    public void fillInApplication(String testName, String testSurname, String testZipcode) {
        List<WebElement> inputButtons = driver.findElements(inputButtonsLocator);
        inputButtons.get(0).sendKeys(testName);
        inputButtons.get(1).sendKeys(testSurname);
        inputButtons.get(2).sendKeys(testZipcode);
        driver.findElement(continueButton).click();
    AllureUtils.attachScreenshot(driver);
    }
@Step("Cancelling checkout")
    public void cancelCheckout() {
        driver.findElement(cancelButton).click();
    }
@Step("Finishing checkout")
@Attachment(value = "screenshot", type = "image/png")
    public void finishCheckout() {
        driver.findElement(finishButton).click();
    AllureUtils.attachScreenshot(driver);
    }

    public String findOverviewInfoText() {
        return driver.findElement(checkoutHeaderContainer).getText();
    }

    public String findSuccessfulPaymentText() {
        return driver.findElement(checkoutHeaderContainer).getText();
    }

}

