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
import classes.clsDate;
import classes.clsReport;
import classes.rowcount;
import classes.superCls;
import custom_jPanels.SimpleGradientPanel;
import farm.entity.DtCustomer;
import farm.entity.DtInvoice;
import farm.entity.DtShippings;
import farm.entity.DtVat;
import farmTech.util.HibernateUtil;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
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
public class invoice extends javax.swing.JInternalFrame {

    private Object[] customerID,address,VAT,SHIPMENT;
    private Object[][] data;
    private superCls sup=new superCls();
    private JLabel errorFields;
    private JTextField item;
    private DtInvoice invoice;
    private HashMap jasperParameter;
    private clsReport rpt;
//    Delivery delivery=new Delivery();
    
    
    InvoiceTableModel item_model;
    private double discount=0.0,vat=0.0,unit_price=0.00,price=0.00,gross_total=0.00,shipping=0.00;
    private int  qty=0;
    private final TextFieldEditor inte=new TextFieldEditor("integer");
    private final TextFieldEditor str=new TextFieldEditor("string");
       
    /**
     * Creates new form invoice
     */
    public invoice() {
        initComponents();
    }

    public void formload(){
        SwingWorker doComputeRate=new SwingWorker() {
                  @Override
	          protected Integer doInBackground() throws Exception{
                    cmb_customer.setModel(customers());
                    cmb_tax.setModel(vats());
                    cmb_ship.setModel(shippings());
                  return 0;
	        }
                @Override
                protected void done(){
                     SwingUtilities.invokeLater(new Runnable() {
                       public void run() {  
                           cmb_customer.setSelectedIndex(-1);
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
                    cmb_customer.setModel(customers());
                  return 0;
	        }
                @Override
                protected void done(){
                     SwingUtilities.invokeLater(new Runnable() {
                       public void run() {  
                           cmb_customer.setSelectedIndex(-1);     
//                        jTable5.getColumnModel().getColumn(7).setCellRenderer( new  labelRenderer());
//                        ColumnResizer.adjustColumnPreferredWidths (jTable5);
//                        jTable5.revalidate();
                    }});
                }
        };
        doComputeRate.execute();
//        resetfields();
        jTable1.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        cmb_customer.setSelectedItem(customer);
        txt_custid.setText(custid);
        txtA_address.setText(address);
        jDateChooser2.setDate(serviceDate);
        jDateChooser3.setDate(orderDate);      
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
        item_model=(InvoiceTableModel)model;
        item_model.addTableModelListener(new InteractiveTableModelListener());
        jTable1.setModel(item_model);
    }
    
    public void sale_formload(final InvoiceTableModel model, String customer,String custid,String address,Date orderDate,Date serviceDate,double gross,double discount,double shipping,double vat,double total){
        resetfields();
        jButton7.setEnabled(false);
        SwingWorker doComputeRate=new SwingWorker() {
                  @Override
	          protected Integer doInBackground() throws Exception{
                    cmb_customer.setModel(customers());
                  return 0;
	        }
                @Override
                protected void done(){
                     SwingUtilities.invokeLater(new Runnable() {
                       public void run() {  
                           cmb_customer.setSelectedIndex(-1);     
//                        jTable5.getColumnModel().getColumn(7).setCellRenderer( new  labelRenderer());
//                        ColumnResizer.adjustColumnPreferredWidths (jTable5);
//                        jTable5.revalidate();
                    }});
                }
        };
        doComputeRate.execute();
//        resetfields();
        jTable1.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        cmb_customer.setSelectedItem(customer);
        txt_custid.setText(custid);
        txtA_address.setText(address);
        jDateChooser2.setDate(serviceDate);
        jDateChooser3.setDate(orderDate);      
        lbl_Gtotal.setText(String.valueOf(gross));
        txt_discount.setText(String.valueOf(discount));
        txt_shipping.setText(String.valueOf(shipping));
        lbl_vat.setText(String.valueOf(vat));
        lbl_total.setText(String.valueOf(total));
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
        item_model=(InvoiceTableModel)model;
        item_model.addTableModelListener(new InteractiveTableModelListener());
        jTable1.setModel(item_model);
        calc();
    }
    
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
        txt_invoiceno = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        cmb_customer = new javax.swing.JComboBox();
        txt_custid = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtA_address = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
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
        jLabel15 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel16 = new javax.swing.JLabel();
        jDateChooser4 = new com.toedter.calendar.JDateChooser();
        cmb_payment = new javax.swing.JComboBox();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        txt_orderNo = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        cmb_payment_status = new javax.swing.JComboBox();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 19), new java.awt.Dimension(0, 19), new java.awt.Dimension(32767, 19));
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(24, 0), new java.awt.Dimension(24, 0), new java.awt.Dimension(24, 32767));

