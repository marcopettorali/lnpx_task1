
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author dcoll
 */
@Entity
public class PC implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private int pcNumber;
    @Id
    private String roomName;

    public void setPcNumber(int pcNumber) {
        this.pcNumber = pcNumber;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getPcNumber() {
        return pcNumber;
    }

    public String getRoomName() {
        return roomName;
    }
    
    public static PC createNewPc(int newPcNumber,String newRoomName){
        PC newPC  = new PC();
        newPC.setPcNumber(newPcNumber);
        newPC.setRoomName(newRoomName);
        JPAManager.createPC(newPC);
        return newPC;
    }
}

