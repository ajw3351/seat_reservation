package ajw.View;
// package ajw;

// import java.util.Vector;

// import javax.swing.JFrame;
// import javax.swing.JScrollPane;
// import javax.swing.JTable;

// public class Ticket extends JFrame{
//     private JTable table;
//     private JScrollPane scrollPane;

//     public Ticket(Vector<String> reserveInfo){
//         setTitle("티켓");
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         setLayout(null);
        
//         String ticket = "티   켓";
        
//         int people = Integer.parseInt(reserveInfo.get(5));

//         String [] gameInfo = reserveInfo.get(0).split("  ");
//         String info[] = {"경   기 : " + gameInfo[0], "일   시 : " + gameInfo[1], reserveInfo.get(1),reserveInfo.get(2),reserveInfo.get(3),reserveInfo.get(2),"좌석 : "};
//         for(int i = 0; i < people; i++)
//             info[i] = "        " + reserveInfo.get(i+6);
        
//         JTable table = new JTable(ticket,info);


       
//         setSize(300,600);
//         setVisible(true);
//         setResizable(false);
//     }
// }
