package framework.utils;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.apache.log4j.Logger;

import java.io.File;

public class ApiUtils {

    private static final Logger LOG = Logger.getLogger(ApiUtils.class);

    public static String postRequest(String url) {
        HttpResponse<String> jsonResponse = null;
        LOG.info("Post " + url);
        try {
            jsonResponse = Unirest.post(url).asString();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return jsonResponse.getBody();
    }


}