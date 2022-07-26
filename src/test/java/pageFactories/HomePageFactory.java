package pageFactories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class HomePageFactory {

    protected WebDriver driver;


    @FindBy(css = "button[id = react-burger-menu-btn]")
    WebElement menuButton;
    @FindBy(css = "a[class=shopping_cart_link]")
    WebElement cartButton;


    public HomePageFactory(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);

    }


    public void navigateToCart() {
        cartButton.click();
    }
}

