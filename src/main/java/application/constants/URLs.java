package application.constants;

import framework.utils.PropertiesRead;

public class URLs {
    public static final String WEB_URL = PropertiesRead.readFromFrameworkConfig("url", Paths.TEST_PROPERTY) + AccessMethod.WEB;
    public static final String API_URL = PropertiesRead.readFromFrameworkConfig("url", Paths.TEST_PROPERTY) + AccessMethod.API;
}