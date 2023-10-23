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
import java.util.*;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class ManageMenuModel {
    private ManageMenuUI view;
    private String imgPath;
    private JTable table;
    public ManageMenuModel(ManageMenuUI view){
        this.view = view;
    }
    
//    public void UploadPicture() {
//        JFileChooser fileChooser = new JFileChooser();
//        FileNameExtensionFilter filter = new FileNameExtensionFilter("4 supported extensions", "jpg", "jpeg", "gif", "png");
//        fileChooser.setFileFilter(filter);
//        int selected = fileChooser.showOpenDialog(null);
//
//        if (selected == JFileChooser.APPROVE_OPTION) {
//            File file = fileChooser.getSelectedFile();
//            String getSelectedImage = file.getAbsolutePath();
//            ImageIcon img = new ImageIcon(getSelectedImage);
//            
//            // img to fit the jlabel
//            
//            view.getImageLabel().setIcon(new ImageIcon(img.getImage().getScaledInstance(250, 210, Image.SCALE_SMOOTH)));
//            this.imgPath = getSelectedImage;
//
//            // Update the image path in the AddMenuModel
//            //addMenuModel.setImagePath(getSelectedImage);
//        }
//    }
//    
//    public String getImagePath(){
//        return this.imgPath;
//    }
//    public ImageIcon setMenuImg(String imgPath){
//        ImageIcon img = new ImageIcon(imgPath);
//        return new ImageIcon(img.getImage().getScaledInstance(60,  60, Image.SCALE_SMOOTH));
//    }
//    //file input and output
//    public ArrayList loadMenu(){
//        try(FileInputStream fin = new FileInputStream("MenuData.dat");
//            ObjectInputStream in = new ObjectInputStream(fin);){
//            System.out.println("load menu data success");
//            return ((ArrayList)in.readObject());
//        }catch(IOException | ClassNotFoundException e){
//            System.out.println(e);
//            return new ArrayList();
//        }
//    }
//    public void saveMenu(ArrayList menu){
//        try(FileOutputStream fOut = new FileOutputStream("MenuData.dat");
//            ObjectOutputStream oout = new ObjectOutputStream(fOut);){
//            oout.writeObject(menu);
//            System.out.println("save menu data success");
//        }catch(IOException e){
//        System.out.println(e); 
//        }
//    }
//    public void loadTable(ArrayList<Menu> menuData){
//        table = view.getMenuTable();
//        DefaultTableModel tableModel = (DefaultTableModel)view.getMenuTable().getModel();
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
//            
//        }
//        
//        
//    }
//    public void updateTable(Object[] rowData) {
//        tableModel.addRow(rowData);
//    }
//    
//    public JTable addMenuTable(){
//        DefaultTableModel tableModel = (DefaultTableModel)view.getMenuTable().getModel();
//        ImageRenderer imageRenderer = new ImageRenderer();
//        view.getMenuTable().getColumnModel().getColumn(1).setCellRenderer(imageRenderer);
//        view.getMenuTable().getColumnModel().getColumn(1).setPreferredWidth(60);
//        view.getMenuTable().setRowHeight(60);
//        return view.getMenuTable();
//    }
    
}
