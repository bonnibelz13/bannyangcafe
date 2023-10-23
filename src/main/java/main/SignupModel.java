
package main;

import model.db;
import model.Account;
import static banyang.Connection.generateUUID;
import java.sql.Statement;
//import com.google.firebase.database.*;
import java.util.concurrent.CompletableFuture;


import java.util.regex.*;
import javax.swing.JOptionPane;
import model.AccountDao;

/**
 *
 * @author baibu
 */

public class SignupModel {
    
//    private DatabaseReference mDatabase;
//    
//    public SignupModel(DatabaseReference mDatabase) {
//        this.mDatabase = mDatabase;
//    }
    AccountDao dao;
    
    public SignupModel(AccountDao dao) {
        this.dao = dao;

    }
    
    public static boolean isEmailValid(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    public static boolean isUsernameValid(String username) {
        String usernameRegex = "^[A-Za-z0-9_]+$";
        Pattern pattern = Pattern.compile(usernameRegex);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }
    

    private void registerNewUser(String email, String username, String password, String conpassword){
        Account user = new Account();
        
//        String id = generateUUID();
//        
//        user.setId_user(id);
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);

        
//        System.out.println(id);
        System.out.println(user.getEmail());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        
        if (dao.insertAccount(user)) {
            
            JOptionPane.showMessageDialog(null, "Successfully!");
        }

    }
    public CompletableFuture<String> checkData(String email, String username, String password, String conpassword) {
        
        CompletableFuture<String> isValidFuture = new CompletableFuture<>();
        
        if (dao.isEmailExist(email)) {
            isValidFuture.complete("emailExisted");
        }
        if (dao.isUsernameExist(username)){
            isValidFuture.complete("usernameExisted");
        } else {
            registerNewUser(email, username, password, conpassword);
            System.out.println("Sign Up Success");
            isValidFuture.complete("Success");
        }

        return isValidFuture;
    }
//    private void registerNewUser(String email, String username, String password, String conpassword){
//        
//        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
//        String id = generateUUID();
//
//        Account user = new Account();
//        user.setId_user(id);
//        user.setEmail(email);
//        user.setUsername(username);
//        user.setPassword(password);
//        user.setConPassword(conpassword);
//        
//        mDatabase.child(id).setValue(user, new DatabaseReference.CompletionListener() {
//            @Override
//            public void onComplete(DatabaseError error, DatabaseReference ref) {
//                
//            }
//        });
//        
//    }
//    public CompletableFuture<String> checkData(String email, String username, String password, String conpassword) {
//        
//        CompletableFuture<String> isValidFuture = new CompletableFuture<>();
//        
//        
//        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
//        
//
//        Query emailQuery = mDatabase.orderByChild("email").equalTo(email);
//        emailQuery.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists()) {
//                    // email already exists in the database
//                    // handle the error or show an error message to the user
//                    
//                    isValidFuture.complete("emailExisted");
//                    
//                } else {
//                    // email does not exist in the database, proceed to check username
//                    Query usernameQuery = mDatabase.orderByChild("username").equalTo(username);
//                    usernameQuery.addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(DataSnapshot dataSnapshot) {
//                            if (dataSnapshot.exists()) {
//                                // username already exists in the database
//                                // handle the error or show an error message to the user
//                                
//                                isValidFuture.complete("usernameExisted");
//
//                            } else {
//                                // username does not exist in the database, proceed to register the new user
//                                registerNewUser(email, username, password, conpassword);
//                                
//                                isValidFuture.complete("Success");
//
//                            }
//                        }
//
//                        @Override
//                        public void onCancelled(DatabaseError databaseError) {
//                            // handle the error or show an error message to the user
//                            System.out.println(databaseError.getDetails());
//                        }
//                    });
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                // handle the error or show an error message to the user
//                System.out.println(databaseError.getDetails());
//            }
//        });
//        return isValidFuture;
//    }
}
