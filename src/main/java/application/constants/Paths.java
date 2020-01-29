package application.constants;

import framework.utils.PropertiesRead;

public class Paths {
    public static final String FRAMEWORK_PROPERTY = "src/main/resources/config.properties";
    public static final String TEST_PROPERTY = "src/main/resources/test.properties";
    public static final String SCREENSHOT_PATH = System.getProperty("user.dir") + PropertiesRead.readFromFrameworkConfig("screenshotPath", FRAMEWORK_PROPERTY);
}
