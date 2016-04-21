package RotationAndScale;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by Dico on 04.04.16.
 */
public class RotationAndScaleView extends JDialog{
    private JPanel panel, panelSlide;
    private BufferedImage DEFAULT_IMAGE;
    private ScrollPane scrollStartImage;
    private JLabel labelStartImage;
    private TurnImage turnImage;
    private ScaleImage scaleImage;
    private JSlider sliderTurn, sliderScale;

    public RotationAndScaleView() {
        setTitle("Поворот и масштабирование");
        setSize(500, 700);
        panel = new JPanel();
        panelSlide = new JPanel();

        JMenuBar menuBar = new JMenuBar();
        JMenu action = new JMenu("Действия");
        JMenuItem loadPicture = new JMenuItem("Загрузить картинку");

        sliderTurn = new JSlider(JSlider.HORIZONTAL, 0, 360, 0);
        sliderTurn.setMajorTickSpacing(90);
        sliderTurn.setMinorTickSpacing(1);
        sliderTurn.setPaintTicks(true);
        sliderTurn.setPaintLabels(true);

        sliderScale = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
        sliderScale.setMajorTickSpacing(20);
        sliderScale.setMinorTickSpacing(1);
        sliderScale.setPaintTicks(true);
        sliderScale.setPaintLabels(true);

        setJMenuBar(menuBar);
        menuBar.add(action);
        action.add(loadPicture);

        scrollStartImage = new ScrollPane();
        labelStartImage = new JLabel();
        scrollStartImage.setBounds(30, 20, 310, 400);
        scrollStartImage.add(labelStartImage);
        panel.setLayout(new BorderLayout());

        panel.add(scrollStartImage, BorderLayout.NORTH);
        panel.add(panelSlide, BorderLayout.SOUTH);

        panelSlide.setLayout(new BoxLayout(panelSlide, BoxLayout.Y_AXIS));
        panelSlide.add(sliderTurn);
        panelSlide.add(sliderScale);

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
                            "Выберите картинку.");
                }
            }
        });

        sliderTurn.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider sourse = (JSlider) e.getSource();
                turnImage = new TurnImage(sourse.getValue(), DEFAULT_IMAGE);
                setImageView(turnImage.getImage());
            }
        });

        sliderScale.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider sourse = (JSlider) e.getSource();
                scaleImage = new ScaleImage(sourse.getValue(), DEFAULT_IMAGE);
                setImageView(scaleImage.getImage());
            }
        });
        setVisible(true);
    }

    public void setImageView(BufferedImage image) {
        labelStartImage.setIcon(new ImageIcon(image));
        panel.revalidate();
    }
}
