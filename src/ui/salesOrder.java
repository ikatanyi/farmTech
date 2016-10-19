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
package ui;

import classes.DeliveryTableModel;
import classes.InvoiceTableModel;
import classes.TextFieldEditor;
import classes.clsReport;
import classes.comboCellEditor;
import classes.rowcount;
import classes.saleTableModel;
import classes.superCls;
import custom_jPanels.SimpleGradientPanel;
import farm.entity.DtCustomer;
import farm.entity.DtDelivery;
import farm.entity.DtDeliveryStatus;
import farm.entity.DtInventoryItemlist;
import farm.entity.DtInvoice;
import farm.entity.DtOrdersale;
import farm.entity.DtSaletrx;
import farm.entity.DtShippings;
import farm.entity.DtStoreStock;
import farm.entity.VCurrentStock;
import farmTech.util.HibernateUtil;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import models.InvoiceRecord;
import models.deliveryRecord;
import models.saleRecord;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Kent
 */
public class salesOrder extends javax.swing.JFrame {
private saleTableModel item_model;
private Object[] customerID, SHIPMENT,address,contact,itemID,price;
private final TextFieldEditor inte=new TextFieldEditor("integer");
private final TextFieldEditor str=new TextFieldEditor("string");
private JLabel errorFields;
private JComboBox combobox=new JComboBox();
private superCls sup=new superCls();
private String customerid;
private Double gross=0.00;
private HashMap jasperParameter;
clsReport rpt;
DtOrdersale ordersale;
    /**
     * 
     * Creates new form salesOrder
     */
    public salesOrder() {
        initComponents();
    }

