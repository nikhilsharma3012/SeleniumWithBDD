package seleniumTemplate.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import seleniumTemplate.pageserviceprovider.PageEngine;


public class LoginPage extends PageEngine {

    @FindBy(xpath = "//a[contains(text(),'Log in')]")
    public WebElement loginButton;

    @FindBy(css = "input[data-automationid = Username--input]")
    public WebElement emailAddressLabel;

    @FindBy(css = "input[data-automationid = PassWord--input]")
    public WebElement passwordLabel;


    @FindBy(css = "button[data-automationid=LoginSubmit--button]")
    public WebElement loginSubmitButton;


    public LoginPage(WebDriver driver) {
        super(driver);
    }


    public void setLoginCredentials(String userName, String password) {
        waitForVisibilityOfElement(10, emailAddressLabel);
        emailAddressLabel.sendKeys(userName);
        passwordLabel.sendKeys(password);
    }


    public void clickLogIn() {
        loginButton.click();
    }


    public void clickLoginSubmitButton() {
        loginSubmitButton.click();
    }


}
