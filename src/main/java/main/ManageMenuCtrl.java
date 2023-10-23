
package main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.*;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Account;

/**
 *
 * @author User
 */
public class ManageMenuCtrl implements ActionListener {
    ManageMenuUI manageMenuUI;
    MainCtrl mainCtrl;
    ManageMenuModel model;
   // String imgPath;
    File img_file;
    Account user;
    ArrayList <Menu>menuArr =new ArrayList<Menu>();

    DefaultTableModel tableModel;

    public ManageMenuCtrl(MainCtrl mainCtrl, Account user){
        initcomponents();
        this.mainCtrl = mainCtrl;
        this.user = user;
    
    }
    public void initcomponents(){
        manageMenuUI = new ManageMenuUI();

        model = new ManageMenuModel(manageMenuUI);
        manageMenuUI.getAddImgBtn().addActionListener((ActionListener)this);
        manageMenuUI.getAddMenuBtn().addActionListener((ActionListener) this);
        manageMenuUI.getBackBtn().addActionListener((ActionListener) this);
        manageMenuUI.getUpdateMenuBtn().addActionListener((ActionListener) this);
        manageMenuUI.getDelMenuBtn().addActionListener((ActionListener) this);
        //menuArr = model.loadMenu();
        //set table
        //model.loadTable(menuArr);
        model.findUserID(user);

        
        
        
        
        
        
        
        
        
    }
    public JPanel getPanel(){
        return manageMenuUI.getPanel();
    }
        

    @Override
    public void actionPerformed(ActionEvent ev) {
   //------
        //add image btn
        //------
        
        if (ev.getSource() == manageMenuUI.getAddImgBtn()){
            model.UploadPicture();
            this.img_file = model.getImageFile();
            
        }
        
        //------
        //add menu
        //------
        if (ev.getSource()== manageMenuUI.getAddMenuBtn()){
            
            //ImageIcon img = model.setMenuImg(imgPath);
         
            boolean checkPrice = ManageMenuModel.isNumeric((manageMenuUI.getPrice().getText()));
            boolean isChecked = model.checkMenu(manageMenuUI.getMenuName().getText());
            if(!manageMenuUI.getMenuName().getText().equals("") && manageMenuUI.getImageLabel().getIcon() != null && 
               !manageMenuUI.getPrice().getText().equals("") && !manageMenuUI.getDesciption().getText().equals("") && isChecked && checkPrice){
                //create new Menu
                try{
                       Menu newMenu = new Menu();
                       newMenu.setName(manageMenuUI.getMenuName().getText());
                       newMenu.setPrice(manageMenuUI.getPrice().getText());
                       newMenu.setDescription(manageMenuUI.getDesciption().getText());
                       newMenu.setImage(Files.readAllBytes(img_file.toPath()));
                      if(model.saveMenu(newMenu)){
                          JOptionPane.showMessageDialog(null, "Add menu success!");
                      }

                }catch(Exception e){
                    e.printStackTrace();
                }
               // Menu newMenu = new Menu(name, price, des, img); 
//                menuArr = model.loadMenu();
//                menuArr.add(newMenu);
//                //save menu into file
//                model.saveMenu(menuArr);

                //cleardata
                manageMenuUI.getImageLabel().setIcon(null);
                manageMenuUI.getMenuName().setText("");
                manageMenuUI.getPrice().setText("");
                manageMenuUI.getDesciption().setText("");

               // model.addMenuIntoTable(newMenu);
                System.out.println("save and clear");
                
                manageMenuUI.revalidate();
                
            }
            else if(!isChecked){
                JOptionPane.showMessageDialog(null, "This menu is already exist!");
            }
            else if(!checkPrice){
                JOptionPane.showMessageDialog(null, "The price is invalid!");
            }
            else{
                JOptionPane.showMessageDialog(null, "Complete the data first!");
            }
            
        }
        //------
        //delete
        //------
        if (ev.getSource() == manageMenuUI.getDelMenuBtn()){
            if(manageMenuUI.getMenuTable().getSelectedRow() != -1){
                //remove from table
                int row = manageMenuUI.getMenuTable().getSelectedRow();
                DefaultTableModel tableModel = (DefaultTableModel) manageMenuUI.getMenuTable().getModel();
                tableModel.removeRow(row);
                String name = (String)manageMenuUI.getMenuTable().getModel().getValueAt(row, 1);
                
                //remove from file
                //------deletemenu----------
               // model.deleteMenu(row, name);
                
                //success
                JOptionPane.showMessageDialog(null, "The menu has been deleted");
                manageMenuUI.getMenuTable().revalidate();
                manageMenuUI.getMenuTable().repaint();
 
            }
            else if(manageMenuUI.getMenuTable().getSelectedRow() == 0){
                JOptionPane.showMessageDialog(null, "Please select menu");
            }
            
    
        }
        //------
        //update
        //------
        if(ev.getSource().equals(manageMenuUI.getUpdateMenuBtn())){
            if(manageMenuUI.getMenuTable().getSelectedRow() != -1){
                int row = manageMenuUI.getMenuTable().getSelectedRow();
                String name = (String)manageMenuUI.getMenuTable().getModel().getValueAt(row, 1);
                Menu menu = model.findMenu(name);
                new UpdateMenuCtrl(menu);
                System.out.println("open updateMenu");
            }
            else if(manageMenuUI.getMenuTable().getSelectedRow() == 0){
                JOptionPane.showMessageDialog(null, "Please select menu");
            }
      
        }
        //------
        //back
        //------
        if (ev.getSource().equals(manageMenuUI.getBackBtn())) {
            System.out.println("Home Open...");
            mainCtrl.setView("Home", "ManageMenu");
        }
        if (ev.getSource().equals(manageMenuUI.getBackBtn())) {
            System.out.println("Home Open...");
            mainCtrl.setView("Home", "ManageMenu");
        }
       
    }

     
//            tableModel.addRow(new Object[]{foodRecipe.getName(), foodRecipe.getCategory(), foodRecipe.getIngredientDetail(), foodRecipe.getDescription(), foodRecipe.getPicture()});
//            
//            
//            // show JOptionPane added menu.
//            JOptionPane.showMessageDialog(null, "New menu added.");
//            
//            // Render pic to table.
//            ImageRenderer imageRenderer = new ImageRenderer();
//            view.getjTable1().getColumnModel().getColumn(4).setCellRenderer(imageRenderer);
//            view.getjTable1().getColumnModel().getColumn(4).setPreferredWidth(60);
//            view.getjTable1().setRowHeight(60);
//    
}
