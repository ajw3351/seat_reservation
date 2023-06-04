package ajw.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import ajw.Controller.Controller;

public class Home extends JFrame {

    public Home() {

        Controller.getInstance().clear();

        setTitle("선택");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton reservationButton = new JButton("예매하기");
        reservationButton.setBounds(112, 225, 150, 150);

        JButton checkTicketButton = new JButton("예매내역");
        checkTicketButton.setBounds(375, 225, 150, 150);

        JButton logoutButton = new JButton("로그아웃");
        logoutButton.setBounds(638, 225, 150, 150);

        getContentPane().setLayout(null);

        getContentPane().add(reservationButton);
        getContentPane().add(checkTicketButton);
        getContentPane().add(logoutButton);

        setSize(900, 600);
        setVisible(true);

        reservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Reservation();
                dispose();
            }
        });
        checkTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ReservationDetails();
                dispose();
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.getInstance().clearUser();
                new LoginScreen();
                dispose();
            }
        });
    }
}
