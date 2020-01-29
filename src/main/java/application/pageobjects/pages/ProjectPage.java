package application.pageobjects.pages;

import application.constants.WebElements;
import application.pageobjects.forms.AddTestForm;
import com.google.common.collect.Comparators;
import framework.browser.Browser;
import framework.elements.Button;
import framework.elements.Label;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProjectPage {
    private static String tablePath = "//table[contains(@class,'table')]//tbody//tr[%d]//td[4]";
    private Button addTest = new Button(By.xpath("//button[contains(@class,'btn-xs')]"), "Add Test");
    private Label test = new Label(By.xpath("//span[contains(@class, 'label')]"), "Test");
    private Button goToTest = new Button(By.xpath("//*[@id='allTests']//td[1]/a"), "Go to test");

    private static Label getLabel(int iterator) {
        return new Label(By.xpath(String.format(tablePath, iterator)), "Start time");
    }

    public void clickAddTest() {
        Browser.setWaitUntilInvisible(WebElements.PLEASE_WAIT.getElementLocator());
        addTest.click();
    }

    public void goToTest() {
        while (goToTest.isDisplayed()) {
            Browser.actionClick(goToTest.getElement());
        }
    }

    public boolean isTestPresented() {
        Browser.setExplicitWait(test.getElementLocator());
        return test.isDisplayed();
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
