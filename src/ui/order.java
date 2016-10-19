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
import classes.rowcount;
import classes.superCls;
import custom_jPanels.SimpleGradientPanel;
import farm.entity.DtOrders;
import farm.entity.DtShippings;
import farm.entity.DtVat;
import farmTech.util.HibernateUtil;
import java.awt.event.ItemEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import models.InvoiceRecord;
import models.deliveryRecord;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 *
 * @author Kent
 */
public class order extends javax.swing.JInternalFrame {

    private Object[] supplierID,address,VAT,SHIPMENT;
    private Object[][] data;
    private superCls sup=new superCls();
    private JLabel errorFields;
    private JTextField item;
    private DtOrders order;
    private HashMap jasperParameter;
    private clsReport rpt;
//    Delivery delivery=new Delivery();
    
    
    InvoiceTableModel item_model;
    private double discount=0.0,vat=0.0,unit_price=0.00,price=0.00,gross_total=0.00,shipping=0.00;
    private int  qty=0;
    private final TextFieldEditor inte=new TextFieldEditor("integer");
    private final TextFieldEditor str=new TextFieldEditor("string");
       
    /**
     * Creates new form order
     */
    public order() {
        initComponents();
    }

    public void formload(){
        SwingWorker doComputeRate=new SwingWorker() {
                  @Override
	          protected Integer doInBackground() throws Exception{
                    cmb_supplier.setModel(suppliers());
                    cmb_tax.setModel(vats());
                    cmb_ship.setModel(shippings());
                  return 0;
	        }
                @Override
                protected void done(){
                     SwingUtilities.invokeLater(new Runnable() {
                       public void run() {  
                           cmb_supplier.setSelectedIndex(-1);
                           cmb_ship.setSelectedIndex(-1);
                           cmb_tax.setSelectedIndex(-1);        
//                        jTable5.getColumnModel().getColumn(7).setCellRenderer( new  labelRenderer());
//                        ColumnResizer.adjustColumnPreferredWidths (jTable5);
//                        jTable5.revalidate();
                    }});
                }
        };
        doComputeRate.execute();
        resetfields();
        jTable1.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        
//        ImageJScrollPane isp = new ImageJScrollPane(table);
//  
//       isp.setBackgroundImage(new ImageIcon("c:\mong.jpg"));
    }
    
