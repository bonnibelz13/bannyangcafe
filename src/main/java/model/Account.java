
package model;

/**
 *
 * @author baibu
 */
public class Account {
//    private String id_user;
    private String username;
    private String email;
    private String password;
//    private String conpassword;

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
    
    
//    public String getId_user() {
//        return id_user;
//    }
//    public void setId_user(String id_user) {
//        this.id_user = id_user;
//    }
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
//    public String getConPassword(){
//        return conpassword;
//    }
//    public void setConPassword(String conpass){
//        this.conpassword = conpassword;
//    }
}