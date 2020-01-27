package tests;

import application.constants.Paths;
import application.constants.URLs;
import application.constants.UserData;
import application.pageobjects.models.User;
import application.pageobjects.pages.MainPage;
import application.utils.PageApiUtils;
import framework.browser.Browser;
import framework.utils.PropertiesRead;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestDeveloper {
    private static final String VARIANT_ID = PropertiesRead.readFromFrameworkConfig("variant", Paths.TEST_PROPERTY);

    @Test
    public static void testStart() {
        String token = PageApiUtils.getToken(VARIANT_ID);
        Assert.assertNotNull(token, "Token is not generated");
        User user = new User(UserData.LOGIN, UserData.PASSWORD);
        Browser.goToUrl(String.format(URLs.WEB_URL, user.toString()));
        MainPage mainPage = new MainPage();
        Assert.assertTrue(mainPage.isDisplayed(), "Page is not open");
        Browser.addCookie("token", token);
    }
}
