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
 
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.TableCellEditor;
 
/**
 * A custom editor for cells in the Country column.
 * @author www.codejava.net
 *
 */
public class comboCellEditor extends AbstractCellEditor 
        implements TableCellEditor, ActionListener {
    
    private JComboBox jComboBox = new JComboBox();
    private DefaultComboBoxModel model=null;
     
    public comboCellEditor(DefaultComboBoxModel model) {
        this.model = model;
        jComboBox = new JComboBox(this.model);
    }
     
    public comboCellEditor(JComboBox combobox) {
        jComboBox=combobox;
        jComboBox.setBorder (null);
    }
    
    boolean cellEditingStopped = false;

    @Override
    public Object getCellEditorValue() {
        return jComboBox.getSelectedItem();
    }
 
    
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
//        Vector vector = new Vector();
//        ArrayList<Integer> arrayList = new ArrayList<Integer>();
//        arrayList.add(Integer.parseInt(value.toString()));
//        vector.add(0);
//        for (int i = 0; i < table.getRowCount(); i++) {
//            if (!vector.contains(table.getValueAt(i, 0)) && table.getValueAt(i, 3).toString().equals("Sheep")) {
//                vector.add(table.getValueAt(i, 0));
//            }
//        }
//        vector.remove(table.getValueAt(row, 0));
//
//        for (int i = 0; i < vector.size(); i++) {
//        }
        
        jComboBox.setSelectedItem(value);

        jComboBox.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    fireEditingStopped();
                }
            }
        });
        jComboBox.addPopupMenuListener(new PopupMenuListener() {

            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                cellEditingStopped = false;
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                cellEditingStopped = true;
                fireEditingCanceled();
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {

            }

        });
        return jComboBox;
    }

    @Override
    public boolean stopCellEditing() {
        return cellEditingStopped;
    } 

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
