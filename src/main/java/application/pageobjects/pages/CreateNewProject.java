package application.pageobjects.pages;

import framework.base.BaseForm;
import framework.elements.Button;
import framework.elements.Label;
import framework.elements.TextBox;
import org.openqa.selenium.By;

public class CreateNewProject extends BaseForm {
    private TextBox setProjectName = new TextBox(By.xpath("//input[@id='projectName']"), "Project Name");
    private Button submit = new Button(By.xpath("//button[@type='submit']"), "Save project");
    private static Label newTabLabel = new Label(By.xpath("//label[@for='projectName']"), "New Project Adding Label");
    private Label successSave = new Label(By.xpath("//div[contains(@class,'alert-success')]"), "Success");

    public CreateNewProject() {
        super(newTabLabel);
    }

    public void submitNewProject(String projectName) {
        setProjectName.sendKeys(projectName);
        submit.click();
    }

    public boolean isSuccessTextDisplayed() {
        return successSave.isDisplayed();
    }
}
