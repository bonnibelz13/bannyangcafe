
package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import model.Account;
import model.db;

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
    String transID;
    int user_id, order_id;
    Account user;
    
    
    //Default Constructure and Methods
    
    public PaymentChooseCtrl(PayMainCtrl paymentCtrl, CheckoutCtrl checkoutCtrl, Account user){
        this.mainCtrl = paymentCtrl;
        this.total = checkoutCtrl.getTotal();
        this.cash = checkoutCtrl.getCash();
        this.change = checkoutCtrl.getChange();
        this.orderTableModel = checkoutCtrl.getOrderTableModel();
        this.transID = checkoutCtrl.getPaymentID();
        this.user = user;
        
        ReceiptUI receipt = new ReceiptUI();
        receipt.getOrderTable().setModel(orderTableModel);
        
        this.receipt = receipt;

        
        initComponents();


    }
    
    public void initComponents() {
        view = new PaymentChooseUI();
        
        view.getCashBtn().addActionListener((ActionListener)this);
        view.getPromptBtn().addActionListener((ActionListener)this);
        
        findUserID(user);

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
        receipt.getTxtPane().setText(receipt.getTxtPane().getText()+"    Transaction ID: " + transID + "\n");
        
        
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
    
    
    public int newOrder(int paymentID) {
        String sql = "INSERT INTO order_bill (payment_id) VALUES (?)";
        Connection con = db.getConnection();
        PreparedStatement ps = null;
        int generatedOrderID = -1; // กำหนดค่าเริ่มต้น

        try {
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, paymentID);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected == 1) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    generatedOrderID = generatedKeys.getInt(1);
                    System.out.println("Inserted order with order_id: " + generatedOrderID);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Insert Failed.");
        }

        return generatedOrderID; // คืนค่า order_id ที่ได้รับ
    }
    
    public void findUserID(Account user){
        
        System.out.println(user.getUsername());
        //String
        Connection con = db.getConnection();
        String sql= ("SELECT id FROM user WHERE username = '" + user.getUsername()+"'" );

        try (Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);)
        {while(rs.next()){
            user_id = rs.getInt("id");
            System.out.println(user_id);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    

    }
    
    public void newOrderItem(int paymentID) {
        DefaultTableModel dt = (DefaultTableModel) orderTableModel;
        System.out.println(orderTableModel.getRowCount());

        // เรียกฟังก์ชัน newOrder และรับค่า order_id ที่สร้างขึ้น
        int order_id = newOrder(paymentID);

        for (int i = 0; i < dt.getRowCount(); i++) {
            String item = dt.getValueAt(i, 0).toString(); // item Name
            String qty = dt.getValueAt(i, 3).toString(); // item Qty
            String totalPrice = dt.getValueAt(i, 4).toString(); // item Total Price

            // select menu_id from menu
            String sql = "SELECT menu_id FROM menu WHERE menu_name = ? AND user_id = ?";
            Connection con = db.getConnection();
            PreparedStatement ps = null;

            try {
                ps = con.prepareStatement(sql);
                ps.setString(1, item); // ตั้งค่าค่า parameter ในคำสั่ง SQL
                ps.setInt(2, user_id);

                ResultSet menuResult = ps.executeQuery();

                if (menuResult.next()) {
                    int menu_id = menuResult.getInt("menu_id");
                    System.out.println("MenuID = " + menu_id);

                    String sql_insert = "INSERT INTO order_item (order_id, menu_id, item_qt, item_lineprice) VALUES (?,?,?,?)";

                    try {
                        ps = con.prepareStatement(sql_insert);
                        ps.setInt(1, order_id);
                        ps.setInt(2, menu_id);
                        ps.setInt(3, Integer.valueOf(qty));
                        ps.setFloat(4, Float.valueOf(totalPrice));

                        int rowsAffected = ps.executeUpdate();

                        System.out.println("Insert Order_Item to DB completed");
                        JOptionPane.showMessageDialog(null, "Successfully!");

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        System.out.println("Insert order_item Failed.");
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("Error fetching menu_id.");
            }
        }
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
                     
                    // Save to Database.
                    
                    int paymentID = 1;
                    newOrder(paymentID);
                    newOrderItem(paymentID);
                    
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
