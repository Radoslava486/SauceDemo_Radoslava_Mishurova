package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ProductsPage extends HomePage {

    private final By productsSortContainer = By.cssSelector("select[class = product_sort_container]");
    private final By addToCartButton = By.cssSelector("button[id^= add-to-cart]");
    private final By productLink = By.cssSelector("a[id$=_link]");
    private final String productContainerLocator
            = "//div[@class = 'inventory_item_name' and text() = '%s']/ancestor::div[@class='inventory_item']";
    private final By productNameLocator = By.cssSelector("div[class = inventory_item_name]");
    private final By productDescriptionLocator = By.cssSelector("div[class = inventory_item_desc]");
    private final By productPriceLocator = By.cssSelector("div[class = inventory_item_price]");
    private final By productsPageHeader = By.id("header_container");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isProductPageHeaderDisplayed() {

        return driver.findElement(productsPageHeader).isDisplayed();
    }

    public boolean isProductsSorterDisplayed() {

        return driver.findElement(productsSortContainer).isDisplayed();
    }

    public WebElement getProductContainerByName(String productName) {
        return driver.findElement(By.xpath(String.format(productContainerLocator, productName)));
    }

    public boolean isProductPresent(String productName) {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        try {
            driver.findElement(productNameLocator);
        } catch (NoSuchElementException exception) {
            return false;
        } finally {
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        }
        return true;
    }


    public String getProductDescription(String productName) {
        WebElement productContainer = getProductContainerByName(productName);
        return productContainer.findElement(productDescriptionLocator).getText();

    }

    public String getProductPrice(String productName) {
        WebElement productContainer = getProductContainerByName(productName);
        return productContainer.findElement(productPriceLocator).getText();

    }

    public void openItemByName(String productName) {
        WebElement productContainer = getProductContainerByName(productName);
        productContainer.findElement(productLink).click();
    }


    public void clickAddToCartButton(String productName) {
        WebElement productContainer = getProductContainerByName(productName);
        productContainer.findElement(addToCartButton).click();
    }

    public void sortProductsFromLowToHighPrice() {
        WebElement dropdownElement = driver.findElement(productsSortContainer);
        Select select = new Select(dropdownElement);
        select.selectByVisibleText("Price (low to high)");
    }

    public List<String> getActualSortedItemOrder() {
        return driver.findElements(By.className("inventory_item_name")).stream().map(WebElement::getText).toList();
    }

}
