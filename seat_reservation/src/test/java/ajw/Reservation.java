// package ajw;

// import java.awt.FlowLayout;
// import java.awt.Image;

// import javax.swing.ButtonGroup;
// import javax.swing.ImageIcon;
// import javax.swing.JFrame;
// import javax.swing.JLabel;
// import javax.swing.JList;
// import javax.swing.JPanel;
// import javax.swing.JRadioButton;
// import javax.swing.ListSelectionModel;
// import javax.swing.event.ListSelectionEvent;
// import javax.swing.event.ListSelectionListener;

// import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
// import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
// import net.sourceforge.jdatepicker.impl.UtilDateModel;

// public class Reservation extends JFrame implements ListSelectionListener{
//     String [] movie= {"범죄도시","신과 함께","스즈매의 문단속"};
//     String [] th={"남춘천점","춘천점","원주점","속초점"};
   
//     private ImageIcon [] image = {new ImageIcon("C:\\Users\\ajw1\\Desktop\\ee\\city.jpg"),new ImageIcon("C:\\Users\\ajw1\\Desktop\\ee\\god.jpg"),new ImageIcon("C:\\Users\\ajw1\\Desktop\\ee\\ss.jpg"),new ImageIcon("C:\\Users\\ajw1\\Desktop\\ee\\xx.png")}; 
//     private ImageIcon [] updateimage=new ImageIcon[image.length];
   
//     JList<String> mvlist =new JList<String>(movie);
//     JLabel imgLabel =new JLabel();
//     JLabel [] label={new JLabel("영화"), new JLabel("상영관"), new JLabel("날짜"), new JLabel("상영 시간"), new JLabel("인원")};

//     JRadioButton [] time = {new JRadioButton("10:00"),new JRadioButton("15:00"),new JRadioButton("19:00")};
//     public Reservation(){
//         setTitle("예매");
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
//         getContentPane().setLayout(null);
        
//         label[0].setBounds(70,60,150,20);
//         label[1].setBounds(300,60,150,20);
//         label[2].setBounds(500,60,150,20); 
        
//         for(int i=0;i<3;i++){
//             getContentPane().add(label[i]);
//         }
        
//         //영화
//         mvlist.setBounds(70,80,150,290);
//         mvlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//         mvlist.addListSelectionListener(this);
//         getContentPane().add(mvlist);
        
//         //상영관
//         JList<String> thlist = new JList<String>(th);
//         thlist.setBounds(300,80,150,290);
//         thlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//         getContentPane().add(thlist);

//         //달력 
//         UtilDateModel model = new UtilDateModel();
//         JDatePanelImpl datePanel = new JDatePanelImpl(model);
//         JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
//         add(datePicker);
//         datePicker.setBounds(500,80,150,30);

//         //이미지 사이즈 조절
//         for(int i=0;i<image.length;i++){
//             Image img = image[i].getImage();
//             Image updateImg = img.getScaledInstance(150, 210, Image.SCALE_SMOOTH);
//             updateimage[i]= new ImageIcon(updateImg);
//         }

//         imgLabel.setIcon(updateimage[3]);
        
//         imgLabel.setBounds(680,80,150,290);
//         getContentPane().add(imgLabel);

//         //상영 시간
//         ButtonGroup t= new ButtonGroup();
//         JPanel p1=new JPanel(new FlowLayout());

//         for(int i=0;i<3;i++){
//             t.add(time[i]);
//             p1.add(time[i]);
//         }
//         p1.setBounds(70,500,200,50);
//         getContentPane().add(p1);



//         setSize(900,600);
//         setVisible(true);
//         setResizable(false);
//     }
//     @Override
//     public void valueChanged(ListSelectionEvent e) {
//         switch(mvlist.getSelectedIndex()){
//                     case 0: imgLabel.setIcon(updateimage[0]);
//                             break;
//                     case 1: imgLabel.setIcon(updateimage[1]);
//                             break;
//                     case 2:imgLabel.setIcon(updateimage[2]);
//                             break;  
//                 }
//     }

// }
