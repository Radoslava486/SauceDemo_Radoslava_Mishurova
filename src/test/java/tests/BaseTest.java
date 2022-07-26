package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pages.*;

import java.util.concurrent.TimeUnit;

@Listeners(TestListener.class)
public class BaseTest {
    final static String USERNAME = "standard_user";
    final static String PASSWORD = "secret_sauce";
    final static String PRODUCT_NAME = "Sauce Labs Fleece Jacket";
    protected LoginPage loginPage;
    protected ProductsPage productsPage;
    protected ItemDetailsPage itemDetailsPage;
    protected CartPage cartPage;
    protected CheckoutPage checkoutPage;
    protected WebDriver driver;
    protected WebDriverWait wait;


    @Parameters({"browser"})
    @BeforeClass(alwaysRun = true)
    public void setUp(@Optional("chrome") String browserName, ITestContext testContext) throws Exception {
        if (browserName.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browserName.equals("opera")) {
            WebDriverManager.operadriver().setup();
            driver = new OperaDriver();
        } else {
            throw new Exception("Undefined browser type");
        }
        wait = new WebDriverWait(driver, 30);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        itemDetailsPage = new ItemDetailsPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        testContext.setAttribute("driver", driver);
    }

    @BeforeMethod(alwaysRun = true)
    public void navigate() {
        driver.get("https://www.saucedemo.com/");
    }

    @AfterMethod(alwaysRun = true)
    public void clearCookies() {
        driver.manage().deleteAllCookies();
        ((JavascriptExecutor) driver).executeScript("window.localStorage.clear();");
        ((JavascriptExecutor) driver).executeScript("window.sessionStorage.clear();");

    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }


}
