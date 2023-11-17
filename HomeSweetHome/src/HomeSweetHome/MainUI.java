package HomeSweetHome;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainUI extends JFrame {
    public MainUI() {
        setTitle("Home Sweet Home");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(984, 662);

        JButton loginButton = new JButton("로그인");
        JButton signUpButton = new JButton("회원가입");
        
        JTextField Logo= new JTextField("Home Sweet Home"); //로고 이미지 넣을 예정

        
        loginButton.setBounds(361, 349, 260, 59);
        signUpButton.setBounds(361, 458, 260, 59);
        Logo.setBounds(43,139,898,130);

        add(loginButton);
        add(signUpButton);
        add(Logo);


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
            }
        });
        
        setLayout(null);

        setVisible(true);
    }

    public static void main(String[] args) {
        new MainUI();
    }
}
