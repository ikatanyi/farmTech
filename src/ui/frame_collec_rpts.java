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
import custom_jPanels.GradientRoundRectButton;
import custom_jPanels.RoundRectGradientPanel;
import farmTech.util.HibernateUtil;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Kent
 */
public class frame_collec_rpts extends javax.swing.JInternalFrame {

    /**
     * Creates new form frame_collec_rpts
     */
    superCls sup=new superCls();
    clsReport rpt;
    clsDate dt=new clsDate();
    private DefaultTableModel farmer_model;
    private JLabel errorFields ;
    private String farmerid;
    Date dt1,dt2;
    private double rate=0.0,total=0.0;
    public frame_collec_rpts() {
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
        jLabel1 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jComboBox1 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtname = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setTitle("Milk Statements");
        setToolTipText("");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ox-cow.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel1.setText("From: ");

        jDateChooser1.setDate(new Date());
        jDateChooser1.setDateFormatString("dd-MM-yyyy");
        jDateChooser1.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jDateChooser1.setOpaque(false);
        jDateChooser1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser1PropertyChange(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel2.setText("To:");

        jDateChooser2.setDate(new Date());
        jDateChooser2.setDateFormatString("dd-MM-yyyy");
        jDateChooser2.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jDateChooser2.setOpaque(false);
        jDateChooser2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser2PropertyChange(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Date", "Am", "Pm", "Total", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setGridColor(new java.awt.Color(204, 204, 204));
        jScrollPane1.setViewportView(jTable1);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/arrow_right.gif"))); // NOI18N
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/arrow_left.gif"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel3.setText("Search Farmer:");

        txtname.setText("search ");
        txtname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtnameKeyReleased(evt);
            }
        });

        jCheckBox1.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jCheckBox1.setSelected(true);
        jCheckBox1.setText("All");
        jCheckBox1.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jCheckBox1.setOpaque(false);
        jCheckBox1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBox1StateChanged(evt);
            }
        });
        jCheckBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox1ItemStateChanged(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel5.setText("Farmer :");

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel6.setText("All");

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel7.setText("Total (Ltrs) :");

        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel8.setText("0.00");

        jButton3.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jButton3.setText("Print");
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
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator1)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(3, 3, 3)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCheckBox1)))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(jLabel5)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton3)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(jLabel8)))
                .addGap(7, 7, 7)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton1, jButton2, jComboBox1});

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtnameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnameKeyReleased
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
               jLabel6.setText(sup.Farmer_name(sup.getValue(farmerid)));
               SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    jTable1.getColumnModel().getColumn(4).setCellRenderer( new  labelRenderer());
                    ColumnResizer.adjustColumnPreferredWidths (jTable1);
                    jTable1.revalidate();
                }});
            }
            //            EnableFarmerFields(false);
        }
        txtname.setText("Search");
    }//GEN-LAST:event_txtnameKeyReleased

    private void jCheckBox1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBox1StateChanged
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jCheckBox1StateChanged

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
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
                    jTable1.getColumnModel().getColumn(4).setCellRenderer( new  labelRenderer());
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

    private void jDateChooser1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser1PropertyChange
        // TODO add your handling code here:
        dt1=sup.sqlDate(new SimpleDateFormat("dd-MM-yyyy").format(jDateChooser1.getDate()));
        dt2=sup.sqlDate(new SimpleDateFormat("dd-MM-yyyy").format(jDateChooser2.getDate()));
        if(farmerid==null||farmerid.equals(""))
           collection_display(0,"",dt1,dt2,0); 
        else
           collection_display(1,farmerid,dt1,dt2,0); 
        
        jTable1.setModel(farmer_model); 
               SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    jTable1.getColumnModel().getColumn(4).setCellRenderer( new  labelRenderer());
                    ColumnResizer.adjustColumnPreferredWidths (jTable1);
                    jTable1.revalidate();
                }});
    }//GEN-LAST:event_jDateChooser1PropertyChange

    private void jDateChooser2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser2PropertyChange
        // TODO add your handling code here:
        dt1=sup.sqlDate(new SimpleDateFormat("dd-MM-yyyy").format(jDateChooser1.getDate()));
        dt2=sup.sqlDate(new SimpleDateFormat("dd-MM-yyyy").format(jDateChooser2.getDate()));
        if(farmerid==null||farmerid.equals(""))
           collection_display(0,"",dt1,dt2,0); 
        else
           collection_display(1,farmerid,dt1,dt2,0);
    }//GEN-LAST:event_jDateChooser2PropertyChange

    private void jCheckBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox1ItemStateChanged
        // TODO add your handling code here:
         if(jCheckBox1.isSelected()){
            farmerid=null;
            collection_display(0,farmerid, dt1, dt2,0);
               jTable1.setModel(farmer_model); 
                jLabel6.setText("<HTML><FONT COLOR = BLUE>All Farmers</FONT></HTML>");
               SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    jTable1.getColumnModel().getColumn(4).setCellRenderer( new  labelRenderer());
                    ColumnResizer.adjustColumnPreferredWidths (jTable1);
                    jTable1.revalidate();
                }});
        }
            
        else{
            errorFields = new JLabel("<HTML><FONT COLOR = BLUE>please select a farmer</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields,"DMS",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jCheckBox1ItemStateChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        HashMap jasperParameter=new HashMap();
        dt1=sup.sqlDate(new SimpleDateFormat("dd-MM-yyyy").format(jDateChooser1.getDate()));
        dt2=sup.sqlDate(new SimpleDateFormat("dd-MM-yyyy").format(jDateChooser2.getDate()));
        jasperParameter.put("farmer", jLabel6.getText());
        jasperParameter.put("from_date", dt1);
        jasperParameter.put("to_date", dt2);
        
        TableModel item_model=(TableModel)jTable1.getModel();
               
       rpt=new clsReport();
       rpt.table_Reporter(jasperParameter, "milk_statement.jasper", new JRTableModelDataSource(item_model));
    }//GEN-LAST:event_jButton3ActionPerformed

    public void formload(){
        dt1=sup.sqlDate(new SimpleDateFormat("dd-MM-yyyy").format(jDateChooser1.getDate()));
        dt2=sup.sqlDate(new SimpleDateFormat("dd-MM-yyyy").format(jDateChooser2.getDate()));
       collection_display(0,"",dt1,dt2,0);
       jTable1.setModel(farmer_model);
       SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                    jTable1.getColumnModel().getColumn(4).setCellRenderer( new  labelRenderer());
                    ColumnResizer.adjustColumnPreferredWidths (jTable1);
                    jTable1.revalidate();
                 }});
