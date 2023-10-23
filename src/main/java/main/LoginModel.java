
package main;


import model.db;
import model.Account;


import java.sql.*;
import java.util.concurrent.CompletableFuture;

import javax.swing.JOptionPane;
import model.AccountDao;

/**
 *
 * @author baibu
 */

public class LoginModel {

    private Account user;
    AccountDao dao;

    
    public LoginModel(AccountDao dao) {
        this.dao = dao;

    }
   
    
    public CompletableFuture<Account> checkAccount(String username, String password) {
        CompletableFuture<Account> userFuture = new CompletableFuture<>();
        
        Connection con = db.getConnection();
        
        try {
            
            String sql = " SELECT * FROM user WHERE username=? AND password=? ";
            PreparedStatement pst = con.prepareCall(sql);
            
            pst.setString(1, username);
            pst.setString(2, password);
            
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                System.out.println("Login Successful!");
                Account user = new Account();
                user.setUsername(username);
                user.setPassword(password);
                
                userFuture.complete(user);
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect password!", "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println("Incorrect password!");
                userFuture.complete(null); 
            }
            
        } catch (Exception e) {
            
        }
        
        return userFuture;
        
    } 
//    public LoginModel(DatabaseReference mDatabase) {
//        this.mDatabase = mDatabase;
//    }
    
//    public CompletableFuture<Account> checkAccount(String username, String password) {
//        CompletableFuture<Account> userFuture = new CompletableFuture<>();
//
//        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
//        Query usernameQuery = mDatabase.orderByChild("username").equalTo(username);
//
//        usernameQuery.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists()) {
//                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
//                        Account account = userSnapshot.getValue(Account.class);
//
//                        if (account.getPassword().equals(password)) {
//                            
//                            user = account;
//                            System.out.println("Login Successful!");
//                            user.setUsername(username);
//                            user.setPassword(password);
//                            userFuture.complete(user);
//                        } else {
//                            JOptionPane.showMessageDialog(null, "Incorrect password!", "Error", JOptionPane.ERROR_MESSAGE);
//                            System.out.println("Incorrect password!");
//                            userFuture.complete(null); 
//                        }
//                    }
//                } else {
//                    userFuture.complete(null); 
//                    JOptionPane.showMessageDialog(null, "Username does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
//                    System.out.println("Username does not exist!");
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                userFuture.completeExceptionally(databaseError.toException());
//            }
//        });
//
//        return userFuture;
//    }


    public Account getUser() {
        return user;
    }
}