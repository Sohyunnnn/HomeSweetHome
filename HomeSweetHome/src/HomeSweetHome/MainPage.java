package HomeSweetHome;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Cursor;
import java.awt.*;
import java.awt.event.*;

import HomeSweetHome.MainUI;

public class MainPage extends JPanel{
	private JLabel BestItemB = new JLabel("Best상품");
	private JLabel vintageB = new JLabel("빈티지");
	private JLabel modernB = new JLabel("모던");
	private JLabel midcenturyB = new JLabel("미드센추리");
	private JLabel contryB = new JLabel("컨트리");
	private JLabel northEuB = new JLabel("북유럽");
	private JLabel naturalB = new JLabel("내추럴");

	private JLabel lampB = new JLabel("조명");
	private JLabel chairB = new JLabel("의자");
	private JLabel closetB = new JLabel("옷장");
	private JLabel bedB = new JLabel("침대");
	private JLabel sofaB = new JLabel("소파");
	private JLabel drawerB = new JLabel("서랍장");
	private JTextField Mappingtf = new Mappingtf("검색");
	private JSlider PriceSl = new JSlider();
	
	ImageIcon profileImg = new ImageIcon("images/profile.png");
	Image pImg = profileImg.getImage();
	Image updateImg = pImg.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
	ImageIcon updateIcon = new ImageIcon(updateImg);
	ImageIcon smallLogo = new ImageIcon("images/smallLogo.png");
	private JButton profileB = new JButton(updateIcon);
	private RoundedButton LogoutB = new RoundedButton("로그아웃");
	private JLabel PriceLabel = new JLabel("1000~50000");
	
	
	public MainPage(MainUI mainUI) {
		setLayout(null);
				
		BestItemB.setBounds(53,82,93,24);
		vintageB.setBounds(202,82,93,24);
		modernB.setBounds(335,82,93,24);
		midcenturyB.setBounds(456,82,93,24);
		contryB.setBounds(624,82,93,24);
		northEuB.setBounds(750,82,93,24);
		naturalB.setBounds(870,82,93,24);
		
		lampB.setBounds(154,144,93,24);
		chairB.setBounds(274,144,93,24);
		closetB.setBounds(396,144,93,24);
		bedB.setBounds(531,144,93,24);
		sofaB.setBounds(648,144,93,24);
		drawerB.setBounds(785,144,93,24);
		Mappingtf.setBounds(311,25,428,28);
		
		LogoutB.setBounds(773,22,95,32);
        LogoutB.setBackground(Color.decode("#D9D9D9"));
        
		profileB.setBounds(882,16,45,45);
		profileB.setBorderPainted(false);
		profileB.setContentAreaFilled(false);
		profileB.setFocusPainted(false);		
		
		profileB.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
        JLabel smallLogoLabel = new JLabel(smallLogo);
        smallLogoLabel.setBounds(16, 16, 262, 39);

        System.out.println("smallLogoLabel bounds: " + smallLogoLabel.getBounds());
        

		
        PriceSl = new JSlider(JSlider.HORIZONTAL, 0, 100000, 100000);
        PriceSl.setPaintLabels(true);
        PriceSl.setPaintTicks(true);
        PriceSl.setPaintTrack(true);
        //PriceSl.setMajorTickSpacing(50);
        //PriceSl.setMinorTickSpacing(50);
        PriceSl.addChangeListener(new MyChangeListener());
        PriceSl.setBounds(569, 205, 200, 50);
        PriceSl.setBackground(Color.WHITE);
        PriceLabel.setBounds(649,250,70,18);
        PriceLabel.setBackground(Color.WHITE);
		
		add(BestItemB);
		add(vintageB);
		add(modernB);
		add(midcenturyB);
		add(contryB);
		add(northEuB);
		add(naturalB);
		
		add(lampB);
		add(chairB);
		add(closetB);
		add(bedB);
		add(sofaB);
		add(drawerB);
		
		add(profileB);
		add(LogoutB);
		add(smallLogoLabel);
		add(Mappingtf);
		add(PriceSl);
		add(PriceLabel);
		
		setBackground(Color.WHITE);
		
	    
		
        LogoutB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainUI.showStartPanel(); // StartPanel로 전환
            }
        });
        profileB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainUI.showWishListPanel(); // WishListPanel로 전환
            }
        });
		
		
	}
		class MyChangeListener implements ChangeListener {
			public void stateChanged(ChangeEvent e) {
				//int minimum = PriceSl.getValue();
				int maximum = PriceSl.getValue();
				PriceLabel.setText("0~"+maximum);
			}
				

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
