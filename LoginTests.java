package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class LoginTests extends BaseTest {

    @Test(description = "positive Login Test", groups = {"Smoke"})
    public void positiveLoginTest() {
        loginPage.setUsername(USERNAME);
        loginPage.setPassword(PASSWORD);
        loginPage.clickLoginButton();
        Assert.assertTrue(productsPage.isProductPageHeaderDisplayed());
    }

    @Test(description = "negative Login Test", dataProvider = "negativeLoginData", groups = {"Negative", "Regression"})
    public void negativeLoginTest(String userName, String password, String errorMessage) {
        loginPage.setUsername(userName);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
        Assert.assertEquals(loginPage.getErrorMessageText(), errorMessage);
    }

    @DataProvider(name = "negativeLoginData")
    public Object[][] negativeLoginTestData() {
        return new Object[][]{
                {"", PASSWORD, "Epic sadface: Username is required"},
                {USERNAME, "", "Epic sadface: Password is required"},
                {"", "", "Epic sadface: Username is required"},
                {"locked_out_user", PASSWORD, "Epic sadface: Sorry, this user has been locked out."},
                {"USERNAME", "secret_sauc", "Epic sadface: Username and password do not match any user in this service"},
        };
    }
}