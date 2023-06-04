package ajw.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

import ajw.Controller.Controller;
import ajw.Controller.FileWrite;

public class Pay extends JFrame {
    String[] mArea = { "테라존(VIP석)", "1루 테이블석", "1루 익사이팅존", "1루 블루석", "1루 FILA zone", "1루 레드석", "1루 네이비석", "중앙네이비석",
            "1루 외야지정석", "3루 테이블석", "3루 익사이팅존", "3루 블루석", "3루 오렌지석", "3루 레드석", "3루 네이비석", "3루 외야지정석", "1루 블루휠체어석",
            "1루 레드휠체어석(동반O)", "1루 레드휠체어석(동반X)", "3루 블루휠체어석", "3루 레드휠체어석(동반O)", "3루 레드휠체어석(동반X)", "1루 블루석 시야방해",
            "1루 FILA zone 시야방해", "1루 레드석 시야방해", "1루 네이비석 시야방해", "중앙네이비석 시야방해", "1루 외야지정석 시야방해", "3루 블루석 시야방해",
            "3루 오렌지석 시야방해", "3루 레드석 시야방해", "3루 네이비석 시야방해", "3루 외야지정석 시야방해" };
    JRadioButton[] getButtons = new JRadioButton[3];
    String[] 수령방법 = { "현장수령", "모바일티켓", "티켓배송" };
    JRadioButton[] paymentButtons = new JRadioButton[3];
    String[] 결제수단 = { "무통장입금", "카카오페이", "네이버페이" };
    JTextArea ta = new JTextArea(5, 40);

    Vector<JLabel> seatDetails = new Vector<>();

    Random randdom = new Random();
    int createNum = 0;
    String ranNum = "";
    String bookingNum = "";

    // 넘겨주는 값int area,String gameInfo,int areaPrice,JLabel areaImage
    int mAreaIndex;
    String mGameInfo;
    int mAreaPrice;
    int mPeople;

    Controller controller = Controller.getInstance();
    Vector<String> reserveInfo = new Vector<String>(); // 여기에 예매 정보가 저장된다.

