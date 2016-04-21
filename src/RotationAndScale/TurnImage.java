package RotationAndScale;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Dico on 04.04.16.
 */
public class TurnImage {
    private BufferedImage endImage;

    public TurnImage(int value, BufferedImage DEFAULT_IMAGE) {
        int radius = (int) (Math.sqrt(Math.pow(DEFAULT_IMAGE.getWidth(), 2) + Math.pow(DEFAULT_IMAGE.getHeight(), 2)) / 2);
        int newWidth = 2 * radius;
        int newHeight = 2 * radius;

        int dx = (newWidth - DEFAULT_IMAGE.getWidth()) / 2;
        int dy = (newHeight - DEFAULT_IMAGE.getHeight()) / 2;
        endImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < newWidth; x++)
            for (int y = 0; y < newHeight; y++) {
                double angleBOH;
                int newX, newY;

                double OB = Math.sqrt(Math.pow(newWidth / 2 - x, 2) + Math.pow(newHeight / 2 - y, 2));
                double OH = newWidth / 2 - x;

                if (OB != 0) {
                    angleBOH = Math.acos((Math.abs(OH) / OB));
                } else {
                    angleBOH = 0;
                }
                if (((y >= newHeight / 2) && (x < newWidth / 2)) || ((y < newHeight / 2) && (x >= newWidth / 2))) {
                    angleBOH = -angleBOH;
                }
                if (OH > 0) {
                    newX = (int) (newWidth / 2 - Math.cos(angleBOH + value * Math.PI / 180) * OB - dx);
                    newY = (int) (newHeight / 2 - Math.sin(angleBOH + value * Math.PI / 180) * OB - dy);
                } else {
                    newX = (int) (newWidth / 2 - Math.cos(angleBOH + value * Math.PI / 180 + Math.PI) * OB - dx);
                    newY = (int) (newHeight / 2 - Math.sin(angleBOH + value * Math.PI / 180 + Math.PI) * OB - dy);
                }
                if ((newX >= 0) && (newY >= 0) && (newX <= DEFAULT_IMAGE.getWidth() - 1)
                        && (newY <= DEFAULT_IMAGE.getHeight() - 1)) {
                    endImage.setRGB(x, y, DEFAULT_IMAGE.getRGB(newX, newY));
                }
            }
    }

    public BufferedImage getImage() {
        return endImage;
    }
}
