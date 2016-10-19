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
import custom_jPanels.RoundRectGradientPanel;
import farmTech.util.HibernateUtil;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Vector;
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
public class milk_statement extends javax.swing.JInternalFrame {
    
    superCls sup=new superCls();
    clsReport rpt;
    clsDate dt=new clsDate();
    private DefaultTableModel farmer_model;
    private JLabel errorFields ;
    private String farmerid;
    private double total=0.00;
    Date dt1,dt2;

    /**
     * Creates new form milk_statement
     */
    public milk_statement() {
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jSeparator1 = new javax.swing.JSeparator();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox();
        jButton3 = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setTitle("Milk Statements");

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
        jTable1.setGridColor(new java.awt.Color(204, 204, 204));
        jTable1.setRowHeight(18);
        jTable1.setSelectionBackground(java.awt.SystemColor.inactiveCaption);
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel1.setText("Search Farmer :");

        jTextField1.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jTextField1.setText("search");
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel2.setText("From :");

        jDateChooser1.setDate(new Date());

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel3.setText("To :");

        jDateChooser2.setDate(new Date());

        jCheckBox1.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jCheckBox1.setText("All");
        jCheckBox1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jCheckBox1.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jCheckBox1.setOpaque(false);

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel4.setText("Collection Center :");

        jComboBox1.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel5.setText("Farmer :");

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        jButton1.setText("Print Statement");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/arrow_left.gif"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/arrow_right.gif"))); // NOI18N
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCheckBox1)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 250, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(3, 3, 3)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox1)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel5)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton3)
                        .addComponent(jButton2)))
                .addGap(18, 18, 18))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        // TODO add your handling code here:
        int value=Integer.parseInt(new superCls().getIntValue(jComboBox1.getSelectedItem())),upperlimit=30;
        if(value>0)
        upperlimit=(value)*upperlimit;
        else
        upperlimit=0;
        if(farmerid==null||farmerid.equals(""))
        collection_display(0,"",dt1,dt2,upperlimit);
        else
        collection_display(1,farmerid,dt1,dt2,upperlimit);
        jTable1.setModel(farmer_model);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                jTable1.getColumnModel().getColumn(7).setCellRenderer( new  labelRenderer());
                ColumnResizer.adjustColumnPreferredWidths (jTable1);
                jTable1.revalidate();
            }});
    }//GEN-LAST:event_jComboBox2ItemStateChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int value=Integer.parseInt(new superCls().getIntValue(jComboBox1.getSelectedIndex()));
        int max=jComboBox1.getItemCount();
        int upperlimit=30;
        if(value!=max){
            value++;
            jComboBox1.setSelectedItem(value);
        }else{
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        // TODO add your handling code here:
        
        panel_Search search=new panel_Search();
        search.formload();
        int option=JOptionPane.showInternalConfirmDialog(this.getParent(),search, "Farmer Search",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
        if(option==JOptionPane.OK_OPTION){
            farmerid=search.getPatientId();
            if(!farmerid.equals("")){
                jCheckBox1.setSelected(false);
               collection_display(1,farmerid, dt1, dt2,0);
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
    }//GEN-LAST:event_jTextField1KeyTyped

public void collection_display(int val,String fid,Date date1,Date date2,int upperlimit) {
    try {
        String hql;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        switch(val){
            case 0:
                hql="SELECT c.id,c.farmerId,c.date, max(case when c.`session`='AM' then coalesce(c.`quantity`,0.0) end) as am," +
                    "max(case when c.`session`='PM' then coalesce(c.`quantity`,0.0) end) AS pm,c.attendant from dt_Dailycollection c WHERE c.date between :date1 and :date2 GROUP BY c.farmerId,Date(c.date) order by c.id asc ";
                break;  
                        
            case 1:
                hql="SELECT c.id,c.farmerId,c.date, max(case when c.`session`='AM' then coalesce(c.`quantity`,0.0) end) as am," +
                     "max(case when c.`session`='PM' then coalesce(c.`quantity`,0.0) end) AS pm,c.attendant from dt_Dailycollection c WHERE c.farmerId=:id AND c.date between :date1 and :date2 GROUP BY Day(c.date)order by date asc";
                break;
            default:
                hql="SELECT c.id,c.farmerId,c.date, max(case when c.`session`='AM' then coalesce(c.`quantity`,0.0) end) as am," +
                     "max(case when c.`session`='PM' then coalesce(c.`quantity`,0.0) end) AS pm,c.attendant from dt_Dailycollection c WHERE c.date between :date1 and :date2 GROUP BY c.farmerId,Date(c.date) order by c.id asc ";
                break;        
    }
        Query q = session.createSQLQuery(hql);
        q.setDate("date1", date1);
        q.setDate("date2", date2);        
        if(val==1)
            q.setParameter("id", fid);
        q.setFirstResult(upperlimit);
        q.setMaxResults(30);
        List <Object[]> resultList = q.list();
        displayResult(resultList);
        session.getTransaction().commit();
    } catch (HibernateException he) {
        he.printStackTrace();
    }
}
    
    private void displayResult(List<Object[]> resultList) {
    Vector tableData = new Vector();
    total=0.0;
    String header[]={"Record","id","Name","Date","AM","PM","Attendant",""};
    Vector<String> tableHeaders= new Vector<>();
    tableHeaders.addAll(Arrays.asList(header));
    for(Object[] farmer : resultList) {        
//        DtDailycollection collection = (DtDailycollection)o;
        Vector<Object> oneRow = new Vector<>();
        List col=Arrays.asList(farmer[0],
                               farmer[1],
                               sup.Farmer_name(sup.getValue(farmer[1])),
                               farmer[2],
                               farmer[3],
                               farmer[4],
                               farmer[5],
                               "delete");
//        total+=collection.getQuantity();
        oneRow.addAll(col);
        tableData.add(oneRow);
    }
    if(resultList.isEmpty()){
       farmer_model=new DefaultTableModel(new String[20][8],header);   
    }else
        farmer_model=new DefaultTableModel(tableData, tableHeaders);
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}