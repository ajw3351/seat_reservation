package ajw.Controller;

import java.util.ArrayList;

import ajw.json.Info;
import ajw.json.User;

public class Controller {

    private static Controller mController = new Controller();

    User mUser;

    Info mInfo = new Info();

    private int price;

    private Controller() {

    }

    public User theEnd() {
        mUser.addInfo(mInfo);
        return mUser;
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

    public void setUserName(String userName) {
        mUser.setUserName(userName);
    }

    public void setGame(String game, String time) {
        mInfo.setTitle(game);
        mInfo.setTime(time);
    }

    public String[] getGame() {
        return new String[] {mInfo.getTitle(), mInfo.getTime()};
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

    public int getPrice(){
        return price;
    }

    public void setSeatNum(ArrayList<String> seatNum){
        mInfo.setSeatList(seatNum);
    }

    public ArrayList<String> getSeatNum(){
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

    public void setSelectInfo(int index){
        mInfo = new Info();
        mInfo = mUser.getInfos().get(index);
    }

    public Info getSelectInfo(){
        return mInfo;
    }
}

