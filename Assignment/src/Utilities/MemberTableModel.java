package Utilities;


import Classes.Family;
import Classes.Members;
import Classes.Single;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Poonnamee
 */
public class MemberTableModel extends AbstractTableModel{
    //Data will be retrieved from the database
    //Each Student will be saved as an object
    //Student objects will be saved in an arrayList
    
    private ArrayList<Members> list = new ArrayList<>();
    
    private String[] columnNames = {"ID", "First Name", "Last Name", "Gender", 
                "Email", "Phone", "Address", "Suburb", "State", "Postcode", "Package", "No. of Member"};
    
    //constructor
    public MemberTableModel() {
        //call a method
        Utilities.DataAccessLayer.getDataFromDatabase(list);
        System.out.println(list);
    }
    
    public MemberTableModel(ArrayList<Members> searchList){
        list = searchList;
    }
    
    /* "AbstractTableModel" abstract class - Must implement atleast 3 methods
    public int getRowCount()
    public int getColumnCount()
    public Object getValueAt(int row, int col)
    */

   
    public int getRowCount() {
        return list.size(); //number of records/rows retrieved
    }

    
    public int getColumnCount() {
        return columnNames.length; //how many columns to display
    }

    
    public Object getValueAt(int rowIndex, int columnIndex) {
        Members m = list.get(rowIndex);
        
        switch(columnIndex)
        {
            case 0: return m.getId();
            case 1: return m.getName();
            case 2: return m.getLast();
            case 3: return m.getGender();
            case 4: return m.getEmail();
            case 5: return m.getPhone();
            case 6: return m.getAddress();
            case 7: return m.getSuburb();
            case 8: return m.getState();
            case 9: return m.getPostcode();
            case 10: if(m instanceof Single){
                Single s = (Single)m;
                return s.getPackLoad();
            } else {
                return null;
            }
            case 11: if(m instanceof Family){
                Family f = (Family)m;
                return f.getNoMembers();
            }
        }
        return null;
    }
    
    //non-abstract method that we will override
    @Override
    public String getColumnName(int col){
        
        return columnNames[col];
    }
    
    //this method will extract one object (Student) from the ArrayList at a time
    public Members getRow(int row)
    {
        Members m = list.get(row);
        return m;
    }

}