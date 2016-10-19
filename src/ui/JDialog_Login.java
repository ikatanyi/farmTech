/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import farm.entity.DtCompany;
import farm.entity.DtUsers;
import farmTech.util.HibernateUtil;
import java.util.Iterator;
import java.util.List;
import com.jtattoo.plaf.graphite.*;
import custom_jPanels.RoundRectGradientPanel;
import custom_jPanels.SimpleGradientPanel;
import java.awt.Container;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.GUIUtils;
import java.awt.*;

/**
 *
 * @author KENT
 */
public class JDialog_Login extends javax.swing.JDialog {
    private JLabel errorFields;
    adminFrm admin=new adminFrm();
    signup signup=new signup();
    /**
     * Creates new form JDialog_Login
     * @param parent
     * @param modal
     */
    public JDialog_Login(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        formload();
         GUIUtils.centerOnScreen(JDialog_Login.this);
        jPanel2.add(admin);
        jPanel2.add(signup);
        signup.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new RoundRectGradientPanel();
        jPanel1 = new SimpleGradientPanel(new Color(153,51,0),new Color(255,255,255));
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblStreetAdd = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/ox-cow.png")));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Username :");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Password :");

        jTextField1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jTextField1.setMinimumSize(new java.awt.Dimension(4, 22));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton1.setText("Login");
        jButton1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPasswordField1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPasswordField1.setMinimumSize(new java.awt.Dimension(4, 22));
        jPasswordField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(80, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(156, 156, 156))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jPasswordField1, jTextField1});

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel4.setText("Sign in or Create a New Account ");

        jLabel5.setForeground(new java.awt.Color(153, 51, 0));
        jLabel5.setText("Create an account");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel5MouseExited(evt);
            }
        });
        jLabel5.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jLabel5MouseMoved(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel6.setText("Admin Login");

        jLabel7.setForeground(new java.awt.Color(153, 51, 0));
        jLabel7.setText("Click Here");
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel7MouseExited(evt);
            }
        });
        jLabel7.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jLabel7MouseMoved(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("Dairy Management System");

        lblStreetAdd.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addGap(34, 34, 34)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(108, 108, 108)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(32, 32, 32))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblStreetAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblStreetAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(34, 34, 34))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     *
     */
    public void login(){
         String username= new String(jTextField1.getText());
         String pass= new String( jPasswordField1.getPassword());

        if(username.equals("")||pass.equals(""))
        {
            jButton1.setEnabled(false);
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>You must enter a username and password to login.</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields);
            jTextField1.setText("");
            jPasswordField1.setText("");
            jButton1.setEnabled(true);
            this.setVisible(true);
        }
        else{
            final String Idno=login(pass,username);
            if( Idno!=null&& !Idno.equals("")){
                this.dispose();
                java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {                  
                    Application app=  new Application(Idno);
                    app.setVisible(true);
                  }
                 });
            }else{
               errorFields = new JLabel("<HTML><FONT COLOR = BLUE>Please Verify That your Server is well setup and Running also IP is correctly Configured</FONT></HTML>");
               JOptionPane.showMessageDialog(null,errorFields,"Dairy Management System",JOptionPane.INFORMATION_MESSAGE);
            }

        }
    }
    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
        login();
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
       login();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     *
     * @param pass
     * @param username
     * @return
     */
    public String login(String pass,String username){
        String Idno="";
        Session session = HibernateUtil.getSessionFactory().openSession();
      try {        
        session.beginTransaction();
        Query q = session.createQuery("FROM DtUsers user WHERE user.password=:pass AND user.username=:user");
        q.setParameter("pass", pass);
        q.setParameter("user", username);
        List auth=q.list();
        Iterator iterator=auth.iterator();
        if(iterator.hasNext()){
          DtUsers user=(DtUsers)iterator.next();
          Idno=user.getIdNo();
        }
        session.getTransaction().commit();
    } catch (HibernateException he) {
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>'"+he.getMessage()+"'</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields,"Dairy Management System",JOptionPane.ERROR_MESSAGE);
        
    }finally {
         session.close(); 
      }
      return Idno;
    }
    
    private void jPasswordField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField1ActionPerformed
        // TODO add your handling code here:
        login();
    }//GEN-LAST:event_jPasswordField1ActionPerformed

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        Container pane=signup.getContentPane();
        pane.setVisible(true);
        JOptionPane.showMessageDialog(this,pane,"Create a new Account: Dairy Management System",JOptionPane.PLAIN_MESSAGE);

    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseExited
        // TODO add your handling code here:
        jLabel5.setText("<html><body><p><font >Create an Account</font></p></body></html>");
    }//GEN-LAST:event_jLabel5MouseExited

    private void jLabel5MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseMoved
        // TODO add your handling code here:
        jLabel5.setText("<html><body><p><font ><u>Create an Account</u></font></p></body></html>");
    }//GEN-LAST:event_jLabel5MouseMoved

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:
        JPasswordField pswd=new JPasswordField();
        Object[] message={"Enter Password:",pswd};
        int option=JOptionPane.showConfirmDialog(null, message, "Dairy Management System",JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE);
        //        String msg="This is a notification that you hava an appointent tomorrow: DOCTOR ON DUTY: Dr."+doctor;
        while(option==JOptionPane.OK_OPTION){
            String passwd=new String(pswd.getPassword());
            if(passwd.equals("")){
                JOptionPane.showMessageDialog(null,"Please provide a Password!","Dairy Management system",JOptionPane.WARNING_MESSAGE);
                option=JOptionPane.showConfirmDialog(null, message, "Dairy Management System",JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE);
            }else{
                if(passwd.equals("admin1234")){
//                    AdminpaneFrame admin=new AdminpaneFrame();
                    admin.setClosable(false);
                    admin.setVisible(true);
//                    JPopupMenu menu=new JPopupMenu();
//                    menu.add(admin);
//                    menu.setVisible(true);
                    JOptionPane.showConfirmDialog(this, admin,"Dairy Management system: Administrator Database By_Pass Panel",JOptionPane.PLAIN_MESSAGE);//MessageDialog(null,admin,"Dairy Management system",JOptionPane.PLAIN_MESSAGE);
                    break;
                }else{
                    JOptionPane.showMessageDialog(null,"Wrong Password!","Dairy Management system",JOptionPane.WARNING_MESSAGE);
                    option=JOptionPane.showConfirmDialog(null, message, "Dairy Management System",JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseExited
        jLabel7.setText("<html><body><p><font >Click Here</font></p></body></html>");
    }//GEN-LAST:event_jLabel7MouseExited

    private void jLabel7MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseMoved
        // TODO add your handling code here:
        jLabel7.setText("<html><body><p><font ><u>Click Here</u></font></p></body></html>");
    }//GEN-LAST:event_jLabel7MouseMoved

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                String lnf=info.getName();
                if ("Nimbus1".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }else{
//                    GraphiteLookAndFeel.setTheme("aquathemepack");
                    javax.swing.UIManager.setLookAndFeel(new GraphiteLookAndFeel());
//                    TextureLookAndFeel.setTheme("aquathemepack");
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | NullPointerException| IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            JOptionPane.showMessageDialog(null,ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDialog_Login dialog = new JDialog_Login(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    
    
    private void formload() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
    try {        
            tx=session.beginTransaction();        
            Query q = session.createQuery("FROM DtCompany company WHERE company.id=:id");
            q.setParameter("id", 1);
            List results=q.list();
            Iterator iterator=results.iterator();
        if(iterator.hasNext()){            
            DtCompany details=(DtCompany)iterator.next();
            jLabel8.setText(details.getCompanyName());
            lblStreetAdd.setText(details.getSaddress());
        }
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
              errorFields = new JLabel("<HTML><FONT COLOR = BLUE>Please Verify That your Server is well setup and Running also IP is correctly Configured</FONT></HTML>");
              JOptionPane.showMessageDialog(null,errorFields,"Dairy Management System",JOptionPane.INFORMATION_MESSAGE);
      }finally {
         session.flush();
         session.close(); 
      }
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblStreetAdd;
    // End of variables declaration//GEN-END:variables
}