    public void formload(){
        String val=String.valueOf(new rowcount().row_count(DtDelivery.class)+1);
        String prodid="D/O"+sup.getcode(6, val);

        String invoice=String.valueOf(new rowcount().row_count(DtInvoice.class)+1);
        String inv="INV"+sup.getcode(6, invoice);

        String sale=String.valueOf(new rowcount().row_count(DtOrdersale.class)+1);
        String sal="S"+sup.getcode(6, sale);  
        
        txt_saleno.setText(sal);
        txt_invoice.setText(inv);
        txtdeliveryNo.setText(prodid);
        
        SwingWorker doComputeRate=new SwingWorker() {
                  @Override
	          protected Integer doInBackground() throws Exception{
                    cmb_customer.setModel(customers());
                    cmb_shipping_type.setModel(shippings());
                  return 0;
	        }
                @Override
                protected void done(){
                     SwingUtilities.invokeLater(new Runnable() {
                       public void run() {  
                           cmb_customer.setSelectedIndex(-1);
                           cmb_shipping_type.setSelectedIndex(-1);     
//                        jTable5.getColumnModel().getColumn(7).setCellRenderer( new  labelRenderer());
//                        ColumnResizer.adjustColumnPreferredWidths (jTable5);
//                        jTable5.revalidate();
                    }});
                }
        };
        doComputeRate.execute();
        String header[]={"Item Name","Units","Avail","Unit Price","Amount","SO_Units","Packs","Shipped"};
        item_model=new  saleTableModel(header);
        item_model.addTableModelListener(new InteractiveTableModelListener());
        jTable1.setModel(item_model);
        combobox.setModel(items());
        combobox.setBorder (BorderFactory.createEmptyBorder());
         if(!item_model.hasEmptyRow()){
                item_model.addEmptyRow(new saleRecord("", 0, 0, 0.0, 0.0, 0, 0, 0,""));
                jTable1.getColumnModel().getColumn(0).setCellEditor(new comboCellEditor(combobox));
                jTable1.getColumnModel().getColumn(1).setCellEditor(inte);
                jTable1.getColumnModel().getColumn(2).setCellEditor(inte);
                jTable1.getColumnModel().getColumn(3).setCellEditor(inte);
                jTable1.getColumnModel().getColumn(4).setCellEditor(inte);
                jTable1.getColumnModel().getColumn(5).setCellEditor(inte);
                jTable1.getColumnModel().getColumn(6).setCellEditor(inte);
                jTable1.getColumnModel().getColumn(7).setCellEditor(inte);
            } 
        jTable1.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        
        combobox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int idx=combobox.getSelectedIndex();      
                int row =jTable1.getSelectedRow();         
                if (e.getStateChange() == ItemEvent.SELECTED && idx!=0 && idx!=-1) {                
                    if(idx!=-1 && idx!=0){ 
                       String item = sup.getValue(item_model.getValueAt(row, 0));  
                       String item_no=sup.getValue(itemID[idx]);
                       Integer  avail= Integer.parseInt(sup.getIntValue(getAvail (item_no)));
                       double  unit_price=Double.parseDouble(sup.getCostValue(price[idx]));
                       item_model.setValueAt(avail, row, 2);
                       item_model.setValueAt(unit_price, row, 3);
                       item_model.setValueAt(item_no, row, 8);
                       addRow();
                       highlightLastRow();
                    }                    
                }
            }
        });
    }
    
    private DefaultComboBoxModel shippings () {                 
       int i=0;
       Query q;
       DefaultComboBoxModel tmodel =new DefaultComboBoxModel();
       org.hibernate.Session session = HibernateUtil.getSessionFactory().openSession();
      try {        
           session.beginTransaction();
           q = session.createQuery("from DtShippings c");
           q.setCacheable(true);
           List auth=q.list();
           Iterator iterator=auth.iterator();
           SHIPMENT=new Object[auth.size()];
           while(iterator.hasNext()){ 
                   DtShippings cust=(DtShippings)iterator.next();
                   tmodel.addElement(cust.getName()); 
                   SHIPMENT[i]=cust.getValue();
                   i++;         
           }
           session.getTransaction().commit();
         }catch (HibernateException he) {
                 errorFields = new JLabel("<HTML><FONT COLOR = Blue>'"+he.getMessage()+"'</FONT></HTML>");
                 JOptionPane.showMessageDialog(null,errorFields,"csrim",JOptionPane.ERROR_MESSAGE);

         }finally {
             session.close(); 
         }
          return tmodel;
    }
    
    public void addRow(){        
       if(!item_model.hasEmptyRow()){
            item_model.addEmptyRow(new saleRecord("", 0, 0, 0.00, 0.00, 0, 0, 0,""));            
            jTable1.getColumnModel().getColumn(0).setCellEditor(new comboCellEditor(combobox));
            jTable1.getColumnModel().getColumn(1).setCellEditor(inte);
            jTable1.getColumnModel().getColumn(2).setCellEditor(inte);
            jTable1.getColumnModel().getColumn(3).setCellEditor(inte);
            jTable1.getColumnModel().getColumn(4).setCellEditor(inte);
            jTable1.getColumnModel().getColumn(5).setCellEditor(inte);
            jTable1.getColumnModel().getColumn(6).setCellEditor(inte);
            jTable1.getColumnModel().getColumn(7).setCellEditor(inte);
        }
       
       
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new SimpleGradientPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new SimpleGradientPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cmb_customer = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtA_bill_to = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        txt_contact = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtA_shipto = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cmb_shipby = new javax.swing.JComboBox();
        cmb_shipping_type = new javax.swing.JComboBox();
        txt_customer_acc = new javax.swing.JTextField();
        txt_po = new javax.swing.JTextField();
        txt_process_no = new javax.swing.JTextField();
        txt_contact_name = new javax.swing.JTextField();
        txt_attention = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jdate_created_on = new com.toedter.calendar.JDateChooser();
        jdate_ship_date = new com.toedter.calendar.JDateChooser();
        jLabel17 = new javax.swing.JLabel();
        txt_carrier_no = new javax.swing.JTextField();
        txt_carrier_name = new javax.swing.JTextField();
        jdate_po_duedate = new com.toedter.calendar.JDateChooser();
        jLabel18 = new javax.swing.JLabel();
        txt_saleno = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel3 = new SimpleGradientPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jButton4 = new javax.swing.JButton();
        jPanel4 = new SimpleGradientPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txt_invoice = new javax.swing.JTextField();
        jDate_due_date = new com.toedter.calendar.JDateChooser();
        jDate_invoice_date = new com.toedter.calendar.JDateChooser();
        txt_gross = new javax.swing.JTextField();
        txt_discount = new javax.swing.JTextField();
        txt_vat = new javax.swing.JTextField();
        txt_shipping = new javax.swing.JTextField();
        txt_charges = new javax.swing.JTextField();
        txt_total = new javax.swing.JTextField();
        chckbx_invoice = new javax.swing.JCheckBox();
        chckbx_delivery = new javax.swing.JCheckBox();
        jLabel28 = new javax.swing.JLabel();
        txtdeliveryNo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel2.setText("Bill To :");

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel3.setText("Address :");

        cmb_customer.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        cmb_customer.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_customerItemStateChanged(evt);
            }
        });

        txtA_bill_to.setColumns(20);
        txtA_bill_to.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        txtA_bill_to.setRows(5);
        jScrollPane1.setViewportView(txtA_bill_to);

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel4.setText("Contact :");

        txt_contact.setToolTipText("");

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel5.setText("Ship To:");

        txtA_shipto.setColumns(20);
        txtA_shipto.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        txtA_shipto.setRows(5);
        jScrollPane2.setViewportView(txtA_shipto);

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel6.setText("Ship By :");

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel7.setText("Shipping Type :");

        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel8.setText("Customer Acc.");

        jLabel9.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel9.setText("Customer PO");

        jLabel10.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel10.setText("Customer PO Due.");

        jLabel11.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel11.setText("Process No.");

        jLabel12.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel12.setText("Contact Name :");

        jLabel13.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel13.setText("Attention :");

        cmb_shipby.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Bus", "Train", "Ship", "Air", "Motorcycle", "Hand Delivery", "Other" }));

        cmb_shipping_type.setEditable(true);
        cmb_shipping_type.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "...", "Best Way" }));

        txt_customer_acc.setToolTipText("");

        txt_po.setToolTipText("");

        txt_process_no.setToolTipText("");

        txt_contact_name.setToolTipText("");

        txt_attention.setToolTipText("");

        jLabel14.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel14.setText("Created On :");

        jLabel15.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel15.setText("Shipped On Date :");

        jLabel16.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel16.setText("Carrier :");

        jdate_created_on.setDate(new Date());

        jdate_ship_date.setDate(new Date());

        jLabel17.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel17.setText("Carrier # :");

        txt_carrier_no.setToolTipText("");

        txt_carrier_name.setToolTipText("");

        jdate_po_duedate.setDate(new Date());

        jLabel18.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel18.setText("Sale No :");

        txt_saleno.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        txt_saleno.setToolTipText("");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 732, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(txt_saleno, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel2)
                                .addComponent(cmb_customer, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel4)
                                .addComponent(txt_contact)
                                .addComponent(jLabel5)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txt_contact_name, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_process_no, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jdate_po_duedate, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_po, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_customer_acc, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_attention, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(cmb_shipby, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmb_shipping_type, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jdate_ship_date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_carrier_name)
                            .addComponent(txt_carrier_no)
                            .addComponent(jdate_created_on, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel18)
                .addGap(4, 4, 4)
                .addComponent(txt_saleno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel8))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_customer_acc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jdate_created_on, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_po, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmb_shipby, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cmb_shipping_type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jdate_po_duedate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel15))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_process_no, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jdate_ship_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_contact_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_carrier_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_attention, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_carrier_no, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(4, 4, 4)
                        .addComponent(cmb_customer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_contact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Order Header", jPanel2);

        jTable1.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Item Name", "Units", "Available", "Unit Price", "Amount", "SO_Units", "Packs", "Shipped"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, false, false, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setGridColor(new java.awt.Color(204, 204, 204));
        jTable1.setRowHeight(22);
        jTable1.setSelectionBackground(new java.awt.Color(204, 204, 255));
        jScrollPane3.setViewportView(jTable1);

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jRadioButton1.setText("Items");
        jRadioButton1.setEnabled(false);
        jRadioButton1.setOpaque(false);

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jRadioButton2.setText("Equipments");
        jRadioButton2.setEnabled(false);
        jRadioButton2.setOpaque(false);

        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jRadioButton3.setText("Drugs");
        jRadioButton3.setEnabled(false);
        jRadioButton3.setOpaque(false);

        buttonGroup1.add(jRadioButton4);
        jRadioButton4.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jRadioButton4.setSelected(true);
        jRadioButton4.setText("All Items");
        jRadioButton4.setOpaque(false);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete_16.png"))); // NOI18N
        jButton4.setToolTipText("Removes a selected row");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addGap(144, 144, 144)
                        .addComponent(jRadioButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton3)
                        .addGap(0, 324, Short.MAX_VALUE)))
                .addGap(5, 5, 5))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2)
                    .addComponent(jRadioButton3)
                    .addComponent(jRadioButton4)
                    .addComponent(jButton4))
                .addGap(4, 4, 4)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        jTabbedPane1.addTab("Line Items", jPanel3);

        jLabel19.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel19.setText("Invoice No:");

        jLabel20.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel20.setText("Invoice Date :");

        jLabel21.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel21.setText("Payment Due :");

        jLabel22.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel22.setText("Sub Total Amount :");

        jLabel23.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel23.setText("Discount % :");

        jLabel24.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel24.setText("VAT :");

        jLabel25.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel25.setText("Shipping Charges :");

        jLabel26.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel26.setText("Other Charges :");

        jLabel27.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel27.setText("Order Total :");

        txt_invoice.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        txt_invoice.setToolTipText("");

        jDate_due_date.setDate(new Date());
        jDate_due_date.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jDate_due_date.setOpaque(false);

        jDate_invoice_date.setDate(new Date());
        jDate_invoice_date.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jDate_invoice_date.setOpaque(false);

        txt_gross.setEditable(false);
        txt_gross.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        txt_gross.setText("0.00");
        txt_gross.setToolTipText("");
        txt_gross.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_grossKeyTyped(evt);
            }
        });

        txt_discount.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        txt_discount.setText("0.00");
        txt_discount.setToolTipText("");
        txt_discount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_discountActionPerformed(evt);
            }
        });
        txt_discount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_discountFocusLost(evt);
            }
        });
        txt_discount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_discountKeyTyped(evt);
            }
        });

        txt_vat.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        txt_vat.setText("0.00");
        txt_vat.setToolTipText("");
        txt_vat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_vatActionPerformed(evt);
            }
        });
        txt_vat.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_vatFocusLost(evt);
            }
        });
        txt_vat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_vatKeyTyped(evt);
            }
        });

        txt_shipping.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        txt_shipping.setText("0.00");
        txt_shipping.setToolTipText("");
        txt_shipping.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_shippingActionPerformed(evt);
            }
        });
        txt_shipping.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_shippingFocusLost(evt);
            }
        });
        txt_shipping.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_shippingKeyTyped(evt);
            }
        });

        txt_charges.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        txt_charges.setText("0.00");
        txt_charges.setToolTipText("");
        txt_charges.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_chargesActionPerformed(evt);
            }
        });
        txt_charges.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_chargesFocusLost(evt);
            }
        });
        txt_charges.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_chargesKeyTyped(evt);
            }
        });

        txt_total.setEditable(false);
        txt_total.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        txt_total.setText("0.00");
        txt_total.setToolTipText("");
        txt_total.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_totalKeyTyped(evt);
            }
        });

        chckbx_invoice.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        chckbx_invoice.setSelected(true);
        chckbx_invoice.setText("Print Invoice :                      ");
        chckbx_invoice.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        chckbx_invoice.setOpaque(false);
        chckbx_invoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chckbx_invoiceActionPerformed(evt);
            }
        });

        chckbx_delivery.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        chckbx_delivery.setSelected(true);
        chckbx_delivery.setText("Print Delivery :                     ");
        chckbx_delivery.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        chckbx_delivery.setOpaque(false);

        jLabel28.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel28.setText("Delivery No :");

        txtdeliveryNo.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        txtdeliveryNo.setToolTipText("");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chckbx_invoice)
                    .addComponent(chckbx_delivery)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(jLabel20)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_invoice)
                            .addComponent(jDate_invoice_date, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                            .addComponent(jDate_due_date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtdeliveryNo))))
                .addGap(65, 65, 65)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27)
                    .addComponent(jLabel24)
                    .addComponent(jLabel23)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_charges, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_shipping, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_vat, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_discount, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_gross, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(204, Short.MAX_VALUE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txt_charges, txt_discount, txt_gross, txt_shipping, txt_total, txt_vat});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel19)
                        .addComponent(txt_invoice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel22)
                        .addComponent(txt_gross, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDate_invoice_date, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_discount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDate_due_date, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_vat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(txt_shipping, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(txt_charges, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chckbx_invoice))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chckbx_delivery)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(txtdeliveryNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(151, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Invoice/Totals", jPanel4);

        jLabel1.setBackground(new java.awt.Color(177, 118, 88));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Order Sale");
        jLabel1.setOpaque(true);

        jButton1.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jButton2.setText("Save&Print");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jButton3.setText("New Sale");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jButton5.setText("Print Invoice");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jButton6.setText("Print Delivery");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTabbedPane1)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(5, 5, 5))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton5)
                    .addComponent(jButton6))
                .addContainerGap())
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chckbx_invoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chckbx_invoiceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chckbx_invoiceActionPerformed

    private void txt_grossKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_grossKeyTyped
        // TODO add your handling code here:
        sup.field_validation(txt_gross, evt);
    }//GEN-LAST:event_txt_grossKeyTyped

    private void txt_discountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_discountKeyTyped
        // TODO add your handling code here:
        sup.field_validation(txt_discount, evt);
    }//GEN-LAST:event_txt_discountKeyTyped

    private void txt_vatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_vatKeyTyped
        // TODO add your handling code here:
        sup.field_validation(txt_vat, evt);
    }//GEN-LAST:event_txt_vatKeyTyped

    private void txt_shippingKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_shippingKeyTyped
        // TODO add your handling code here:
        sup.field_validation(txt_shipping, evt);
    }//GEN-LAST:event_txt_shippingKeyTyped

    private void txt_chargesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_chargesKeyTyped
        // TODO add your handling code here:
        sup.field_validation(txt_charges, evt);
    }//GEN-LAST:event_txt_chargesKeyTyped

    private void txt_totalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_totalKeyTyped
        // TODO add your handling code here:
        sup.field_validation(txt_total, evt);
    }//GEN-LAST:event_txt_totalKeyTyped

    private void cmb_customerItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_customerItemStateChanged
        // TODO add your handling code here:
        int idx=cmb_customer.getSelectedIndex();
        int event=evt.getStateChange();
        if(idx!=-1 && event==ItemEvent.SELECTED){
            String custid=sup.getValue(customerID[idx]);
            String add=sup.getValue(address[idx]);
            customerid=custid;
            txtA_bill_to.setText(add);
            txtA_shipto.setText(add);
        }
    }//GEN-LAST:event_cmb_customerItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    boolean status=false;
    String saleNo=txt_saleno.getText();
    String customerId=customerid;
    String customer=sup.getValue(cmb_customer.getSelectedItem());
    String address=txtA_bill_to.getText();
    String contactName=txt_contact.getText();
    String shipto=txtA_shipto.getText();
    String customerAcc=txt_customer_acc.getText();
    String orderno=txt_po.getText();
    String orderDue=new SimpleDateFormat("dd-MM-yyyy").format(jdate_po_duedate.getDate());
    String processNo=txt_process_no.getText();
    txt_contact_name.getText();
    String attention=txt_attention.getText();
    String date=new SimpleDateFormat("dd-MM-yyyy").format(jdate_created_on.getDate());
    String shipBy=sup.getValue(cmb_shipby.getSelectedItem());
    String shipType=sup.getValue(cmb_shipping_type.getSelectedItem());
    String shipDate=new SimpleDateFormat("dd-MM-yyyy").format(jdate_ship_date.getDate());
    String carrier=txt_carrier_name.getText();
    String carrierNo=txt_carrier_no.getText();
    
    String invoiceNo=txt_invoice.getText();
    String deliveryNo=txtdeliveryNo.getText();
    
    double gross_total=Double.parseDouble(sup.getCostValue(txt_gross.getText()));
    double  discount=Double.parseDouble(sup.getCostValue(txt_discount.getText()));
    double vat=Double.parseDouble(sup.getCostValue(txt_vat.getText()));
    double shippingcost=Double.parseDouble(sup.getCostValue(txt_shipping.getText()));
    double other_charges=Double.parseDouble(sup.getCostValue(txt_charges.getText()));
    double amount=Double.parseDouble(sup.getCostValue(txt_total.getText()));
    String serviceDate=new SimpleDateFormat("dd-MM-yyyy").format(jDate_invoice_date.getDate());
    String dueDate=new SimpleDateFormat("dd-MM-yyyy").format(jDate_due_date.getDate());   
    
    if(!saleNo.equals("") && !customerId.equals("") && item_model!=null){
       if(this.save_sale(saleNo, customerId, customerAcc, orderno, orderDue, processNo, shipBy, shipType, shipDate, carrier, carrierNo, amount, date)){
          for(int i=0; i<item_model.getRowCount(); i++){
             String item_name=sup.getValue(item_model.getValueAt(i, 0));
             String item_no=sup.getValue(item_model.getValueAt(i, 8));
             int units=Integer.parseInt(sup.getIntValue(item_model.getValueAt(i, 1)));
             if(stock_trx(item_name,item_no ,0, units, date)){
                 if(product_trx(customerId, deliveryNo, "n/a", item_name, units, "HQ", date)){
                     status=true;
                 }
             }
          } 
          if(status){
              delivery_note(customerId,shipDate, "00-00-0000", deliveryNo, invoiceNo, "pending", shipBy,carrierNo, false, "");
//              sup.log_msg("HQ", customerId, "Please Check Your Shipping Log Delivery in Pipeline", false, sup.sqlDate(date));
          }
       }
    }
    else{
       errorFields = new JLabel("<HTML><FONT COLOR = red>Please make sure required fields are filled & Item List isnt empty</FONT></HTML>"); 
       JOptionPane.showMessageDialog(null,errorFields,"csrim",JOptionPane.INFORMATION_MESSAGE); 
    }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt_discountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_discountFocusLost
        // TODO add your handling code here:
        calc();
    }//GEN-LAST:event_txt_discountFocusLost

    private void txt_vatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_vatActionPerformed
        // TODO add your handling code here:
        calc();
    }//GEN-LAST:event_txt_vatActionPerformed

    private void txt_shippingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_shippingActionPerformed
        // TODO add your handling code here:
        calc();
    }//GEN-LAST:event_txt_shippingActionPerformed

    private void txt_chargesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_chargesActionPerformed
        // TODO add your handling code here:
        calc();
    }//GEN-LAST:event_txt_chargesActionPerformed

    private void txt_discountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_discountActionPerformed
        // TODO add your handling code here:
        calc();
    }//GEN-LAST:event_txt_discountActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        int row=jTable1.getSelectedRow();
        if(row!=-1){
            item_model.removeRow(row);
            this.calc();
        }else{
            errorFields = new JLabel("<HTML><FONT COLOR = BLUE>Please select a row to remove</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields,"csrim",JOptionPane.ERROR_MESSAGE); 
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.resetfields();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        
        String customer=sup.getValue(cmb_customer.getSelectedItem());
        String customerId=customerid;
        String address=txtA_bill_to.getText();

        double gross_total=Double.parseDouble(sup.getCostValue(txt_gross.getText()));
        double  discount=Double.parseDouble(sup.getCostValue(txt_discount.getText()));
        double vat=Double.parseDouble(sup.getCostValue(txt_vat.getText()));
        double shippingcost=Double.parseDouble(sup.getCostValue(txt_shipping.getText()));
        double other_charges=Double.parseDouble(sup.getCostValue(txt_charges.getText()));
        double total=Double.parseDouble(sup.getCostValue(txt_total.getText()));
        Date serviceDate=jDate_invoice_date.getDate();
        String dueDate=new SimpleDateFormat("dd-MM-yyyy").format(jDate_due_date.getDate());   
        
        invoice invoic=new invoice();
        invoic.sale_formload(this.getInvModel(), customer,customerId, address,new Date(),serviceDate,gross_total,discount,shippingcost+other_charges,vat,total);
        JOptionPane.showInternalMessageDialog(this.getContentPane().getParent(),invoic.getContentPane(), "Invoice Form",JOptionPane.PLAIN_MESSAGE);
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        String customer=sup.getValue(cmb_customer.getSelectedItem());
        String customerId=customerid;
        String address=txtA_bill_to.getText();
        Date serviceDate=jDate_invoice_date.getDate();
        String invoiceNo=txt_invoice.getText();
        
        Delivery deli=new Delivery();
        deli.sale_formload(this.getDelModel(),invoiceNo, customer, customerId, address,new Date(),serviceDate);
        JOptionPane.showInternalConfirmDialog(this.getContentPane().getParent(),deli.getContentPane(), "Invoice Form",-1,JOptionPane.PLAIN_MESSAGE);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
    boolean status=false;
    String saleNo=txt_saleno.getText();
    String customerId=customerid;
    String customer=sup.getValue(cmb_customer.getSelectedItem());
    String address=txtA_bill_to.getText();
    String contactName=txt_contact.getText();
    String shipto=txtA_shipto.getText();
    String customerAcc=txt_customer_acc.getText();
    String orderno=txt_po.getText();
    String orderDue=new SimpleDateFormat("dd-MM-yyyy").format(jdate_po_duedate.getDate());
    String processNo=txt_process_no.getText();
    txt_contact_name.getText();
    String attention=txt_attention.getText();
    String date=new SimpleDateFormat("dd-MM-yyyy").format(jdate_created_on.getDate());
    String shipBy=sup.getValue(cmb_shipby.getSelectedItem());
    String shipType=sup.getValue(cmb_shipping_type.getSelectedItem());
    String shipDate=new SimpleDateFormat("dd-MM-yyyy").format(jdate_ship_date.getDate());
    String carrier=txt_carrier_name.getText();
    String carrierNo=txt_carrier_no.getText();
    
    String invoiceNo=txt_invoice.getText();
    String deliveryNo=txtdeliveryNo.getText();
    
    double gross_total=Double.parseDouble(sup.getCostValue(txt_gross.getText()));
    double  discount=Double.parseDouble(sup.getCostValue(txt_discount.getText()));
    double vat=Double.parseDouble(sup.getCostValue(txt_vat.getText()));
    double shippingcost=Double.parseDouble(sup.getCostValue(txt_shipping.getText()));
    double other_charges=Double.parseDouble(sup.getCostValue(txt_charges.getText()));
    double amount=Double.parseDouble(sup.getCostValue(txt_total.getText()));
    String serviceDate=new SimpleDateFormat("dd-MM-yyyy").format(jDate_invoice_date.getDate());
    String dueDate=new SimpleDateFormat("dd-MM-yyyy").format(jDate_due_date.getDate());   
    
    if(!saleNo.equals("") && !customerId.equals("") && item_model!=null){
       if(this.save_sale(saleNo, customerId, customerAcc, orderno, orderDue, processNo, shipBy, shipType, shipDate, carrier, carrierNo, amount, date)){
          for(int i=0; i<item_model.getRowCount(); i++){
             String item_name=sup.getValue(item_model.getValueAt(i, 0));
             String item_no=sup.getValue(item_model.getValueAt(i, 8));
             int units=Integer.parseInt(sup.getIntValue(item_model.getValueAt(i, 1)));
             if(stock_trx(item_name,item_no ,0, units, date)){
                 if(product_trx(customerId, deliveryNo, "n/a", item_name, units, "HQ", date)){
                     status=true;
                     
                 }
             }
          } 
          if(status){
              delivery_note(customerId,shipDate, "00-00-0000", deliveryNo, invoiceNo, "pending", shipBy,carrierNo, false, "");
               jasperParameter=new HashMap();
               jasperParameter.put("invoiceNo", invoiceNo);
               jasperParameter.put("CustomerName", customer);
               jasperParameter.put("customerId", customerid);
               jasperParameter.put("cust_account", customerAcc);
               jasperParameter.put("orderNo", orderno);
               jasperParameter.put("deliveryNo", deliveryNo);
               jasperParameter.put("serviceDate", serviceDate);
               jasperParameter.put("dueDate", dueDate);
               jasperParameter.put("shipto", shipto);
               jasperParameter.put("carrier", carrier);
               jasperParameter.put("carrier_no", carrierNo);
               jasperParameter.put("discount", discount);
               jasperParameter.put("process_no", processNo);
               jasperParameter.put("shippingCost", shippingcost);
               jasperParameter.put("vat", vat);
               jasperParameter.put("other_charges", other_charges);
               jasperParameter.put("total", amount);
               jasperParameter.put("saleOrder", saleNo);

               rpt=new clsReport();
               rpt.table_Reporter(jasperParameter, "saleOrder_template.jasper", new JRTableModelDataSource(item_model));
                  }
               }
            }
            else{
               errorFields = new JLabel("<HTML><FONT COLOR = red>Please make sure required fields are filled & Item List isnt empty</FONT></HTML>"); 
               JOptionPane.showMessageDialog(null,errorFields,"csrim",JOptionPane.INFORMATION_MESSAGE); 
            }      
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txt_vatFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_vatFocusLost
        // TODO add your handling code here:
        calc();
    }//GEN-LAST:event_txt_vatFocusLost

    private void txt_shippingFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_shippingFocusLost
        // TODO add your handling code here:
        calc();
    }//GEN-LAST:event_txt_shippingFocusLost

    private void txt_chargesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_chargesFocusLost
        // TODO add your handling code here:
        calc();
    }//GEN-LAST:event_txt_chargesFocusLost

    private DefaultComboBoxModel customers () {                 
       int i=0;
       Query q;
       DefaultComboBoxModel tmodel =new DefaultComboBoxModel();
       org.hibernate.Session session = HibernateUtil.getSessionFactory().openSession();
      try {        
           session.beginTransaction();
           q = session.createQuery("from DtCustomer c");
           q.setCacheable(true);
           List auth=q.list();
           Iterator iterator=auth.iterator();
           address=new Object[auth.size()];
           customerID=new Object[auth.size()];
           while(iterator.hasNext()){ 
                   DtCustomer cust=(DtCustomer)iterator.next();
                   tmodel.addElement(cust.getCustomerName()); 
                   address[i]=cust.getAddress();
                   customerID[i]=cust.getCustomerId();
                   i++;         
           }
           session.getTransaction().commit();
         }catch (HibernateException he) {
                 errorFields = new JLabel("<HTML><FONT COLOR = Blue>'"+he.getMessage()+"'</FONT></HTML>");
                 JOptionPane.showMessageDialog(null,errorFields,"DMS",JOptionPane.ERROR_MESSAGE);

         }finally {
             session.close(); 
         }
          return tmodel;
    }
    
