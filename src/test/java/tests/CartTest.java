package tests;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;


public class CartTest extends BaseTest {

    @Test(groups = {"Regression"})
    @Description("Remove Item From the Cart")
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

    @Test(groups = {"Smoke"})
    @Description("Positive Cart Test")
    public void positiveCartTest() {
        loginPage.login(USERNAME, PASSWORD);
        productsPage.openItemByName(PRODUCT_NAME);
        itemDetailsPage.clickAddToCartButton();
        itemDetailsPage.navigateToCart();
        cartPage.clickCheckoutButton();
        Assert.assertEquals(checkoutPage.findCheckoutInfoText(), "CHECKOUT: YOUR INFORMATION", "Personal info is required");


    }

    @Test(groups = {"Smoke"})
    @Description("Verify Added Item Quantity In The Cart")
    public void verifyItemQuantity() {
        loginPage.login(USERNAME, PASSWORD);
        productsPage.openItemByName(PRODUCT_NAME);
        itemDetailsPage.clickAddToCartButton();
        itemDetailsPage.navigateToCart();
        Assert.assertEquals(cartPage.getItemQuantity(), "1");

    }


    @Test(groups = {"Smoke"})
    @Description("Verify Added Item By Name, Price and Description on Cart Page")
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

