import java.time.*;
import java.time.format.*;
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
    
    private static final String loadUserReservationsQuery = "SELECT b FROM booking b WHERE Username = :name AND Date >= :date";

    private static final String queryControlReservations = ""
            + "select count(*) as NumPrenotazioni "
            + "from booking b "
            + "where b.username=:name and b.StartTime=:time and b.date=:date;";
    
    private static final EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("lnpx_lnpx_task1_jar_1.0-SNAPSHOTPU");
    private static final EntityManager emManager = emFactory.createEntityManager();
    
    public static void JPAStart(){
        emManager.getTransaction().begin();
    }
    
    public static void JPAStop(){
        emManager.close();
    }
    
    
    public static void createPC(PC newPC){
        emManager.persist(newPC);
        emManager.getTransaction().commit();
    } 
    
    public static void createRoom(Room newRoom){
        emManager.persist(newRoom);
        emManager.getTransaction().commit();
    } 
    
    /*
    public static List<Room> loadRooms(String date, String time){
        TypedQuery<Room> = emManager.createNativeQuery(JPAManager.queryAvailableRooms, Room.class).setParameter("time", time).setParameter("date", date);
        
        
    }*/
    
    public static List<Reservation> loadUserReservations(String username){
        List<Reservation> ret=null;        
        Query q=emManager.createQuery(loadUserReservationsQuery,Reservation.class);
        
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String localDate = date.format(formatter);
        
        q.setParameter("name",username);
        q.setParameter("date",localDate);
        
        ret=q.getResultList();
        return ret;
     
    }
    
    
    public static boolean reservePC(Reservation R){
        
        Query q=emManager.createQuery(queryControlReservations);
        
        String user=R.getUsername();
        String rTime=R.getStartTime();
        String rDate=R.getBookingDate();
        
        q.setParameter("name",user);
        q.setParameter("time",rTime);
        q.setParameter("date",rDate);
        
        int numbRes=(int)q.getSingleResult();
        if(numbRes > 0){
            
            return false;
            
        }
        
        emManager.getTransaction().begin();
        try{
             emManager.persist(R);
            }
        catch(EntityExistsException eee){
                System.out.println("The entity alredy exists !");
                return false;
            }
        return true;
              
    }
    
    public static boolean deleteReservation(Reservation r){
       
       try{ 
        emManager.getTransaction().begin();
        emManager.remove(r);
        emManager.getTransaction().commit();
        /* If the emManager.remove(r) removes nothing because it wants a persisted object
          we have to previously find the target object and after remove it
        */
       }catch(IllegalArgumentException iae){
           
           System.out.println("A detached object is passed as parameter instead of persisted Object !");
           
       }
       return true; 
        
    }
    
}
