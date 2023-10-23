/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
/**
 *
 * @author User
 */
public class Menu implements Serializable {
    private String name;
    private String price;
    private String description;
    private ImageIcon image;
    
    public Menu(){
         // Default constructor required for calls to DataSnapshot.getValue(User.class)
        this("", "", "", null);
    
    }
    public Menu( String name , String price, String description, ImageIcon image){
        this.name = name;
        this.price =price;
        this.description = description;
        this.image = image;
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }
    
}
