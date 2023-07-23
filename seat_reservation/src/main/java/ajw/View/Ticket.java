package ajw.View;
// package ajw;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import ajw.Controller.Controller;

public class Ticket extends JFrame{
    private JTable table;
    private JScrollPane scrollPane;
    String header[] = {"티   켓", "정   보"};
    String head[] = {"경   기", "일   시", "결제금액", "예매번호","수령방법","결제수단","좌석구역","좌   석","티켓수령지"};
    String [][]mReserveInfo;
    Controller controller = Controller.getInstance();

    public Ticket(){
        setTitle("티켓");
        
        setLayout(new BorderLayout());
        


        mReserveInfo = new String[9][2];
        mReserveInfo[7][1] = "";
        for(int i =0; i<9; i++){
            mReserveInfo[i][0] = head[i]; 
        }
        mReserveInfo[0][1] = controller.getGame()[0];
        mReserveInfo[1][1] = controller.getGame()[1];
        mReserveInfo[2][1] = controller.getTotalPrice() + "원";
        mReserveInfo[3][1] = controller.getBookingNum();
        mReserveInfo[4][1] = controller.get_GetTicket();
        mReserveInfo[5][1] = controller.getPayment();
        mReserveInfo[6][1] = controller.getArea();
        for(int i = 0; i<controller.getSeatNum().size(); i++){
            mReserveInfo[7][1] = mReserveInfo[7][1] + controller.getSeatNum().get(i) + " \n";
        }
        if((controller.get_GetTicket()).equals("티켓배송"))
            mReserveInfo[8][1] = controller.getTicketAddress();
        else
            mReserveInfo[8][1] = "";

        table = new JTable(mReserveInfo,header);
        scrollPane = new JScrollPane(table);
     
        JButton button = new JButton("인  쇄");
        button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					table.print(JTable.PrintMode.FIT_WIDTH,new MessageFormat("Header"),new MessageFormat("Footer")); //JTable.PrintMode.FIT_WIDTH 테이블 전체를 출력하는 인쇄모드, 행이 많다면 여러 페이지로 나눠서 인쇄함, 
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

        getContentPane().add(button,BorderLayout.NORTH);
        getContentPane().add(scrollPane,BorderLayout.CENTER);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
       
        setSize(300,600);
        setVisible(true);
        setResizable(false);
    }
}
