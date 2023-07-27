package ajw.VO;

public class GameVO {
    private int gameId;
    private String title;
    private String date;
    private String time;
    private String homeImg;
    public String getHomeImg() {
        return homeImg;
    }
    public void setHomeImg(String homeImg) {
        this.homeImg = homeImg;
    }
    private String awayImg;

    public String getAwayImg() {
        return awayImg;
    }
    public void setAwayImg(String awayImg) {
        this.awayImg = awayImg;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public int getGameId() {
        return gameId;
    }
    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
