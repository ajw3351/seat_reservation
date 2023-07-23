package ajw;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
 
public class PostgreSQL {
    private static final String url = "jdbc:postgresql://localhost:5432/baseball";
    private static final String user = "postgres";
    private static final String password = "wwert50280867";
    Connection connection;

    private static final PostgreSQL mInstance = new PostgreSQL();

    public static PostgreSQL getInstance(){
        return mInstance;
    }

    public Connection connectPostgreSQL(){
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("연결 성공");
        } catch (Exception e) {
            System.out.println("연결 실패");
        }
        return connection;
    } 

    public ArrayList<String> Change_arrayList(Array array){
        ArrayList<String> arrList = new ArrayList<>();
        if(array != null){
            try {
                String[] stringArray = (String[]) array.getArray();
                arrList.addAll(Arrays.asList(stringArray));
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        return arrList;
    }
  
}