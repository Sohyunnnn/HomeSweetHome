package HomeSweetHome;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.RoundRectangle2D;

public class HintTextField extends JTextField {
    private String hint;
    private Shape shape;

    public HintTextField(String hint) {
    	super();
        this.hint = hint;
        setOpaque(false);
        setForeground(Color.GRAY);
        setText(hint);
        setFont(new Font("굴림체", Font.PLAIN, 12));

        this.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (getText().equals(hint)) {
                    setText("");
                    setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getText().isEmpty()) {
                    setText(hint);
                    setForeground(Color.GRAY);
                }
            }
        });
    }
    
    protected void paintComponent(Graphics g) {
        //if (getPassword().length == 0 && !hasFocus()) { <- 이부분은 패스워드필드에만 필요해서 그냥 뺌
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(Color.GRAY);
            g2.drawString(hint, 5, 15); // Adjust the position of the hint text as needed
            g2.dispose();
        //}

        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
        super.paintComponent(g);
    }

    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
    }

    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
        }
        return shape.contains(x, y);
    }
}
