/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

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
 * @author ppunme
 */
public class SearchTable extends AbstractTableModel{
    private ArrayList<Members> list = new ArrayList<>();
    private String[] columnNames = {"Member ID", "First Name", "Last Name", "Gender", 
                "Email", "Phone", "Address", "Suburb", "Type", "No. of Member"};
    
    //constructor
    public SearchTable() {
        //call a method
        //getDataFromDatabase();
    }
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
          
        }
        return null;
    }
    
    //non-abstract method that we will override
    @Override
    public String getColumnName(int col){
        
        return columnNames[col];
    }
    
    //this method will wxtract one object (Student) from the ArrayList at a time
    public Members getRow(int row)
    {
        Members m = list.get(row);
        return m;
    }
    
    //connect to the database
    public Connection getConnection(){
        Connection con = null;
        
        //get mySQL Connection details
        String username = ConnectionDetails.getUserName();
        String password = ConnectionDetails.getPassWord();
        String url = ConnectionDetails.getUrl();
        
        System.out.println(url);
        
        try{
            //load the SQL driver
            Class.forName(ConnectionDetails.getDriver());
            con = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the Database");
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        return con;
    }
    
    /*public void getDataFromDatabase()
    {
        Connection con = null;
        Statement stmt = null;
        ResultSet r = null;
        
        try{
            con = getConnection();
            stmt = con.createStatement();
            String sql = "SELECT * FROM tblMember INNER JOIN tblSingle ON tblMember.memberID = tblSingle.memberID"
                    + " INNER JOIN tblAddress ON tblMember.memberID = tblAddress.memberID";
            r = stmt.executeQuery(sql);
            System.out.println(r);
            
            //clear out the arrayList
            list.clear();
            
            //loop through the records and add them to the ArrayList
            while(r.next())
            {
                //make sure column names from the DATABASE are spelt correctly
                list.add(new Single(r.getInt("memberID"),r.getString("first"),
                        r.getString("last"),r.getString("gender"),r.getString("email"),
                        r.getString("phone"),r.getString("address"),r.getString("suburb"),
                        r.getString("state"),r.getString("postcode"),r.getDouble("baseFee"),r.getString("type")));
            }
            stmt.close();
            con.close();
        }catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }*/
    
   
}
