
package model;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author baibu
 */

public class db {
    
    public static final String username = "root";
    public static final String password = "";
    public static final String url = "jdbc:mysql://127.0.0.1:3306/cafe";
    public static Connection con = null;
    
    public static Connection getConnection(){

        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ""+ex, "", JOptionPane.WARNING_MESSAGE);
        }
        return con;
        
    }
    
}
