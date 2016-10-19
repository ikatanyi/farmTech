package ui;

import classes.ColumnResizer;
import classes.clsDate;
import classes.clsReport;
import classes.labelRenderer;
import classes.superCls;
import custom_jPanels.RoundRectGradientPanel;
import farm.entity.VFarmerPayroll;
import farmTech.util.HibernateUtil;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kent
 */
public class farmer_payment extends javax.swing.JInternalFrame {

    /**
     * Creates new form farmer_payment
     */
    superCls sup=new superCls();
    clsReport rpt;
    clsDate dt=new clsDate();
    private DefaultTableModel farmer_model;
    private JLabel errorFields ;
    private String farmerid;
    private int dt1=0,dt2=0;
    private HashMap jasperParameter;
    public farmer_payment() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new RoundRectGradientPanel();
        jComboBox1 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        jMonthChooser1 = new com.toedter.calendar.JMonthChooser();
        jYearChooser1 = new com.toedter.calendar.JYearChooser();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setTitle("Milk Farmer_payment/Payroll");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ox-cow.png"))); // NOI18N

        jComboBox1.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/arrow_right.gif"))); // NOI18N
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/arrow_left.gif"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable1.setGridColor(new java.awt.Color(204, 204, 204));
        jTable1.setRowHeight(18);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel1.setText("FarmerName :");

        jTextField1.setToolTipText("");
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        jCheckBox1.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jCheckBox1.setSelected(true);
        jCheckBox1.setText("All");
        jCheckBox1.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jCheckBox1.setOpaque(false);
        jCheckBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox1ItemStateChanged(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel3.setText("Month:");

        jMonthChooser1.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jMonthChooser1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jMonthChooser1PropertyChange(evt);
            }
        });

        jYearChooser1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jYearChooser1PropertyChange(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jButton3.setText("Print Payslips");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jButton4.setText("Generate Payroll");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 818, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jMonthChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jYearChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jButton3)
                        .addGap(5, 5, 5)
                        .addComponent(jButton4)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(2, 2, 2)
                            .addComponent(jYearChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jMonthChooser1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(jButton4))))
                .addGap(14, 14, 14)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jMonthChooser1, jYearChooser1});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel1, jLabel3});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        // TODO add your handling code here:
               
        panel_Search search=new panel_Search();
        search.formload();
        int option=JOptionPane.showInternalConfirmDialog(this.getParent(),search, "Farmer Search",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
        if(option==JOptionPane.OK_OPTION){
            farmerid=search.getPatientId();
            if(!farmerid.equals("")){
                jCheckBox1.setSelected(false);
               payroll_display(1,farmerid, dt1, dt2,0);
               jTable1.setModel(farmer_model); 
               SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    jTable1.getColumnModel().getColumn(7).setCellRenderer( new  labelRenderer());
                    ColumnResizer.adjustColumnPreferredWidths (jTable1);
                    jTable1.revalidate();
                }});
            }
            //            EnableFarmerFields(false);
        }
        jTextField1.setText("Type here");
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int value=Integer.parseInt(new superCls().getIntValue(jComboBox1.getSelectedIndex()));
        int max=jComboBox1.getItemCount();
        int upperlimit=30;
        if(value!=max){
            value++;
            jComboBox1.setSelectedItem(value);
        }else{
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int value=Integer.parseInt(new superCls().getIntValue(jComboBox1.getSelectedIndex()));
        int max=jComboBox1.getItemCount();
        int upperlimit=30;
        if(value!=0){
            value--;
            jComboBox1.setSelectedItem(value);
        }else{
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        // TODO add your handling code here:
         int value=Integer.parseInt(new superCls().getIntValue(jComboBox1.getSelectedItem())),upperlimit=30;
        if(value>0)
            upperlimit=(value)*upperlimit;
           else
            upperlimit=0;
        if(farmerid==null||farmerid.equals(""))           
           payroll_display(0,farmerid, dt1, dt2,upperlimit);
        else
            payroll_display(1,farmerid, dt1, dt2,upperlimit);
        jTable1.setModel(farmer_model); 
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                jTable1.getColumnModel().getColumn(8).setCellRenderer( new  labelRenderer());
                ColumnResizer.adjustColumnPreferredWidths (jTable1);
                jTable1.revalidate();
            }});
        
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jCheckBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox1ItemStateChanged
        // TODO add your handling code here:
        if(jCheckBox1.isSelected())
            farmerid=null;
        else{
            errorFields = new JLabel("<HTML><FONT COLOR = BLUE>please select a farmer</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields,"DMS",JOptionPane.INFORMATION_MESSAGE);
        }
            
    }//GEN-LAST:event_jCheckBox1ItemStateChanged

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        jasperParameter=new HashMap();
        int col=jTable1.getSelectedColumn();
        int row=jTable1.getSelectedRow();
        String id=sup.getValue((jTable1.getValueAt(row, 0)));
        String date=sup.getValue((jTable1.getValueAt(row, 2)));
        int mon=jMonthChooser1.getMonth()+1;
        int year=jYearChooser1.getYear();
        jasperParameter.put("month", mon);
        jasperParameter.put("year", year);
        jasperParameter.put("farmerid", id);
         
        if(col==8 && id!=null){
             rpt=new clsReport(date,dt1,dt2);
             rpt.report_viewer("rpt_farmer_payment.jasper",jasperParameter);
           }      
    }//GEN-LAST:event_jTable1MouseClicked

    private void jMonthChooser1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jMonthChooser1PropertyChange
        // TODO add your handling code here:
         dt1=jMonthChooser1.getMonth()+1;
