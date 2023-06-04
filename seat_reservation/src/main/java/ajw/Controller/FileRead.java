package ajw.Controller;

import java.io.FileNotFoundException;
import java.io.FileReader;

import java.io.Reader;

import com.google.gson.Gson;

import ajw.json.User;
import ajw.json.UserDataBase;

public class FileRead {
    private User mUser;
    private UserDataBase mUserDataBase;
    public User ReadFile(String Id) throws FileNotFoundException {

        User tempuser;

        String root = System.getProperty("user.dir");
        String filePath;
        filePath = root + "/seat_reservation/src/main/java/Info/" + Id + ".json";
        Reader reader = new FileReader(filePath);

        Gson gson = new Gson();
        tempuser = gson.fromJson(reader, User.class);

        return tempuser;
    }

    public UserDataBase ReadUserFile(UserDataBase userdDataBase) throws FileNotFoundException {

        UserDataBase tempuser;

        String root = System.getProperty("user.dir");
        String filePath;
        filePath = root + "/seat_reservation/src/main/java/Info/UserInfo.json";
        Reader reader = new FileReader(filePath);

        Gson gson = new Gson();
        tempuser = gson.fromJson(reader, UserDataBase.class);

        return tempuser;
    }
}
