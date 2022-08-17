package pages;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.AllureUtils;

public class LoginPage extends BasePage {

    private final By usernameInput = By.id("user-name");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector(".error-message-container");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean isErrorMessageDisplayed() {
        return driver.findElement(errorMessage).isDisplayed();
    }

    public String getErrorMessageText() {
        return driver.findElement(errorMessage).getText();
    }
@Step("Logging in")
@Attachment(value = "screenshot", type = "image/png")
    public void login(String username, String password) {
        setUsername(username);
        setPassword(password);
        clickLoginButton();
    AllureUtils.attachScreenshot(driver);
    }

    public void setUsername(String username) {
        driver.findElement(usernameInput).sendKeys(username);

    }

    public void setPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);

    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();

    }
}
