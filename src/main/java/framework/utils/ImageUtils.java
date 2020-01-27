package framework.utils;

import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;
import java.net.URL;

public class ImageUtils {
    private static final Logger LOG = Logger.getLogger(ImageUtils.class);
    private static final int ONE_HUNDRED = 100;

    public static float compareImage(String filePath, String fileUrl) {
        LOG.info("Detecting percentage difference");
        float percentage = 0;
        try {
            BufferedImage biA = ImageIO.read(new File(filePath));
            DataBuffer dbA = biA.getData().getDataBuffer();
            int sizeA = dbA.getSize();
            BufferedImage biB = ImageIO.read(new URL(fileUrl));
            DataBuffer dbB = biB.getData().getDataBuffer();
            int sizeB = dbB.getSize();
            int count = 0;
            if (sizeA == sizeB) {
                for (int i = 0; i < sizeA; i++) {
                    if (dbA.getElem(i) == dbB.getElem(i)) {
                        count = count + 1;
                    }
                }
                percentage = (float) (count * ONE_HUNDRED) / sizeA;
            } else {
                LOG.error("Both the images are not of same size");
            }

        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return percentage;
    }
}