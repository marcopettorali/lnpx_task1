
import java.util.*;
import javafx.collections.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ReservationsTable extends TableView<Reservation> {

    private ObservableList<Reservation> ReservationList;

    ReservationsTable() {
        super();
        TableColumn Username = new TableColumn("Username");
        Username.setCellValueFactory(new PropertyValueFactory<>("username"));
        TableColumn Room = new TableColumn("Room");
        Room.setCellValueFactory(new PropertyValueFactory<>("roomName"));
        TableColumn Day = new TableColumn("PC");
        Day.setCellValueFactory(new PropertyValueFactory<>("pcBooked"));
        TableColumn Hour = new TableColumn("Date");
        Hour.setCellValueFactory(new PropertyValueFactory<>("bookingDate"));
        TableColumn PC = new TableColumn("Hour");
        PC.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        this.getColumns().addAll(Day, Hour, PC);
        this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    public void setItems(List<Reservation> ArrayReservation) {
        ReservationList = FXCollections.observableArrayList();
        ReservationList.addAll(ArrayReservation);
        this.setItems(ReservationList);
        this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    public Reservation getSelected() {
        return this.getSelectionModel().getSelectedItem();
    }

    public void relaseSelection() {
        this.getSelectionModel().clearSelection();
    }

}
