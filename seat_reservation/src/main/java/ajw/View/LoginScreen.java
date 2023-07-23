package ajw.View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ajw.Controller.Controller;
import ajw.Controller.FileRead;
import ajw.DAO.TicketDAO;
import ajw.DAO.UserDAO;
import ajw.json.UserDataBase;
import ajw.json.UserInfo;

public class LoginScreen extends JFrame {
    JTextField jtf1 = new JTextField(10);
    JPasswordField jtf2 = new JPasswordField(10);
    String userId;
    ArrayList<UserInfo> mUserList = new ArrayList<>();
    UserDataBase mUserDataBase;
    String userPwd;
    private UserDAO userDao = UserDAO.getInstance();
    private TicketDAO ticketDao = TicketDAO.getInstance();

    public LoginScreen() {
        setTitle("시스템");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel title = new JPanel();

        JLabel login = new JLabel("로그인");
        login.setFont(new Font("휴먼편지체", Font.BOLD, 25));

        title.add(login);

        JPanel jp1 = new JPanel();
        jp1.setLayout(new GridLayout(4, 2));

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

        JPanel findIdPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton findIdButton = new JButton("아이디 찾기");

        JPanel findPwdPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton findPwd = new JButton("비밀번호 찾기");

        loginPanel.add(jLogin);
        joinPanel.add(join);

        findIdPanel.add(findIdButton);
        findPwdPanel.add(findPwd);

        jp1.add(loginPanel);
        jp1.add(joinPanel);
        jp1.add(findIdPanel);
        jp1.add(findPwdPanel);

        JPanel jp2 = new JPanel();
        jp2.setLayout(new FlowLayout());
        jp2.add(jp1);

        setLayout(new BorderLayout());

        add(title, BorderLayout.NORTH);
        add(jp2, BorderLayout.CENTER);

        setBounds(200, 200, 300, 250);

        setResizable(false);
        setVisible(true);

        Controller controller = Controller.getInstance();
        mUserDataBase = controller.getUserDataBase();
        FileRead FR = new FileRead();

        try {
            mUserDataBase = FR.ReadUserFile(mUserDataBase);
            controller.setUserDataBase(mUserDataBase);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }

        jLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                userDao.connect();
                String myId = jtf1.getText();
                String mypwd = new String(jtf2.getPassword());

                int login = userDao.login(myId, mypwd);

                if(login == -1){
                    JOptionPane.showMessageDialog(null, "일치하지 않은 비밀번호입니다.");
                }else if(login == -2){
                    JOptionPane.showMessageDialog(null, "등록되지 않은 사용자입니다.");
                }else if(login == -3){
                    JOptionPane.showMessageDialog(null, "서버 오류");
                }else{
                    ticketDao.connect();
                    Controller.getInstance().setmUserId(myId);
                    ticketDao.getTicket(Controller.getInstance().getUser(), myId);
                    new Home();
                    dispose();
                }


                // 파일 입출력
                // mUserList = mUserDataBase.getUserList();

                // for (int i = 0; i < mUserList.size(); i++) {
                //     if ((mUserList.get(i).getId()).equals(myId)) {
                //         userId = mUserList.get(i).getId();
                //         controller.setUserName(mUserList.get(i).getName());
                //         controller.setmUserId(mUserList.get(i).getId());
                //         // 위의 2줄 바꿔서 가능
                //         controller.setUserInfo(mUserList.get(i));
                //         userPwd = mUserList.get(i).getPwd();
                //         break;
                //     }
                // }
                // if (userId == null)
                //     JOptionPane.showMessageDialog(null, "등록되지 않은 사용자입니다.");

                // else if (mypwd.equals(userPwd)) {

                //     var user = controller.getUser();

                //     try {
                //         user = FR.ReadFile(userId);
                //         Controller.getInstance().setUser(1);
                //     } catch (FileNotFoundException exception) {
                //         exception.printStackTrace();
                //     }
                //     new Home();
                //     dispose();
                // } else {

                //     JOptionPane.showMessageDialog(null, "일치하지 않은 비밀번호입니다.");
                // }

            }
        });

        join.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                new JoinScreen();
                dispose();

            }
        });

        findIdButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new FindId();
                dispose();

            }
        });

        findPwd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new FindPwd();
                dispose();

            }
        });
    }

}
