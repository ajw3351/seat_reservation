package ajw.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import ajw.PostgreSQL;
import ajw.json.UserInfo;

public class UserDAO {
    PostgreSQL mDatabase = PostgreSQL.getInstance();
    private static Connection con;
	private static PreparedStatement pstmt;
	private static ResultSet rs;
    private static Statement stmt;

    private static UserDAO instance = new UserDAO();

    private UserDAO(){

    }

    public static UserDAO getInstance(){
        return instance;
    }

    public void connect(){
        con = mDatabase.connectPostgreSQL();
    }
    
    public void register(UserInfo userInfo){
        String id = userInfo.getId();
        String pwd = userInfo.getPwd();
        String name = userInfo.getName();
        String email = userInfo.getEmail();
        String phoneNum = userInfo.getPhoneNum();
        String type = "0";

        String sql = "INSERT INTO \"User\" VALUES(?, ?, ?, ?, ?, ?)";
        
        try{
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,id);      
            pstmt.setString(2,pwd); 
            pstmt.setString(3,name); 
            pstmt.setString(4,email); 
            pstmt.setString(5,phoneNum);   
            pstmt.setString(6,type);
            pstmt.executeUpdate();
            System.out.println("데이터 삽입 완료");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int login(String Id, String pwd){
        String sql = "SELECT * FROM \"User\" WHERE \"ID\" = ? ";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, Id);
            rs = pstmt.executeQuery();

            if(rs.next()){
                System.out.println("id가 확인됨");
                String password = rs.getString("Pwd");

                if(pwd.equals(password)){
                    System.out.println("로그인 성공");
                    return 0;
                }
                else{
                    return -1; // 비밀번호가 일치하지 않습니다.
                }
            }
            else{
                return -2; // 아이디 없음
            }
            // stmt = con.createStatement();
            // rs = stmt.executeQuery(sql);
            // while(rs.next()){
            //     if()
            // }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -3; // 데이터베이스 오류
    }

}
