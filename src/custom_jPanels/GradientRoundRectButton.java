package custom_jPanels;


//package com.swing.custom.button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
//import org.jvnet.substance.SubstanceLookAndFeel;
//import org.jvnet.substance.skin.NebulaSkin;

/*** * @author JDhilsukh**/
public class GradientRoundRectButton extends JButton {
 private static boolean REPAINT_SHADOW = true;
 private Color COLOR1 = new Color(255, 255, 255);
 private Color COLOR2 = new Color(115, 201, 29);
 private Color startColor = new Color(255, 255, 255);
 private Color endColor = new Color(82, 82, 82); 
 private int outerRoundRectSize = 8;
 private int innerRoundRectSize = 6;
 private Color unselectedLow;
 
 private Color rollOverColor = new Color(255, 91, 13);
private Color pressedColor = new Color(206, 67, 0);   
private static final int HORIZONTAL=0;   
private static final int VERTICAL =1;  
private static final int CENTRAL =2;   
private static final int NORMAL =3;   
private int direction = VERTICAL;   
private int gradientType = CENTRAL;
//private int outerRoundRectSize = 30;
//private int innerRoundRectSize = 28;
private GradientPaint GP;

 // Lowlight for unselected tab
 private Color unselectedHigh; 
 // Highlight for unselected tab
 private Color unselectedMid;
 
 private Color selectedLow = new Color(204, 67, 0);
 
 // Lowlight for selected tab
   private Color selectedHigh = Color.WHITE; 
 // Highlight for selectedtab

public GradientRoundRectButton() {
 super();
 setContentAreaFilled(false);
// setText(text);
 setBorderPainted(false);
 unselectedLow = Color.BLACK;
 unselectedMid = new Color(0x330099);
 unselectedHigh = Color.WHITE;
 setForeground(Color.WHITE);
 setFont(new Font("Thoma", Font.BOLD, 12));
 setFocusable(false);
 setContentAreaFilled(false);
}
 
 public GradientRoundRectButton(String text) {
 super();
 setContentAreaFilled(false);
 setText(text);
 setBorderPainted(false);
 unselectedLow = Color.BLACK;
 unselectedMid = new Color(0x330099);
 unselectedHigh = Color.WHITE;
 setForeground(Color.WHITE);
 setFont(new Font("Thoma", Font.BOLD, 12));
 setFocusable(false);
 setContentAreaFilled(false);
 }
 
