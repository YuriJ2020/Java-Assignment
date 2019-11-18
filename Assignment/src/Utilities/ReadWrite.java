/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import Classes.Members;
import java.io.EOFException;
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
 * @author ppunme
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
        
        ArrayList<Members> restoredList = new ArrayList<>();
        boolean reading = true;
        try
        {
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            
            //while (ois.readObject() != null)
            //while (ois.available() != 0)
            //while (ois.read() != -1) 
            
            while(reading)
            {			
                restoredList.add((Members)ois.readObject());	         
            }
            ois.close();
            System.out.println(restoredList);
        }
        catch(EOFException ex)
        {
            reading = false;
        }
        catch(Exception e)
        {
            reading = false;
        }
        return restoredList;
    }
    
    public static void writeData(String fileName, ArrayList<Members> list)
                                        throws FileNotFoundException, 
                                            ClassNotFoundException,
                                                NotSerializableException,
                                                    IOException{
        
        System.out.println("Backup List:" + list);
        try{							
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            //open OutputFile
                   
            //write Student objects to file
            for (int i=0; i < list.size() && list.get(i) != null; i++)
            {
                oos.writeObject(list.get(i));
            }
            oos.close();								
        }

        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }	
    }
}        
       
    


