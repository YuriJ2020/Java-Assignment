/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import Classes.Agent;
import Classes.Family;
import Classes.Members;
import Classes.Single;
import static GUI.AddMember.agentLoad;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
                    + "memberID int not null AUTO_INCREMENT, "
                    + "first varchar(50),"
                    + "last varchar(50),"
                    + "gender varchar(20),"
                    + "email varchar(50),"
                    + "phone varchar(20),"
                    + "package varchar(50),"
                    + "noMember int," 
                    + "baseFee double,"
                    + "type varchar(50),"
                    + "agentID int,"
                    + "PRIMARY KEY (memberID),"
                    + "FOREIGN KEY (agentID) References tblAgent(agentID))";
            System.out.println(tblMember);        
            stmt.executeUpdate(tblMember);
            System.out.println("tblMember has been created");
            
            //create tblAddress
            String tblAddress = "CREATE TABLE if not exists tblAddress("
                    + "addressID int not null AUTO_INCREMENT,"
                    + "address varchar(50)," 
                    + "suburb varchar(50),"
                    + "state varchar(50),"
                    + "postcode varchar(10), " 
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
                System.out.println("agent id");
                //insert data to member table
                sql = "INSERT INTO tblMember (memberID, first, last, gender, email, phone, package, baseFee, type, agentID) values"
                        + "('" + s.getId() + "','" + s.getName() + "','" + s.getLast() + "','" + s.getGender() + "','" 
                        + s.getEmail() + "','" + s.getPhone() + "','" + s.getPackLoad() + "','" + s.getBaseFee() + "','" 
                        + type + "','" + s.getAgentID() + "')";
                stmt.executeUpdate(sql);
                System.out.println(sql);

                //insert data to address table
                sql = "INSERT INTO tblAddress (address, suburb, state, postcode, memberID) values ('"+ s.getAddress()
                        + "','" + s.getSuburb() + "','" + s.getState() + "','" + s.getPostcode() + "','" + s.getId() + "')";
                stmt.executeUpdate(sql);
                System.out.println(sql);
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
                    + "memberID int not null AUTO_INCREMENT, "
                    + "first varchar(50),"
                    + "last varchar(50),"
                    + "gender varchar(20),"
                    + "email varchar(50),"
                    + "phone varchar(20),"
                    + "package varchar(50),"
                    + "noMember int," 
                    + "baseFee double,"
                    + "type varchar(50),"
                    + "agentID int,"
                    + "PRIMARY KEY (memberID),"
                    + "FOREIGN KEY (agentID) References tblAgent(agentID))";
            System.out.println(tblMember);        
            stmt.executeUpdate(tblMember);
            System.out.println("tblMember has been created");
            
            //create tblAddress
            String tblAddress = "CREATE TABLE if not exists tblAddress("
                    + "addressID int not null AUTO_INCREMENT,"
                    + "address varchar(50)," 
                    + "suburb varchar(50),"
                    + "state varchar(50),"
                    + "postcode varchar(10), " 
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
                sql = "INSERT INTO tblMember (memberID, first, last, gender, email, phone, noMember, baseFee, type, agentID) values"
                        + "('" + f.getId() + "','" + f.getName() + "','" + f.getLast() + "','" + f.getGender() + "','" 
                        + f.getEmail() + "','" + f.getPhone() + "','" + f.getNoMembers() + "','" + f.getBaseFee() + "','" 
                        + type + "','" + f.getAgentID() + "')";
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
    
    public static void addAgentToDatabase(Agent a)
    {
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
            { //found this agent id in database
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
    
    //Get Agent name for database to ComboBox
    public static void getAgentToCombobox(JComboBox cboAgentLoad)
    {
        Connection con = null;
        Statement stmt = null;
        ResultSet r = null;
        
        try {  
            con = ConnectionDetails.getConnection();
            stmt = con.createStatement();
            System.out.println("Connected to the database");

            String tblAgent = "CREATE TABLE if not exists tblAgent(" 
                    + "agentID int not null AUTO_INCREMENT, "
                    + "first varchar(50), "
                    + "last varchar(50),"
                    + "phone varchar(20)," 
                    + "PRIMARY KEY (agentID))";
            stmt.executeUpdate(tblAgent);
            System.out.println("tblAgent has been created");
            
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
    
    public static int getAgentID()
    {
        int agentID;
        Connection con = null;
        Statement stmt = null;
        ResultSet r = null;
        
        try{
            con = ConnectionDetails.getConnection();
            stmt = con.createStatement();
            System.out.println("Agent Load: " + agentLoad);
            String sql = "SELECT * FROM tblAgent WHERE first LIKE '%" + agentLoad + "%'";
            //SELECT * FROM tblAgent WHERE CONCAT(first, ' ', last) LIKE '%Jon aa%'
            r = stmt.executeQuery(sql);
            System.out.println(sql);
            
            r.next();
            agentID = r.getInt("agentID");
            
            stmt.close();
            con.close();
            
            return agentID;
            
        }catch(SQLException ex) {
            ex.printStackTrace();
            return agentID = 0;
        }
    }
    
    public static void getDataFromDatabase(ArrayList<Members> list)
    {     
        String agentName;
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
                            r.getDouble("baseFee"),r.getString("package"),r.getInt("agentID"))); 
                } else {
                    list.add(new Family(r.getInt("memberID"),r.getString("first"),r.getString("last"),
                            r.getString("gender"),r.getString("email"),r.getString("phone"),r.getString("address"),
                            r.getString("suburb"),r.getString("state"),r.getString("postcode"),
                            r.getDouble("baseFee"),r.getInt("noMember"),r.getInt("agentID"))); 
                }
          
            }  
            
            stmt.close();
            con.close();
            
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void getAgentList(ArrayList<Agent> agentList){
        String agentName;
        Connection con = null;
        Statement stmt = null;
        ResultSet r = null;
        
        try{
            con = ConnectionDetails.getConnection();
            stmt = con.createStatement();
            String sql = "SELECT * FROM tblMember INNER JOIN tblAgent WHERE"
                    + " tblMember.agentID = tblAddress.agentID";
            r = stmt.executeQuery(sql);
            System.out.println(sql);

            //clear out the arrayList
            agentList.clear();
            
            //loop through the records and add them to the ArrayList
            while(r.next())
            {     
                
          
            }  
            
            stmt.close();
            con.close();
            
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
}
