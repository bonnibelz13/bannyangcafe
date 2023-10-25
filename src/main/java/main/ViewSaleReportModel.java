/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Account;
import model.db;

/**
 *
 * @author User
 */
public class ViewSaleReportModel {
    private Account user;
    private int user_id;
    
    public ViewSaleReportModel(Account user){
        this.user = user;
       this.user_id = userID(user);
    }
    public int userID(Account user){
        String name = user.getUsername();
        System.out.println("view sale model username: "+name);
        int user_id =0;
        Connection con = db.getConnection();
        String sql_id = "SELECT id FROM user WHERE username = '"+name +"'";
        try {Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql_id);
            while(rs.next()){
               
                user_id = rs.getInt("id");
                System.out.println("view sale query id: " + user_id);
            }
            return user_id;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return user_id;
        }
    }
    public void loadTable(JTable table){
        System.out.println("load view sale table w/user_id = "+ user_id);
        Connection con = db.getConnection();
        String sql= "SELECT * FROM order_bill JOIN order_item using(order_id) JOIN menu using(menu_id) where user_id = '"+user_id+"'";
        try {Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
            tableModel.setRowCount(0);
        while(rs.next()){
                Object[] row = new Object[5];
                row[0] = rs.getDate("order_date");
                row[1] = rs.getInt("order_id");
                row[2] = rs.getString("menu_name");
                row[3] = rs.getInt("item_qt");
                row[4] = rs.getDouble("item_lineprice");
                tableModel.addRow(row);
            }
                table.setRowHeight(20);
                System.out.println("setting table");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void loadBestSeller(JTable table){
        System.out.println("load view best seller table w/user_id = "+ user_id);
        Connection con = db.getConnection();
        String sql= "SELECT *, sum(item_qt)'sum' FROM order_bill JOIN order_item using(order_id) JOIN menu using(menu_id) where user_id = '"+user_id+"' group by (menu_id) Order by sum(item_qt) DESC";
        try {Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
            tableModel.setRowCount(0);
            int rownum = 1;
        while(rs.next()){
                Object[] row = new Object[3];
                row[0] = rownum;
                row[1] = rs.getString("menu_name");
                row[2] = rs.getInt("sum");
                tableModel.addRow(row);
                rownum++;
            }
                table.setRowHeight(20);
                System.out.println("setting table");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void reportDate(JTable table, String date, String month, String year){
        month = convertMonth(month);
        Connection con = db.getConnection();
        String dateFormat = year+'-'+month+'-'+date;
        String sql= "SELECT * FROM order_bill JOIN order_item using(order_id) JOIN menu using(menu_id) where user_id = '"+user_id+"' And order_date LIKE '" +dateFormat+"%' Order by order_date ASC ";
        try {Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
            tableModel.setRowCount(0);
        while(rs.next()){
                Object[] row = new Object[5];
                row[0] = rs.getDate("order_date");
                row[1] = rs.getInt("order_id");
                row[2] = rs.getString("menu_name");
                row[3] = rs.getInt("item_qt");
                row[4] = rs.getDouble("item_lineprice");
                tableModel.addRow(row);
            }
                table.setRowHeight(20);
                System.out.println("setting report date table");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void bestSellerDate(JTable table, String date, String month, String year){
        month = convertMonth(month);
        Connection con = db.getConnection();
        String dateFormat = year+'-'+month+'-'+date;
        String sql= "SELECT *, sum(item_qt)'sum' FROM order_bill JOIN order_item using(order_id) JOIN menu using(menu_id) where user_id = '"+user_id+"' And order_date LIKE '" +dateFormat+"%' group by menu_id Order by sum(item_qt) DESC ";
        try {Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
            tableModel.setRowCount(0);
            int rownum = 1;
        while(rs.next()){
                Object[] row = new Object[3];
                row[0] = rownum;
                row[1] = rs.getString("menu_name");
                row[2] = rs.getInt("sum");
                tableModel.addRow(row);
                rownum++;
            }
                table.setRowHeight(20);
                System.out.println("setting best seller date table");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
     public void reportMonth(JTable table, String month, String year){
        month = convertMonth(month);
        Connection con = db.getConnection();
        String dateFormat = year+'-'+month;
        String sql= "SELECT * FROM order_bill JOIN order_item using(order_id) JOIN menu using(menu_id) where user_id = '"+user_id+"' And order_date LIKE '" +dateFormat+"%' Order by order_date ASC ";
        try {Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
            tableModel.setRowCount(0);
        while(rs.next()){
                Object[] row = new Object[5];
                row[0] = rs.getDate("order_date");
                row[1] = rs.getInt("order_id");
                row[2] = rs.getString("menu_name");
                row[3] = rs.getInt("item_qt");
                row[4] = rs.getDouble("item_lineprice");
                tableModel.addRow(row);
            }
                table.setRowHeight(20);
                System.out.println("setting report date table");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
     public void bestSellerMonth(JTable table, String month, String year){
        month = convertMonth(month);
        Connection con = db.getConnection();
        String dateFormat = year+'-'+month+'-';
        String sql= "SELECT *, sum(item_qt)'sum' FROM order_bill JOIN order_item using(order_id) JOIN menu using(menu_id) where user_id = '"+user_id+"' And order_date LIKE '" +dateFormat+"%' group by menu_id Order by sum(item_qt) DESC ";
        try {Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
            tableModel.setRowCount(0);
            int rownum = 1;
        while(rs.next()){
                Object[] row = new Object[3];
                row[0] = rownum;
                row[1] = rs.getString("menu_name");
                row[2] = rs.getInt("sum");
                tableModel.addRow(row);
                rownum++;
            }
                table.setRowHeight(20);
                System.out.println("setting best seller date table");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
     public void reportYear(JTable table,String year){
       
        Connection con = db.getConnection();
        String dateFormat = year;
        String sql= "SELECT * FROM order_bill JOIN order_item using(order_id) JOIN menu using(menu_id) where user_id = '"+user_id+"' And order_date LIKE '" +dateFormat+"%' Order by order_date ASC ";
        try {Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
            tableModel.setRowCount(0);
        while(rs.next()){
                Object[] row = new Object[5];
                row[0] = rs.getDate("order_date");
                row[1] = rs.getInt("order_id");
                row[2] = rs.getString("menu_name");
                row[3] = rs.getInt("item_qt");
                row[4] = rs.getDouble("item_lineprice");
                tableModel.addRow(row);
            }
                table.setRowHeight(20);
                System.out.println("setting report date table");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
     public void bestSellerYear(JTable table, String year){
//        month = convertMonth(month);
        Connection con = db.getConnection();
        String dateFormat = year+'-';
        String sql= "SELECT *, sum(item_qt)'sum' FROM order_bill JOIN order_item using(order_id) JOIN menu using(menu_id) where user_id = '"+user_id+"' And order_date LIKE '" +dateFormat+"%' group by menu_id Order by sum(item_qt) DESC ";
        try {Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
            tableModel.setRowCount(0);
            int rownum = 1;
        while(rs.next()){
                Object[] row = new Object[3];
                row[0] = rownum;
                row[1] = rs.getString("menu_name");
                row[2] = rs.getInt("sum");
                tableModel.addRow(row);
                rownum++;
            }
                table.setRowHeight(20);
                System.out.println("setting best seller date table");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String convertMonth(String month){
        switch (month) {
            case "All Month":
                return "0";
            case "January":
                return "01";
            case "February":
                return "02";
            case "March":
                return "03";
            case "April":
                return "04";
            case "May":
                return "05";
            case "June":
                return "06";
            case "July":
                return "07";
            case "August":
                return "08";
            case "September":
                return "09";
            case "October":
                return "10";
            case "November":
                return "11";
            case "December":
                return "12";
            default:
                break;
        }
        return "0";
    }
}
