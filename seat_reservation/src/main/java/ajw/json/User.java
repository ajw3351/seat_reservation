package ajw.json;

import java.util.ArrayList;

public class User {

    String userId;
    ArrayList<Info> infos = new ArrayList<>();

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ArrayList<Info> getInfos() {
        return infos;
    }

    // public void setInfos(ArrayList<Info> infos) {
    //     this.infos = infos;
    // }

    public void addInfo(Info info) {
        infos.add(info);
    }
}
