package ajw.json;

import java.util.ArrayList;

public class UserDataBase {

    ArrayList<UserInfo> userList = new ArrayList<>();

    public ArrayList<UserInfo> getUserList() {
        return userList;
    }

    public void setUserList(ArrayList<UserInfo> userList) {
        this.userList = userList;
    }

    public void addUserList(UserInfo user) {
        userList.add(user);
    }
}
