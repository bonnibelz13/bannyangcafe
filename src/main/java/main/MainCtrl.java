
package main;

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
        
        this.viewSaleReportCtrl = new ViewSaleReportCtrl(this);
        this.manageMenuCtrl = new ManageMenuCtrl(this);
        this.createOrderCtrl = new CreateOrderCtrl(this);
//        this.checkoutCtrl = new CheckoutCtrl(this);

        
        setView("Home", "null");
        
        mainUI.setLocationRelativeTo(null);
        mainUI.setVisible(true);
    }
    
    public ViewSaleReportCtrl getViewSaleRepoerCtrl() {
        return viewSaleReportCtrl;
    }
    public ManageMenuCtrl getManageMenuCtrl(){
        return manageMenuCtrl;
    }
    
    public void setView(String to, String from) {
        if (to.equals(new String("ViewSaleReport")) && from.equals("null")) {
            mainUI.remove(homeCtrl.getPanel());
            mainUI.add(viewSaleReportCtrl.getPanel());
            mainUI.revalidate();
            mainUI.repaint();
            System.out.println("In ViewSale");
        }
        else if (to.equals(new String("ManageMenu")) && from.equals("null")) {
            mainUI.remove(homeCtrl.getPanel());
            mainUI.add(manageMenuCtrl.getPanel());
            mainUI.revalidate();
            mainUI.repaint();
            System.out.println("In ManageMenu");
//        }
//        else if (to.equals(new String("CreateOrder")) && from.equals("null")) {
//            mainUI.remove(homeCtrl.getPanel());
//            mainUI.add(createOrderCtrl.getPanel());
//            mainUI.revalidate();
//            mainUI.repaint();
//            System.out.println("In CreateOrder");
//            
//            
        } else if (to.equals(new String("CreateOrder"))) {
            if (from.equals("null")) {
                mainUI.remove(homeCtrl.getPanel());
                
            } else if (from.equals(new String("Checkout"))) {
                mainUI.remove(checkoutCtrl.getPanel());
            }
            mainUI.add(createOrderCtrl.getPanel());
            mainUI.revalidate();
            mainUI.repaint();
            System.out.println("In CreateOrder");
        }
        else if (to.equals(new String("Home"))) {
            if (from.equals("null")) {
                mainUI.remove(mainUI.getPanel());
            }
            else if (from.equals(new String("ViewSaleReport"))) {
                mainUI.remove(viewSaleReportCtrl.getPanel());
            }
            else if (from.equals(new String("ManageMenu"))) {
                mainUI.remove(manageMenuCtrl.getPanel());
            }
            else if (from.equals(new String("CreateOrder"))) {
                mainUI.remove(createOrderCtrl.getPanel());
            }
            
            mainUI.add(homeCtrl.getPanel());
            mainUI.revalidate();
            mainUI.repaint();
        }
    }
     

}

