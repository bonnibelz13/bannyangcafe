
package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Account;
import model.db;

/**
 *
 * @author User
 */
public class CreateOrderCtrl implements ActionListener {
    CreateOrderUI view;
    CheckoutCtrl checkoutCtrl;
    MainCtrl mainCtrl;
    
    private int user_id;
    File img_file;
    Account user;
    ArrayList <Menu>menuArr =new ArrayList<Menu>();

    DefaultTableModel tableModel;
    
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
        
        findUserID(user);
        loadTable(view.getMenuTable());
        
    }


    
    public JPanel getPanel(){
        return view.getPanel();
    }

    
    public File getImageFile(){
        return this.img_file;
        
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
        
        if (ev.getSource() == view.getAddBtn()) {
            int selectedRow = view.getMenuTable().getSelectedRow();
            
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Please Select the Menu.", "Warning", 2);
            }

            if (view.getQuantity().getText().isEmpty() || view.getQuantity().getText().equals("0")) {
                JOptionPane.showMessageDialog(null, "Menu Quantity is required.", "Warning", 2);
                
            }
            
            else {
                int quantity = Integer.parseInt(view.getQuantity().getText());
                if (quantity > 0) {
                    DefaultTableModel menuTableModel = (DefaultTableModel) view.getMenuTable().getModel();
                    DefaultTableModel orderTableModel = (DefaultTableModel) view.getOrderTable().getModel();

                    Object[] menuData = new Object[menuTableModel.getColumnCount() + 2]; // +2 เพิ่ม Quantity และ Total Price

                    for (int i = 0; i < menuTableModel.getColumnCount(); i++) {
                        menuData[i] = view.getMenuTable().getValueAt(selectedRow, i);
                    }

                    menuData[menuTableModel.getColumnCount()] = quantity;
                    menuData[menuTableModel.getColumnCount() + 1] = Double.parseDouble(menuData[menuTableModel.getColumnCount() - 1].toString()) * quantity; // คำนวณ Total Price

                    orderTableModel.addRow(menuData);

                    double total = 0.0;
                    for (int i = 0; i < orderTableModel.getRowCount(); i++) {
                        double price = (Double) orderTableModel.getValueAt(i, menuTableModel.getColumnCount() + 1); // ใช้คอลัมน์ `Total Price`
                        total += price;
                    }

                    view.getTotal().setText("Total (฿) : " + total);
                }
            }
        }
        
    }
    
    
}
