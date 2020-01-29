package application.constants;

import application.utils.DateUtils;
import framework.utils.PropertiesRead;

import java.util.Date;

public class TestInfo {
    public static final String TEST_NAME = PropertiesRead.readFromFrameworkConfig("testName", Paths.TEST_PROPERTY);
    public static final String TEST_METHOD = PropertiesRead.readFromFrameworkConfig("testMethod", Paths.TEST_PROPERTY);
    public static final String ENVIRONMENT = PropertiesRead.readFromFrameworkConfig("environment", Paths.TEST_PROPERTY);
    public static final String BROWSER = PropertiesRead.readFromFrameworkConfig("browser", Paths.TEST_PROPERTY);
    public static final String START_TIME = DateUtils.getFormattedDate(new Date());
    public static final String END_TIME = DateUtils.getFormattedDate(new Date());
}
