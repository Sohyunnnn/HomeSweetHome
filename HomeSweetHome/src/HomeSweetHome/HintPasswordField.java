package HomeSweetHome;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.RoundRectangle2D;

public class HintPasswordField extends JPasswordField {

    private Shape shape;
    private String hint;

    public HintPasswordField(String hint) {
        super();
        this.hint = hint;
        setOpaque(false);
        setForeground(Color.GRAY);
        setText(hint);
        setEchoChar((char) 0);
        setFont(new Font("굴림체", Font.PLAIN, 12));
        
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            	if (getText().equals(hint)) {
                  setText("");
                  setForeground(Color.BLACK);
                  setEchoChar('*'); // 비밀번호 필드의 echo char 설정
            	}
            }

            @Override
            public void focusLost(FocusEvent e) {
            	if (getText().isEmpty()) {
                  setText(hint);
                  setForeground(Color.GRAY);
                  setEchoChar((char) 0);
            	}
            }
        });
    }

    protected void paintComponent(Graphics g) {
        if (getPassword().length == 0 && !hasFocus()) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(Color.GRAY);
            g2.drawString(hint, 5, 15); // Adjust the position of the hint text as needed
            g2.dispose();
        }

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
