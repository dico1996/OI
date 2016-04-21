package Filters;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

/**
 * Created by Dico on 21.02.16.
 */
public class MedianFilter {
    private BufferedImage filters;

    public MedianFilter(BufferedImage image) {
        filters = new BufferedImage(image.getWidth(), image.getHeight(),
                BufferedImage.TYPE_INT_RGB);

        for (int w = 1; w < image.getWidth() - 1; w++) {
            for (int h = 1; h < image.getHeight() - 1; h++) {
                int[][] pixels = new int[3][3];
                int pixel;

                for (int i=0; i<3; i++)
                    for (int j=0; j<3; j++)
                        pixels[i][j] = image.getRGB(w - 1+i, h - 1+j);

                double[] mass = new double[9];
                int k = 0;

                for (int i = 0; i < pixels.length; i++)
                    for (int j = 0; j < pixels.length; j++) {
                        if (mass[k] == 0) {
                            mass[k] = pixels[i][j];
                            k ++;
                        }
                    }

                Arrays.sort(mass);
                Color color = new Color((int) mass[4]);
                pixel = color.getRGB();
                filters.setRGB(w, h, pixel);
            }
        }
    }

    public BufferedImage getMedianFilters() {
        return filters;
    }
}
