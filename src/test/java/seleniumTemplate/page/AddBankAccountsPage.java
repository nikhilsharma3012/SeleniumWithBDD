package seleniumTemplate.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import seleniumTemplate.pageserviceprovider.PageEngine;

import java.util.List;


public class AddBankAccountsPage extends PageEngine {


    @FindBy(css = "div[data-automationid='popularBanksList'] ul li")
    public List<WebElement> popularBankList;


    public AddBankAccountsPage(WebDriver driver) {
        super(driver);
    }


    public void getPopularBankList(String userBankNameIs) {
        for (int i = 0; i < popularBankList.size(); i++) {
            if (popularBankList.get(i).getText().equals(userBankNameIs)) {
                int j = i + 1;
                String customizeCss = "li.ba-banklist--item.xui-contentblock--item:nth-child(" + j + ")";
                WebElement selectBankName = driver.findElement(By.cssSelector(customizeCss));
                selectBankName.click();
                break;
            }
        }
    }
}