    public Pay() {
        setTitle("예매");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        int price = controller.getPrice() * controller.getSeatNum().size();

        JPanel payDetailPanel = new JPanel();
        JPanel reservationDetails = new JPanel();
        JPanel gameInfoPanel = new JPanel();
        JPanel pricePanel = new JPanel();
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 0));

        reservationDetails.setLayout(null);
        payDetailPanel.setLayout(null);

        JLabel priceLabel = new JLabel("결제금액 : " + price + "원");
        priceLabel.setFont(new Font("휴먼엑스포", Font.PLAIN, 18));
        pricePanel.add(priceLabel);

        gameInfoPanel.setBackground(Color.CYAN);
        reservationDetails.setBackground(Color.WHITE);
        payDetailPanel.setBackground(Color.WHITE);

        gameInfoPanel.setBounds(10, 10, 980, 50);
        payDetailPanel.setBounds(10, 70, 700, 500);
        reservationDetails.setBounds(720, 70, 270, 400);
        pricePanel.setBounds(720, 480, 270, 50);
        buttonPanel.setBounds(720, 530, 270, 40);

        getContentPane().add(gameInfoPanel);
        getContentPane().add(payDetailPanel);
        getContentPane().add(reservationDetails);
        getContentPane().add(pricePanel);
        getContentPane().add(buttonPanel);

        // 버튼 생성
        JButton back = new JButton("이전");
        JButton next = new JButton("결제");
        buttonPanel.add(back);
        buttonPanel.add(next);

        // 경기 정보
        var gameInfo = controller.getGame();
        JLabel gameInfoLabel = new JLabel(gameInfo[0] + gameInfo[1]);
        gameInfoPanel.add(gameInfoLabel);

        // 좌석 정보
        JPanel seatInfo = new JPanel(new GridLayout(4, 1));
        JLabel areaInfo = new JLabel(controller.getArea());
        JLabel area = new JLabel("선택 구역");
        JLabel seat = new JLabel("선택 좌석");

        area.setFont(new Font("휴먼엑스포", Font.PLAIN, 23));
        area.setForeground(Color.RED);
        seat.setFont(new Font("휴먼엑스포", Font.PLAIN, 23));
        seat.setForeground(Color.RED);
        areaInfo.setFont(new Font("휴먼엑스포", Font.PLAIN, 18));

        area.setBounds(10, 10, 250, 60);
        areaInfo.setBounds(10, 80, 250, 50);
        seat.setBounds(10, 140, 250, 60);
        seatInfo.setBounds(10, 210, 250, 180);

        for (int i = 0; i < 4; i++) {
            JLabel seatInfoLabel = new JLabel("");
            seatInfoLabel.setFont(new Font("휴먼엑스포", Font.PLAIN, 18));
            seatDetails.add(seatInfoLabel);
            if (i < controller.getSeatNum().size())
                seatDetails.get(i).setText(controller.getSeatNum().get(i));
            seatInfo.add(seatInfoLabel);
        }
        reservationDetails.add(area);
        reservationDetails.add(areaInfo);
        reservationDetails.add(seat);
        reservationDetails.add(seatInfo);

        // 결제정보 패널
        JPanel getTicketPanel = new JPanel(new GridLayout(4, 1));
        JPanel paymentPanel = new JPanel(new GridLayout(4, 1));
        JPanel buyerInfoPanel = new JPanel(new GridLayout(6, 1));
        JPanel addressPanel = new JPanel();
        addressPanel.setLayout(null);

        // 티켓수령
        JLabel getTicketLabel = new JLabel("티켓 수령 방법");
        getTicketLabel.setFont(new Font("휴먼엑스포", Font.PLAIN, 23));

        ButtonGroup getGroup = new ButtonGroup();
        getTicketPanel.add(getTicketLabel);

        for (int i = 0; i < 3; i++) {
            getButtons[i] = new JRadioButton(수령방법[i]);
            getButtons[i].addItemListener(new MyItemListener());
            getGroup.add(getButtons[i]);
            getTicketPanel.add(getButtons[i]);
        }

        // 결제방법
        JLabel paymentLabel = new JLabel("결제방법");
        paymentLabel.setFont(new Font("휴먼엑스포", Font.PLAIN, 23));
        ButtonGroup payGroup = new ButtonGroup();

        paymentPanel.add(paymentLabel);
        for (int i = 0; i < 3; i++) {
            paymentButtons[i] = new JRadioButton(결제수단[i]);
            paymentButtons[i].addItemListener(new MyItemListener());
            payGroup.add(paymentButtons[i]);
            paymentPanel.add(paymentButtons[i]);
        }

        // 예매자
        JLabel userCheck = new JLabel("예매자 확인");
        userCheck.setFont(new Font("휴먼엑스포", Font.PLAIN, 23));
        JLabel name = new JLabel("이름 : " + controller.getUserInfo().getName());
        JLabel phoneNum = new JLabel("연락처 : " + controller.getUserInfo().getPhoneNum());
        JLabel teamCheck = new JLabel("응원구단");
        ButtonGroup teamGroup = new ButtonGroup();
        JRadioButton homeTeam = new JRadioButton("두산"); // gameInfo에서 가져와야됨
        JRadioButton awayTeam = new JRadioButton("키움");

        teamGroup.add(homeTeam);
        teamGroup.add(awayTeam);

        buyerInfoPanel.add(userCheck);
        buyerInfoPanel.add(name);
        buyerInfoPanel.add(phoneNum);
        buyerInfoPanel.add(teamCheck);
        buyerInfoPanel.add(homeTeam);
        buyerInfoPanel.add(awayTeam);

        // 배송지 입력
        JLabel address = new JLabel("배송지 입력");
        address.setFont(new Font("휴먼엑스포", Font.PLAIN, 23));

        address.setBounds(0, 10, 335, 60);

        ta.setBounds(10, 80, 315, 80);
        ta.setEnabled(false);

        addressPanel.add(address);
        addressPanel.add(ta);

        getTicketPanel.setBounds(10, 10, 335, 235);
        paymentPanel.setBounds(10, 255, 335, 235);
        buyerInfoPanel.setBounds(355, 10, 335, 300);
        addressPanel.setBounds(355, 320, 335, 170);

        payDetailPanel.add(getTicketPanel);
        payDetailPanel.add(paymentPanel);
        payDetailPanel.add(buyerInfoPanel);
        payDetailPanel.add(addressPanel);

        // 예매번호 생성
        for (int i = 0; i < 9; i++) {
            createNum = randdom.nextInt(9);
            ranNum = Integer.toString(createNum);
            bookingNum += ranNum;
        }

        setSize(1020, 630);
        setVisible(true);
        setResizable(false);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO wldnrl지우기
                new SelectSeat();
                dispose();
            }
        });
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "결제가 완료되었습니다.");

                controller.setBookingNum(bookingNum);
                controller.setTicketAddress(ta.getText());

                var user = controller.theEnd();
                FileWrite FW = new FileWrite();

                try {
                    FW.FileWrite(user, controller.getmUserId());
                } catch (IOException exception) {
                    exception.printStackTrace();
                }

                new CheckTicket();
                dispose();
            }
        });
    }

    // Item 리스너 생성
    class MyItemListener implements ItemListener {
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.DESELECTED)
                return;
            if (getButtons[0].isSelected()) {
                controller.set_GetTicket(수령방법[0]);
                ta.setEnabled(false);
            }

            if (getButtons[1].isSelected()) {
                controller.set_GetTicket(수령방법[1]);
                ta.setEnabled(false);
            }

            if (getButtons[2].isSelected()) {
                controller.set_GetTicket(수령방법[2]);
                ta.setEnabled(true);
            }

            if (paymentButtons[0].isSelected())
                controller.setPayment(결제수단[0]);
            if (paymentButtons[1].isSelected())
                controller.setPayment(결제수단[1]);
            if (paymentButtons[2].isSelected())
                controller.setPayment(결제수단[2]);

        }
    }
}
