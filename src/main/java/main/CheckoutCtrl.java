
package main;

import java.awt.Color;
import java.awt.event.*;
import java.text.*;
import java.util.Date;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Account;


/**
 *
 * @author baibu
 */
public class CheckoutCtrl implements ActionListener {
    private CheckoutUI view;
    private DefaultTableModel orderTable;
    private Account user;
    private double total, cashAmount, change;
    private String paymentID;
    
    public CheckoutCtrl(DefaultTableModel orderTable, double total, Account user){
        this.orderTable = orderTable;
        this.total = total;
        this.user = user;

//        System.out.println(orderTable.getRowCount());

        initComponents();
        

    }
    
    public void initComponents(){
        view = new CheckoutUI();
        view.setVisible(true);
        view.setLocationRelativeTo(null);
        
        view.getPaymentBtn().setVisible(false);

        view.getPaymentBtn().addActionListener((ActionListener)this);
        
        // orderTable
        view.getOrderTable().setModel(orderTable);
        // Total
        view.getTotalTxt().setText(String.valueOf(total));
        
        // Payment ID
        paymentID = generatePaymentID();
        view.getPaymentIDtxt().setText(paymentID);
        
        // Payment Date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String currentDate = sdf.format(new Date());
        view.getPaymenyDatetxt().setText(currentDate);
        
        // action key release when insert Cash Text -> PRESS ENTER -> do calculateChange()
        view.getCashTxt().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    calculateChange();
                }
            }
        });
    
    }
    
    
    
    public JPanel getPanel(){
        return view.getPanel();
    }
    
    public DefaultTableModel getOrderTableModel() {
        return orderTable;
    }
    
    public double getTotal() {
        return total;
    }
    
    public double getCash(){
        return cashAmount;
    }
    
    public double getChange(){
        return change;
    }
    
    public static String generatePaymentID() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date now = new Date();
        return dateFormat.format(now);
    }
    
    public String getPaymentID(){
        return paymentID;
    }
    
    //-----------
    // Change cal.
    //-----------
    
    private void calculateChange() {
        try {

            this.cashAmount = Double.parseDouble(view.getCashTxt().getText());
            this.change = cashAmount - total;
            
            DecimalFormat df = new DecimalFormat("0.00");
            view.getChangeTxt().setText(df.format(change));
            
            // check change >= 0.0 then PAYMENT BUTTON APPEAR.
            if (change < 0.0) {
                view.getChangeTxt().setForeground(Color.red);
                view.getPaymentBtn().setVisible(false);
            } else {
                view.getChangeTxt().setForeground(Color.black);
                view.getPaymentBtn().setVisible(true);
            }
        } catch (NumberFormatException e) {
            view.getChangeTxt().setText("N/A");
            
            view.getPaymentBtn().setVisible(false);
        }
    }
    
    
    public void actionPerformed(ActionEvent ev) {
        if(ev.getSource()== view.getPaymentBtn()){
            new PayMainCtrl(this, user);
            System.out.println("PAYMENT PRESSED.");
            
            }
        
        }
    }
    
   
