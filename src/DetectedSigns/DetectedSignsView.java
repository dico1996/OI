package DetectedSigns;

import OpenCv.DetectObject;
import org.opencv.core.Core;
import org.opencv.core.Mat;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;

/**
 * Created by Dico on 04.04.16.
 */
public class DetectedSignsView extends JDialog {
    private JPanel panel;
    private BufferedImage DEFAULT_IMAGE;
    private ScrollPane scrollStartImage, scrollFinishImage;
    private JLabel labelStartImage, labelFinishImage;
    private String path;

    public DetectedSignsView() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        setTitle("Распознавание знаков");
        setSize(700, 500);
        panel = new JPanel();

        JMenuBar menuBar = new JMenuBar();
        JMenu action = new JMenu("Действия");
        JMenuItem loadPicture = new JMenuItem("Загрузить картинку");
        JMenuItem openCv = new JMenuItem("Распознавание");

        setJMenuBar(menuBar);
        menuBar.add(action);
        action.add(loadPicture);
        action.add(openCv);

        scrollStartImage = new ScrollPane();
        labelStartImage = new JLabel();
        scrollFinishImage = new ScrollPane();
        labelFinishImage = new JLabel();
        scrollStartImage.setBounds(30, 20, 310, 400);
        scrollStartImage.add(labelStartImage);
        panel.add(scrollStartImage);
        scrollFinishImage.setBounds(30, 20, 310, 400);
        scrollFinishImage.add(labelFinishImage);
        panel.add(scrollFinishImage);

        add(panel);

        loadPicture.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setCurrentDirectory(new File("E:\\JAVALAB\\OI1\\res"));
                    int ret = fileChooser.showDialog(null, "openfile");
                    if (ret == JFileChooser.APPROVE_OPTION) {
                        File file = fileChooser.getSelectedFile();
                        path = file.getPath();
                        DEFAULT_IMAGE = ImageIO.read(file);
                        setImageView(DEFAULT_IMAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                            "Выберите картинку.");
                }
            }
        });

        openCv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DetectSigns detectSigns = new DetectSigns(path);
                setFinishImageView(createBufferImage(detectSigns.getImage()));
            }
        });

        setVisible(true);
    }

    public void setImageView(BufferedImage image) {
        labelStartImage.setIcon(new ImageIcon(image));
        panel.revalidate();
    }

    public void setFinishImageView(BufferedImage image) {
        labelFinishImage.setIcon(new ImageIcon(image));
        panel.revalidate();
    }

    public BufferedImage createBufferImage(Mat mat) {
        int type = 0;
        if (mat.channels() == 1) {
            type = BufferedImage.TYPE_BYTE_GRAY;
        } else if (mat.channels() == 3) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        BufferedImage image = new BufferedImage(mat.width(), mat.height(), type);
        mat.get(0, 0, ((DataBufferByte) image.getRaster().getDataBuffer()).getData());
        return image;
    }
}
