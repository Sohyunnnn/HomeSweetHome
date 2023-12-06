package HomeSweetHome;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

public class MainPage extends JPanel {
    private JLabel vintageB = new JLabel("빈티지");
    private JLabel modernB = new JLabel("모던");
    private JLabel midcenturyB = new JLabel("미드센추리");
    private JLabel contryB = new JLabel("컨트리");
    private JLabel naturalB = new JLabel("내추럴");

    private JLabel lampB = new JLabel("조명");
    private JLabel chairB = new JLabel("의자");
    private JLabel bedB = new JLabel("침대");
    private JLabel sofaB = new JLabel("소파");
    private JTextField Mappingtf = new Mappingtf("검색");
    private CustomSlider PriceSl = new CustomSlider();
    private String[] sort = {"최신순", "인기순", "높은가격순", "낮은가격순"};
    private JComboBox<String> strCombo = new JComboBox<String>(sort);

    ImageIcon profileImg = new ImageIcon("images/profile.png");
    Image pImg = profileImg.getImage();
    Image updateImg = pImg.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
    ImageIcon updateIcon = new ImageIcon(updateImg);
    ImageIcon smallLogo = new ImageIcon("images/smallLogo.png");
    private JButton profileB = new JButton(updateIcon);
    private RoundedButton LogoutB = new RoundedButton("로그아웃");
    private JLabel maxPriceLabel = new JLabel("1000000");
    private JLabel minPriceLabel = new JLabel("1000");

    public MainPage(MainUI mainUI) {
        setLayout(null);

        vintageB.setBounds(103, 84, 93, 24);
        modernB.setBounds(277, 84, 93, 24);
        midcenturyB.setBounds(438, 84, 93, 24);
        contryB.setBounds(650, 84, 93, 24);
        naturalB.setBounds(827, 84, 93, 24);

        lampB.setBounds(357, 138, 93, 24);
        chairB.setBounds(169, 138, 93, 24);
        bedB.setBounds(70, 138, 93, 24);
        sofaB.setBounds(263, 138, 93, 24);
        Mappingtf.setBounds(311, 25, 428, 28);

        LogoutB.setBounds(773, 22, 95, 32);
        LogoutB.setBackground(Color.decode("#D9D9D9"));

        profileB.setBounds(882, 16, 45, 45);
        profileB.setBorderPainted(false);
        profileB.setContentAreaFilled(false);
        profileB.setFocusPainted(false);

        profileB.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel smallLogoLabel = new JLabel(smallLogo);
        smallLogoLabel.setBounds(16, 16, 262, 39);

        strCombo.setBounds(843, 141, 70, 22);

        // PriceSl 위치 설정
        PriceSl.setBounds(510, 113, 250, 70);

        add(vintageB);
        add(modernB);
        add(midcenturyB);
        add(contryB);
        add(naturalB);

        add(lampB);
        add(chairB);
        add(bedB);
        add(sofaB);

        add(profileB);

        add(LogoutB);
        add(smallLogoLabel);
        add(Mappingtf);
        add(PriceSl);
        add(maxPriceLabel);
        add(minPriceLabel);
        add(strCombo);

        setBackground(Color.WHITE);
        

        LogoutB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainUI.showStartPanel();
            }
        });

        profileB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainUI.showWishListPanel();
            }
        });

        strCombo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 콤보박스 눌렀을 때 리스너 구현
            }
        });

        PriceSl.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int minPrice = PriceSl.getLowerValue();
                int maxPrice = PriceSl.getUpperValue();
                maxPriceLabel.setText(String.valueOf(maxPrice));
                minPriceLabel.setText(String.valueOf(minPrice));
            }
        });
        
        
    }

    class Mappingtf extends JTextField {
        private String mapword;

        public Mappingtf(String mapword) {
            this.mapword = mapword;
            setForeground(Color.GRAY);
            setText(mapword);
            setFont(new Font("굴림체", Font.PLAIN, 12));

            this.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    if (getText().equals(mapword)) {
                        setText("");
                        setForeground(Color.BLACK);
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (getText().isEmpty()) {
                        setText(mapword);
                        setForeground(Color.GRAY);
                    }
                }
            });
        }
    }
}
