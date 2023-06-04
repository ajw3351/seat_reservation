package ajw.Controller;

public class LoginSystem {
    private static LoginSystem mLoginSystem = new LoginSystem();
    
    String ID;
    public String getID() {
        return ID;
    }
    public void setID(String iD) {
        ID = iD;
    }
    public LoginSystem(){

    }

    public static LoginSystem getInstance() {
        return mLoginSystem;
    }


}
