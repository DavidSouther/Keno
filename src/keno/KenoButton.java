/*
 * KenoButton.java
 *
 * Created on May 26, 2007, 6:12 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package keno;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author David Souther
 */
public class KenoButton extends javax.swing.JLabel implements java.awt.event.MouseListener{
    int number;
    View control;
    boolean selected, bonus;
        
    private ImageIcon normal,
                  select,
                  down,
                  hit,
                  hitSelect,
                  bonusIcon,
                  bonusHit;
    
    /** Creates a new instance of KenoButton */
    public KenoButton(int num, View c) {
        //super("" + (num + 1));

        this.control = c;
        this.number = num;
        
        this.setOpaque(true);
        this.setHorizontalAlignment(SwingConstants.CENTER);
        
        this.buildIcons();
                
        //this.setFont(new java.awt.Font("Serif", java.awt.Font.BOLD, 48));
        //this.setPreferredSize(new java.awt.Dimension(60, 60));
        this.addMouseListener(this);
   
        this.selected = false;
        this.setIcon(normal);
    }
    
    public void buildIcons(){
        int buttonNumber = number + 1;
        java.net.URL path;
        path = KenoButton.class.getResource("normal/"+ buttonNumber + ".gif"); 
        if(path != null)
            normal = new ImageIcon(path);
        
        path = KenoButton.class.getResource("selected/"+ buttonNumber + ".gif"); 
        if(path != null)
            select = new ImageIcon(path);
        
        path = KenoButton.class.getResource("down/"+ buttonNumber + ".gif"); 
        if(path != null)
            down = new ImageIcon(path);
        
        path = KenoButton.class.getResource("hit/"+ buttonNumber + ".gif"); 
        if(path != null)
            hit = new ImageIcon(path);

        path = KenoButton.class.getResource("hitSelected/"+ buttonNumber + ".gif"); 
        if(path != null)
             hitSelect = new ImageIcon(path);
        
        path = KenoButton.class.getResource("bonus/"+ buttonNumber + ".gif"); 
        if(path != null)
            bonusIcon = new ImageIcon(path);
        
        path = KenoButton.class.getResource("hitBonus/"+ buttonNumber + ".gif"); 
        if(path != null)
            bonusHit = new ImageIcon(path);
    }
    
    public void mousePressed(MouseEvent e) {
        this.setIcon(down);
    }

    public void mouseReleased(MouseEvent e) {
        if(this.selected) deselect();
        else select();
    }

    public void select(){
        if(control.select(this.number)){
            this.selected = true;
            this.setIcon(select);
        } else {
            this.setIcon(normal);
        }
    }
    
    public void deselect(){
        if(control.deselect(this.number)){
            this.selected = false;
            this.setIcon(normal);
        }
    }
    
    public void hit(){
        if(this.selected){
            this.setIcon(hitSelect);
        } else if(this.bonus){
            this.setIcon(bonusHit);        
        } else {
            this.setIcon(hit);
        }
    }
    
    public void reset(){
        if(this.selected){
            this.setIcon(select);
        } else {
            this.bonus = false;
            this.setIcon(normal);
        }
    }
    
    public void bonus(){
        this.bonus = true;
        this.setIcon(bonusIcon);
    }
        
    public boolean isSelected(){
        return selected;
    }
    
    public boolean isBonus(){
        return bonus;
    }
    
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
}
