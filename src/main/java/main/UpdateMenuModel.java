/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author User
 */
public class UpdateMenuModel {
    UpdateMenuUI view;
    String imgPath;
    
    
    public UpdateMenuModel(UpdateMenuUI view){
        this.view = view;
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
            
            // img to fit the jlabel
            
            view.getImgLabel().setIcon(new ImageIcon(img.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
            this.imgPath = getSelectedImage;

        }
    }
    public void updateMenu(Menu menu){
        ManageMenuModel md = new ManageMenuModel();
        ArrayList<Menu> menuArr = md.loadMenu();
        try{
                int index = -1;
                for (int i = 0; i < menuArr.size() && !menuArr.isEmpty(); i++){
                    if ((menuArr.get(i)).getName().equals(menu.getName())){
                        index = i;
                        break;
                    }
                }
                 
                menu.setName(view.getMenuName().getText());
                menu.setPrice(view.getPrice().getText());
                menu.setDescription(view.getDescription().getText());
               // menu.setImage((ImageIcon)view.getImgLabel().getIcon());
                
                menuArr.set(index, menu);
                }catch(IndexOutOfBoundsException e){
                    System.out.println("User not found");
                }
               // md.saveMenu(menuArr);
              
                JOptionPane.showConfirmDialog(null, "Update Menu success!", "Notification", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
                
                System.out.println("update success");
        
        
        
    }
}