 public GradientRoundRectButton(Color color1, Color color2,int outerRoundRectSize, int innerRoundRectSize) {
 super();
 COLOR1 = color1;
 COLOR2 = color2;
 this.outerRoundRectSize = outerRoundRectSize;
 this.innerRoundRectSize = innerRoundRectSize;
 setFont(new Font("Thoma", Font.BOLD, 12));
 setForeground(Color.BLACK);
 setFocusable(false);
 setContentAreaFilled(false);
 setBorderPainted(false);
 }
 
@Override
  public void paintComponent(Graphics g) {
  Graphics2D g2d = (Graphics2D) g.create();
  g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
  int h = getHeight();
  int w = getWidth();
  ButtonModel model = getModel();
  if (!model.isEnabled()) {setForeground(Color.GRAY);
  GP = new GradientPaint(0, 0, new Color(192,192,192), 0, h, new Color(192,192,192),true);
  }
  else{
   setForeground(Color.WHITE);
   if (model.isRollover()) 
   {
    if(direction==VERTICAL && gradientType == CENTRAL)
 {
  GP = new GradientPaint(0, 0, startColor, 0, h/2, rollOverColor,true);
 }
 else if(direction==VERTICAL && gradientType == NORMAL)
  {
   GP = new GradientPaint(0, 0, startColor, 0, h, rollOverColor,true);
   }
   else if(direction==HORIZONTAL && gradientType == CENTRAL)
   {
    GP = new GradientPaint(0, 0, startColor, w/2, 0, rollOverColor,true);
   }else if(direction==HORIZONTAL && gradientType == NORMAL)
   {
    GP = new GradientPaint(0, 0, startColor, w, 0, rollOverColor,true);
    }
        } 
        else {if(direction==VERTICAL && gradientType == CENTRAL)
         {
          GP = new GradientPaint(0, 0, startColor, 0, h/2, endColor,true);
         }
         else if(direction==VERTICAL && gradientType == NORMAL)
         {
          GP = new GradientPaint(0, 0, startColor, 0, h, endColor,true);
         }
          else if(direction==HORIZONTAL && gradientType == CENTRAL)
         {
           GP = new GradientPaint(0, 0, startColor, w/2, 0, endColor,true);
           }
           else if(direction==HORIZONTAL && gradientType == NORMAL)
        {
          GP = new GradientPaint(0, 0, startColor, w, 0, endColor,true);
        }	}}

        g2d.setPaint(GP);GradientPaint p1;
        GradientPaint p2;
        if (model.isPressed()) {
        if(direction==VERTICAL && gradientType == CENTRAL){
        GP = new GradientPaint(0, 0, startColor, 0, h/2, pressedColor,true);
        }
        else if(direction==VERTICAL && gradientType == NORMAL){
        GP = new GradientPaint(0, 0, startColor, 0, h, pressedColor,true);
        }
        else if(direction==HORIZONTAL && gradientType == CENTRAL){
        GP = new GradientPaint(0, 0, startColor, w/2, 0, pressedColor,true);
        }
        else if(direction==HORIZONTAL && gradientType == NORMAL){
        GP = new GradientPaint(0, 0, startColor, w, 0, pressedColor,true);
        }
        g2d.setPaint(GP);
        p1 = new GradientPaint(0, 0, new Color(0, 0, 0), 0, h - 1,new Color(100, 100, 100));
        p2 = new GradientPaint(0, 1, new Color(0, 0, 0, 50), 0, h - 3,new Color(255, 255, 255, 100));
}
else {
  p1 = new GradientPaint(0, 0, new Color(100, 100, 100), 0, h - 1,new Color(0, 0, 0));
  p2 = new GradientPaint(0, 1, new Color(255, 255, 255, 100), 0,h - 3, new Color(0, 0, 0, 50));
  GP = new GradientPaint(0, 0, startColor, 0, h, endColor, true);
 }
 RoundRectangle2D.Float r2d = new RoundRectangle2D.Float(0, 0, w - 1,h - 1, outerRoundRectSize, outerRoundRectSize);
 Shape clip = g2d.getClip();g2d.clip(r2d);
 g2d.fillRect(0, 0, w, h);
 g2d.setClip(clip);
 g2d.setPaint(p1);
 g2d.drawRoundRect(0, 0, w - 1, h - 1, outerRoundRectSize,outerRoundRectSize);
 g2d.setPaint(p2);
 g2d.drawRoundRect(1, 1, w - 3, h - 3, innerRoundRectSize,innerRoundRectSize);
 g2d.dispose();
 super.paintComponent(g);
}
 
