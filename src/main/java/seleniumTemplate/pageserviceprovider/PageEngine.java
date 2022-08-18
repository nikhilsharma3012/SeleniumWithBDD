package seleniumTemplate.pageserviceprovider;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class PageEngine {

    public static final int TIMEOUT = 5;
    public static final int POLLING = 100;

    public WebDriver driver;
    private WebDriverWait wait;

    /**
     * Similar to the other "initElements" methods, but takes an {@link ElementLocatorFactory} which
     * is used for providing the mechanism for finding elements. If the ElementLocatorFactory returns
     * null then the field won't be decorated.
     *
     * @param driver The driver to use PageFactory
     */
    public PageEngine(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, TIMEOUT, POLLING);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, TIMEOUT), this);
        this.driver.manage().deleteAllCookies();
        this.driver.manage().window().maximize();
    }

    /**
     * Wait will ignore instances of NotFoundException that are encountered (thrown) by default in
     * the 'until' condition, and immediately propagate all others.
     *
     * @param timeOutInSeconds The timeout in seconds when an expectation is called
     * @param webElement       The element is in search
     * @return the (same) WebElement once it is clickable
     */
    public void waitForElementToBeClickable(long timeOutInSeconds, WebElement webElement) {
        new WebDriverWait(driver, timeOutInSeconds, 5).
                until(ExpectedConditions.elementToBeClickable(webElement));
    }

    /**
     * An expectation for checking that an element, known to be present on the DOM of a page, is
     * visible. Visibility means that the element is not only displayed but also has a height and
     * width that is greater than 0.
     *
     * @param timeOutInSeconds The timeout in seconds when an expectation is called
     * @param webElement       the WebElement
     * @return the (same) WebElement once it is visible
     */
    public void waitForVisibilityOfElement(long timeOutInSeconds, WebElement webElement) {
        new WebDriverWait(driver, timeOutInSeconds).
                until(ExpectedConditions.visibilityOf(webElement));
    }
}
