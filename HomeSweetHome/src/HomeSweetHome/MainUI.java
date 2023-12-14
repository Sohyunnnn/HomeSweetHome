package HomeSweetHome;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class MainUI extends JFrame {
    private JPanel cardPanel;
    private CardLayout cardLayout;

    private StartPanel startPanel;
    private SignUpPanel signUpPanel;
    private LogInPanel logInPanel;
    private ImagePanel imagePanel;
    private WishListPanel wishListPanel;
    private MainPage mainPage;
    

    public MainUI() {
        setTitle("Home Sweet Home");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(984, 662);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        startPanel = new StartPanel(this);
        signUpPanel = new SignUpPanel(this);
        logInPanel = new LogInPanel(this);
        imagePanel = new ImagePanel(this);
        wishListPanel = new WishListPanel(this);
        mainPage = new MainPage(this);

        cardPanel.add(startPanel, "start");
        cardPanel.add(signUpPanel, "signUp");
        cardPanel.add(logInPanel, "login");
        cardPanel.add(imagePanel, "image");
        cardPanel.add(wishListPanel, "WishList");
        cardPanel.add(mainPage, "MainPage");

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
    
    public void showWishListPanel() {
        cardLayout.show(cardPanel, "WishList");
    }
    
    public void showMainPage() {
    	int selectedStyleCode = imagePanel.getSelectedStyleCode();
    	mainPage.filterProductsByStyle(selectedStyleCode);
    	mainPage.getScrollPane().requestFocusInWindow();
    	
        cardLayout.show(cardPanel, "MainPage");
    }

    public static void main(String[] args) {
        new MainUI();
    }
}

class StartPanel extends JPanel {
    public StartPanel(MainUI mainUI) {
        setLayout(null);
       
        RoundedButton loginButton = new RoundedButton("로그인");
        RoundedButton signUpButton = new RoundedButton("회원가입");
        Font customFont = new Font("굴림체", Font.PLAIN, 27);
        loginButton.setCustomFont(customFont);
        signUpButton.setCustomFont(customFont);
        loginButton.setBackground(new Color(0x16, 0x3A, 0x9C)); // 배경색 설정
        loginButton.setForeground(new Color(255, 255, 255)); // 글자색 설정
        signUpButton.setBackground(new Color(0x16, 0x3A, 0x9C)); // 배경색 설정
        signUpButton.setForeground(new Color(255, 255, 255)); // 글자색 설정
        

        //ImageIcon logo = new ImageIcon("images/logo.svg");
        ImageIcon logo = new ImageIcon("images/Logo.png");


        loginButton.setBounds(361, 349, 260, 60);
        signUpButton.setBounds(361, 458, 260, 60);
        
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

    }
}

class SignUpPanel extends JPanel {
    public SignUpPanel(MainUI mainUI) {
        setLayout(null);

        JLabel welcomeMent = new JLabel("<html><body><center>환영합니다.<br> <br>회원가입을 위한 아이디와 비밀번호를 입력해주세요.</center></body></html>");
        HintTextField idInput = new HintTextField("로그인");
        HintPasswordField passwordInput = new HintPasswordField("비밀번호");
        HintPasswordField passCheckInput = new HintPasswordField("비밀번호 확인");
        RoundedButton SignUpConfirmation = new RoundedButton("확인");
        
        Font customFont = new Font("굴림체", Font.PLAIN, 27);
        SignUpConfirmation.setCustomFont(customFont);
        SignUpConfirmation.setBackground(new Color(0x16, 0x3A, 0x9C)); // 배경색 설정
        SignUpConfirmation.setForeground(new Color(255, 255, 255)); // 글자색 설정
        
        ImageIcon SignUpShape = new ImageIcon("images/SignUpShape.png");
        
        ImageIcon smallLogo = new ImageIcon("images/smallLogo.png");
        
        JLabel signUpShapeLabel = new JLabel(SignUpShape);
        signUpShapeLabel.setBounds(0, 0, SignUpShape.getIconWidth(), SignUpShape.getIconHeight());
        
        JLabel smallLogoLabel = new JLabel(smallLogo);
        smallLogoLabel.setBounds(16, 16, 262, 39);


        welcomeMent.setBounds(36, 260, 287, 153);
        idInput.setBounds(487, 107, 425, 59);
        passwordInput.setBounds(487, 223, 425, 59);
        passCheckInput.setBounds(487, 331, 425, 59);
        SignUpConfirmation.setBounds(586, 476, 209, 59);

        add(welcomeMent);
        add(idInput);
        add(passwordInput);
        add(passCheckInput);
        add(SignUpConfirmation);
        add(smallLogoLabel);
        add(signUpShapeLabel);

        setBackground(Color.WHITE);

        SignUpConfirmation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //mainUI.showStartPanel(); // StartPanel로 전환
            	mainUI.showWishListPanel();
            }
        });
    }
}


