package tests;

import application.constants.*;
import application.enums.TestResultForm;
import application.pageobjects.models.TestGroup;
import application.pageobjects.models.User;
import application.pageobjects.pages.CreateNewProject;
import application.pageobjects.pages.MainPage;
import application.pageobjects.pages.ProjectPage;
import application.pageobjects.pages.TestPage;
import application.utils.DateUtils;
import application.utils.PageApiUtils;
import framework.browser.Browser;
import framework.utils.PropertiesRead;
import framework.utils.XMLService;
import org.apache.http.HttpResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.UUID;

public class TestDeveloper {
    private static final String VARIANT_ID = PropertiesRead.readFromFrameworkConfig("variant", Paths.TEST_PROPERTY);
    private static final String VERSION = String.format("Version: %s", VARIANT_ID);
    private static final String PROJECT_TO_CLICK = "Nexage";
    private static final String NEW_PROJECT_NAME = UUID.randomUUID().toString();
    private static final int EXPECTED_TABS_AMOUNT = 1;

    @Test
    public static void testStart() {
        String token = PageApiUtils.getToken(VARIANT_ID);
        Assert.assertNotNull(token, "Token is not generated");
        User user = new User(UserData.LOGIN, UserData.PASSWORD);
        Browser.goToUrl(String.format(URLs.WEB_URL, user.toString()));
        Browser.maximize();
        MainPage mainPage = new MainPage();
        Assert.assertTrue(mainPage.isDisplayed(), "Page is not open");
        Browser.addCookie("token", token);
        Browser.refresh();
        Assert.assertEquals(mainPage.getVersion(), VERSION, "Cookie is not added or wrong variant ID");
        String projectId = mainPage.getProjects().getProjectId(PROJECT_TO_CLICK);
        mainPage.getProjects().projectClick(PROJECT_TO_CLICK);
        ProjectPage projectPage = new ProjectPage();
        Assert.assertTrue(projectPage.isSorted(), "Tests are not sorted in a reverse order");
        HttpResponse response = PageApiUtils.getTests(Extension.XML, projectId);
        TestGroup testGroup = (TestGroup) XMLService.setModel(response, TestGroup.class);
        Assert.assertTrue(projectPage.isTestMatch(testGroup), "Tests are not match with API response");
        Browser.back();
        mainPage.clickAdd();
        Browser.switchTo(Browser.getTabs().size() - 1, Browser.getTabs());
        CreateNewProject createNewProject = new CreateNewProject();
        Assert.assertTrue(createNewProject.isDisplayed(), "New Tab is not open");
        createNewProject.submitNewProject(NEW_PROJECT_NAME);
        Assert.assertTrue(createNewProject.isSuccessTextDisplayed(), "New Project save is unsuccessful");
        Browser.close();
        Browser.switchTo(Browser.getTabs().size() - 1, Browser.getTabs());
        Assert.assertEquals(Browser.getTabs().size(), EXPECTED_TABS_AMOUNT, "Closed tab is open");
        Browser.refresh();
        Assert.assertTrue(mainPage.getProjects().isProjectDisplayed(NEW_PROJECT_NAME), NEW_PROJECT_NAME + " is not added");
        mainPage.getProjects().projectClick(NEW_PROJECT_NAME);
        projectPage.clickAddTest();
        String startTime = DateUtils.getFormattedDate(new Date());
        String endTime = DateUtils.getFormattedDate(new Date());
        Browser.takeScreenshot(Paths.SCREENSHOT_PATH);
        projectPage.getAddTestForm().writeForm(TestInfo.TEST_NAME, TestInfo.TEST_METHOD, TestResultForm.SUCCESS.getValue(), startTime, endTime, TestInfo.ENVIRONMENT, TestInfo.BROWSER);
        projectPage.getAddTestForm().clickSave();
        Assert.assertTrue(projectPage.getAddTestForm().isTestSaved(), "Test save is unsuccessful");
        projectPage.getAddTestForm().clickOutside();
        Assert.assertTrue(projectPage.isTestPresented(), "Test is not added");
        projectPage.goToTest();
        TestPage testPage = new TestPage();
        Assert.assertTrue(testPage.isInfoCorrect(), "Info is incorrect");
    }
}
