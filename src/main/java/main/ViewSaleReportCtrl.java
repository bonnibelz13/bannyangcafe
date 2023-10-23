/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 *
 * @author Noppakorn
 */
public class ViewSaleReportCtrl implements ActionListener{
    ViewSaleReportUI view;
    MainCtrl mainCtrl;
    
    public ViewSaleReportCtrl(MainCtrl mainCtrl) {
        initComponents();
        this.mainCtrl = mainCtrl;
    }
    public void initComponents() {
        this.view = new ViewSaleReportUI();
        view.getBtnBack().addActionListener((ActionListener) this);
    }
    public JPanel getPanel() {
        return view.getPanel();
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().equals(view.getBtnBack())) {
            System.out.println("ViewSaleReport Back To Home Page");
            mainCtrl.setView("Home", "ViewSaleReport");
        }
    }
}
