
import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

@Entity
public class Room implements Serializable {

    @Id
    @Column(name = "roomName")
    private String roomName;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "rowsNumber")
    private int rowsNumber;

    @OneToMany(mappedBy = "pcRoom")
    private Set<PC> PCs;

    @Transient
    private int availablePCs;

    public int getAvailablePCs() {
        return availablePCs;
    }

    public String getRoomName() {
        return roomName;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getRowsNumber() {
        return rowsNumber;
    }

    public Set<PC> getPCs() {
        return PCs;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setRowsNumber(int rowsNumber) {
        this.rowsNumber = rowsNumber;
    }

    public void setPCs(Set<PC> PCs) {
        this.PCs = PCs;
    }

    public void setAvailablePCs(int availablePCs) {
        this.availablePCs = availablePCs;
    }

    public static Room createNewRoom(String newRoomName, int newRoomCapacity, int newRowsNumber) {
        Room newRoom = new Room();
        newRoom.setRoomName(newRoomName);
        newRoom.setCapacity(newRoomCapacity);
        newRoom.setRowsNumber(newRowsNumber);
        JPAManager.createRoom(newRoom);
        return newRoom;
    }
}
