package HomeSweetHome;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.RoundRectangle2D;

public class HintPasswordField extends JPasswordField {
    private String hint;

    public HintPasswordField(String hint) {
        this.hint = hint;
        setForeground(Color.GRAY);
        setText(hint);
        setEchoChar((char) 0);
        setFont(new Font("굴림체", Font.PLAIN, 12));

        this.addFocusListener(new FocusListener() {
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

    public class RoundJPasswordField extends JPasswordField {
        private Shape shape;

        public RoundJPasswordField() {
            super();
            setOpaque(false); // suggested by @AVD in comment
        }

        protected void paintComponent(Graphics g) {
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

}
