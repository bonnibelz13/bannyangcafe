
package main;


//import com.google.firebase.FirebaseApp;
//import com.google.firebase.database.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.io.IOException;
import javax.swing.JOptionPane;
 
/**
 *
 * @author User
 */
public class SignupCtrl implements ActionListener {
    SignupUI signupUI;
    SignupModel signupModel;
    
    
    public SignupCtrl(){
//        try {
//            Connection.initFirebase();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
        
//        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
//        signupModel = new SignupModel(databaseReference);

        initComponents();
    }
    public void initComponents(){
        signupUI = new SignupUI();
        signupUI.setVisible(true);
        
        
        signupUI.setLocationRelativeTo(null);

                
        signupUI.getSignupbtn().addActionListener((ActionListener) this);
       // signupUI.getSignupbtn().setEnabled(false);
        
        signupUI.getEmail().addActionListener((ActionListener)this);
        signupUI.getUsername().addActionListener((ActionListener)this);
        signupUI.getPassword1().addActionListener((ActionListener) this);
        signupUI.getPassword2().addActionListener((ActionListener) this);
        
        signupUI.getLoginbtn().addActionListener((ActionListener) this);

        
        

        
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        if(ev.getSource() == signupUI.getSignupbtn()){
            
            
            String email = signupUI.getEmail().getText();
            String username = signupUI.getUsername().getText();
            
            String password = String.valueOf(signupUI.getPassword1().getPassword());
            String conpassword = String.valueOf(signupUI.getPassword2().getPassword());


            signupUI.getjLabel_Email_Message().setText("Please enter email.");
            signupUI.getjLabel_Username_Message().setText("Please enter username.");
            signupUI.getjLabel_Password_Message().setText("Please enter password.");

            signupUI.getjLabel_Email_Message().setVisible(false);
            signupUI.getjLabel_Username_Message().setVisible(false);
            signupUI.getjLabel_Password_Message().setVisible(false);
            signupUI.getjLabel_ConPassword_Message().setVisible(false);


            // check email and user that is not invalid format
            if (!signupModel.isEmailValid(email)) {
                // handle invalid email format
                signupUI.getjLabel_Email_Message().setVisible(true);
                signupUI.getjLabel_Email_Message().setText("Invalid email format!");
            }


            if (!signupModel.isUsernameValid(username)) {
                // handle invalid username format
                signupUI.getjLabel_Username_Message().setVisible(true);
                signupUI.getjLabel_Username_Message().setText("Invalid username format!");


            } if (password.trim().isEmpty()){
                signupUI.getjLabel_Password_Message().setVisible(true);



            } else if (!password.equals(conpassword)){
                signupUI.getjLabel_ConPassword_Message().setVisible(true);
                signupUI.getjLabel_ConPassword_Message().setText("The passwords do not match.");


            
                
                // Valid Email, Username, Password, Conpassword -> checkData in Firebase
            } else if (!email.trim().isEmpty() && !username.trim().isEmpty() && !password.trim().isEmpty() && (password.equals(conpassword))){
                
                signupModel.checkData(signupUI.getEmail().getText(), signupUI.getUsername().getText(), String.valueOf(signupUI.getPassword1().getPassword()), String.valueOf(signupUI.getPassword2().getPassword())).thenAccept(isValid -> {
                if (isValid.equals("Success")) {
                    // SignUp Success
                    JOptionPane.showMessageDialog(null, "Sign Up Successfully!");
                    signupUI.getEmail().setText("");
                    signupUI.getUsername().setText("");
                    signupUI.getPassword1().setText("");
                    signupUI.getPassword2().setText("");
                    
                    // Email is already existed
                } else if (isValid.equals("emailExisted")){
                    System.out.println("Email Existed");
                    signupUI.getjLabel_Email_Message().setVisible(true);
                    signupUI.getjLabel_Email_Message().setText("Email already exists!");
                    
                    
                    // Username is already existed
                } else if (isValid.equals("usernameExisted")) {
                    System.out.println("Username Existed");
                    signupUI.getjLabel_Username_Message().setVisible(true);
                    signupUI.getjLabel_Username_Message().setText("Username already exists!");
                }
                    

            });

            }

        }
        if(ev.getSource() == signupUI.getLoginbtn()){
            // don't forget to close old Firebase from this frame before open other Frame
//            FirebaseApp.getInstance().delete();
            
            new LoginCtrl();
            signupUI.dispose();
            
            
        }
        
       
    }
    public static void main(String[] args) {
        new SignupCtrl();
    }
    
    
}