public void highlightLastRow()
{
 int lastrow = item_model.getRowCount();
    jTable1.setRowSelectionInterval(lastrow - 1, lastrow - 1);
    jTable1.setColumnSelectionInterval(0, 0);
 }
    
    private DefaultComboBoxModel items() {                 
       int i=0;
       Query q;
       DefaultComboBoxModel tmodel =new DefaultComboBoxModel();
       org.hibernate.Session session = HibernateUtil.getSessionFactory().openSession();
      try {        
           session.beginTransaction();
           q = session.createQuery("from DtInventoryItemlist c");
           q.setCacheable(true);
           List auth=q.list();
           Iterator iterator=auth.iterator();
           itemID=new Object[auth.size()+1];
           price=new Object[auth.size()+1];
           tmodel.addElement("");
           itemID[i]="0";
           price[i]=0.00;
           i++;  
           while(iterator.hasNext()){ 
               DtInventoryItemlist prod=(DtInventoryItemlist)iterator.next();
               tmodel.addElement(prod.getItemName()); 
               itemID[i]=prod.getProductCode();
               price[i]=prod.getUnitPrice();
               i++;         
           }
           session.getTransaction().commit();
         }catch (HibernateException he) {
                 errorFields = new JLabel("<HTML><FONT COLOR = Blue>'"+he.getMessage()+"'</FONT></HTML>");
                 JOptionPane.showMessageDialog(null,errorFields,"DMS",JOptionPane.ERROR_MESSAGE);

         }finally {
             session.close(); 
         }
          return tmodel;
    }
    private void calculate(int row){        
        int idx=combobox.getSelectedIndex();
        if(idx!=-1){            
            double  unit_price=Double.parseDouble(sup.getCostValue(item_model.getValueAt(row, 3)));  
            double  units=Integer.parseInt(sup.getIntValue(item_model.getValueAt(row, 1)));       
            double  amount=unit_price *units;    
            item_model.setValueAt(amount, row, 4);
            calc();            
        }
    }
    
    private void calc(){
        gross=0.00;
        for(int i=0; i<item_model.getRowCount(); i++){
            gross+= Double.parseDouble(sup.getCostValue(item_model.getValueAt(i, 4)));
        }
        double discount=(Double.parseDouble(sup.getCostValue(txt_discount.getText()))/100)*gross;
        double vat=Double.parseDouble(sup.getCostValue(txt_vat.getText()));
        double shipping=Double.parseDouble(sup.getCostValue(txt_shipping.getText()));
        double other_charges=Double.parseDouble(sup.getCostValue(txt_charges.getText()));
        double net_total=gross+vat+shipping+other_charges-discount;
        txt_total.setText(String.valueOf(net_total));
        txt_gross.setText(String.valueOf(gross));
    }
    private String getAvail (String productCode) {                 
       int i=0;
       DefaultComboBoxModel tmodel =new DefaultComboBoxModel();
       org.hibernate.Session session = HibernateUtil.getSessionFactory().openSession();
       int avail=0;
      try {        
           session.beginTransaction();
           Query q = session.createQuery("from VCurrentStock  stock where stock.productCode=:prod");
           q.setParameter("prod", productCode);
           q.setCacheable(true);
           List auth=q.list();
           Iterator iterator=auth.iterator();
           if(iterator.hasNext()){ 
                   VCurrentStock stock=(VCurrentStock)iterator.next();
                   tmodel.addElement(stock.getItemName()); 
                   avail=stock.getAvailable();        
           }
           session.getTransaction().commit();
         }catch (HibernateException he) {
                 errorFields = new JLabel("<HTML><FONT COLOR = Blue>'"+he.getStackTrace()+"'</FONT></HTML>");
                 JOptionPane.showMessageDialog(null,errorFields,"DMS",JOptionPane.ERROR_MESSAGE);

         }finally {
             session.close(); 
         }
          return String.valueOf(avail);
    }
 
    
