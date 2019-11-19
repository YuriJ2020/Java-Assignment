package Utilities;

import Classes.Family;
import Classes.Members;
import Classes.Single;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Poonnamee
 */
public class MemberTableModel extends AbstractTableModel {

    private ArrayList<Members> list = new ArrayList<>();

    private String[] columnNames = {"ID", "First Name", "Last Name", "Gender",
        "Email", "Phone", "Address", "Suburb", "State", "Postcode", "Package", "No. of Member", "Fee", "Type", "Agent ID"};

    //constructor
    public MemberTableModel() {
        //call a method - retrieve data from database
        getDataFromDatabase();
        //Utilities.DataAccessLayer.getDataFromDatabase(list);
    }

    public MemberTableModel(ArrayList<Members> searchList) {
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

        switch (columnIndex) {
            case 0:               
                return m.getId();
            case 1:
                return m.getName();
            case 2:
                return m.getLast();
            case 3:
                return m.getGender();
            case 4:
                return m.getEmail();
            case 5:
                return m.getPhone();
            case 6:
                return m.getAddress();
            case 7:
                return m.getSuburb();
            case 8:
                return m.getState();
            case 9:
                return m.getPostcode();
            case 10:
                if (m instanceof Single) {
                    Single s = (Single) m;
                    return s.getPackLoad();
                } else {
                    return null;
                }
            case 11:
                if (m instanceof Family) {
                    Family f = (Family) m;
                    return f.getNoMembers();
                } else {
                    return null;
                }
            case 12:
                return m.getFee();
            case 13:
                return m.getType();
            case 14:
                return m.getAgentID();              
        }
        return null;
    }

    //non-abstract method that we will override
    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    //this method will extract one object (Members) from the ArrayList at a time
    public Members getRow(int row) {
        Members m = list.get(row);
        return m;
    }
    
    public void getDataFromDatabase()
    {     
        Connection con = null;
        Statement stmt = null;
        ResultSet r = null;
        
        try{
            con = ConnectionDetails.getConnection();
            stmt = con.createStatement();
            String sql = "SELECT * FROM tblMember INNER JOIN tblAddress WHERE"
                    + " tblMember.memberID = tblAddress.memberID";
            r = stmt.executeQuery(sql);
            System.out.println(sql);

            //clear out the arrayList
            list.clear();
            
            //loop through the records and add them to the ArrayList
            while(r.next())
            {     
                if(r.getString("type").equals("Single")){
                    //make sure column names from the DATABASE are spelt correctly
                    list.add(new Single(r.getInt("memberID"),r.getString("first"),r.getString("last"),
                            r.getString("gender"),r.getString("email"),r.getString("phone"),r.getString("address"),
                            r.getString("suburb"),r.getString("state"),r.getString("postcode"),
                            r.getDouble("baseFee"),r.getString("package"),r.getString("type"),r.getInt("agentID"))); 
                } else {
                    list.add(new Family(r.getInt("memberID"),r.getString("first"),r.getString("last"),
                            r.getString("gender"),r.getString("email"),r.getString("phone"),r.getString("address"),
                            r.getString("suburb"),r.getString("state"),r.getString("postcode"),
                            r.getDouble("baseFee"),r.getInt("noMember"),r.getString("type"),r.getInt("agentID"))); 
                }
            }  
            stmt.close();
            con.close();
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

}
