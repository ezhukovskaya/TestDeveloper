package framework.elements;

import framework.base.BaseElement;
import framework.browser.Browser;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class TextBox extends BaseElement {
    static final Logger LOG = Logger.getLogger(TextBox.class);

    public TextBox(By locator, String name) {
        super(locator, name);
    }

    public void sendKeys(String text) {
        LOG.info("TextBox takes " + text);
        Browser.getBrowser().findElement(elementLocator).sendKeys(text);
    }
}