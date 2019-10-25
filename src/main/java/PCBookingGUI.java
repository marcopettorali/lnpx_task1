
import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.*;

/**
 * PCBookingGUI is the main class of the application. It builds the GUI of the
 * system and calls the method when click events are fired on the user
 *
 * @author Marco
 */
public class PCBookingGUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(new Scene(new Group(new LoginPaneGUI())));
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
