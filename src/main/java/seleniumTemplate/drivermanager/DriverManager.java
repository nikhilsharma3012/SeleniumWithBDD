package seleniumTemplate.drivermanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DriverManager {

    private String browser;
    private String headlessMode;

    private WebDriver driver;
    static Logger logger = Logger.getLogger(DriverManager.class.getName());


    public DriverManager() {
    }

    public DriverManager(String browser, String headlessMode) {
        this.browser = browser.toLowerCase();
        this.headlessMode = headlessMode;
    }

    
    public WebDriver getDriver() {
        this.driver = createDriver();
        return driver;
    }


    /**
     * @return a driver instance
     * Create an instance for the Chrome Or Firefox driver / xyz driver
     */
    private WebDriver createDriver() {
        String osName = System.getProperty("os.name");
        String driverName = "chromedriver.exe";
        if (osName.contains("Mac") || osName.contains("mac")) {
            osName = "ios";
            driverName = "chromedriver";
        } else if (osName.contains("win")) {
            osName = "windows";
        }

        switch (browser) {
            case "chrome":
                try {
                    System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/" + osName + "/" + driverName);
                    Map<String, Object> prefs = new HashMap<>();
                    prefs.put("credentials_enable_service", false);
                    prefs.put("password_manager_enabled", false);

                    ChromeOptions options = new ChromeOptions();
                    options.setExperimentalOption("prefs", prefs);
                    options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
                    options.addArguments("disable-infobars");
                    options.addArguments("disable-popup-blocking");
                    options.addArguments("disable-extensions");
                    options.addArguments("start-maximized");

                    if (headlessMode.equals("true")) {
                        options.setHeadless(true);
                    }

                    DesiredCapabilities capabilities = DesiredCapabilities.chrome();
                    capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                    capabilities.setCapability("applicationCacheEnabled", false);

                    ChromeDriverService chromeDriverService = ChromeDriverService.createDefaultService();
                    chromeDriverService.start();

                    driver = new RemoteWebDriver(chromeDriverService.getUrl(), capabilities);
                    driver.manage().deleteAllCookies();
                    logger.info(browser.toUpperCase() + " browser: is started");
                    break;
                } catch (IOException e) {
                    e.getMessage();
                }
            case "internetexplorer":
                //@TODO: We can add code for internet explorer here
                break;

        }


        assert driver != null;
        return driver;
    }

}
