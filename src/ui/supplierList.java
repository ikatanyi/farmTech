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

import classes.AnimatingCardLayout;
import classes.ColumnResizer;
import classes.DashboardAnimation;
import classes.labelRenderer;
import classes.superCls;
import farmTech.util.HibernateUtil;
import custom_jPanels.RoundRectGradientPanel;
import custom_jPanels.SimpleGradientPanel;
import custom_jPanels.StandardButton;
import farm.entity.DtSuppliers;
import java.awt.CardLayout;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
public class supplierList extends javax.swing.JInternalFrame {
private JLabel errorFields;
private DefaultTableModel supplier_model;
private DtSuppliers suppliers;
private superCls sup=new superCls();
private CardLayout cardlayout;
public String supplier_name,supplier_code;
    /**
     * Creates new form supplierList
     */
    public supplierList() {
        initComponents();
    }

    public void formload(){
        SwingWorker doComputeRate=new SwingWorker() {
                  @Override
	          protected Integer doInBackground() throws Exception{
                    supplier_display(0,"",0);
                    jTable5.setModel(supplier_model);
                  return 0;
	        }
                @Override
                protected void done(){
                     SwingUtilities.invokeLater(new Runnable() {
                       public void run() {                                                          
                        jTable5.getColumnModel().getColumn(7).setCellRenderer( new  labelRenderer());
                        ColumnResizer.adjustColumnPreferredWidths (jTable5);
                        jTable5.revalidate();
                    }});
                }
        };
        doComputeRate.execute();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new RoundRectGradientPanel();
        jPanel6 = new SimpleGradientPanel();
        jLabel33 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jButton3 = new StandardButton();
        jButton7 = new StandardButton();
        jButton13 = new StandardButton();
        jButton15 = new StandardButton();
        jPanel7 = new javax.swing.JPanel();
        jPanel12 = new RoundRectGradientPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jLabel43 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setTitle("Suppliers List");
        setToolTipText("");

        jPanel4.setName("card2"); // NOI18N

        jLabel33.setText("Menu");

        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jButton3.setText("Add New");
        jButton3.setEnabled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jButton7.setText("Active");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton13.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jButton13.setText("BlackListed");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton15.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jButton15.setText("View All");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButton15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel33)
                .addGap(4, 4, 4)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton15)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setLayout(new java.awt.CardLayout());
        cardlayout=new AnimatingCardLayout(new DashboardAnimation());
        jPanel7.setLayout(cardlayout);

        jPanel12.setName("card3"); // NOI18N

        jTable5.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
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
        jTable5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable5MouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jTable5);

        jLabel43.setText("Active");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator8)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 719, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(5, 5, 5))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel43)
                .addGap(4, 4, 4)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jPanel7.add(jPanel12, "card3");

        jLabel1.setBackground(new java.awt.Color(153, 0, 102));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("  Supplier List");
        jLabel1.setOpaque(true);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        getContentPane().add(jPanel4, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
//        CardLayout cardmanager=(CardLayout)(jPanel7.getLayout());
//        cardmanager.show(jPanel7,"card2");
//        supplier_display(0,"",0);
//        jTable3.setModel(supplier_model);
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                jTable3.getColumnModel().getColumn(7).setCellRenderer( new  labelRenderer());
//                jTable3.getColumnModel().getColumn(8).setCellRenderer( new  labelRenderer());
//                ColumnResizer.adjustColumnPreferredWidths (jTable3);
//                jTable3.revalidate();
//            }});
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        CardLayout cardmanager=(CardLayout)(jPanel7.getLayout());
        cardmanager.show(jPanel7,"card3");
        jLabel43.setText("Active Suppliers List");
        
        SwingWorker doComputeRate=new SwingWorker() {
                  @Override
	          protected Integer doInBackground() throws Exception{
                    supplier_display(1,"active",0);
                    jTable5.setModel(supplier_model);
                  return 0;
	        }
                @Override
                protected void done(){
                     SwingUtilities.invokeLater(new Runnable() {
                       public void run() {                                                          
                        jTable5.getColumnModel().getColumn(7).setCellRenderer( new  labelRenderer());
                        ColumnResizer.adjustColumnPreferredWidths (jTable5);
                        jTable5.revalidate();
                    }});
                }
        };
        doComputeRate.execute();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        CardLayout cardmanager=(CardLayout)(jPanel7.getLayout());
        cardmanager.show(jPanel7,"card3");
        jLabel43.setText("Blacklisted Suppliers List");
        
        SwingWorker doComputeRate=new SwingWorker() {
                  @Override
	          protected Integer doInBackground() throws Exception{
                    supplier_display(1,"Blacklisted",0);
                    jTable5.setModel(supplier_model);
                  return 0;
	        }
                @Override
                protected void done(){
                     SwingUtilities.invokeLater(new Runnable() {
                       public void run() {                                                          
                        jTable5.getColumnModel().getColumn(7).setCellRenderer( new  labelRenderer());
                        ColumnResizer.adjustColumnPreferredWidths (jTable5);
                        jTable5.revalidate();
                    }});
                }
        };
        doComputeRate.execute();
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        CardLayout cardmanager=(CardLayout)(jPanel7.getLayout());
        cardmanager.show(jPanel7,"card3");
        jLabel43.setText("All Suppliers List");
        SwingWorker doComputeRate=new SwingWorker() {
                  @Override
	          protected Integer doInBackground() throws Exception{
                    supplier_display(0,"all",0);
                    jTable5.setModel(supplier_model);
                  return 0;
	        }
                @Override
                protected void done(){
                     SwingUtilities.invokeLater(new Runnable() {
                       public void run() {                                                          
                        jTable5.getColumnModel().getColumn(7).setCellRenderer( new  labelRenderer());
                        ColumnResizer.adjustColumnPreferredWidths (jTable5);
                        jTable5.revalidate();
                    }});
                }
        };
        doComputeRate.execute();
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jTable5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable5MouseClicked
        // TODO add your handling code here:
        int col=jTable5.getSelectedColumn();
        int row=jTable5.getSelectedRow();
        int code=Integer.parseInt(sup.getIntValue((jTable5.getValueAt(row, 0))));
        String name=sup.getValue((jTable5.getValueAt(row, 1)));
        String supp_code=sup.getValue((jTable5.getValueAt(row, 2)));
        DefaultTableModel model=(DefaultTableModel)jTable5.getModel();
        
        this.setSupplier_code(supp_code);
        this.setSupplier_name(name);
        if(col==8){
            deleteSupplier(code);
            model.removeRow(row);
        }
    }//GEN-LAST:event_jTable5MouseClicked

