
import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="Start Time")
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

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getBookingDate() {
        return bookingDate;
    }  
    
}
