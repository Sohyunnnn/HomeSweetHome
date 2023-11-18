package HomeSweetHome;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

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
}
