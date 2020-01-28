package application.utils;

import application.builders.RequestBuilder;
import application.constants.ApiMethod;
import application.constants.ApiMethodName;
import application.constants.RequestParameters;
import framework.utils.ApiUtils;
import org.apache.http.HttpResponse;
import org.apache.log4j.Logger;

public class PageApiUtils {
    private static final Logger LOG = Logger.getLogger(PageApiUtils.class);

    private static String getResponse(String url) {
        LOG.info("Gets response body");
        try {
            return ApiUtils.postRequest(url);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return null;
    }

    public static String getToken(String variant) {
        return getResponse(RequestBuilder.createRequestWithParams(ApiMethodName.TOKEN, ApiMethod.GET, RequestParameters.VARIANT, variant));
    }

    public static HttpResponse getTests(String extension, String projectId) {
        return  ApiUtils.getResponse(RequestBuilder.createRequestWithParams(ApiMethodName.TEST, extension, RequestParameters.PROJECT_ID, projectId));
    }
}
