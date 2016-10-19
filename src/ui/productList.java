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

import classes.ColumnResizer;
import classes.labelRenderer;
import classes.superCls;
import custom_jPanels.SimpleGradientPanel;
import farm.entity.DtInventoryItemlist;
import farm.entity.DtProduct;
import farm.entity.DtSuppliers;
import farmTech.util.HibernateUtil;
import java.awt.CardLayout;
import java.awt.Frame;
import java.awt.event.ItemEvent;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.getFrameForComponent;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Kent
 */
public class productList extends javax.swing.JInternalFrame {
private JLabel errorFields;
private DefaultTableModel product_model;
private DtProduct products;
private superCls sup=new superCls();
private CardLayout cardlayout;
public String product_name,product_code;
private int vat=0;
private double price;
private Object[] supp_id,supplierID;
    /**
     * Creates new form productList
     */
    public productList() {
        initComponents();
    }

    public void formload(){
        SwingWorker doComputeRate=new SwingWorker() {
                  @Override
	          protected Integer doInBackground() throws Exception{
                    product_display(0,"",0);
                    jTable5.setModel(product_model);
                    cmb_supplier.setModel(supplier(""));
                  return 0;
	        }
                @Override
                protected void done(){
                     SwingUtilities.invokeLater(new Runnable() {
                       public void run() {                                                          
                        jTable5.getColumnModel().getColumn(6).setCellRenderer( new  labelRenderer());
                        ColumnResizer.adjustColumnPreferredWidths (jTable5);
                        jTable5.revalidate();
                    }});
                }
        };
        doComputeRate.execute();
        cmb_supplier.setSelectedIndex(-1);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new SimpleGradientPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        cmb_supplier = new javax.swing.JComboBox();
        txt_product = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setTitle("Store Products");
        setToolTipText("");

        jPanel4.setName("card2"); // NOI18N

        jLabel1.setBackground(new java.awt.Color(153, 0, 102));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 51, 0));
        jLabel1.setText("  Product List");

        jLabel43.setText("Search Product :");

        jLabel2.setText("Supplier Name:");

        cmb_supplier.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_supplierItemStateChanged(evt);
            }
        });

        txt_product.setToolTipText("");
        txt_product.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_productFocusGained(evt);
            }
        });

        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable5.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable5.setGridColor(new java.awt.Color(204, 204, 204));
        jTable5.setRowHeight(17);
        jTable5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable5MouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jTable5);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_product, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmb_supplier, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jSeparator8)
                        .addContainerGap())
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(5, 5, 5))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmb_supplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jButton1)
                    .addComponent(txt_product, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43))
                .addGap(8, 8, 8)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7))
        );

        getContentPane().add(jPanel4, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable5MouseClicked
        // TODO add your handling code here:
        int col=jTable5.getSelectedColumn();
        int row=jTable5.getSelectedRow();
        int code=Integer.parseInt(sup.getIntValue((jTable5.getValueAt(row, 0))));
        String name=sup.getValue((jTable5.getValueAt(row, 2)));
        String supp_code=sup.getValue((jTable5.getValueAt(row, 1)));
        int vat=Integer.parseInt(sup.getIntValue(jTable5.getValueAt(row, 5)));
        double price=Double.parseDouble(sup.getCostValue(jTable5.getValueAt(row, 3)));
        DefaultTableModel model=(DefaultTableModel)jTable5.getModel();
        
        
        if(col==6 && col!=-1){
            deleteProduct(code);
            model.removeRow(row);
        }        
        if(col!=-1 && col!=6){
            this.setProduct_code(supp_code);
            this.setProduct_name(name);
            this.setVat(vat);
            this.setPrice(price);
//            this.setVisible(false);
            Frame frame=getFrameForComponent(this.getParent());
            frame.dispose();
        }
    }//GEN-LAST:event_jTable5MouseClicked

    private void txt_productFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_productFocusGained
        // TODO add your handling code here:
        txt_product.selectAll();
    }//GEN-LAST:event_txt_productFocusGained

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        final String product=txt_product.getText();
        if(!product.equals("")){
            SwingWorker doComputeRate=new SwingWorker() {
                  @Override
	          protected Integer doInBackground() throws Exception{
                    product_display(2,product,0);
                    jTable5.setModel(product_model);
                  return 0;
	        }
                @Override
                protected void done(){
                     SwingUtilities.invokeLater(new Runnable() {
                       public void run() {                                                          
                        jTable5.getColumnModel().getColumn(6).setCellRenderer( new  labelRenderer());
                        ColumnResizer.adjustColumnPreferredWidths (jTable5);
                        jTable5.revalidate();
                    }});
                }
        };
        doComputeRate.execute();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cmb_supplierItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_supplierItemStateChanged
        // TODO add your handling code here:
        final String supplier=sup.getValue(cmb_supplier.getSelectedItem());
        int evtID=evt.getStateChange();
           if(!supplier.equals("") && evtID==ItemEvent.SELECTED){
            SwingWorker doComputeRate=new SwingWorker() {
                  @Override
	          protected Integer doInBackground() throws Exception{
                    product_display(1,supplier,0);
                    jTable5.setModel(product_model);
                  return 0;
	        }
                @Override
                protected void done(){
                     SwingUtilities.invokeLater(new Runnable() {
                       public void run() {                                                          
                        jTable5.getColumnModel().getColumn(6).setCellRenderer( new  labelRenderer());
                        ColumnResizer.adjustColumnPreferredWidths (jTable5);
                        jTable5.revalidate();
                    }});
                }
        };
        doComputeRate.execute();
        } 
    }//GEN-LAST:event_cmb_supplierItemStateChanged

