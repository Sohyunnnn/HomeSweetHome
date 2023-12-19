package HomeSweetHome;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomSlider extends JPanel {

    private int minValue = 0;
    private int maxValue = 3000000;
    private int lowerValue = 1000000;
    private int upperValue = 2000000;

    private int thumbDiameter = 15;
    private int barWidth = 229;

    private JLabel lowerLabel;
    private JLabel upperLabel;
    private ChangeListener changeListener;

    public CustomSlider() {
        setLayout(null); 
        lowerLabel = new JLabel("" + lowerValue, SwingConstants.CENTER);
        upperLabel = new JLabel("" + upperValue, SwingConstants.CENTER);

        lowerLabel.setBounds(0, 40, 50, 20);
        upperLabel.setBounds(200, 40, 50, 20); 

        add(lowerLabel);
        add(upperLabel);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleThumbDrag(e.getX());
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                handleThumbDrag(e.getX());
            }
        });

        setBackground(Color.WHITE);
    }

    public void addChangeListener(ChangeListener listener) {
        changeListener = listener;
    }

    public int getLowerValue() {
        return lowerValue;
    }

    public int getUpperValue() {
        return upperValue;
    }

    private void handleThumbDrag(int mouseX) {
        int newValue = (int) ((double) (mouseX - thumbDiameter / 2) / barWidth * (maxValue - minValue)) + minValue;

        if (Math.abs(newValue - lowerValue) < Math.abs(newValue - upperValue)) {
            lowerValue = Math.max(minValue, Math.min(newValue, upperValue));
        } else {
            upperValue = Math.max(lowerValue, Math.min(newValue, maxValue));
        }

        lowerLabel.setText("" + lowerValue);
        upperLabel.setText("" + upperValue);

        repaint();

        if (changeListener != null) {
            changeListener.stateChanged(new ChangeEvent(this));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawSlider(g);
    }

    private void drawSlider(Graphics g) {
        int barHeight = 5;

        int lowerThumbX = (int) ((double) (lowerValue - minValue) / (maxValue - minValue) * barWidth);
        int upperThumbX = (int) ((double) (upperValue - minValue) / (maxValue - minValue) * barWidth);

        g.setColor(Color.GRAY);
        g.fillRect(thumbDiameter / 2, 25- barHeight / 2, barWidth, barHeight);

        g.setColor(Color.decode("#163A9C"));
        g.fillOval(lowerThumbX, 25 - thumbDiameter / 2, thumbDiameter, thumbDiameter);

        g.setColor(Color.decode("#163A9C"));
        g.fillOval(upperThumbX, 25 - thumbDiameter / 2, thumbDiameter, thumbDiameter);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Dual Thumb Slider");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(280, 60);
        

        CustomSlider slider = new CustomSlider();
        frame.add(slider);

        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                System.out.println("Lower Value: " + slider.getLowerValue());
                System.out.println("Upper Value: " + slider.getUpperValue());
            }
        });

        frame.setVisible(true);
    }
}
