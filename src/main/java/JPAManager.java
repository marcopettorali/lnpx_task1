
import java.time.*;
import java.time.format.*;
import java.util.*;
import javax.persistence.*;

public class JPAManager {

    //CHECKED
    private static final String availablePCsQuery = ""
            + "SELECT p.pcNumber,p.pcRoom "
            + "FROM pc p "
            + "WHERE p.pcRoom= :roomname "
            + "      and "
            + "      NOT EXISTS "
            + "          (SELECT * "
            + "           FROM Reservation r "
            + "           WHERE r.pcBooked.id = p.id and b.Date= :date and b.StartTime= :time)";

    private static final String loadUserReservationsQuery = ""
            + "SELECT r "
            + "FROM Reservation r "
            + "WHERE username = :name AND bookingDate >= :date ";
            

    private static final String controlReservationQuery = ""
            + "SELECT count(*) as NumPrenotazioni "
            + "FROM Reservation r "
            + "WHERE r.username=:name and r.startTime=:time and r.bookingDate=:date";

    /**
     * *************************************************
     */
    private static final String availableRoomsQuery = ""
            + "SELECT D.roomName, D.capacity - D.booked as available, D.capacity, D.rowsNumber	"
            + "FROM ( "
            + "      SELECT res.pcBooked.pcRoom as roomName, count(*) as booked ,r.capacity, r.rowsNumber "
            + "      FROM Reservation res inner join Room r on (r.roomName=res.pcBooked.pcRoom) "
            + "      WHERE res.startTime= :time and res.bookingDate= :date	"
            + "	     GROUP BY res.pcBooked.pcRoom "
            + "	    ) as D "
            + "WHERE D.capacity - D.booked > 0 "
            + "   UNION  "
            + "SELECT  r1.roomName, r1.capacity, r1.rowsNumber "
            + "FROM Room r1 "
            + "WHERE r1.roomName NOT IN ( "
            + "      SELECT res1.pcBooked.pcRoom "
            + "      FROM Reservation res1 "
            + "      WHERE res1.StartTime= :time and res1.Date= :date)";

    private static final EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("lnpx_lnpx_task1_jar_1.0-SNAPSHOTPU");
    private static final EntityManager emManager = emFactory.createEntityManager();
    
    public static void close(){
        emManager.close();
        emFactory.close();
    }

    public static void createPC(PC newPC) {
        emManager.getTransaction().begin();
        emManager.persist(newPC);
        emManager.getTransaction().commit();
        
    }

    public static void createRoom(Room newRoom) {
        emManager.getTransaction().begin();
        emManager.persist(newRoom);
        emManager.getTransaction().commit();
    }
    
    public static PC findPc(long id){
       PC ret=emManager.find(PC.class,id);
       return ret;   
    }
    
    public static List<Reservation> loadUserReservations(String username) {
        List<Reservation> ret = null;
        Query q = emManager.createQuery(loadUserReservationsQuery, Reservation.class);

        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String localDate = date.format(formatter);

        q.setParameter("name", username);
        q.setParameter("date", localDate);

        ret = q.getResultList();
        for(Reservation r : ret){
            r.updateTransientFields();
        }
        return ret;

    }

    public static int reservePC(Reservation R) {

        Query q = emManager.createQuery(controlReservationQuery);

        String user = R.getUsername();
        String rTime = R.getStartTime();
        String rDate = R.getBookingDate();

        q.setParameter("name", user);
        q.setParameter("time", rTime);
        q.setParameter("date", rDate);

        long numbRes = (long) q.getSingleResult();
        if (numbRes > 0) {

            return -2;

        }

        
        try {
            emManager.getTransaction().begin();
            emManager.persist(R);
            emManager.getTransaction().commit();
            
        } catch (EntityExistsException eee) {
            System.out.println("The entity alredy exists !");
            return -1;
        }
        return 1;

    }

    public static boolean deleteReservation(Reservation r) {

        try {
            emManager.getTransaction().begin();
            emManager.remove(r);
            emManager.getTransaction().commit();
        } catch (IllegalArgumentException iae) {

            System.out.println("A detached object is passed as parameter instead of persisted Object !");

        }
        return true;

    }
    
    public static List<Room> loadRooms(String date, String time) {
        Query query = emManager.createNativeQuery(JPAManager.availableRoomsQuery, Room.class);
        query.setParameter("time", time);
        query.setParameter("date", date);

        return query.getResultList();
    }

    public static List<PC> loadAvailablePCs(String roomName, String date, String time) {
        Query query = emManager.createNativeQuery(JPAManager.availablePCsQuery, PC.class);
        query.setParameter("roomname", roomName);
        query.setParameter("date", date);
        query.setParameter("time", time);

        return query.getResultList();
    }

}
