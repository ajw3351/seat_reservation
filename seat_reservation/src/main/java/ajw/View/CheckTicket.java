package ajw.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ajw.Controller.Controller;
import ajw.json.User;

public class CheckTicket extends JFrame {

    User mUser;

    public CheckTicket() {
        setTitle("예매");
        
        setLayout(null);

        var controller = Controller.getInstance();

        JPanel topPanel = new JPanel();
        JPanel midPanel = new JPanel(new BorderLayout());
        JPanel botPanel = new JPanel(new GridLayout(1, 3, 10, 10));

        topPanel.setBounds(10, 10, 400, 30);
        midPanel.setBounds(80, 50, 840, 480);
        botPanel.setBounds(720, 540, 270, 30);

        getContentPane().add(topPanel);
        getContentPane().add(midPanel);
        getContentPane().add(botPanel);

        // topPanel
        JLabel endLabel = new JLabel("예매가 완료되었습니다.");
        endLabel.setFont(new Font("휴먼엑스포", Font.BOLD, 20));
        topPanel.add(endLabel);

        // midPanel
        JPanel infoLabelPanel = new JPanel();
        JPanel infoPanel = new JPanel(new GridLayout(13, 1, 10, 10));

        JLabel infoLabel = new JLabel("My 예매정보");
        infoLabel.setFont(new Font("휴먼편지체", Font.BOLD, 25));
        infoLabelPanel.add(infoLabel);

        JLabel gameLabel = new JLabel("경   기 : " + controller.getGame()[0]);
        JLabel dateLabel = new JLabel("일   시 : " + controller.getGame()[1]);
        JLabel priceLabel = new JLabel("결제금액 : " + controller.getTotalPrice() + "원");
        JLabel bookingNumLabel = new JLabel("예매번호 : " + controller.getBookingNum());
        JLabel getTicketLabel = new JLabel("수령방법 : " + controller.get_GetTicket());
        JLabel paymentLabel = new JLabel("결제수단 : " + controller.getPayment());
        JLabel AreaLabel = new JLabel("좌석구역 : " + controller.getArea());
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
        for (int i = 0; i < controller.getSeatNum().size(); i++) {
            seatNumLabel[i] = new JLabel("        " + controller.getSeatNum().get(i));
            infoPanel.add(seatNumLabel[i]);
        }

        if ((controller.get_GetTicket()).equals("티켓배송")) {
            JLabel addressLabel = new JLabel("티켓수령지 : " + controller.getTicketAddress());
            infoPanel.add(addressLabel);
        }

        infoPanel.setBackground(Color.WHITE);
        infoLabelPanel.setBounds(10, 10, 200, 25);
        infoPanel.setBounds(10, 45, 820, 430);

        midPanel.add(infoLabelPanel, BorderLayout.NORTH);
        midPanel.add(infoPanel, BorderLayout.CENTER);

        // botPanel
        JButton printButton = new JButton("인쇄");
        JButton homeButton = new JButton("홈");
        JButton disposeButton = new JButton("종료");
        botPanel.add(printButton);
        botPanel.add(homeButton);
        botPanel.add(disposeButton);

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Home();

                dispose();
            }
        });

        printButton.addActionListener(new ActionListener() {
        @Override
            public void actionPerformed(ActionEvent e) {
                new Ticket();
            }
        });
        disposeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setSize(1020, 630);
        setVisible(true);
        setResizable(false);
    }

}
