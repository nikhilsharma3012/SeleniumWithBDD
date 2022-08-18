package seleniumTemplate.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import seleniumTemplate.pageserviceprovider.PageEngine;

import java.util.List;


public class AddBankAccountDetailsPage extends PageEngine {


    @FindBy(css = "div[data-automationid='accountName'] div div div input")
    public List<WebElement> accountNameLabel;


    @FindBy(css = "div[data-automationid='accountType'] div div div input")
    public List<WebElement> accountTypeLabel;


    @FindBy(css = "li[class='ba-combo-list-item']")
    public List<WebElement> comboList;


    @FindBy(xpath = "//*[contains(@id,'accountDetailGeneric') and @data-ref='innerCt']//*[contains(@id,'accountnumber') and @type='text']")
    public WebElement accountNumber;


    @FindBy(css = "a[data-automationid='continueButton']")
    public WebElement continueButton;

    @FindBy(css = "a[data-automationid='connectbank-buttonIHaveAForm']")
    public WebElement connectBankForm;


    public AddBankAccountDetailsPage(WebDriver driver) {
        super(driver);
    }


    public void clickContinueButton() {
        waitForElementToBeClickable(5, continueButton);
        continueButton.click();
    }

    public void clickIGotMyFormButton() {
        waitForElementToBeClickable(10, connectBankForm);
        connectBankForm.click();
    }


    public void setAccountNumber(int number) {
        waitForElementToBeClickable(20, accountNumber);
        accountNumber.sendKeys(String.valueOf(number));
    }


    public void addAccountName(String userAccountNameIs) {
        waitForElementToBeClickable(10, accountNameLabel.get(0));
        for (WebElement accountName : accountNameLabel) {
            accountName.sendKeys(userAccountNameIs);
        }
    }

   //@TODO: We can make it dynamic
    public void setAccountType(String userAccountTypeIs) {
        for (WebElement accountType : accountTypeLabel) {
            accountType.click();
            break;
        }

        for (int i = 0; i < comboList.size(); i++) {
            int j = i + 1;
            String customizeCss = ".ba-combo-list-item:nth-child(" + j + ")";
            WebElement selectBankName = driver.findElement(By.cssSelector(customizeCss));
            selectBankName.click();
            break;
        }
    }
}
