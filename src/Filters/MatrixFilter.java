package Filters;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Dico on 21.02.16.
 */
public class MatrixFilter {
    private double[][] matrix = {{-1, -1, -1}, {-1, 9, -1}, {-1, -1, -1}};
    private BufferedImage filters;
    private double div = 0;

    public MatrixFilter(BufferedImage image) {
        filters = new BufferedImage(image.getWidth(), image.getHeight(),
                BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < Math.sqrt(matrix.length); i++)
            for (int j = 0; j < Math.sqrt(matrix.length); j++)
                div += matrix[i][j];

        for (int w = 1; w < image.getWidth() - 1; w++) {
            for (int h = 1; h < image.getHeight() - 1; h++) {
                int pixel;
                int red = 0;
                int green = 0;
                int blue = 0;
                int[][] pixels = new int[3][3];

                for (int i = 0; i < 3; i++)
                    for (int j = 0; j < 3; j++)
                        pixels[i][j] = image.getRGB(w - 1 + i, h - 1 + j);

                for (int i = 0; i < Math.sqrt(pixels.length); i++)
                    for (int j = 0; j < Math.sqrt(pixels.length); j++) {
                        red += (((pixels[i][j] >> 16) & 0xff) * matrix[i][j]) / div;
                        green += (((pixels[i][j] >> 8) & 0xff) * matrix[i][j]) / div;
                        blue += (((pixels[i][j]) & 0xff) * matrix[i][j]) / div;
                    }

                if (red > 255)
                    red = 255;
                if (red < 0)
                    red = 0;
                if (green > 255)
                    green = 255;
                if (green < 0)
                    green = 0;
                if (blue > 255)
                    blue = 255;
                if (blue < 0)
                    blue = 0;

                Color color = new Color(red, green, blue);
                pixel = color.getRGB();
                filters.setRGB(w, h, pixel);
            }
        }
    }

    public BufferedImage getFilters() {
        return filters;
    }
}
