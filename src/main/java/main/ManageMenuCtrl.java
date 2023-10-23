
package main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.*;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class ManageMenuCtrl implements ActionListener {
    ManageMenuUI manageMenuUI;
    MainCtrl mainCtrl;
    ManageMenuModel model;
    String imgPath;

    DefaultTableModel tableModel;

    public ManageMenuCtrl(MainCtrl mainCtrl){
        initcomponents();
        this.mainCtrl = mainCtrl;
    
    }
    public void initcomponents(){
        manageMenuUI = new ManageMenuUI();
        manageMenuUI.getAddImgBtn().addActionListener((ActionListener)this);
        manageMenuUI.getAddMenuBtn().addActionListener((ActionListener) this);
        manageMenuUI.getBackBtn().addActionListener((ActionListener) this);
        //set table

        
        
        
        
        
        
        
        
        
    }
    public JPanel getPanel(){
        return manageMenuUI.getPanel();
    }
        

    @Override
    public void actionPerformed(ActionEvent ev) {
        //------
        //add image btn
        //------
//        if (ev.getSource() == manageMenuUI.getAddImgBtn()){
//            model.UploadPicture();
//            this.imgPath = model.getImagePath();
//            
//        }
        //------
        //add menu
        //------

        //------
        //back
        //------
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
