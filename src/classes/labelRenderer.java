package classes;


import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTable;
import java.awt.Component;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kent
 */
public class labelRenderer extends JLabel implements TableCellRenderer{
    JLabel label=new JLabel();
    private final  DefaultTableCellRenderer adaptee =  new  DefaultTableCellRenderer();
    
    @Override
    public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,
            boolean hasFocus,int row ,int column){
        adaptee.getTableCellRendererComponent(table, value,isSelected, hasFocus, row, column);  
        setForeground(Color.red);  
        setBackground(table.getBackground()); 
//        setBorder(adaptee.getBorder());
        setOpaque(true);
        setFont(adaptee.getFont());  
        setText(adaptee.getText());  
        setText("<html><body><p><font color=blue><u>"+((value !=  null )?value.toString() :  "")+"</u></font></p></body></html>");
        if(isSelected){
            setText("<html><body><p><font color=blue><u>"+((value !=  null )?value.toString() :  "")+"</u></font></p></body></html>");
        }
        if(!hasFocus){
           setText("<html><body><p><font color=blue>"+((value !=  null )?value.toString() :  "")+"</font></p></body></html>"); 
        } 
        this.setHorizontalAlignment(adaptee.CENTER);
        return this;
           
}
    
}
