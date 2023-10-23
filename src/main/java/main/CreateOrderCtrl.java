
package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 *
 * @author User
 */
public class CreateOrderCtrl implements ActionListener {
    CreateOrderUI view;
    CheckoutCtrl checkoutCtrl;
    MainCtrl mainCtrl;
    
    public CreateOrderCtrl(MainCtrl mainCtrl){
        this.mainCtrl = mainCtrl;
        initComponents();
    }
    public void initComponents(){
        view = new CreateOrderUI();
        view.getAddBtn().addActionListener((ActionListener)this);
        view.getBackBtn().addActionListener((ActionListener) this);
        view.getCheckoutBtn().addActionListener((ActionListener)this);
        view.getQuantity().addActionListener((ActionListener)this);
        
        
    }

    
    public JPanel getPanel(){
        return view.getPanel();
    }
    @Override
    public void actionPerformed(ActionEvent ev) {
        if(ev.getSource()== view.getBackBtn()){
            System.out.println("Creae Order Open...");
            mainCtrl.setView("Home", "CreateOrder");
        
        }
        if (ev.getSource() == view.getCheckoutBtn()) {
            System.out.println("CheckOut Press");
            new CheckoutCtrl();
//            mainCtrl.setView("CreateOrder", "Checkout");
        }
        
    }
    
    
}
