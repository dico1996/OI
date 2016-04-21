package Segmentation;

import org.opencv.core.Core;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by Dico on 11.04.16.
 */
public class SegmentationView extends JDialog {
    private JPanel panel;
    private BufferedImage DEFAULT_IMAGE;
    private ScrollPane scrollStartImage, scrollFinishImage;
    private JLabel labelStartImage, labelFinishImage;
    private JTextField textField;

    public SegmentationView() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        setTitle("Сегментация");
        setSize(700, 500);
        panel = new JPanel();

        JMenuBar menuBar = new JMenuBar();
        JMenu action = new JMenu("Действия");
        JMenuItem loadPicture = new JMenuItem("Загрузить картинку");
        JMenuItem segmentation = new JMenuItem("Сегментирование");

        setJMenuBar(menuBar);
        menuBar.add(action);
        action.add(loadPicture);
        action.add(segmentation);

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

        textField = new JTextField(10);
        panel.add(textField, BorderLayout.SOUTH);

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
                        DEFAULT_IMAGE = ImageIO.read(file);
                        setImageView(DEFAULT_IMAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                            "Выберите картинку.");
                }
            }
        });

        segmentation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int numberSegment = Integer.parseInt(textField.getText());
                    SegmentationKMeans segmentationKMeans = new SegmentationKMeans(DEFAULT_IMAGE, numberSegment);
                    setFinishImageView(segmentationKMeans.getResult());
                } catch (Exception v) {
                    JOptionPane.showMessageDialog(null, "Введите корректные данные.");
                }
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
}
