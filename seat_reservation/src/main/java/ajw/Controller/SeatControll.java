package ajw.Controller;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import ajw.json.UserDataBase;
import ajw.json.UserInfo;
import ajw.json.User;
import ajw.View.GameInfo;
import ajw.json.Info;

public class SeatControll {
    Controller controller = Controller.getInstance();

    UserDataBase userDataBase = controller.getUserDataBase();
    ArrayList<UserInfo> userList = new ArrayList<>();
    ArrayList<String> reserveSeat = new ArrayList<>();
    String userId;
    FileRead FR = new FileRead();
    User user = new User();

    GameInfo gameInfo = new GameInfo();
    ArrayList<String> gameDate = new ArrayList<>();

    public ArrayList<String> getReserveSeat(String date){
        setReserveSeat(date);
        return reserveSeat;
    }

    public void setReserveSeat(String date){
        gameDate = gameInfo.GameDate();
        userList = userDataBase.getUserList();
        for(UserInfo userInfo : userList){
            userId = userInfo.getId();
            
            try {
                user = FR.ReadFile(userId);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            for(Info info : user.getInfos())
            {
                if(date.equals(info.getTime())){
                    for(String seat : info.getSeatList())
                        reserveSeat.add(seat);
                }                
            }
        }
    }

    
}
