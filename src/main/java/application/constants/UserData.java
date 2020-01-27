package application.constants;

import framework.utils.PropertiesRead;

public class UserData {
    public static final String LOGIN = PropertiesRead.readFromFrameworkConfig("login", Paths.TEST_PROPERTY);
    public static final String PASSWORD = PropertiesRead.readFromFrameworkConfig("password", Paths.TEST_PROPERTY);
}
