package ajw.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import ajw.PostgreSQL;
import ajw.VO.AreaVO;
import ajw.VO.GameVO;

public class AreaDAO {
    PostgreSQL mDatabase = PostgreSQL.getInstance();
    private static Connection con;
    private static PreparedStatement pstmt;
    private static ResultSet rs;
    private static Statement stmt;

    private static AreaDAO instance = new AreaDAO();

    private AreaDAO() {

    }

    public static AreaDAO getInstance() {
        return instance;
    }

    public void connect() {
        con = mDatabase.connectPostgreSQL();
    }

    public int getCapacity() {
        connect();
        
        int capacity = 0;
        String sql = "SELECT * FROM \"Area\" WHERE \"areaId\" = ? ";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, 0);
            rs = pstmt.executeQuery();

            if(rs.next())
                capacity = rs.getInt("capacity");

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return capacity;
    }



}
