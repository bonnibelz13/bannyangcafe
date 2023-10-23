
package main;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author baibu
 */
public class ManageMenuUI extends javax.swing.JFrame {

    /**
     * Creates new form ManageMenuUI
     */
    public ManageMenuUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        copyright_panel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        imageLabel = new javax.swing.JLabel();
        addImgBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        menuName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        price = new javax.swing.JTextField();
        desciption = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        menuTable = new javax.swing.JTable();
        delMenuBtn = new javax.swing.JButton();
        updateMenuBtn = new javax.swing.JButton();
        addMenuBtn = new javax.swing.JButton();
        backBtn = new javax.swing.JButton();
        refreshBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel.setBackground(new java.awt.Color(243, 234, 221));
        panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        copyright_panel.setBackground(new java.awt.Color(40, 38, 38));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("© 2023 BaanYang Cha Inc.");

        javax.swing.GroupLayout copyright_panelLayout = new javax.swing.GroupLayout(copyright_panel);
        copyright_panel.setLayout(copyright_panelLayout);
        copyright_panelLayout.setHorizontalGroup(
            copyright_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, copyright_panelLayout.createSequentialGroup()
                .addContainerGap(475, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(440, 440, 440))
        );
        copyright_panelLayout.setVerticalGroup(
            copyright_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(copyright_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        panel.add(copyright_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 780, 1110, 40));

        imageLabel.setBackground(new java.awt.Color(255, 255, 255));
        imageLabel.setOpaque(true);
        panel.add(imageLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, 250, 170));

        addImgBtn.setBackground(new java.awt.Color(239, 220, 208));
        addImgBtn.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        addImgBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/iconaddpic.png"))); // NOI18N
        addImgBtn.setText("picture");
        panel.add(addImgBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 310, 110, 40));

        jLabel2.setFont(new java.awt.Font("Angsana New", 0, 28)); // NOI18N
        jLabel2.setText("Name:");
        panel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 370, -1, -1));
        panel.add(menuName, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 410, 250, -1));

        jLabel3.setFont(new java.awt.Font("Angsana New", 0, 28)); // NOI18N
        jLabel3.setText("Price (฿):");
        panel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 450, -1, -1));
        panel.add(price, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 490, 250, -1));
        panel.add(desciption, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 590, 250, 70));

        jLabel4.setFont(new java.awt.Font("Angsana New", 0, 28)); // NOI18N
        jLabel4.setText("Description");
        panel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 540, -1, -1));

        menuTable.setFont(new java.awt.Font("BD Megatoya Extended Thin Itali", 0, 14)); // NOI18N
        menuTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Image", "Name", "Description", "Price"
            }
        ));
        jScrollPane1.setViewportView(menuTable);

        panel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 110, 510, 450));

        delMenuBtn.setBackground(new java.awt.Color(213, 135, 135));
        delMenuBtn.setFont(new java.awt.Font("Angsana New", 1, 28)); // NOI18N
        delMenuBtn.setForeground(new java.awt.Color(25, 24, 24));
        delMenuBtn.setText("Delete");
        panel.add(delMenuBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 590, 170, 40));

        updateMenuBtn.setBackground(new java.awt.Color(209, 199, 173));
        updateMenuBtn.setFont(new java.awt.Font("Angsana New", 1, 28)); // NOI18N
        updateMenuBtn.setForeground(new java.awt.Color(32, 29, 29));
        updateMenuBtn.setText("Update");
        panel.add(updateMenuBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 590, 170, 40));

        addMenuBtn.setBackground(new java.awt.Color(127, 161, 136));
        addMenuBtn.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        addMenuBtn.setText("Add Menu");
        panel.add(addMenuBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 690, 100, 40));

        backBtn.setBackground(new java.awt.Color(163, 46, 46));
        backBtn.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        backBtn.setForeground(new java.awt.Color(255, 255, 255));
        backBtn.setText("Back");
        panel.add(backBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        refreshBtn.setBackground(new java.awt.Color(153, 204, 255));
        refreshBtn.setFont(new java.awt.Font("Angsana New", 1, 28)); // NOI18N
        refreshBtn.setText("Refresh");
        refreshBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshBtnActionPerformed(evt);
            }
        });
        panel.add(refreshBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 660, 150, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void refreshBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_refreshBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ManageMenuUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageMenuUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageMenuUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageMenuUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManageMenuUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addImgBtn;
    private javax.swing.JButton addMenuBtn;
    private javax.swing.JButton backBtn;
    private javax.swing.JPanel copyright_panel;
    private javax.swing.JButton delMenuBtn;
    private javax.swing.JTextField desciption;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField menuName;
    private javax.swing.JTable menuTable;
    private javax.swing.JPanel panel;
    private javax.swing.JTextField price;
    private javax.swing.JButton refreshBtn;
    private javax.swing.JButton updateMenuBtn;
    // End of variables declaration//GEN-END:variables
    public JButton getRefreshBtn(){
        return this.refreshBtn;
    }
    public void setRefreshBtn(JButton refreshBtn){
        this.refreshBtn = refreshBtn;
    }
    public JButton getUpdateMenuBtn(){
        return this.updateMenuBtn;
    }
    public void setUpdateMenuBtn(JButton updateMenuBtn){
        this.updateMenuBtn = updateMenuBtn;
    }
    public JTextField getMenuName(){
        return this.menuName;
    }
    public void setMenuName(){
        this.menuName = menuName;
    }
    public JPanel getPanel(){
        return this.panel;
    }

    public JButton getAddImgBtn() {
        return addImgBtn;
    }

    public void setAddImgBtn(JButton addImgBtn) {
        this.addImgBtn = addImgBtn;
    }

    public JButton getAddMenuBtn() {
        return addMenuBtn;
    }

    public void setAddMenuBtn(JButton addMenuBtn) {
        this.addMenuBtn = addMenuBtn;
    }

    public JButton getBackBtn() {
        return backBtn;
    }

    public void setBackBtn(JButton backBtn) {
        this.backBtn = backBtn;
    }

    public JButton getDelMenuBtn() {
        return delMenuBtn;
    }

    public void setDelMenuBtn(JButton delMenuBtn) {
        this.delMenuBtn = delMenuBtn;
    }

    public JTextField getDesciption() {
        return desciption;
    }

    public void setDesciption(JTextField desciption) {
        this.desciption = desciption;
    }

    public JTable getMenuTable() {
        return menuTable;
    }

    public void setMenuTable(JTable menuTable) {
        this.menuTable = menuTable;
    }
    public JLabel getImageLabel() {
        return this.imageLabel;
    }
    public void setImageLabel(JLabel imageLabel) {
        this.imageLabel = imageLabel;
    }



    public JTextField getPrice() {
        return price;
    }

    public void setPrice(JTextField price) {
        this.price = price;
    }
}
