package ajw;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class seat extends JFrame {
    JCheckBox[][] seats1 = new JCheckBox[6][15];
    JCheckBox[][] seats2 = new JCheckBox[6][15];
    JCheckBox[][] seats3 = new JCheckBox[10][15];
    JCheckBox[][] seats4 = new JCheckBox[10][15];

	Color colors=new Color(153,204,255);
	Color colorr=new Color(102,255,204);

    public seat(){
        setTitle("시스템");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container main=getContentPane();
        main.setLayout(new BorderLayout(50,20));

		//선택한 공연 정보 패널
		JPanel concertInfo = new JPanel();
		JLabel label1=new JLabel("d");
		concertInfo.setAlignmentX(JPanel.CENTER_ALIGNMENT);

		concertInfo.add(label1);

		//선택한 좌석 정보 패널
		JPanel seatinfo=new JPanel(new GridLayout(3,1));
		JPanel seatcolorinfo=new JPanel();

		JLabel S=new JLabel("S석 : 110,000원");
		S.setForeground(colors);
		JLabel R=new JLabel("R석 : 90,000원");
		R.setForeground(colorr);

		seatcolorinfo.add(S);
		seatcolorinfo.add(R);

		 

		seatinfo.add(seatcolorinfo);


		

        //좌석 구역 패널
        JPanel Apanel=new JPanel(new GridLayout(6,15));
        JPanel Bpanel =new JPanel(new GridLayout(6,15));
        JPanel Cpanel =new JPanel(new GridLayout(10,15));
        JPanel Dpanel =new JPanel(new GridLayout(10,15));

		

        //A구역
        for (int j = 0; j < 6; j++) {
			for (int i = 0; i < 15; i++) {
				JCheckBox chkBox = new JCheckBox();
				chkBox.setBackground(colors);
				chkBox.setForeground(Color.BLACK);
				
				seats1[j][i] = chkBox; 
				char input = (char) (j + 65);
				chkBox.setText(input + "," + Integer.toString(i + 1)); 
			    Apanel.add(chkBox);
			}
		}
        //B구역
        for (int j = 0; j < 6; j++) {
			for (int i = 0; i < 15; i++) {
				JCheckBox chkBox = new JCheckBox();
				chkBox.setBackground(colors);
				chkBox.setForeground(Color.BLACK);
				
				seats2[j][i] = chkBox; 
				char input = (char) (j + 65);
				chkBox.setText(input + "," + Integer.toString(i + 1)); 
			    Bpanel.add(chkBox);
			}
		}
        //C구역
        for (int j = 0; j < 10; j++) {
			for (int i = 0; i < 15; i++) {
				JCheckBox chkBox = new JCheckBox();
				chkBox.setBackground(colorr);
				chkBox.setForeground(Color.BLACK);
				
				seats3[j][i] = chkBox; 
				char input = (char) (j + 65);
				chkBox.setText(input + "," + Integer.toString(i + 1)); 
			    Cpanel.add(chkBox);
			}
		}
        //D구역
        for (int j = 0; j < 10; j++) {
			for (int i = 0; i < 15; i++) {
				JCheckBox chkBox = new JCheckBox();
				chkBox.setBackground(colorr);
				chkBox.setForeground(Color.BLACK);
				 
				seats4[j][i] = chkBox; 
				char input = (char) (j + 65);
				chkBox.setText(input + "," + Integer.toString(i + 1)); 
			    Dpanel.add(chkBox);
			}
		}

        JPanel seatpanel=new JPanel();
        seatpanel.setLayout(new GridLayout(2,2,20,20));
        seatpanel.add(Apanel);
        seatpanel.add(Bpanel);
        seatpanel.add(Cpanel);
        seatpanel.add(Dpanel);
        
        main.add(seatpanel,BorderLayout.CENTER);
		main.add(concertInfo,BorderLayout.NORTH);
        
        

        setSize(900,600);
        setVisible(true);
    }
    
}
