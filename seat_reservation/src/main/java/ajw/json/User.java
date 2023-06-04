package ajw.json;

import java.util.ArrayList;

public class User {

    String userName;
    ArrayList<Info> infos = new ArrayList<>();

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ArrayList<Info> getInfos() {
        return infos;
    }

    public void setInfos(ArrayList<Info> infos) {
        this.infos = infos;
    }

    public void addInfo(Info info) {
        infos.add(info);
    }
}
