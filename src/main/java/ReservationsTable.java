
import java.util.*;
import javafx.collections.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ReservationsTable extends TableView<Reservation> {

    private ObservableList<Reservation> ReservationList;

    ReservationsTable() {
        super();
        
        TableColumn Room = new TableColumn("Room");
        Room.setCellValueFactory(new PropertyValueFactory<>("roomN"));
        
        TableColumn PC = new TableColumn("PC");
        PC.setCellValueFactory(new PropertyValueFactory<>("pcnumb"));
        
        TableColumn Date = new TableColumn("Date");
        Date.setCellValueFactory(new PropertyValueFactory<>("bookingDate"));
        
        TableColumn Hour = new TableColumn("Hour");
        Hour.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        
        this.getColumns().addAll(Room,PC,Date,Hour);
        this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    public void setItems(List<Reservation> ArrayReservation) {
        ReservationList = FXCollections.observableArrayList();
        ReservationList.addAll(ArrayReservation);
        this.getItems().clear();
        this.setItems(ReservationList);
        this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    public Reservation getSelected(){ 
        return this.getSelectionModel().getSelectedItem();
    }

    public void relaseSelection() {
        this.getSelectionModel().clearSelection();
    }

}
