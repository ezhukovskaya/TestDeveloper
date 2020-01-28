package application.pageobjects.forms;

import framework.browser.Browser;
import framework.elements.Button;
import org.openqa.selenium.By;

public class Projects {

    private String buttonXpath = "//a[contains(@class, 'list-group-item') and contains(text(), '%s')]";

    private Button createButton(String projectName) {
        return new Button(By.xpath(String.format(buttonXpath, projectName)), projectName);
    }

    public boolean isProjectDisplayed(String projectName){
        return createButton(projectName).isDisplayed();
    }

    public void projectClick(String projectName) {
        createButton(projectName).click();
    }

    public String getProjectId(String projectName) {
        String attribute = Browser.getAttribute(By.xpath(String.format(buttonXpath, projectName)), "href");
        return attribute.substring(attribute.length() - 1);
    }
}
