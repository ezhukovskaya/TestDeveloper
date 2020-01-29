package application.pageobjects.forms;

import application.constants.AddTestFormConstants;
import application.constants.Paths;
import application.constants.WebElements;
import framework.browser.Browser;
import framework.elements.Button;
import framework.elements.DropDown;
import framework.elements.Label;
import framework.elements.TextBox;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

public class AddTestForm {
    private final int Y = 1;
    private DropDown testStatusDropDown = new DropDown(By.xpath("//select[@name='status']"), "Test Status");
    private Button save = new Button(By.xpath("//button[contains(@class,'btn btn-primary')]"), "Save");
    private Label success = new Label(By.xpath("//div[@id='success']"), "Success");
    private Button outside = new Button(By.xpath("//div[contains(@class, 'modal fade in')]"), "Outside");

    private TextBox getTextFieldByName(String fieldName) {
        return new TextBox(By.xpath(String.format("//input[@name='%s']", fieldName)), fieldName);
    }

    private void sendKeys(String fieldName, String data) {
        Browser.setWaitUntilClickable(getTextFieldByName(fieldName).getElementLocator());
        getTextFieldByName(fieldName).sendKeys(data);
    }

    public void clickSave() {
        save.click();
    }

    public void clickOutside() {
        Dimension size = outside.getSize();
        Browser.clickOnBlankArea(outside.getElement(), -size.getWidth() / 2, Y);
    }

    public boolean isTestSaved() {
        Browser.setWaitUntilInvisible(WebElements.PLEASE_WAIT.getElementLocator());
        return success.isDisplayed();
    }

    public void writeForm(String testName, String testMethod, int testStatus, String startTime, String endTime, String environment, String browser) {
        sendKeys(AddTestFormConstants.TEST_NAME, testName);
        sendKeys(AddTestFormConstants.TEST_METHOD, testMethod);
        testStatusDropDown.select(testStatus);
        sendKeys(AddTestFormConstants.START_TIME, startTime);
        sendKeys(AddTestFormConstants.END_TIME, endTime);
        sendKeys(AddTestFormConstants.ENVIRONMENT, environment);
        sendKeys(AddTestFormConstants.BROWSER, browser);
        sendKeys(AddTestFormConstants.ATTACHMENT, Paths.SCREENSHOT_PATH);
    }
}
