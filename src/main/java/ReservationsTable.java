
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
        Room.setCellValueFactory(new PropertyValueFactory<>("room"));
        TableColumn Day = new TableColumn("PC");
        Day.setCellValueFactory(new PropertyValueFactory<>("PCnumber"));
        TableColumn Hour = new TableColumn("Date");
        Hour.setCellValueFactory(new PropertyValueFactory<>("date"));
        TableColumn PC = new TableColumn("Hour");
        PC.setCellValueFactory(new PropertyValueFactory<>("hour"));
        this.getColumns().addAll(Room, Day, Hour, PC);
        this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    /*
    Questa funzione inserisce all'interno della TabellaPrenotazioni un array 
        di reservation che dovrà essere riempito da DBManager.
     */
    public void FillTableReservations(ArrayList<Reservation> ArrayReservation) {
        ReservationList = FXCollections.observableArrayList();
        for (int i = 0; i < ArrayReservation.size(); i++) {
            ReservationList.add(ArrayReservation.get(i));
        }
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