private boolean deleteSupplier(int id){
       org.hibernate.Session session = HibernateUtil.getSessionFactory().openSession();
       Transaction tx = null;
       boolean status=false;
      try{
         tx = session.beginTransaction();
         DtSuppliers supplier = (DtSuppliers)session.get(DtSuppliers.class, id); 
         if(supplier!=null)
            session.delete(supplier);
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

private void supplier_display(int val,String name,int upperlimit) {
    try {
        String hql;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        switch(val){
            case 0:
                hql="from DtSuppliers s ORDER by s.id DESC";
                break;
            case 1:
                hql="from DtSuppliers s WHERE s.status=:status ORDER by s.id ASC";
                break;
            default:
                hql="from DtSuppliers s ORDER by s.id DESC";
                break;
    }
        Query q = session.createQuery(hql);
        if(val==1)
            q.setParameter("status", name);
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
    String header[]={"Item","Supplier","Supplier#","Phone","Address","Email","status",""};
    Vector<String> tableHeaders= new Vector<>();
    tableHeaders.addAll(Arrays.asList(header));
    for(Object o : resultList) {
        
        DtSuppliers collection = (DtSuppliers)o;
        Vector<Object> oneRow = new Vector<>();
        List col=Arrays.asList(
                               collection.getId(),
                               collection.getSuppliername(),
                               collection.getSupplierId(),
                               collection.getPhone(),
                               collection.getAddress(),
                               collection.getEmail(),
                               collection.getStatus(),
                               "delete"
                               );
                         
        oneRow.addAll(col);
        tableData.add(oneRow);
    }
    if(resultList.isEmpty()){
       supplier_model=new DefaultTableModel(new String[0][0],header);   
    }else
        supplier_model=new DefaultTableModel(tableData, tableHeaders);
   }
   
   private void FindSupplier(int id){
       org.hibernate.Session session = HibernateUtil.getSessionFactory().openSession();
       Transaction tx = null;
       boolean status=false;
      try{
         tx = session.beginTransaction();
         suppliers = (DtSuppliers)session.get(DtSuppliers.class, id); 
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

    public String getSupplier_name() {
        return supplier_name;
    }

    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name;
    }

    public String getSupplier_code() {
        return supplier_code;
    }

    public void setSupplier_code(String supplier_code) {
        this.supplier_code = supplier_code;
    }
   
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JTable jTable5;
    // End of variables declaration//GEN-END:variables
}