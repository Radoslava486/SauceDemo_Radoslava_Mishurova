package pageFactories;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPageFactory extends HomePageFactory {

    protected WebDriver driver;

    @FindBy(id = "continue-shopping")
    WebElement continueShoppingButton;

    @FindBy(css = "button[id^= remove-]")
    WebElement removeItemButton;

    @FindBy(id = "checkout")
    WebElement checkoutButton;

    @FindBy(css = "div[class=cart_quantity]")
    WebElement quantityButton;

    @FindBy(css = "button[id$ = _title_link]")
    WebElement itemButton;

    public CartPageFactory(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    public void clickMenuButton() {
        menuButton.click();
    }

    public void clickContinueShoppingButton() {
        continueShoppingButton.click();
    }

    public void clickRemoveItemButton() {
        removeItemButton.click();
    }

    //git clone https://Radoslava486:94rovode@github.com/Radoslava486/SauceDemo_Radoslava_Mishurova.git
    public void clickCheckoutButton() {
        checkoutButton.click();
    }

    public void showItemInfo() {
        itemButton.click();
    }

    public String getItemQuantity() {

        return quantityButton.getText();
    }

    public boolean isEmpty() {
        try {
            driver.findElement((By) itemButton);
        } catch (NoSuchElementException exception) {
            return true;
        }
        return false;

    }
}