private void resetfields(){
    String val=String.valueOf(new rowcount().row_count(DtDelivery.class)+1);
    String prodid="D/O"+sup.getcode(6, val);
    
    String invoice=String.valueOf(new rowcount().row_count(DtInvoice.class)+1);
    String inv="INV"+sup.getcode(6, invoice);
    
    String sale=String.valueOf(new rowcount().row_count(DtOrdersale.class)+1);
    String sal="S"+sup.getcode(6, sale);  
        
    txt_saleno.setText(sal);
    cmb_customer.setSelectedIndex(-1);
    txtA_bill_to.setText("");
    txt_contact.setText("");
    txtA_shipto.setText("");
    txt_customer_acc.setText("");
    txt_po.setText("");
    jdate_po_duedate.setDate(new Date());
    txt_process_no.setText("");
    txt_contact_name.setText("");
    txt_attention.setText("");
    jdate_created_on.setDate(new Date());
    cmb_shipby.setSelectedIndex(-1);
    cmb_shipping_type.setSelectedIndex(-1);
    jdate_ship_date.setDate(new Date());
    txt_carrier_name.setText("");
    txt_carrier_no.setText("");
    
    txt_invoice.setText(inv);
    txtdeliveryNo.setText(prodid);
    
    txt_gross.setText("0.00");
    txt_discount.setText("0.00");
    txt_vat.setText("0.00");
    txt_shipping.setText("0.00");
    txt_charges.setText("0.00");
    txt_total.setText("0.00");
    jDate_invoice_date.setDate(new Date());
    jDate_due_date.setDate(new Date());   
    
    item_model.clearAllRows();
        
}

