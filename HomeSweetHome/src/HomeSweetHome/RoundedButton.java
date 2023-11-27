package HomeSweetHome;

import javax.swing.*;
import java.awt.*;

public class RoundedButton extends JButton {
	private Font customFont; // 추가: 외부에서 설정할 폰트 변수
    
	public RoundedButton() {
        super();
        decorate();
    }

    public RoundedButton(String text) {
        super(text);
        decorate();
    }

    public RoundedButton(Action action) {
        super(action);
        decorate();
    }

    public RoundedButton(Icon icon) {
        super(icon);
        decorate();
    }

    public RoundedButton(String text, Icon icon) {
        super(text, icon);
        decorate();
    }

    protected void decorate() {
        setBorderPainted(false);
        setOpaque(false);
    }
    
    // 추가: 외부에서 폰트 설정하는 메서드
    public void setCustomFont(Font font) {
        this.customFont = font;
        repaint(); // 변경된 폰트를 적용하기 위해 다시 그리기
    }
    

    @Override
    protected void paintComponent(Graphics g) {
        Color c = getBackground();
        Color o = getForeground();//외부에서 색을 지정하도록 설정
        
//        Color c = new Color(0x16, 0x3A, 0x9C);
//        Color o = new Color(255, 255, 255);
        
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        if (getModel().isArmed()) {
            graphics.setColor(c.darker());
        } else if (getModel().isRollover()) {
            graphics.setColor(c.brighter());
        } else {
            graphics.setColor(c);
        }
        graphics.fillRoundRect(0, 0, width, height, 10, 10);
        
        //기존 폰트 사용 또는 외부에서 설정한 폰트 사용
        Font buttonFont = (customFont != null) ? customFont : getFont();
        graphics.setFont(buttonFont);
        
        //graphics.setFont(getFont().deriveFont(Font.PLAIN, 27));
        //setFont를 먼저 설정하고 fontmetrics를 해줘야하는데 두개 순서가 뒤바뀌어서 자꾸 위치가 제대로 안되었던 것
        FontMetrics fontMetrics = graphics.getFontMetrics();
        int textX = (width - fontMetrics.stringWidth(getText())) / 2;
        int textY = (height - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent();
        graphics.setColor(o);
        graphics.drawString(getText(), textX, textY);
        graphics.dispose();
        super.paintComponent(g);
    }
}
