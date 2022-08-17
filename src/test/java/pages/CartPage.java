package pages;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import utils.AllureUtils;


public class CartPage extends HomePage {

    private final By continueShoppingButton = By.id("continue-shopping");
    private final By removeItemButton = By.cssSelector("button[id^= remove-]");
    private final By checkoutButton = By.id("checkout");
    private final By itemButton = By.cssSelector("button[id$ = _title_link]");
    private final By itemName = By.cssSelector(".inventory_item_name");
    private final By itemDescription = By.cssSelector(".inventory_item_desc");
    private final By itemPrice = By.cssSelector(".inventory_item_price");
    By quantityButton = By.cssSelector("div[class=cart_quantity]");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Step("Continuing shopping")
    @Attachment(value = "screenshot", type = "image/png")
    public void clickContinueShoppingButton() {
        driver.findElement(continueShoppingButton).click();
        AllureUtils.attachScreenshot(driver);
    }

@Step("Removing item from the cart")
@Attachment(value = "screenshot", type = "image/png")
    public void clickRemoveItemButton() {
        driver.findElement(removeItemButton).click();
    AllureUtils.attachScreenshot(driver);
    }

@Step("Proceeding to checkout")
@Attachment(value = "screenshot", type = "image/png")
    public void clickCheckoutButton() {
        driver.findElement(checkoutButton).click();
    }

    public String getItemQuantity() {
        return driver.findElement(quantityButton).getText();
    }
@Step("Checking whether cart is empty")
@Attachment(value = "screenshot", type = "image/png")
    public boolean isEmpty() {
        try {
            driver.findElement(itemButton);
        } catch (NoSuchElementException exception) {
            return true;
        }
        return false;

    }

    public String getItemName() {
        return driver.findElement(itemName).getText();
    }

    public String getItemDescription() {
        return driver.findElement(itemDescription).getText();
    }

    public String getItemPrice() {
        return driver.findElement(itemPrice).getText();
    }

}

