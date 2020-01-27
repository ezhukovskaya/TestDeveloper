package application.pageobjects.pages;

import framework.base.BaseForm;
import framework.elements.Label;
import org.openqa.selenium.By;

public class MainPage extends BaseForm {

    private static Label mainPageLabel = new Label(By.xpath("//div[contains(@class,'list-group')]"), "Projects");

    public MainPage() {
        super(mainPageLabel);
    }
}
