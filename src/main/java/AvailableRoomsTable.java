
import java.util.*;
import javafx.collections.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;

public class AvailableRoomsTable extends TableView<Room> {

    private ObservableList<Room> ObListRoom;
    private String dateDisplayed = "";
    private String timeDisplayed = "";

    AvailableRoomsTable() {
        super();
        TableColumn Room = new TableColumn("Room");
        Room.setCellValueFactory(new PropertyValueFactory<>("roomName"));
        TableColumn Available = new TableColumn("Capacity");
        Available.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        TableColumn Booked = new TableColumn("availablePCs");
        Booked.setCellValueFactory(new PropertyValueFactory<>("availablePCs"));
        this.getColumns().addAll(Room, Available, Booked);
        this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    public void setItems(List<Room> ArrayRooms) {
        ObListRoom = FXCollections.observableArrayList();
        ObListRoom.addAll(ArrayRooms);
        this.getItems().clear();
        this.setItems(ObListRoom);
        this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    public Room getSelected() {
        return this.getSelectionModel().getSelectedItem();
    }

    public void relaseSelection() {
        this.getSelectionModel().clearSelection();
    }

    public void updateRoomsInformation(int row, Room NewRoomInformations) {
        ObListRoom.set(row, NewRoomInformations);
        this.setItems(ObListRoom);
    }

    public void setDateDisplayed(String dateDisplayed) {
        this.dateDisplayed = dateDisplayed;
    }

    public void setTimeDisplayed(String timeDisplayed) {
        this.timeDisplayed = timeDisplayed;
    }

    public String getDateDisplayed() {
        return dateDisplayed;
    }

    public String getTimeDisplayed() {
        return timeDisplayed;
    }
}
