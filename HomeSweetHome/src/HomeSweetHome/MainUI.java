package HomeSweetHome;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainUI extends JFrame {
	private JPanel startPanel;

	
    public MainUI() {
        setTitle("Home Sweet Home");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(984, 662);
        
        startPanel = new JPanel();
        startPanel.setLayout(null);

        JButton loginButton = new JButton("로그인");
        JButton signUpButton = new JButton("회원가입");
        
        JTextField logo= new JTextField("Home Sweet Home"); //로고 이미지 넣을 예정

        
        loginButton.setBounds(361, 349, 260, 59);
        signUpButton.setBounds(361, 458, 260, 59);
        logo.setBounds(43,139,898,130);

        startPanel.add(loginButton);
        startPanel.add(signUpButton);
        startPanel.add(logo);


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 로그인 버튼을 눌렀을 때 수행할 로직을 여기에 추가
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 회원가입 버튼을 눌렀을 때 수행할 로직을 여기에 추가
            	new SignUpPage();
            }
        });
        
        add(startPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainUI();
    }
}
