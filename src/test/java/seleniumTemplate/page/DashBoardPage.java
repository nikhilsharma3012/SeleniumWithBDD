package seleniumTemplate.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import seleniumTemplate.pageserviceprovider.PageEngine;


public class DashBoardPage extends PageEngine {

    @FindBy(css = "button[data-name='navigation-menu/accounting']")
    public WebElement accountingTab;

    @FindBy(css = "a[data-name='navigation-menu/accounting/bank-accounts']")
    public WebElement subMenuBankAccount;

    @FindBy(css = "span[data-automationid='Add Bank Account-button']")
    public WebElement addBankAccountLink;


    public DashBoardPage(WebDriver driver) {
        super(driver);
    }


    public void clickOnAddBankAccountLink() {
        addBankAccountLink.click();
    }

    public String getAddBankAccountText() {
        return addBankAccountLink.getText();
    }

    public void selectSubMenuBankAccount() throws InterruptedException {
        waitForElementToBeClickable(10, accountingTab);
        accountingTab.click();
        subMenuBankAccount.click();
    }



}
