package framework.browser;

import application.constants.Paths;
import framework.enums.BrowserList;
import framework.enums.RemoteStatus;
import framework.utils.PropertiesRead;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.HashMap;


public class BrowserFactory {
    private static final Logger LOG = Logger.getLogger(BrowserFactory.class);


    public static WebDriver getBrowser() {
        String language = PropertiesRead.readFromFrameworkConfig("language", Paths.FRAMEWORK_PROPERTY);
        String browserName = PropertiesRead.readFromFrameworkConfig("browser", Paths.FRAMEWORK_PROPERTY);
        String remoteStatusName = PropertiesRead.readFromFrameworkConfig("remote", Paths.FRAMEWORK_PROPERTY);
        LOG.info("Language of web-site is " + language);
        BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
        RemoteStatus remoteStatus = RemoteStatus.valueOf(remoteStatusName.toUpperCase());
        switch (remoteStatus) {
            case LOCAL:
                switch (browserList) {
                    case CHROME:
                        LOG.info("Chosen browser is chrome");
                        return getChromeInstance(language);
                    case FIREFOX:
                        LOG.info("Chosen browser is firefox");
                        return getFirefoxInstance(language);
                    default:
                        throw new IllegalStateException("Incorrect browser name in configuration file");
                }
            case REMOTE:
                return getSelenoidDriver(browserName);
            default:
                throw new IllegalStateException("Please, Install driver for Chrome or Firefox Browser or Selenoid");
        }
    }

    private static RemoteWebDriver getSelenoidDriver(String browserName) {
        String version = PropertiesRead.readFromFrameworkConfig("version", Paths.FRAMEWORK_PROPERTY);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(browserName.toLowerCase());
        capabilities.setVersion(version);
        try {
            return new RemoteWebDriver(URI.create(PropertiesRead.readFromFrameworkConfig("selenoidUrl", Paths.FRAMEWORK_PROPERTY)).toURL(), capabilities);
        } catch (MalformedURLException e) {
            e.getStackTrace();
        }
        return null;
    }

    private static ChromeDriver getChromeInstance(String language) {
        WebDriverManager.chromedriver().setup();
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("safebrowsing.enabled", true);
        chromePrefs.put("intl.accept_languages", language);
        chromeOptions.setExperimentalOption("prefs", chromePrefs);
        return new ChromeDriver(chromeOptions);
    }

    private static FirefoxDriver getFirefoxInstance(String language) {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addPreference("browser.download.folderList", 2);
        firefoxOptions.addPreference("browser.download.useDownloadDir", true);
        firefoxOptions.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/x-debian-package");
        firefoxOptions.addPreference("pdfjs.disabled", true);
        firefoxOptions.addPreference("intl.accept_languages", language);
        return new FirefoxDriver(firefoxOptions);
    }
}