 private void _drawRect(Graphics2D g2, int x, int w, Color hiCol,Color mdCol, Color loCol) {
 Dimension d = getSize();
 // Divide height into quarters
 int h = (int) d.getHeight();
 int th = h / 4;
 GradientPaint p1;
 GradientPaint p2;
 ButtonModel model = getModel();
 // Paint shaded area
 if (!model.isEnabled()) {
 p1 = new GradientPaint(0, 0, new Color(192, 192, 192), 0, th,new Color(192, 192, 192));
 p2 = new GradientPaint(0, 4, new Color(192, 192, 192), 0, h - th- 4, new Color(192, 192, 192));
 } 
 else {if (model.isRollover()) {
 p1 = new GradientPaint(0, 0, new Color(255, 143, 89), 0, th,selectedHigh);
 p2 = new GradientPaint(0, 4, selectedHigh, 0, h - th - 4,new Color(255, 143, 89));
 } 
 else {
 p1 = new GradientPaint(0, 0, loCol, 0, th, hiCol);
 p2 = new GradientPaint(0, 4, hiCol, 0, h - th - 4, loCol);
 }
}

g2.setPaint(p1);
g2.fillRoundRect(1, 1, w - 2, th, 6, 6);
g2.setPaint(p2);
g2.fillRect(1, th, w - 2, h - th - 1);
if (model.isPressed()) {
 p1 = new GradientPaint(0, 0, selectedLow, 0, th, selectedHigh);
 p2 = new GradientPaint(0, 4, selectedHigh, 0, h - th - 4,selectedLow);
 g2.setPaint(p1);
 g2.fillRoundRect(1, 1, w - 2, th, 6, 6);g2.setPaint(p2);
 g2.fillRect(1, th, w - 2, h - th - 1);
 } 
// g2.fillRect(1,th,w-2,h-th-1);
 }
 /***  This method sets the Actual Background Color of the Button*/
 public void setStartColor(Color color) {
 COLOR1 = color;
 }
 
 /***  This method sets the Pressed Color of the Button*/
 public void setEndColor(Color pressedColor) {
 COLOR2 = pressedColor;
 }
 
 /***  This method sets the OuterRoundRect Size of the Button*/
 public void setOuterRoundRectSize(int outerRoundRectSize) {
 this.outerRoundRectSize = outerRoundRectSize;
 }
 
 /***  This method sets the InnerRoundRect Size of the Button*/
 public void setInnerRoundRectSize(int innerRoundRectSize) {
 this.innerRoundRectSize = innerRoundRectSize;
 }
 /*** @return  Starting Color of the Button*/
 public Color getStartColor() {
 return COLOR1;
 }
 
 /*** @return  Ending Color of the Button*/
 public Color getEndColor() {
 return COLOR2;
 }
 /*** @return the OuterRoundRect Size of the Button*/
 public int getOuterRoundRectSize() {
 return this.outerRoundRectSize;
 }
 
 /*** @return the InnerRoundRect Size of the Button*/
 public int getInnerRoundRectSize() {
 return this.innerRoundRectSize;
 }
 
// public static void main(String args[]) {
// try {
//   //UIManager.setLookAndFeel(new NimbusLookAndFeel());
////   UIManager.setLookAndFeel(new SubstanceLookAndFeel());
////   SubstanceLookAndFeel.setSkin(new NebulaSkin());
//   GradientRoundRectButton standardButton = new GradientRoundRectButton("Standard Button");
//   JFrame frame = new JFrame("CustomButtons Demo");
//   frame.setLayout(new FlowLayout());
//   frame.add(standardButton.getButtonsPanel());
//   frame.getContentPane().setBackground(Color.WHITE);
//   frame.setBackground(Color.WHITE);
//   frame.setSize(700, 85);
//   frame.setVisible(true);
//   } catch (Exception e) {
//   // TODO: handle exception} 
//   
//   }
// }
   
//  public JPanel getButtonsPanel() {
//  JPanel panel = new JPanel();
//  panel.setBackground(Color.WHITE);
//  GradientRoundRectButton standardButton = new GradientRoundRectButton("Standard Button");
//  standardButton.setPreferredSize(new Dimension(130, 28));
//  GradientRoundRectButton rollOverButton = new GradientRoundRectButton("RollOver Button");
//  rollOverButton.setPreferredSize(new Dimension(130, 28));
//  GradientRoundRectButton disabledButton = new GradientRoundRectButton("Disable Button");
//  disabledButton.setPreferredSize(new Dimension(130, 28));
//  disabledButton.setEnabled(false);
//  GradientRoundRectButton pressedButton = new GradientRoundRectButton("Pressed Button");
//  pressedButton.setPreferredSize(new Dimension(130, 28));
//  panel.add(standardButton);
//  panel.add(rollOverButton);
//  panel.add(disabledButton);
//  panel.add(pressedButton);
//  return panel;
//  }
}