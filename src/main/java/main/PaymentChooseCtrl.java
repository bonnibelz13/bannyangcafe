
package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author baibu
 */
public class PaymentChooseCtrl implements ActionListener{
    PaymentChooseUI view;
    PayMainCtrl mainCtrl;
    DefaultTableModel orderTableModel;
    ReceiptUI receipt;
    double total, cash, change;
    String paymentID;
    
    
    //Default Constructure and Methods
    
    public PaymentChooseCtrl(PayMainCtrl paymentCtrl, CheckoutCtrl checkoutCtrl){
        this.mainCtrl = paymentCtrl;
        this.total = checkoutCtrl.getTotal();
        this.cash = checkoutCtrl.getCash();
        this.change = checkoutCtrl.getChange();
        this.orderTableModel = checkoutCtrl.getOrderTableModel();
        this.paymentID = checkoutCtrl.getPaymentID();
        
        ReceiptUI receipt = new ReceiptUI();
        receipt.getOrderTable().setModel(orderTableModel);
        
        this.receipt = receipt;

        
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
    
    public ReceiptUI getReceiptUI() {
        return receipt;
    }
    public void drawBill(){
        receipt.getTxtPane().setText("= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = \n");
        receipt.getTxtPane().setText(receipt.getTxtPane().getText()+"               ");
        receipt.getTxtPane().setText(receipt.getTxtPane().getText()+" \t\t   BANNYANGCHA. CAFE\n");
        receipt.getTxtPane().setText(receipt.getTxtPane().getText()+"\t\t         Tel: 021111111\n");
        receipt.getTxtPane().setText(receipt.getTxtPane().getText()+"    Payment ID: " + paymentID + "\n");
        
        
        Date dd = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat tf = new SimpleDateFormat("hh:mm:ss aa");
        String date = df.format(dd);
        String time = tf.format(dd);
        
        
        
        receipt.getTxtPane().setText(receipt.getTxtPane().getText()+"    Date: " + date + "\t\t\t           Time: " + time + "\n"); 
        receipt.getTxtPane().setText(receipt.getTxtPane().getText()+"= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = \n");
        receipt.getTxtPane().setText(receipt.getTxtPane().getText()+"  Item\t(Qty.)\t\t\tPrice (Baht.)\n");
        receipt.getTxtPane().setText(receipt.getTxtPane().getText()+"-----------------------------------------------------------------------------------------------------------\n");
        
        DefaultTableModel dt = (DefaultTableModel) orderTableModel;
        
        for (int i=0; i<dt.getRowCount(); i++) {
            
            String item = dt.getValueAt(i, 0).toString(); // item Name
            String qty = dt.getValueAt(i, 3).toString(); // item Qty
            String totalPrice = dt.getValueAt(i, 4).toString(); // item Total Price
            
            receipt.getTxtPane().setText(receipt.getTxtPane().getText()+ item);
            receipt.getTxtPane().setText(receipt.getTxtPane().getText()+ " x" + qty + String.format("%-90s", ""));
            receipt.getTxtPane().setText(receipt.getTxtPane().getText()+ "\t\t\t\t\t" +totalPrice + "\n");
        }
        
        receipt.getTxtPane().setText(receipt.getTxtPane().getText()+"-----------------------------------------------------------------------------------------------------------\n");
        receipt.getTxtPane().setText(receipt.getTxtPane().getText()+"    Total\t\t\t\t" + total + " \n");
        receipt.getTxtPane().setText(receipt.getTxtPane().getText()+"-----------------------------------------------------------------------------------------------------------\n");
        receipt.getTxtPane().setText(receipt.getTxtPane().getText()+"    Paid\t\t\t\t" + cash + " \n");
        receipt.getTxtPane().setText(receipt.getTxtPane().getText()+"    Change\t\t\t\t" + change + " \n");
        receipt.getTxtPane().setText(receipt.getTxtPane().getText()+"-----------------------------------------------------------------------------------------------------------\n");


    }
    
    public void actionPerformed(ActionEvent ae) {
        
        // cash button pressed.
        if (ae.getSource().equals(view.getCashBtn())) {
            int option = JOptionPane.showConfirmDialog(null, "Do you want to print the receipt?", "Print", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                System.out.println("Printing...");
                drawBill();
                try {
                     receipt.getTxtPane().print();
                } catch (Exception e){
                    
                }
               
                
            }
        }
        
        
        // prompt button pressed.
        else if (ae.getSource().equals(view.getPromptBtn())) {

            System.out.println("Prompt Press.");
            mainCtrl.setView("PaybyPrompt", "PaymentChoose");

        }
        
    }
}
