import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 * PCBookingGUI is the main class of the application. It builds the GUI of the
 * system. It's the application controller.
 *
 * @author Marco
 */
public class PCBookingApplicationController extends Application {

    private static Stage loginStage;
    private static Stage signupStage;
    private static SignupPaneGUI signupPaneGUI;
    private static Stage mainStage;

    private static void loadLoginPane() {
        mainStage.close();
        signupStage.close();

        loginStage.setTitle("Login");
        loginStage.setResizable(false);
        loginStage.setScene(new Scene(new Group(new LoginPaneGUI())));
        loginStage.sizeToScene();
        loginStage.show();
    }

    private static void loadSignupPane() {
        loginStage.close();
        mainStage.close();

        signupStage = new Stage();
        signupStage.setTitle("Sign up");
        signupStage.setResizable(false);
        signupStage.setScene(new Scene(new Group(signupPaneGUI)));
        signupStage.sizeToScene();
        signupStage.show();
    }

    private static void loadMainPane() {
        loginStage.close();
        signupStage.close();

        mainStage = new Stage();
        mainStage.setTitle("PC Booking");
        mainStage.setResizable(false);
        mainStage.setScene(new Scene(new Group(new MainPaneGUI())));
        mainStage.sizeToScene();
        mainStage.show();
    }

    @Override
    public void start(Stage primaryStage) {
        loginStage = new Stage();
        signupStage = new Stage();
        mainStage = new Stage();
        signupPaneGUI = new SignupPaneGUI();
        loadLoginPane();
    }

    public static void loadSignupForm() {
        loadSignupPane();
    }

    public static void backToLoginForm() {
        loadLoginPane();
    }

    public static boolean login(String username, String password) {
        if (LDBManager.checkLogin(username, password)) {
            LDBManager.loadUserInformation(username);
            loadMainPane();
            return true;
        } else {
            return false;
        }
    }

    public static String signup(int matriculation, String firstName, String lastName, String password) {
        return LDBManager.insertUser(password, firstName, lastName, matriculation);
    }

    public static List<Room> loadRooms(String date, String time) {
        List<Room> rooms = JPAManager.loadRooms(date, time);
        return rooms;
    }

    public static List<PC> loadAvailablePCs(String room, String date, String time) {
        List<PC> PCs = JPAManager.loadAvailablePCs(room, date, time);
        return PCs;
    }

    public static int reservePC(String user, String room, long pcid, String date, String time) {
        Reservation res = new Reservation();
        res.setBookingDate(date);
        PC pcBooked = JPAManager.findPcById(pcid);
        res.setPcBooked(pcBooked);
        res.setStartTime(time);
        res.setUsername(user);
        pcBooked.updatePCStat(1);
        return JPAManager.reservePC(res);
    }

    public static boolean deleteReservation(Reservation r) {
        r.getPcBooked().updatePCStat(-1);
        return JPAManager.deleteReservation(r);
    }

    public static List<Reservation> loadUserReservations(String user) {
        return JPAManager.loadUserReservations(user);
    }

