
package main;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        
        System.out.println(user.getPassword());
        
    }
    
    public JPanel getPanel() {
        return view.getPanel();
    }
    
    public void actionPerformed(ActionEvent ae) {
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