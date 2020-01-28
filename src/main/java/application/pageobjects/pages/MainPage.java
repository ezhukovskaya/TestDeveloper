package application.pageobjects.pages;

import application.pageobjects.forms.Projects;
import framework.base.BaseForm;
import framework.elements.Button;
import framework.elements.Label;
import org.openqa.selenium.By;

public class MainPage extends BaseForm {

    private static Label mainPageLabel = new Label(By.xpath("//div[contains(@class,'list-group')]"), "Projects");
    private static Label footer = new Label(By.xpath("//p[contains(@class, 'footer-text')]//span"), "Footer");
    private static Button add = new Button(By.xpath("//a[contains(@class, 'btn')]"), "Add");

    public MainPage() {
        super(mainPageLabel);
    }

    public String getVersion() {
        return footer.getText();
    }

    public Projects getProjects() {
        return new Projects();
    }

    public void clickAdd() {
        add.click();
    }
}
