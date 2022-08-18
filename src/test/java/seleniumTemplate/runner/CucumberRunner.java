package seleniumTemplate.runner;

import com.github.mkolisnyk.cucumber.runner.ExtendedCucumberOptions;
import com.github.mkolisnyk.cucumber.runner.ReportRunner;
import com.github.mkolisnyk.cucumber.runner.runtime.ExtendedRuntimeOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;


@ExtendedCucumberOptions(
        usageReport = true,
        detailedReport = true,
        overviewReport = true,
        featureOverviewChart = true,
        overviewChartsReport = true,
        coverageReport = true,
        detailedAggregatedReport = true,
        jsonReport = "build/reports/json/testResult.json",
        jsonUsageReport = "build/reports/json/cucumber-usage.json",
        outputFolder = "build/reports/cucumber_reports/",
        toPDF = true,
        pdfPageSize = "auto"
)

@CucumberOptions(

        plugin = {
                "timeline:build/reports/cucumber_reports",
                "html:build/reports/cucumber_reports",
                "json:build/reports/json/testResult.json",
                "pretty:build/reports/cucumber-pretty.txt",
                "usage:build/reports/json/cucumber-usage.json",
                "junit:build/reports/cucumber-results.xml"
        },

        dryRun = false,
        strict = true,
        monochrome = true,
        features = "src/test/resources/features",
        glue = {"seleniumTemplate.stepdefs", "seleniumTemplate.drivermanager"}
)


public class CucumberRunner extends AbstractTestNGCucumberTests {
    private Class<?> clazz;
    private ExtendedRuntimeOptions[] extendedOptions;


    private void runPredefinedMethods(Class<?> annotation) throws Exception {
        Method[] methodList = this.clazz.getMethods();
        for (Method method : methodList) {
            Annotation[] annotations = method.getAnnotations();
            for (Annotation item : annotations) {
                if (item.annotationType().equals(annotation)) {
                    method.invoke(this);
                    break;
                }
            }
        }
    }

    @Override
    @BeforeClass(alwaysRun = true)
    public void setUpClass() {
        super.setUpClass();
        this.clazz = this.getClass();
        try {
            extendedOptions = ExtendedRuntimeOptions.init(clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        clazz = this.getClass();
        try {
            runPredefinedMethods(org.testng.annotations.BeforeSuite.class);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }


    @Override
    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        super.tearDownClass();
        try {
            runPredefinedMethods(org.testng.annotations.AfterSuite.class);
        } catch (Exception e) {
            e.getStackTrace();
        }
        for (ExtendedRuntimeOptions extendedOption : extendedOptions) {
            ReportRunner.run(extendedOption);
        }
    }


    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

}



