package ajw.View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import ajw.Controller.Controller;

public class ManageGame extends JFrame {

    public ManageGame() {

        Controller.getInstance().clear();

        setTitle("선택");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel title = new JLabel("관리자 모드");
        title.setFont(new Font("휴먼편지체", Font.BOLD, 25));
        title.setBounds(112, 60, 400, 30);

        JButton createButton = new JButton("경기 등록");
        createButton.setBounds(112, 225, 150, 150);

        JButton deleteButton = new JButton("경기 관리");
        deleteButton.setBounds(375, 225, 150, 150);

        JButton logoutButton = new JButton("로그아웃");
        logoutButton.setBounds(638, 225, 150, 150);

        getContentPane().setLayout(null);

        getContentPane().add(title);
        getContentPane().add(createButton);
        getContentPane().add(deleteButton);
        getContentPane().add(logoutButton);

        setSize(900, 600);
        setVisible(true);

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateGame();
                dispose();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManageG();
                dispose();
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.getInstance().clearUser();
                new LoginScreen();
                dispose();
            }
        });
    }
}