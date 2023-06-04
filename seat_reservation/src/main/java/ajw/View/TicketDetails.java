package ajw.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ajw.Controller.Controller;
import ajw.Controller.FileWrite;
import ajw.json.Info;
import ajw.json.User;

public class TicketDetails extends JFrame {

    User mUser;
    Info mInfo;
    Controller controller = Controller.getInstance();

    public TicketDetails() {
        setTitle("예매");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        mUser = controller.getUser();
        mInfo = controller.getSelectInfo();

        JPanel midPanel = new JPanel(new BorderLayout());
        JPanel botPanel = new JPanel(new GridLayout(1, 3, 10, 10));

        midPanel.setBounds(125, 25, 750, 480);
        botPanel.setBounds(720, 540, 270, 30);

        getContentPane().add(midPanel);
        getContentPane().add(botPanel);

        // midPanel
        JPanel infoLabelPanel = new JPanel();
        JPanel infoPanel = new JPanel(new GridLayout(13, 1, 10, 10));
        JPanel cancelPanel = new JPanel(new BorderLayout());

        JPanel emptyPanel = new JPanel();
        JButton cancelButton = new JButton("예매취소");

        cancelPanel.add(emptyPanel, BorderLayout.CENTER);
        cancelPanel.add(cancelButton, BorderLayout.NORTH);

        JLabel infoLabel = new JLabel("My 예매정보");
        infoLabel.setFont(new Font("휴먼편지체", Font.BOLD, 25));
        infoLabelPanel.add(infoLabel);

        JLabel gameLabel = new JLabel("경   기 : " + mInfo.getTitle());
        JLabel dateLabel = new JLabel("일   시 : " + mInfo.getTime());
        JLabel priceLabel = new JLabel("결제금액 : " + mInfo.getPrice() + "원");
        JLabel bookingNumLabel = new JLabel("예매번호 : " + mInfo.getBookingNum());
        JLabel getTicketLabel = new JLabel("수령방법 : " + mInfo.getGetTicket());
        JLabel paymentLabel = new JLabel("결제수단 : " + mInfo.getPayment());
        JLabel AreaLabel = new JLabel("좌석구역 : " + mInfo.getArea());
        JLabel seatLabel = new JLabel("좌석 : ");
        infoPanel.add(gameLabel);
        infoPanel.add(dateLabel);
        infoPanel.add(priceLabel);
        infoPanel.add(bookingNumLabel);
        infoPanel.add(getTicketLabel);
        infoPanel.add(paymentLabel);
        infoPanel.add(AreaLabel);
        infoPanel.add(seatLabel);

        JLabel[] seatNumLabel = new JLabel[4];
        for (int i = 0; i < mInfo.getSeatList().size(); i++) {
            seatNumLabel[i] = new JLabel("        " + mInfo.getSeatList().get(i));
            infoPanel.add(seatNumLabel[i]);
        }
        infoPanel.setBackground(Color.WHITE);
        emptyPanel.setBackground(Color.WHITE);

        if ((mInfo.getGetTicket()).equals("티켓배송")) {
            JLabel addressLabel = new JLabel("티켓수령지 : " + mInfo.getTicketAddress());
            infoPanel.add(addressLabel);
        }

        midPanel.add(infoLabelPanel, BorderLayout.NORTH);
        midPanel.add(infoPanel, BorderLayout.CENTER);
        midPanel.add(cancelPanel, BorderLayout.EAST);

        // botPanel
        JButton backButton = new JButton("이전");
        JButton homeButton = new JButton("홈");
        JButton disposeButton = new JButton("종료");
        botPanel.add(backButton);
        botPanel.add(homeButton);
        botPanel.add(disposeButton);

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Home();

                dispose();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ReservationDetails();
                dispose();
            }
        });
        disposeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mUser.getInfos().remove(mInfo);

                FileWrite FW = new FileWrite();

                try {
                    FW.FileWrite(mUser, controller.getmUserId());
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                new ReservationDetails();

                dispose();
            }
        });

        setSize(1020, 630);
        setVisible(true);
        setResizable(false);
    }
}
