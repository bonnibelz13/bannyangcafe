
package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author baibu
 */
public class PaybyPromptCtrl implements ActionListener{
    PaybyPromptUI view;
    PayMainCtrl mainCtrl;
    CheckoutCtrl checkoutCtrl;
    
    public PaybyPromptCtrl(PayMainCtrl paymentCtrl, CheckoutCtrl checkoutCtrl){
        
        this.mainCtrl = paymentCtrl;
        this.checkoutCtrl = checkoutCtrl;
        
        initComponents();
    }
    
    public void initComponents(){
        view = new PaybyPromptUI();

        view.getBackBtn().addActionListener((ActionListener)this);
        view.getPrintBtn().addActionListener((ActionListener)this);

    }
    
    public JPanel getPanel() {
        return view.getPanel();
    }
    
    public void actionPerformed(ActionEvent ev) {
        
        // Back
        if (ev.getSource().equals(view.getBackBtn())) {
            mainCtrl.setView("PaymentChoose", "PaybyPrompt");
        }
        
        
        // Print Receipt
        else if (ev.getSource().equals(view.getPrintBtn())) {
            
            // Pay Success -> Print Receipt.
            int option = JOptionPane.showConfirmDialog(null, "Do you want to print the receipt?", "Print", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                System.out.println("Printing...");
                
                PaymentChooseCtrl paymentChooseCtrl = new PaymentChooseCtrl(mainCtrl, checkoutCtrl);
                paymentChooseCtrl.drawBill();
                ReceiptUI receipt = paymentChooseCtrl.getReceiptUI();
                
                try {
                     receipt.getTxtPane().print();
                } catch (Exception e){
                    
                }
                
                
            }
        }
    }
}
