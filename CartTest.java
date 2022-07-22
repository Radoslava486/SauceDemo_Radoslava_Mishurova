package tests;

import org.testng.Assert;
import org.testng.annotations.Test;


public class CartTest extends BaseTest {

    @Test(description = "Remove Item From the Cart", groups = {"Regression"})
    public void removeItemCartTest() {
        loginPage.login(USERNAME, PASSWORD);
        productsPage.openItemByName(PRODUCT_NAME);
        itemDetailsPage.clickAddToCartButton();
        itemDetailsPage.navigateToCart();
        cartPage.clickRemoveItemButton();
        Assert.assertTrue(cartPage.isEmpty(), "Cart is empty");
        cartPage.clickContinueShoppingButton();
        Assert.assertTrue(productsPage.isProductsSorterDisplayed(), "Product page is open");

    }

    @Test(description = "Positive Cart Test", groups = {"Smoke"})
    public void positiveCartTest() {
        loginPage.login(USERNAME, PASSWORD);
        productsPage.openItemByName(PRODUCT_NAME);
        itemDetailsPage.clickAddToCartButton();
        itemDetailsPage.navigateToCart();
        cartPage.clickCheckoutButton();
        Assert.assertEquals(checkoutPage.isCheckoutInfoDisplayed(), "CHECKOUT: YOUR INFORMATION", "Personal info is required");


    }

    @Test(description = "Verify Added Item Quantity In The Cart", groups = {"Smoke"})
    public void verifyItemQuantity() {
        loginPage.login(USERNAME, PASSWORD);
        productsPage.openItemByName(PRODUCT_NAME);
        itemDetailsPage.clickAddToCartButton();
        itemDetailsPage.navigateToCart();
        Assert.assertEquals(cartPage.getItemQuantity(), "1");

    }


    @Test(description = "Verify Added Item By Name, Price and Description on Cart Page", groups = {"Smoke"})
    public void verifyItemByNameAndPriceOnCartPage() {
        loginPage.login(USERNAME, PASSWORD);
        productsPage.openItemByName(PRODUCT_NAME);
        itemDetailsPage.clickAddToCartButton();
        itemDetailsPage.navigateToCart();
        Assert.assertEquals(cartPage.getItemName(), PRODUCT_NAME);
        Assert.assertEquals(cartPage.getItemDescription(), "It's not every day that you come across a midweight quarter-zip fleece jacket capable of handling everything from a relaxing day outdoors to a busy day at the office.");
        Assert.assertEquals(cartPage.getItemPrice(), "$49.99");

    }
}

