/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import Classes.Single;
import static GUI.Update.txfID;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author ppunme
 */
public class DataAccessLayer{
    
    
    public static void addToDatabase(Single s, String type){
        //Insert into database
        Connection con = null;
        Statement stmt = null;
        ResultSet r = null;
        
        int MYSQL_DUPLICATE_PK = 1062; //1062 error code for duplicate primary key
        
        try{
            con = ConnectionDetails.getConnection();
            stmt = con.createStatement();
            System.out.println("Connected to the database");
            
            //create table if it not exist
            //create tblMember
            String tblMember = "CREATE TABLE if not exists tblMember(" 
                    + "memberID int not null primary key AUTO_INCREMENT, "
                    + "first varchar(50), "
                    + "last varchar(50),"
                    + "gender varchar(20),"
                    + "email varchar(50),"
                    + "phone varchar(20))"; 
            System.out.println(tblMember);        
            stmt.executeUpdate(tblMember);
            System.out.println("tblEmployees has been created");
            
            //create tblAddress
            String tblAddress = "CREATE TABLE if not exists tblAddress("
                    + "addressID int not null AUTO_INCREMENT,"
                    + "address varchar(50)," 
                    + "suburb varchar(50),"
                    + "state varchar(50),"
                    + "postcode int, " 
                    + "memberID int, "
                    + "PRIMARY KEY (addressID),"
                    + "FOREIGN KEY (memberID) References tblMember(memberID))";
            System.out.println(tblAddress);        
            stmt.executeUpdate(tblAddress);
            System.out.println("tblAddress has been created");
            
            //create tblSingle
            String tblSingle = "CREATE TABLE if not exists tblSingle("
                    + "singleID int not null AUTO_INCREMENT,"
                    + "package varchar(50), " 
                    + "baseFee double,"
                    + "memberID int,"
                    + "PRIMARY KEY (singleID),"
                    + "FOREIGN KEY (memberID) References tblMember(memberID))";
            System.out.println(tblSingle);        
            stmt.executeUpdate(tblSingle);
            System.out.println("tblSingle has been created");
            
            //create tblFamily
            String tblFamily = "CREATE TABLE if not exists tblFamily("
                    + "familyID int not null AUTO_INCREMENT,"
                    + "noMember int, " 
                    + "memberID int,"
                    + "PRIMARY KEY (familyID),"
                    + "FOREIGN KEY (memberId) References tblMember(memberId))";
            System.out.println(tblFamily);        
            stmt.executeUpdate(tblFamily);
            System.out.println("tblFamily has been created");
            
            //check memberID in database
            String sql = "SELECT * from tblMember where memberID=" + txfID.getText();
            r = stmt.executeQuery(sql);
            System.out.println(r);
            
            sql = "SELECT * from tblAddress where memberID=" + txfID.getText();
            r = stmt.executeQuery(sql);
            
            sql = "SELECT * from tblSingle where memberID=" + txfID.getText();
            r = stmt.executeQuery(sql);
            
            if(r.next())
            { //found this member id in database
                JOptionPane.showMessageDialog(null, "This member id is already exist");
            }
            else if(type.equals("Single")) 
            {
                //insert data to member table
                sql = "INSERT INTO tblMember (memberID, first, last, gender, email, phone) values"
                        + "('" + s.getId() + "','" + s.getName() + "','" + s.getLast()
                        + "','" + s.getGender() + "','" + s.getEmail() + "','" + s.getPhone() + "')";
                stmt.executeUpdate(sql);

                //insert data to address table
                sql = "INSERT INTO tblAddress (address, suburb, state, postcode, memberID) values ('"+ s.getAddress()
                        + "','" + s.getSuburb() + "','" + s.getState() + "','" + s.getPostcode() + "','" + txfID.getText() + "')";
                stmt.executeUpdate(sql);
                
                //insert data to single table
                sql = "INSERT INTO tblSingle (package, baseFee, memberID) values ('"+ s.getPackLoad() + "','" + s.getBaseFee() + "','" + s.getId() + "')";
                stmt.executeUpdate(sql);
                System.out.println("Single Member details have been added to database");
            } else {
                //insert data to member table
                /*sql = "INSERT INTO tblMember (memberID, first, last, gender, email, phone) values"
                        + "('" + txfID.getText() + "','" + txfFirst.getText() + "','" + txfLast.getText() 
                        + "','" + gender + "','" + txfEmail.getText() + "','" + txfPhone.getText() + "')";
                stmt.executeUpdate(sql);

                //insert data to address table
                sql = "INSERT INTO tblAddress (address, suburb, state, postcode, memberID) values ('"+ txfAddress.getText()
                        + "','" + txfSuburb.getText() + "','" + stateLoad + "','" + txfPostcode.getText() + "','" + txfID.getText() + "')";
                stmt.executeUpdate(sql);
                
                //insert data to single table
                sql = "INSERT INTO tblMember (noMember, baseFee, memberID) values ('"+ packLoad + "','" + BASE_FEE + "','" + txfID.getText() + "')";
                stmt.executeUpdate(sql);
                System.out.println("Family Member details have been added to database");*/
            }
            
        } catch (SQLException sqlE) {
            sqlE.printStackTrace();
            System.err.println("ERROR: " + sqlE.getMessage());
            if(sqlE.getErrorCode() == MYSQL_DUPLICATE_PK){ //duplicate primary key
                JOptionPane.showMessageDialog(null, "Duplicate Member ID");
                txfID.requestFocusInWindow();
            }
        } finally {
            
        }
        
        try {
            if(stmt != null) {
                stmt.close();
            }
            System.out.println("Statement close");
        } catch (SQLException sqlE) {
            System.out.println("Error closing statement");
        }
        
        try{
            if (con != null) {
                con.close();
            }
            System.out.println("Connection close");
        } catch (SQLException sqlE) {
            System.out.println("Error Closing connection");
        }
    }
    
    //Get agent name for database to ComboBox
    public static void getAgentToCombobox(String type, JComboBox cboAgentLoad){
        Connection con = null;
        Statement stmt = null;
        ResultSet r = null;
        
        try {  
            con = ConnectionDetails.getConnection();
            stmt = con.createStatement();
            System.out.println("Connected to the database");

            r = stmt.executeQuery("select * from tblAgent");
            System.out.println(r);
         
            cboAgentLoad.addItem("Make a Selection");
            while (r.next()) {  
                cboAgentLoad.addItem(r.getString("first"));  
            }

            con.close();
        } catch (Exception e) {     
            JOptionPane.showMessageDialog(null,"Failed to Connect to Database","Error Connection", JOptionPane.WARNING_MESSAGE);  
            System.exit(0);  
        }  
    }
}
