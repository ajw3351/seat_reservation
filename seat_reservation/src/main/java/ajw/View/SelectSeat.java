package ajw.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ajw.Controller.Controller;
import ajw.Controller.SeatControll;
import ajw.json.User;

public class SelectSeat extends JFrame {
    JLabel imgLabel = new JLabel();
    SeatControll seatControll = new SeatControll();

    JScrollPane mScrollSeatPane;

    int mSeatCheck = 0;

    User mUser;

    // 좌석리스너
    JCheckBox[][] seats = new JCheckBox[100][100];
    ArrayList<String> seatsNumber = new ArrayList<String>(); // 여기에 check한 좌석이 저장된다.
    ArrayList<JLabel> seatDetails = new ArrayList<>();

    // 예매된 좌석
    ArrayList<String> reserveSeats = new ArrayList<>();

    public SelectSeat() {
        setTitle("예매");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JPanel seatPanel = new JPanel();
        JPanel seatInfoPanel = new JPanel(new GridLayout(5, 1));
        JPanel seatAreaPanel = new JPanel(new GridLayout(2, 1));
        JPanel gameInfoPanel = new JPanel();
        JPanel buttonPanel = new JPanel(new BorderLayout());

        seatAreaPanel.setBackground(Color.WHITE);
        gameInfoPanel.setBackground(Color.GREEN);
        seatInfoPanel.setBackground(Color.WHITE);
        seatPanel.setBackground(Color.YELLOW);

        seatPanel.setLayout(new GridLayout(1, 1));

        seatAreaPanel.setBounds(720, 70, 270, 70);
        gameInfoPanel.setBounds(10, 10, 980, 50);
        seatInfoPanel.setBounds(720, 150, 270, 350);
        buttonPanel.setBounds(720, 510, 270, 60);

        getContentPane().add(gameInfoPanel);
        getContentPane().add(seatInfoPanel);
        getContentPane().add(seatAreaPanel);
        getContentPane().add(buttonPanel);

        JPanel buttonPanel2 = new JPanel(new GridLayout(1, 2));// reset, back 버튼 담을 패널
        JButton reset = new JButton("좌석 다시 선택");
        JButton back = new JButton("이전");
        JButton next = new JButton("좌석 선택 완료");

        buttonPanel2.add(back);
        buttonPanel2.add(reset);
        buttonPanel.add(buttonPanel2, BorderLayout.SOUTH);
        buttonPanel.add(next, BorderLayout.CENTER);

        // 경기정보
        var controller = Controller.getInstance();
        var gameInfo = controller.getGame();
        JLabel gameInfoLabel = new JLabel(gameInfo[0] + gameInfo[1]);
        gameInfoPanel.add(gameInfoLabel);

        // 좌석 구역, 가격 라벨
        JLabel seatArea = new JLabel();
        seatArea.setText(controller.getArea());
        seatArea.setFont(new Font("휴먼엑스포", Font.PLAIN, 20));

        JLabel price = new JLabel();
        price.setText(controller.getPrice() + "원");
        price.setFont(new Font("휴먼엑스포", Font.PLAIN, 20));

        seatAreaPanel.add(seatArea);
        seatAreaPanel.add(price);

        // seatInfoPanel에 1인 최대 가능 예매 수
        JLabel maxseat = new JLabel("1인 최대 4매");
        maxseat.setFont(new Font("휴먼엑스포", Font.PLAIN, 12));
        seatInfoPanel.add(maxseat);

        // 좌석에 리스너 달기.
        for (int i = 0; i < 4; i++) {
            JLabel seatInfoLabel = new JLabel("");
            seatInfoLabel.setFont(new Font("휴먼엑스포", Font.PLAIN, 17));
            seatDetails.add(seatInfoLabel);
            seatInfoPanel.add(seatInfoLabel);
        }

        class SeatChangeListener implements ItemListener {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) {
                    if (mSeatCheck < 4) {
                        JCheckBox tempCheckbox = (JCheckBox) e.getSource();
                        String test = tempCheckbox.getText();
                        String[] testArr = test.split(",", 0);

                        String row = testArr[0];
                        String col = testArr[1];

                        seatsNumber.add(row + "열 " + col); // 좌석 번호 vector에 넣기.
                        seatsNumber.set(mSeatCheck, row + "열 " + col);
                        seatDetails.get(mSeatCheck).setText(row + "열 " + col);
                    }
                    mSeatCheck++;
                    if (mSeatCheck == 5)
                        JOptionPane.showMessageDialog(null, "좌석은 4매까지 예매가능합니다.");
                } else {
                    // e.getItem()
                    JCheckBox tempCheckbox = (JCheckBox) e.getSource();
                    String test = tempCheckbox.getText();
                    String[] testArr = test.split(",", 0);

                    String row = testArr[0];
                    String col = testArr[1];

                    for (int i = 0; i < 4; i++) {
                        String temp = seatDetails.get(i).getText();
                        if (temp.equals(row + "열 " + col)) {
                            for (int j = i; j < mSeatCheck - 1; j++) {
                                seatsNumber.set(j, seatsNumber.get(j + 1));
                                seatDetails.get(j).setText(seatsNumber.get(j));
                            }
                            seatsNumber.set(mSeatCheck - 1, "");
                            seatDetails.get(mSeatCheck - 1).setText(seatsNumber.get(mSeatCheck - 1));
                        }
                    }
                    if (mSeatCheck >= 0)
                        mSeatCheck--;
                }

            }
        }

        // 구역에 따라 체크박스를 다르게 출력하는 부분
        int cnt = 1;

        var area = Controller.getInstance().getArea();

        switch (area) {
            case "테라존(VIP석)":
                // JCheckBox[][] seats = new JCheckBox[6][44];
                JPanel teraZone = new JPanel();

                teraZone.setLayout(null);
                teraZone.setBackground(Color.GRAY);

                SeatChangeListener listener = new SeatChangeListener();

                // 열
                for (int i = 0; i < 44; i++) {
                    JLabel seatColLabel = new JLabel();
                    if (i != 13 && i != 24 && i != 34) {
                        String input = Integer.toString(cnt++);
                        seatColLabel.setText(input);
                        seatColLabel.setFont(new Font("휴먼엑스포", Font.PLAIN, 13));
                        seatColLabel.setBounds(40 + (i * 20), 310, 20, 20);
                        teraZone.add(seatColLabel);
                    }

                }

                // 행
                for (int i = 0; i < 6; i++) {
                    JLabel seatRowLabel = new JLabel();
                    char input = (char) ('A' + i);
                    seatRowLabel.setText(Character.toString(input));
                    seatRowLabel.setFont(new Font("휴먼엑스포", Font.PLAIN, 13));
                    seatRowLabel.setBounds(20, 190 + (i * 20), 20, 20);
                    teraZone.add(seatRowLabel);
                }

                // 좌석
                for (int j = 0; j < 6; j++) {
                    cnt = 0;
                    for (int i = 0; i < 44; i++) {
                        if (i != 13 && i != 24 && i != 34) {
                            cnt++;
                            JCheckBox chkBox = new JCheckBox();
                            chkBox.setForeground(Color.BLACK);
                            chkBox.setBounds(40 + (i * 20), 190 + (j * 20), 20, 20);
                            seats[j][i] = chkBox;
                            char input = (char) (j + 65);
                            chkBox.setText(input + "," + Integer.toString(cnt));
                            teraZone.add(chkBox);

                            seats[j][i].addItemListener(listener);

                        }
                    }
                }
                // 예매된 좌석 선택 불가능
                ReservedSeat();

                seatPanel.setPreferredSize(new Dimension(950, 400));

                seatPanel.add(teraZone);
                mScrollSeatPane = new JScrollPane(seatPanel);
                mScrollSeatPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                mScrollSeatPane.setBounds(10, 70, 700, 500);
                getContentPane().add(mScrollSeatPane);

                break;

            // case 2:
            // JCheckBox[][] seats1 = new JCheckBox[6][15];
            // JCheckBox[][] seats2 = new JCheckBox[6][15];
            // JCheckBox[][] seats3 = new JCheckBox[10][15];
            // // JCheckBox[][] seats4 = new JCheckBox[10][15];

            // JPanel Apanel=new JPanel(new GridLayout(6,15));
            // JPanel Bpanel =new JPanel(new GridLayout(6,15));
            // JPanel Cpanel =new JPanel(new GridLayout(10,15));
            // //JPanel Dpanel =new JPanel(new GridLayout(10,15));
            // for (int j = 0; j < 6; j++) {
            // for (int i = 0; i < 15; i++) {
            // JCheckBox chkBox = new JCheckBox();
            // chkBox.setForeground(Color.BLACK);
            // seats1[j][i] = chkBox;
            // char input = (char) (j + 65);
            // Apanel.add(chkBox);
            // }
            // }
            // //B구역
            // for (int j = 0; j < 6; j++) {
            // for (int i = 0; i < 15; i++) {
            // JCheckBox chkBox = new JCheckBox();
            // chkBox.setForeground(Color.BLACK);
            // seats2[j][i] = chkBox;
            // char input = (char) (j + 65);
            // Bpanel.add(chkBox);
            // }
            // }
            // //C구역
            // for (int j = 0; j < 10; j++) {
            // for (int i = 0; i < 15; i++) {
            // JCheckBox chkBox = new JCheckBox();
            // chkBox.setForeground(Color.BLACK);
            // seats3[j][i] = chkBox;
            // char input = (char) (j + 65);
            // Cpanel.add(chkBox);
            // }
            // }
            // seatPanel.setLayout(new GridLayout(2,2));
            // seatPanel.add(Apanel);
            // seatPanel.add(Bpanel);
            // seatPanel.add(Cpanel);

            // reset.addActionListener(new ActionListener() {
            // @Override
            // public void actionPerformed(ActionEvent e){

            // dispose();
            // }
            // });

            // break;

            default:
                break;
        }

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // TODO 지우기
                new SelectSeatArea();
                dispose();
            }
        });
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "선택한 좌석을 모두 취소합니다.");
                new SelectSeat();
                dispose();
            }
        });

        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // new Pay(mGameInfo, seatsNumber, mAreaPrice, mSeatCheck,mAreaindex,mUser);
                // //경기정보, 선택한 좌석 정보, 결제금액, 선택한 좌석수, 구역인덱스를 담아서 넘김

                Controller.getInstance().setSeatNum(seatsNumber);
                Controller.getInstance().setTotalPrice(seatsNumber.size());

                new Pay();

                dispose();
            }
        });

        setSize(1020, 630);
        setVisible(true);
        setResizable(false);

    }

    public void ReservedSeat() {
        reserveSeats = seatControll.getReserveSeat(Controller.getInstance().getGame()[1]);

        for (String reservedSeat : reserveSeats) {
            String[] seatInfo = reservedSeat.split("열 ");
            int row = reservedSeat.charAt(0);
            row = row - 65;
            // if (i != 13 && i != 24 && i != 34)
            int col = Integer.parseInt(seatInfo[1]) - 1;
            if (13 <= col && col < 24)
                col++;
            else if (24 <= col && col < 34)
                col += 2;
            else if (col >= 34)
                col += 3;

            seats[row][col].setEnabled(false);
        }

    }
}
