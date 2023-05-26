package ajw.View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.google.gson.Gson;

import ajw.Controller.Controller;

import ajw.json.User;

public class LoginScreen extends JFrame {
    JTextField jtf1 = new JTextField(10);
    JTextField jtf2 = new JTextField(10);

    public LoginScreen() {
        setTitle("시스템");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel title = new JPanel();

        JLabel login = new JLabel("로그인");
        login.setFont(new Font("휴먼편지체", Font.BOLD, 25));

        title.add(login);

        JPanel jp1 = new JPanel();
        jp1.setLayout(new GridLayout(3, 2));

        JPanel idPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlb1 = new JLabel("아이디 : ", JLabel.CENTER);

        idPanel.add(jlb1);

        JPanel idPanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));

        idPanel2.add(jtf1);

        jp1.add(idPanel);
        jp1.add(idPanel2);

        JPanel pwdPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlb2 = new JLabel("비밀번호 : ", JLabel.CENTER);

        pwdPanel.add(jlb2);

        JPanel pwdPanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));

        pwdPanel2.add(jtf2);

        jp1.add(pwdPanel);
        jp1.add(pwdPanel2);

        JPanel loginPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton jLogin = new JButton("로그인");

        JPanel joinPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton join = new JButton("회원가입");

        loginPanel.add(jLogin);
        joinPanel.add(join);

        jp1.add(loginPanel);
        jp1.add(joinPanel);

        JPanel jp2 = new JPanel();
        jp2.setLayout(new FlowLayout());
        jp2.add(jp1);

        setLayout(new BorderLayout());

        add(title, BorderLayout.NORTH);
        add(jp2, BorderLayout.CENTER);

        setBounds(200, 200, 300, 250);

        setResizable(false);
        setVisible(true);

        jLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String myId = jtf1.getText();

                var user = Controller.getInstance().getUser();
                Controller.getInstance().setUserName(myId);

                try {
                    ReadFile(user);
                } catch (FileNotFoundException exception) {
                    exception.printStackTrace();
                }

                String mypwd = new String(jtf2.getText());

                // JOptionPane.showMessageDialog(null,"아이디 : "+myId+", 비밀번호"+mypwd);

                new Home();
                dispose();
            }
        });

        join.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                new JoinScreen();
                dispose();

            }
        });
    }

    private void ReadFile(User user) throws FileNotFoundException {

        String root = System.getProperty("user.dir");
        String filePath;
        filePath = root + "/seat_reservation/src/main/java/Info/" + user.getUserName() + ".json";
        Reader reader = new FileReader(filePath);

        Gson gson = new Gson();
        user = gson.fromJson(reader, User.class);
    }
}
