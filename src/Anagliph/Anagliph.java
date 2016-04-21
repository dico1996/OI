package Anagliph;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by Dico on 29.01.16.
 */
public class Anagliph extends JDialog{

    private JPanel panel;
    private BufferedImage DEFAULT_IMAGE_ONE, DEFAULT_IMAGE_TWO;
    private ScrollPane scrollImageFirst, scrollImageSecond, scrollFinishImage;
    private JLabel labelImageFirst, labelImageSecond,labelFinishImage;
    private double[][] leftFilter = {{1, 0, 0}, {0, 0, 0}, {0, 0, 0}};
    private double[][] rightFilter = {{0, 0, 0}, {0, 1, 0}, {0, 0, 1}};
    private JMenuItem loadFirst, loadSecond;

    public Anagliph() {
        setTitle("Анаглиф");
        setSize(1000, 500);
        panel = new JPanel();

        JMenuBar menuBar = new JMenuBar();
        JMenu action = new JMenu("Действия");
        JMenuItem start = new JMenuItem("Начать");
        loadFirst = new JMenuItem("Загрузить первую картинку");
        loadSecond = new JMenuItem("Загрузить вторую картинку");

        setJMenuBar(menuBar);
        menuBar.add(action);
        action.add(start);
        action.add(loadFirst);
        action.add(loadSecond);

        scrollImageFirst = new ScrollPane();
        labelImageFirst = new JLabel();
        scrollImageSecond = new ScrollPane();
        labelImageSecond = new JLabel();
        scrollFinishImage = new ScrollPane();
        labelFinishImage = new JLabel();

        scrollImageFirst.setBounds(30, 20, 310, 400);
        scrollImageFirst.add(labelImageFirst);
        panel.add(scrollImageFirst);
        scrollImageSecond.setBounds(30, 20, 310, 400);
        scrollImageSecond.add(labelImageSecond);
        panel.add(scrollImageSecond);
        scrollFinishImage.setBounds(30, 20, 310, 400);
        scrollFinishImage.add(labelFinishImage);
        panel.add(scrollFinishImage);

        add(panel);

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setImageView(anagliph(DEFAULT_IMAGE_ONE, DEFAULT_IMAGE_TWO, leftFilter, rightFilter), labelFinishImage);
            }
        });

        loadFirst.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    JFileChooser fileChooser = new JFileChooser();
                    int ret = fileChooser.showDialog(null, "openfile");
                    if (ret == JFileChooser.APPROVE_OPTION) {
                        File file = fileChooser.getSelectedFile();
                        DEFAULT_IMAGE_ONE = ImageIO.read(file);
                        setImageView(DEFAULT_IMAGE_ONE, labelImageFirst);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                            "Выберите изображение");
                }
            }
        });

        loadSecond.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    JFileChooser fileChooser = new JFileChooser();
                    int ret = fileChooser.showDialog(null, "openfile");
                    if (ret == JFileChooser.APPROVE_OPTION) {
                        File file = fileChooser.getSelectedFile();
                        DEFAULT_IMAGE_TWO = ImageIO.read(file);
                        setImageView(DEFAULT_IMAGE_TWO, labelImageSecond);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                            "выберите изображение");
                }
            }
        });

        setVisible(true);
    }

    public void setImageView(BufferedImage image, JLabel label) {
            label.setIcon(new ImageIcon(image));
        panel.revalidate();
    }

    public BufferedImage anagliph(BufferedImage imageLeft, BufferedImage imageRight, double[][] leftFilter, double[][] rightFilter) {

        BufferedImage anagliph = new BufferedImage(imageLeft.getWidth(), imageLeft.getHeight(),
                BufferedImage.TYPE_INT_RGB);

        for (int h = 0; h < imageLeft.getHeight(); h++)
            for (int w = 0; w < imageLeft.getWidth(); w++) {

                int pixel = imageLeft.getRGB(w, h);
                int red = ((0xff << 16) & pixel) >> 16;
                int green = ((0xff << 8) & pixel) >> 8;
                int blue = 0xff  & pixel;

                int newRed = (int) (leftFilter[0][0] * red + leftFilter[0][1] * green + leftFilter[0][2] * blue);
                int newGreen = (int) (leftFilter[1][0] * red + leftFilter[1][1] * green + leftFilter[1][2] * blue);
                int newBlue = (int) (leftFilter[2][0] * red + leftFilter[2][1] * green + leftFilter[2][2] * blue);

                pixel = imageRight.getRGB(w, h);
                red = ((0xff << 16) & pixel) >> 16;
                green = ((0xff << 8) & pixel) >> 8;
                blue = 0xff  & pixel;

                newRed += (int) (rightFilter[0][0] * red + rightFilter[0][1] * green + rightFilter[0][2] * blue);
                newGreen += (int) (rightFilter[1][0] * red + rightFilter[1][1] * green + rightFilter[1][2] * blue);
                newBlue += (int) (rightFilter[2][0] * red + rightFilter[2][1] * green + rightFilter[2][2] * blue);

                Color color = new Color(newRed, newGreen, newBlue);
                pixel = color.getRGB();
                anagliph.setRGB(w, h, pixel);
            }
        return anagliph;
    }
}

