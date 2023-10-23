
package model;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author baibu
 */

public class AccountDao {
    
    Connection con = db.getConnection();
    PreparedStatement ps;
    Statement st;
    ResultSet rs;
    
    
    public boolean isUsernameExist(String username){
        try {
            ps = con.prepareStatement(" SELECT * FROM user WHERE username=?");
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception ex) {
            Logger.getLogger(AccountDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean isEmailExist(String email){
        try {
            ps = con.prepareStatement(" SELECT * FROM user WHERE email=?");
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception ex) {
            Logger.getLogger(AccountDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean insertAccount(Account account){
        String sql = "INSERT INTO user (email, username, password) VALUES (?,?,?)";
        Connection con = db.getConnection();
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(sql);

            ps.setString(1, account.getEmail());
            ps.setString(2, account.getUsername());
            ps.setString(3, account.getPassword());
            
            int rowsAffected = ps.executeUpdate();
            
            return rowsAffected > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        
    }
}
