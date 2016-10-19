/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

/**
 *
 * @author Kent
 */
public class myDesktopPane extends javax.swing.JDesktopPane {
    private Image backImage = null; //member variable
 
   public myDesktopPane()
   {     
      try
      {
         backImage = new javax.swing.ImageIcon(this.getClass().getResource("/images/Background.jpg")).getImage();
      }
      catch(Exception e)
      {
         System.out.println("Could not find file in folder: " + this.getClass().getResource("."));
      }
   }
 
   public void paintComponent( Graphics g )
   {
      if(backImage == null)
         super.paintComponent(g);
      else
      {
         Graphics2D g2d = (Graphics2D)g;
 
         //scale the image to fit the size of the Panel
         double mw = backImage.getWidth(null);
         double mh = backImage.getHeight(null);
 
         double sw = getWidth() / mw;
         double sh = getHeight() / mh;
 
         g2d.scale(sw, sh);
         g2d.drawImage(backImage, 0, 0, this);
      }
   }
 }
