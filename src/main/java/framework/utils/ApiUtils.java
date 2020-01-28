package framework.utils;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;

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

    public static JsonNode postRequest(String url, String key, String value, String name, String password, Object body) {
        LOG.info(String.format("Gets %s JSON body as String", body.toString()));
        HttpResponse<JsonNode> jsonResponse = null;
        try {
            jsonResponse = Unirest.post(url).basicAuth(name, password).header(key, value).body(body.toString()).asJson();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonResponse.getBody();
    }

    public static org.apache.http.HttpResponse getResponse(String url) {
        LOG.info(String.format("Get HttpResponse from %s", url));
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost request = new HttpPost(url);
        try {
            LOG.info("Execute request");
            return httpClient.execute(request);
        } catch (Exception e) {
            LOG.error("Request error");
            e.getStackTrace();
        }
        return null;
    }

}