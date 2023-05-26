package ajw.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

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
		idPanel.add(new JButton("중복확인"));

		JPanel pwdPanel = new JPanel();
		pwdPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pwdPanel.add(new JLabel("비밀번호 : "));
		pwdPanel.add(pwd);
		pwdPanel.add(new JLabel("       "));

		JPanel pwdCheckPanel = new JPanel();
		pwdCheckPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pwdCheckPanel.add(new JLabel("비밀번호 확인 : "));
		pwdCheckPanel.add(pwdCheck);
		pwdCheckPanel.add(new JLabel("       "));

		JPanel namePanel = new JPanel();
		namePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		namePanel.add(new JLabel("이    름 : "));
		namePanel.add(name);
		namePanel.add(new JLabel("       "));

		JPanel emailPanel = new JPanel();
		emailPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		emailPanel.add(new JLabel("이 메 일 : "));
		emailPanel.add(email);
		emailPanel.add(new JButton("인   증"));

		JPanel checkPanel = new JPanel();
		checkPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		checkPanel.add(new JLabel("인증번호 : "));
		checkPanel.add(checkNum);
		checkPanel.add(new JButton("확   인"));

		JPanel phonePanel = new JPanel();
		phonePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		phonePanel.add(new JLabel("연 락 처 : "));
		phonePanel.add(phone);
		phonePanel.add(new JLabel("       "));

		JPanel formPanel = new JPanel();
		formPanel.setLayout(new GridLayout(7, 1));
		formPanel.add(idPanel);
		formPanel.add(pwdPanel);
		formPanel.add(pwdCheckPanel);
		formPanel.add(namePanel);
		formPanel.add(emailPanel);
		formPanel.add(checkPanel);
		formPanel.add(phonePanel);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(null);
		buttonPanel.setBackground(Color.BLACK);

		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new FlowLayout());

		contentPanel.add(formPanel);

		JPanel panel = new JPanel();
		panel.add(join);
		panel.add(cancel);

		add(title, BorderLayout.NORTH);
		add(contentPanel, BorderLayout.CENTER);
		add(panel, BorderLayout.SOUTH);
		add(buttonPanel, BorderLayout.EAST);

		setBounds(200, 200, 400, 400);

		setVisible(true);

		join.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String myId = id.getText();
				String myPwd = new String(pwd.getPassword());
				String myPwdCheck = new String(pwdCheck.getPassword());
				String myName = name.getText();
				String myPhone = phone.getText();

				if (myPwd.equals(myPwdCheck)) {
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
	}
}