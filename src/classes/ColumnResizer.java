package classes;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kent
 */
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class ColumnResizer {

    public static void adjustColumnPreferredWidths(JTable table) {
        try{
        // strategy - get max width for cells in column and
        // make that the preferred width
        TableColumnModel columnModel = table.getColumnModel();
        for (int col=0; col<table.getColumnCount(); col++) {
            // System.out.println ("--- col " + col + " ---");
            int maxwidth = 0;
            for (int row=0; row<table.getRowCount(); row++) {
                TableCellRenderer rend = table.getCellRenderer (row, col);
                Object value = table.getValueAt (row, col);
                Component comp =
                    rend.getTableCellRendererComponent (table, 
                                                        value,
                                                        false,
                                                        false,
                                                        row,
                                                        col);
                maxwidth = Math.max (comp.getPreferredSize().width,
                                     maxwidth);
                //System.out.println ("col " + col +
                //                    " pref width now " + 
                //                    maxwidth);
            } // for row

            /* this version of the width set doesn't consider the
               column header's preferred width
            */
//            TableColumn column = columnModel.getColumn (col);
//            column.setPreferredWidth (maxwidth);

            /* this version of the width set considers the
               column header's preferred width too*/
            TableColumn column = columnModel.getColumn (col);
            TableCellRenderer headerRenderer = column.getHeaderRenderer();
            if (headerRenderer == null)
                headerRenderer = table.getTableHeader().getDefaultRenderer();
            Object headerValue = column.getHeaderValue();
            Component headerComp = 
                    headerRenderer.getTableCellRendererComponent (table, 
                                                                  headerValue,
                                                                  false,
                                                                  false,
                                                                  0,
                                                                  col);
            maxwidth = Math.max (maxwidth, 
                                 headerComp.getPreferredSize().width);
            column.setPreferredWidth (maxwidth);
            

        } // for col
         }catch(Exception ex){
    }
   
    
}
}

