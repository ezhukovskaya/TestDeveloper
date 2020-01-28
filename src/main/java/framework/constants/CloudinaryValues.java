package framework.constants;

import application.constants.Paths;
import framework.utils.PropertiesRead;

public class CloudinaryValues {
    public static final String CLOUDINARY_URL = PropertiesRead.readFromFrameworkConfig("cloudinaryUrl", Paths.FRAMEWORK_PROPERTY);
    public static final String SCREENSHOT_PATH = PropertiesRead.readFromFrameworkConfig("screenshotPath", Paths.FRAMEWORK_PROPERTY);
    public static final String SECURE_URL = "secure_url";
}