public class InteractiveTableModelListener implements TableModelListener{
     @Override
     public void tableChanged(TableModelEvent evt) {
             int column = evt.getColumn();
             int row = evt.getFirstRow();
         if (evt.getType() == TableModelEvent.UPDATE) {             
             if(column==0||column==1|| column==3){
                 calculate(row);
                 
             }
         }
         if (evt.getType() == TableModelEvent.DELETE) {
             
         }         
         if (evt.getType() == TableModelEvent.INSERT) {
//             model_update(row );
             
         }
     }
 }

public boolean save_sale(String saleNo, String customerId, String customerAcc, String orderno, String orderDue, String processNo, String shipBy, String shipType, String shipDate, String carrier, String carrierNo, double amount, String date){
      Session session = HibernateUtil.getSessionFactory().openSession();      
      Transaction tx = null;
      int val=0;
      boolean state=false;
      try{ 
            tx=session.beginTransaction();
             ordersale=new DtOrdersale(saleNo, customerId, customerAcc, orderno, sup.sqlDate(orderDue), processNo, shipBy, shipType, sup.sqlDate(shipDate), carrier, carrierNo, amount, sup.sqlDate(date));
           
            Query q = session.getNamedQuery("DtOrdersale.findBySaleNo");
            q.setParameter("saleNo", saleNo);
            q.setCacheable(true);
            List<DtOrdersale> vt=q.list();           
            Iterator<DtOrdersale> itr=vt.iterator();         
         if(!itr.hasNext()){
            session.save(ordersale);
            errorFields = new JLabel("<HTML><FONT COLOR = BLUE>Sale saved successfully.</FONT></HTML>");}
         else{
            ordersale=(DtOrdersale)itr.next();
            ordersale.setAmount(amount);
            ordersale.setCarrier(carrier);
            ordersale.setCarrierNo(carrierNo);
            ordersale.setCustomerAcc(customerAcc);
            ordersale.setCustomerId(customerId);
            ordersale.setDate(sup.sqlDate(date));
            ordersale.setOrderDue(sup.sqlDate(orderDue));
            ordersale.setProcessNo(processNo);
            ordersale.setSaleNo(saleNo);
            ordersale.setShipBy(shipBy);
            ordersale.setShipDate(sup.sqlDate(shipType));
            ordersale.setShipType(shipType);
            session.update(ordersale);  
            errorFields = new JLabel("<HTML><FONT COLOR = BLUE>Sale updated successfully.</FONT></HTML>");
         }
            
        tx.commit();
        JOptionPane.showMessageDialog(null,errorFields,"csrim",JOptionPane.INFORMATION_MESSAGE);
        state=true;
            
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         errorFields = new JLabel("<HTML><FONT COLOR = red>'"+e.getMessage()+"'</FONT></HTML>"); 
         JOptionPane.showMessageDialog(null,errorFields,"csrim",JOptionPane.INFORMATION_MESSAGE);
      }finally {
         session.flush();
         session.close(); 
      }
      return state;
   }

