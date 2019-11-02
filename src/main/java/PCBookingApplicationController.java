
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.*;

/**
 * PCBookingGUI is the main class of the application. It builds the GUI of the
 * system. It's the application controller.
 *
 * @author Marco
 */
public class PCBookingApplicationController extends Application {

    private static Stage loginStage;
    private static Stage mainStage;

    private static void loadLoginPane() {
        loginStage = new Stage();
        loginStage.setTitle("Login");
        loginStage.setResizable(false);
        loginStage.setScene(new Scene(new Group(new LoginPaneGUI())));
        loginStage.sizeToScene();
        loginStage.show();
    }

    private static void loadMainPane() {
        loginStage.close();

        mainStage = new Stage();
        mainStage.setTitle("PC Booking");
        mainStage.setResizable(false);
        mainStage.setScene(new Scene(new Group(new MainPaneGUI())));
        mainStage.sizeToScene();
        mainStage.show();
    }

    @Override
    public void start(Stage primaryStage) {
        loadLoginPane();
    }

    public static boolean login(String username, String password) {
        if (DBManager.checkLogin(username, password)) {
            loadMainPane();
            return true;
        } else {
            return false;
        }
    }

    public static List<Room> loadRooms(String date, String time) {
        List<Room> rooms = DBManager.loadRooms(date, time);
        return rooms;
    }

    public static List<PC_bean> loadAvaiablePCs(String room, String date, String time) {
        List<PC_bean> PCs = DBManager.loadAvailablePCs(room, date, time);
        return PCs;
    }

    public static int reservePC(String user, String room, int pcnumb, String date, String time) {
        return DBManager.reservePC(user, room, pcnumb, date, time);
    }

    public static boolean deleteReservation(String user, String room, int pcnumb, String date, String time) {
        return DBManager.deleteReservation(user, room, pcnumb, date, time);
    }

    public static ArrayList<Reservation> loadUserReservations(String user) {
        return DBManager.loadUserReservations(user);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        /* Tentativo di inserimento di un PC*/
        PC.createNewPc(0, "SI 1");
    }

}
