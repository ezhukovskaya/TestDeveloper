package framework.base;

import framework.browser.Browser;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public abstract class BaseElement {

    private static final Logger log = Logger.getLogger(BaseElement.class);
    protected By elementLocator;
    protected String elementName;

    public BaseElement(By locator, String name) {
        this.elementLocator = locator;
        this.elementName = name;
    }

    public void click() {
        log.info(this.elementName + " click");
        getElement().click();
    }

    private WebElement getElement() {
        return Browser.getBrowser().findElement(elementLocator);
    }

    private List<WebElement> getElements() {
        return Browser.getBrowser().findElements(this.elementLocator);
    }

    public String getText() {
        log.info("Get text from element");
        return getElement().getText();
    }

    public boolean isDisplayed() {
        return getElements().size() > 0;
    }

}