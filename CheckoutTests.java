package tests;

import org.testng.Assert;
import org.testng.annotations.Test;


public class CheckoutTests extends BaseTest {


    @Test(description = "positive Checkout Test", groups = {"Smoke"})
    public void positiveCheckoutTest() {
        loginPage.login(USERNAME, PASSWORD);
        productsPage.openItemByName(PRODUCT_NAME);
        itemDetailsPage.clickAddToCartButton();
        itemDetailsPage.navigateToCart();
        cartPage.clickCheckoutButton();
        checkoutPage.fillInApplication();
        Assert.assertEquals(checkoutPage.isOverviewInfoDisplayed(), "CHECKOUT: OVERVIEW");
        checkoutPage.finishCheckout();
        Assert.assertEquals(checkoutPage.isPaymentFinished(), "CHECKOUT: COMPLETE!", " Payment's made. Congratulations ");

    }

    @Test(description = "Cancel Checkout", groups = {"Regression"})
    public void cancelCheckoutTest() {
        loginPage.login(USERNAME, PASSWORD);
        productsPage.openItemByName(PRODUCT_NAME);
        itemDetailsPage.clickAddToCartButton();
        itemDetailsPage.navigateToCart();
        cartPage.clickCheckoutButton();
        checkoutPage.fillInApplication();
        Assert.assertEquals(checkoutPage.isOverviewInfoDisplayed(), "CHECKOUT: OVERVIEW");
        checkoutPage.cancelCheckout();
        Assert.assertTrue(productsPage.isProductsSorterDisplayed(), "You've been readdressed to product page");

    }
}
