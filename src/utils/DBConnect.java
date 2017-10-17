package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author marwen
 */

public class DBConnect {
    
    String login="root";
    String passwd="123456";
    String server ="jdbc:mysql://localhost:3306/pidev";
    
   static Connection mycnx;
    
    public DBConnect () {
        try 
        {
        mycnx=DriverManager.getConnection(server,login,passwd);
            System.err.println("ok");
        }
        catch (SQLException e)
            
        {
        
            System.err.println("error");
        }
        
}
    public static Connection getInstanceDB(){
        
        if (mycnx == null)
         new DBConnect();
        
        return mycnx;
        
    }
    
}
