package application.pageobjects.pages;

import application.pageobjects.forms.AddTestForm;
import com.google.common.collect.Comparators;
import framework.elements.Button;
import framework.elements.Label;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProjectPage {
    private static String tablePath = "//table[contains(@class,'table')]//tbody//tr[%d]//td[4]";
    private Button addTest = new Button(By.xpath("//button[contains(@class,'btn')]"), "Add Test");

    private static Label getLabel(int iterator) {
        return new Label(By.xpath(String.format(tablePath, iterator)), "Start time");
    }

    public void clickAddTest() {
        addTest.click();
    }

    public boolean isSorted() {
        List<String> startTime = new ArrayList<>();
        int i = 2;
        while (getLabel(i + 1).isDisplayed()) {
            startTime.add(getLabel(i).getText());
            i++;
        }
        return Comparators.isInOrder(startTime, Comparator.reverseOrder());
    }

    public AddTestForm getAddTestForm() {
        return new AddTestForm();
    }
}
