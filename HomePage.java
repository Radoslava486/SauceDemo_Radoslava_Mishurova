package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class HomePage extends BasePage {

    protected final By menuButton = By.cssSelector("button[id = react-burger-menu-btn]");
    private final By cartButton = By.cssSelector("a[class=shopping_cart_link]");

    public HomePage(WebDriver driver) {
        super(driver);
    }


    public void navigateToCart() {
        driver.findElement(cartButton).click();
    }
}
