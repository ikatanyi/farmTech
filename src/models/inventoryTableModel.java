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
package models;

/**
 *
 * @author Kent
 */

import classes.superCls;
import java.text.SimpleDateFormat;
import java.util.Date;
import models.medRecord;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;


 public class inventoryTableModel extends AbstractTableModel { 
     public static final int ITEM_INDEX = 0;
     public static final int ITEMID_INDEX = 1;      
     public static final int UNITS_INDEX = 2;
     public static final int LOCATION_INDEX = 3;
     public static final int SERIAL_INDEX = 4; 
     public static final int EXPDATE_INDEX =5;
     public static final int REFERENCE_INDEX = 6;
     public static final int COST_INDEX = 7;
     public static final int PRICE_INDEX = 8;
     public static final int HIDDEN_INDEX = 9;
     
     Date date;
     protected String[] columnNames; 
     protected Vector dataVector; 
     SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//     date=sup.sqlDate(dateFormat.format(dateFormat.parse(new Date())));
     
     private final superCls sup=new superCls();
 
     public inventoryTableModel(String[] columnNames) { 
        this.date = sup.sqlDate(dateFormat.format(new Date()));
         this.columnNames = columnNames; 
         dataVector = new Vector(); 
     } 
 
     public String getColumnName(int column) { 
         return columnNames[column]; 
     } 
 
  
     public boolean isCellEditable(int row, int column) { 
         if (column == HIDDEN_INDEX || column==ITEMID_INDEX) 
             return false; 
         else 
             return true; 
     } 
 
     
     public Class getColumnClass(int column) { 
         switch (column) {
             case UNITS_INDEX:
                 return Integer.class;
             case PRICE_INDEX:
             case COST_INDEX:    
                 return Double.class;
             case ITEMID_INDEX:    
             case ITEM_INDEX: 
             case LOCATION_INDEX:
             case SERIAL_INDEX:             
             case REFERENCE_INDEX:    
             case HIDDEN_INDEX:
                return String.class; 
             case EXPDATE_INDEX:
                 return Date.class;
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
 
 public void clearAllRows() {
       dataVector.removeAllElements();        
       this.addEmptyRow(new inventoryRecord("","",0,"","",date,"",0.00,0.00));
 }
     public Object getValueAt(int row, int column) { 
         inventoryRecord record = (inventoryRecord)dataVector.get(row); 
         switch (column) { 
             case ITEMID_INDEX:
                return record.getItemId();
             case ITEM_INDEX:
                return record.getItem();
             case UNITS_INDEX:  
                return record.getUnits();
             case LOCATION_INDEX: 
                return record.getLocation();
             case SERIAL_INDEX:    
                return record.getSerial();
             case EXPDATE_INDEX: 
                return record.getExpDate();
             case REFERENCE_INDEX: 
                return record.getReference();
             case COST_INDEX:
                 return record.getCost();
             case PRICE_INDEX: 
                return record.getPrice();
             default: 
                return new Object(); 
         } 
     } 
 
     public void setValueAt(Object value, int row, int column) { 
         inventoryRecord record = (inventoryRecord)dataVector.get(row); 
         switch (column) { 
             case ITEMID_INDEX:
                 record.setItemId(String.valueOf(value==null?"":value));
                 break;
             case ITEM_INDEX:
                 record.setItem(String.valueOf(value==null?"":value));
                 break;
             case UNITS_INDEX:  
                  record.setUnits(Integer.parseInt(sup.getIntValue(value==null?0:value)));
                 break;
             case LOCATION_INDEX: 
                 record.setLocation(String.valueOf(value==null?"":value));
                 break;
             case SERIAL_INDEX:    
                 record.setSerial(String.valueOf(value==null?"":value));
                 break;
             case EXPDATE_INDEX: 
                 record.setExpDate(value==null?date:(Date)value);
                 break;
             case REFERENCE_INDEX: 
                 record.setReference(String.valueOf(value==null?"":value));
                 break;
             case COST_INDEX:
                 record.setCost(Double.parseDouble(sup.getCostValue(value==null?0.0:value)));
                 break;
             case PRICE_INDEX: 
                 record.setPrice(Double.parseDouble(sup.getCostValue(value==null?0.0:value)));
                 break;
             case HIDDEN_INDEX:
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
     
     public inventoryRecord getRowValues(int row) { 
         inventoryRecord record = (inventoryRecord)dataVector.get(row); 
         return record;
     }
 
     public boolean hasEmptyRow() { 
         if (dataVector.size() == 0) 
             return false; 
         inventoryRecord record = (inventoryRecord)dataVector.get(dataVector.size() - 1); 
         if (sup.getValue(record.getItem()).trim().equals(""))         { 
            return true; 
         } 
         else return false; 
     } 
 
     public void addEmptyRow(inventoryRecord record) { 
         dataVector.add(record); 
         fireTableRowsInserted( 
            dataVector.size() - 1, 
            dataVector.size() - 1); 
     } 
 }
