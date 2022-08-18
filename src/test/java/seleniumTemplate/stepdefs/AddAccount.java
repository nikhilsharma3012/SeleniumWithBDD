package seleniumTemplate.stepdefs;

import com.google.inject.Inject;
import io.cucumber.datatable.DataTable;
import io.cucumber.guice.ScenarioScoped;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import seleniumTemplate.page.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Random;

@ScenarioScoped
public class AddAccount {

    @Inject
    World world;

    private LoginPage loginPage;
    private DashBoardPage dashBoardPage;
    private AddBankAccountsPage addBankAccountsPage;
    private AddBankAccountDetailsPage addBankAccountDetailsPage;
    private LogOutPage logOutPage;

    @Before
    public void setup() {
        try {
            loginPage = (LoginPage)
                    world.pageManager.getPageInstance("LoginPage");
            dashBoardPage = (DashBoardPage) world.pageManager.getPageInstance("DashBoardPage");
            addBankAccountsPage = (AddBankAccountsPage) world.pageManager.getPageInstance("AddBankAccountsPage");
            addBankAccountDetailsPage = (AddBankAccountDetailsPage) world.pageManager.getPageInstance("AddBankAccountDetailsPage");
            logOutPage = (LogOutPage) world.pageManager.getPageInstance("LogOutPage");
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException
                | InstantiationException e) {
            e.printStackTrace();
        }
    }


    public static int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }


    @When("^user is at xero company login page$")
    public void xeroLoginPage() {
        String xeroUrl = world.testDataProvider.getConfigData("baseURL");
        world.driver.get(xeroUrl);
        loginPage.clickLogIn();
    }

    @When("^user provides valid login credentials$")
    public void enterCredentials() {
        //@TODO: Encrypt the password
        String userName= world.testDataProvider.getConfigData("username");
        String password= world.testDataProvider.getConfigData("password");
        loginPage.setLoginCredentials(userName, password);
        loginPage.clickLoginSubmitButton();
    }


    @When("^user redirects to xero company dashboard$")
    public void navigateToDashboard() {
        Assert.assertTrue(world.driver.getCurrentUrl().contains("dashboard"), "Page is not redirected to user dashboard page");
    }

    @When("^user select the Bank accounts option from the Accounting tab$")
    public void selectSubMenu() throws InterruptedException {
        dashBoardPage.selectSubMenuBankAccount();
        Assert.assertEquals(dashBoardPage.getAddBankAccountText(), "Add Bank Account",
                "Add Bank Account is not link" +
                        "is not present on the page hence operation aborted");

    }

    @And("^proceeds for adding his bank account information$")
    public void initiateAddBankConnection() {
        dashBoardPage.clickOnAddBankAccountLink();
    }

    @Then("^user choose his registered bank ([^\"]*) from the list of popular available banks$")
    public void selectBankName(String userBankNameIs) {
        addBankAccountsPage.getPopularBankList(userBankNameIs);
    }


    @And("enter his account details with following information")
    public void customerBankInfo(DataTable customerDataTable) throws InterruptedException {
        List<Map<String, String>> customerInfoHeaders = customerDataTable.asMaps(String.class, String.class);
        Map<String, String> customerInformation = customerInfoHeaders.get(0);
        int randomNum = getRandomNumber(1, 5000);

        String accountNameIs = customerInformation.get("accountName");
        String accountTypeIs = customerInformation.get("accountType");

        addBankAccountDetailsPage.addAccountName(accountNameIs + randomNum);
        addBankAccountDetailsPage.setAccountType(accountTypeIs + randomNum);
        int accountNumber = Integer.parseInt(customerInformation.get("accountNumber"));
        int randomAccountNumber = accountNumber + randomNum;
        addBankAccountDetailsPage.setAccountNumber(randomAccountNumber);

    }

    @And("user select the Continue button")
    public void clickContinue() {
        addBankAccountDetailsPage.clickContinueButton();
    }


    @And("choose to go upload form for activating the bank connection")
    public void uploadForm() {
        addBankAccountDetailsPage.clickIGotMyFormButton();
    }


    @And("logout from the portal")
    public void logoutFromThePortal() {
        logOutPage.selectUserAccount();
        logOutPage.clickLogOutButton();
    }
}
