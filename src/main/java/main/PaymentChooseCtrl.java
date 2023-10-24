
package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author baibu
 */
public class PaymentChooseCtrl implements ActionListener{
    PaymentChooseUI view;
    PayMainCtrl mainCtrl;
    
    
    //Default Constructure and Methods
    
    public PaymentChooseCtrl(PayMainCtrl paymentCtrl) {
        this.mainCtrl = paymentCtrl;
        
        initComponents();


    }
    
    public void initComponents() {
        view = new PaymentChooseUI();

        
        view.getCashBtn().addActionListener((ActionListener)this);
        view.getPromptBtn().addActionListener((ActionListener)this);
    }
    
    public JPanel getPanel() {
        return view.getPanel();
    }
    
    public void actionPerformed(ActionEvent ae) {
        
        // cash button pressed.
        if (ae.getSource().equals(view.getCashBtn())) {
            
            // Pay Success -> Print Receipt.
            int option = JOptionPane.showConfirmDialog(null, "Do you want to print the receipt?", "Print", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                System.out.println("Printing...");
            }
        }
        
        // prompt button pressed.
        else if (ae.getSource().equals(view.getPromptBtn())) {

            System.out.println("Prompt Press.");
            mainCtrl.setView("PaybyPrompt", "PaymentChoose");

        }
        
    }
}
