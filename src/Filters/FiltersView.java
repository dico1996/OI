package Filters;

import javax.imageio.ImageIO;
import javax.print.attribute.standard.Media;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;


/**
 * Created by Dico on 19.02.16.
 */
public class FiltersView extends JDialog {
    private JPanel panel;
    private BufferedImage DEFAULT_IMAGE;
    private ScrollPane scrollStartImage, scrollFinishImage;
    private JLabel labelStartImage, labelFinishImage;

    public FiltersView() {
        setTitle("�������");
        setSize(700, 500);
        panel = new JPanel();

        JMenuBar menuBar = new JMenuBar();
        JMenu action = new JMenu("��������");
        JMenu filters = new JMenu("�������");
        JMenuItem loadPicture = new JMenuItem("��������� ��������");
        JMenuItem matrixFilterButton = new JMenuItem("��������� ������");
        JMenuItem medianFilterButton = new JMenuItem("��������� ������");
        JMenuItem sepiaFilterButton = new JMenuItem("������");

        setJMenuBar(menuBar);
        menuBar.add(action);
        menuBar.add(filters);
        action.add(loadPicture);
        filters.add(matrixFilterButton);
        filters.add(medianFilterButton);
        filters.add(sepiaFilterButton);

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
                    int ret = fileChooser.showDialog(null, "openfile");
                    if (ret == JFileChooser.APPROVE_OPTION) {
                        File file = fileChooser.getSelectedFile();
                        DEFAULT_IMAGE = ImageIO.read(file);
                        setImageView(DEFAULT_IMAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                            "�������� ��������.");
                }
            }
        });

        matrixFilterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MatrixFilter matrixFilter = new MatrixFilter(DEFAULT_IMAGE);
                    setFinishImageView(matrixFilter.getFilters());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "�������� ��������.");
                }
            }
        });

        medianFilterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MedianFilter medianFilter = new MedianFilter(DEFAULT_IMAGE);
                    setFinishImageView(medianFilter.getMedianFilters());
                } catch(Exception ex) {
                    JOptionPane.showMessageDialog(null, "�������� ��������.");
                }
            }
        });

        sepiaFilterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    SepiaFilter sepiaFilter = new SepiaFilter(DEFAULT_IMAGE);
                    setFinishImageView(sepiaFilter.getFilterImageSepia());
                } catch(Exception ex) {
                    JOptionPane.showMessageDialog(null, "�������� ��������.");
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
