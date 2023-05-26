package ajw.Controller;

import java.io.FileWriter;

import java.io.IOException;
import com.google.gson.Gson;

import ajw.json.User;

public class FileWrite {
    private User mUser;
    public void FileWrite(User user)throws IOException{
        Gson gson = new Gson();
        
        mUser = user;

        //파일에 저장
        String root = System.getProperty("user.dir");
        String filePath;
        filePath = root + "/seat_reservation/src/main/java/Info/" + this.mUser.getUserName() + ".json";
        FileWriter fw = new FileWriter(filePath);
        gson.toJson(user,fw);

        fw.flush();
        fw.close(); 
    }
}
