package framework.utils;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import java.io.IOException;

public class XMLService {
    private static final Logger log = Logger.getLogger(XMLService.class);

    public static Object setModel(HttpResponse response, Class clazz) {
        XmlMapper xmlMapper = new XmlMapper();
        try {
            log.info("read XML objects");
            System.out.println(EntityUtils.toString(response.getEntity()));
            return xmlMapper.readValue(EntityUtils.toString(response.getEntity()), clazz);
        } catch (IOException e) {
            log.error("reading values error");
            e.printStackTrace();
        }
        return null;
    }
}