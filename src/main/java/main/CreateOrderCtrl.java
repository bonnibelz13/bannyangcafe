package main;

import java.awt.event.*;
import java.io.*;
import java.sql.*;

import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.*;

/**
 *
 * @author User
 */
public class CreateOrderCtrl implements ActionListener {
    CreateOrderUI view;
    CheckoutCtrl checkoutCtrl;
    MainCtrl mainCtrl;
    
    private int user_id;
    Account user;

    
    public CreateOrderCtrl(MainCtrl mainCtrl, Account user){
        this.mainCtrl = mainCtrl;
        this.user = user;
        initComponents();
    }
    public void initComponents(){
        view = new CreateOrderUI();
        
        
        
        view.getAddBtn().addActionListener((ActionListener)this);
        view.getBackBtn().addActionListener((ActionListener) this);
        view.getCheckoutBtn().addActionListener((ActionListener)this);
        view.getQuantity().addActionListener((ActionListener)this);
        view.getRemoveBtn().addActionListener((ActionListener)this);
        view.getClearBtn().addActionListener((ActionListener)this);
        
        view.getCheckoutBtn().setVisible(false);
        
        findUserID(user);
        loadTable(view.getMenuTable());
        
    }


    
    public JPanel getPanel(){
        return view.getPanel();
    }

    
    
    
    //file input and output
    public ArrayList loadMenu(){
        try(FileInputStream fin = new FileInputStream("MenuData.dat");
            ObjectInputStream in = new ObjectInputStream(fin);){
            System.out.println("load menu data success");
            return ((ArrayList)in.readObject());
        }catch(IOException | ClassNotFoundException e){
            System.out.println(e);
            return new ArrayList();
        }
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
    
    
    public void loadTable(JTable table){

        Connection con = db.getConnection();
        String sql= ("SELECT * FROM menu WHERE user_id = '"+ user_id+"' and status_id = 1");
        try {Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            //table = view.getMenuTable();
            DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
            while(rs.next()){
                Object[] row = new Object[4];
                row[0] = rs.getBytes("menu_pic");
                row[1] = rs.getString("menu_name");
                row[2] = rs.getString("menu_des");
                row[3] = rs.getDouble("menu_price");
                tableModel.addRow(row);
            }
                ImageRenderer imageRenderer = new ImageRenderer();
                table.getColumnModel().getColumn(0).setCellRenderer(imageRenderer);
                table.getColumnModel().getColumn(0).setPreferredWidth(60);
                table.setRowHeight(60);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void updateTotal(DefaultTableModel orderTableModel) {
       double total = 0.0;
       for (int i = 0; i < orderTableModel.getRowCount(); i++) {
           double price = (Double) orderTableModel.getValueAt(i, orderTableModel.getColumnCount() - 1); // ใช้คอลัมน์ `Total Price`
           total += price;
       }
       view.getTotal().setText("Total (฿) : " + total);
       if (total <= 0.0) {
           view.getCheckoutBtn().setVisible(false);
       } else {
           view.getCheckoutBtn().setVisible(true);
       }
    }
    
    private double calculateTotal(DefaultTableModel orderTableModel) {
        double total = 0.0;
        for (int i = 0; i < orderTableModel.getRowCount(); i++) {
            double price = (Double) orderTableModel.getValueAt(i, orderTableModel.getColumnCount() - 1); // ใช้คอลัมน์ `Total Price`
            total += price;
        }
        return total;
    }
    
    
    @Override
    public void actionPerformed(ActionEvent ev) {
        
       
        //---------------
        //  BACK BUTTON.
        //---------------

        if (ev.getSource() == view.getBackBtn()){
            DefaultTableModel orderTableModel = (DefaultTableModel) view.getOrderTable().getModel();
            double total = calculateTotal(orderTableModel);

            
            // check if orderTable is not null to Warn user before back to HomeUI.
            
            if (total <= 0.0) {
                System.out.println("Create Order Open...");
                mainCtrl.setView("Home", "CreateOrder");
                
                
            } else {
                int option = JOptionPane.showConfirmDialog(null, "Do you want to go back? The order will be cleared.", "Confirm Back", JOptionPane.YES_NO_OPTION);

                if (option == JOptionPane.YES_OPTION) {
                    // Clear the order
                    orderTableModel.setRowCount(0);
                    view.getTotal().setText("Total (฿) : 0.0");
                    view.getCheckoutBtn().setVisible(false);

                    System.out.println("Create Order Open...");
                    mainCtrl.setView("Home", "CreateOrder");
                }
            }
        }
        
        
        //--------------------
        //  ADD MENU BUTTON.
        //--------------------

        if (ev.getSource() == view.getAddBtn()) {
            int selectedRow = view.getMenuTable().getSelectedRow();
            
            
            // not select Menu.
            
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Please Select the Menu.", "Warning", 2);
            }

            // not insert Quantity.
            
            if (view.getQuantity().getText().isEmpty() || view.getQuantity().getText().equals("0")) {
                JOptionPane.showMessageDialog(null, "Menu Quantity is required.", "Warning", 2);
                
            }
            
            // already selected the Menu and inserted Quantity.
            
            else {
                int quantity = Integer.parseInt(view.getQuantity().getText());
                if (quantity > 0) {
                    DefaultTableModel menuTableModel = (DefaultTableModel) view.getMenuTable().getModel(); // menuTable
                    DefaultTableModel orderTableModel = (DefaultTableModel) view.getOrderTable().getModel(); //orderTable

                    
                    // object for new menuData (for orderTable) orderTable has more 2 column (quantity, total price).
                    Object[] menuData = new Object[menuTableModel.getColumnCount() + 2]; // +2 เพิ่ม Quantity และ Total Price

                    
                    // start i=1 not involve image col from menuTable. So it'll be Name[1], Description[2], Price[3].
                    for (int i = 1; i < menuTableModel.getColumnCount(); i++) {
                        
                        // orderTable menuData will start adding at i=0 (Name, Description, Price, null, null).
                        menuData[i-1] = view.getMenuTable().getValueAt(selectedRow, i);
                    }

                    
                    // orderTable menuData 2 more column will be (Name, Description, Price, quantity, total price) at columncount-1 = quantity, at columncount = total price.
                    menuData[menuTableModel.getColumnCount()-1] = quantity;
                    
                    // Total price = col-2(Price from menuTable) * quantity.
                    menuData[menuTableModel.getColumnCount()] = Double.parseDouble(menuData[menuTableModel.getColumnCount() - 2].toString()) * quantity; // คำนวณ Total Price

                    
                    // add menuData object to orderTable.
                    orderTableModel.addRow(menuData);

                    
                    // change jLabel Total
                    double total = 0.0;
                    for (int i = 0; i < orderTableModel.getRowCount(); i++) {
                        double price = (Double) orderTableModel.getValueAt(i, menuTableModel.getColumnCount()); // ใช้คอลัมน์ `Total Price`
                        total += price;
                    }

                    view.getTotal().setText("Total (฿) : " + total);
                    
                    
                    // SET VISIBLE = TRUE CHECKOUT BUTTON. IF TOTAL > 0.0 BAHT.
                    if (total <= 0.0) {
                        view.getCheckoutBtn().setVisible(false);
                    } else {
                        view.getCheckoutBtn().setVisible(true);
                       
                    }

                }
            }
        }
       
        //-----------------
        //  REMOVE BUTTON.
        //-----------------
        
        if (ev.getSource() == view.getRemoveBtn()) {
            System.out.println("Remove Press");
            DefaultTableModel orderTableModel = (DefaultTableModel) view.getOrderTable().getModel();
            
            int selectedRow = view.getOrderTable().getSelectedRow();

            if (selectedRow != -1) {
                orderTableModel.removeRow(selectedRow);
                updateTotal(orderTableModel);
            }
        }
        
        
        
        
        //---------------------------------------
        //  CLEAR ALL MENU IN ORDERTABLE BUTTON.
        //---------------------------------------
        
        if (ev.getSource() == view.getClearBtn()){
            System.out.println("Clear All Menu Pressed");
            DefaultTableModel orderTableModel = (DefaultTableModel) view.getOrderTable().getModel();

            orderTableModel.setRowCount(0);

            view.getTotal().setText("Total (฿) : 0.0");

            view.getCheckoutBtn().setVisible(false);
        
        }
        
        
        //-------------------
        //  CHECKOUT BUTTON.
        //-------------------
        
        if (ev.getSource() == view.getCheckoutBtn()) {
            System.out.println("CheckOut Press");
            DefaultTableModel orderTableModel = (DefaultTableModel) view.getOrderTable().getModel();
            System.out.println(orderTableModel.getRowCount());
            
            double total = calculateTotal(orderTableModel);
            System.out.println(total);
            
            CheckoutCtrl checkoutCtrl = new CheckoutCtrl(orderTableModel, total);
            
        }
        
        

    }
    
    
}