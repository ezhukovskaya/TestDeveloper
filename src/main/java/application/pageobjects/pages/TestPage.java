package application.pageobjects.pages;

import application.constants.TestInfo;
import framework.browser.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class TestPage {
    private By by = By.xpath(("//p[contains(@class, 'list-group-item-text')]"));
    private List<WebElement> actualTestInfo = Browser.findElements(by);

    public ArrayList<String> getActualTestInfo() {
        Browser.setExplicitWait(by);
        ArrayList<String> info = new ArrayList<>();
        for (WebElement test : actualTestInfo) {
            info.add(test.getText());
        }
        return info;
    }

    public boolean isInfoCorrect() {
        ArrayList<String> testInfo = new ArrayList<>();
        testInfo.add(TestInfo.TEST_NAME);
        testInfo.add(TestInfo.TEST_METHOD);
        testInfo.add(TestInfo.START_TIME);
        testInfo.add(TestInfo.END_TIME);
        testInfo.add(TestInfo.ENVIRONMENT);
        testInfo.add(TestInfo.BROWSER);
        return getActualTestInfo().contains(testInfo);
    }


}