//       jLabel14.setText(String.valueOf(total));
       jComboBox1.setModel(new superCls().pagination("DtDailycollection"));
    }
   public void collection_display(int val,String fid,Date date1,Date date2,int upperlimit) {
    try {
        String hql;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        switch(val){
            case 0:
                hql="SELECT c.date, IFNULL(SUM(case when c.`session`='AM' then c.`quantity` end),0.00) as am ,"
                        + "IFNULL(SUM(case when c.`session`='PM' then c.`quantity` end),0.00) AS pm,c.attendant from dt_Dailycollection c WHERE c.date between :date1 and :date2 GROUP BY Date(c.date) order by c.date asc ";
                break;  
                        
            case 1:
                hql="SELECT c.date, max(case when c.`session`='AM' then coalesce(c.`quantity`,0.0) end) as am," +
                     "max(case when c.`session`='PM' then coalesce(c.`quantity`,0.0) end) AS pm,c.attendant from dt_Dailycollection c WHERE c.farmerId=:id AND c.date between :date1 and :date2 GROUP BY Day(c.date)order by date asc";
                break;
            default:
                hql="SELECT c.date, IFNULL(SUM(case when c.`session`='AM' then c.`quantity` end),0.00) as am ,"
                        + "IFNULL(SUM(case when c.`session`='PM' then c.`quantity` end),0.00) AS pm,c.attendant from dt_Dailycollection c WHERE c.date between :date1 and :date2 GROUP BY Date(c.date) order by c.date asc ";
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
    String header[]={"Date","Am","Pm","Total",""};
    Vector<String> tableHeaders= new Vector<>();
    tableHeaders.addAll(Arrays.asList(header));
    for(Object[] farmer : resultList) {        
//        DtDailycollection collection = (DtDailycollection)o;
        Vector<Object> oneRow = new Vector<>();
        List col=Arrays.asList(
                               farmer[0],
                               farmer[1],
                               farmer[2],
                               Double.parseDouble(sup.getCostValue(farmer[1]))+Double.parseDouble(sup.getCostValue(farmer[2])),
                               "delete");
        total+=Double.parseDouble(sup.getCostValue(farmer[1]))+Double.parseDouble(sup.getCostValue(farmer[2]));
        oneRow.addAll(col);
        tableData.add(oneRow);
    }
    if(resultList.isEmpty()){
       farmer_model=new DefaultTableModel(new String[20][8],header);   
    }else
        farmer_model=new DefaultTableModel(tableData, tableHeaders);
    jLabel8.setText(String.valueOf(total));
}
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox jComboBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtname;
    // End of variables declaration//GEN-END:variables
}
