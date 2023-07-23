package ajw.View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import ajw.Controller.Controller;
import ajw.Controller.FileWrite;
import ajw.Controller.LoginSystem;
import ajw.json.UserDataBase;
import ajw.json.UserInfo;

public class ChangePwd extends JFrame {
    JPasswordField pwdText = new JPasswordField(10);
    JPasswordField pwdCheckText = new JPasswordField(10);

    ArrayList<UserInfo> mUserList = new ArrayList<>();
    UserDataBase mUserDataBase;

    Controller controller = Controller.getInstance();

    public ChangePwd() {
        setTitle("예매");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JPanel title = new JPanel();

        JLabel findId = new JLabel("새 비밀번호 설정");
        findId.setFont(new Font("맑은 고딕", Font.BOLD, 25));

        title.add(findId);

        JPanel jp1 = new JPanel();
        jp1.setLayout(new GridLayout(2, 2));

        JPanel pwdPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel pwdLabel = new JLabel("새 비밀번호 : ", JLabel.CENTER);

        pwdPanel.add(pwdLabel);

        JPanel pwdTextPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        pwdTextPanel.add(pwdText);

        jp1.add(pwdPanel);
        jp1.add(pwdTextPanel);

        JPanel pwdCheckPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel pwdCheckLabel = new JLabel("새 비밀번호 확인 : ", JLabel.CENTER);

        pwdCheckPanel.add(pwdCheckLabel);

        JPanel pwdCheckTPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        pwdCheckTPanel.add(pwdCheckText);

        jp1.add(pwdCheckPanel);
        jp1.add(pwdCheckTPanel);

        JPanel nextPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton nextButton = new JButton("확인");

        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton backButton = new JButton("이전");

        nextPanel.add(nextButton);
        backPanel.add(backButton);

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(nextPanel);
        buttonPanel.add(backPanel);

        JPanel jp2 = new JPanel();
        jp2.setLayout(new FlowLayout());
        jp2.add(jp1);

        setLayout(new BorderLayout());

        add(title, BorderLayout.NORTH);
        add(jp2, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setBounds(200, 200, 350, 250);

        setResizable(false);
        setVisible(true);

        mUserDataBase = controller.getUserDataBase();

        nextButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                mUserList = mUserDataBase.getUserList();
                String newPassword = new String(pwdText.getPassword());
                String pwdCheck = new String(pwdCheckText.getPassword());
                if ((newPassword).equals(pwdCheck)) {
                    for (int i = 0; i < mUserList.size(); i++) {
                        if ((mUserList.get(i).getId()).equals(LoginSystem.getInstance().getID())) {
                            var user = mUserList.get(i);

                            user.setPwd(newPassword);

                            mUserList.set(i, user);
                            break;
                        }
                    }
                    mUserDataBase.setUserList(mUserList);
                    FileWrite FW = new FileWrite();
                    try {
                        FW.FileWrite(mUserDataBase);
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(null, "비밀번호가 변경되었습니다.");

                    new LoginScreen();
                    dispose();

                } else
                    JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.");
            }
        });

        backButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new FindPwd();
                dispose();

            }
        });

    }
}