    public void delivery_formload(final InvoiceTableModel model, String customer,String custid,String address,Date orderDate,Date serviceDate){
        resetfields();
        SwingWorker doComputeRate=new SwingWorker() {
                  @Override
	          protected Integer doInBackground() throws Exception{
                    cmb_supplier.setModel(suppliers());
                    jTable1.setModel(model);
                  return 0;
	        }
                @Override
                protected void done(){
                     SwingUtilities.invokeLater(new Runnable() {
                       public void run() {  
                           cmb_supplier.setSelectedIndex(-1);     
//                        jTable5.getColumnModel().getColumn(7).setCellRenderer( new  labelRenderer());
//                        ColumnResizer.adjustColumnPreferredWidths (jTable5);
//                        jTable5.revalidate();
                    }});
                }
        };
        doComputeRate.execute();
//        resetfields();
        jTable1.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        cmb_supplier.setSelectedItem(customer);
        txt_supplierid.setText(custid);
        txtA_address.setText(address);   
        if(model!=null){
            jTable1.getColumnModel().getColumn(0).setCellEditor(inte);
            jTable1.getColumnModel().getColumn(1).setCellEditor(str);
            jTable1.getColumnModel().getColumn(2).setCellEditor(str);
            jTable1.getColumnModel().getColumn(3).setCellEditor(str);
            jTable1.getColumnModel().getColumn(4).setCellEditor(inte);
            jTable1.getColumnModel().getColumn(5).setCellEditor(inte);
            jTable1.getColumnModel().getColumn(6).setCellEditor(inte);
            jTable1.getColumnModel().getColumn(7).setCellEditor(inte);
        }
    }
    
    
    private DefaultComboBoxModel suppliers () {                 
       int i=0;
       Query q;
       DefaultComboBoxModel tmodel =new DefaultComboBoxModel();
       org.hibernate.Session session = HibernateUtil.getSessionFactory().openSession();
      try {        
           session.beginTransaction();
           q = session.createQuery("SELECT c.suppliername,c.address,c.supplierId from DtSuppliers c");
           q.setCacheable(true);
           List auth=q.list();
           Iterator iterator=auth.iterator();
           address=new Object[auth.size()];
           supplierID=new Object[auth.size()];
           for(Object o: auth){ 
                   Object[] cust=(Object[])iterator.next();
                   tmodel.addElement(cust[0]); 
                   address[i]=cust[1];
                   supplierID[i]=cust[2];
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
    
    private DefaultComboBoxModel vats () {                 
       int i=0;
       Query q;
       DefaultComboBoxModel tmodel =new DefaultComboBoxModel();
       org.hibernate.Session session = HibernateUtil.getSessionFactory().openSession();
      try {        
           session.beginTransaction();
           q = session.createQuery("from DtVat c");
           q.setCacheable(true);
           List auth=q.list();
           Iterator iterator=auth.iterator();
           VAT=new Object[auth.size()];
           while(iterator.hasNext()){ 
                   DtVat cust=(DtVat)iterator.next();
                   tmodel.addElement(cust.getName()); 
                   VAT[i]=cust.getValue();
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
                 JOptionPane.showMessageDialog(null,errorFields,"DMS",JOptionPane.ERROR_MESSAGE);

         }finally {
             session.close(); 
         }
          return tmodel;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new SimpleGradientPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_orderno = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        cmb_supplier = new javax.swing.JComboBox();
        txt_supplierid = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtA_address = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        cmb_tax = new javax.swing.JComboBox();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txt_remarks = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cmb_ship = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txt_discount = new javax.swing.JTextField();
        txt_shipping = new javax.swing.JTextField();
        lbl_vat = new javax.swing.JLabel();
        lbl_total = new javax.swing.JLabel();
        lbl_Gtotal = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 19), new java.awt.Dimension(0, 19), new java.awt.Dimension(32767, 19));
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(24, 0), new java.awt.Dimension(24, 0), new java.awt.Dimension(24, 32767));
        jLabel4 = new javax.swing.JLabel();
        cmb_status = new javax.swing.JComboBox();

        jPanel1.setMinimumSize(new java.awt.Dimension(118, 46));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel1.setText("Order No.");

        txt_orderno.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        txt_orderno.setToolTipText("");

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel2.setText("Date :");

        jDateChooser1.setDate(new Date());
        jDateChooser1.setDateFormatString("dd-MM-yyyy");
        jDateChooser1.setOpaque(false);

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel3.setText("Supplier :");

        cmb_supplier.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        cmb_supplier.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_supplierItemStateChanged(evt);
            }
        });

        txt_supplierid.setEditable(false);
        txt_supplierid.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        txt_supplierid.setToolTipText("");

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel5.setText("Address :");

        txtA_address.setColumns(20);
        txtA_address.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        txtA_address.setRows(5);
        txtA_address.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txtA_address);

        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel8.setText("VAT :");

        cmb_tax.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        cmb_tax.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tax free" }));
        cmb_tax.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_taxItemStateChanged(evt);
            }
        });
        cmb_tax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_taxActionPerformed(evt);
            }
        });

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Qty", "Item No", "Name", "Description", "vat", "Discount (%)", "U.Price", "Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setCellEditor(new DefaultCellEditor(item=new JTextField(0)));
        jTable1.setGridColor(new java.awt.Color(204, 204, 204));
        jTable1.setRowHeight(20);
        jTable1.setRowMargin(2);
        jTable1.setSelectionBackground(new java.awt.Color(181, 225, 248));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTable1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTable1FocusLost(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jLabel9.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel9.setText("Remarks :");

        txt_remarks.setColumns(20);
        txt_remarks.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        txt_remarks.setRows(5);
        jScrollPane3.setViewportView(txt_remarks);

        jLabel10.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Total Gross:");

        jLabel11.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Discount:");
        jLabel11.setToolTipText("Enter a discount in % for all Items");

        jLabel12.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel12.setText("Shipping");

        cmb_ship.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        cmb_ship.setToolTipText("Its's possible to enter a different shipping value than those of the selected shipping entry");
        cmb_ship.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_shipItemStateChanged(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("VAT:");

        jLabel14.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Total:");

        txt_discount.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        txt_discount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_discount.setText("0.00");
        txt_discount.setToolTipText("");
        txt_discount.setBorder(null);
        txt_discount.setMinimumSize(new java.awt.Dimension(0, 20));
        txt_discount.setName(""); // NOI18N
        txt_discount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_discountActionPerformed(evt);
            }
        });
        txt_discount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_discountFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_discountFocusLost(evt);
            }
        });

        txt_shipping.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        txt_shipping.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_shipping.setText("0.00");
        txt_shipping.setToolTipText("");
        txt_shipping.setBorder(null);
        txt_shipping.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_shippingActionPerformed(evt);
            }
        });
        txt_shipping.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_shippingFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_shippingFocusLost(evt);
            }
        });

        lbl_vat.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        lbl_vat.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_vat.setText("0.00");

        lbl_total.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        lbl_total.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_total.setText("0.00");

        lbl_Gtotal.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        lbl_Gtotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_Gtotal.setText("0.00");
        lbl_Gtotal.setMaximumSize(new java.awt.Dimension(17, 20));
        lbl_Gtotal.setMinimumSize(new java.awt.Dimension(17, 20));

        jLabel18.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel18.setText("Items :");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/product_list_20.png"))); // NOI18N
        jButton1.setToolTipText("Pick an item from the list of products");
        jButton1.setOpaque(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/plus_16.png"))); // NOI18N
        jButton2.setToolTipText("Add a new item with a default name and quantity '1'");
        jButton2.setOpaque(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete_16.png"))); // NOI18N
        jButton3.setOpaque(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sales_16.png"))); // NOI18N
        jButton4.setToolTipText("Pick Items from Sales");
        jButton4.setEnabled(false);
        jButton4.setOpaque(false);

        jButton5.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jButton5.setText("New");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jButton6.setText("Print");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/order_32.png"))); // NOI18N

        jButton9.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jButton9.setText("Save");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel22.setForeground(new java.awt.Color(251, 49, 105));
        jLabel22.setText("*");

        jLabel4.setText("Status :");

        cmb_status.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        cmb_status.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pending", "Fullfilled", "Cancelled" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmb_ship, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel4))
                                .addGap(7, 7, 7)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmb_status, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmb_tax, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(126, 126, 126))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(jButton5)
                                .addGap(3, 3, 3)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(477, 477, 477)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel10)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_discount, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_shipping, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_Gtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lbl_vat, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                        .addComponent(lbl_total, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(16, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(filler2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(76, 76, 76)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 746, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txt_orderno, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cmb_supplier, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_supplierid))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel22)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel19)
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(filler2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(cmb_tax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_orderno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmb_supplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_supplierid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cmb_status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)))
                .addGap(5, 5, 5)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(7, 7, 7)
                        .addComponent(jButton1)
                        .addGap(3, 3, 3)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel9))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(9, 9, 9)
                        .addComponent(jLabel11)
                        .addGap(4, 4, 4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmb_ship, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbl_Gtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(txt_discount, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(txt_shipping, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel13)
                            .addGap(4, 4, 4)
                            .addComponent(jLabel14))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(lbl_vat)
                            .addGap(4, 4, 4)
                            .addComponent(lbl_total)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton9)
                        .addComponent(jButton5)
                        .addComponent(jButton6)))
                .addGap(8, 8, 8))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel22)
                                    .addComponent(jLabel1))))
                        .addGap(10, 10, 10)
                        .addComponent(jLabel3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmb_supplierItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_supplierItemStateChanged
        // TODO add your handling code here:
        int idx=cmb_supplier.getSelectedIndex();
        int event=evt.getStateChange();
        if(idx!=-1 && event==ItemEvent.SELECTED){
            String custid=sup.getValue(supplierID[idx]);
            String add=sup.getValue(address[idx]);
            txt_supplierid.setText(custid);
            txtA_address.setText(add);
        }
    }//GEN-LAST:event_cmb_supplierItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if(!item_model.hasEmptyRow()){
            item_model.addEmptyRow( new InvoiceRecord(1,"Item","","", 0.00,0.00,0,0.00));
            jTable1.getColumnModel().getColumn(0).setCellEditor(inte);
            jTable1.getColumnModel().getColumn(1).setCellEditor(str);
            jTable1.getColumnModel().getColumn(2).setCellEditor(str);
            jTable1.getColumnModel().getColumn(3).setCellEditor(str);
            jTable1.getColumnModel().getColumn(4).setCellEditor(inte);
            jTable1.getColumnModel().getColumn(5).setCellEditor(inte);
            jTable1.getColumnModel().getColumn(6).setCellEditor(inte);
            jTable1.getColumnModel().getColumn(7).setCellEditor(inte);
