
import java.time.*;
import java.time.format.*;
import java.util.ArrayList;
import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import static javafx.scene.layout.Region.USE_PREF_SIZE;
import javafx.scene.paint.Color;

public class MainPaneGUI extends HBox {

    private VBox leftVBox;
    private VBox searchVBox;
    private DatePicker datePicker;
    private ComboBox timePicker;
    private Button searchButton;
    private Label errorLabel;
    private Separator leftSeparator;
    private Label myReservationsLabel;
    private ReservationsTable reservationsTable;
    private Button deleteReservationButton;
    private Separator verticalSeparator;
    private VBox rightVBox;
    private Label availableRoomsLabel;
    private AvailableRoomsTable availableRoomsTable;
    private Button reserveButton;
    private Pane mapPane;

    private void buildSearchButton() {
        searchButton = new Button();
        searchButton.setMaxWidth(Double.MAX_VALUE);
        searchButton.setMnemonicParsing(false);
        searchButton.setText("SEARCH");
        searchButton.setOnAction(e -> {
            LocalDate date = datePicker.getValue();
            String time = timePicker.getValue() + ":00";

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter dtt = DateTimeFormatter.ofPattern("HH:mm:ss");

            if ((date != null) && (time != null)) {
                LocalTime l = LocalTime.parse(time);
                if (date.isEqual(LocalDate.now()) && (l.isBefore(LocalTime.now()))) {
                    errorLabel.setText("This program is not able to go back in time");

                } else {
                    String dateString = date.format(formatter);
                    PCBookingApplicationController.loadRooms(dateString, time);
                }
            } else {
                errorLabel.setText("Please, select date and time first");
            }
        });
    }

    public MainPaneGUI() {

        leftVBox = new VBox();
        searchVBox = new VBox();
        datePicker = new DatePicker();
        timePicker = new ComboBox(FXCollections.observableArrayList("08:30", "09:30", "10:30", "11:30", "12:30", "13:30", "14:30", "15:30", "16:30", "17:30"));
        errorLabel = new Label("");
        leftSeparator = new Separator();
        myReservationsLabel = new Label();
        reservationsTable = new ReservationsTable();
        deleteReservationButton = new Button();
        verticalSeparator = new Separator();
        rightVBox = new VBox();
        availableRoomsLabel = new Label();
        availableRoomsTable = new AvailableRoomsTable();
        reserveButton = new Button();
        mapPane = new Pane();

        buildSearchButton();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(600.0);
        setPrefWidth(800.0);

        leftVBox.setPrefHeight(560.0);
        leftVBox.setPrefWidth(363.0);

        searchVBox.setMaxHeight(USE_PREF_SIZE);
        searchVBox.setMaxWidth(USE_PREF_SIZE);
        searchVBox.setMinHeight(USE_PREF_SIZE);
        searchVBox.setMinWidth(USE_PREF_SIZE);
        searchVBox.setPrefHeight(113.0);
        searchVBox.setPrefWidth(146.0);
        searchVBox.setSpacing(15.0);

        timePicker.setPrefWidth(150.0);

        leftSeparator.setPrefWidth(200.0);
        VBox.setMargin(leftSeparator, new Insets(50.0, 0.0, 50.0, 0.0));

        myReservationsLabel.setText("My reservations:");

        VBox.setMargin(reservationsTable, new Insets(20.0, 0.0, 0.0, 0.0));
        ArrayList<Reservation> userReservations = DBManager.loadUserReservations(User.username);
        reservationsTable.setItems(userReservations);

        deleteReservationButton.setMnemonicParsing(false);
        deleteReservationButton.setText("Delete reservation");
        VBox.setMargin(deleteReservationButton, new Insets(20.0, 0.0, 0.0, 0.0));
        HBox.setMargin(leftVBox, new Insets(20.0));

        errorLabel.setTextFill(Color.RED);
        errorLabel.setAlignment(Pos.CENTER_LEFT);

        verticalSeparator.setOrientation(javafx.geometry.Orientation.VERTICAL);
        verticalSeparator.setPrefHeight(200.0);

        rightVBox.setMaxWidth(Double.MAX_VALUE);
        rightVBox.setPrefHeight(560.0);
        rightVBox.setPrefWidth(373.0);

        availableRoomsLabel.setText("Available rooms:");

        VBox.setMargin(availableRoomsTable, new Insets(20.0, 0.0, 0.0, 0.0));

        reserveButton.setMnemonicParsing(false);
        reserveButton.setText("Reserve");
        VBox.setMargin(reserveButton, new Insets(20.0, 0.0, 0.0, 0.0));

        mapPane.setId("mapPane");
        mapPane.setPrefHeight(400.0);
        mapPane.setPrefWidth(200.0);
        VBox.setMargin(mapPane, new Insets(50.0, 0.0, 0.0, 0.0));

        searchVBox.getChildren().add(datePicker);
        searchVBox.getChildren().add(timePicker);
        searchVBox.getChildren().add(searchButton);
        leftVBox.getChildren().add(searchVBox);
        leftVBox.getChildren().add(errorLabel);
        leftVBox.getChildren().add(leftSeparator);
        leftVBox.getChildren().add(myReservationsLabel);
        leftVBox.getChildren().add(reservationsTable);
        leftVBox.getChildren().add(deleteReservationButton);
        getChildren().add(leftVBox);
        getChildren().add(verticalSeparator);
        rightVBox.getChildren().add(availableRoomsLabel);
        rightVBox.getChildren().add(availableRoomsTable);
        rightVBox.getChildren().add(reserveButton);
        rightVBox.getChildren().add(mapPane);
        getChildren().add(rightVBox);

        reserveButton.setDisable(true);

    }
}
