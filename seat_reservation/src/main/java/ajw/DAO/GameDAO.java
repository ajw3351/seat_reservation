package ajw.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ajw.PostgreSQL;
import ajw.VO.GameVO;

public class GameDAO {
    PostgreSQL mDatabase = PostgreSQL.getInstance();
    private static Connection con;
    private static PreparedStatement pstmt;
    private static ResultSet rs;
    private static Statement stmt;

    private static GameDAO instance = new GameDAO();

    private GameDAO() {

    }

    public static GameDAO getInstance() {
        return instance;
    }

    public void connect() {
        con = mDatabase.connectPostgreSQL();
    }

    public void register(GameVO gameVO) {
        connect();
        //등록되어 있는 경기 수
        int size = 2;
        String sqlTable = "SELECT * COUNT(*) AS total FROM \"Game\"";
        try {
            pstmt = con.prepareStatement(sqlTable);
            rs = pstmt.executeQuery();
            if(rs.next())
                size = rs.getInt("total");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        int gameId = size;
        String title = gameVO.getTitle();
        String date = gameVO.getDate();
        String time = gameVO.getTime();
        String homeImg = gameVO.getHomeImg();
        String awayImg = gameVO.getAwayImg();

        String sql = "INSERT INTO \"Game\" VALUES(?, ?, ?, ?, ?, ?)";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, gameId);
            pstmt.setString(2, title);
            pstmt.setString(3, date);
            pstmt.setString(4, time);
            pstmt.setString(5, homeImg);
            pstmt.setString(6, awayImg);
            pstmt.executeUpdate();
            System.out.println("데이터 삽입 완료");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<GameVO> getGameInfo() {
        connect();
        
        ArrayList<GameVO> gameInfo = new ArrayList<>();
        String sql = "SELECT * FROM \"Game\" WHERE \"gameId\" = ? ";
        for (int i = 0; i < 3; i++) {
            try {
                GameVO gameVO = new GameVO();
                pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, i);
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    gameVO.setGameId(i);
                    gameVO.setTitle(rs.getString("title"));
                    gameVO.setDate(rs.getString("date"));
                    gameVO.setTime(rs.getString("time"));
                    gameVO.setHomeImg(rs.getString("homeTeamImg"));
                    gameVO.setAwayImg(rs.getString("awayTeamImg"));

                    gameInfo.add(gameVO);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return gameInfo;
    }

    public void DeleteGame(int index){
        connect();
        int size = 0;
        String sqlTable = "SELECT * COUNT(*) AS total FROM \"Game\"";
        try {
            pstmt = con.prepareStatement(sqlTable);
            rs = pstmt.executeQuery();
            if(rs.next())
                size = rs.getInt("total");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        String sqlDel = "DELETE * FROM \"Game\" SET \"gameId\" = ? WHERE \"gameId\" = ? ";
        String sqlUpd = "UPDATE * FROM \"Game\" WHERE \"gameId\" = ? ";
        try {
            pstmt = con.prepareStatement(sqlDel);
            pstmt.setInt(1, index);
            int rowsAffected = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            for(int i = index + 1; i < size; i++){
                pstmt = con.prepareStatement(sqlUpd);
                pstmt.setInt(1, i-1);
                pstmt.setInt(2, i);
                int rowsAffected = pstmt.executeUpdate();
            }
            

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
