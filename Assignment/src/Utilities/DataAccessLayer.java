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
import static GUI.UpdateForm.packLoad;
import static GUI.UpdateForm.gender;
import static GUI.UpdateForm.type;
import static GUI.UpdateForm.agentID;
import static GUI.UpdateForm.txfEmail;
import static GUI.UpdateForm.txfFirst;
import static GUI.UpdateForm.txfID;
import static GUI.UpdateForm.txfLast;
import static GUI.UpdateForm.txfPhone;
import static GUI.UpdateForm.txfNoMember;
import static GUI.UpdateForm.agentName;
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
    
    public static void addMemberToDatabase(String first, String last, String gender, String email, String phone, double totalFee, String packLoad, int noMember, String type, int agentID){
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
                    + "fee double,"
                    + "type varchar(50),"
                    + "agentID int,"
                    + "PRIMARY KEY (memberID),"
                    + "FOREIGN KEY (agentID) References tblAgent(agentID))";
            System.out.println(tblMember);        
            stmt.executeUpdate(tblMember);
            System.out.println("tblMember has been created");
            System.out.println(email);
            
            //check if this member is exist in database by email
            String sql = "SELECT * from tblMember where email='" + email + "'";
            r = stmt.executeQuery(sql);
            System.out.println(sql);
            
            if(r.next())
            { //found this member in database
                JOptionPane.showMessageDialog(null, "This email is already exist");
            }
           
            else if(type.equals("Single")){
                sql = "INSERT INTO tblMember (first, last, gender, email, phone, package, fee, type, agentID) values"
                        + "('" + first + "','" + last + "','" + gender + "','" + email + "','" + phone 
                        + "','" + packLoad + "'," + totalFee + ",'" + type + "'," + agentID + ")";
                stmt.executeUpdate(sql);
                System.out.println(sql);
                System.out.println("Added Single member to Database");
                JOptionPane.showMessageDialog(null, "Member Record successfully added");
                
            } else { //Family
                sql = "INSERT INTO tblMember (first, last, gender, email, phone, noMember, fee, type, agentID) values"
                        + "('" + first + "','" + last + "','" + gender + "','" + email + "','" 
                        + phone + "'," + noMember + "," + totalFee + ",'" + type + "'," + agentID + ")";
                stmt.executeUpdate(sql);
                System.out.println(sql);
                System.out.println("Added Family member to Database");
                JOptionPane.showMessageDialog(null, "Member Record successfully added");
            }
        } catch (SQLException sqlE) {
            sqlE.printStackTrace();
            System.err.println("ERROR: " + sqlE.getMessage());
            if(sqlE.getErrorCode() == MYSQL_DUPLICATE_PK){ //duplicate primary key
                JOptionPane.showMessageDialog(null, "Duplicate Member ID");
            }
        } catch (NullPointerException nEx){
            nEx.printStackTrace();
            System.err.println("ERROR: " + nEx.getMessage());
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
                JOptionPane.showMessageDialog(null, "Agent Record successfully added");
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
    
    //Get Agent id for updateForm
    public static int getAgentIDupdate()
    {
        int agentID;
        Connection con = null;
        Statement stmt = null;
        ResultSet r = null;
        
        try{
            con = ConnectionDetails.getConnection();
            stmt = con.createStatement();
            System.out.println("Agent Load: " + agentName);
            String sql = "SELECT * FROM tblAgent WHERE first ='" + agentName + "'";
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
            String sql = "SELECT * FROM tblMember";
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
                            r.getString("gender"),r.getString("email"),r.getString("phone"),
                            r.getDouble("fee"),r.getString("package"),r.getString("type"),r.getInt("agentID"))); 
                } else {
                    list.add(new Family(r.getInt("memberID"),r.getString("first"),r.getString("last"),
                            r.getString("gender"),r.getString("email"),r.getString("phone"),
                            r.getDouble("fee"),r.getInt("noMember"),r.getString("type"),r.getInt("agentID"))); 
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
            
            String sql = "Delete from tblMember where memberID=" + m.getId();
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
                stmt.executeUpdate(sql);
            }
            
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
   
            String sql = "DROP TABLE tblMember";
            stmt.executeUpdate(sql);
            System.out.println("tblMember was drop");
            
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
                    + "fee double,"
                    + "type varchar(50),"
                    + "agentID int,"
                    + "PRIMARY KEY (memberID),"
                    + "FOREIGN KEY (agentID) References tblAgent(agentID))";
            System.out.println(tblMember);        
            stmt.executeUpdate(tblMember);
            System.out.println("New tblMember has been created");
            
            stmt.close();
            con.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
        for(Members m : restoredList){
            String pack;
            int noMember;
            
            if(m instanceof Single){
                addMemberToDatabase(m.getName(),m.getLast(),m.getGender(),m.getEmail(),m.getPhone(),m.getFee(),((Single) m).getPackLoad(),0,m.getType(),m.getAgentID());
                
            }
            if(m instanceof Family){
                addMemberToDatabase(m.getName(),m.getLast(),m.getGender(),m.getEmail(),m.getPhone(),m.getFee(),"",((Family) m).getNoMembers(),m.getType(),m.getAgentID());
            }
        }
    }
}
