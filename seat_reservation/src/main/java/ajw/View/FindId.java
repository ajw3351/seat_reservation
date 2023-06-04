package ajw.View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ajw.Controller.Controller;
import ajw.Controller.EmailController;
import ajw.json.UserDataBase;
import ajw.json.UserInfo;

public class FindId extends JFrame {
    JTextField jtf1 = new JTextField(10);
    JTextField email = new JTextField(10);
    JTextField codeText = new JTextField(10);

    String ID;
    ArrayList<UserInfo> mUserList = new ArrayList<>();
    UserDataBase mUserDataBase;

    Controller controller = Controller.getInstance();

    public FindId() {
        setTitle("예매");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JPanel title = new JPanel();

        JLabel findId = new JLabel("아이디 찾기");
        findId.setFont(new Font("맑은 고딕", Font.BOLD, 25));

        title.add(findId);

        JPanel jp1 = new JPanel();
        jp1.setLayout(new GridLayout(3, 3));

        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel nameLabel = new JLabel("이    름 : ", JLabel.CENTER);

        namePanel.add(nameLabel);

        JPanel nameTextPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        nameTextPanel.add(jtf1);

        JPanel emptyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel emptyLabel = new JLabel("     ");

        emptyPanel.add(emptyLabel);

        jp1.add(namePanel);
        jp1.add(nameTextPanel);
        jp1.add(emptyPanel);

        JPanel pwdPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlb2 = new JLabel("이메일 : ", JLabel.CENTER);

        pwdPanel.add(jlb2);

        JPanel pwdPanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));

        pwdPanel2.add(email);

        JPanel sendPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton sendButton = new JButton("보내기");

        sendPanel.add(sendButton);

        jp1.add(pwdPanel);
        jp1.add(pwdPanel2);
        jp1.add(sendPanel);

        JPanel codePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel codeLabel = new JLabel("인증번호 : ", JLabel.CENTER);

        codePanel.add(codeLabel);

        JPanel codeTextPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        codeTextPanel.add(codeText);

        JPanel emptyPanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel emptyLabel2 = new JLabel("     ");

        emptyPanel2.add(emptyLabel2);

        jp1.add(codePanel);
        jp1.add(codeTextPanel);
        jp1.add(emptyPanel2);

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
                String Code = controller.getAuthenticationCode();
                if (codeText.getText().equals(Code)) {
                    mUserList = mUserDataBase.getUserList();

                    for (int i = 0; i < mUserList.size(); i++) {
                        if ((mUserList.get(i).getName()).equals(jtf1.getText())
                                && (mUserList.get(i).getEmail()).equals(email.getText())) {
                            ID = mUserList.get(i).getId();
                            break;
                        }
                    }

                    if (ID == null)
                        JOptionPane.showMessageDialog(null, "등록된 아이디가 없습니다.");
                    else
                        JOptionPane.showMessageDialog(null, "아이디 : " + ID);

                    new LoginScreen();
                    dispose();

                } else
                    JOptionPane.showMessageDialog(null, "인증번호가 일치하지 않습니다.");

            }
        });

        backButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginScreen();
                dispose();

            }
        });

        sendButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                EmailController emailSenController = new EmailController();
                emailSenController.sendEmail(email.getText());
            }
        });
    }
}
