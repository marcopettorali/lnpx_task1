
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
            + "           WHERE r.pcBooked.id = p.id and b.Date= :date and b.StartTime= :time)"
            + ";";

    private static final String loadUserReservationsQuery = ""
            + "SELECT r "
            + "FROM Reservation r "
            + "WHERE username = :name AND bookingDate >= :date "
            + ";";

    private static final String controlReservationQuery = ""
            + "SELECT count(*) as NumPrenotazioni "
            + "FROM Reservation r "
            + "WHERE r.username=:name and r.startTime=:time and r.bookingDate=:date"
            + ";";

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
            + "      WHERE res1.StartTime= :time and res1.Date= :date)"
            + ";";

    private static final EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("lnpx_lnpx_task1_jar_1.0-SNAPSHOTPU");
    private static final EntityManager emManager = emFactory.createEntityManager();

    public static void JPAStart() {
        emManager.getTransaction().begin();
    }

    public static void JPAStop() {
        emManager.getTransaction().commit();
        emManager.close();
    }

    public static void createPC(PC newPC) {
        emManager.persist(newPC);
    }

    public static void createRoom(Room newRoom) {
        emManager.persist(newRoom);
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

        int numbRes = (int) q.getSingleResult();
        if (numbRes > 0) {

            return 0;

        }

        emManager.getTransaction().begin();
        try {
            emManager.persist(R);
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
            /* If the emManager.remove(r) removes nothing because it wants a persisted object
          we have to previously find the target object and after remove it
             */
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
