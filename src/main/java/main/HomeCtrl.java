
package main;


import model.Account;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author baibu
 */
public class HomeCtrl implements ActionListener {
    //Attributes
    HomeUI view;
    MainCtrl mainCtrl;
    Account user;


    

    

    //Default Constructure and Methods
    
    public HomeCtrl(MainCtrl mainCtrl, Account user) {
        this.mainCtrl = mainCtrl;
        this.user = user;
        initComponents();


    }
    
    public void initComponents() {
        view = new HomeUI();
        view.getCurrentusername().setText("welcome, " + user.getUsername());
        view.getBtnCreateOrder().addActionListener((ActionListener) this);
        view.getBtnManageMenu().addActionListener((ActionListener) this);
        view.getBtnSaleReport().addActionListener((ActionListener) this);
        view.getBtnLogout().addActionListener((ActionListener) this);
        
        
        System.out.println(user.getPassword());
        
        // Date Time in HomeUI
        setDateTime();
    }
    
    public JPanel getPanel() {
        return view.getPanel();
    }
    
    // Date Time in HomeUI
    private void setDateTime() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                        
                    }
                    Date date = new Date();
                    SimpleDateFormat tf = new SimpleDateFormat("hh:mm:ss aa");
                    SimpleDateFormat df = new SimpleDateFormat("EEEE, yyyy/MM/dd");
                    String time = tf.format(date);
                    view.getjTime().setText(time.split(" ")[0]);
                    view.getjPMAM().setText(time.split(" ")[1]);
                    view.getjDate().setText(df.format(date));
                    
                    
                }
            }
        }).start();
    }
    
    
    public void actionPerformed(ActionEvent ae) {
        
        if (ae.getSource().equals(view.getBtnLogout())) {
            mainCtrl.logout();
        }
        
        
        
        if (ae.getSource().equals(view.getBtnCreateOrder())) {

            System.out.println("Creae Order Open...");
            mainCtrl.setView("CreateOrder", "null");

            System.out.println("Create Order Open...");

        }
        else if (ae.getSource().equals(view.getBtnManageMenu())) {
//            mainCtrl.switchToManageMenu();
            System.out.println("Manage Menu Open...");
            mainCtrl.setView("ManageMenu", "null");
        }
        else if (ae.getSource().equals(view.getBtnSaleReport())) {
            String password = JOptionPane.showInputDialog(null, "Please Enter the Password:");

            String userPassword = user.getPassword();
            System.out.println(userPassword);

            if (password != null) {
                if (password.equals(userPassword)) {
                    mainCtrl.setView("ViewSaleReport", "null");
                    System.out.println("Sale's Report Open...");
                } else {
                    JOptionPane.showMessageDialog(null, "Wrong Password!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    // Running Part
}