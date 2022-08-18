package seleniumTemplate.stepdefs;

import com.google.inject.Inject;
import io.cucumber.java.Scenario;

import io.cucumber.guice.ScenarioScoped;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import seleniumTemplate.dataprovider.TestDataProvider;
import seleniumTemplate.drivermanager.DriverManager;
import seleniumTemplate.pageserviceprovider.PageManager;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@ScenarioScoped
public class World {

    public TestDataProvider testDataProvider;
    public DriverManager driverManager;
    public PageManager pageManager;
    public WebDriver driver;
    public Scenario scenario;


    @Inject
    World(TestDataProvider testDataProvider, DriverManager driverManager) {
        String browserName = System.getProperty("browserName");
        String headlessMode = System.getProperty("headlessMode");

        if (browserName == null) {
            browserName = "chrome";
        }

        if (headlessMode == null) {
            headlessMode = "false";
        }

        this.scenario = null;

        this.driverManager = new DriverManager(browserName, headlessMode);
        this.driver = this.driverManager.getDriver();
        this.pageManager = new PageManager(this.driver);
        this.testDataProvider = testDataProvider;
    }

    public byte[] takeScreenShot() throws IOException {
        String screenShotPath = "src/test/resources/screenshot/FailedTest.png";
        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).
                takeScreenshot(driver);
        ImageIO.write(screenshot.getImage(), "PNG", new File(screenShotPath));
        File file = new File(screenShotPath);
        return Files.readAllBytes(file.toPath());
    }

}
