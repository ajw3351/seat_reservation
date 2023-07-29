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
import ajw.DAO.GameDAO;
import ajw.DAO.UserDAO;
import ajw.VO.GameVO;
import ajw.json.UserDataBase;
import ajw.json.UserInfo;

public class CreateGame extends JFrame {

    // String choice = null;
    JButton create = new JButton("등록");
    JButton cancel = new JButton("취소");

    JTextField titleText = new JTextField(10);
    JTextField date = new JTextField(10);
    JTextField time = new JTextField(10);
    JTextField homeImg = new JTextField(10);
    JTextField awayImg = new JTextField(10);

    UserInfo userInfo = new UserInfo();
    UserDataBase userDataBase = new UserDataBase();

    Controller controller = Controller.getInstance();

    ArrayList<UserInfo> mUserList = new ArrayList<>();
    boolean TF = true;

    private GameDAO gameDao = GameDAO.getInstance();

    public CreateGame() {

        setTitle("경기 등록");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel title = new JLabel("경기 등록", JLabel.CENTER);

        title.setForeground(new Color(5, 0, 153));
        title.setFont(new Font("휴먼엑스포", Font.BOLD, 30));

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        titlePanel.add(new JLabel("경    기 : "));
        titlePanel.add(titleText);

        JPanel datePanel = new JPanel();
        datePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        datePanel.add(new JLabel("날    짜 : "));
        datePanel.add(date);

        JPanel timePanel = new JPanel();
        timePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        timePanel.add(new JLabel("시    간 : "));
        timePanel.add(time);

        JPanel homeImgPanel = new JPanel();
        homeImgPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        homeImgPanel.add(new JLabel("홈    팀 : "));
        homeImgPanel.add(homeImg);

        JPanel awayPanel = new JPanel();
        awayPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        awayPanel.add(new JLabel("원 정 팀 : "));
        awayPanel.add(awayImg);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(5, 1));
        formPanel.add(titlePanel);
        formPanel.add(datePanel);
        formPanel.add(timePanel);
        formPanel.add(homeImgPanel);
        formPanel.add(awayPanel);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new FlowLayout());

        contentPanel.add(formPanel);

        JPanel panel = new JPanel();
        panel.add(create);
        panel.add(cancel);

        create.setEnabled(true);

        add(title, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        setBounds(200, 200, 400, 400);

        setVisible(true);

        create.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String newTitle = new String(titleText.getText());
                String newDate = new String(date.getText());
                String newTime = new String(time.getText());
                String newHomeImg = new String(homeImg.getText());
                String newAwayImg = new String(awayImg.getText());
                GameVO newGame = new GameVO();
                newGame.setTitle(titleText.getText());
                newGame.setDate(date.getText());
                newGame.setTime(time.getText());
                newGame.setHomeImg(homeImg.getText());
                newGame.setAwayImg(awayImg.getText());


                if (newTitle.equals("") || newDate.equals("") || newTime.equals("") || newHomeImg.equals("") || newAwayImg.equals("")) {
                    JOptionPane.showMessageDialog(null, "입력이 필요합니다.");
                } else if(newHomeImg != "LG" && newHomeImg != "한화" && newHomeImg != "두산" && newHomeImg != "키움" && newAwayImg != "한화" && newAwayImg != "두산" && newAwayImg != "키움" && newAwayImg != "LG") {
                    JOptionPane.showMessageDialog(null, "존재하지 않는 팀입니다.");
                } else{
                    //데이터베이스에 저장 gameid는 제일 마지막으로
                    gameDao.register(newGame);
                    JOptionPane.showMessageDialog(null, "등록이 완료되었습니다.");
                }   
            }
        });

        cancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                new ManageGame();
                dispose();

            }
        });
    }
}