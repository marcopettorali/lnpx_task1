
import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="startTime")
    private String startTime;
    @Id
    @Column(name="bookingDate")
    private String bookingDate;
    @Id
    @ManyToOne
    private PC pcBooked; 
    
    @Transient
    private String roomN;

    @Transient
    private int pcnumb;

    //@ManyToOne
    //@JoinColumn
    @Column(name="username")
    private String username;

    public String getStartTime() {
        return startTime;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public PC getPcBooked() {
        return pcBooked;
    }

    public String getUsername() {
        return username;
    }
    
    public int getPcnumb() {
        return pcnumb;
    }
    
    public String getroomN() {
        return roomN;
    }
    
    
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public void setPcBooked(PC pcBooked) {
        this.pcBooked = pcBooked;
    }
    
    public void updateTransientFields(){
        this.roomN = pcBooked.getPcRoom().getRoomName();
        this.pcnumb = pcBooked.getPcNumber();
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setPcnumb(int pcnumb) {
        this.pcnumb = pcnumb;
    }
    
     public void setRoomName(String roomName) {
        this.roomN = roomName;
    }
     
}
