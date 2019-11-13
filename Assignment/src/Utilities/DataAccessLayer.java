/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import Classes.Agent;
import Classes.Family;
import Classes.Single;
import static GUI.Update.txfEmail;
import static GUI.Update.txfPhone;
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
    
    
    public static void addSingleToDatabase(Single s, String type){
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
                    + "first varchar(50),"
                    + "last varchar(50),"
                    + "gender varchar(20),"
                    + "email varchar(50),"
                    + "phone varchar(20),"
                    + "package varchar(50),"
                    + "noMember int," 
                    + "baseFee double,"
                    + "type varchar(50)"
                    + ")"; 
            System.out.println(tblMember);        
            stmt.executeUpdate(tblMember);
            System.out.println("tblMember has been created");
            
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
            
            //check memberID in database
            String sql = "SELECT * from tblMember where memberID=" + s.getId();
            r = stmt.executeQuery(sql);
            System.out.println(r);
            
            sql = "SELECT * from tblAddress where memberID=" + s.getId();
            r = stmt.executeQuery(sql);
            
            if(r.next())
            { //found this member id in database
                JOptionPane.showMessageDialog(null, "This member id is already exist");
            }
            else
            {
                //insert data to member table
                sql = "INSERT INTO tblMember (memberID, first, last, gender, email, phone, package, baseFee, type) values"
                        + "('" + s.getId() + "','" + s.getName() + "','" + s.getLast() + "','" + s.getGender() + "','" 
                        + s.getEmail() + "','" + s.getPhone() + "','" + s.getPackLoad() + "','" + s.getBaseFee() + "','" + type + "')";
                stmt.executeUpdate(sql);

                //insert data to address table
                sql = "INSERT INTO tblAddress (address, suburb, state, postcode, memberID) values ('"+ s.getAddress()
                        + "','" + s.getSuburb() + "','" + s.getState() + "','" + s.getPostcode() + "','" + s.getId() + "')";
                stmt.executeUpdate(sql);
                System.out.println("Added Single member to Database");
            } 
        } catch (SQLException sqlE) {
            sqlE.printStackTrace();
            System.err.println("ERROR: " + sqlE.getMessage());
            if(sqlE.getErrorCode() == MYSQL_DUPLICATE_PK){ //duplicate primary key
                JOptionPane.showMessageDialog(null, "Duplicate Member ID");
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
    
    public static void addFamilyToDatabase(Family f, String type){
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
                    + "first varchar(50),"
                    + "last varchar(50),"
                    + "gender varchar(20),"
                    + "email varchar(50),"
                    + "phone varchar(20),"
                    + "package varchar(50),"
                    + "noMember int," 
                    + "baseFee double,"
                    + "type varchar(50)"
                    + ")"; 
            System.out.println(tblMember);        
            stmt.executeUpdate(tblMember);
            System.out.println("tblMember has been created");
            
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
            
            //check memberID in database
            String sql = "SELECT * from tblMember where memberID=" + f.getId();
            r = stmt.executeQuery(sql);
            System.out.println(r);
            
            sql = "SELECT * from tblAddress where memberID=" + f.getId();
            r = stmt.executeQuery(sql);
            
            if(r.next())
            { //found this member id in database
                JOptionPane.showMessageDialog(null, "This member id is already exist");
            } 
            else 
            {
                sql = "INSERT INTO tblMember (memberID, first, last, gender, email, phone, noMember, baseFee, type) values"
                        + "('" + f.getId() + "','" + f.getName() + "','" + f.getLast() + "','" + f.getGender() + "','" 
                        + f.getEmail() + "','" + f.getPhone() + "','" + f.getNoMembers() + "','" + f.getBaseFee() + "','" + type + "')";
                stmt.executeUpdate(sql);

                //insert data to address table
                sql = "INSERT INTO tblAddress (address, suburb, state, postcode, memberID) values ('"+ f.getAddress()
                        + "','" + f.getSuburb() + "','" + f.getState() + "','" + f.getPostcode() + "','" + f.getId() + "')";
                stmt.executeUpdate(sql);
                System.out.println("Added Family member to Database");
                
            }
            
        } catch (SQLException sqlE) {
            sqlE.printStackTrace();
            System.err.println("ERROR: " + sqlE.getMessage());
            if(sqlE.getErrorCode() == MYSQL_DUPLICATE_PK){ //duplicate primary key
                JOptionPane.showMessageDialog(null, "Duplicate Member ID");
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
    
    public static void addAgentToDatabase(Agent a){
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
            String tblMember = "CREATE TABLE if not exists tblAgent(" 
                    + "agentID int not null AUTO_INCREMENT, "
                    + "first varchar(50), "
                    + "last varchar(50),"
                    + "phone varchar(20)," 
                    + "PRIMARY KEY (agentID))";
                    
            System.out.println(tblMember);        
            stmt.executeUpdate(tblMember);
            System.out.println("tblAgent has been created");
            
            //check agentID in database
            String sql = "SELECT * from tblAgent where agentID=" + a.getId();
            r = stmt.executeQuery(sql);
            System.out.println(r);
            
            if(r.next())
            { //found this member id in database
                JOptionPane.showMessageDialog(null, "This agent id is already exist");
            }
            else 
            {
                //insert data to agent table
                sql = "INSERT INTO tblAgent (agentID, first, last, phone) values"
                        + "('" + a.getId() + "','" + a.getFirst() + "','" + a.getLast()
                        + "','" + a.getPhone() + "')";
                stmt.executeUpdate(sql);
                System.out.println("add data to tblAgent");
            }
            
        } catch (SQLException sqlE) {
            sqlE.printStackTrace();
            System.err.println("ERROR: " + sqlE.getMessage());
            if(sqlE.getErrorCode() == MYSQL_DUPLICATE_PK){ //duplicate primary key
                JOptionPane.showMessageDialog(null, "Duplicate Member ID");
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
    public static void getAgentToCombobox(JComboBox cboAgentLoad){
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
