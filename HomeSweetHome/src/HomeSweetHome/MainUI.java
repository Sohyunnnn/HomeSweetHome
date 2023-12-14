package HomeSweetHome;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.awt.event.MouseAdapter; 
import java.awt.event.MouseEvent; 
//


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
    	mainPage.setLoggedInUserID(logInPanel.getLoggedInUserID()); // loggedInUserID 값을 설정
    	mainPage.filterProductsByStyle(selectedStyleCode);
    	mainPage.getScrollPane().requestFocusInWindow();
    	
        cardLayout.show(cardPanel, "MainPage");
    }
    
    public String getLoggedInUserID() {
        return logInPanel.getLoggedInUserID();
    }


    public static void main(String[] args) {
        new MainUI();
    }
    
    public WishListPanel getWishListPanel() {
        return wishListPanel;
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
                try {
                    String username = idInput.getText();
                    String password = new String(passwordInput.getPassword());
                    String confirmPassword = new String(passCheckInput.getPassword());

                    if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                        // 빈 필드 처리
                        JOptionPane.showMessageDialog(null, "회원가입에 실패했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
                        return;
                    } else if (!password.equals(confirmPassword)) {
                        // 비밀번호 불일치 처리
                        JOptionPane.showMessageDialog(null, "회원가입에 실패했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // 중복된 아이디 확인
                    if (databaseConnect.isUserExists(username)) {
                        JOptionPane.showMessageDialog(null, "이미 존재하는 아이디입니다.", "오류", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // 사용자 정보를 데이터베이스에 저장
                    if (databaseConnect.storeUserInDatabase(username, password)) {
                        // 등록 성공
                    	JOptionPane.showMessageDialog(null, "환영합니다. 회원가입에 성공했습니다.", "회원가입 성공", JOptionPane.PLAIN_MESSAGE);
                        mainUI.showStartPanel();
                    } else {
                        // 등록 실패
                        JOptionPane.showMessageDialog(null, "등록에 실패했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    // 예외 처리
                    ex.printStackTrace(); // 또는 다른 예외 처리 로직을 추가할 수 있음
                }
            }
        });




    }
}


class LogInPanel extends JPanel {
	private String loggedInUserID;
	
	
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
                String username = checkidInput.getText();
                String password = new String(checkpasswordInput.getPassword());

                try {
                    String loggedInUserID = null;

                    if (databaseConnect.isUserExists(username)) {
                        // 사용자가 존재하는 경우
                        loggedInUserID = databaseConnect.checkPassword(username, password);

                        if (loggedInUserID != null) {
                            // 비밀번호가 일치하는 경우
                        	loggedInUserID=username;
                        	//System.out.println(username+loggedInUserID);
                        	setLoggedInUserID(loggedInUserID);
                            JOptionPane.showMessageDialog(null, "로그인에 성공했습니다.", "로그인 성공", JOptionPane.PLAIN_MESSAGE);
                            mainUI.showImagePanel(); // ImagePanel로 전환
                        } else {
                            // 비밀번호가 일치하지 않는 경우
                            JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.", "로그인 실패", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        // 사용자가 존재하지 않는 경우
                        JOptionPane.showMessageDialog(null, "사용자가 존재하지 않습니다.", "로그인 실패", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    // 예외 처리
                    ex.printStackTrace(); // 또는 다른 예외 처리 로직을 추가할 수 있음
                }

            }
        });
    }
    private void setLoggedInUserID(String userID) {
    	System.out.println(userID);
        this.loggedInUserID = userID;
        System.out.println(userID+loggedInUserID);
    }

    public String getLoggedInUserID() {
        return loggedInUserID;
        
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
	
	private JLabel id;
	
    public WishListPanel(MainUI mainUI) {
        setLayout(null);
        
        //idLabel = new JLabel();

        id = new JLabel();
        RoundedButton SignUpConfirmation = new RoundedButton("확인");//나중에 삭제 할 코드
        JLabel logOut = new JLabel("로그아웃");
        JLabel wishList = new JLabel("찜 목록");
        
        id.setOpaque(true);
        id.setBackground(Color.decode("#D9D9D9"));
        
        logOut.setOpaque(true);
        logOut.setBackground(Color.decode("#D9D9D9"));
        logOut.setHorizontalAlignment(JLabel.CENTER);
       
        
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
        
        logOut.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // 마우스가 버튼 위에 올라갈 때의 동작
                logOut.setBackground(Color.decode("#EDEDED")); // 원하는 연한 색상으로 변경
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // 마우스가 버튼에서 벗어날 때의 동작
                logOut.setBackground(Color.decode("#D9D9D9")); // 버튼의 기본 배경색으로 변경
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // 마우스 클릭 시의 동작
                int response = JOptionPane.showConfirmDialog(
                        null,
                        "프로그램을 종료하시겠습니까?",
                        "종료 확인",
                        JOptionPane.YES_NO_OPTION
                );

                if (response == JOptionPane.YES_OPTION) {
                    // 예를 선택한 경우
                    System.exit(0); // 프로그램 종료
                } else {
                    // 아니오를 선택한 경우
                    mainUI.showLogInPanel(); // 로그인 화면으로 전환
                }
            }
        });

    }
    
    public void setLoggedInUserID(String loggedInUserID) {
        id.setText(loggedInUserID); //loggedInUserID 값을 JLabel에 설정
        id.setHorizontalAlignment(JLabel.CENTER);
    }
}
