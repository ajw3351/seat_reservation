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
import javax.swing.JPanel;

import ajw.Controller.Controller;

public class Reservation extends JFrame {

    ImageIcon updateimage;
    ImageIcon updateimage2;

    JLabel[] imgLabel = new JLabel[3];
    JLabel[] imgLabel2 = new JLabel[3];
    JPanel[] info = new JPanel[3];
    JPanel[] teamImgPanel = new JPanel[3];
    JButton[] buy = new JButton[3];
    ArrayList<JLabel> mDateLabels = new ArrayList<>();
    ArrayList<JLabel> mTimeLabels = new ArrayList<>();
    ArrayList<JLabel> mGameLabels = new ArrayList<>();

    JPanel[] box = new JPanel[3];
    int i;

    private GameInfo model = new GameInfo();

    private String mGame;
    private String mTime;
    private ArrayList<String> mDate = new ArrayList<>();

    public Reservation() {
        setTitle("예매");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container main = getContentPane();
        main.setLayout(new BorderLayout(50, 20));

        JPanel p1 = new JPanel(new GridLayout(3, 1, 10, 20));
        JPanel p2 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 20));
        JPanel p3 = new JPanel(new FlowLayout(FlowLayout.LEFT, 100, 20));

        JButton backButton = new JButton("이전");

        p2.add(backButton);
        p3.add(new JLabel("예매"));

        // 여백 패널
        JPanel em1 = new JPanel();
        em1.add(new JLabel(" "));
        JPanel em2 = new JPanel();
        em2.add(new JLabel(" "));
        JPanel em3 = new JPanel();
        em3.add(new JLabel(" "));
        JPanel em4 = new JPanel();
        em4.add(new JLabel(" "));

        // gameInfo에서 가져온 정보 변수에 저장
        mGame = this.model.GameTeam();
        mTime = this.model.GameTime();
        mDate = this.model.GameDate();

        // 이미지 사이즈 변경
        sizeChanged();

        // 예매 박스
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

        buy[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.getInstance().setGame(mGame, mDate.get(0) + mTime);
                new SelectSeatArea();
                dispose();
            }
        });
        buy[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.getInstance().setGame(mGame, mDate.get(1) + mTime);
                new SelectSeatArea();
                dispose();
            }
        });

        buy[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.getInstance().setGame(mGame, mDate.get(2) + mTime);
                new SelectSeatArea();
                dispose();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Home();
                dispose();
            }
        });
    }

    private void makeBox() {
        for (i = 0; i < 3; i++) {
            imgLabel[i] = new JLabel();
            imgLabel[i].setIcon(updateimage);
            imgLabel[i].setHorizontalAlignment(JLabel.CENTER);
            imgLabel2[i] = new JLabel();
            imgLabel2[i].setIcon(updateimage2);
            imgLabel2[i].setHorizontalAlignment(JLabel.CENTER);
            info[i] = new JPanel();
            box[i] = new JPanel();
            buy[i] = new JButton();
            teamImgPanel[i] = new JPanel();

            mGameLabels.add(new JLabel(mGame));
            mDateLabels.add(new JLabel(mDate.get(i)));
            mTimeLabels.add(new JLabel(mTime));

            info[i].setLayout(new GridLayout(3, 1));
            info[i].add(mGameLabels.get(i));
            info[i].add(mDateLabels.get(i));
            info[i].add(mTimeLabels.get(i));
            info[i].setBackground(Color.WHITE);

            buy[i].setText("예매하기");

            teamImgPanel[i].setLayout(new GridLayout(1,3));
            teamImgPanel[i].add(imgLabel[i]);
            teamImgPanel[i].add(new JLabel("                  VS"));
            teamImgPanel[i].add(imgLabel2[i]);
            teamImgPanel[i].setBackground(Color.WHITE);
            
            box[i].setLayout(new BorderLayout(20, 10));
            box[i].add(info[i], BorderLayout.CENTER);
            box[i].add(teamImgPanel[i], BorderLayout.WEST);
            box[i].add(buy[i], BorderLayout.EAST);
            box[i].setBackground(Color.WHITE);
        }
    }

    private void sizeChanged() {

        Image img = model.GameTeamImage().get(0).getImage();
        Image updateImg = img.getScaledInstance(140, 140, Image.SCALE_SMOOTH);
        Image img2 = model.GameTeamImage().get(1).getImage();
        Image updateImg2 = img2.getScaledInstance(140, 140, Image.SCALE_SMOOTH);
        updateimage = new ImageIcon(updateImg);
        updateimage2 = new ImageIcon(updateImg2);
    }
}
