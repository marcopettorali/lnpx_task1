
import java.io.*;
import org.iq80.leveldb.*;
import static org.iq80.leveldb.impl.Iq80DBFactory.*;


public class LDBManager {
        private static DB userDB;
        private static final String[] userDBFormat={":username=",":password=",":firstName=",":lastName=",":matriculationNumber="};
        
        static {
            Options options = new Options();
            options.createIfMissing(true);
            try {
                
                userDB = factory.open(new File("UserDB"), options);
            } catch (IOException ex) {
               System.out.println("Error opening UserDB");
            }
        }
        
        /**
         * This function inserts a new user in a key-value database 
         * @param uname
         * @param pword
         * @param fName
         * @param lName
         * @param mNumber 
         */
        public static void insertUser(String uname,String pword,String fName,String lName, int mNumber)
        {
            String key="user:"+uname.hashCode();
            String[] LDBInfo={uname,pword,fName,lName,String.valueOf(mNumber)};
            for(int i=0;i<5;i++)
            {
                String upKey=key+userDBFormat[i];
                userDB.put(bytes(upKey), bytes(LDBInfo[i]));
            }
        }
        
        /**
         * This function checks if an user exists in the Database 
         * @param uname: Username
         * @param pword: Password
         * @return 
         */
       public static boolean checkLogin(String uname, String pword)
        {
            String s=asString(userDB.get(bytes("user:"+uname.hashCode()+userDBFormat[1])));
            if(s!=null)
                return s.compareTo(pword)==0;
            return false;
        }
       
       /**
        * This function deletes from the Database all the informations of the specified user
        * @param uname
        * @return 
        */
       public static boolean deleteUser(String uname)
       {
            int cont=0;
            DBIterator keyIterator=userDB.iterator();
            keyIterator.seek(bytes("user:"+uname.hashCode()));
            while(keyIterator.hasNext())
            {
               cont++;
               userDB.delete(keyIterator.peekNext().getKey());
               keyIterator.next();
            }
            return cont>0;
       }
       
       /**
        * Temporary insert of Users in the key-value DB
        */
       public static void InsertTemporary()
       {
            LDBManager.insertUser("r.xefraj", "r.xefraj", "Riccardo", "Xefraj", 547897);
            LDBManager.insertUser("d.lorenzoni2","d.lorenzoni2","Dario","Lorenzoni",546619);
            LDBManager.insertUser("m.pettorali","m.pettorali","Marco","Pettorali",555444);
            LDBManager.insertUser("r.nocerino","r.nocerino","Raffaele","Nocerino",530199);                    
       }
    }