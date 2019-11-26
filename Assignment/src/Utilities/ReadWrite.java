
package Utilities;

import Classes.Members;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author Poonnamee
 * Date: 27/11/19
 * Read and Write member data to file
 */
public class ReadWrite {
    
    /**
     * 
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     * @throws NotSerializableException
     * @throws IOException 
     */
            
    public static ArrayList<Members> readData(String fileName) 
                                        throws FileNotFoundException, ClassNotFoundException,
                                            NotSerializableException, IOException{
        
        
        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);
        ArrayList<Members> restoredList = new ArrayList<>();
        
        restoredList = (ArrayList<Members>) ois.readObject();
        
        ois.close();
        return restoredList;
    }
    
    public static void writeData(String fileName, ArrayList<Members> list)
                                        throws FileNotFoundException, 
                                            ClassNotFoundException,
                                                NotSerializableException,
                                                    IOException{
        
        try{							
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            //open OutputFile
            oos.writeObject(list);
            
            oos.close();	
        }

        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }	
    }
}        
       
    


