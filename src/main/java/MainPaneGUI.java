
import java.time.*;
import java.time.format.*;
import java.util.ArrayList;
import java.util.List;
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
    private Button reserveButton;
    private Label errorLabel;
    private Separator leftSeparator;
    private Label myReservationsLabel;
    private ReservationsTable reservationsTable;
    private Button deleteReservationButton;
    private Separator verticalSeparator;
    private VBox rightVBox;
    private Label availableRoomsLabel;
    private AvailableRoomsTable availableRoomsTable;
    private Pane mapPane;
    private PCIcon[] pcArray = null;

    private void buildDeleteReservationButton() {
        deleteReservationButton = new Button();
        deleteReservationButton.setMnemonicParsing(false);
        deleteReservationButton.setText("Delete reservation");
        deleteReservationButton.setOnAction(e -> {
            errorLabel.setText("");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            Reservation selectedReservation = reservationsTable.getSelected();
            if (selectedReservation != null) {
                
                
                if((mapPane != null)&&(pcArray!=null)){
                    mapPane.getChildren().removeAll(pcArray);
                }
                
                if (PCBookingApplicationController.deleteReservation(selectedReservation)) {
                    
                    List<Reservation> userReservations = PCBookingApplicationController.loadUserReservations(User.username);
                    reservationsTable.setItems(userReservations);
                    reservationsTable.relaseSelection();
                } else {
                    errorLabel.setText("There was an error during the deletion of the reservation.");
                }
            } else {
                errorLabel.setText("Please select one reservation.");
            }
        });
    }

    private void buildReserveButton() {
        reserveButton = new Button();
        reserveButton.setMnemonicParsing(false);
        reserveButton.setText("Reserve");
        reserveButton.setOnAction(e -> {
            errorLabel.setText("");
            if (pcArray != null) {
                mapPane.getChildren().removeAll(pcArray);
                pcArray = null;
            }
            Room selectedRoom = availableRoomsTable.getSelected();
            if (selectedRoom != null) {
                String roomName = selectedRoom.getRoomName();
                int roomCapacity = selectedRoom.getCapacity();
                int rowNumber = selectedRoom.getRowsNumber();
                int availablePCs = selectedRoom.getAvailablePCs();
                int index = availableRoomsTable.getSelectionModel().getFocusedIndex();
                availableRoomsTable.relaseSelection();

                LocalDate date = datePicker.getValue();
                String time = timePicker.getValue() + ":00";

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                DateTimeFormatter dtt = DateTimeFormatter.ofPattern("HH:mm:ss");

                if ((date != null) && (time != null)) {
                    LocalTime l = LocalTime.parse(time);
                    String localTime = l.toString();
                    String dateString = date.format(formatter);

                    List<PC> avaiablePcList = PCBookingApplicationController.loadAvailablePCs(roomName, dateString, time);
                    if (!avaiablePcList.isEmpty()) {
                        int indexPcSelected = avaiablePcList.get(0).getPcNumber();
                        int ret = PCBookingApplicationController.reservePC(User.username, roomName, indexPcSelected, dateString, time);
                        if (ret==1) {
                            selectedRoom.setAvailablePCs(availablePCs - 1);
                            availableRoomsTable.updateRoomsInformation(index, selectedRoom);
                            pcArray = drawMap(rowNumber, roomCapacity, indexPcSelected);
                            mapPane.getChildren().addAll(pcArray);

                            List<Reservation> userReservations = PCBookingApplicationController.loadUserReservations(User.username);
                            reservationsTable.setItems(userReservations);
                        }else if(ret == -1){
                            errorLabel.setText("You have already booked another PC for the specified date and time");
                        }else if(ret == -2){
                            errorLabel.setText("The selected PC is already occupied");
                        }else{
                            errorLabel.setText("An error occured during the reservation. Try again later.");
                        }

                    } else {
                        errorLabel.setText("No PC available in this room.");
                    }
                }

            } else {
                errorLabel.setText("Please select one room.");
            }
        });
    }

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
                if (date.isBefore(LocalDate.now()) || (date.isEqual(LocalDate.now()) && (l.isBefore(LocalTime.now())))) {
                    errorLabel.setText("This program is not able to go back in time");
                } else {
                    errorLabel.setText("");
                    String dateString = date.format(formatter);
                    availableRoomsTable.setItems(PCBookingApplicationController.loadRooms(dateString, time));
                    reserveButton.setDisable(false);
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
        verticalSeparator = new Separator();
        rightVBox = new VBox();
        availableRoomsLabel = new Label();
        availableRoomsTable = new AvailableRoomsTable();
        mapPane = new Pane();

        buildDeleteReservationButton();
        buildReserveButton();
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

        datePicker.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();

                setDisable(empty || date.compareTo(today) < 0);
            }
        });

        timePicker.setPrefWidth(150.0);

        leftSeparator.setPrefWidth(200.0);
        VBox.setMargin(leftSeparator, new Insets(50.0, 0.0, 50.0, 0.0));

        myReservationsLabel.setText("My reservations:");

        VBox.setMargin(reservationsTable, new Insets(20.0, 0.0, 0.0, 0.0));
        List<Reservation> userReservations = PCBookingApplicationController.loadUserReservations(User.username);
        reservationsTable.setItems(userReservations);

        errorLabel.setTextFill(Color.RED);
        errorLabel.setAlignment(Pos.CENTER_LEFT);

        verticalSeparator.setOrientation(javafx.geometry.Orientation.VERTICAL);
        verticalSeparator.setPrefHeight(200.0);

        rightVBox.setMaxWidth(Double.MAX_VALUE);
        rightVBox.setPrefHeight(560.0);
        rightVBox.setPrefWidth(373.0);

        availableRoomsLabel.setText("Available rooms:");

        VBox.setMargin(deleteReservationButton, new Insets(20.0, 0.0, 0.0, 0.0));
        HBox.setMargin(leftVBox, new Insets(20.0));
        VBox.setMargin(availableRoomsTable, new Insets(20.0, 0.0, 0.0, 0.0));
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

    private PCIcon[] drawMap(int rowNumber, int roomCapacity, int indexPcSelected) {
        PCIcon[] pcArray = new PCIcon[roomCapacity];
        int Max = rowNumber;
        double x_offset;
        double y_offset;
        if ((roomCapacity / rowNumber) > rowNumber) {
            Max = roomCapacity / rowNumber;
        }
        double MapSize = mapPane.getWidth();
        if (MapSize > (mapPane.getHeight())) {
            MapSize = mapPane.getHeight();
        }
        y_offset = (mapPane.getHeight()) - MapSize;
        x_offset = (mapPane.getWidth()) - MapSize;
        double Dim = MapSize / (2 * Max);
        for (int i = 0; i < roomCapacity; i++) {
            PCIcon NewPc = new PCIcon(i / rowNumber, i % rowNumber, Dim, i % rowNumber * (roomCapacity / rowNumber) + i / rowNumber + 1, x_offset, y_offset);
            pcArray[i] = NewPc;
            if ((i % rowNumber * (roomCapacity / rowNumber) + i / rowNumber + 1) == indexPcSelected) {
                NewPc.FillYellow();
            }
        }
        return pcArray;
    }
}
