
package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 *
 * @author baibu
 */
public class CheckoutCtrl {
    CheckoutUI view;
//    MainCtrl mainCtrl;
    
    public CheckoutCtrl(){
//        this.mainCtrl = mainCtrl;
        initComponents();
    }
    
    public void initComponents(){
        view = new CheckoutUI();
        view.setVisible(true);
        view.setLocationRelativeTo(null);
        
//        view.getBackBtn().addActionListener((ActionListener)this);
          
    }
    
    public JPanel getPanel(){
        return view.getPanel();
    }
    
//    public void actionPerformed(ActionEvent ev) {
//        if(ev.getSource()== view.getBackBtn()){
//            System.out.println("Creae Order Open...");
//            view.dispose();
////            mainCtrl.setView("CreateOrder", "null");
//        
//        }
//    }
}
