package Segmentation;

import java.awt.image.BufferedImage;
import java.util.Arrays;

/**
 * Created by Dico on 11.04.16.
 */
public class SegmentationKMeans {
    private BufferedImage image;
    private Segment[] segments;
    private int numberSegments;

    public SegmentationKMeans(BufferedImage image, int numberSegments) {
        this.image = image;
        this.numberSegments = numberSegments;
    }

    public BufferedImage getResult() {
        int width = image.getWidth();
        int height = image.getHeight();
        segments = createSegments(image, numberSegments);
        int[] numberArray = new int[width * height];
        Arrays.fill(numberArray, -1);
        boolean pixelChangedSegment = true;
        int iteration = 0;

        while (pixelChangedSegment) {
            pixelChangedSegment = false;

            for (int h = 0; h < height; h++) {
                for (int w = 0; w < width; w++) {
                    int pixel = image.getRGB(w, h);
                    Segment segment = findMinimalSegment(pixel);
                    if (numberArray[w + width * h] != segment.getNumberSegment()) {
                        segment.add(pixel);
                        pixelChangedSegment = true;
                        numberArray[w + width * h] = segment.getNumberSegment();
                    }
                }
            }
            iteration++;
        }
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int segmentId = numberArray[width * y + x];
                result.setRGB(x, y, segments[segmentId].getRGB());
            }
        }
        return result;
    }

    public Segment findMinimalSegment(int pixel) {
        Segment segment = null;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < segments.length; i++) {
            int distance = segments[i].distance(pixel);
            if (distance < min) {
                min = distance;
                segment = segments[i];
            }
        }
        return segment;
    }

    public Segment[] createSegments(BufferedImage image, int numberSegment) {
        Segment[] result = new Segment[numberSegment];
        int width = 0;
        int height = 0;
        int dx = image.getWidth() / numberSegment;
        int dy = image.getHeight() / numberSegment;
        for (int i = 0; i < numberSegment; i++) {
            result[i] = new Segment(i, image.getRGB(width, height));
            width += dx;
            height += dy;
        }
        return result;
    }
}
