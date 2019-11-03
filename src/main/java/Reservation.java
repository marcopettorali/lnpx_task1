
import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="Start_Time")
    private String startTime;
    @Id
    @Column(name="Date")
    private String bookingDate;
    @Id
    @ManyToOne
    private PC pcBooked; 
    
    //@ManyToOne
    //@JoinColumn
    @Column(name="Username")
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

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public void setPcBooked(PC pcBooked) {
        this.pcBooked = pcBooked;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    
}