public boolean stock_trx(String productname, String batchno, int instock, int out_stock, String date){
      Session session = HibernateUtil.getSessionFactory().openSession();      
      Transaction tx = null;
      boolean state=false;
      try{
          
            DtStoreStock stock=new DtStoreStock();
            stock.setBatchNo(batchno);
            stock.setProductName(productname);
            stock.setInStock(instock);
            stock.setOutStock(out_stock);
            stock.setTransDate(date);
            
            tx = session.beginTransaction();
            session.save(stock);
            tx.commit();
            state=true;
            
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
           e.printStackTrace(); 
      }finally {
         session.flush();
         session.close(); 
      }
      return state;
   }

public boolean product_trx(String farmNo, String dno, String batchno, String productName, int units, String attendant, String date){
      Session session = HibernateUtil.getSessionFactory().openSession();      
      Transaction tx = null;
      boolean state=false;
      try{
            tx = session.beginTransaction();
            DtSaletrx sale=new DtSaletrx(farmNo, dno, batchno, productName, units, attendant, sup.sqlDate(date));            
            
            Query q = session.createQuery("FROM DtSaletrx sale WHERE sale.farmNo=:farm_no and sale.productName=:prod and sale.date=:date");
            q.setParameter("farm_no", farmNo);
            q.setParameter("prod", productName);
            q.setParameter("date", sup.sqlDate(date));
            q.setCacheable(true);
            List results=q.list();
            Iterator iterator=results.iterator();         
         if(!iterator.hasNext())
            session.save(sale);
         else{
             sale=(DtSaletrx)results.get(0);
             sale.setDno(dno);
             sale.setBatchno(batchno);
             sale.setUnits(units);
             sale.setAttendant(attendant);
             session.update(sale);              
         }
         
            tx.commit();
            state=true;
            
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
           e.printStackTrace(); 
      }finally {
         session.flush();
         session.close(); 
      }
      return state;
   }

