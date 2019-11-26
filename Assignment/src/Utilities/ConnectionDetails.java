package Utilities;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Poonnamee
 * Date: 27/11/19
 * Connection details to connect to database
 */
public class ConnectionDetails {
    
    private static final String userName = "root"; // to use db
    private static final String passWord = "root";
    private static final String driver = "com.mysql.jdbc.Driver";
    
    private static final String url = "jdbc:mysql://localhost:8889/";
    private static final String db = "insuranceMembers";
    

    public static String getUserName() {
        return userName;
    }

    public static String getPassWord() {
        return passWord;
    }

    public static String getDriver() {
        return driver;
    }

    public static String getUrl() {
        return url + db + "?allowMultiQueries=true&autoReconnect=true";
    }

    public static Connection getConnection(){
        Connection con = null;
        
        try{
            Class.forName(ConnectionDetails.getDriver());
            con = DriverManager.getConnection(url + db + "?autoReconnect=true", userName, passWord);
        }
        catch(SQLException ex){
            ex.printStackTrace();
        } catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        return con;
    }
}
