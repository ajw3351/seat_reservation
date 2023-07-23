package ajw.DAO;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import ajw.PostgreSQL;
import ajw.json.Info;
import ajw.json.User;

public class TicketDAO {
    PostgreSQL mDatabase = PostgreSQL.getInstance();
    private static Connection con;
	private static PreparedStatement pstmt;
	private static ResultSet rs;

    private static TicketDAO instance = new TicketDAO();

    private TicketDAO(){
    }

    public static TicketDAO getInstance(){
        return instance;
    }

    public void connect(){
        con = mDatabase.connectPostgreSQL();
    }

    public void buyTicket(Info ticketInfo, String Id){
        String title = ticketInfo.getTitle();
        String bookingNum = ticketInfo.getBookingNum();
        String time = ticketInfo.getTime();
        String price = ticketInfo.getPrice();
        String payment = ticketInfo.getPayment();
        String ticketPickUpMethod = ticketInfo.getGetTicket();
        String area = ticketInfo.getArea();
        String ticketAddress = ticketInfo.getTicketAddress();
        ArrayList<String> seatList = ticketInfo.getSeatList();
    
        String sql = "INSERT INTO Ticket VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try{
            Array seat = con.createArrayOf("text", seatList.toArray());

            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,title);      
            pstmt.setString(2, bookingNum);
            pstmt.setString(3,time); 
            pstmt.setString(4,price); 
            pstmt.setString(5,payment); 
            pstmt.setString(6,ticketPickUpMethod);      
            pstmt.setString(7,area); 
            pstmt.setString(8, ticketAddress); 
            pstmt.setArray(9,seat); 
            pstmt.setString(10,Id);  
            pstmt.executeUpdate();
            System.out.println("데이터 삽입 완료");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getTicket(User user, String Id){
        String sql = "SELECT * FROM \"ticket\" WHERE \"user_id\" = ? ";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, Id);
            rs = pstmt.executeQuery();

            while(rs.next()){
                Info info = new Info();
                info.setTitle(rs.getString("title"));
                info.setBookingNum(rs.getString("bookingNum"));
                info.setTime(rs.getString("time"));
                info.setPayment(rs.getString("payment"));
                info.setPrice(rs.getString("price"));
                info.setGetTicket(rs.getString("ticketPickUpMethod"));
                info.setArea(rs.getString("area"));
                info.setTicketAddress(rs.getString("ticketAddress"));
                info.setSeatList(mDatabase.Change_arrayList(rs.getArray("seatList")));
                
                user.addInfo(info);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
