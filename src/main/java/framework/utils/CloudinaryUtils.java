package framework.utils;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import framework.constants.CloudinaryValues;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class CloudinaryUtils {
    private static final Logger log = Logger.getLogger(CloudinaryUtils.class);

    public static Map upload(File file) {
        try {
            log.info(String.format("Uploading %s", file.getName()));
            return new Cloudinary(CloudinaryValues.CLOUDINARY_URL).uploader().upload(file, ObjectUtils.emptyMap());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}