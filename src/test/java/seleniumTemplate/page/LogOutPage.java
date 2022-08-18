package seleniumTemplate.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import seleniumTemplate.pageserviceprovider.PageEngine;


public class LogOutPage extends PageEngine {

    @FindBy(css = "button[data-automationid='xrh-addon-user-iconbutton']")
    public WebElement userAccount;

    @FindBy(xpath = "//*[@data-automationid='xrh-addon-user-body' and @class ='xrh-dropdown--body']//a[@href='/logout']")
    WebElement logOutButton;

    public LogOutPage(WebDriver driver) {
        super(driver);
    }

    public void clickLogOutButton() {
        logOutButton.click();
    }


    public void selectUserAccount() {
        userAccount.click();
    }


}
