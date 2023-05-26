package ajw.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import ajw.json.User;
public class Home extends JFrame {
    
    public Home(){
        setTitle("선택");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JButton jres = new JButton("예매하기");
        jres.setBounds(112,225,150,150);

        JButton jcheck = new JButton("예매내역");
        jcheck.setBounds(375,225,150,150);
        
        JButton jlogout = new JButton("로그아웃");
        jlogout.setBounds(638,225,150,150);

        getContentPane().setLayout(null);

        getContentPane().add(jres);
        getContentPane().add(jcheck);
        getContentPane().add(jlogout);

        setSize(900,600);
        setVisible(true);

        jres.addActionListener(new ActionListener() {
            @Override
			public void actionPerformed(ActionEvent e){
                new Reservation();
                dispose();
            }
        });
        jcheck.addActionListener(new ActionListener() {
            @Override
			public void actionPerformed(ActionEvent e){
                new ReservationDetails();
                dispose();
            }
        });
        
        jlogout.addActionListener(new ActionListener() {
            @Override
			public void actionPerformed(ActionEvent e){
                new LoginScreen();
                dispose();
            }
        });
    }
}