//         dt2=jYearChooser1.getYear();
         if(farmerid==null||farmerid.equals(""))           
           payroll_display(0,farmerid, dt1, dt2,0);
        else
            payroll_display(1,farmerid, dt1, dt2,0);
         jTable1.setModel(farmer_model);
         SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                jTable1.getColumnModel().getColumn(8).setCellRenderer( new  labelRenderer());
                ColumnResizer.adjustColumnPreferredWidths (jTable1);
                jTable1.revalidate();
            }});
    }//GEN-LAST:event_jMonthChooser1PropertyChange

    private void jYearChooser1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jYearChooser1PropertyChange
        // TODO add your handling code here:
//        dt1=jMonthChooser1.getMonth()+1;
         dt2=jYearChooser1.getYear();
         if(farmerid==null || farmerid.equals(""))           
           payroll_display(0,farmerid, dt1, dt2,0);
        else
            payroll_display(1,farmerid, dt1, dt2,0);
         jTable1.setModel(farmer_model);
         SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                jTable1.getColumnModel().getColumn(8).setCellRenderer( new  labelRenderer());
                ColumnResizer.adjustColumnPreferredWidths (jTable1);
                jTable1.revalidate();
            }});
    }//GEN-LAST:event_jYearChooser1PropertyChange

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        rpt=new clsReport("",dt1,dt2);
        rpt.report("", "rpt_all_milk payment.jasper");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        String center = "";
        jasperParameter=new HashMap();
        int mon=jMonthChooser1.getMonth()+1;
        int year=jYearChooser1.getYear();
        jasperParameter.put("month", mon);
        jasperParameter.put("year", year);
        jasperParameter.put("center", center);
        
        rpt=new clsReport("",mon,year);
        if(farmerid==null||farmerid.equals(""))
           rpt.report_viewer("report5Dairy_payment_journal.jasper",jasperParameter);
        else
           rpt.report(farmerid, "rpt_individual_collection.jasper");
    }//GEN-LAST:event_jButton4ActionPerformed

    public void formload(){        
        payroll_display(0,"", 0, 0,0);
        jTable1.setModel(farmer_model);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                jTable1.getColumnModel().getColumn(8).setCellRenderer( new  labelRenderer());
                ColumnResizer.adjustColumnPreferredWidths (jTable1);
                jTable1.revalidate();
            }});
         jComboBox1.setModel(new superCls().pagination("VFarmerPayroll"));
         dt1=jMonthChooser1.getMonth()+1;
         dt2=jYearChooser1.getYear();
    }
   private void payroll_display(int val,String farmerid,int month, int year,int upperlimit) {
    try {
        String hql;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        switch(val){
            case 0:
                hql="from VFarmerPayroll pay where Month(pay.date)=:month and Year(pay.date)=:year order by pay.id asc";
                break;
            case 1:
                hql="from VFarmerPayroll pay where pay.farmerId=:id and Month(pay.date)=:month and Year(pay.date)=:year order by pay.id asc";
                break;
            case 2:
                hql="from VFarmerPayroll pay where Month(pay.date)=:month and Year(pay.date)=:year order by pay.id asc";
                break;
            default:
                hql="from VFarmerPayroll pay where Month(pay.date)=:month and Year(pay.date)=:year order by pay.id asc";
                break;
    }
        Query q = session.createQuery(hql);
        if(val==1)
            q.setParameter("id", farmerid);        
         q.setParameter("month", dt1);
         q.setParameter("year", dt2);
         q.setFirstResult(upperlimit);
         q.setMaxResults(30);
         List resultList = q.list();
         displayAssets(resultList);        
         session.getTransaction().commit();
    } catch (HibernateException he) {
        errorFields = new JLabel("<HTML><FONT COLOR = BLUE>'"+he.getMessage()+"'</FONT></HTML>");
        JOptionPane.showMessageDialog(null,errorFields,"DMS_fpayment",JOptionPane.ERROR_MESSAGE); 
    }
   }
   
   private void displayAssets(List resultList) {
    Vector tableData = new Vector();
    String header[]={"farmerId","Name","Date","Quantity","rate","Gross","Credit","Net",""};
    Vector<String> tableHeaders= new Vector<>();
    tableHeaders.addAll(Arrays.asList(header));
    for(Object o : resultList) {
        
        VFarmerPayroll collection = (VFarmerPayroll)o;
        Vector<Object> oneRow = new Vector<>();
        List col=Arrays.asList(
                               collection.getFarmerId(),
                               sup.Farmer_name(collection.getFarmerId()),
                               collection.getDate(),
                               collection.getQuantity(),
                               collection.getRate(),
                               collection.getQuantity()*collection.getRate(),
                               this.getCredit(collection.getFarmerId(), dt1, dt2),
                               (collection.getRate()*collection.getQuantity())+(this.getCredit(collection.getFarmerId(), dt1, dt2)),
                               "view Details"
                               );                         
        oneRow.addAll(col);
        tableData.add(oneRow);
    }
    if(resultList.isEmpty()){
       farmer_model=new DefaultTableModel(new String[8][20],header);   
    }else
        farmer_model=new DefaultTableModel(tableData, tableHeaders);
   }
   
   private Double getCredit(String farmerid,int month,int year){
       org.hibernate.Session session = HibernateUtil.getSessionFactory().openSession();
       Double credit=0.0;
      try {        
           session.beginTransaction();
           SQLQuery q = session.createSQLQuery("SELECT SUM(e.balance) from v_farmer_expenses e WHERE e.description='Milk' AND e.farmerId=:farmerId AND Month(e.date)=:month AND Year(e.date)=:year");
           q.setParameter("farmerId", farmerid);
           q.setParameter("month", month);
           q.setParameter("year", year);
           List<Object[]> auth=q.list();
           for(Object o:auth){  
               credit=(Double)o;    
           }
           session.getTransaction().commit();
         }catch (HibernateException he) {
                 errorFields = new JLabel("<HTML><FONT COLOR = Blue>'"+he.getMessage()+"'</FONT></HTML>");
                 JOptionPane.showMessageDialog(null,errorFields,"farmTech",JOptionPane.ERROR_MESSAGE);

         }finally {
             session.close(); 
         }
          return credit==null? 0.0:credit;
   }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private com.toedter.calendar.JMonthChooser jMonthChooser1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private com.toedter.calendar.JYearChooser jYearChooser1;
    // End of variables declaration//GEN-END:variables
}
