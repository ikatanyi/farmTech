/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.awt.Component;
import java.text.DecimalFormat;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Kent
 */
public class DecimalFormatRenderer extends DefaultTableCellRenderer {
    private static final DecimalFormat formatter = new DecimalFormat( "##.00" ); 
    public Component getTableCellRendererComponent(

        JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        // First format the cell value as required

        value = formatter.format((Number)Double.parseDouble(new superCls().getCostValue(value)));
        // And pass it on to parent class
        return super.getTableCellRendererComponent(
           table, value, isSelected, hasFocus, row, column );
    }
}
   
