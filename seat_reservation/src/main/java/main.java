
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import com.google.gson.Gson;

import ajw.Controller.Controller;
import ajw.View.LoginScreen;
import ajw.json.User;
import ajw.json.UserDataBase;

public class main {

    public static void main(String[] args) {
        // View
        new LoginScreen();

        // Model
        User user = new User();
            
        // Controller
        Controller.getInstance().setUser(user);

    }
}
