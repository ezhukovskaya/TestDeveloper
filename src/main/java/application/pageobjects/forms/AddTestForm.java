package application.pageobjects.forms;

import application.constants.AddTestFormConstants;
import application.constants.Paths;
import framework.elements.TextBox;
import framework.utils.PropertiesRead;
import org.openqa.selenium.By;
import org.testng.ITestResult;

public class AddTestForm {
    private TextBox getTextFieldByName(String fieldName) {
        return new TextBox(By.xpath(String.format("//input[@name='%s']", fieldName)), fieldName);
    }

    private void sendKeys(String fieldName, String data) {
        getTextFieldByName(fieldName).sendKeys(data);
    }

    public void writeForm(ITestResult testResult){
        sendKeys(AddTestFormConstants.TEST_NAME, testResult.getTestName());
        sendKeys(AddTestFormConstants.TEST_METHOD, testResult.getMethod().getMethodName());
        sendKeys(AddTestFormConstants.START_TIME, String.valueOf(testResult.getStartMillis()));
        sendKeys(AddTestFormConstants.END_TIME, String.valueOf(testResult.getEndMillis()));
        sendKeys(AddTestFormConstants.ENVIRONMENT, "Selenium");
        sendKeys(AddTestFormConstants.BROWSER, PropertiesRead.readFromFrameworkConfig("browser", Paths.FRAMEWORK_PROPERTY));
    }
}
