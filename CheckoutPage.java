package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckoutPage extends HomePage {

    private final By checkoutHeaderContainer = By.cssSelector("span[class = title]");
    private final By cancelButton = By.id("cancel");
    private final By finishButton = By.id("finish");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public String isCheckoutInfoDisplayed() {
        return driver.findElement(checkoutHeaderContainer).getText();
    }

    public void fillInApplication() {
        List<WebElement> inputButtons = driver.findElements(By.cssSelector(".form_input"));
        inputButtons.get(0).sendKeys("Radoslava");
        inputButtons.get(1).sendKeys("vdc");
        inputButtons.get(2).sendKeys("12345");
        By continueButton = By.id("continue");
        driver.findElement(continueButton).click();

    }

    public void cancelCheckout() {
        driver.findElement(cancelButton).click();
    }

    public void finishCheckout() {
        driver.findElement(finishButton).click();
    }

    public String isOverviewInfoDisplayed() {
        return driver.findElement(checkoutHeaderContainer).getText();
    }

    public String isPaymentFinished() {
        return driver.findElement(checkoutHeaderContainer).getText();
    }

}

