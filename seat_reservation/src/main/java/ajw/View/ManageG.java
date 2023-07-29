package ajw.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ajw.Controller.Controller;
import ajw.DAO.AreaDAO;
import ajw.DAO.GameDAO;
import ajw.VO.GameVO;

public class ManageG extends JFrame {

    ImageIcon updateimage;
    ImageIcon updateimage2;

    JLabel[] imgLabel = new JLabel[3];
    JLabel[] imgLabel2 = new JLabel[3];
    JPanel[] info = new JPanel[3];
    JPanel[] teamImgPanel = new JPanel[3];
    JButton[] delete = new JButton[3];
    ArrayList<JLabel> mDateLabels = new ArrayList<>();
    ArrayList<JLabel> mTimeLabels = new ArrayList<>();
    ArrayList<JLabel> mGameLabels = new ArrayList<>();
    ArrayList<JLabel> mSeatLabels = new ArrayList<>();
    int capacity;
    int reserved;

    JPanel[] box = new JPanel[3];
    int i;

    ArrayList<GameVO> gameInfo;

    private GameDAO gameDao = GameDAO.getInstance();
    private AreaDAO areaDao = AreaDAO.getInstance();

    public ManageG() {
        setTitle("관리자모드");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container main = getContentPane();
        main.setLayout(new BorderLayout(50, 20));

        gameInfo = gameDao.getGameInfo();

        JPanel p1 = new JPanel(new GridLayout(3, 1, 10, 20));
        JPanel p2 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 20));
        JPanel p3 = new JPanel(new FlowLayout(FlowLayout.LEFT, 100, 20));

        JButton backButton = new JButton("이전");

        p2.add(backButton);
        p3.add(new JLabel("경기 관리"));

        // 여백 패널
        JPanel em1 = new JPanel();
        em1.add(new JLabel(" "));
        JPanel em2 = new JPanel();
        em2.add(new JLabel(" "));
        JPanel em3 = new JPanel();
        em3.add(new JLabel(" "));
        JPanel em4 = new JPanel();
        em4.add(new JLabel(" "));

        // 경기 박스
        makeBox();
        for (i = 0; i < 3; i++)
            p1.add(box[i]);

        main.add(p1, BorderLayout.CENTER);
        main.add(em1, BorderLayout.WEST);
        main.add(em2, BorderLayout.EAST);
        main.add(p3, BorderLayout.NORTH);
        main.add(p2, BorderLayout.SOUTH);

        setSize(900, 600);
        setVisible(true);
        setResizable(false);

        delete[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    gameDao.DeleteGame(0);
                    JOptionPane.showMessageDialog(null, "삭제되었습니다.");
                } catch (Exception e1) {
                    System.out.println(e1);
                    JOptionPane.showMessageDialog(null, "에러 발생");
                }
            }
        });
        delete[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    gameDao.DeleteGame(1);
                    JOptionPane.showMessageDialog(null, "삭제되었습니다.");
                } catch (Exception e1) {
                    System.out.println(e1);
                    JOptionPane.showMessageDialog(null, "에러 발생");
                }
            }
        });

        delete[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    gameDao.DeleteGame(2);
                    JOptionPane.showMessageDialog(null, "삭제되었습니다.");
                } catch (Exception e1) {
                    System.out.println(e1);
                    JOptionPane.showMessageDialog(null, "에러 발생");
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManageGame();
                dispose();
            }
        });
    }

    private void makeBox() {
        for (i = 0; i < 3; i++) {
            capacity = areaDao.getCapacity();
            //팔린 좌석 수 
            reserved = 0;
            sizeChanged(i);
            imgLabel[i] = new JLabel();
            imgLabel[i].setIcon(updateimage);
            imgLabel[i].setHorizontalAlignment(JLabel.CENTER);
            imgLabel2[i] = new JLabel();
            imgLabel2[i].setIcon(updateimage2);
            imgLabel2[i].setHorizontalAlignment(JLabel.CENTER);
            info[i] = new JPanel();
            box[i] = new JPanel();
            delete[i] = new JButton();
            teamImgPanel[i] = new JPanel();

            mGameLabels.add(new JLabel(gameInfo.get(i).getTitle()));
            mDateLabels.add(new JLabel(gameInfo.get(i).getDate()));
            mTimeLabels.add(new JLabel(gameInfo.get(i).getTime()));
            mSeatLabels.add(new JLabel(reserved + " / " + capacity));

            info[i].setLayout(new GridLayout(4, 1));
            info[i].add(mGameLabels.get(i));
            info[i].add(mDateLabels.get(i));
            info[i].add(mTimeLabels.get(i));
            info[i].add(mSeatLabels.get(i));
            info[i].setBackground(Color.WHITE);

            delete[i].setText("예매하기");

            teamImgPanel[i].setLayout(new GridLayout(1, 3));
            teamImgPanel[i].add(imgLabel[i]);
            teamImgPanel[i].add(new JLabel("                  VS"));
            teamImgPanel[i].add(imgLabel2[i]);
            teamImgPanel[i].setBackground(Color.WHITE);

            box[i].setLayout(new BorderLayout(20, 10));
            box[i].add(info[i], BorderLayout.CENTER);
            box[i].add(teamImgPanel[i], BorderLayout.WEST);
            box[i].add(delete[i], BorderLayout.EAST);
            box[i].setBackground(Color.WHITE);
        }
    }

    private void sizeChanged(int index) {
        String root = System.getProperty("user.dir");
        String filePath1, filePath2;
        String home = gameInfo.get(index).getHomeImg();
        String away = gameInfo.get(index).getAwayImg();
        filePath1 = root + home;
        filePath2 = root + away;
        Image img = (new ImageIcon(filePath1)).getImage();
        Image updateImg = img.getScaledInstance(140, 140, Image.SCALE_SMOOTH);
        Image img2 = (new ImageIcon(filePath2)).getImage();
        Image updateImg2 = img2.getScaledInstance(140, 140, Image.SCALE_SMOOTH);
        updateimage = new ImageIcon(updateImg);
        updateimage2 = new ImageIcon(updateImg2);
    }
}
