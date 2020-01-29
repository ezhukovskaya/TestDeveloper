package framework.utils;

import application.constants.XMLTags;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;

public class XMLService {
    private static final Logger log = Logger.getLogger(XMLService.class);

    private static String fixXML(HttpResponse response) {
        try {
            String buffer = EntityUtils.toString(response.getEntity());
            return XMLTags.TEST_GROUP + buffer + XMLTags.CLOSED_TEST_GROUP;
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    public static Object setModel(HttpResponse response, Class clazz) {
        XmlMapper xmlMapper = new XmlMapper();
        String fixedXML = fixXML(response);
        try {
            log.info("read XML objects");
            System.out.println(EntityUtils.toString(response.getEntity()));
            return xmlMapper.readValue(fixedXML, clazz);
        } catch (IOException e) {
            log.error("reading values error");
            e.printStackTrace();
        }
        return null;
    }
}