/*
 * Copyright (c) 2015, Kent
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package classes;

/**
 *
 * @author Kent
 */
import models.saleRecord;
 import java.util.Vector; 
 import javax.swing.table.AbstractTableModel; 
//import javax.swing.table.DefaultTableModel;
 public class saleTableModel extends AbstractTableModel { 
     public static final int ITEM_INDEX = 0;
     public static final int UNITS_INDEX = 1;
     public static final int AVAIL_INDEX = 2;
     public static final int UNITP_INDEX = 3;
     public static final int AMOUNT_INDEX = 4;
     public static final int SO_UNIT_INDEX = 5;
     public static final int PACK_INDEX = 6;
     public static final int SHIPPED_INDEX = 7;
     public static final int HIDDEN_INDEX = 8;
 
     protected String[] columnNames; 
     protected Vector dataVector; 
     
     private final superCls sup=new superCls();
 
     public saleTableModel(String[] columnNames) { 
         this.columnNames = columnNames; 
         dataVector = new Vector(); 
     } 
 
     public String getColumnName(int column) { 
         return columnNames[column]; 
     } 
 
  
     public boolean isCellEditable(int row, int column) { 
         if (column == HIDDEN_INDEX) return false; 
         else return true; 
     } 
 
     public Class getColumnClass(int column) { 
         switch (column) {
             case ITEM_INDEX: 
             case HIDDEN_INDEX:
                 return String.class; 
             case UNITS_INDEX :
             case AVAIL_INDEX :
             case SHIPPED_INDEX :
             case PACK_INDEX :
             case SO_UNIT_INDEX :
                 return Integer.class;
             case UNITP_INDEX :             
             case AMOUNT_INDEX :
                return Double.class;
             default: 
                return Object.class; 
         } 
     } 
 public void removeRow(int row) 
 {
       dataVector.remove(row); 
         fireTableRowsDeleted( 
            dataVector.size() - 1, 
            dataVector.size() - 1); 
 }
 
 public void clearAllRows() 
 {
       dataVector.removeAllElements(); 
         this.addEmptyRow(new saleRecord("", 0, 0, 0.00, 0.00, 0, 0, 0,""));
 }
     public Object getValueAt(int row, int column) { 
         saleRecord record = (saleRecord)dataVector.get(row); 
         switch (column) { 
             case ITEM_INDEX: 
                return record.getItem();
             case UNITS_INDEX:
                 return record.getUnits();
             case AVAIL_INDEX:
                return record.getAvail(); 
             case UNITP_INDEX:
                 return record.getUnit_price();
             case AMOUNT_INDEX:
                 return record.getAmount();
             case SO_UNIT_INDEX:   
                 return record.getSo_units();
             case PACK_INDEX:   
                 return record.getPacks();
            case SHIPPED_INDEX:   
                return record.getShipped();
            case HIDDEN_INDEX :
                return record.getItem_no();
             default: 
                return new Object(); 
         } 
     } 
 
     public void setValueAt(Object value, int row, int column) { 
         saleRecord record = (saleRecord)dataVector.get(row); 
         switch (column) { 
             case ITEM_INDEX: 
                 record.setItem(String.valueOf(value==null?"Item":value));
                 break;
             case UNITS_INDEX :
                  record.setUnits(Integer.parseInt(sup.getIntValue(value==null?0:value)));
                 break;
             case AVAIL_INDEX :
                 record.setAvail(Integer.parseInt(sup.getIntValue(value==null?0:value)));
                 break;
             case UNITP_INDEX :
                 record.setUnit_price(Double.parseDouble(sup.getCostValue(value==null?0.0:value)));
                 break;
             case AMOUNT_INDEX :
                 record.setAmount(Double.parseDouble(sup.getCostValue(value==null?0.0:value)));
                 break;
             case SO_UNIT_INDEX:   
                 record.setSo_units(Integer.parseInt(sup.getIntValue(value==null?0:value)));    
                 break;
            case PACK_INDEX:   
                 record.setPacks(Integer.parseInt(sup.getIntValue(value==null?0:value)));    
                 break;     
            case SHIPPED_INDEX:   
                 record.setShipped(Integer.parseInt(sup.getIntValue(value==null?0:value)));    
                 break;  
            case HIDDEN_INDEX :
                record.setItem_no(String.valueOf(value==null?"Item1":value));
             default: 
                System.out.println("invalid index"); 
                 break;
         } 
         fireTableCellUpdated(row, column); 
     } 
 
     public int getRowCount() { 
         return dataVector.size(); 
     } 
 
     public int getColumnCount() { 
         return columnNames.length; 
     } 
 
     public boolean hasEmptyRow() { 
         if (dataVector.size() == 0) 
             return false; 
         saleRecord record = (saleRecord)dataVector.get(dataVector.size() - 1); 
         if (sup.getValue(record.getItem()).trim().equals(""))         { 
            return true; 
         } 
         else return false; 
     } 
 
     public void addEmptyRow(saleRecord record) { 
         dataVector.add(record); 
         fireTableRowsInserted( 
            dataVector.size() - 1, 
            dataVector.size() - 1); 
     } 
 }
