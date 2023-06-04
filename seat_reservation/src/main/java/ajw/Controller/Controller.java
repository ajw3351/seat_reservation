package ajw.Controller;

import java.util.ArrayList;

import ajw.json.Info;
import ajw.json.User;
import ajw.json.UserDataBase;
import ajw.json.UserInfo;

public class Controller {

    private static Controller mController = new Controller();

    User mUser;
    UserDataBase mUserDataBase = new UserDataBase();
    UserInfo mUserInfo;
    String mUserId;
    String mUserPwd;
    
    String mAuthenticationCode;

    

    Info mInfo = new Info();

    private int price;


    public String getAuthenticationCode() {
        return mAuthenticationCode;
    }

    public void setAuthenticationCode(String AuthenticationCode) {
        this.mAuthenticationCode = AuthenticationCode;
    }

    public String getmUserId() {
        return mUserId;
    }

    public void setmUserId(String userId) {
        this.mUserId = userId;
    }

    // public String getmUserPwd() {
    //     return mUserPwd;
    // }

    // public void setmUserPwd(String mUserPwd) {
    //     this.mUserPwd = mUserPwd;
    // }

    private Controller() {

    }

    public User theEnd() {
        mUser.addInfo(mInfo);
        return mUser;
    }

    public void setUserInfo(UserInfo userInfo) {
        mUserInfo = new UserInfo();
        mUserInfo = userInfo;
    }

    public UserInfo getUserInfo(){
        return mUserInfo;
    }

    public UserDataBase addUserInfo() {
        mUserDataBase.addUserList(mUserInfo);
        return mUserDataBase;
    }

    public void clearUser() {
        mUser = new User();
    }

    public static Controller getInstance() {
        return mController;
    }

    public void setUser(User user) {
        mUser = new User();
        this.mUser = user;
    }

    public User getUser() {
        return mUser;
    }

    public UserDataBase getUserDataBase() {
        return mUserDataBase;
    }

    public void setUserDataBase(UserDataBase userDataBase) {
        this.mUserDataBase = userDataBase;
    }

    public void setUserName(String userName) {
        mUser.setUserName(userName);
    }

    public void setGame(String game, String time) {
        mInfo.setTitle(game);
        mInfo.setTime(time);
    }

    public String[] getGame() {
        return new String[] { mInfo.getTitle(), mInfo.getTime() };
    }

    public void clearGame() {

    }

    public void setArea(String area) {
        mInfo.setArea(area);
    }

    public String getArea() {
        return mInfo.getArea();
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setTotalPrice(int people) {
        int totalPrice = people * price;
        mInfo.setPrice(Integer.toString(totalPrice));
    }

    public String getTotalPrice() {
        return mInfo.getPrice();
    }

    public void setSeatNum(ArrayList<String> seatNum) {
        mInfo.setSeatList(seatNum);
    }

    public ArrayList<String> getSeatNum() {
        return mInfo.getSeatList();
    }

    public void setBookingNum(String num) {
        mInfo.setBookingNum(num);
    }

    public String getBookingNum() {
        return mInfo.getBookingNum();
    }

    public void set_GetTicket(String getTicket) {
        mInfo.setGetTicket(getTicket);
    }

    public String get_GetTicket() {
        return mInfo.getGetTicket();
    }

    public void setPayment(String payment) {
        mInfo.setPayment(payment);
    }

    public String getPayment() {
        return mInfo.getPayment();
    }

    public void clear() {
        mInfo = new Info();
    }

    public void setSelectInfo(int index) {
        mInfo = new Info();
        mInfo = mUser.getInfos().get(index);
    }

    public Info getSelectInfo() {
        return mInfo;
    }

    public void setTicketAddress(String address){
        mInfo.setTicketAddress(address);
    }

    public String getTicketAddress(){
        return mInfo.getTicketAddress();
    }

}
