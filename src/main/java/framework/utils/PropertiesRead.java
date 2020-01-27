package framework.utils;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class PropertiesRead {
    private static final Logger LOG = Logger.getLogger(PropertiesRead.class);
    private static Properties prop = new Properties();

    public static String readFromFrameworkConfig(String key, String path) {
        try {
            InputStream input = new FileInputStream(path);
            prop.load(new InputStreamReader(input, StandardCharsets.UTF_8));
        } catch (IOException e) {
            LOG.info(e.getMessage());
        }
        return prop.getProperty(key);
    }
}