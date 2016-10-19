/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ui;

import classes.ColumnResizer;
import classes.clsDate;
import classes.clsReport;
import classes.labelRenderer;
import classes.superCls;
import custom_jPanels.BeveledGradientPanel;
import custom_jPanels.CurvedGradientPanel;
import farm.entity.FarmerCofeeSum;
import farmTech.util.HibernateUtil;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Kent
 */
public class farmer_creport extends javax.swing.JInternalFrame {

    superCls sup=new superCls();
    clsReport rpt;
    clsDate dt=new clsDate();
    private DefaultTableModel farmer_model;
    private JLabel errorFields ;
    private String farmerid,session="1st",type="MBUNI";
    private int dt1=0,dt2=0;
    /**
     * Creates new form farmer_creport
     */
    public farmer_creport() {
        initComponents();
    }

    
    private DefaultComboBoxModel c_types () {                 
       int i=0;
       DefaultComboBoxModel tmodel =new DefaultComboBoxModel();
       org.hibernate.Session session = HibernateUtil.getSessionFactory().openSession();
      try {        
           session.beginTransaction();
           Query q = session.createQuery("Select Distinct c.type, c.id from FarmerCofeeSum c");
           List<Object[]> rows = q.list();
           for(Object[] row : rows){ 
               tmodel.addElement(row[0]);                  
               i++;         
           }
           session.getTransaction().commit();
         }catch (HibernateException he) {
                 errorFields = new JLabel("<HTML><FONT COLOR = Blue>'"+he.getMessage()+"'</FONT></HTML>");
                 JOptionPane.showMessageDialog(null,errorFields,"DMS1",JOptionPane.ERROR_MESSAGE);

         }finally {
             session.close(); 
         }
          return tmodel;
    }
    
    
    private void payroll_display(int val,String farmerid,String sesion, String type,int year,int upperlimit) {
    try {
        String hql;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        switch(val){
            case 0:
                hql="from FarmerCofeeSum pay where pay.harvest=:session and Year(pay.date)=:year order by pay.id asc";
                break;
            case 1:
                hql="from FarmerCofeeSum pay where pay.farmerid=:id and pay.harvest=:session and Year(pay.date)=:year order by pay.id asc";
                break;
            case 2:
                hql="from FarmerCofeeSum pay where pay.harvest=:session and Year(pay.date)=:year order by pay.id asc";
                break;
            case 3:
                hql="from FarmerCofeeSum pay where pay.harvest=:session and Year(pay.date)=:year and pay.type=:type order by pay.id asc";
                break;
            case 4:
                hql="from FarmerCofeeSum pay where pay.farmerid=:id and  pay.harvest=:session and Year(pay.date)=:year and pay.type=:type order by pay.id asc";
                break;    
            default:
                hql="from FarmerCofeeSum pay where pay.harvest=:session and Year(pay.date)=:year order by pay.id asc";
                break;
    }
        Query q = session.createQuery(hql);
        if(val==1||val==4)
            q.setParameter("id", farmerid);
        if(val==4||val==3)
           q.setParameter("type", type);
        
         q.setParameter("session", sesion);
         q.setParameter("year", year); 
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
    Vector<Object> oneRow = null;
    double t_amount=0.0,qty=0.0;
    String header[]={"farmerId","Name","Date","Session","Quantity","rate","Amount",""};
    Vector<String> tableHeaders= new Vector<>();
    Object[] emp=new Object[8];
    tableHeaders.addAll(Arrays.asList(header));
    for(Object o : resultList) {
        
        FarmerCofeeSum collection = (FarmerCofeeSum)o;
        oneRow = new Vector<>();
        List col=Arrays.asList(
                               collection.getFarmerid(),
                               sup.Farmer_name(collection.getFarmerid()),
                               collection.getDate(),
                               collection.getHarvest(),
                               collection.getQuantity(),                               
                               collection.getRate(),
                               collection.getQuantity()*collection.getRate(),
                               "Gen payslip"
                               );
         t_amount+=collection.getQuantity()*collection.getRate(); 
         qty+=collection.getQuantity();
         oneRow.addAll(col);
         tableData.add(oneRow);
    }
    oneRow = new Vector<>();
    Object[] emp1={"","","","Total",qty,"",t_amount};
    oneRow.addAll(Arrays.asList(emp));
    tableData.add(oneRow);
    oneRow = new Vector<>();
    oneRow.addAll(Arrays.asList(emp1));
    tableData.add(oneRow);
    if(resultList.isEmpty()){
       farmer_model=new DefaultTableModel(new String[7][20],header);   
    }else
        farmer_model=new DefaultTableModel(tableData, tableHeaders);
   }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new CurvedGradientPanel();
        jPanel2 = new BeveledGradientPanel();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jComboBox1 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jYearChooser1 = new com.toedter.calendar.JYearChooser();
        jButton3 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox();
        jComboBox3 = new javax.swing.JComboBox();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setTitle("Coffee Collection Record");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ox-cow.png"))); // NOI18N

        jTextField1.setToolTipText("");
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        jLabel1.setText("FarmerName :");

        jCheckBox1.setSelected(true);
        jCheckBox1.setText("All");
        jCheckBox1.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jCheckBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox1ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(13, 13, 13))
        );

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/arrow_right.gif"))); // NOI18N
        jButton1.setText("Next");
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/arrow_left.gif"))); // NOI18N
        jButton2.setText("Previous");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel3.setText("Session :");

        jYearChooser1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jYearChooser1PropertyChange(evt);
            }
        });

        jButton3.setText("Print Payslips");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1st", "2nd", "3rd", "4th" }));
        jComboBox2.setSelectedIndex(-1);
        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox3ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jYearChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 139, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jYearChooser1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

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
                payroll_display(1,farmerid, session,type, dt2,0);
                jTable1.setModel(farmer_model);
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
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

     public void formload(){
        
        payroll_display(0,"", session,type, 0,0);
        jTable1.setModel(farmer_model);
         SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                jTable1.getColumnModel().getColumn(7).setCellRenderer( new  labelRenderer());
                ColumnResizer.adjustColumnPreferredWidths (jTable1);
                jTable1.revalidate();
            }});
         jComboBox1.setModel(new superCls().pagination("FarmerCofeeSum"));
         jComboBox3.setModel(c_types());
         dt2=jYearChooser1.getYear();
         type=sup.getValue(jComboBox3.getSelectedItem());
    }
    
    private void jCheckBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox1ItemStateChanged
        // TODO add your handling code here:
        if(jCheckBox1.isSelected())
        farmerid=null;
        else{
            errorFields = new JLabel("<HTML><FONT COLOR = BLUE>please select a farmer</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields,"DMS",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jCheckBox1ItemStateChanged

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        // TODO add your handling code here:
        int value=Integer.parseInt(new superCls().getIntValue(jComboBox1.getSelectedItem())),upperlimit=30;
        if(value>0)
           upperlimit=(value)*upperlimit;
        else
          upperlimit=0;
        if(farmerid==null||farmerid.equals(""))
          payroll_display(0,farmerid, session,type, dt2,upperlimit);
        else
          payroll_display(1,farmerid, session, type,dt2,upperlimit);
        jTable1.setModel(farmer_model);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                jTable1.getColumnModel().getColumn(7).setCellRenderer( new  labelRenderer());
                ColumnResizer.adjustColumnPreferredWidths (jTable1);
                jTable1.revalidate();
            }});
    }//GEN-LAST:event_jComboBox1ItemStateChanged

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

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int col=jTable1.getSelectedColumn();
        int row=jTable1.getSelectedRow();
        int year=jYearChooser1.getYear();
        String id=sup.getValue((jTable1.getValueAt(row, 0)));
        String date=sup.getValue((jTable1.getValueAt(row, 2)));
        String sess=sup.getValue((jTable1.getValueAt(row, 3)));

        if(col==7 && id!=null){
            rpt=new clsReport(date,dt1,year,sess);
            rpt.report(id, "rpt_coffee_farmer_payment.jasper");
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jYearChooser1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jYearChooser1PropertyChange
        // TODO add your handling code here:
        //        dt1=jMonthChooser1.getMonth()+1;
        dt2=jYearChooser1.getYear();
        if(farmerid==null||farmerid.equals(""))
           payroll_display(0,farmerid, session,type, dt2,0);
        else
          payroll_display(1,farmerid, session,type, dt2,0);
        jTable1.setModel(farmer_model);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                jTable1.getColumnModel().getColumn(7).setCellRenderer( new  labelRenderer());
                ColumnResizer.adjustColumnPreferredWidths (jTable1);
                jTable1.revalidate();
            }});
    }//GEN-LAST:event_jYearChooser1PropertyChange

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        rpt=new clsReport("",dt1,dt2);
        rpt.report("", "rpt_all_coffee_payment.jasper");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        // TODO add your handling code here:
        if(jComboBox2.getSelectedIndex()!=-1){
            session=sup.getValue(jComboBox2.getSelectedItem());
            if(farmerid==null||farmerid.equals(""))
              payroll_display(0,farmerid, session,type, dt2,0);
            else
               payroll_display(1,farmerid, session, type,dt2,0);
            jTable1.setModel(farmer_model);
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    jTable1.getColumnModel().getColumn(7).setCellRenderer( new  labelRenderer());
                    ColumnResizer.adjustColumnPreferredWidths (jTable1);
                    jTable1.revalidate();
                }});
            }
    }//GEN-LAST:event_jComboBox2ItemStateChanged

    private void jComboBox3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox3ItemStateChanged
        // TODO add your handling code here:
        if(jComboBox3.getSelectedIndex()!=-1){
           type=sup.getValue(jComboBox3.getSelectedItem());
           if(farmerid==null||farmerid.equals(""))
              payroll_display(0,farmerid, session,type, dt2,0);
            else
               payroll_display(1,farmerid, session, type,dt2,0);
            jTable1.setModel(farmer_model);
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    jTable1.getColumnModel().getColumn(7).setCellRenderer( new  labelRenderer());
                    ColumnResizer.adjustColumnPreferredWidths (jTable1);
                    jTable1.revalidate();
                }});
            }
    }//GEN-LAST:event_jComboBox3ItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private com.toedter.calendar.JYearChooser jYearChooser1;
    // End of variables declaration//GEN-END:variables
}
