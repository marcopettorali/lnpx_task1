
import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author Dario
 */
@Entity
public class PC implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="PC Number")
    private int pcNumber;
    @Id
    @ManyToOne
    @JoinColumn(name = "Room Name")
    private Room pcRoom;

    @OneToMany(mappedBy="pcBooked")
    private Set<Reservation> reservations;

    public int getPcNumber() {
        return pcNumber;
    }

    public Room getPcRoom() {
        return pcRoom;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setPcNumber(int pcNumber) {
        this.pcNumber = pcNumber;
    }

    public void setPcRoom(Room pcRoom) {
        this.pcRoom = pcRoom;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }
    

    
    public static PC createNewPc(int newPcNumber,Room newpcRoom){
        PC newPC  = new PC();
        newPC.setPcNumber(newPcNumber);
        newPC.setPcRoom(newpcRoom);
        JPAManager.createPC(newPC);
        return newPC;
    }
}