public boolean delivery_note(String farmNo,String departureDate, String arrivalDate, String dno, String batchno, String status, String method,String vessel, boolean received, String comments){
      Session session = HibernateUtil.getSessionFactory().openSession();      
      Transaction tx = null;
      boolean state=false;
      try{
            tx = session.beginTransaction();
            DtDeliveryStatus sale=new DtDeliveryStatus(farmNo,sup.sqlDate(departureDate), sup.sqlDate(arrivalDate),dno, batchno, status, method,vessel, received, comments);
            
            
            Query q = session.createQuery("FROM DtDeliveryStatus status WHERE status.dno=:dno");
            q.setParameter("dno", dno);
            q.setCacheable(true);
            List results=q.list();
            Iterator iterator=results.iterator();         
         if(!iterator.hasNext())
             session.save(sale);
         else{
             sale=(DtDeliveryStatus)results.get(0);
             sale.setFarmNo(farmNo);
             sale.setDepartureDate(sup.sqlDate(departureDate));
             sale.setArrivalDate(sup.sqlDate(arrivalDate));
             sale.setBatchno(batchno);
             session.update(sale);              
         }
         
            tx.commit();
            state=true;
            
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
           e.printStackTrace(); 
      }finally {
         session.flush();
         session.close(); 
      }
      return state;
   }

