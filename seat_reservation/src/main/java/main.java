import ajw.Controller.Controller;
import ajw.View.LoginScreen;
import ajw.json.User;

public class main {
    public static void main(String[] args)
    {   
        // View
        new LoginScreen();

        // Model
        User user = new User();

        // Controller
        Controller.getInstance().setUser(user);



        


        //new SelectSeatArea("sf");
        //new SelectSeat(0,"sdg",15000);
        //new Reservation();







        // User user = new User();

        // user.setUserName("천민서");

        // //new ReservationDetails(user);

        // Book book1 = new Book();

        // Info asdf = book1.getInfo();

        // ArrayList<String> asss = asdf.getSeatList();

        // Info info1 = new Info();

        // ArrayList<String> seatList = new ArrayList<>();

        // seatList.add("좌석 1-1");

        // info1.setSeatList(seatList);

        // book1.setInfo(info1);

        // ArrayList<Book> bookList = new ArrayList<>();

        // bookList.add(book1);

        // user.setBooks(bookList);

        // UserDataBase database = new UserDataBase();

        // ArrayList<User> userList = new ArrayList<>();

        // userList.add(user);

        // database.setUserList(userList);

        // Gson gson = new Gson();

        // String jsonData = gson.toJson(user);   

        // System.out.println(jsonData);
    }
}
