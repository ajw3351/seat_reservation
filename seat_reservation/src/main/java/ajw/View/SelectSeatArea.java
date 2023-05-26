package ajw.View;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ajw.Controller.Controller;
import ajw.json.User;

public class SelectSeatArea extends JFrame implements ListSelectionListener{
    String [] mArea= {"테라존(VIP석)","1루 테이블석","1루 익사이팅존","1루 블루석","1루 FILA zone","1루 레드석","1루 네이비석","중앙네이비석","1루 외야지정석","3루 테이블석","3루 익사이팅존","3루 블루석","3루 오렌지석","3루 레드석","3루 네이비석","3루 외야지정석","1루 블루휠체어석","1루 레드휠체어석(동반O)","1루 레드휠체어석(동반X)","3루 블루휠체어석","3루 레드휠체어석(동반O)","3루 레드휠체어석(동반X)","1루 블루석 시야방해","1루 FILA zone 시야방해","1루 레드석 시야방해","1루 네이비석 시야방해","중앙네이비석 시야방해","1루 외야지정석 시야방해","3루 블루석 시야방해","3루 오렌지석 시야방해","3루 레드석 시야방해","3루 네이비석 시야방해","3루 외야지정석 시야방해"};
    int [] mPrice={15000,18000,2134,2134,2134,243,2143,243,243,243,2431,2143,24,2134}; //HashMap으로 구역, 가격

    private ArrayList<ImageIcon> mImageList = new ArrayList<>();
    private User mUser;
    JLabel imgLabel =new JLabel();
    
    JList<String> mAreaList =new JList<String>(mArea);
    String mGameInfo;
    
    public SelectSeatArea(){
        setTitle("예매");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setLayout(null);

        var gameInfo = Controller.getInstance().getGame();

        JPanel seatImgPanel= new JPanel();
        JPanel seatListPanel=new JPanel();
        JPanel gameInfoPanel=new JPanel();
        JPanel buttonPanel=new JPanel(new GridLayout(1,2,10,10));

        JLabel gameInfoLabel=new JLabel(gameInfo[0] + gameInfo[1]);
        gameInfoPanel.add(gameInfoLabel);

        seatImgPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        imgLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        createImageList();

        gameInfoPanel.setBackground(Color.CYAN);
        seatListPanel.setBackground(Color.BLUE);
        seatImgPanel.setBackground(Color.YELLOW);
        

        gameInfoPanel.setBounds(10,10,980,50);
        seatImgPanel.setBounds(10,70,700,500);
        seatListPanel.setBounds(720,70,270,430);
        buttonPanel.setBounds(720,510,270,60);
        
        getContentPane().add(gameInfoPanel);
        getContentPane().add(seatImgPanel);
        getContentPane().add(seatListPanel);
        getContentPane().add(buttonPanel);
        
        mAreaList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        mAreaList.addListSelectionListener(this);
        seatListPanel.setLayout(new GridLayout(1,1));
        seatListPanel.add(new JScrollPane(mAreaList));

        seatImgPanel.setLayout(new GridLayout(1,1));
        imgLabel.setIcon(mImageList.get(33));
        seatImgPanel.add(imgLabel);

        JButton back = new JButton("이전");
        JButton next=new JButton("좌석 선택");
        buttonPanel.add(back);
        buttonPanel.add(next);
        
        setSize(1020,630);
        setVisible(true);
        setResizable(false);

        back.addActionListener(new ActionListener() {
            @Override
			public void actionPerformed(ActionEvent e){
                // TODO 지우는 코드 추가할 것
                new Reservation();
                dispose();
            }
        });

        next.addActionListener(new ActionListener() {
            @Override
			public void actionPerformed(ActionEvent e){
                Controller.getInstance().setArea(mArea[mAreaList.getSelectedIndex()]);
                Controller.getInstance().setPrice(mPrice[mAreaList.getSelectedIndex()]);
                new SelectSeat();
                dispose();
            }
        });


    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        imgLabel.setIcon(mImageList.get(mAreaList.getSelectedIndex()));
    }

    private void createImageList() {

        String root = System.getProperty("user.dir");
        String filePath;

        for (int i=0;i<36;i++) {
            filePath =  root + "/seat_reservation/src/main/java/resource/"+(i+1)+".png";
            mImageList.add(new ImageIcon(filePath));
        }
    }
}
