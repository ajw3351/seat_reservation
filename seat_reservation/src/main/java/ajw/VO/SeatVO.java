package ajw.VO;

public class SeatVO {
    private int seatId; // 기본키 하나면 seatID 만들고
    private int areaId; // 기본키 2개 가능하면 areaId,seatNumber로 좌석 구분
    private String seatNumber;
    private int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }
    
}
