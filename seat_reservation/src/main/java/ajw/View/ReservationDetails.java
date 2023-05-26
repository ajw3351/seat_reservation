package ajw.View;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import ajw.Controller.Controller;
import ajw.json.Info;
import ajw.json.User;

public class ReservationDetails extends JFrame{
    User mUser;
    ArrayList<Info> reservation = new ArrayList<>();
    String [][] mInfo;
    String header[] = {"경    기", "날    짜", "구    역", "결제금액", "   "};
    JTable table;
    
    Controller controller = Controller.getInstance();

    public ReservationDetails(){
        setTitle("예매");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

       
        var user = controller.getUser();

        JPanel topPanel = new JPanel();
        JPanel midPanel = new JPanel(new BorderLayout());
        JPanel botPanel = new JPanel(new GridLayout(1,2,10,10));

        topPanel.setBounds(10,10,400,30);
        midPanel.setBounds(80,50,840,480);
        botPanel.setBounds(720,540,270,30);

        getContentPane().add(topPanel);
        getContentPane().add(midPanel);
        getContentPane().add(botPanel);

        //botPanel
        JButton backButton = new JButton("이전");
        JButton disposeButton=new JButton("종료");
        botPanel.add(backButton);
        botPanel.add(disposeButton);

        // topPanel
        JLabel titleLabel=new JLabel("예매 현황");
        titleLabel.setFont(new Font("휴먼엑스포",Font.BOLD,20));
        topPanel.add(titleLabel);

        //저장된 내역 변수에 저장
        reservation = user.getInfos();
        mInfo = new String[reservation.size()][5];
        for(int i = 0; i<reservation.size(); i++){
            
            mInfo[i][0] = reservation.get(i).getTitle();
            mInfo[i][1] = reservation.get(i).getTime();
            mInfo[i][2] = reservation.get(i).getArea();
            mInfo[i][3] = reservation.get(i).getPrice() + "원";
            mInfo[i][4] = " ";
        }

        //midPanel
        table = new JTable(mInfo,header);
        JScrollPane scrollPane = new JScrollPane(table);

        table.setRowHeight(60);
        table.getColumnModel().getColumn(4).setCellRenderer(new TableCell());;
        table.getColumnModel().getColumn(4).setCellEditor(new TableCell());;

        midPanel.add(scrollPane, BorderLayout.CENTER);



        backButton.addActionListener(new ActionListener() {
            @Override
			public void actionPerformed(ActionEvent e){
                new Home();

                dispose();
            }
        });

        disposeButton.addActionListener(new ActionListener() {
            @Override
			public void actionPerformed(ActionEvent e){
                dispose();
            }
        });


        setSize(1020,630);
        setVisible(true);
        setResizable(false);
    }

    class TableCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {
        JButton jb;
 
        public TableCell() {
            jb = new JButton("선택");
            jb.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    controller.setSelectInfo(table.getSelectedRow());
                    new TicketDetails();
                    dispose();
                }
            });
        }
 
        @Override
        public Object getCellEditorValue() {
            return null;
        }
 
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {
            return jb;
        }
 
        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
                int column) {
            return jb;
        }
    }
 
}
