/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class UpdateMenuCtrl implements ActionListener{
    private UpdateMenuUI view;
    private UpdateMenuModel model;
    //private ArrayList<Menu> menuArr;
    private Menu menu;
    private String name;
    private File img_file;
    public UpdateMenuCtrl(Menu menu){
        this.menu = menu;
        initComponents();
    }
    public void initComponents(){
        this.name = menu.getName();
        view = new UpdateMenuUI();
        model = new UpdateMenuModel(view, menu);
        view.getAddImgBtn().addActionListener(this);
        view.getSaveBtn().addActionListener(this);
        view.getMenuName().setText(menu.getName());
        view.getPrice().setText(menu.getPrice());
        view.getDescription().setText(menu.getDescription());
        
        byte[] bytes = menu.getImage();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(bytes).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        // img to fit the jlab
        view.getImgLabel().setIcon(imageIcon);
        //this.img_file = 
        view.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        view.setVisible(true);
        
    }
    

    @Override
    public void actionPerformed(ActionEvent ev){
        //-----------
        //add new img
        //-----------
        if(ev.getSource() == view.getAddImgBtn()){
            model.updateImg();
            this.img_file = model.getImageFile();
        }
        //----------
        // save update
        //----------
        if(ev.getSource() ==  view.getSaveBtn()){
           // Menu newMenu = new Menu();
            if(!view.getMenuName().getText().equals("") && view.getImgLabel().getIcon() != null && 
              !view.getPrice().getText().equals("") && !view.getDescription().getText().equals("")){
                //create new Menu
                try{
                       menu.setName(view.getMenuName().getText());
                       menu.setPrice(view.getPrice().getText());
                       menu.setDescription(view.getDescription().getText());
                       if(img_file != null){
                           menu.setImage(Files.readAllBytes(img_file.toPath())); 
                       }
                       

                }catch(Exception e){
                    e.printStackTrace();
                }
                
                if(model.updateMenu(menu)){
                    JOptionPane.showMessageDialog(null, "Update menu success!");
                }
                else{
                    JOptionPane.showMessageDialog(null, "some thing error");
                }
           // model.updateMenu(menu);
            view.dispose();
            
        }
    }
    }
}