//            jTable1.getColumnModel().getColumn(3).setCellRenderer(new TextAreaRenderer());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTable1FocusLost
        // TODO add your handling code here:
//        String comp=evt.getOppositeComponent().getName();
//        int col=jTable1.getSelectedColumn();
//        TableCellEditor editor=jTable1.getColumnModel().getColumn(col).getCellEditor();
//        if(editor!=null && evt.getStateChange()==FocusEvent.FOCUS_LOST && !comp.equals("editor")){
//            editor.stopCellEditing();
//        }
        
    }//GEN-LAST:event_jTable1FocusLost

    private void jTable1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTable1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1FocusGained

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        resetfields();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        String orderNo=txt_orderno.getText();
        String supplierid=txt_supplierid.getText();
        String date=new SimpleDateFormat("dd-MM-yyyy").format(jDateChooser1.getDate());
        String stateDate=new SimpleDateFormat("dd-MM-yyyy").format(jDateChooser1.getDate());
        String supplierName=sup.getValue(cmb_supplier.getSelectedItem());
        String address=txtA_address.getText();
        String state=sup.getValue(cmb_status.getSelectedItem());
        sup.getValue(cmb_tax.getSelectedItem());
        String remarks=txt_remarks.getText();
        Double.parseDouble(sup.getCostValue(lbl_Gtotal.getText()));
        double discount=Double.parseDouble(sup.getCostValue(txt_discount.getText()));
        double shippingCost=Double.parseDouble(sup.getCostValue(txt_shipping.getText()));
        double vat=Double.parseDouble(sup.getCostValue(lbl_vat.getText()));
        double amount=Double.parseDouble(sup.getCostValue(lbl_total.getText()));
        
        if(!orderNo.equals("") && !supplierid.equals("")){
            this.save_order(supplierid, orderNo, state,  amount,  date,  stateDate);
               jasperParameter=new HashMap();
               jasperParameter.put("orderNo", orderNo);
               jasperParameter.put("supplierName", supplierName);
               jasperParameter.put("supplierId", supplierid);
               jasperParameter.put("address", address);
               jasperParameter.put("remarks", remarks);
               jasperParameter.put("discount", discount);
               jasperParameter.put("shippingCost", shippingCost);
               jasperParameter.put("VAT", vat);
               jasperParameter.put("Total", amount);
               
               rpt=new clsReport();
               rpt.table_Reporter(jasperParameter, "order_template.jasper", new JRTableModelDataSource(item_model));
            
        }else{
            errorFields = new JLabel("<HTML><FONT COLOR = red>Please make sure required fields are filled</FONT></HTML>"); 
            JOptionPane.showMessageDialog(null,errorFields,"farmTech",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void txt_discountFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_discountFocusGained
        // TODO add your handling code here:
       txt_discount.selectAll();
    }//GEN-LAST:event_txt_discountFocusGained

    private void txt_discountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_discountActionPerformed
        // TODO add your handling code here:
        this.calc();
    }//GEN-LAST:event_txt_discountActionPerformed

    private void txt_shippingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_shippingActionPerformed
        // TODO add your handling code here:
        this.calc();        
    }//GEN-LAST:event_txt_shippingActionPerformed

    private void txt_shippingFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_shippingFocusGained
        // TODO add your handling code here:
        txt_shipping.selectAll();
    }//GEN-LAST:event_txt_shippingFocusGained

    private void txt_shippingFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_shippingFocusLost
        // TODO add your handling code here:
        this.calc();
    }//GEN-LAST:event_txt_shippingFocusLost

    private void txt_discountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_discountFocusLost
        // TODO add your handling code here:
        this.calc();
    }//GEN-LAST:event_txt_discountFocusLost

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int row=jTable1.getSelectedRow();
        if(row!=-1){
            item_model.removeRow(row);
            this.calc();
        }else{
            errorFields = new JLabel("<HTML><FONT COLOR = BLUE>Please select item to remove</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields,"farmTech",JOptionPane.ERROR_MESSAGE); 
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        // TODO add your handling code here:
        if (jTable1.isEditing())
           jTable1.getCellEditor().stopCellEditing();
    }//GEN-LAST:event_jPanel1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:   
        
        productList supp=new productList();
        supp.formload();
        int option=JOptionPane.showOptionDialog(null,supp.getContentPane(), "Product List",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE,null,new Object[]{},null);
        String item_code=supp.getProduct_code();
            if(item_code!=null && !item_model.hasEmptyRow()){
                String item_name=supp.getProduct_name();
                int vat1=supp.getVat();
                double price1=supp.getPrice();              
                item_model.addEmptyRow(new InvoiceRecord(1,item_code,item_name,"", vat1,price1,0,0.00));
                
                jTable1.getColumnModel().getColumn(0).setCellEditor(inte);
                jTable1.getColumnModel().getColumn(1).setCellEditor(str);
                jTable1.getColumnModel().getColumn(2).setCellEditor(str);
                jTable1.getColumnModel().getColumn(3).setCellEditor(str);
                jTable1.getColumnModel().getColumn(4).setCellEditor(inte);
                jTable1.getColumnModel().getColumn(5).setCellEditor(inte);
                jTable1.getColumnModel().getColumn(6).setCellEditor(inte);
                jTable1.getColumnModel().getColumn(7).setCellEditor(inte);
            }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cmb_taxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_taxItemStateChanged
        // TODO add your handling code here:
        int idx=cmb_tax.getSelectedIndex();
        if(idx!=-1 && evt.getStateChange()==ItemEvent.SELECTED){
            Double value=Double.parseDouble(sup.getCostValue(VAT[idx]));
            lbl_vat.setText(String.valueOf(value));
            this.calc();
        }
    }//GEN-LAST:event_cmb_taxItemStateChanged

    private void cmb_shipItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_shipItemStateChanged
        // TODO add your handling code here:
        int idx=cmb_ship.getSelectedIndex();
        if(idx!=-1 && evt.getStateChange()==ItemEvent.SELECTED){
            Double value=Double.parseDouble(sup.getCostValue(SHIPMENT[idx]));
            txt_shipping.setText(String.valueOf(value));
            this.calc();
        }
    }//GEN-LAST:event_cmb_shipItemStateChanged

    private void cmb_taxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_taxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_taxActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        String orderNo=txt_orderno.getText();
        String supplierid=txt_supplierid.getText();
        String date=new SimpleDateFormat("dd-MM-yyyy").format(jDateChooser1.getDate());
        String stateDate=new SimpleDateFormat("dd-MM-yyyy").format(jDateChooser1.getDate());
        String supplierName=sup.getValue(cmb_supplier.getSelectedItem());
        String address=txtA_address.getText();
        String state=sup.getValue(cmb_status.getSelectedItem());
        sup.getValue(cmb_tax.getSelectedItem());
        String remarks=txt_remarks.getText();
        Double.parseDouble(sup.getCostValue(lbl_Gtotal.getText()));
        double discount=Double.parseDouble(sup.getCostValue(txt_discount.getText()));
        double shippingCost=Double.parseDouble(sup.getCostValue(txt_shipping.getText()));
        double vat=Double.parseDouble(sup.getCostValue(lbl_vat.getText()));
        double amount=Double.parseDouble(sup.getCostValue(lbl_total.getText()));
        
        if(!orderNo.equals("") && !supplierid.equals("")){
            this.save_order(supplierid, orderNo, state,  amount,  date,  stateDate);
        }else{
            errorFields = new JLabel("<HTML><FONT COLOR = red>Please make sure required fields are filled</FONT></HTML>"); 
            JOptionPane.showMessageDialog(null,errorFields,"farmTech",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    public boolean save_order(String supplierid, String orderNo, String state, double amount, String date, String stateDate){
      Session session = HibernateUtil.getSessionFactory().openSession();      
      Transaction tx = null;
      boolean status=false;
      try{        
           tx=session.beginTransaction();
            DtOrders orders=new DtOrders(supplierid, orderNo, state, amount, sup.sqlDate(date),sup.sqlDate(date));
           
            Query q = session.getNamedQuery("DtOrders.findByOrderNo");
            q.setParameter("orderNo", orderNo);
            q.setCacheable(true);
            List<DtOrders> order=q.list();           
            Iterator<DtOrders> itr=order.iterator();             
         if(!itr.hasNext()){
            session.save(orders);
            errorFields = new JLabel("<HTML><FONT COLOR = BLUE>Record Saved Successfully</FONT></HTML>");
         }
         else{
             orders=(DtOrders)itr.next(); 
             orders.setDate(sup.sqlDate(date));
             orders.setState(state);
             orders.setOrderNo(orderNo);
             orders.setAmount(amount);
             orders.setSupplierid(supplierid);
             orders.setStateDate(sup.sqlDate(stateDate));
             session.update(orders);  
             errorFields = new JLabel("<HTML><FONT COLOR = BLUE>Record Updated Successfully</FONT></HTML>");
         }
         tx.commit();
         status=true;         
         JOptionPane.showMessageDialog(null,errorFields,"farmTech",JOptionPane.INFORMATION_MESSAGE);             
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
      }finally {
         session.flush();
         session.close(); 
      }
      return status;
   }

    public void resetfields(){
        String val=String.valueOf(new rowcount().row_count(DtOrders.class)+1);
        String prodid="PO"+sup.getcode(6, val);
        txt_orderno.setText(prodid);
        cmb_supplier.setSelectedIndex(-1);
        txt_supplierid.setText("");
        txtA_address.setText("");
        cmb_tax.setSelectedIndex(-1);
        txt_remarks.setText("");
        lbl_Gtotal.setText("0.00");
        txt_discount.setText("0.0");
        txt_shipping.setText("0.0");
        lbl_vat.setText("0.00");
        lbl_total.setText("0.00");
        
        String header[]={"Qty","Item No","Name","Description","VAT","U.Price","Discount%","Price"};
        item_model=new InvoiceTableModel(header);
        item_model.addTableModelListener(new InteractiveTableModelListener());
        jTable1.setModel(item_model);
    }
    
   public void calc(){
       vat=0.0;
       discount=0.0;
       gross_total=0.0;
       for(int i=0; i<item_model.getRowCount();i++){
          qty = Integer.parseInt(sup.getIntValue(item_model.getValueAt(i, 0))); 
//          vat+= Double.parseDouble(sup.getCostValue(item_model.getValueAt(i, 4)));
          unit_price=Double.parseDouble(sup.getCostValue(item_model.getValueAt(i, 5))); 
//          discount+=Double.parseDouble(sup.getCostValue(item_model.getValueAt(i, 7)))*(Double.parseDouble(sup.getCostValue(item_model.getValueAt(i, 6)))/100); 
          gross_total+=Double.parseDouble(sup.getCostValue(item_model.getValueAt(i, 7))); 
       }
       shipping=Double.parseDouble(sup.getCostValue(txt_shipping.getText()));
       vat=Double.parseDouble(sup.getCostValue(lbl_vat.getText()));
       discount=Double.parseDouble(sup.getCostValue(txt_discount.getText()))/100*gross_total;
       price=gross_total+vat-discount+shipping;
       lbl_Gtotal.setText(String.valueOf(gross_total));
       lbl_total.setText(String.valueOf(price));
   } 
    public void model_update(int row ){
        
        int  col_qty = Integer.parseInt(sup.getIntValue(item_model.getValueAt(row, 0)));           
        double  col_vat= Double.parseDouble(sup.getCostValue(item_model.getValueAt(row, 4)));
        double  col_unit_price=Double.parseDouble(sup.getCostValue(item_model.getValueAt(row, 5))); 
        double  col_price=col_qty*col_unit_price;
        double  col_discount=col_price*(Double.parseDouble(sup.getCostValue(item_model.getValueAt(row, 6)))/100);        
        double  col_net_price=col_price-col_vat-col_discount;        
//        item_model.setValueAt(col_discount, row, 6);
        item_model.setValueAt(col_net_price, row, 7);
    }  
    
    public DeliveryTableModel getDelModel(){
        String header[]={"Qty","Item No","Name","Description"};
        DeliveryTableModel model=new DeliveryTableModel(header);
        for(int i=0; i<item_model.getRowCount();i++){
          qty = Integer.parseInt(sup.getIntValue(item_model.getValueAt(i, 0))); 
          String item_no=sup.getValue(item_model.getValueAt(i, 1));
          String item_name=sup.getValue(item_model.getValueAt(i, 2));
          String description=sup.getValue(item_model.getValueAt(i, 3));
          model.addEmptyRow(new deliveryRecord(qty,item_name,item_no,description));
       }
        return model;
    }
   
    public class InteractiveTableModelListener implements TableModelListener{
     @Override
     public void tableChanged(TableModelEvent evt) {
             int column = evt.getColumn();
             int row = evt.getFirstRow();
         if (evt.getType() == TableModelEvent.UPDATE) {             
             if(column==0|| column==4 ||column==5|| column==6){
                 model_update(row );
                 calc();
             }
         }
         if (evt.getType() == TableModelEvent.DELETE) {
             calc();
         }         
         if (evt.getType() == TableModelEvent.INSERT) {
             model_update(row );
             calc();
         }
     }
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
                
                order app=new order();
                app.formload();
                app.setDefaultCloseOperation(EXIT_ON_CLOSE);
                app.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmb_ship;
    private javax.swing.JComboBox cmb_status;
    private javax.swing.JComboBox cmb_supplier;
    private javax.swing.JComboBox cmb_tax;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton9;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbl_Gtotal;
    private javax.swing.JLabel lbl_total;
    private javax.swing.JLabel lbl_vat;
    private javax.swing.JTextArea txtA_address;
    private javax.swing.JTextField txt_discount;
    private javax.swing.JTextField txt_orderno;
    private javax.swing.JTextArea txt_remarks;
    private javax.swing.JTextField txt_shipping;
    private javax.swing.JTextField txt_supplierid;
    // End of variables declaration//GEN-END:variables
}
