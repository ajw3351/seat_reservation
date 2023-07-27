package ajw.VO;

import java.util.ArrayList;

public class TicketVO {
    private String bookingNum; //예매번호를 기본키로
    private String userId;
    private int areaId;
    private String totalPrice;
    private String payment;
    private String ticketAddress;
    private String ticketPickUpMethod;
    private ArrayList<Integer> seatId = new ArrayList<>();
    private int gameId;
    
    public String getBookingNum() {
        return bookingNum;
    }
    public void setBookingNum(String bookingNum) {
        this.bookingNum = bookingNum;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public int getAreaId() {
        return areaId;
    }
    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }
    public String getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
    public String getPayment() {
        return payment;
    }
    public void setPayment(String payment) {
        this.payment = payment;
    }
    public String getTicketAddress() {
        return ticketAddress;
    }
    public void setTicketAddress(String ticketAddress) {
        this.ticketAddress = ticketAddress;
    }
    public String getTicketPickUpMethod() {
        return ticketPickUpMethod;
    }
    public void setTicketPickUpMethod(String ticketPickUpMethod) {
        this.ticketPickUpMethod = ticketPickUpMethod;
    }
    public ArrayList<Integer> getSeatId() {
        return seatId;
    }
    public void setSeatId(ArrayList<Integer> seatId) {
        this.seatId = seatId;
    }
    public int getGameId() {
        return gameId;
    }
    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    
}
