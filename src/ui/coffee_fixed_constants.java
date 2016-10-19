/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ui;

import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import classes.rowcount;
import classes.superCls;
import custom_jPanels.BeveledGradientPanel;
import farm.entity.DtCRate;
import farm.entity.DtCoffeCollection;
import farmTech.util.HibernateUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.hibernate.CacheMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Kent
 */
public class coffee_fixed_constants extends javax.swing.JInternalFrame {

    /**
     * Creates new form coffee_fixed_constants
     */
    private AutoCompleteSupport type=null;
    private JLabel errorFields;
    DtCRate ctype;
    superCls sup=new superCls();
    private int[] id;
    public coffee_fixed_constants() {
        initComponents();
    }
    
    public void formload(){
         try{
         if(type==null||!type.isInstalled()){
            type=AutoCompleteSupport.install(jComboBox1, GlazedLists.eventListOf(sup.getElements(ctypes())));
                        
         }
         }catch(Exception ex){
             errorFields = new JLabel("<HTML><FONT COLOR = BLUE>'"+ex.getMessage()+"'</FONT></HTML>");
             JOptionPane.showMessageDialog(null,errorFields,"DMS",JOptionPane.ERROR_MESSAGE);                  
         }
    }
    
    private DefaultComboBoxModel ctypes () {                 
       int i=0;
       DefaultComboBoxModel tmodel =new DefaultComboBoxModel();
       org.hibernate.Session session = HibernateUtil.getSessionFactory().openSession();
      try {        
           session.beginTransaction();
           Query q = session.createQuery("from DtCRate");
           List auth=q.list();
           Iterator iterator=auth.iterator();
           id=new int[auth.size()];
           while(iterator.hasNext()){ 
                   ctype=(DtCRate)iterator.next();
                   tmodel.addElement(ctype.getType()); 
                   id[i]=ctype.getId();
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

    public void CtypeSearch(int id ){
       Session session = HibernateUtil.getSessionFactory().openSession();
       Transaction tx = null;
      try{
         tx = session.beginTransaction();
          ctype= (DtCRate)session.get(DtCRate.class, id);
          setfields();
          tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
               errorFields = new JLabel("<HTML><FONT COLOR = Blue>'"+e.getMessage()+"'</FONT></HTML>");
               JOptionPane.showMessageDialog(null,errorFields,"DMS",JOptionPane.ERROR_MESSAGE);
      }finally {
         session.close(); 
      }
   }
    
    public void setfields(){
        txtid.setText(sup.getIntValue(ctype.getCode()));
        txtrate.setText(sup.getCostValue(ctype.getCRate()));
    }
    
    public boolean save(double CRate, String type, String code, String date){
      Session session = HibernateUtil.getSessionFactory().openSession();      
      Transaction tx = null;
      boolean state=false;
      try{          
            DtCRate crate=new DtCRate(CRate, type, code, sup.sqlDate(date));           
            tx = session.beginTransaction();  
            Query q = session.createQuery("from DtCRate where type=:type");
            q.setParameter("type", type);
            List auth=q.list();
            Iterator iterator=auth.iterator();
            if(!iterator.hasNext()){
               session.save(crate);
                errorFields = new JLabel("<HTML><FONT COLOR = Blue>Coffee Type saved successfully</FONT></HTML>");
            }else{
                DtCRate crat=(DtCRate)iterator.next();
                crat.setCRate(CRate);
                crat.setCode(code);
                crat.setDate(sup.sqlDate(date));
                session.update(crat);
                errorFields = new JLabel("<HTML><FONT COLOR = Blue>Coffee Type updated successfully</FONT></HTML>");
            }
            tx.commit();
            JOptionPane.showMessageDialog(null,errorFields,"DMS",JOptionPane.INFORMATION_MESSAGE);
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
    
    public boolean update_rate(int month,  int year,String type, double rate){
      Session session = HibernateUtil.getSessionFactory().openSession();
      Transaction tx = null;
      String farmerID = null;
      boolean state=false;
      try{
         tx = session.beginTransaction();
         Query q = session.createQuery("from DtCoffeCollection cof where Month(cof.date)=:month and Year(cof.date)=:year and cof.type=:type");
         q.setParameter("month", month);
         q.setParameter("year", year);
         q.setParameter("type", type);
         q.setCacheMode(CacheMode.IGNORE);
         q.scroll(ScrollMode.FORWARD_ONLY);
         ScrollableResults auth=q.scroll();             
          int i=0;     
         while(auth.next()){
             DtCoffeCollection collec = (DtCoffeCollection)auth.get(0);
             collec.setRate(rate);
             session.update(collec);
             if ( ++i % 20 == 0 ) {
                //flush a batch of updates and release memory:
                session.flush();
                session.clear();
              }
            errorFields = new JLabel("<HTML><FONT COLOR = BLUE>Transaction completed successfully</FONT></HTML>");
         }
         tx.commit();
         state=true;         
         JOptionPane.showMessageDialog(null,errorFields,"DMS",JOptionPane.INFORMATION_MESSAGE);
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
             errorFields = new JLabel("<HTML><FONT COLOR = BLUE>'"+e.getMessage()+"'</FONT></HTML>");
             JOptionPane.showMessageDialog(null,errorFields,"DMS",JOptionPane.INFORMATION_MESSAGE);
      }finally {
         session.close(); 
      }
      return state;
   }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new BeveledGradientPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        txtrate = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jMonthChooser1 = new com.toedter.calendar.JMonthChooser();
        jButton2 = new javax.swing.JButton();
        jYearChooser1 = new com.toedter.calendar.JYearChooser();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setTitle("Coffee  Buying rate");
        setToolTipText("");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ox-cow.png"))); // NOI18N

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/coffee.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Type :");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Buying Rate (Ksh.) :");

        jComboBox1.setEditable(true);
        jComboBox1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        txtrate.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtrate.setToolTipText("");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Id:");

        txtid.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtid.setToolTipText("");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Choose Month :");

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton2.setText("Delete");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2))
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtid, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jMonthChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jYearChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(txtrate, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(80, 80, 80))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtid, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtrate, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(4, 4, 4)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jMonthChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jYearChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(83, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jComboBox1, jMonthChooser1, txtid, txtrate});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton1, jButton2});

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String code="";
        double CRate=Double.parseDouble(sup.getCostValue(txtrate.getText()));
        String type=sup.getValue(jComboBox1.getSelectedItem());
        code=!txtid.getText().equals("")? code:String.valueOf(new rowcount().row_count(DtCRate.class)+1);
        String date=new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        int month=jMonthChooser1.getMonth()+1;
        int year=jYearChooser1.getYear();
        if(!type.equals("")&& CRate!=0.0){
          if(this.save(CRate, type, code, date))
              this.update_rate(month, year, type,CRate);
        }
        else{
          errorFields = new JLabel("<HTML><FONT COLOR = Blue>Please make sure Type and rate fields are filled</FONT></HTML>"); 
          JOptionPane.showMessageDialog(null,errorFields,"DMS",JOptionPane.WARNING_MESSAGE);
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        // TODO add your handling code here:
        int index=jComboBox1.getSelectedIndex();
        if(index!=-1){
            int code=id[index];
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private com.toedter.calendar.JMonthChooser jMonthChooser1;
    private javax.swing.JPanel jPanel1;
    private com.toedter.calendar.JYearChooser jYearChooser1;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtrate;
    // End of variables declaration//GEN-END:variables
}
