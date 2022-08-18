package seleniumTemplate.stepdefs;

import com.google.inject.Inject;
import io.cucumber.guice.ScenarioScoped;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriverException;

import java.io.IOException;
import java.sql.SQLException;

@ScenarioScoped
public class Hooks {

    @Inject
    private World world;

    @Before
    public void beforeScenario(Scenario scenario) throws SQLException {
        world.scenario = scenario;
    }

    @After
    public void afterScenario() throws IOException, SQLException {
        try {
            if (world.scenario.isFailed()) {
                world.scenario.write(world.driver.getCurrentUrl());
                world.scenario.embed(world.takeScreenShot(), "image/png");

            }
        } catch (WebDriverException | IOException | NullPointerException e) {
            world.driver.quit();
        }

        if (world.driver != null) {
            world.driver.quit();
        }

    }
}
