package ajw.json;

import java.util.ArrayList;

public class Info {
    String title;
    String bookingNum;
    String time;
    String price;
    String payment;
    String getTicket;
    String area;
    ArrayList<String> seatList = new ArrayList<>();

    public Info() {
        
    }

    public Info(String title, String time, String price, String bookingNum, String payment, String getTicket, String area) {
        this.title = title;
        this.time = time;
        this.price = price;
        this.bookingNum = bookingNum;
        this.payment = payment;
        this.getTicket = getTicket;
        this.area = area;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBookingNum() {
        return bookingNum;
    }

    public void setBookingNum(String number) {
        this.bookingNum = number;
    }

    public String getGetTicket() {
        return getTicket;
    }

    public void setGetTicket(String rx) {
        this.getTicket = rx;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public ArrayList<String> getSeatList() {
        return seatList;
    }

    public void setSeatList(ArrayList<String> seatList) {
        this.seatList = seatList;
    }
}
