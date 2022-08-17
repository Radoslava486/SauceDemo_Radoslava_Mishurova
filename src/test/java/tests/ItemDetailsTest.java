package tests;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;


public class ItemDetailsTest extends BaseTest {

    final static String PRODUCT_NAME = "Sauce Labs Fleece Jacket";

    @Test(groups = {"Smoke"})
    @Description("Verify Item By Name, Price and Description on Item Details Page")
    public void verifyItemByNameAndPriceOnDetailsPage() {
        loginPage.login(USERNAME, PASSWORD);
        productsPage.openItemByName(PRODUCT_NAME);
        Assert.assertEquals(itemDetailsPage.getItemName(), PRODUCT_NAME);
        Assert.assertEquals(itemDetailsPage.getItemDescription(), "It's not every day that you come across a midweight quarter-zip fleece jacket capable of handling everything from a relaxing day outdoors to a busy day at the office.");
        Assert.assertEquals(itemDetailsPage.getItemPrice(), "$49.99");
    }

    @Test(groups = {"Regression"})
    @Description("Go to Home Page from Item Details Page")
    public void goToHomePageFromDetailsPage() {
        loginPage.login(USERNAME, PASSWORD);
        productsPage.openItemByName(PRODUCT_NAME);
        itemDetailsPage.clickBackToProductsButton();
        Assert.assertTrue(productsPage.isProductsSorterDisplayed());

    }

}