    public static void buildTestDatabase() {

        //build Rooms
        Room roomSI1 = Room.createNewRoom("SI1", 6, 2);
        Room roomSI2 = Room.createNewRoom("SI2", 6, 3);
        Room roomSI3 = Room.createNewRoom("SI3", 6, 1);
        Room roomONE = Room.createNewRoom("Only one pc", 1, 1);

        //build PCs
        PC pcONE = PC.createNewPc(0, roomONE);

        PC pc0SI1 = PC.createNewPc(0, roomSI1);
        PC pc1SI1 = PC.createNewPc(1, roomSI1);
        PC pc2SI1 = PC.createNewPc(2, roomSI1);
        PC pc3SI1 = PC.createNewPc(3, roomSI1);
        PC pc4SI1 = PC.createNewPc(4, roomSI1);
        PC pc5SI1 = PC.createNewPc(5, roomSI1);

        PC pc0SI2 = PC.createNewPc(0, roomSI2);
        PC pc1SI2 = PC.createNewPc(1, roomSI2);
        PC pc2SI2 = PC.createNewPc(2, roomSI2);
        PC pc3SI2 = PC.createNewPc(3, roomSI2);
        PC pc4SI2 = PC.createNewPc(4, roomSI2);
        PC pc5SI2 = PC.createNewPc(5, roomSI2);

        PC pc0SI3 = PC.createNewPc(0, roomSI3);
        PC pc1SI3 = PC.createNewPc(1, roomSI3);
        PC pc2SI3 = PC.createNewPc(2, roomSI3);
        PC pc3SI3 = PC.createNewPc(3, roomSI3);
        PC pc4SI3 = PC.createNewPc(4, roomSI3);
        PC pc5SI3 = PC.createNewPc(5, roomSI3);

        //build Reservations
        Reservation res0 = new Reservation();
        res0.setPcBooked(pc0SI1);
        res0.setBookingDate("2019-12-01");
        res0.setStartTime("12:30:00");
        res0.setUsername("m.pettorali");
        JPAManager.reservePC(res0);

        Reservation res1 = new Reservation();
        res1.setPcBooked(pc1SI1);
        pc1SI1.updatePCStat(1);
        res1.setBookingDate("2019-12-01");
        res1.setStartTime("12:30:00");
        res1.setUsername("d.lorenzoni2");
        JPAManager.reservePC(res1);

        Reservation res2 = new Reservation();
        res2.setPcBooked(pc2SI1);
        pc2SI1.updatePCStat(1);
        res2.setBookingDate("2019-12-01");
        res2.setStartTime("12:30:00");
        res2.setUsername("r.xefraj");
        JPAManager.reservePC(res2);

        Reservation res3 = new Reservation();
        res3.setPcBooked(pc0SI1);
        pc0SI1.updatePCStat(1);
        res3.setBookingDate("2019-12-02");
        res3.setStartTime("10:30:00");
        res3.setUsername("r.nocerino");
        JPAManager.reservePC(res3);

        Reservation res4 = new Reservation();
        res4.setPcBooked(pc0SI2);
        pc0SI2.updatePCStat(1);
        res4.setBookingDate("2019-12-02");
        res4.setStartTime("10:30:00");
        res4.setUsername("m.pettorali");
        JPAManager.reservePC(res4);

        Reservation res5 = new Reservation();
        res5.setPcBooked(pc1SI2);
        pc1SI2.updatePCStat(1);
        res5.setBookingDate("2019-12-05");
        res5.setStartTime("10:30:00");
        res5.setUsername("r.nocerino");
        JPAManager.reservePC(res5);

        Reservation res6 = new Reservation();
        res6.setPcBooked(pc1SI2);
        pc1SI2.updatePCStat(1);
        res6.setBookingDate("2019-12-02");
        res6.setStartTime("11:30:00");
        res6.setUsername("d.lorenzoni2");
        JPAManager.reservePC(res6);

        Reservation res7 = new Reservation();
        res7.setPcBooked(pc3SI3);
        pc3SI3.updatePCStat(1);
        res7.setBookingDate("2019-12-15");
        res7.setStartTime("17:30:00");
        res7.setUsername("r.xefraj");
        JPAManager.reservePC(res7);

        Reservation res8 = new Reservation();
        res8.setPcBooked(pcONE);
        pcONE.updatePCStat(1);
        res8.setBookingDate("2019-12-25");
        res8.setStartTime("12:30:00");
        res8.setUsername("m.pettorali");
        JPAManager.reservePC(res8);

    }
    
    private static void checkFirstExecution(){
        File f=new File("./CheckFirstExecution.txt");
        try(
            FileReader fr=new FileReader(f);
            BufferedReader br=new BufferedReader(fr);    
                
           ){
            String value=br.readLine();
            int res=Integer.parseInt(value);
            if(res==0){
                
                File xml=new File("./src/main/resources/META-INF/persistence.xml");
                
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                Document doc = docBuilder.parse(xml);
                org.w3c.dom.Node properties=doc.getElementsByTagName("properties").item(0);
                 org.w3c.dom.Node property=doc.getElementsByTagName("property").item(6);
                
                /*NamedNodeMap attr=property.getAttributes();
                org.w3c.dom.Node nodeattr=attr.getNamedItem("value");
                String val = nodeattr.getNodeValue();
                nodeattr.setNodeValue("drop-and-create");*/
                properties.removeChild(property);
                
                Transformer xformer = TransformerFactory.newInstance().newTransformer();
                xformer.transform(new DOMSource(doc), new StreamResult(new File("./src/main/resources/META-INF/persistence.xml")));
                
                buildTestDatabase();
                LDBManager.InsertTemporary();
                
                FileWriter fw=new FileWriter(f,false);
                fw.write("1");
                fw.close();
                             
            }
            
            
          }catch(IOException io){
              System.out.println("An error during the opening of checkFirstExecution !");
          }catch(SAXException sax){
              System.out.println("Error during opening the DOM file!");
          } catch(ParserConfigurationException ex) {
            Logger.getLogger(PCBookingApplicationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(PCBookingApplicationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(PCBookingApplicationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
          checkFirstExecution();
          launch(args);
          JPAManager.close();
          System.exit(0);
    }

}
