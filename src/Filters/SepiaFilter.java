package Filters;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Dico on 21.02.16.
 */
public class SepiaFilter {
    private BufferedImage filter;
    private int shiftRed = 25;
    private int shiftGreen = 10;
    private int shiftBlue = 5;

    public SepiaFilter(BufferedImage image) {
        filter = new BufferedImage(image.getWidth(), image.getHeight(),
                BufferedImage.TYPE_INT_RGB);

        for (int h = 0; h < image.getHeight(); h++)
            for (int w = 0; w < image.getWidth(); w++) {
                int pixel = image.getRGB(w, h);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                int newRGB = (red + green + blue) / 3;

                red = green = blue = newRGB;
                red = red + shiftRed;
                green = green + shiftGreen;
                blue = blue + shiftBlue;

                if (red > 255) red = 255;
                if (green > 255) green = 255;
                if (blue > 255) blue = 255;
                if (red < 0) red = 0;
                if (green < 0) green = 0;
                if (blue < 0) blue = 0;

                Color color = new Color(red, green, blue);
                pixel = color.getRGB();
                filter.setRGB(w, h, pixel);
            }
    }

    public BufferedImage getFilterImageSepia() {
        return filter;
    }
}
