
import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author Dario
 */
@Entity
public class PC implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pcId")
    private long pcId;

    @Column(name = "pcNumber")
    private int pcNumber;

    @Column(name = "usageStatistics")
    private int usageStatistics;

    @ManyToOne
    @JoinColumn(name = "pcRoom")
    private Room pcRoom;

    @OneToMany(mappedBy = "pcBooked")
    private Set<Reservation> reservations;

    public int getUsageStatistics() {
        return usageStatistics;
    }

    public void setUsageStatistics(int usageStatistics) {
        this.usageStatistics = usageStatistics;
    }

    public long getPcId() {
        return pcId;
    }

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

    public void updatePCStat(int i) {
        int stat = getUsageStatistics();
        setUsageStatistics(stat + i);
        JPAManager.updatePC(this);
    }

    public static PC createNewPc(int newPcNumber, Room newpcRoom) {
        PC newPC = new PC();
        newPC.setPcNumber(newPcNumber);
        newPC.setPcRoom(newpcRoom);
        newPC.setUsageStatistics(0);
        JPAManager.createPC(newPC);
        return newPC;
    }
}
