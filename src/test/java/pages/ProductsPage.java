package pages;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.AllureUtils;

import java.util.List;
import java.util.Set;
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
    private final By twitterLink = By.linkText("Twitter");

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

   @Step("Checking if product is present on the page")
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
@Step("Opening item by Name")
    public void openItemByName(String productName) {
        WebElement productContainer = getProductContainerByName(productName);
        productContainer.findElement(productLink).click();
    }


   @Step("Adding item to cart")
   @Attachment(value = "screenshot", type = "image/png")
   public void clickAddToCartButton(String productName) {
        WebElement productContainer = getProductContainerByName(productName);
        productContainer.findElement(addToCartButton).click();
       AllureUtils.attachScreenshot(driver);
    }

    @Step("Sorting products")
    public void sortProducts(String option) {
        WebElement dropdownElement = driver.findElement(productsSortContainer);
        Select select = new Select(dropdownElement);
        select.selectByVisibleText(option);
    }
@Step("Getting sorted item order")
    public List<String> getActualSortedItemOrder() {
        return driver.findElements(By.className("inventory_item_name")).stream().map(WebElement::getText).toList();
    }



    public List<Double> getActualSortedItemsPrices() {
        return driver.findElements(productPriceLocator).stream().map(p -> Double.parseDouble(
                        (p.getText().replace("$", ""))))
                .toList();


    }
    @Attachment(value = "screenshot", type = "image/png")
    public void goToTwitter() {
        driver.findElement(twitterLink).click();
        Set<String> allWindows = driver.getWindowHandles();
        List<String> allWindowsList = allWindows.stream().toList();
        driver.switchTo().window(allWindowsList.get(1));
        AllureUtils.attachScreenshot(driver);

    }
    public String getLink() {
        return driver.getCurrentUrl();
    }

    public void waitForPageLoaded() {
        new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
            }
        };
    }

    }



