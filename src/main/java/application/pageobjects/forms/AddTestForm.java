package application.pageobjects.forms;

import framework.elements.TextBox;
import org.openqa.selenium.By;

public class AddTestForm {
    private TextBox getTextFieldByName(String fieldName) {
        return new TextBox(By.xpath(String.format("//input[@name='%s']", fieldName)), fieldName);
    }

    public void sendKeys(String fieldName, String data) {
        getTextFieldByName(fieldName).sendKeys(data);
    }
}
