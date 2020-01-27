package tests;

import application.constants.Paths;
import application.constants.URLs;
import framework.browser.Browser;
import framework.utils.PropertiesRead;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;

abstract public class BaseTest {
    @BeforeTest
    public void init() {
        PropertyConfigurator.configure(PropertiesRead.readFromFrameworkConfig("logfile", Paths.FRAMEWORK_PROPERTY));
        Browser.setImplicitlyWait();
    }

    @AfterMethod
    public void close() {
        //Browser.close();
    }
}
