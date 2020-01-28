package framework.utils;

import application.constants.Paths;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import framework.constants.APIMethodConstants;
import framework.constants.CloudinaryValues;
import framework.constants.Fields;
import framework.models.*;
import org.apache.http.HttpHeaders;
import org.apache.log4j.Logger;
import org.testng.ITestResult;

import java.io.File;
import java.util.Map;

public class TestRailUtils {
    private static Map map = CloudinaryUtils.upload(new File(CloudinaryValues.SCREENSHOT_PATH));
    private static String imageUrl = (String) map.get(CloudinaryValues.SECURE_URL);
    private static final int PROJECT_ID = 140;
    private static final Logger log = Logger.getLogger(TestRailUtils.class);
    private static final String USERNAME = PropertiesRead.readFromFrameworkConfig("testRailUsername", Paths.FRAMEWORK_PROPERTY);
    private static final String PASSWORD = PropertiesRead.readFromFrameworkConfig("testRailPassword", Paths.FRAMEWORK_PROPERTY);
    private static String URL = PropertiesRead.readFromFrameworkConfig("testRailUrl", Paths.FRAMEWORK_PROPERTY);
    private static final String API_VERSION = PropertiesRead.readFromFrameworkConfig("testRailApi", Paths.FRAMEWORK_PROPERTY);

    private static JsonNode getResponse(String name, String password, String url, Object object) {
        log.info(String.format("Adding Modal Windows test data to %s", url));
        ObjectMapper mapperForTestResult = new ObjectMapper();
        try {
            String json = mapperForTestResult.writeValueAsString(object);
            com.mashape.unirest.http.JsonNode response = ApiUtils.postRequest(url, HttpHeaders.CONTENT_TYPE, "application/json", name, password, json);
            return mapperForTestResult.readValue(response.toString(), JsonNode.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JsonNode createSuite(String name, String username, String password, String url) {
        SuiteModel suiteModel = new SuiteModel(name);
        return getResponse(username, password, url, suiteModel);
    }

    public static JsonNode createSection(int suiteId, String name, String username, String password, String url) {
        SectionModel sectionModel = new SectionModel(suiteId, name);
        return getResponse(username, password, url, sectionModel);
    }

    public static JsonNode createCase(String name, String username, String password, String url) {
        CaseModel caseModel = new CaseModel(name);
        return getResponse(username, password, url, caseModel);
    }

    public static JsonNode createRun(int suiteId, String name, String username, String password, String url) {
        RunModel runModel = new RunModel(suiteId, name);
        return getResponse(username, password, url, runModel);
    }

    public static JsonNode createResult(int resultStatus,String comment, String username, String password, String url) {
        Result result = new Result(resultStatus, comment);
        return getResponse(username, password, url, result);
    }

    public static void addResult(ITestResult iTestResult, int testStatus, String comment){
        URL +=  API_VERSION;
        log.info(String.format("Setting runModel with  %s", iTestResult.getName()));
        JsonNode jsonNode = createSuite(iTestResult.getName(), USERNAME, PASSWORD, URL + APIMethodConstants.ADD_SUITE + PROJECT_ID);
        int suiteId = Integer.parseInt(jsonNode.get(Fields.ID).asText());
        jsonNode = createSection(suiteId, iTestResult.getName(), USERNAME, PASSWORD, URL + APIMethodConstants.ADD_SECTION + PROJECT_ID);
        int sectionId = Integer.parseInt(jsonNode.get(Fields.ID).asText());
        jsonNode = createCase(iTestResult.getName(), USERNAME, PASSWORD, URL + APIMethodConstants.ADD_CASE + sectionId);
        int case_id = Integer.parseInt(jsonNode.get(Fields.ID).toString());
        jsonNode = createRun(suiteId, iTestResult.getName(), USERNAME, PASSWORD, URL + APIMethodConstants.ADD_RUN + PROJECT_ID);
        int run_id = Integer.parseInt(jsonNode.get(Fields.ID).toString());
        createResult(testStatus, comment + imageUrl, USERNAME, PASSWORD, URL + APIMethodConstants.ADD_RESULT + run_id + APIMethodConstants.SLASH + case_id);
    }
}