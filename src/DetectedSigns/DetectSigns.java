package DetectedSigns;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

/**
 * Created by Dico on 04.04.16.
 */
public class DetectSigns {
    private MatOfRect faceDetections;
    private Mat image;

    public DetectSigns(String path) {
        image = Imgcodecs.imread(path);
        CascadeClassifier faceDetector = new  CascadeClassifier("G:\\pictures\\haarcascade\\cascade.xml");
        faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(image, faceDetections);
        for (Rect rect : faceDetections.toArray()) {
            Imgproc.rectangle(image, new Point(rect.x, rect.y),
                    new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(165, 205, 34));
        }
    }

    public Mat getImage() {
        return image;
    }
}
