
import java.util.*;
import javafx.collections.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class AvailableRoomsTable extends TableView<Room_bean> {

    private ObservableList<Room_bean> ObListRoom;

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

    public void setItems(List<Room_bean> ArrayRooms) {
        ObListRoom = FXCollections.observableArrayList();
        ObListRoom.addAll(ArrayRooms);
        this.setItems(ObListRoom);
        this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    public Room_bean getSelected() {
        return this.getSelectionModel().getSelectedItem();
    }

    public void relaseSelection() {
        this.getSelectionModel().clearSelection();
    }

    public void updateRoomsInformation(int row, Room_bean NewRoomInformations) {
        ObListRoom.set(row, NewRoomInformations);
        this.setItems(ObListRoom);
    }

}
