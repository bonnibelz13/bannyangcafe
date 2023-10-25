/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import model.Account;

/**
 *
 * @author Noppakorn
 */
public class ViewSaleReportCtrl implements ActionListener{
    ViewSaleReportUI view;
    ViewSaleReportModel model;
    MainCtrl mainCtrl;
    Account user;
    JTable reportTable;
    String date;
    String month;
    String year;
    
    
    public ViewSaleReportCtrl(MainCtrl mainCtrl, Account user) {
        this.mainCtrl = mainCtrl;
        this.user = user;
        
        initComponents();
        
        
    }

    public void initComponents() {
        System.out.println("viewsale user: "+user.getUsername());
        view = new ViewSaleReportUI();
        model = new ViewSaleReportModel(user);
        view.getSearchBtn().addActionListener((ActionListener) this);
        view.getBtnBack().addActionListener((ActionListener) this);
        view.getYearCB().addActionListener((ActionListener)this);
        view.getMonthCB().addActionListener((ActionListener)this);
        view.getDateCB().addActionListener((ActionListener) this);
//        view.getReportTable().addActionListener((ActionListener)this);
        
        
        model.loadTable(view.getReportTable());
        model.loadBestSeller(view.getBestSellerTable());
        
        
        
    }
    public JPanel getPanel() {
        return view.getPanel();
    }
    
    public void actionPerformed(ActionEvent ae) {
        //=====
        //Search
        //=====
        if(ae.getSource().equals(view.getSearchBtn())){
            date = view.getDateCB().getSelectedItem().toString();
            year = view.getYearCB().getSelectedItem().toString();
            month = view.getMonthCB().getSelectedItem().toString();
            if(!date.equals("All Date")&& (month.equals("All Month") || year.equals("All Year"))){
                JOptionPane.showMessageDialog(null, "Please select year and month.");
            }
            else if(!date.equals("All Date")&& !month.equals("All Month")&& !year.equals("All Year")){
                model.reportDate(view.getReportTable(), date, month, year);
                model.bestSellerDate(view.getBestSellerTable(), date, month, year);
            }
            else if(!year.equals("All Year") && month.equals("All Month") && date.equals("All Date")){
                model.reportYear(view.getReportTable(), year);
                model.bestSellerYear(view.getBestSellerTable(), year);
            }
            else if(!year.equals("All Year") && !month.equals("All Month") && date.equals("All Date")){
                model.reportMonth(view.getReportTable(), month, year);
                model.bestSellerMonth(view.getBestSellerTable(), month, year);
            }
            else if (year.equals("All Year")&& month.equals("All Month") && date.equals("All Date")){
                model.loadTable(view.getReportTable());
                model.loadTable(view.getBestSellerTable());
            }
            else{
                JOptionPane.showMessageDialog(null, "Please complete the report");
            }
            
        }
        if (ae.getSource().equals(view.getBtnBack())) {
            System.out.println("ViewSaleReport Back To Home Page");
            mainCtrl.setView("Home", "ViewSaleReport");
        }
    }
}
