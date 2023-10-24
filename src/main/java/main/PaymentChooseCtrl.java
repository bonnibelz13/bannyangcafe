
package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.checkerframework.checker.units.qual.g;

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
    
    
    //Default Constructure and Methods
    
    public PaymentChooseCtrl(PayMainCtrl paymentCtrl, CheckoutCtrl checkoutCtrl){
        this.mainCtrl = paymentCtrl;
        this.total = checkoutCtrl.getTotal();
        this.cash = checkoutCtrl.getCash();
        this.change = checkoutCtrl.getChange();
        this.orderTableModel = checkoutCtrl.getOrderTableModel();
        
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
        receipt.getTxtPane().setText(receipt.getTxtPane().getText()+"               \n");
        
        
        Date dd = new Date();
        SimpleDateFormat datef = new SimpleDateFormat("yyyy-mm-dd");
        SimpleDateFormat timef = new SimpleDateFormat("hh:mm:ss");
        String date = datef.format(dd);
        String time = timef.format(dd);
        
        
        
        receipt.getTxtPane().setText(receipt.getTxtPane().getText()+"\t         Date: " + date + "     Time: " + time + "\n"); 
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
        receipt.getTxtPane().setText(receipt.getTxtPane().getText()+"    Total\t\t\t\t" + total + " Baht\n");
        receipt.getTxtPane().setText(receipt.getTxtPane().getText()+"-----------------------------------------------------------------------------------------------------------\n");
        receipt.getTxtPane().setText(receipt.getTxtPane().getText()+"    Paid\t\t\t\t" + cash + " Baht\n");
        receipt.getTxtPane().setText(receipt.getTxtPane().getText()+"    Change\t\t\t\t" + change + " Baht\n");
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
               
                
//                MessageFormat header = new MessageFormat("*** BannyangCha Cafe***" + "\n" + "Total: " + total + " " + "Baht.");
//                MessageFormat footer = new MessageFormat("Page{0, number, integer}");
//
//                ReceiptUI receipt = new ReceiptUI();
//                receipt.getOrderTable().setModel(orderTableModel);
//                drawBill();
//                System.out.println(receipt.getOrderTable().getRowCount());
//                
//                try {
//                    receipt.getOrderTable().print(JTable.PrintMode.FIT_WIDTH, header, footer);
//                } catch (PrinterException ex) {
//                    Logger.getLogger(PaymentChooseCtrl.class.getName()).log(Level.SEVERE, null, ex);
//                }
            }
        }
        
        
        // prompt button pressed.
        else if (ae.getSource().equals(view.getPromptBtn())) {

            System.out.println("Prompt Press.");
            mainCtrl.setView("PaybyPrompt", "PaymentChoose");

        }
        
    }
}
