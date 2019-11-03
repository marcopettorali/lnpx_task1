
import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;


@Entity
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="Room_Name")
    private String roomName;
    
    @Column(name="Capacity")
    private int capacity;
    
    @Column(name="Rows_Number")
    private int rowsNumber;
    
    @OneToMany(mappedBy="pcRoom")
    private Set<PC> PCs;

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
    
    public static Room createNewRoom(String newRoomName,int newRoomCapacity,int newRowsNumber){
        Room newRoom  = new Room();
        newRoom.setRoomName(newRoomName);
        newRoom.setCapacity(newRoomCapacity);
        newRoom.setRowsNumber(newRowsNumber);
        JPAManager.createRoom(newRoom);
        return newRoom;
    }
}