        jPanel1.setMinimumSize(new java.awt.Dimension(118, 46));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel1.setText("Invoice No.");

        txt_invoiceno.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        txt_invoiceno.setToolTipText("");

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel2.setText("Date :");

        jDateChooser1.setDate(new Date());
        jDateChooser1.setDateFormatString("dd-MM-yyyy");
        jDateChooser1.setOpaque(false);

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel3.setText("Customer :");

        cmb_customer.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        cmb_customer.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_customerItemStateChanged(evt);
            }
        });

        txt_custid.setEditable(false);
        txt_custid.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        txt_custid.setToolTipText("");

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel5.setText("Address :");

        txtA_address.setColumns(20);
        txtA_address.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        txtA_address.setRows(5);
        txtA_address.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txtA_address);

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel6.setText("Service Date :");

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel7.setText("Order Date :");

        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel8.setText("VAT :");

        jDateChooser2.setDate(new Date());
        jDateChooser2.setDateFormatString("dd-MM-yyyy");
        jDateChooser2.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jDateChooser2.setOpaque(false);

        jDateChooser3.setDate(new Date());
        jDateChooser3.setDateFormatString("dd-MM-yyyy");
        jDateChooser3.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jDateChooser3.setOpaque(false);

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

        jLabel15.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel15.setText("Due Days :");

        jSpinner1.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jSpinner1.setModel(new javax.swing.SpinnerNumberModel());
        jSpinner1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner1StateChanged(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel16.setText("Pay Until :");

        jDateChooser4.setDate(new Date());
        jDateChooser4.setDateFormatString("dd-MM-yyyy");
        jDateChooser4.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jDateChooser4.setOpaque(false);

        cmb_payment.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        cmb_payment.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pay Cash", "Cheque", "Credit" }));

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

        jLabel17.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel17.setText("Order No");

        txt_orderNo.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/invoice_new_32.png"))); // NOI18N

        jToolBar1.setBorder(javax.swing.BorderFactory.createTitledBorder("Duplicate"));
        jToolBar1.setRollover(true);
        jToolBar1.setOpaque(false);

        jButton7.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delivery_new_32.png"))); // NOI18N
        jButton7.setText("Delivery Note");
        jButton7.setFocusable(false);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setOpaque(false);
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton7);

        jButton8.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/credit_new_32.png"))); // NOI18N
        jButton8.setText("Credit");
        jButton8.setEnabled(false);
        jButton8.setFocusable(false);
        jButton8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton8.setOpaque(false);
        jButton8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton8);

        jButton9.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jButton9.setText("Save");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel20.setText("Status :");

        cmb_payment_status.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        cmb_payment_status.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Unpaid", "Paid", "Cancelled" }));

        jLabel21.setForeground(new java.awt.Color(255, 51, 51));
        jLabel21.setText("*");

        jLabel22.setForeground(new java.awt.Color(251, 49, 105));
        jLabel22.setText("*");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel20)
                        .addGap(4, 4, 4)
                        .addComponent(cmb_payment_status, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(cmb_payment, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel15)
                        .addGap(4, 4, 4)
                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jLabel16)
                        .addGap(4, 4, 4)
                        .addComponent(jDateChooser4, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(159, 159, 159)
                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(159, 159, 159)
                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmb_ship, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_discount, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_shipping, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_Gtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lbl_vat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_total, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(48, 48, 48))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txt_invoiceno, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cmb_customer, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_custid))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_orderNo, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel19)
                                .addGap(29, 29, 29)))
                        .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cmb_tax, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jButton5)
                        .addGap(3, 3, 3)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(52, 52, 52))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(filler2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel22))
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
                        .addGap(4, 4, 4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator1)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 773, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel21))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel17)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel6)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel7)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel8))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(txt_orderNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(cmb_tax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton9)
                            .addComponent(jButton5)
                            .addComponent(jButton6)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(filler2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel22)
                                            .addComponent(jLabel1))))
                                .addGap(10, 10, 10)
                                .addComponent(jLabel3))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(txt_invoiceno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel2))
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(6, 6, 6)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cmb_customer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_custid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(jLabel5))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel20))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(cmb_payment_status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(cmb_payment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel15))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel16))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jDateChooser4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(4, 4, 4)
                        .addComponent(jLabel14))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbl_vat)
                        .addGap(4, 4, 4)
                        .addComponent(lbl_total)))
                .addContainerGap())
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmb_customerItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_customerItemStateChanged
        // TODO add your handling code here:
        int idx=cmb_customer.getSelectedIndex();
        int event=evt.getStateChange();
        if(idx!=-1 && event==ItemEvent.SELECTED){
            String custid=sup.getValue(customerID[idx]);
            String add=sup.getValue(address[idx]);
            txt_custid.setText(custid);
            txtA_address.setText(add);
        }
    }//GEN-LAST:event_cmb_customerItemStateChanged

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
        String invoiceNo=txt_invoiceno.getText();
        String customerid=txt_custid.getText();
        String orderNo=txt_orderNo.getText();
        String orderDate=new SimpleDateFormat("dd-MM-yyyy").format(jDateChooser3.getDate());
        String serviceDate=new SimpleDateFormat("dd-MM-yyyy").format(jDateChooser2.getDate());
        String date=new SimpleDateFormat("dd-MM-yyyy").format(jDateChooser1.getDate());
        String dueDate=new SimpleDateFormat("dd-MM-yyyy").format(jDateChooser4.getDate());
        String customerName=sup.getValue(cmb_customer.getSelectedItem());
        String shipto=txtA_address.getText();
        sup.getValue(cmb_tax.getSelectedItem());
        String remarks=txt_remarks.getText();
        String paidStatus=sup.getValue(cmb_payment_status.getSelectedItem());
        String pay_mode=sup.getValue(cmb_payment.getSelectedItem());
        Double.parseDouble(sup.getCostValue(lbl_Gtotal.getText()));
        double discount=Double.parseDouble(sup.getCostValue(txt_discount.getText()));
        double shippingCost=Double.parseDouble(sup.getCostValue(txt_shipping.getText()));
        double vat=Double.parseDouble(sup.getCostValue(lbl_vat.getText()));
        double total=Double.parseDouble(sup.getCostValue(lbl_total.getText()));
        
        if(!invoiceNo.equals("") && !customerid.equals("")){
            if(this.save_invoice(invoiceNo, customerid, orderNo, orderDate, serviceDate, vat, remarks, total, discount, shippingCost, paidStatus, dueDate, date)){
               jasperParameter=new HashMap();
               jasperParameter.put("invoiceNo", invoiceNo);
               jasperParameter.put("CustomerName", customerName);
               jasperParameter.put("customerId", customerid);
               jasperParameter.put("orderNo", orderNo);
               jasperParameter.put("invoiceNo", invoiceNo);
               jasperParameter.put("orderDate", orderDate);
               jasperParameter.put("serviceDate", serviceDate);
               jasperParameter.put("dueDate", dueDate);
               jasperParameter.put("shipto", shipto);
               jasperParameter.put("remarks", remarks);
               jasperParameter.put("discount", discount);
               jasperParameter.put("shippingCost", shippingCost);
               jasperParameter.put("VAT", vat);
               jasperParameter.put("Total", total);
               jasperParameter.put("payment_mode", pay_mode);
               
               rpt=new clsReport();
               rpt.table_Reporter(jasperParameter, "invoice_template.jasper", new JRTableModelDataSource(item_model));
            }
            
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
        int option=JOptionPane.showOptionDialog(null,supp.getContentPane(), "Product List",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);
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

    private void jSpinner1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner1StateChanged
        // TODO add your handling code here:
        int days=Integer.parseInt(sup.getIntValue(jSpinner1.getValue()));
        jDateChooser4.setDate(new clsDate().seekDateByDay(-days));
    }//GEN-LAST:event_jSpinner1StateChanged

    private void cmb_taxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_taxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_taxActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        String invoiceNo=txt_invoiceno.getText();
        String customerid=txt_custid.getText();
        String orderNo=txt_orderNo.getText();
        String orderDate=new SimpleDateFormat("dd-MM-yyyy").format(jDateChooser3.getDate());
        String serviceDate=new SimpleDateFormat("dd-MM-yyyy").format(jDateChooser2.getDate());
        String date=new SimpleDateFormat("dd-MM-yyyy").format(jDateChooser1.getDate());
        String dueDate=new SimpleDateFormat("dd-MM-yyyy").format(jDateChooser4.getDate());
        String custName=sup.getValue(cmb_customer.getSelectedItem());
        String address=txtA_address.getText();
        sup.getValue(cmb_tax.getSelectedItem());
        String remarks=txt_remarks.getText();
        String paidStatus=sup.getValue(cmb_payment_status.getSelectedItem());
        String pay_type=sup.getValue(cmb_payment.getSelectedItem());
        Double.parseDouble(sup.getCostValue(lbl_Gtotal.getText()));
        double discount=Double.parseDouble(sup.getCostValue(txt_discount.getText()));
        double shippingCost=Double.parseDouble(sup.getCostValue(txt_shipping.getText()));
        double vat=Double.parseDouble(sup.getCostValue(lbl_vat.getText()));
        double total=Double.parseDouble(sup.getCostValue(lbl_total.getText()));
        
        if(!invoiceNo.equals("") && !customerid.equals("")){
            this.save_invoice(invoiceNo, customerid, orderNo, orderDate, serviceDate, vat, remarks, total, discount, shippingCost, paidStatus, dueDate, date);
        }else{
            errorFields = new JLabel("<HTML><FONT COLOR = red>Please make sure required fields are filled</FONT></HTML>"); 
            JOptionPane.showMessageDialog(null,errorFields,"farmTech",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        Container jpanel1=this.getContentPane().getParent();    
        Component comp=jpanel1.getComponent(5);
        jpanel1.remove(comp);
        Delivery delivery=new Delivery();
        jpanel1.add("delivery", delivery.getContentPane());        
        String invoice_no=txt_invoiceno.getText();
        String customer=sup.getValue(cmb_customer.getSelectedItem());
        String custid=txt_custid.getText();
        String address=txtA_address.getText();
        Date orderDate=jDateChooser3.getDate();
        Date serviceDate=jDateChooser2.getDate();
        delivery.invoice_formload(this.getDelModel(), invoice_no, customer,custid,address,orderDate,serviceDate);
        CardLayout cardmanager=(CardLayout)(jpanel1.getLayout());
        cardmanager.show(jpanel1,"delivery");      
        
    }//GEN-LAST:event_jButton7ActionPerformed

    public boolean save_invoice(String invoiceNo, String customerid, String orderNo, String orderDate, String serviceDate, double vat, String remarks, double total, double discount, double shippingCost, String paidStatus, String dueDate, String date){
      Session session = HibernateUtil.getSessionFactory().openSession();      
      Transaction tx = null;
      int val=0;
      boolean state=false;
      try{ 
            tx=session.beginTransaction();
             invoice=new DtInvoice(invoiceNo, customerid, orderNo, sup.sqlDate(orderDate), sup.sqlDate(serviceDate),  vat, remarks,  total,  discount,  shippingCost,  paidStatus,  sup.sqlDate(dueDate), sup.sqlDate(date));
           
            Query q = session.getNamedQuery("DtInvoice.findByInvoiceNo");
            q.setParameter("invoiceNo", invoiceNo);
            q.setCacheable(true);
            List<DtInvoice> vt=q.list();           
            Iterator<DtInvoice> itr=vt.iterator();         
         if(!itr.hasNext()){
            session.save(invoice);
            errorFields = new JLabel("<HTML><FONT COLOR = BLUE>Record saved successfully.</FONT></HTML>");}
         else{
            invoice=(DtInvoice)itr.next();
            invoice.setCustomerid(customerid);
            invoice.setDate(sup.sqlDate(date));
            invoice.setDiscount(discount);
            invoice.setDueDate(sup.sqlDate(dueDate));
            invoice.setInvoiceNo(invoiceNo);
            invoice.setOrderDate(sup.sqlDate(orderDate));
            invoice.setOrderNo(orderNo);
            invoice.setPaidStatus(paidStatus);
            invoice.setRemarks(remarks);
            invoice.setServiceDate(sup.sqlDate(serviceDate));
            invoice.setShippingCost(shippingCost);
            invoice.setTotal(total);
            invoice.setVat(vat);
            session.update(invoice);  
            errorFields = new JLabel("<HTML><FONT COLOR = BLUE>Record updated successfully.</FONT></HTML>");
         }
            
        tx.commit();
        JOptionPane.showMessageDialog(null,errorFields,"farmTech",JOptionPane.INFORMATION_MESSAGE);
        state=true;
            
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         errorFields = new JLabel("<HTML><FONT COLOR = red>'"+e.getMessage()+"'</FONT></HTML>"); 
         JOptionPane.showMessageDialog(null,errorFields,"farmTech",JOptionPane.INFORMATION_MESSAGE);
      }finally {
         session.flush();
         session.close(); 
      }
      return state;
   }

    public void resetfields(){
        String val=String.valueOf(new rowcount().row_count(DtInvoice.class)+1);
        String prodid="INV"+sup.getcode(6, val);
        txt_invoiceno.setText(prodid);
        cmb_customer.setSelectedIndex(-1);
        txt_custid.setText("");
        txt_orderNo.setText("");
        txtA_address.setText("");
        cmb_tax.setSelectedIndex(-1);
        txt_remarks.setText("");
        cmb_payment_status.setSelectedIndex(0);
        cmb_payment.setSelectedIndex(0);
        jSpinner1.setValue(0);
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
                
                invoice app=new invoice();
                app.formload();
                app.setDefaultCloseOperation(EXIT_ON_CLOSE);
                app.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmb_customer;
    private javax.swing.JComboBox cmb_payment;
    private javax.swing.JComboBox cmb_payment_status;
    private javax.swing.JComboBox cmb_ship;
    private javax.swing.JComboBox cmb_tax;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private com.toedter.calendar.JDateChooser jDateChooser4;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTable jTable1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lbl_Gtotal;
    private javax.swing.JLabel lbl_total;
    private javax.swing.JLabel lbl_vat;
    private javax.swing.JTextArea txtA_address;
    private javax.swing.JTextField txt_custid;
    private javax.swing.JTextField txt_discount;
    private javax.swing.JTextField txt_invoiceno;
    private javax.swing.JTextField txt_orderNo;
    private javax.swing.JTextArea txt_remarks;
    private javax.swing.JTextField txt_shipping;
    // End of variables declaration//GEN-END:variables
}
