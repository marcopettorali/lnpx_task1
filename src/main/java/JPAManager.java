
import java.sql.*;
import java.util.*;
import persistence.*;

public class JPAManager {

    EntityManagerFactory EMF;
    EntityManager EM;
    
    private static Connection DBConnection;
    private static final String DBMSFormat = "jdbc:mysql";
    private static final String DBMSAddress = "localhost";//"127.0.0.1";
    private static final String DBMSPort = "3306";
    private static final String DBName = "pc_booking";
    private static final String DBMSUsername = "root";
    private static final String DBMSPassword = "";

    private static final String checkLoginQuery = "SELECT * FROM user WHERE Username = ? AND Password = ?";

    private static final String loadUserReservationsQuery = "SELECT * FROM booking WHERE Username = ? AND Date >= ?";

    private static final String queryControlReservations = ""
            + "select count(*) as NumPrenotazioni "
            + "from booking b "
            + "where b.username=? and b.StartTime=? and b.date=?;";

    private static final String queryAvailableRooms = ""
            + "select D.RoomName,D.Capacity - D.Booked as Available, D.Capacity, D.RowNumber	"
            + "from (   "
            + "          select b.RoomName, count(*) as Booked ,r.Capacity, r.RowNumber"
            + "          from booking b inner join room r on (r.RoomName=b.RoomName)"
            + "	    where b.StartTime=? and b.Date=?	"
            + "	    group by b.RoomName "
            + "		) as D "
            + "where D.Capacity - D.Booked > 0 "
            + " union  select  r1.RoomName, r1.Capacity,r1.Capacity,r1.RowNumber "
            + "   from room r1 "
            + "   where r1.roomName not in (select b1.roomName"
            + "			         from booking b1"
            + "                             where b1.StartTime=? and b1.Date=?);";

    private static final String queryCreateReservation = ""
            + "INSERT INTO booking "
            + " VALUES (?,?,?,?,?);";

    private static final String queryDeleteReservation = ""
            + "DELETE FROM booking "
            + "  WHERE (Username = ?) and (StartTime = ?) and (Date =?) and (PCNumber = ?) and (RoomName = ?);";

    private static final String queryAvailablePC = ""
            + " SELECT p.PCNumber,p.RoomName "
            + " FROM pc p "
            + " WHERE p.RoomName=? "
            + "    and "
            + "   NOT EXISTS "
            + "  (SELECT b.PCNumber,b.RoomName "
            + "   FROM booking b "
            + "   WHERE b.RoomName=p.RoomName and b.PCNumber=p.PCNumber and b.Date=? and b.StartTime=?);";

    static {
        try {
            DBConnection = DriverManager.getConnection(DBMSFormat + "://" + DBMSAddress + ":" + DBMSPort + "/" + DBName + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Rome", DBMSUsername, DBMSPassword);
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
    }

    public static boolean checkLogin(String username, String password) {
        
    }

    public static ArrayList<Reservation> loadUserReservations(String username) {
        
    }

    public static int reservePC(String user, String rn, int pcnumb, String D, String T) {

    }

    public static boolean deleteReservation(String user, String rn, int pcnumb, String D, String T) {

    }

    public static List<Room> loadRooms(String D, String T) {
  
    }

    public static List<PC> loadAvailablePCs(String rn, String D, String T) {
        
    }

}
