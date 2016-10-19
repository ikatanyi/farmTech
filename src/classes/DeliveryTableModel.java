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
import models.deliveryRecord;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;
 
 public class DeliveryTableModel extends AbstractTableModel { 
     public static final int QTY_INDEX = 0; 
     public static final int ITEMNO_INDEX = 1;
     public static final int ITEM_INDEX = 2;
     public static final int DESCRIPTION_INDEX = 3;
     public static final int HIDDEN_INDEX = 4;
 
     protected String[] columnNames; 
     protected Vector dataVector; 
     
     private final superCls sup=new superCls();
 
     public DeliveryTableModel(String[] columnNames) { 
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
             case QTY_INDEX:   
                 return Integer.class;
             case ITEM_INDEX: 
             case ITEMNO_INDEX: 
             case DESCRIPTION_INDEX: 
                return String.class; 
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
         this.addEmptyRow(new deliveryRecord(1,"Item","",""));
 }
     public Object getValueAt(int row, int column) { 
         deliveryRecord record = (deliveryRecord)dataVector.get(row); 
         switch (column) { 
             case QTY_INDEX:
                return record.getQuantity(); 
             case ITEM_INDEX: 
                return record.getItem();
             case ITEMNO_INDEX: 
                return record.getItem_no();
             case DESCRIPTION_INDEX: 
                return record.getDescription();
             default: 
                return new Object(); 
         } 
     } 
 
     public void setValueAt(Object value, int row, int column) { 
         deliveryRecord record = (deliveryRecord)dataVector.get(row); 
         switch (column) { 
             case QTY_INDEX:
                 record.setQuantity(Integer.parseInt(sup.getIntValue(value==null?0:value)));
                 break;
             case ITEM_INDEX: 
                 record.setItem(String.valueOf(value==null?"Item":value));
                 break;
             case ITEMNO_INDEX: 
                 record.setItem_no(String.valueOf(value==null?"":value));
                 break;
             case DESCRIPTION_INDEX: 
                 record.setDescription(String.valueOf(value==null?"":value));
                 break;
             default: 
                System.out.println("invalid index"); 
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
         deliveryRecord record = (deliveryRecord)dataVector.get(dataVector.size() - 1); 
         if (sup.getValue(record.getItem()).trim().equals(""))         { 
            return true; 
         } 
         else return false; 
     } 
 
     public void addEmptyRow(deliveryRecord record) { 
         dataVector.add(record); 
         fireTableRowsInserted( 
            dataVector.size() - 1, 
            dataVector.size() - 1); 
     } 
 }
