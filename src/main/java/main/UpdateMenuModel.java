/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import model.db;

/**
 *
 * @author User
 */
public class UpdateMenuModel {
    UpdateMenuUI view;
    String imgPath;
    int menu_id;
    File file;
    
    
    public UpdateMenuModel(UpdateMenuUI view, Menu menu){
        this.view = view;
        //this.file = menu.getImage();
        this.menu_id = menuId(menu);
    }
    public void updateImg(){
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("4 supported extensions", "jpg", "jpeg", "gif", "png");
        fileChooser.setFileFilter(filter);
        int selected = fileChooser.showOpenDialog(null);

        if (selected == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String getSelectedImage = file.getAbsolutePath();
            ImageIcon img = new ImageIcon(getSelectedImage);
            
            this.file = file;
          
            view.getImgLabel().setIcon(new ImageIcon(img.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
            //this.imgPath = getSelectedImage;

        }
    }
    public File getImageFile(){
        return this.file;
    }
    public int menuId(Menu menu){
        String name = menu.getName();
        int menu_id =0;
        Connection con = db.getConnection();
        String sql_id = "SELECT menu_id FROM menu WHERE menu_name = '"+name +"'";
        try {Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql_id);
            //table = view.getMenuTable();
            
            while(rs.next()){
                menu_id = rs.getInt("menu_id");
                System.out.println(menu_id);
            }
            return menu_id;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return menu_id;
        }
    }
    public boolean updateMenu(Menu menu){
        String name = menu.getName();
        Connection con = db.getConnection();
//        String sql_id = "SELECT menu_id FROM menu WHERE menu_name = '"+name +"'";
//        try {Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery(sql_id);
//            //table = view.getMenuTable();
//            while(rs.next()){
//                menu_id = rs.getInt("menu_id");
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
        
        //update table
        PreparedStatement ps = null;
        try{
        //    byte[] img = this.file;
            String sql = "UPDATE menu SET menu_name = ?, menu_des = ?, menu_price = ?, menu_pic = ? Where menu_id = ?";
            ps = con.prepareStatement(sql);
        //    ps.setInt(1, menu_id);
            ps.setString(1, menu.getName());
            ps.setString(2, menu.getDescription());
            ps.setDouble(3, Double.parseDouble(menu.getPrice()));
            ps.setBytes(4, menu.getImage());
            ps.setInt(5, menu_id);
            int rowsAffected = ps.executeUpdate();
            System.out.println(rowsAffected);
            return true;
        }
        catch(SQLException e){
            e.printStackTrace();
             return false;
        }
        
    }
       // Connection con = db.getConnection();
       
//        ManageMenuModel md = new ManageMenuModel();
//        ArrayList<Menu> menuArr = md.loadMenu();
//        try{
//                int index = -1;
//                for (int i = 0; i < menuArr.size() && !menuArr.isEmpty(); i++){
//                    if ((menuArr.get(i)).getName().equals(menu.getName())){
//                        index = i;
//                        break;
//                    }
//                }
//                 
//                menu.setName(view.getMenuName().getText());
//                menu.setPrice(view.getPrice().getText());
//                menu.setDescription(view.getDescription().getText());
//               // menu.setImage((ImageIcon)view.getImgLabel().getIcon());
//                
//                menuArr.set(index, menu);
//                }catch(IndexOutOfBoundsException e){
//                    System.out.println("User not found");
//                }
//               // md.saveMenu(menuArr);
//              
//                JOptionPane.showConfirmDialog(null, "Update Menu success!", "Notification", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
//                
//                System.out.println("update success");
//        
        
        
    
}
