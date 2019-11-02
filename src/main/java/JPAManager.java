import java.util.*;
import javax.persistence.*;

public class JPAManager {
    private static final String queryAvailableRooms = ""
            + "select D.RoomName,D.Capacity - D.Booked as Available, D.Capacity, D.RowNumber	"
            + "from (   "
            + "          select b.RoomName, count(*) as Booked ,r.Capacity, r.RowNumber"
            + "          from booking b inner join room r on (r.RoomName=b.RoomName)"
            + "	    where b.StartTime= :time and b.Date= :date	"
            + "	    group by b.RoomName "
            + "		) as D "
            + "where D.Capacity - D.Booked > 0 "
            + " union  select  r1.RoomName, r1.Capacity,r1.Capacity,r1.RowNumber "
            + "   from room r1 "
            + "   where r1.roomName not in (select b1.roomName"
            + "			         from booking b1"
            + "                             where b1.StartTime= :time and b1.Date= :date);";
    
    private static final EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("lnpx_lnpx_task1_jar_1.0-SNAPSHOTPU");
    private static final EntityManager emManager = emFactory.createEntityManager();
    public static void createPC(PC newPC){
        emManager.persist(newPC);
        emManager.getTransaction().commit();
    } 
    
    public static List<Room> loadRooms(String date, String time){
        TypedQuery<Room> = emManager.createNativeQuery(JPAManager.queryAvailableRooms, Room.class).setParameter("time", time).setParameter("date", date);
        
        
    }
}