public InvoiceTableModel getInvModel(){
        String header[]={"Qty","Item No","Name","Description","VAT","U.Price","Discount%","Price"};
        InvoiceTableModel model=new InvoiceTableModel(header);
        for(int i=0; i<item_model.getRowCount();i++){
          int qty = Integer.parseInt(sup.getIntValue(item_model.getValueAt(i, 1))); 
          String item_no=sup.getValue(item_model.getValueAt(i, 8));
          String item_name=sup.getValue(item_model.getValueAt(i, 0));
          double unit_price=Double.parseDouble(sup.getCostValue(item_model.getValueAt(i, 3)));
          
          model.addEmptyRow(new InvoiceRecord(qty,item_no,item_name,"", 0.00,unit_price,0,0.00));          
       }
        return model;
    }

public DeliveryTableModel getDelModel(){
        String header[]={"Qty","Item No","Name","Description"};
        DeliveryTableModel model=new DeliveryTableModel(header);
        for(int i=0; i<item_model.getRowCount();i++){
          int qty = Integer.parseInt(sup.getIntValue(item_model.getValueAt(i, 1))); 
          String item_no=sup.getValue(item_model.getValueAt(i, 8));
          String item_name=sup.getValue(item_model.getValueAt(i, 0));
          model.addEmptyRow(new deliveryRecord(qty,item_name,item_no,""));
       }
        return model;
    }

public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbu".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Application.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                salesOrder app=new salesOrder();
                app.formload();
                app.setDefaultCloseOperation(EXIT_ON_CLOSE);
                app.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox chckbx_delivery;
    private javax.swing.JCheckBox chckbx_invoice;
    private javax.swing.JComboBox cmb_customer;
    private javax.swing.JComboBox cmb_shipby;
    private javax.swing.JComboBox cmb_shipping_type;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private com.toedter.calendar.JDateChooser jDate_due_date;
    private com.toedter.calendar.JDateChooser jDate_invoice_date;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private com.toedter.calendar.JDateChooser jdate_created_on;
    private com.toedter.calendar.JDateChooser jdate_po_duedate;
    private com.toedter.calendar.JDateChooser jdate_ship_date;
    private javax.swing.JTextArea txtA_bill_to;
    private javax.swing.JTextArea txtA_shipto;
    private javax.swing.JTextField txt_attention;
    private javax.swing.JTextField txt_carrier_name;
    private javax.swing.JTextField txt_carrier_no;
    private javax.swing.JTextField txt_charges;
    private javax.swing.JTextField txt_contact;
    private javax.swing.JTextField txt_contact_name;
    private javax.swing.JTextField txt_customer_acc;
    private javax.swing.JTextField txt_discount;
    private javax.swing.JTextField txt_gross;
    private javax.swing.JTextField txt_invoice;
    private javax.swing.JTextField txt_po;
    private javax.swing.JTextField txt_process_no;
    private javax.swing.JTextField txt_saleno;
    private javax.swing.JTextField txt_shipping;
    private javax.swing.JTextField txt_total;
    private javax.swing.JTextField txt_vat;
    private javax.swing.JTextField txtdeliveryNo;
    // End of variables declaration//GEN-END:variables
}
