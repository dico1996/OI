package Segmentation;

/**
 * Created by Dico on 11.04.16.
 */
public class Segment {
    int numberSegment, pixelCount, redSegment, greenSegment, blueSegment, reds, greens, blues;

    public Segment(int numberSegment, int pixel) {
        this.numberSegment = numberSegment;
        add(pixel);
    }

    int getNumberSegment() {
        return numberSegment;
    }

    int getRGB() {
        int r = reds / pixelCount;
        int g = greens / pixelCount;
        int b = blues / pixelCount;
        return 0xff000000 | r << 16 | g << 8 | b;
    }

    void add(int pixel) {
        reds += (pixel >> 16) & 0xff;
        greens += (pixel >> 8) & 0xff;
        blues += pixel & 0xff;
        pixelCount++;
        redSegment = reds / pixelCount;
        greenSegment = greens / pixelCount;
        blueSegment = blues / pixelCount;
    }

    int distance(int pixel) {
        int redPixel = (pixel >> 16) & 0xff;
        int greenPixel = (pixel >> 8) & 0xff;
        int bluePixel = pixel & 0xff;
        int redDistance = Math.abs(redSegment - redPixel);
        int greenDistance = Math.abs(greenSegment - greenPixel);
        int blueDistance = Math.abs(blueSegment - bluePixel);
        int distance = (redDistance + greenDistance + blueDistance) / 3;
        return distance;
    }
}
