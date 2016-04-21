package RotationAndScale;

import java.awt.image.BufferedImage;

/**
 * Created by Dico on 04.04.16.
 */
public class ScaleImage {
    private BufferedImage endImage;

    public ScaleImage(double value, BufferedImage DEFAULT_IMAGE) {
        int newWidth = (int)(DEFAULT_IMAGE.getWidth() * (value / 10));
        int newHeight = (int)(DEFAULT_IMAGE.getHeight() * (value / 10));
        if (newWidth != 0) {
            endImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            int x, y;
            int oldWidth = DEFAULT_IMAGE.getWidth();
            int oldHeight = DEFAULT_IMAGE.getHeight();
            for (x = 0; x < newWidth; x++) {
                for (y = 0; y < newHeight; y++) {
                    int color = DEFAULT_IMAGE.getRGB(x * oldWidth / newWidth, y * oldHeight / newHeight);
                    endImage.setRGB(x, y, color);
                }
            }
        } else {
            endImage = DEFAULT_IMAGE;
        }
    }

    public BufferedImage getImage() {
        return endImage;
    }
}
