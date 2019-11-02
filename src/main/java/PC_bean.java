
import javafx.beans.property.*;

public class PC_bean {
    private SimpleIntegerProperty PCnumber;
    private SimpleStringProperty roomName;
    
    public PC_bean(int PCnumber, String roomName){
        
        this.PCnumber = new SimpleIntegerProperty(PCnumber);
        this.roomName = new SimpleStringProperty(roomName);
    
    }

    public int getPCnumber() {
        return PCnumber.get();
    }

    public String getRoomName() {
        return roomName.get();
    }
    
    
}
