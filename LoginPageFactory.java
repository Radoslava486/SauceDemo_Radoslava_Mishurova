package pageFactories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageFactory {
    protected WebDriver driver;

    @FindBy(id = "user-name")
    WebElement usernameInput;

    @FindBy(id = "password")
    WebElement passwordInput;

    @FindBy(id = "login-button")
    WebElement loginButton;

    @FindBy(css = ".error-message-container")
    WebElement errorMessage;

    public LoginPageFactory(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);

    }


    public boolean isErrorMessageDisplayed() {
        return errorMessage.isDisplayed();
    }

    public String getErrorMessageText() {
        return errorMessage.getText();
    }

    public void login(String username, String password) {
        setUsername(username);
        setPassword(password);
        clickLoginButton();
    }

    public void setUsername(String username) {
        usernameInput.sendKeys(username);

    }

    public void setPassword(String password) {
        passwordInput.sendKeys(password);

    }

    public void clickLoginButton() {
        loginButton.click();

    }


}
