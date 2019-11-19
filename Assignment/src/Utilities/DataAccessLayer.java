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
import static GUI.UpdateForm.stateLoad;
import static GUI.UpdateForm.packLoad;
import static GUI.UpdateForm.gender;
import static GUI.UpdateForm.type;
import static GUI.UpdateForm.agentID;
import static GUI.UpdateForm.txfEmail;
import static GUI.UpdateForm.txfFirst;
import static GUI.UpdateForm.txfID;
import static GUI.UpdateForm.txfLast;
import static GUI.UpdateForm.txfPhone;
import static GUI.UpdateForm.txfAddress;
import static GUI.UpdateForm.txfSuburb;
import static GUI.UpdateForm.txfPostcode;
import static GUI.UpdateForm.txfNoMember;
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
    
    public static void addSingleToDatabase(Single s){
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
            System.out.println(sql);
            
            if(r.next())
            { //found this member id in database
                JOptionPane.showMessageDialog(null, "This member id is already exist");
            }
            else
            {
                //insert data to member table
                sql = "INSERT INTO tblMember (memberID, first, last, gender, email, phone, package, baseFee, type, agentID) values"
                        + "('" + s.getId() + "','" + s.getName() + "','" + s.getLast() + "','" + s.getGender() + "','" 
                        + s.getEmail() + "','" + s.getPhone() + "','" + s.getPackLoad() + "','" + s.getFee() + "','" 
                        + s.getType() + "','" + s.getAgentID() + "')";
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
    
    public static void addFamilyToDatabase(Family f){
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
                        + f.getEmail() + "','" + f.getPhone() + "','" + f.getNoMembers() + "','" + f.getFee() + "','" 
                        + f.getType() + "','" + f.getAgentID() + "')";
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
    
    //Get Agent id from selected agent name
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
            String sql = "SELECT * FROM tblAgent WHERE first ='" + agentLoad + "'";
            //SELECT * FROM tblAgent WHERE CONCAT(first, ' ', last) LIKE '%Jon aa%'
            r = stmt.executeQuery(sql);
            
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
    
    public static String getAgentName(){
        
        String agentName;
        Connection con = null;
        Statement stmt = null;
        ResultSet r = null;
        try{
            con = ConnectionDetails.getConnection();
            stmt = con.createStatement();
            System.out.println("get Agent ID: " + agentID);
            String sql = "SELECT * FROM tblAgent WHERE agentID =" + agentID ;
       
            r = stmt.executeQuery(sql);
            System.out.println(sql);
            
            r.next();
            agentName = r.getString("first");
            System.out.println(agentName);
            
            stmt.close();
            con.close();
               
            return agentName;
            
        }catch(SQLException ex) {
            ex.printStackTrace();
            return agentName = "";
        }
    }
    
    
    public static void getDataFromDatabase(ArrayList<Members> list)
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
    
    public static void deleteMember(Members m){
        Connection con = null;
        Statement stmt = null;
             
        try{
            con = ConnectionDetails.getConnection();
            stmt = con.createStatement();
            
            String sql = "Delete from tblAddress where memberID=" + m.getId();
            stmt.executeUpdate(sql);
            
            sql = "Delete from tblMember where memberID=" + m.getId();
            stmt.executeUpdate(sql);

            stmt.close();
            con.close();
        } catch (SQLException ex){
            ex.printStackTrace();
        } finally {
            
        }
    }
    
    public static void updateMember(){
        Connection con = null;
        Statement stmt = null;
        
        try{
            con = ConnectionDetails.getConnection();
            stmt = con.createStatement();
            
            System.out.println("-- UPDATE --");
            System.out.println(txfFirst.getText());
            System.out.println(txfLast.getText());
            System.out.println(gender);
            System.out.println(txfPhone.getText());
            System.out.println(txfEmail.getText());
            System.out.println(packLoad);
            System.out.println(txfNoMember.getText());
            System.out.println(type);
            System.out.println(agentID);
            System.out.println(txfID.getText());
            System.out.println(txfAddress.getText());
            System.out.println(txfSuburb.getText());
            System.out.println(stateLoad);
            System.out.println(txfPostcode.getText());
            
            if(type.equals("Single")){
                String sql = "UPDATE tblMember SET first='" + txfFirst.getText()
                    + "',last='" + txfLast.getText() + "',gender='" + gender 
                    + "',phone='" + txfPhone.getText() + "',email='" + txfEmail.getText() 
                    + "',package='" + packLoad + "',type='" + type + "',agentID='" + agentID 
                    + "' WHERE memberId=" + txfID.getText();
            stmt.executeUpdate(sql);
            
            
            } else {
                String sql = "UPDATE tblMember SET first='" + txfFirst.getText()
                    + "',last='" + txfLast.getText() + "',gender='" + gender 
                    + "',phone='" + txfPhone.getText() + "',email='" + txfEmail.getText() 
                    + "',noMember='" + txfNoMember.getText() + "',type='" + type 
                    + "',agentID='" + agentID + "' WHERE memberId=" + txfID.getText();
            }
            
            String sql = "UPDATE tblAddress SET address='" + txfAddress.getText() + "',suburb='" 
                    + txfSuburb.getText() + "',state='" + stateLoad + "',postcode='" 
                    + txfPostcode.getText() + "' WHERE memberId=" + txfID.getText();
            stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Member details have been updated");
            
            stmt.close();
            con.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public static void restoreData(ArrayList<Members> restoredList){
        Connection con = null;
        Statement stmt = null;
        
        try{
            con = ConnectionDetails.getConnection();
            stmt = con.createStatement();
   
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
            
            //Delete All data in database
            String sql = "DELETE FROM tblAddress";
            stmt.executeUpdate(sql);
            
            sql = "DELETE FROM tblMember";
            stmt.executeUpdate(sql);
            
            stmt.close();
            con.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
        for(Members m : restoredList){
            String pack;
            int noMember;
            
            if(m instanceof Single){
                
                //Single s = New Single(m.getId());
                //Single s = New Single(m.getId(),m.getName(),m.getLast(),m.getGender(),m.getEmail(),m.getPhone(),m.getAddress(),m.getSuburb(),m.getState(),m.getPostcode(),m.getFee(),((Single) m).getPackLoad(),m.getType(),m.getAddress());
                //addSingleToDatabase(s);
            }
            if(m instanceof Family){
                Family f = (Family)m;
                noMember = f.getNoMembers();
            }
            
           
        }
    }
}
