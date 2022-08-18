package seleniumTemplate.dataprovider;

import org.yaml.snakeyaml.Yaml;
import seleniumTemplate.constant.ProjectConfigConstant;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

public class TestDataProvider {

    private String testEnvironment;
    private final Yaml yaml;

    public TestDataProvider() {
        yaml = new Yaml();
        testEnvironment = System.getProperty(ProjectConfigConstant.COMMAND_LINE_ENV_NAME.getValue());
    }

    public String getConfigData(String key) {
        Map<String, String> data = null;
        try {
            data = loadConfigData();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert data != null;
        return data.get(key);
    }

    private Map<String, String> loadConfigData() throws FileNotFoundException {
        Map<String, String> testData;

        if (testEnvironment == null) {
            testEnvironment = "test";
        }

        InputStream inputStream = new FileInputStream(new File("ConfigProperties_"
                + testEnvironment.toUpperCase() + ".yaml"));

        testData = yaml.load(inputStream);

        return testData;
    }

}
