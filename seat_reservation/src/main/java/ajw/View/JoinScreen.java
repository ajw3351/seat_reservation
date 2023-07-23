package ajw.View;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JTextField;

import ajw.Controller.Controller;
import ajw.Controller.EmailController;
import ajw.Controller.FileWrite;
import ajw.DAO.UserDAO;
import ajw.json.UserDataBase;
import ajw.json.UserInfo;

public class JoinScreen extends JFrame {

	// String choice = null;
	JButton join = new JButton("회원가입");
	JButton cancel = new JButton("취소");

	JTextField id = new JTextField(10);
	JPasswordField pwd = new JPasswordField(10);
	JTextField name = new JTextField(10);
	JTextField phone = new JTextField(10);
	JPasswordField pwdCheck = new JPasswordField(10);
	JTextField email = new JTextField(10);
	JTextField checkNum = new JTextField(10);

	UserInfo userInfo = new UserInfo();
	UserDataBase userDataBase = new UserDataBase();

	Controller controller = Controller.getInstance();

	ArrayList<UserInfo> mUserList = new ArrayList<>();
	boolean TF = true;

	private UserDAO userDao = UserDAO.getInstance();

	public JoinScreen() {

		setTitle("회원관리 화면");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel title = new JLabel("회원가입", JLabel.CENTER);

		title.setForeground(new Color(5, 0, 153));
		title.setFont(new Font("휴먼엑스포", Font.BOLD, 30));

		JPanel idPanel = new JPanel();
		idPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		idPanel.add(new JLabel("아이디 : "));
		idPanel.add(id);
		JButton IdConfirmButton = new JButton("중복확인");
		idPanel.add(IdConfirmButton);

		JPanel pwdPanel = new JPanel();
		pwdPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pwdPanel.add(new JLabel("비밀번호 : "));
		pwdPanel.add(pwd);
		pwdPanel.add(new JLabel("                            "));

		JPanel pwdCheckPanel = new JPanel();
		pwdCheckPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pwdCheckPanel.add(new JLabel("비밀번호 확인 : "));
		pwdCheckPanel.add(pwdCheck);
		pwdCheckPanel.add(new JLabel("                            "));

		JPanel namePanel = new JPanel();
		namePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		namePanel.add(new JLabel("이    름 : "));
		namePanel.add(name);
		namePanel.add(new JLabel("                            "));

		JPanel emailPanel = new JPanel();
		emailPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		emailPanel.add(new JLabel("이 메 일 : "));
		emailPanel.add(email);
		JButton emailSendButton = new JButton("인        증");
		emailPanel.add(emailSendButton);

		JPanel checkPanel = new JPanel();
		checkPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		checkPanel.add(new JLabel("인증번호 : "));
		checkPanel.add(checkNum);
		JButton confirmButton = new JButton("확        인");
		checkPanel.add(confirmButton);

		JPanel phonePanel = new JPanel();
		phonePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		phonePanel.add(new JLabel("연 락 처 : "));
		phonePanel.add(phone);
		phonePanel.add(new JLabel("                            "));

		JPanel formPanel = new JPanel();
		formPanel.setLayout(new GridLayout(7, 1));
		formPanel.add(idPanel);
		formPanel.add(pwdPanel);
		formPanel.add(pwdCheckPanel);
		formPanel.add(namePanel);
		formPanel.add(emailPanel);
		formPanel.add(checkPanel);
		formPanel.add(phonePanel);

		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new FlowLayout());

		contentPanel.add(formPanel);

		JPanel panel = new JPanel();
		panel.add(join);
		panel.add(cancel);

		join.setEnabled(false);
		emailSendButton.setEnabled(false);
		confirmButton.setEnabled(false);

		add(title, BorderLayout.NORTH);
		add(contentPanel, BorderLayout.CENTER);
		add(panel, BorderLayout.SOUTH);

		setBounds(200, 200, 400, 400);

		setVisible(true);

		join.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String myId = new String(id.getText());
				String myPwd = new String(pwd.getPassword());
				String myPwdCheck = new String(pwdCheck.getPassword());
				String myName = new String(name.getText());
				String myEmail = new String(email.getText());
				String myPhone = new String(phone.getText());

				if (myPwd.equals(myPwdCheck) && myPhone != null) {
					userDao.connect();

					userInfo.setId(myId);
					userInfo.setPwd(myPwd);
					userInfo.setName(myName);
					userInfo.setEmail(myEmail);
					userInfo.setPhoneNum(myPhone);

					controller.setUserInfo(userInfo);
					userDataBase = controller.addUserInfo();

					FileWrite FW = new FileWrite();
					userDao.register(userInfo);

					JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.");
					new LoginScreen();
					dispose();

				} else
					JOptionPane.showMessageDialog(null, "비밀번호를 다시 입력하세요");
			}
		});

		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new LoginScreen();
				dispose();

			}
		});

		emailSendButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				EmailController emailSenController = new EmailController();
				emailSenController.sendEmail(email.getText());
				confirmButton.setEnabled(true);

			}
		});

		confirmButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String Code = controller.getAuthenticationCode();
				if (checkNum.getText().equals(Code)) {
					JOptionPane.showMessageDialog(null, "확인되었습니다.");
					join.setEnabled(true);
				} else
					JOptionPane.showMessageDialog(null, "인증번호가 일치하지 않습니다.");

			}
		});

		IdConfirmButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mUserList = controller.getUserDataBase().getUserList();
				if (mUserList.size() == 0) {
					emailSendButton.setEnabled(true);
					JOptionPane.showMessageDialog(null, "사용 가능한 아이디입니다.");
				} else {
					for (int i = 0; i < mUserList.size(); i++) {
						if ((mUserList.get(i).getId()).equals(id.getText())) {
							JOptionPane.showMessageDialog(null, "중복되는 아이디가 있습니다.");
							TF = false;
							break;
						}
						TF = true;
					}
					if(TF){
						emailSendButton.setEnabled(true);
						JOptionPane.showMessageDialog(null, "사용 가능한 아이디입니다.");
					}
					
				}
			}
		});
	}
}