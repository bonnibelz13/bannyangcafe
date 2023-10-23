/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.Component;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author User
 */


public class ImageRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = new JLabel();
        byte[] bytes = (byte[])value;
        ImageIcon imageicon = new ImageIcon(new ImageIcon(bytes).getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
        label.setIcon(imageicon);
        label.setHorizontalAlignment(JLabel.CENTER);
//        if (value instanceof Icon) {
//            label.setIcon((Icon) value);
//            label.setHorizontalAlignment(JLabel.CENTER);
//        }
        return label;
    }

}
