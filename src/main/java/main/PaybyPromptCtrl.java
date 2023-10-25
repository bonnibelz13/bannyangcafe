
package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import model.Account;
import model.db;

/**
 *
 * @author baibu
 */
public class PaybyPromptCtrl implements ActionListener{
    PaybyPromptUI view;
    PayMainCtrl mainCtrl;
    CheckoutCtrl checkoutCtrl;
    DefaultTableModel orderTableModel;
    Account user;
    int user_id, order_id;
    
    public PaybyPromptCtrl(PayMainCtrl paymentCtrl, CheckoutCtrl checkoutCtrl, Account user){
        
        this.mainCtrl = paymentCtrl;
        this.checkoutCtrl = checkoutCtrl;
        this.orderTableModel = checkoutCtrl.getOrderTableModel();
        this.user = user;
        
        
        initComponents();
    }
    
    public void initComponents(){
        view = new PaybyPromptUI();

        view.getBackBtn().addActionListener((ActionListener)this);
        view.getPrintBtn().addActionListener((ActionListener)this);
        
        findUserID(user);

    }
    
    public JPanel getPanel() {
        return view.getPanel();
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
                
                PaymentChooseCtrl paymentChooseCtrl = new PaymentChooseCtrl(mainCtrl, checkoutCtrl, user);
                paymentChooseCtrl.drawBill();
                ReceiptUI receipt = paymentChooseCtrl.getReceiptUI();
                
                try {
                     receipt.getTxtPane().print();
                     // Save to Database.
                    int paymentID = 2;
                    newOrder(paymentID);
                    newOrderItem(paymentID);
                    
                } catch (Exception e){
                    
                }
                
                
                
                
            }
        }
    }
}
