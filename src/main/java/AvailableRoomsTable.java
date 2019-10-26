
import java.util.*;
import javafx.collections.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class AvailableRoomsTable extends TableView<Room> {

    private ObservableList<Room> ObListRoom;

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

    public void FillTableAvailableRooms(List<Room> ArrayRooms) {
        ObListRoom = FXCollections.observableArrayList();
        ObListRoom.addAll(ArrayRooms);
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

}