class LogInPanel extends JPanel {
    public LogInPanel(MainUI mainUI) {
    	setLayout(null);

        RoundedButton loginButton = new RoundedButton("로그인");
        HintTextField checkidInput = new HintTextField("로그인");
        HintPasswordField checkpasswordInput = new HintPasswordField("비밀번호");
        
        Font customFont = new Font("굴림체", Font.PLAIN, 27);
        loginButton.setCustomFont(customFont);
        loginButton.setBackground(new Color(0x16, 0x3A, 0x9C)); // 배경색 설정
        loginButton.setForeground(new Color(255, 255, 255)); // 글자색 설정
        
        ImageIcon logInShape = new ImageIcon("images/logInShape.png");
        
        ImageIcon smallLogo = new ImageIcon("images/smallLogo.png");
        
        JLabel logInShapeLabel = new JLabel(logInShape);
        logInShapeLabel.setBounds(530, 0, logInShape.getIconWidth(), logInShape.getIconHeight());
        
        JLabel smallLogoLabel = new JLabel(smallLogo);
        smallLogoLabel.setBounds(16, 16, smallLogo.getIconWidth(), smallLogo.getIconHeight());



        loginButton.setBounds(146, 419, 260, 59);
        checkidInput.setBounds(67, 129, 425, 59);
        checkpasswordInput.setBounds(67, 268, 425, 59);
        
        add(checkidInput);
        add(checkpasswordInput);
        add(loginButton);
        add(smallLogoLabel);
        add(logInShapeLabel);

        setBackground(Color.WHITE);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainUI.showImagePanel(); // StartPanel로 전환 
            }
        });
    }
}

class ImagePanel extends JPanel { 
    private MainUI mainUI;
    private int selectedStyleCode;

    public ImagePanel(MainUI mainUI) {
        this.mainUI = mainUI;
        setLayout(null);

        JLabel largerLogoLabel = new JLabel();
        setLabelProperties(largerLogoLabel, 6);
        add(largerLogoLabel);

        JButton[] imageButtons = new JButton[5];

        for (int i = 1; i <= 5; i++) {
            imageButtons[i - 1] = new JButton();
            setButtonProperties(imageButtons[i - 1], i);
            addListener(imageButtons[i - 1], i);
            add(imageButtons[i - 1]);
        }
    }

    private void setLabelProperties(JLabel label, int index) {
        ImageIcon icon = null;

        switch (index) {
            case 6:
                icon = new ImageIcon("images/LargeLogo.png");
                break;
        }

        label.setIcon(icon);
        label.setBounds(684,344, 216, 243); // 라벨 위치 설정
    }

    private void setButtonProperties(JButton button, int index) {
        ImageIcon icon = null;

        switch (index) {
            case 1:
                icon = new ImageIcon("images/modern.png");
                break;
            case 2:
                icon = new ImageIcon("images/vintage.png");
                break;
            case 3:
                icon = new ImageIcon("images/midcentury.png");
                break;
            case 4:
                icon = new ImageIcon("images/natural.png");
                break;
            case 5:
                icon = new ImageIcon("images/contry.png");
                break;
        }

        button.setIcon(icon);
        button.setBounds((index - 1) % 3 * 309 + 43, (index - 1) / 3 * 301 + 34, 260, 260);
    }

    private void addListener(JButton button, final int index) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	selectedStyleCode = index;
                mainUI.showMainPage();
            }
        });
    }
    public int getSelectedStyleCode() {
        return selectedStyleCode;
    }
}


class WishListPanel extends JPanel {
    public WishListPanel(MainUI mainUI) {
        setLayout(null);

        JLabel id = new JLabel("아이디");
        RoundedButton SignUpConfirmation = new RoundedButton("확인");//나중에 삭제 할 코드
        JButton logOut = new JButton("로그아웃");
        JLabel wishList = new JLabel("찜 목록");
        
        id.setOpaque(true);
        id.setBackground(Color.decode("#D9D9D9"));
       
        
        ImageIcon smallLogo = new ImageIcon("images/smallLogo.png");
        
        JLabel smallLogoLabel = new JLabel(smallLogo);
        smallLogoLabel.setBounds(16, 16, smallLogo.getIconWidth(), smallLogo.getIconHeight());
        
        ImageIcon profile = new ImageIcon("images/profile.png");
        
        JLabel profileLabel = new JLabel(profile);
        profileLabel.setBounds(63, 101, 200, 200);


        id.setBounds(63, 364, 200, 47);
        SignUpConfirmation.setBounds(586, 476, 209, 59);//나중에 삭제 할 코드
        wishList.setBounds(342, 49, 200, 47);
        logOut.setBounds(63,455,200,47);
        
        id.setFont(new Font("굴림체", Font.PLAIN, 18)); 
        wishList.setFont(new Font("굴림체", Font.PLAIN, 35));
        
        logOut.setBackground(Color.decode("#D9D9D9"));


        add(id);
        add(SignUpConfirmation);//나중에 삭제 할 코드
        add(smallLogoLabel);
        add(profileLabel);
        add(wishList);
        add(logOut);

        setBackground(Color.WHITE);

        SignUpConfirmation.addActionListener(new ActionListener() {//나중에 삭제 할 코드
            @Override
            public void actionPerformed(ActionEvent e) {
                mainUI.showStartPanel(); // StartPanel로 전환 
            }
        });
    }
}
