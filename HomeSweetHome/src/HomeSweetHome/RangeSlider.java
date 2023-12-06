package HomeSweetHome;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicSliderUI;

import java.awt.*;


public class RangeSlider extends JSlider {
    private int lowValue;
    private int highValue;

    public RangeSlider(int min, int max, int lowValue, int highValue) {
        super(min, max);
        this.lowValue = lowValue;
        this.highValue = highValue;
        setOrientation(JSlider.HORIZONTAL);
        setPaintTicks(true);
        setPaintLabels(true);
        setPaintTrack(true);
        setPaintTicks(true);
        setMajorTickSpacing((max - min) / 10);
        setMinorTickSpacing((max - min) / 50);
        setLowValue(lowValue);
        setHighValue(highValue);

        addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                if (!source.getValueIsAdjusting()) {
                    setLowValue(source.getValue());
                }
            }
        });
    }

    public int getLowValue() {
        return lowValue;
    }

    public void setLowValue(int lowValue) {
        this.lowValue = lowValue;
        if (lowValue > highValue) {
            highValue = lowValue;
        }
        setValues(lowValue, highValue);
    }

    public int getHighValue() {
        return highValue;
    }

    public void setHighValue(int highValue) {
        int oldValue = this.highValue;
        this.highValue = Math.min(Math.max(getLowValue(), highValue), getMaximum());
        firePropertyChange("highValue", oldValue, this.highValue);
        if (this.highValue != oldValue) {
            setValueIsAdjusting(true);
            super.setValue(this.highValue);
            fireStateChanged();
            setValueIsAdjusting(false);
        }
    }

    private void setValues(int low, int high) {
        lowValue = low;
        highValue = high;
        setValue(low);
        setValueIsAdjusting(true);
        setHighValue(high);
        setValueIsAdjusting(false);
    }
    
    public void setValues(int[] values) {
        if (values == null || values.length != 2) {
            throw new IllegalArgumentException("Invalid input array");
        }
        setValues(values[0], values[1]);
    }

    @Override
    public void updateUI() {
        setUI(new RangeSliderUI(this));
        updateLabelUIs();
    }
}

class RangeSliderUI extends BasicSliderUI {
    public RangeSliderUI(JSlider slider) {
        super(slider);
    }

    @Override
    public void paintThumb(Graphics g) {
        Rectangle knobBounds = thumbRect;

        int w = knobBounds.width;
        int h = knobBounds.height;

        g.translate(knobBounds.x, knobBounds.y);

        g.setColor(slider.getForeground());
        g.fillRect(0, 0, w, h);

        g.setColor(Color.BLACK);
        g.drawRect(0, 0, w - 1, h - 1);

        // Draw a second thumb for the upper value
        int upperThumbX = xPositionForValue(((RangeSlider) slider).getHighValue());
        g.translate(upperThumbX - knobBounds.x, 0);
        g.setColor(slider.getForeground());
        g.fillRect(0, 0, w, h);
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, w - 1, h - 1);

        g.translate(-upperThumbX + knobBounds.x, -knobBounds.y);
        g.translate(-knobBounds.x, -knobBounds.y);
    }
}
