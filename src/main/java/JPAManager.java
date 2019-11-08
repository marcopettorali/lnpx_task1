
import java.math.*;
import java.time.*;
import java.time.format.*;
import java.util.*;
import javax.persistence.*;

public class JPAManager {
    
    private static final EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("lnpx_lnpx_task1_jar_1.0-SNAPSHOTPU");
    private static final EntityManager emManager = emFactory.createEntityManager();

    private static final String availablePCsQuery = ""
            + "SELECT * "
            + "FROM PC p "
            + "WHERE p.pcRoom= :roomname "
            + "      and "
            + "      NOT EXISTS "
            + "          (SELECT * "
            + "           FROM Reservation r inner join PC p on (r.pcBooked_pcId = p.pcId)"
            + "           WHERE r.bookingDate= :date and r.startTime= :time and p.pcRoom= :roomname )";

    private static final String loadUserReservationsQuery = ""
            + "SELECT r "
            + "FROM Reservation r "
            + "WHERE username = :name AND bookingDate >= :date ";

    private static final String controlReservationQuery = ""
            + "SELECT count(*) as NumPrenotazioni "
            + "FROM Reservation r "
            + "WHERE r.username=:name and r.startTime=:time and r.bookingDate=:date";

    private static final String loadRoomsQuery = ""
            + "SELECT r "
            + "FROM Room r ";

    private static final String availablePCInARoomQuery = ""
            + "SELECT count(*) as CountReservations "
            + "FROM Reservation r INNER JOIN PC p ON (r.pcBooked_pcId = p.pcId) "
            + "WHERE r.startTime = :time AND r.bookingDate = :date AND p.pcRoom = :room ";

    public static void close() {
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

    public static PC findPcById(long id) {
        PC ret = emManager.find(PC.class, id);
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
        for (Reservation r : ret) {
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
        Query loadRoomsQ = emManager.createQuery(JPAManager.loadRoomsQuery, Room.class);
        List<Room> ret = loadRoomsQ.getResultList();

        for (Room room : ret) {
            Query query = emManager.createNativeQuery(JPAManager.availablePCInARoomQuery);
            query.setParameter("time", time);
            query.setParameter("date", date);
            query.setParameter("room", room);
            int temp = ((BigInteger) query.getSingleResult()).intValue();
            room.setAvailablePCs(room.getCapacity() - temp);

        }

        System.out.println(date + " " + time);
        return ret;
    }

    public static List<PC> loadAvailablePCs(String roomName, String date, String time) {
        System.out.println(roomName + " " + date + " " + time);
        Query query = emManager.createNativeQuery(JPAManager.availablePCsQuery, PC.class);
        query.setParameter("roomname", roomName);
        query.setParameter("date", date);
        query.setParameter("time", time);
        List<PC> ret = query.getResultList();
        System.out.println(ret);
        return ret;
    }

    public static void updatePC(PC p) {
        emManager.getTransaction().begin();
        emManager.merge(p);
        emManager.getTransaction().commit();
    }

}
