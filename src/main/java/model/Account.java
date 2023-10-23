
package model;

/**
 *
 * @author baibu
 */

public class Account {

    private String username;
    private String email;
    private String password;


    public Account() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
        this("", "", "");
    }

    public Account(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
      
    }
  
        //-------------------------
        // GETTER AND SETTER.
        //-------------------------
    
    
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }

}