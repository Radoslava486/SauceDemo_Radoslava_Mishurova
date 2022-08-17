package tests;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;


public class CheckoutTests extends BaseTest {


    @Test(groups = {"Smoke"})
    @Description("positive Checkout Test")
    public void positiveCheckoutTest() {
        loginPage.login(USERNAME, PASSWORD);
        productsPage.openItemByName(PRODUCT_NAME);
        itemDetailsPage.clickAddToCartButton();
        itemDetailsPage.navigateToCart();
        cartPage.clickCheckoutButton();
        checkoutPage.fillInApplication("Radoslava", "Mishurova", "11111");
        Assert.assertEquals(checkoutPage.findOverviewInfoText(), "CHECKOUT: OVERVIEW");
        checkoutPage.finishCheckout();
        Assert.assertEquals(checkoutPage.findSuccessfulPaymentText(), "CHECKOUT: COMPLETE!", " Payment's made. Congratulations ");

    }

    @Test(groups = {"Regression"})
    @Description("Cancel Checkout")
    public void cancelCheckoutTest() {
        loginPage.login(USERNAME, PASSWORD);
        productsPage.openItemByName(PRODUCT_NAME);
        itemDetailsPage.clickAddToCartButton();
        itemDetailsPage.navigateToCart();
        cartPage.clickCheckoutButton();
        checkoutPage.fillInApplication("Radoslava", "Mishurova", "11111");
        Assert.assertEquals(checkoutPage.findOverviewInfoText(), "CHECKOUT: OVERVIEW");
        checkoutPage.cancelCheckout();
        Assert.assertTrue(productsPage.isProductsSorterDisplayed(), "You've been readdressed to product page");

    }
}
