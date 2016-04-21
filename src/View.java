import Anagliph.Anagliph;
import DetectedSigns.DetectedSignsView;
import Filters.FiltersView;
import OpenCv.OpenCvView;
import RotationAndScale.RotationAndScaleView;
import Segmentation.SegmentationView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Dico on 29.01.16.
 */
public class View extends JFrame{
    private JPanel mainPanel;
    private JButton anagliphButton, filtersButton, rotationAndScaleButton, openCvButton, segmentationButton, detectedSignsButton;

    public View() {
        setTitle("OI 1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPanel = new JPanel();
        anagliphButton = new JButton("Анаглиф");
        filtersButton = new JButton("Фильтры");
        rotationAndScaleButton = new JButton("Поворот и масштабирование");
        openCvButton = new JButton("Распознавание");
        segmentationButton = new JButton("Сегментация");
        detectedSignsButton = new JButton("Распознавание знаков");

        add(mainPanel);
        mainPanel.add(anagliphButton);
        mainPanel.add(filtersButton);
        mainPanel.add(rotationAndScaleButton);
        mainPanel.add(openCvButton);
        mainPanel.add(segmentationButton);
        mainPanel.add(detectedSignsButton);

        anagliphButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Anagliph anagliph = new Anagliph();
            }
        });

        filtersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltersView filters = new FiltersView();
            }
        });

        rotationAndScaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RotationAndScaleView rotationAndScale = new RotationAndScaleView();
            }
        });

        openCvButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OpenCvView openCvView = new OpenCvView();
            }
        });

        segmentationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SegmentationView segmentationView = new SegmentationView();
            }
        });

        detectedSignsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DetectedSignsView detectedSignsView = new DetectedSignsView();
            }
        });

        setVisible(true);
        pack();
    }
}
