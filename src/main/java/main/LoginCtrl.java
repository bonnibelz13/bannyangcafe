
package main;

/**
 *
 * @author baibu
 */

import model.Account;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import com.google.firebase.FirebaseApp;

import java.io.IOException;
import model.AccountDao;

/**
 *
 * @author User
 */

public class LoginCtrl implements ActionListener {
    private LoginUI loginUI;
    private LoginModel loginModel;
    private Account user;
    AccountDao dao;
    
    public LoginCtrl() {

        loginModel = new LoginModel(dao);
        
        initComponents();
    }
    
    
    public void initComponents(){
     
        loginUI = new LoginUI();
        loginUI.setVisible(true);
        loginUI.setLocationRelativeTo(null);
        loginUI.getLoginbtn().addActionListener((ActionListener)this);
        loginUI.getSignupbtn().addActionListener((ActionListener) this);
        loginUI.getUsername().addActionListener((ActionListener) this);
        loginUI.getPassword().addActionListener((ActionListener) this);
        
        
        
        
    }
    @Override
    public void actionPerformed(ActionEvent ev){
        
        //-------------------
        // Press Login Button
        //-------------------
        
        if (ev.getSource() == loginUI.getLoginbtn()) {
            String username = loginUI.getUsername().getText();
            String password = loginUI.getPassword().getText();

            // check username and password in LoginModel
            
            loginModel.checkAccount(username, password).thenAccept(account -> {
                if (account != null) {
                    // Login Success
                    System.out.println("Login successful");
                    user = account;


                    new MainCtrl(user);
                    loginUI.dispose();
                } else {
                    System.out.println("Login failed");
                }
            });
        
        //---------------
        // Sign Up Button
        //---------------
        
        } else if(ev.getSource() == loginUI.getSignupbtn()){
            
            System.out.println("signup");
            new SignupCtrl();
            loginUI.dispose();
         
        }
    }    
    public static void main(String[] args){
        LoginCtrl loginCtrl = new LoginCtrl();
    }

}