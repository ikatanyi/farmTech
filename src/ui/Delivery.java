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
import farm.entity.DtCustomer;
import farm.entity.DtDelivery;
import farm.entity.DtShippings;
import farm.entity.DtVat;
import farmTech.util.HibernateUtil;
import java.awt.CardLayout;
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
public class Delivery extends javax.swing.JInternalFrame {

    private Object[] customerID,address,VAT,SHIPMENT;
    private superCls sup=new superCls();
    private JLabel errorFields;
    private JTextField item;
    private DtDelivery delivery;
    private HashMap jasperParameter;
    private clsReport rpt;
//    invoice invoice=new invoice();
    
    
    DeliveryTableModel item_model;
    private double discount=0.0,vat=0.0,unit_price=0.00,price=0.00,gross_total=0.00,shipping=0.00;
    private int  qty=0;
    private final TextFieldEditor inte=new TextFieldEditor("integer");
    private final TextFieldEditor str=new TextFieldEditor("string");
       
    /**
     * Creates new form delivery
     */
    public Delivery() {
        initComponents();
    }

    public void formload(){
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
        resetfields();
        jTable1.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        
//        ImageJScrollPane isp = new ImageJScrollPane(table);
//  
//       isp.setBackgroundImage(new ImageIcon("c:\mong.jpg"));
    }
    
    public void invoice_formload(final DeliveryTableModel model, String invoice_no, String customer,String custid,String address,Date orderDate,Date serviceDate){
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
        jTable1.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        txt_invoice.setText(invoice_no);
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
        }
        item_model=(DeliveryTableModel)model;
        item_model.addTableModelListener(new InteractiveTableModelListener());
        jTable1.setModel(item_model);
//        calc();
    }
    
    public void sale_formload(final DeliveryTableModel model, String invoice_no, String customer,String custid,String address,Date orderDate,Date serviceDate){
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
        jTable1.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        txt_invoice.setText(invoice_no);
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
        }
        item_model=(DeliveryTableModel)model;
        item_model.addTableModelListener(new InteractiveTableModelListener());
        jTable1.setModel(item_model);
