package tests;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;


public class ProductsPageTest extends BaseTest {

    @Test(description = "Sort Products by Price from Low to High", groups = {"Regression"})
    public void sortProductsByPriceFromLowToHighTest() {
        loginPage.login(USERNAME, PASSWORD);
        productsPage.sortProductsFromLowToHighPrice();
        List<WebElement> sortedItems = driver.findElements(By.className("inventory_item_name"));
        //тут есть вариант обойтись без локатора?
        Assert.assertEquals(sortedItems.get(0).getText(), "Sauce Labs Onesie");
        Assert.assertEquals(sortedItems.get(5).getText(), "Sauce Labs Fleece Jacket");

    }

    @Test(description = "Verify all Item Names, Prices and Descriptions on Products Page", dataProvider = "itemDataAssert", groups = {"Regression"})
    public void verifyItemNamesDescriptionsAndPricesTest(String name, String description, String price) {
        loginPage.login(USERNAME, PASSWORD);
        Assert.assertTrue(productsPage.isProductPresent(name));
        Assert.assertEquals(productsPage.getProductDescription(name), description);
        Assert.assertEquals(productsPage.getProductPrice(name), price);
    }

    @DataProvider(name = "itemDataAssert")
    public Object[][] verifyItemInfoData() {
        return new Object[][]{
                {"Sauce Labs Backpack", "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.", "$29.99"},
                {"Sauce Labs Bike Light", "A red light isn't the desired state in testing but it sure helps when riding your bike at night. Water-resistant with 3 lighting modes, 1 AAA battery included.", "$9.99"},
                {"Sauce Labs Bolt T-Shirt", "Get your testing superhero on with the Sauce Labs bolt T-shirt. From American Apparel, 100% ringspun combed cotton, heather gray with red bolt.", "$15.99"},
                {"Sauce Labs Fleece Jacket", "It's not every day that you come across a midweight quarter-zip fleece jacket capable of handling everything from a relaxing day outdoors to a busy day at the office.", "$49.99"},
                {"Sauce Labs Onesie", "Rib snap infant onesie for the junior automation engineer in development. Reinforced 3-snap bottom closure, two-needle hemmed sleeved and bottom won't unravel.", "$7.99"},
                {"Test.allTheThings() T-Shirt (Red)", "This classic Sauce Labs t-shirt is perfect to wear when cozying up to your keyboard to automate a few tests. Super-soft and comfy ringspun combed cotton.", "$15.99"}
        };
    }
}
