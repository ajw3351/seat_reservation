package ajw.Controller;

import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

import ajw.json.User;
import ajw.json.UserDataBase;

public class FileWrite {
    private User mUser;
    private UserDataBase mUserDataBase;
    public void FileWrite(User user, String Id)throws IOException{
        Gson gson = new Gson();
        
        mUser = user;

        //파일에 저장
        String root = System.getProperty("user.dir");
        String filePath;
        filePath = root + "/seat_reservation/src/main/java/Info/" + Id + ".json";
        FileWriter fw = new FileWriter(filePath);
        gson.toJson(user,fw);

        fw.flush();
        fw.close(); 
    }

    public void FileWrite(UserDataBase userDataBase) throws IOException{
        Gson gson = new Gson();
        
        mUserDataBase = userDataBase;

        //파일에 저장
        String root = System.getProperty("user.dir");
        String filePath;
        filePath = root + "/seat_reservation/src/main/java/Info/UserInfo.json";
        FileWriter fw = new FileWriter(filePath);
        gson.toJson(userDataBase,fw);

        fw.flush();
        fw.close(); 
    }
}
