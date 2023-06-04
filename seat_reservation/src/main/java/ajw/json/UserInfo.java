package ajw.json;

public class UserInfo {
    String Id;
    String Pwd;
    String name;
    String email;
    String phoneNum;

    public UserInfo() {

    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return Pwd;
    }

    public void setPwd(String pwd) {
        Pwd = pwd;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

}
