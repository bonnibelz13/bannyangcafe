/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package main;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author baibu
 */
public class HomeUI extends javax.swing.JFrame {

    /**
     * Creates new form HomeUI
     */
    public HomeUI() {
        initComponents();
    }
    public JPanel getPanel() {
        return this.panel;
    }
    
    public JButton getBtnCreateOrder() {
        return this.btnCreateOrder;
    }
    public JButton getBtnManageMenu() {
        return this.btnManageMenu;
    }
    public JButton getBtnSaleReport() {
        return this.btnViewSaleReport;
    }

    public JButton getBtnLogout() {
        return btnLogout;
    }

    
    public JLabel getCurrentusername() {
        return currentusername;
    }

    public JLabel getjDate() {
        return jDate;
    }

    public JLabel getjPMAM() {
        return jPMAM;
    }

    public JLabel getjTime() {
        return jTime;
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
        currentusername = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Bannyang = new javax.swing.JLabel();
        jDate = new javax.swing.JLabel();
        jPMAM = new javax.swing.JLabel();
        jTime = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnViewSaleReport = new javax.swing.JButton();
        btnManageMenu = new javax.swing.JButton();
        btnCreateOrder = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel.setBackground(new java.awt.Color(201, 193, 188));

        currentusername.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        currentusername.setForeground(new java.awt.Color(243, 242, 242));
        currentusername.setText("welcome, username");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BAANYANGCHA.png"))); // NOI18N
        jLabel1.setText("jLabel1");

        Bannyang.setBackground(new java.awt.Color(234, 235, 238));
        Bannyang.setFont(new java.awt.Font("Angsana New", 1, 60)); // NOI18N
        Bannyang.setForeground(new java.awt.Color(248, 247, 247));
        Bannyang.setText("BannYang Cha.");

        jDate.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jDate.setForeground(new java.awt.Color(243, 242, 242));
        jDate.setText("Saturday, 21/10/2023");

        jPMAM.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jPMAM.setForeground(new java.awt.Color(243, 242, 242));
        jPMAM.setText("PM");

        jTime.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jTime.setForeground(new java.awt.Color(243, 242, 242));
        jTime.setText("00:00:00");

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("© 2023 BaanYang Cha Inc.");

        btnViewSaleReport.setBackground(new java.awt.Color(255, 255, 255));
        btnViewSaleReport.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        btnViewSaleReport.setText("View Reports");
        btnViewSaleReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewSaleReportActionPerformed(evt);
            }
        });

        btnManageMenu.setBackground(new java.awt.Color(255, 255, 255));
        btnManageMenu.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        btnManageMenu.setText("Manage Menu");
        btnManageMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManageMenuActionPerformed(evt);
            }
        });

        btnCreateOrder.setBackground(new java.awt.Color(255, 255, 255));
        btnCreateOrder.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        btnCreateOrder.setText("Create Order");

        btnLogout.setBackground(new java.awt.Color(17, 16, 16));
        btnLogout.setFont(new java.awt.Font("BD Megatoya Outline Black Itali", 1, 12)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(245, 240, 240));
        btnLogout.setText("logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/signupcafe.jpg"))); // NOI18N

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCreateOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(btnLogout)
                        .addGap(392, 392, 392)
                        .addComponent(jLabel3)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(1060, 1060, 1060)
                        .addComponent(jPMAM, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(890, 890, 890)
                        .addComponent(jDate, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(980, 980, 980)
                        .addComponent(jTime, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(210, 210, 210)
                        .addComponent(currentusername, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(200, 200, 200)
                        .addComponent(Bannyang, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(820, 820, 820)
                        .addComponent(btnViewSaleReport, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(450, 450, 450)
                        .addComponent(btnManageMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(80, 80, 80))
            .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelLayout.createSequentialGroup()
                    .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, 1200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(jPMAM))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jDate))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(300, 300, 300)
                        .addComponent(btnViewSaleReport, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(jTime))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(300, 300, 300)
                        .addComponent(btnManageMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jLabel1))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(currentusername, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(Bannyang))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(300, 300, 300)
                        .addComponent(btnCreateOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 191, Short.MAX_VALUE)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLogout, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(21, 21, 21))
            .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelLayout.createSequentialGroup()
                    .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnViewSaleReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewSaleReportActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnViewSaleReportActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnManageMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManageMenuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnManageMenuActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(HomeUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(HomeUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(HomeUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(HomeUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new HomeUI().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Bannyang;
    private javax.swing.JLabel bg;
    private javax.swing.JButton btnCreateOrder;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnManageMenu;
    private javax.swing.JButton btnViewSaleReport;
    private javax.swing.JLabel currentusername;
    private javax.swing.JLabel jDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jPMAM;
    private javax.swing.JLabel jTime;
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables


}
