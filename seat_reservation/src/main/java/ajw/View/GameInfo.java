package ajw.View;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class GameInfo {

    private String game; // 나중에 데이터베이스나 파일입출력으로 라벨에 값 입력하는 방법으로
    private ArrayList<String> date = new ArrayList<>();
    private String time; // 나중에 데이터베이스나 파일입출력으로 라벨에 값 입력하는 방법으로

    private ImageIcon image;// 나중에 데이터베이스나 파일입출력으로 라벨에 값 입력하는 방법으로

    private String stadium;// 나중에 데이터베이스나 파일입출력으로 라벨에 값 입력하는 방법으로

    private ArrayList<ImageIcon> imageList = new ArrayList<>();

    public GameInfo() {
        super();
    }

    public String GameInfoTitle(String game, String date, String time) {
        String title = game + " " + date + " " + time;
        return title;
    }

    public String GameTeam() { // ()안에 int index 추가예정
        // 변수 game에 값을 넣는 코드
        game = "두산 VS 키움 ";
        return game;
    }

    public String GameTime() { // ()안에 int index 추가예정
        // 변수 time에 값을 넣는 코드
        time = "18 : 30";
        return time;
    }

    public ArrayList<String> GameDate() { // ()안에 int index 추가예정
        // 변수 date에 값을 넣는 코드
        date.add("2023년 04월 11일");
        date.add("2023년 04월 12일");
        date.add("2023년 04월 13일");
        return date;
    }

    public ArrayList<ImageIcon> GameTeamImage() { // ()안에 int index 추가예정
        // 변수 imgae에 값을 넣는 코드
        image = new ImageIcon("C:\\Users\\ajw1\\Desktop\\ee\\키움.jpg");
        imageList.add(image);
        image = new ImageIcon("C:\\Users\\ajw1\\Desktop\\ee\\두산.jpg");
        imageList.add(image);
        return imageList;
    }

    public String GamePlace() {
        stadium = "잠실야구장";
        return stadium;
    }

}
