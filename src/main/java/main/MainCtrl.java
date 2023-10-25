
package main;

import javax.swing.JOptionPane;
import model.Account;

/**
 *
 * @author Noppakorn
 */
public class MainCtrl {
    // Attributes
    MainUI mainUI;
    HomeCtrl homeCtrl;
    ManageMenuCtrl manageMenuCtrl;
    ViewSaleReportCtrl viewSaleReportCtrl;
    CreateOrderCtrl createOrderCtrl;
    CheckoutCtrl checkoutCtrl;
    Account user;
    
    // Constructor and Method
   public MainCtrl(Account user) {
        this.user = user;
        initComponents();
    }
    
    public void initComponents() {
        this.mainUI = new MainUI();
        this.homeCtrl = new HomeCtrl(this, user);
        
        this.viewSaleReportCtrl = new ViewSaleReportCtrl(this, user);
        this.manageMenuCtrl = new ManageMenuCtrl(this, user);
        this.createOrderCtrl = new CreateOrderCtrl(this, user);
//        this.checkoutCtrl = new CheckoutCtrl(this);

        
        setView("Home", "null");
        
        mainUI.setLocationRelativeTo(null);
        mainUI.setVisible(true);
    }
    
    public ViewSaleReportCtrl getViewSaleReportCtrl() {
        return viewSaleReportCtrl;
    }
    public ManageMenuCtrl getManageMenuCtrl(){
        return manageMenuCtrl;
    }
    
    public void logout() {
        int option = JOptionPane.showConfirmDialog(null, "Do you want to Logout?", "Confirm Logout", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            // Close the MainUI
            mainUI.dispose();


            // Open the LoginUI
            LoginCtrl loginCtrl = new LoginCtrl();
        }
    }
    
    
    public void setView(String to, String from) {
        
        // Home -> ViewSaleReport UI
        if (to.equals(new String("ViewSaleReport")) && from.equals("null")) {
            this.viewSaleReportCtrl = new ViewSaleReportCtrl(this, user);
            mainUI.remove(homeCtrl.getPanel());
            mainUI.add(viewSaleReportCtrl.getPanel());
            mainUI.revalidate();
            mainUI.repaint();
            System.out.println("In ViewSale");
        }
        // Home -> ManageMenu UI
        else if (to.equals(new String("ManageMenu")) && from.equals("null")) {
            this.manageMenuCtrl = new ManageMenuCtrl(this, user);
            mainUI.remove(homeCtrl.getPanel());
            mainUI.add(manageMenuCtrl.getPanel());
            mainUI.revalidate();
            mainUI.repaint();
            System.out.println("In ManageMenu");
       
        // Home -> CreateOrder UI
        } else if (to.equals(new String("CreateOrder"))) {
            if (from.equals("null")) {
                mainUI.remove(homeCtrl.getPanel());
                this.createOrderCtrl = new CreateOrderCtrl(this, user);
                
            // CreateOrder UI -> Checkout UI
            } else if (from.equals(new String("Checkout"))) {
                mainUI.remove(checkoutCtrl.getPanel());
            }
            mainUI.add(createOrderCtrl.getPanel());
            mainUI.revalidate();
            mainUI.repaint();
            System.out.println("In CreateOrder");
        }
        
        // IDK??
        else if (to.equals(new String("ManageMenu"))){
            if(from.equals("ManageMenu")){
                mainUI.remove(manageMenuCtrl.getPanel());
                this.manageMenuCtrl = new ManageMenuCtrl(this, user);
                mainUI.add(this.manageMenuCtrl.getPanel());
                mainUI.revalidate();
                mainUI.repaint();
            }
        }
        
        // Back To Home UI
        else if (to.equals(new String("Home"))) {
            
            // Main -> Home UI
            if (from.equals("null")) {
                mainUI.remove(mainUI.getPanel());
            }
            
            // ViewSaleReport -> Home
            else if (from.equals(new String("ViewSaleReport"))) {
                mainUI.remove(viewSaleReportCtrl.getPanel());
            }
            
            // ManageMenu -> Home
            else if (from.equals(new String("ManageMenu"))) {
                mainUI.remove(manageMenuCtrl.getPanel());
            }
            
            // CreateOrder -> Home
            else if (from.equals(new String("CreateOrder"))) {
                mainUI.remove(createOrderCtrl.getPanel());
            }
            
            
            // Repaint Home UI
            mainUI.add(homeCtrl.getPanel());
            mainUI.revalidate();
            mainUI.repaint();
        }

    }
     

}

