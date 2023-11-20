package HomeSweetHome;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import HomeSweetHome.HintTextField;



public class MainUI extends JFrame {
    private JPanel cardPanel;
    private CardLayout cardLayout;

    private StartPanel startPanel;
    private SignUpPanel signUpPanel;
    private LogInPanel logInPanel;
    private ImagePanel imagePanel;

    public MainUI() {
        setTitle("Home Sweet Home");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(984, 662);
        //492, 331

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        startPanel = new StartPanel(this);
        signUpPanel = new SignUpPanel(this);
        logInPanel = new LogInPanel(this);
        imagePanel = new ImagePanel(this);

        cardPanel.add(startPanel, "start");
        cardPanel.add(signUpPanel, "signUp");
        cardPanel.add(logInPanel, "login");
        cardPanel.add(imagePanel, "image");

        add(cardPanel);
        setVisible(true);
    }

    public void showSignUpPanel() {
        cardLayout.show(cardPanel, "signUp");
    }

    public void showStartPanel() {
        cardLayout.show(cardPanel, "start");
    }

    public void showLogInPanel() {
        cardLayout.show(cardPanel, "login");
    }
    
    public void showImagePanel() {
        cardLayout.show(cardPanel, "image");
    }

    public static void main(String[] args) {
        new MainUI();
    }
}

class StartPanel extends JPanel {
    public StartPanel(MainUI mainUI) {
        setLayout(null);

        JButton loginButton = new JButton("로그인");
        JButton signUpButton = new JButton("회원가입");

        ImageIcon logo = new ImageIcon("images/Logo.png");

        loginButton.setBounds(361, 349, 260, 59);
        signUpButton.setBounds(361, 458, 260, 59);
        
        JLabel logoLabel = new JLabel(logo);
        logoLabel.setBounds(57, 151, logo.getIconWidth(), logo.getIconHeight());


        add(loginButton);
        add(signUpButton);
        
        add(logoLabel);

        setBackground(Color.WHITE);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainUI.showLogInPanel(); // LogInPanel로 전환
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainUI.showSignUpPanel(); // SignUpPanel로 전환
            }
        });
        
        System.out.println("Logo Width: " + logo.getIconWidth());
        System.out.println("Logo Height: " + logo.getIconHeight());
    }
}

class SignUpPanel extends JPanel {
    public SignUpPanel(MainUI mainUI) {
        setLayout(null);

        JTextField welcomeMent = new JTextField("환영합니다. HomeSweet Home입니다.\n 회원가입을 위한 아이디와 비밀번호를 입력해주세요.");
        HintTextField idInput = new HintTextField("로그인");
        HintPasswordField passwordInput = new HintPasswordField("비밀번호");
        HintPasswordField passCheckInput = new HintPasswordField("비밀번호 확인");
        JButton SignUpConfirmation = new JButton("확인");

        welcomeMent.setBounds(185, 86, 605, 120);
        idInput.setBounds(275, 255, 425, 59);
        passwordInput.setBounds(275, 343, 425, 59);
        passCheckInput.setBounds(275, 431, 425, 59);
        SignUpConfirmation.setBounds(387, 539, 209, 59);

        add(welcomeMent);
        add(idInput);
        add(passwordInput);
        add(passCheckInput);
        add(SignUpConfirmation);

        setBackground(Color.BLUE);
      //컬러는 패널 교체를 편리하게 보기 위함. 코드 로직이 탄탄해지면 삭제 예정

        SignUpConfirmation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainUI.showStartPanel(); // StartPanel로 전환 -> loginPanel로 넘어가는 거 아니었는지?
            }
        });
    }
}


class LogInPanel extends JPanel {
    public LogInPanel(MainUI mainUI) {
    	setLayout(null);

        JButton loginButton = new JButton("로그인");
        //로그인 페이지 구현
        HintTextField checkidInput = new HintTextField("로그인");
        HintPasswordField checkpasswordInput = new HintPasswordField("비밀번호");

        loginButton.setBounds(361, 380, 260, 59);
        checkidInput.setBounds(275, 210, 425, 59);
        checkpasswordInput.setBounds(275, 300, 425, 59);
        
        add(checkidInput);
        add(checkpasswordInput);
        add(loginButton);

        setBackground(Color.GREEN);
        //컬러는 패널 교체를 편리하게 보기 위함. 코드 로직이 탄탄해지면 삭제 예정

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 회원가입 버튼을 눌렀을 때 수행할 로직을 여기에 추가 <- 요거는 회원가입 패널에 넣는 거 아닌가요
                mainUI.showImagePanel(); // StartPanel로 전환 
                //이미지 클릭하는 패널 추가해서 이미지 패널로 넘어가게 수정
            }
        });
    }
}

class ImagePanel extends JPanel {
	public ImagePanel(MainUI mainUI) {
//		GridLayout layout = new GridLayout(2,3);
//		setLayout(layout);
//		ImageIcon imgTest = new ImageIcon("images/sunset.jpg");
//		ImageIcon imgTest1 = new ImageIcon("images/test.jpg");

		
		setLayout(null);
		
		JButton Img1 = new JButton("이미지");
		JButton Img2 = new JButton("이미지");
		JButton Img3 = new JButton("이미지");
		JButton Img4 = new JButton("이미지");
		JButton Img5 = new JButton("이미지");
		JButton Img6 = new JButton("이미지");//
		
		Img1.setBounds(20, 20, 288, 291);
		Img2.setBounds(348, 20, 288, 291);
		Img3.setBounds(676, 20, 288, 291);
		Img4.setBounds(20, 351, 288, 291);
		Img5.setBounds(348, 351, 288, 291);
		Img6.setBounds(676, 351, 288, 291);

		
		add(Img1);
		add(Img2);
		add(Img3);
		add(Img4);
		add(Img5);
		add(Img6);
		

		
		
		
	}
}