//        calc();
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
    }private DefaultComboBoxModel shippings () {                 
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
    
    public InvoiceTableModel getInvModel(){
        String header[]={"Qty","Item No","Name","Description","VAT","U.Price","Discount%","Price"};
        InvoiceTableModel model=new InvoiceTableModel(header);
        for(int i=0; i<item_model.getRowCount();i++){
          qty = Integer.parseInt(sup.getIntValue(item_model.getValueAt(i, 0))); 
          String item_no=sup.getValue(item_model.getValueAt(i, 1));
          String item_name=sup.getValue(item_model.getValueAt(i, 2));
          String description=sup.getValue(item_model.getValueAt(i, 3));
          
          model.addEmptyRow(new InvoiceRecord(qty,item_no,item_name,description, 0.00,0.00,0,0.00));          
       }
        return model;
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
        txt_delivery = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cmb_customer = new javax.swing.JComboBox();
        txt_custid = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtA_address = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txt_remarks = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        lbl_Gtotal = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        txt_invoice = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        jButton7 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        jPanel1.setMinimumSize(new java.awt.Dimension(118, 46));
        jPanel1.setName(""); // NOI18N
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel1.setText("Delivery No.");

        txt_delivery.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        txt_delivery.setToolTipText("");

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel2.setText("Date :");

        jDateChooser1.setDate(new Date());
        jDateChooser1.setDateFormatString("dd-MM-yyyy");
        jDateChooser1.setOpaque(false);

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel3.setText("Customer :");

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel4.setText("Cust Ref.");

        cmb_customer.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        cmb_customer.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_customerItemStateChanged(evt);
            }
        });

        txt_custid.setEditable(false);
        txt_custid.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        txt_custid.setToolTipText("");

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel5.setText("Address :");

        txtA_address.setColumns(20);
        txtA_address.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        txtA_address.setRows(5);
        txtA_address.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txtA_address);

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel6.setText("Service Date :");

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel7.setText("Order Date :");

        jDateChooser2.setDate(new Date());
        jDateChooser2.setDateFormatString("dd-MM-yyyy");
        jDateChooser2.setOpaque(false);

        jDateChooser3.setDate(new Date());
        jDateChooser3.setDateFormatString("dd-MM-yyyy");
        jDateChooser3.setOpaque(false);

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Qty", "Item No", "Name", "Description"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
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
        txt_remarks.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        txt_remarks.setRows(5);
        jScrollPane3.setViewportView(txt_remarks);

        jLabel10.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Total Gross:");

        lbl_Gtotal.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
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

        jButton5.setText("New");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Print");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel17.setText("Invoice No");

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delivery_32.png"))); // NOI18N
        jLabel19.setToolTipText("");
        jLabel19.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        jToolBar1.setBorder(javax.swing.BorderFactory.createTitledBorder("Duplicate"));
        jToolBar1.setRollover(true);
        jToolBar1.setOpaque(false);

        jButton7.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/invoice_new_32.png"))); // NOI18N
        jButton7.setText("Invoice");
        jButton7.setFocusable(false);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton7);

        jButton9.setText("Save");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel21.setForeground(new java.awt.Color(255, 51, 51));
        jLabel21.setText("*");

        jLabel22.setForeground(new java.awt.Color(251, 49, 105));
        jLabel22.setText("*");

        jLabel23.setForeground(new java.awt.Color(255, 102, 102));
        jLabel23.setText("*");

        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel8.setText("Delivery Note ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel22)
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel5)))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_invoice, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txt_delivery, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(jLabel2)
                                .addGap(3, 3, 3)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cmb_customer, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(jLabel23)
                                .addGap(2, 2, 2)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(txt_custid, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(109, 109, 109)
                        .addComponent(jLabel8)
                        .addGap(4, 4, 4)
                        .addComponent(jLabel19)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel9)
                .addGap(6, 6, 6)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton9)
                        .addGap(3, 3, 3)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jButton6))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_Gtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)))
                .addGap(7, 7, 7))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel18))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(txt_delivery, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel2))
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmb_customer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel23))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jLabel4))
                            .addComponent(txt_custid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel8))
                            .addComponent(jLabel19))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(txt_invoice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(11, 11, 11)
                                .addComponent(jLabel7)
                                .addGap(10, 10, 10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel21)))
                            .addComponent(jToolBar1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel3)
                                .addGap(29, 29, 29)
                                .addComponent(jLabel5)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(8, 8, 8)
                        .addComponent(jButton1)
                        .addGap(3, 3, 3)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel9)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(lbl_Gtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton9)
                                    .addComponent(jButton5)
                                    .addComponent(jButton6)))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7))))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        // TODO add your handling code here:
        if (jTable1.isEditing())
            jTable1.getCellEditor().stopCellEditing();
    }//GEN-LAST:event_jPanel1MouseClicked

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        String deliveryNo=txt_delivery.getText();
        String invoiceNo=txt_invoice.getText();
        String customerId=txt_custid.getText();
        String orderDate=new SimpleDateFormat("dd-MM-yyyy").format(jDateChooser3.getDate());
        String serviceDate=new SimpleDateFormat("dd-MM-yyyy").format(jDateChooser2.getDate());
        String date=new SimpleDateFormat("dd-MM-yyyy").format(jDateChooser1.getDate());
        String address=txtA_address.getText();
        String remarks=txt_remarks.getText();
        Double.parseDouble(sup.getCostValue(lbl_Gtotal.getText()));

        if(!deliveryNo.equals("") && !customerId.equals("")){
            this.save_delivery(deliveryNo, invoiceNo, customerId, serviceDate, orderDate, date, remarks);
        }else{
            errorFields = new JLabel("<HTML><FONT COLOR = red>Please make sure required fields are filled</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields,"farmTech",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        String deliveryNo=txt_delivery.getText();
        String invoiceNo=txt_invoice.getText();
        String customerId=txt_custid.getText();
        String customerName=sup.getValue(cmb_customer.getSelectedItem());
        String orderNo=txt_delivery.getText();
        String orderDate=new SimpleDateFormat("dd-MM-yyyy").format(jDateChooser3.getDate());
        String serviceDate=new SimpleDateFormat("dd-MM-yyyy").format(jDateChooser2.getDate());
        String date=new SimpleDateFormat("dd-MM-yyyy").format(jDateChooser1.getDate());
        String shipto=txtA_address.getText();
        String remarks=txt_remarks.getText();
        Double.parseDouble(sup.getCostValue(lbl_Gtotal.getText()));

        if(!deliveryNo.equals("") && !customerId.equals("") ){
            if(this.save_delivery(deliveryNo, invoiceNo, customerId, serviceDate, orderDate, date, remarks)){
                jasperParameter=new HashMap();
                jasperParameter.put("deliveryNo", deliveryNo);
                jasperParameter.put("invoiceNo", invoiceNo);
                jasperParameter.put("CustomerName", customerName);
                jasperParameter.put("customerId", customerId);
                jasperParameter.put("orderNo", orderNo);
                jasperParameter.put("invoiceNo", invoiceNo);
                jasperParameter.put("orderDate", orderDate);
                jasperParameter.put("serviceDate", serviceDate);
                jasperParameter.put("shipto", shipto);
                jasperParameter.put("remarks", remarks);
               
                rpt=new clsReport();
                rpt.table_Reporter(jasperParameter, "delivery_template.jasper", new JRTableModelDataSource(item_model));
            }
        }else{
            errorFields = new JLabel("<HTML><FONT COLOR = red>Please make sure required fields are filled</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields,"farmTech",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        resetfields();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int row=jTable1.getSelectedRow();
        if(row!=-1){
            item_model.removeRow(row);
        }else{
            errorFields = new JLabel("<HTML><FONT COLOR = BLUE>Please select item to remove</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields,"farmTech",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if(!item_model.hasEmptyRow()){
            item_model.addEmptyRow(new deliveryRecord(1,"Item","",""));
            jTable1.getColumnModel().getColumn(0).setCellEditor(inte);
            jTable1.getColumnModel().getColumn(1).setCellEditor(str);
            jTable1.getColumnModel().getColumn(2).setCellEditor(str);
            jTable1.getColumnModel().getColumn(3).setCellEditor(str);
            //            jTable1.getColumnModel().getColumn(3).setCellRenderer(new TextAreaRenderer());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        productList supp=new productList();
        supp.formload();
        int option=JOptionPane.showOptionDialog(null,supp.getContentPane(), "Product List",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);
        String item_code=supp.getProduct_code();
            if(item_code!=null && !item_model.hasEmptyRow()){

//                String item_code=supp.getProduct_code();
                String item_name=supp.getProduct_name();
                int vat1=supp.getVat();
                double price1=supp.getPrice();
                item_model.addEmptyRow(new deliveryRecord(1,item_code,item_name,""));

                jTable1.getColumnModel().getColumn(0).setCellEditor(inte);
                jTable1.getColumnModel().getColumn(1).setCellEditor(str);
                jTable1.getColumnModel().getColumn(2).setCellEditor(str);
                jTable1.getColumnModel().getColumn(3).setCellEditor(str);
            }
    }//GEN-LAST:event_jButton1ActionPerformed

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

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        String customer=sup.getValue(cmb_customer.getSelectedItem());
        String custid=txt_custid.getText();
        String address=txtA_address.getText();
        Date orderDate=jDateChooser3.getDate();
        Date serviceDate=jDateChooser2.getDate();
        
        Container jpanel1=this.getContentPane().getParent();
//        String name=this.getParent().getComponent(0).getName();
        new invoice().delivery_formload(getInvModel(), customer,custid,address,orderDate,serviceDate);
        CardLayout cardmanager=(CardLayout)(jpanel1.getLayout());
        cardmanager.show(jpanel1,"invoice");
    }//GEN-LAST:event_jButton7ActionPerformed

    public boolean save_delivery(String deliveryNo, String invoiceNo, String customerId, String serviceDate, String orderDate, String date, String remarks){
      Session session = HibernateUtil.getSessionFactory().openSession();      
      Transaction tx = null;
      int val=0;
      boolean state=false;
      try{ 
            tx=session.beginTransaction();
             delivery=new DtDelivery(deliveryNo, invoiceNo,customerId, sup.sqlDate(serviceDate), sup.sqlDate(orderDate), sup.sqlDate(date), remarks);
           
            Query q = session.getNamedQuery("DtDelivery.findByDeliveryNo");
            q.setParameter("deliveryNo", deliveryNo);
            q.setCacheable(true);
            List<DtDelivery> vt=q.list();           
            Iterator<DtDelivery> itr=vt.iterator();         
         if(!itr.hasNext()){
            session.save(delivery);
            errorFields = new JLabel("<HTML><FONT COLOR = BLUE>Record saved successfully.</FONT></HTML>");}
         else{
            delivery=(DtDelivery)itr.next();
            delivery.setCustomerId(customerId);
            delivery.setDate(sup.sqlDate(date));
            delivery.setDeliveryNo(deliveryNo);
            delivery.setOrderDate(sup.sqlDate(orderDate));
            delivery.setInvoiceNo(invoiceNo);
            delivery.setRemarks(remarks);
            delivery.setServiceDate(sup.sqlDate(serviceDate));
            session.update(delivery);  
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
        String val=String.valueOf(new rowcount().row_count(DtDelivery.class)+1);
        String prodid="D/O"+sup.getcode(6, val);
        txt_delivery.setText(prodid);
        cmb_customer.setSelectedIndex(-1);
        txtA_address.setText("");
        txt_remarks.setText("");
        lbl_Gtotal.setText("0.00");
        txt_invoice.setText("");
        txt_custid.setText("");
        
        String header[]={"Qty","Item No","Name","Description"};
        item_model=new DeliveryTableModel(header);
        item_model.addTableModelListener(new InteractiveTableModelListener());
        jTable1.setModel(item_model);
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
   
    public class InteractiveTableModelListener implements TableModelListener{
     @Override
     public void tableChanged(TableModelEvent evt) {
             int column = evt.getColumn();
             int row = evt.getFirstRow();
         if (evt.getType() == TableModelEvent.UPDATE) {             
             if(column==0|| column==4 ||column==5|| column==6){
//                 model_update(row );
                 
             }
         }
         if (evt.getType() == TableModelEvent.DELETE) {
             
         }         
         if (evt.getType() == TableModelEvent.INSERT) {
//             model_update(row );
             
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
                
                Delivery app=new Delivery();
                app.formload();
                app.setDefaultCloseOperation(EXIT_ON_CLOSE);
                app.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmb_customer;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton9;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
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
    private javax.swing.JTable jTable1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lbl_Gtotal;
    private javax.swing.JTextArea txtA_address;
    private javax.swing.JTextField txt_custid;
    private javax.swing.JTextField txt_delivery;
    private javax.swing.JTextField txt_invoice;
    private javax.swing.JTextArea txt_remarks;
    // End of variables declaration//GEN-END:variables
}
