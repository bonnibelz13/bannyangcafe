/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.Image;
import java.io.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import model.Account;
import model.db;

/**
 *
 * @author User
 */
public class ManageMenuModel {
        private ManageMenuUI view;
   // private UpdateMenuUI updateView;
    private byte[] imgPath;
    private JTable table;
    private int user_id;
    private File img_file;

    public ManageMenuModel(){
        this(null);
    }
    public ManageMenuModel(ManageMenuUI view){
        this.view = view;
        //this.updateView = updateView;
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
    public void UploadPicture() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("4 supported extensions", "jpg", "jpeg", "gif", "png");
        fileChooser.setFileFilter(filter);
        int selected = fileChooser.showOpenDialog(null);

        if (selected == JFileChooser.APPROVE_OPTION) {
            File img_file = fileChooser.getSelectedFile();
            String getSelectedImage = img_file.getAbsolutePath();
            ImageIcon img = new ImageIcon(getSelectedImage);
            
            // img to fit the jlabel
            
            view.getImageLabel().setIcon(new ImageIcon(img.getImage().getScaledInstance(250, 210, Image.SCALE_SMOOTH)));
            this.img_file = img_file;
    //this.imgPath = getSelectedImage;

        }
    }
    public File getImageFile(){
        return this.img_file;
        
    }
//    public byte[] getImagePath(){
//        return this.imgPath;
//    }
    public ImageIcon setMenuImg(String imgPath){
        ImageIcon img = new ImageIcon(imgPath);
        return new ImageIcon(img.getImage().getScaledInstance(60,  60, Image.SCALE_SMOOTH));
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
    public boolean saveMenu(Menu menu){
//        try(FileOutputStream fOut = new FileOutputStream("MenuData.dat");
//            ObjectOutputStream oout = new ObjectOutputStream(fOut);){
//            oout.writeObject(menu);
//            System.out.println("save menu data success");
//        }catch(IOException | IndexOutOfBoundsException e){
//            System.out.println(e); 
//        }
        String sql = "INSERT INTO menu(user_id, menu_name, menu_des,menu_price, menu_pic, status_id)VALUES(?,?,?,?,?,?)";
        Connection con = db.getConnection();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, user_id);
            ps.setString(2, menu.getName());
            ps.setString(3, menu.getDescription());
            ps.setDouble(4, Double.parseDouble(menu.getPrice()));
            ps.setBytes(5, menu.getImage());
            ps.setInt(6, 1);
            
            int rowsAffected = ps.executeUpdate();
            
            return rowsAffected > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
//
    public boolean checkMenu(String name){
//        boolean ischecked = true;
//        ArrayList<Menu> menuArr = loadMenu();
//        for(int i = 0; i< menuArr.size() && !menuArr.isEmpty(); i++){
//            if(menuArr.get(i).getName().equals(name)){
//                ischecked = false;
//            }
//        }
//        return ischecked;
        Connection con = db.getConnection();
        String sql= ("SELECT menu_name FROM menu WHERE menu_name = '" + name+"' AND user_id ='"+user_id+"'");

        try (Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);)
        {while(rs.next()){
                System.out.println("already has this menu in db");
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return true;
    }
    
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    
    public void loadTable(JTable table){
//        table = view.getMenuTable();
//        DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
//        for(int i = 0; i < menuData.size() && !menuData.isEmpty(); i++){
//            int row = table.getRowCount();
//            tableModel.addRow(new Object[0]);
//            tableModel.setValueAt(menuData.get(i).getImage(),row, 0);
//            tableModel.setValueAt(menuData.get(i).getName(),row, 1);
//            tableModel.setValueAt((menuData.get(i)).getDescription(), row, 2);
//            tableModel.setValueAt((menuData.get(i)).getPrice(), row, 3);
//            ImageRenderer imageRenderer = new ImageRenderer();
//            view.getMenuTable().getColumnModel().getColumn(0).setCellRenderer(imageRenderer);
//            view.getMenuTable().getColumnModel().getColumn(0).setPreferredWidth(60);
//            view.getMenuTable().setRowHeight(60);
//        }
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

    public void addMenuIntoTable(Menu menu){
        DefaultTableModel tableModel = (DefaultTableModel)view.getMenuTable().getModel();
        tableModel.addRow(new Object[]{menu.getImage(),menu.getName(), menu.getDescription(), menu.getPrice()});
    }
    public void deleteMenu(String name){
//        ArrayList<Menu> menuArr = loadMenu();
//                try{
//                        int index = -1;
//                        for (int i = 0; i < menuArr.size(); i++){
//                            if ((menuArr.get(i)).getName().equals(name)){
//                                index = i;
//                                break;
//                            }
//                        }
//                    menuArr.remove(index);
//                    saveMenu(<Menu>menuArr);
//                }catch(IndexOutOfBoundsException ex){
//                        System.out.println("Menu not found");
//                }
//                
//                System.out.println("delete menu success");
        
//        try(FileOutputStream fOut = new FileOutputStream("MenuData.dat");
//            ObjectOutputStream oout = new ObjectOutputStream(fOut);){
//            oout.writeObject(menu);
//            System.out.println("save menu data success");
//        }catch(IOException | IndexOutOfBoundsException e){
//            System.out.println(e); 
//        }
        String sql = ("UPDATE menu SET status_id = 2 WHERE menu_name = '"+ name+"'");
        Connection con = db.getConnection();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            int rowsAffected = ps.executeUpdate();
            System.out.println("rowsAffected: "+ rowsAffected);
            //return rowsAffected > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
           // return false;
        
    }
       
        
    }
    //update table
    public Menu findMenu(String name){
        Connection con = db.getConnection();
        String sql= ("SELECT * FROM menu WHERE menu_name = '" + name+"' AND user_id ='"+this.user_id+"'");

        try (Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);)
        { Menu menu = new Menu();
            while(rs.next()){
            menu.setName(rs.getString("menu_name"));
            menu.setDescription(rs.getString("menu_des"));
            menu.setPrice(String.valueOf(rs.getDouble("menu_price")));
            menu.setImage(rs.getBytes("menu_pic"));
            }
            return menu;
        }catch (SQLException ex) {
            ex.printStackTrace();
            
            return null;
        }
        
    }
    
    
}
