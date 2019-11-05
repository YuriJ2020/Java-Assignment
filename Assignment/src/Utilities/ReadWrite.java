/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    public static ArrayList<Members> readData(String fileName) throws FileNotFoundException, 
                                            ClassNotFoundException,
                                                NotSerializableException,
                                                    IOException{
        
        ArrayList<Members> list = new ArrayList<>();
        
        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);
        list = (ArrayList<Members>)ois.readObject(); 
        
        ois.close();
        return list;
    }
    
    public static void writeData(String fileName, ArrayList<Members> list)
                                        throws FileNotFoundException, 
                                            ClassNotFoundException,
                                                NotSerializableException,
                                                    IOException{
        
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        
        //write all data to file
        oos.writeObject(list);
        
        oos.close();
        
    }
}