private boolean deleteProduct(int id){
       org.hibernate.Session session = HibernateUtil.getSessionFactory().openSession();
       Transaction tx = null;
       boolean status=false;
      try{
         tx = session.beginTransaction();
         DtProduct product = (DtProduct)session.get(DtProduct.class, id); 
         if(product!=null)
            session.delete(product);
         tx.commit();
         status=true;
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
             errorFields = new JLabel("<HTML><FONT COLOR = BLUE>'"+e.getMessage()+"'</FONT></HTML>");
             JOptionPane.showMessageDialog(null,errorFields,"DMS",JOptionPane.ERROR_MESSAGE); 
      }finally {
         session.close(); 
      }
     return status;
   }

private void product_display(int val,String name,int upperlimit) {
    try {
        String hql;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        switch(val){
            case 0:
                hql="from DtInventoryItemlist s GROUP BY s.supplierCode, s.id ORDER by s.itemName ASC";
                break;
            case 1:
                hql="from DtInventoryItemlist s WHERE s.supplierCode=:name ORDER by s.itemName ASC";
                break;
            case 2:
                hql="from DtInventoryItemlist s WHERE s.itemName LIKE :name GROUP BY s.supplierCode, s.id ORDER by s.itemName ASC";
                break;    
            default:
                hql="from DtInventoryItemlist s GROUP BY s.supplierCode, s.id ORDER by s.itemName ASC";
                break;
    }
        Query q = session.createQuery(hql);
        if(val==1)
            q.setParameter("name", name);
        if(val==2)
            q.setParameter("name", '%'+name+'%');
        q.setFirstResult(upperlimit);
        q.setMaxResults(30);
        q.setCacheable(true);
        List resultList = q.list();
        displayAssets(resultList);        
        session.getTransaction().commit();
    } catch (HibernateException he) {
        errorFields = new JLabel("<HTML><FONT COLOR = BLUE>'"+he.getMessage()+"'</FONT></HTML>");
        JOptionPane.showMessageDialog(null,errorFields,"DMS",JOptionPane.ERROR_MESSAGE); 
    }
   }
 
   private void displayAssets(List resultList) {
       int i=0;
     Vector tableData = new Vector();
     String header[]={"Item","Product","Product#","Price","Supplier","VAT",""};
     Vector<String> tableHeaders= new Vector<>();
     tableHeaders.addAll(Arrays.asList(header));
     for(Object o : resultList) {        
        DtInventoryItemlist collection = (DtInventoryItemlist)o;
        Vector<Object> oneRow = new Vector<>();
        List col=Arrays.asList(
                               i++,
                               collection.getItemName(),
                               collection.getProductCode(),
                               collection.getUnitPrice(),
                               sup.FindSupplierName( collection.getSupplierCode()),
                               collection.getVat(),
                               "delete"
                               );                         
        oneRow.addAll(col);
        tableData.add(oneRow);
    }                       
    if(resultList.isEmpty()){
        product_model=new DefaultTableModel(new String[0][0],header);   
    }else
        product_model=new DefaultTableModel(tableData, tableHeaders);
   }
   
   private void FindProduct(int id){
       org.hibernate.Session session = HibernateUtil.getSessionFactory().openSession();
       Transaction tx = null;
       boolean status=false;
      try{
         tx = session.beginTransaction();
         products = (DtProduct)session.get(DtProduct.class, id); 
         tx.commit();
         status=true;
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
             errorFields = new JLabel("<HTML><FONT COLOR = BLUE>'"+e.getMessage()+"'</FONT></HTML>");
             JOptionPane.showMessageDialog(null,errorFields,"farmTech",JOptionPane.ERROR_MESSAGE); 
      }finally {
         session.close(); 
      }
   }

   private DefaultComboBoxModel supplier (String type) {                 
       int i=0;
       Query q;
       DefaultComboBoxModel tmodel =new DefaultComboBoxModel();
       org.hibernate.Session session = HibernateUtil.getSessionFactory().openSession();
      try {        
           session.beginTransaction();
           if(type.equals("All"))
             q = session.createQuery("from DtSuppliers s");
           else
              q = session.createQuery("from DtSuppliers s where s.status='Active'"); 
           q.setCacheable(true);
           List auth=q.list();
           Iterator iterator=auth.iterator();
           supp_id=new Object[auth.size()];
           supplierID=new Object[auth.size()];
           while(iterator.hasNext()){ 
                   DtSuppliers supp=(DtSuppliers)iterator.next();
                   tmodel.addElement(supp.getSuppliername()); 
                   supp_id[i]=supp.getId();
                   supplierID[i]=supp.getSupplierId();
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
   
    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public int getVat() {
        return vat;
    }

    public void setVat(int vat) {
        this.vat = vat;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    
   
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmb_supplier;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JTable jTable5;
    private javax.swing.JTextField txt_product;
    // End of variables declaration//GEN-END:variables
}