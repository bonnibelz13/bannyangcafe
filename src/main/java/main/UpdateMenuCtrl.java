/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author User
 */
public class UpdateMenuCtrl implements ActionListener{
    UpdateMenuUI view;
    UpdateMenuModel model;
    ArrayList<Menu> menuArr;
    Menu menu;
    String name;
    
    public UpdateMenuCtrl(Menu menu){
        this.menu = menu;
        initComponents();
    }
    public void initComponents(){
        this.name = menu.getName();
        view = new UpdateMenuUI();
        model = new UpdateMenuModel(view);
        view.getAddImgBtn().addActionListener(this);
        view.getSaveBtn().addActionListener(this);
        view.getMenuName().setText(menu.getName());
        view.getPrice().setText(menu.getPrice());
        view.getDescription().setText(menu.getDescription());
       
        // img to fit the jlab
        view.getImgLabel().setIcon(new ImageIcon((menu.getImage()).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
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
        }
        //----------
        // save update
        //----------
        if(ev.getSource() ==  view.getSaveBtn()){
            model.updateMenu(menu);
            view.dispose();
            
        }
    }
}
