/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import ca.odell.glazedlists.swing.AutoCompleteSupport;
import classes.clsDate;
import classes.rowcount;
import classes.superCls;
import custom_jPanels.RoundRectGradientPanel;
import custom_jPanels.SimpleGradientPanel;
import farm.entity.DtFarmerdetails;
import farm.entity.DtInsurances;
import farm.entity.DtRoute;
import farmTech.util.HibernateUtil;
import java.awt.CardLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author KENT
 */
public class New_FJFrame extends javax.swing.JInternalFrame {

    /**
     * Creates new form New_FJFrame
     */
    superCls sup=new superCls();
    private JLabel errorFields;
    private String user = "";
    private Object[] id;
    private Object[] insurance_id;
    private Object[] schemes_id;
    private int scheme_id;
    private AutoCompleteSupport type=null,sp=null,bank=null,brnch=null,centres=null;   
    
    public New_FJFrame() {
        initComponents();
               
    }

    
    
   public void formload(String userid){
        this.user = userid;
        this.txtpatientid.setText(String.valueOf(new rowcount().row_count(DtFarmerdetails.class) + 1));
        this.jComboBox1.setModel(insurances());    
        this.jComboBox4.setModel(centers());
   }
   
  private DefaultComboBoxModel payment_type()
  {
    int i = 0;
    DefaultComboBoxModel tmodel = new DefaultComboBoxModel();
    Session session = HibernateUtil.getSessionFactory().openSession();
    try
    {
      session.beginTransaction();
      Query q = session.createQuery("SELECT distinct farmer.paymentType from DtFarmerdetails farmer where farmer.paymentType <>''");
      List auth = q.list();
      Iterator iterator = auth.iterator();
      while (iterator.hasNext())
      {
        String ptype = (String)iterator.next();
        tmodel.addElement(ptype);
        i++;
      }
      session.getTransaction().commit();
    }
    catch (HibernateException he)
    {
      this.errorFields = new JLabel("<HTML><FONT COLOR = Blue>'" + he.getStackTrace() + "'</FONT></HTML>");
      JOptionPane.showMessageDialog(null, this.errorFields, "DMS", 0);
    }
    finally
    {
      session.close();
    }
    return tmodel;
  }
  
  private DefaultComboBoxModel centers()
  {
    int i = 0;
    DefaultComboBoxModel tmodel = new DefaultComboBoxModel();
    Session session = HibernateUtil.getSessionFactory().openSession();
    try
    {
      session.beginTransaction();
      Query q = session.createQuery("SELECT distinct r.centerNo,r.name from DtRoute r");
      List auth = q.list();
      Iterator iterator = auth.iterator();
      this.id = new Object[auth.size()];
      for (Object o : auth)
      {
        Object[] ptype = (Object[])o;
        tmodel.addElement(ptype[1]);
        this.id[i] = ptype[0];
        i++;
      }
      session.getTransaction().commit();
    }
    catch (HibernateException he)
    {
      this.errorFields = new JLabel("<HTML><FONT COLOR = Blue>'" + he.getStackTrace() + "'</FONT></HTML>");
      JOptionPane.showMessageDialog(null, this.errorFields, "DMS", 0);
    }
    finally
    {
      session.close();
    }
    return tmodel;
  }
  
  private String getCenter(String id)
  {
    int i = 0;
    String center = "";
    Session session = HibernateUtil.getSessionFactory().openSession();
    try
    {
      session.beginTransaction();
      Query q = session.createQuery("from DtRoute r where r.centerNo=:id");
      q.setParameter("id", id);
      List auth = q.list();
      Iterator iterator = auth.iterator();
      if (iterator.hasNext())
      {
        DtRoute ptype = (DtRoute)iterator.next();
        center = ptype.getName();
        i++;
      }
      session.getTransaction().commit();
    }
    catch (HibernateException he)
    {
      this.errorFields = new JLabel("<HTML><FONT COLOR = Blue>'" + he.getStackTrace() + "'</FONT></HTML>");
      JOptionPane.showMessageDialog(null, this.errorFields, "DMS", 0);
    }
    finally
    {
      session.close();
    }
    return center;
  }
  
  private DefaultComboBoxModel bank()
  {
    int i = 0;
    DefaultComboBoxModel tmodel = new DefaultComboBoxModel();
    Session session = HibernateUtil.getSessionFactory().openSession();
    try
    {
      session.beginTransaction();
      Query q = session.createQuery("SELECT distinct farmer.bankName from DtFarmerdetails farmer where farmer.bankName <>''");
      List auth = q.list();
      Iterator iterator = auth.iterator();
      while (iterator.hasNext())
      {
        String bankname = (String)iterator.next();
        tmodel.addElement(bankname);
        i++;
      }
      session.getTransaction().commit();
    }
    catch (HibernateException he)
    {
      this.errorFields = new JLabel("<HTML><FONT COLOR = Blue>'" + he.getMessage() + "'</FONT></HTML>");
      JOptionPane.showMessageDialog(null, this.errorFields, "DMS", 0);
    }
    finally
    {
      session.close();
    }
    return tmodel;
  }
  
  private DefaultComboBoxModel branch()
  {
    int i = 0;
    DefaultComboBoxModel tmodel = new DefaultComboBoxModel();
    Session session = HibernateUtil.getSessionFactory().openSession();
    try
    {
      session.beginTransaction();
      Query q = session.createQuery("SELECT distinct farmer.branch from DtFarmerdetails farmer where farmer.branch <>''");
      List auth = q.list();
      Iterator iterator = auth.iterator();
      while (iterator.hasNext())
      {
        String branch = (String)iterator.next();
        tmodel.addElement(branch);
        i++;
      }
      session.getTransaction().commit();
    }
    catch (HibernateException he)
    {
      this.errorFields = new JLabel("<HTML><FONT COLOR = Blue>'" + he.getMessage() + "'</FONT></HTML>");
      JOptionPane.showMessageDialog(null, this.errorFields, "DMS", 0);
    }
    finally
    {
      session.close();
    }
    return tmodel;
  }
  
  private DefaultComboBoxModel provider()
  {
    int i = 0;
    DefaultComboBoxModel tmodel = new DefaultComboBoxModel();
    Session session = HibernateUtil.getSessionFactory().openSession();
    try
    {
      session.beginTransaction();
      Query q = session.createQuery("SELECT distinct farmer.serviceProvider from DtFarmerdetails farmer where farmer.serviceProvider <>''");
      List auth = q.list();
      Iterator iterator = auth.iterator();
      while (iterator.hasNext())
      {
        String provider = (String)iterator.next();
        tmodel.addElement(provider);
        i++;
      }
      session.getTransaction().commit();
    }
    catch (HibernateException he)
    {
      this.errorFields = new JLabel("<HTML><FONT COLOR = Blue>'" + he.getMessage() + "'</FONT></HTML>");
      JOptionPane.showMessageDialog(null, this.errorFields, "DMS", 0);
    }
    finally
    {
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

        jPanel1 = new javax.swing.JPanel();
        jPanel72 = new SimpleGradientPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel73 = new RoundRectGradientPanel();
        jPanel68 = new SimpleGradientPanel();
        jLabel201 = new javax.swing.JLabel();
        jLabel203 = new javax.swing.JLabel();
        jLabel200 = new javax.swing.JLabel();
        jLabel210 = new javax.swing.JLabel();
        jLabel211 = new javax.swing.JLabel();
        jLabel246 = new javax.swing.JLabel();
        jLabel248 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtpatientid = new javax.swing.JTextField();
        txtfirstname = new javax.swing.JTextField();
        txtfid = new javax.swing.JTextField();
        cmbsex = new javax.swing.JComboBox();
        jComboBox4 = new javax.swing.JComboBox();
        txtmno = new javax.swing.JTextField();
        txtlastname = new javax.swing.JTextField();
        txtAge = new javax.swing.JTextField();
        cmbMstatus = new javax.swing.JComboBox();
        jLabel204 = new javax.swing.JLabel();
        jLabel205 = new javax.swing.JLabel();
        txtadd = new javax.swing.JTextField();
        jLabel208 = new javax.swing.JLabel();
        txtphone = new javax.swing.JTextField();
        jLabel212 = new javax.swing.JLabel();
        txtSpouseID = new javax.swing.JTextField();
        jLabel213 = new javax.swing.JLabel();
        txtSpouseFname = new javax.swing.JTextField();
        txtSpuseLname = new javax.swing.JTextField();
        jLabel207 = new javax.swing.JLabel();
        txtzip = new javax.swing.JTextField();
        jLabel206 = new javax.swing.JLabel();
        jLabel209 = new javax.swing.JLabel();
        txtemail = new javax.swing.JTextField();
        cmbPatientcity = new javax.swing.JComboBox();
        cmbfarmerstate = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        lbl_instCode = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        txt_acc = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel74 = new RoundRectGradientPanel();
        jPanel71 = new SimpleGradientPanel();
        jLabel229 = new javax.swing.JLabel();
        jLabel224 = new javax.swing.JLabel();
        txtZip = new javax.swing.JTextField();
        jLabel228 = new javax.swing.JLabel();
        jLabel216 = new javax.swing.JLabel();
        cmbInState = new javax.swing.JComboBox();
        jLabel223 = new javax.swing.JLabel();
        cmbIntype = new javax.swing.JComboBox();
        jLabel219 = new javax.swing.JLabel();
        txtplan = new javax.swing.JTextField();
        dtfrom = new com.toedter.calendar.JDateChooser();
        jLabel230 = new javax.swing.JLabel();
        jLabel221 = new javax.swing.JLabel();
        jLabel222 = new javax.swing.JLabel();
        jLabel217 = new javax.swing.JLabel();
        txtInPhone = new javax.swing.JTextField();
        jLabel218 = new javax.swing.JLabel();
        txtInGroup = new javax.swing.JTextField();
        jLabel227 = new javax.swing.JLabel();
        txttype = new javax.swing.JTextField();
        txtInLastname = new javax.swing.JTextField();
        jLabel226 = new javax.swing.JLabel();
        txtInFirstname = new javax.swing.JTextField();
        jLabel220 = new javax.swing.JLabel();
        jLabel231 = new javax.swing.JLabel();
        txtInemail = new javax.swing.JTextField();
        txtAdd = new javax.swing.JTextField();
        cmbIncity = new javax.swing.JComboBox();
        Dtto = new com.toedter.calendar.JDateChooser();
        jLabel225 = new javax.swing.JLabel();
        txtinId = new javax.swing.JTextField();
        dtInDOB = new com.toedter.calendar.JDateChooser();
        jLabel215 = new javax.swing.JLabel();
        txtRelation = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jToolBar1 = new javax.swing.JToolBar();
        btnExit = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnNewPatient = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setResizable(true);
        setTitle("Farmer Registration");
        setToolTipText("");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ox-cow.png"))); // NOI18N

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.CardLayout());

        jPanel72.setName("card15"); // NOI18N

        jPanel68.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel201.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel201.setText("Name :");

        jLabel203.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel203.setText("Age :");

        jLabel200.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel200.setText("FarmerID ");

        jLabel210.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel210.setText("Marital Status :");

        jLabel211.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel211.setText("Sex :");

        jLabel246.setForeground(new java.awt.Color(255, 0, 51));
        jLabel246.setText("*");

        jLabel248.setForeground(new java.awt.Color(255, 0, 51));
        jLabel248.setText("*");

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel3.setText("IdNo:");

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel5.setText("Center_Name:");

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel6.setText("Member no.");

        txtpatientid.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        txtfirstname.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        txtfid.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        cmbsex.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        cmbsex.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox4.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtmno.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        txtlastname.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        txtAge.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        cmbMstatus.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        cmbMstatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Single", "Married" }));

        jLabel204.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel204.setText("Address :");

        jLabel205.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel205.setText("Town");

        txtadd.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        jLabel208.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel208.setText("Phone :");

        txtphone.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        jLabel212.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel212.setText("Kin IdNo :");

        txtSpouseID.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        jLabel213.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel213.setText("Name :");

        txtSpouseFname.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        txtSpuseLname.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        jLabel207.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel207.setText("Code :");

        txtzip.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        jLabel206.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel206.setText("County");

        jLabel209.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel209.setText("Email :");

        txtemail.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        cmbPatientcity.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbfarmerstate.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("Farmer Personal Details");

        javax.swing.GroupLayout jPanel68Layout = new javax.swing.GroupLayout(jPanel68);
        jPanel68.setLayout(jPanel68Layout);
        jPanel68Layout.setHorizontalGroup(
            jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel68Layout.createSequentialGroup()
                .addGroup(jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel68Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel248, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel68Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel246)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel68Layout.createSequentialGroup()
                        .addComponent(jLabel210)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbMstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel68Layout.createSequentialGroup()
                        .addGroup(jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel211)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel201)
                            .addComponent(jLabel200))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel68Layout.createSequentialGroup()
                                .addComponent(txtpatientid, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtmno))
                            .addGroup(jPanel68Layout.createSequentialGroup()
                                .addComponent(txtfirstname, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtlastname, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel68Layout.createSequentialGroup()
                                .addGroup(jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbsex, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtfid))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel203)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtAge, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel213)
                            .addComponent(jLabel212)
                            .addComponent(jLabel208)
                            .addComponent(jLabel204)
                            .addComponent(jLabel205))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel68Layout.createSequentialGroup()
                                .addComponent(txtSpouseFname, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSpuseLname, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 247, Short.MAX_VALUE))
                            .addGroup(jPanel68Layout.createSequentialGroup()
                                .addGroup(jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtSpouseID, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtphone, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtadd, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbPatientcity, 0, 197, Short.MAX_VALUE))
                                .addGap(56, 56, 56)
                                .addGroup(jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel207)
                                    .addComponent(jLabel206)
                                    .addComponent(jLabel209))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtzip)
                                    .addComponent(txtemail)
                                    .addComponent(cmbfarmerstate, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addContainerGap())))
            .addGroup(jPanel68Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator2))
                .addContainerGap())
        );
        jPanel68Layout.setVerticalGroup(
            jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel68Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(2, 2, 2)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel68Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jLabel248))
                    .addGroup(jPanel68Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addGroup(jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel68Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtfirstname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtlastname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel201))
                                .addGap(4, 4, 4)
                                .addGroup(jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtfid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel203)
                                    .addComponent(txtAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel208)
                                    .addComponent(txtphone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel68Layout.createSequentialGroup()
                                .addGroup(jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtpatientid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel200)
                                    .addComponent(jLabel6)
                                    .addComponent(txtmno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel204)
                                    .addComponent(txtadd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel207)
                                    .addComponent(txtzip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel246))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel205)
                                    .addComponent(jLabel206)
                                    .addComponent(cmbPatientcity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbfarmerstate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel209)
                                    .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbsex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel211)
                            .addComponent(jLabel212)
                            .addComponent(txtSpouseID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel213)
                    .addComponent(txtSpouseFname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSpuseLname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel210)
                    .addComponent(cmbMstatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel2.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N

        jLabel4.setText("Payment Mode :");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbl_instCode.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        jButton1.setText("Add");

        jLabel8.setText("Branch Name :");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel9.setText("Acc/Phone No :");

        jLabel1.setText("Farmer payment Mode Details");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_acc, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_instCode, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addContainerGap(566, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(5, 5, 5)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_instCode, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txt_acc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel73Layout = new javax.swing.GroupLayout(jPanel73);
        jPanel73.setLayout(jPanel73Layout);
        jPanel73Layout.setHorizontalGroup(
            jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel68, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel73Layout.setVerticalGroup(
            jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel73Layout.createSequentialGroup()
                .addComponent(jPanel68, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Farmer", jPanel73);

        jLabel229.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel229.setText("InsuredCity :");

        jLabel224.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel224.setText("InsuredFirstName :");

        txtZip.setText("+256");

        jLabel228.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel228.setText("InsuredPhone :");

        jLabel216.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel216.setText("InsuranceType :");

        cmbInState.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Uganda", "Kenya", "Tanzania", "Rwanda", "Ethiopia", "Sudan", "S.Sudan", "Djibouti", "Eritrea", "Somalia" }));

        jLabel223.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel223.setText("PolicyGroup :");

        cmbIntype.setEditable(true);
        cmbIntype.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "medical" }));

        jLabel219.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel219.setText("InsurancePlanName :");

        txtplan.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        dtfrom.setDate(new Date());
        dtfrom.setDateFormatString("dd-MM-yyyy");

        jLabel230.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel230.setText("InsuredState :");

        jLabel221.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel221.setText("InsuredType :");

        jLabel222.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel222.setText("InsuredEmail :");

        jLabel217.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel217.setText("PolicyCoverageFromDate :");

        txtInPhone.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        jLabel218.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel218.setText("InsuranceID :");

        txtInGroup.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        jLabel227.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel227.setText("InsuredAddress :");

        txttype.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        txtInLastname.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        jLabel226.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel226.setText("InsuredDOB :");

        txtInFirstname.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        jLabel220.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel220.setText("PolicyCoverageToDate :");

        jLabel231.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel231.setText("InsuredZip :");

        txtInemail.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        txtAdd.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        cmbIncity.setEditable(true);
        cmbIncity.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Entebbe", "Kampala", "Aswa" }));

        Dtto.setDate(new Date());
        Dtto.setDateFormatString("dd-MM-yyyy");

        jLabel225.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel225.setText("InsuredLastName :");

        txtinId.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        dtInDOB.setDate(new Date());

        jLabel215.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel215.setText("Insured Relationship To Farmer:");

        txtRelation.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        javax.swing.GroupLayout jPanel71Layout = new javax.swing.GroupLayout(jPanel71);
        jPanel71.setLayout(jPanel71Layout);
        jPanel71Layout.setHorizontalGroup(
            jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel71Layout.createSequentialGroup()
                .addGroup(jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel71Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel218)
                            .addComponent(jLabel219)
                            .addComponent(jLabel217)
                            .addComponent(jLabel229)
                            .addComponent(jLabel221)))
                    .addGroup(jPanel71Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel224))
                    .addGroup(jPanel71Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel226))
                    .addGroup(jPanel71Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel227))
                    .addGroup(jPanel71Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel228)
                            .addComponent(jLabel215))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtRelation, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                    .addComponent(txtInPhone, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                    .addComponent(txtAdd, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                    .addComponent(dtInDOB, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                    .addComponent(cmbIncity, 0, 127, Short.MAX_VALUE)
                    .addComponent(dtfrom, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                    .addComponent(txtplan, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                    .addComponent(txtinId, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                    .addComponent(txttype)
                    .addComponent(txtInFirstname))
                .addGap(12, 12, 12)
                .addGroup(jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel225)
                    .addComponent(jLabel222)
                    .addComponent(jLabel216)
                    .addComponent(jLabel223)
                    .addComponent(jLabel220)
                    .addComponent(jLabel230)
                    .addComponent(jLabel231))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtInLastname, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtInemail, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtZip, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbInState, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Dtto, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtInGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbIntype, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(294, Short.MAX_VALUE))
        );

        jPanel71Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {Dtto, cmbInState, cmbIntype, txtInGroup, txtInLastname, txtInemail, txtZip});

        jPanel71Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cmbIncity, dtInDOB, dtfrom, txtAdd, txtInPhone, txtRelation, txtinId, txtplan});

        jPanel71Layout.setVerticalGroup(
            jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel71Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtinId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbIntype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel71Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel218)
                            .addComponent(jLabel216))))
                .addGap(7, 7, 7)
                .addGroup(jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel219, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtplan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel71Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel223))
                    .addComponent(txtInGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dtfrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Dtto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel71Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel217)
                            .addComponent(jLabel220))))
                .addGap(5, 5, 5)
                .addGroup(jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel230, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cmbIncity, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel229, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cmbInState, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel231, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtZip, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel221, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txttype, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(7, 7, 7)
                .addGroup(jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel224, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel222, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtInemail, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtInFirstname, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(6, 6, 6)
                .addGroup(jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel226, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dtInDOB, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel225, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtInLastname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel71Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel227))
                    .addComponent(txtAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel71Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel228))
                    .addComponent(txtInPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel71Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel215))
                    .addGroup(jPanel71Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(txtRelation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jPanel71Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {dtInDOB, txtAdd, txtInPhone, txtRelation, txtinId, txtplan});

        jPanel71Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {Dtto, cmbInState, cmbIntype, txtInGroup, txtInLastname, txtInemail, txtZip});

        javax.swing.GroupLayout jPanel74Layout = new javax.swing.GroupLayout(jPanel74);
        jPanel74.setLayout(jPanel74Layout);
        jPanel74Layout.setHorizontalGroup(
            jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel74Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel71, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel74Layout.setVerticalGroup(
            jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel74Layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addComponent(jPanel71, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );

        jTabbedPane1.addTab("Insurance", jPanel74);

        jToolBar1.setRollover(true);

        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        jToolBar1.add(btnExit);

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jToolBar1.add(btnSave);

        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        jToolBar1.add(btnEdit);

        btnNewPatient.setText("Add New");
        btnNewPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewPatientActionPerformed(evt);
            }
        });
        jToolBar1.add(btnNewPatient);

        javax.swing.GroupLayout jPanel72Layout = new javax.swing.GroupLayout(jPanel72);
        jPanel72.setLayout(jPanel72Layout);
        jPanel72Layout.setHorizontalGroup(
            jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel72Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(5, 5, 5))
        );
        jPanel72Layout.setVerticalGroup(
            jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel72Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel72, "card15");

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // TODO add your handling code here:
        CardLayout cardmanager=(CardLayout)(jPanel1.getLayout());
//        cardmanager.show(jPanel1,jPanel2.getName());
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
    String CenterNo = "1";
    String farmerId = this.txtpatientid.getText().trim();
    String mno = this.txtmno.getText().trim();
    String fname = this.txtfirstname.getText().trim();
    String lname = this.txtlastname.getText().trim();
    String idno = this.txtfid.getText().trim();
    String dob = new clsDate().seekDateByYear(new Date(), Integer.parseInt(new superCls().getIntValue(this.txtAge.getText())));
    String maritalStatus = this.sup.getValue(this.cmbMstatus.getSelectedItem());
    String gender = this.sup.getValue(this.cmbsex.getSelectedItem());
    String SpouseId = this.txtSpouseID.getText().trim();
    String spouseFname = this.txtSpouseFname.getText().trim();
    String spouseLname = this.txtSpuseLname.getText().trim();
    String PAddress = this.txtadd.getText().trim();
    String PCity = this.sup.getValue(this.cmbPatientcity.getSelectedItem());
    String Pstate = this.sup.getValue(this.cmbfarmerstate.getSelectedItem());
    String PZip = this.txtzip.getText().trim();
    String PPhone = this.txtphone.getText().trim();
    String PEmail = "";
    String insuranceId = this.txtinId.getText().trim();
    String insuranceType = this.sup.getValue(this.cmbIntype.getSelectedItem());
    String planName = this.txtplan.getText().trim();
    String pcoverageFrmDate = new SimpleDateFormat("dd-MM-yyyy").format(this.dtfrom.getDate());
    String pcoverageToDate = new SimpleDateFormat("dd-MM-yyyy").format(this.Dtto.getDate());
    String insuredCity = this.sup.getValue(this.cmbIncity.getSelectedItem().toString());
    String insuredState = this.sup.getValue(this.cmbInState.getSelectedItem());
    String insuredZip = this.txtZip.getText().trim();
    String insuredType = this.txttype.getText().trim();
    String insuredEmail = this.txtInemail.getText();
    String policyGroup = this.txtInGroup.getText().trim();
    String insuredFname = this.txtInFirstname.getText().trim();
    String insuredLname = this.txtInLastname.getText().trim();
    String insuredDob = new SimpleDateFormat("dd-MM-yyyy").format(this.dtInDOB.getDate());
    String insuredAddress = this.txtAdd.getText().trim();
    String insuredPhone = this.txtInPhone.getText().trim();
    String insuredRelationship = this.txtRelation.getText().trim();
    
    String paymentType = this.lbl_instCode.getText();
    
    String accountNo = this.txt_acc.getText().trim();
    int branch = this.scheme_id;
    
    String regDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    if (this.jComboBox4.getSelectedIndex() != -1) {
      CenterNo = this.sup.getIntValue(this.id[this.jComboBox4.getSelectedIndex()]);
    }
    if ((farmerId.equals("")) || (fname.equals("")) || (lname.equals("")) || ((PPhone.equals("")) && (idno.equals(""))) || (CenterNo.equals("")))
    {
      JLabel errorFields = new JLabel("<HTML><FONT COLOR = BLUE>Please make sure all Required fields are fileed!</FONT></HTML>");
      JOptionPane.showMessageDialog(null, errorFields, "DMS", 1);
    }
    else
    {
      addFarmer(farmerId, mno, CenterNo, fname, lname, idno, dob, maritalStatus, gender, PAddress, PCity, Pstate, PZip, PPhone, PEmail, SpouseId, spouseFname, spouseLname, paymentType, accountNo, branch, insuranceId, insuranceType, planName, pcoverageFrmDate, pcoverageToDate, insuredCity, insuredState, insuredZip, insuredType, insuredEmail, policyGroup, insuredFname, insuredLname, insuredDob, insuredAddress, insuredPhone, insuredRelationship, regDate);
      
      EnableFarmerFields(false);
    }
    }//GEN-LAST:event_btnSaveActionPerformed

  public void addFarmer(String farmerId, String mno, String centerNo, String fname, String lname, String idno, String dob, String maritalStatus, String gender, String PAddress, String PCity, String PState, String PZip, String PPhone, String PEmail, String spouseId, String spouseFname, String spouseLname, String paymentType, String accountNo, int branch, String insuranceId, String insuranceType, String planName, String pcoverageFrmDate, String pcoverageToDate, String insuredCity, String insuredState, String insuredZip, String insuredType, String insuredEmail, String policyGroup, String insuredFname, String insuredLname, String insuredDob, String insuredAddress, String insuredPhone, String insuredRelationship, String regDate)
  {
    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction tx = null;
    try
    {
      tx = session.beginTransaction();
      DtFarmerdetails farmer_save = new DtFarmerdetails(farmerId, accountNo, branch, centerNo, dob, fname, gender, idno, insuranceId, insuranceType, insuredAddress, insuredCity, insuredDob, insuredEmail, insuredFname, insuredLname, insuredPhone, insuredRelationship, insuredState, insuredType, insuredZip, lname, maritalStatus, mno, PEmail, PAddress, paymentType, PCity, pcoverageFrmDate, pcoverageToDate, planName, policyGroup, PPhone, PState, PZip, regDate, spouseFname, spouseId, spouseLname);
      session.saveOrUpdate(farmer_save);
      
      this.errorFields = new JLabel("<HTML><FONT COLOR = BLUE>Record Saved successfully</FONT></HTML>");
      JOptionPane.showMessageDialog(null, this.errorFields, "DMS", 1);
      tx.commit();
    }
    catch (HibernateException e)
    {
      if (tx != null) {
        tx.rollback();
      }
      e.printStackTrace();
    }
    finally
    {
      session.close();
    }
  }
    
    
    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        EnableFarmerFields(true);
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnNewPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewPatientActionPerformed
        // TODO add your handling code here:
        clearFarmerFields();
        txtpatientid.setText(String.valueOf(new rowcount().row_count(DtFarmerdetails.class)+1));
        EnableFarmerFields(true);
    }//GEN-LAST:event_btnNewPatientActionPerformed

   public void FarmerSearch(String farmerid ){
       Session session = HibernateUtil.getSessionFactory().openSession();
       Transaction tx = null;
      try{
         tx = session.beginTransaction();
         DtFarmerdetails farmer = (DtFarmerdetails)session.get(DtFarmerdetails.class, farmerid);
         if(farmer!=null){
             DtFarmerdetails details=farmer;
             new_patient_FieldsFill(details);          
         }else
             clearFarmerFields();
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
   }
   
   public void new_patient_FieldsFill(DtFarmerdetails details)
  {
    this.txtpatientid.setText(details.getFarmerId());
    this.txtmno.setText(details.getMno());
    this.txtfirstname.setText(details.getFname());
    this.txtlastname.setText(details.getLname());
    this.txtAge.setText(String.valueOf(new superCls().getDateDiff(new superCls().StringToDate(details.getDob()), new Date(), "YEARS")));
    this.cmbMstatus.setSelectedItem(details.getMaritalStatus());
    this.cmbsex.setSelectedItem(details.getGender());
    this.txtSpouseID.setText(details.getSpouseID());
    this.txtSpouseFname.setText(details.getSpouseFname());
    this.txtSpuseLname.setText(details.getSpouseLname());
    this.txtadd.setText(details.getPaddress());
    this.cmbPatientcity.setSelectedItem(details.getPcity());
    this.cmbfarmerstate.setSelectedItem(details.getPstate());
    this.txtzip.setText(details.getPzip());
    this.txtphone.setText(details.getPphone());
    this.txtinId.setText(details.getInsuranceID());
    this.cmbIntype.setSelectedItem(details.getInsuranceType());
    this.txtplan.setText(details.getPlanname());
    this.dtfrom.setDate(new superCls().StringToDate(details.getPcoverageFrmDate()));
    this.Dtto.setDate(new superCls().StringToDate(details.getPcoverageToDate()));
    this.cmbIncity.setSelectedItem(details.getInsuredCity());
    this.cmbInState.setSelectedItem(details.getInsuredState());
    this.txtZip.setText(details.getInsuredZip());
    this.txttype.setText(details.getInsuranceType());
    this.txtInemail.setText(details.getInsuredEmail());
    this.txtInGroup.setText(details.getPolicyGroup());
    this.txtInFirstname.setText(details.getInsuredFname());
    this.txtInLastname.setText(details.getInsuredLname());
    this.dtInDOB.setDate(new superCls().StringToDate(details.getInsuredDOB()));
    this.txtAdd.setText(details.getInsuredAddress());
    this.txtInPhone.setText(details.getInsuredPhone());
    this.txtRelation.setText(details.getInsuredRelationship());
    
    this.txt_acc.setText(details.getAccountNo());
    this.jComboBox1.setSelectedItem(details.getPaymentType());
    this.jComboBox2.setSelectedItem(Integer.valueOf(details.getBranch()));
    
    this.txtfid.setText(details.getIdno());
    this.jComboBox4.setSelectedItem(getCenter(details.getCenterNo()));
    this.jComboBox1.setSelectedItem(this.sup.insurance_name(details.getPaymentType()));
    this.jComboBox2.setSelectedItem(sup.branch_name(details.getBranch()));
  }
  
  public void clearFarmerFields()
  {
    this.txtpatientid.setText("");
    this.txtmno.setText("");
    this.txtfirstname.setText("");
    this.txtlastname.setText("");
    this.txtAge.setText("0");
    this.cmbMstatus.setSelectedItem("Single");
    this.cmbsex.setSelectedItem("Female");
    this.txtSpouseID.setText("");
    this.txtSpouseFname.setText("");
    this.txtSpuseLname.setText("");
    this.txtadd.setText("");
    this.cmbPatientcity.setSelectedItem("Nakuru");
    this.cmbfarmerstate.setSelectedItem("Kenya");
    this.txtzip.setText("+184");
    this.txtphone.setText("");
    this.txtinId.setText("");
    this.cmbIntype.setSelectedItem("");
    this.txtplan.setText("");
    this.dtfrom.setDate(new Date());
    this.Dtto.setDate(new Date());
    this.cmbIncity.setSelectedItem("Nakuru");
    this.cmbInState.setSelectedItem("Kenya");
    this.txtZip.setText("+184");
    this.txttype.setText("");
    this.txtInemail.setText("");
    this.txtInGroup.setText("");
    this.txtInFirstname.setText("");
    this.txtInLastname.setText("");
    this.dtInDOB.setDate(new Date());
    this.txtAdd.setText("");
    this.txtInPhone.setText("");
    this.txtRelation.setText("");
    
    this.txt_acc.setText("");
    this.jComboBox1.setSelectedIndex(-1);
    this.jComboBox2.setSelectedIndex(-1);
    
    this.txtfid.setText("");
    this.jComboBox1.setSelectedItem("");
    this.jComboBox2.setSelectedItem("");
  }
  
  public void EnableFarmerFields(boolean state)
  {
    this.txtpatientid.setEnabled(state);
    this.txtfirstname.setEnabled(state);
    this.txtlastname.setEnabled(state);
    this.txtAge.setEnabled(state);
    this.cmbMstatus.setEnabled(state);
    this.cmbsex.setEnabled(state);
    this.txtSpouseID.setEnabled(state);
    this.txtSpouseFname.setEnabled(state);
    this.txtSpuseLname.setEnabled(state);
    this.txtadd.setEnabled(state);
    this.cmbPatientcity.setEnabled(state);
    this.cmbfarmerstate.setEnabled(state);
    this.txtzip.setEnabled(state);
    this.txtphone.setEnabled(state);
    this.txtinId.setEnabled(state);
    this.cmbIntype.setSelectedItem("");
    this.txtplan.setEnabled(state);
    this.dtfrom.setEnabled(state);
    this.Dtto.setEnabled(state);
    this.cmbIncity.setEnabled(state);
    this.cmbInState.setEnabled(state);
    this.txtZip.setEnabled(state);
    this.txttype.setEnabled(state);
    this.txtInemail.setEnabled(state);
    this.txtInGroup.setEnabled(state);
    this.txtInFirstname.setEnabled(state);
    this.txtInLastname.setEnabled(state);
    this.dtInDOB.setDate(new Date());
    this.txtAdd.setEnabled(state);
    this.txtInPhone.setEnabled(state);
    this.txtRelation.setEnabled(state);
    this.txt_acc.setEnabled(state);
    this.jComboBox1.setEnabled(state);
    this.jComboBox2.setEnabled(state);
    this.txtfid.setEnabled(state);
  }
  
  private DefaultComboBoxModel insurances()
  {
    int i = 0;
    
    DefaultComboBoxModel tmodel = new DefaultComboBoxModel();
    Session session = HibernateUtil.getSessionFactory().openSession();
    try
    {
      session.beginTransaction();
      Query q = session.createQuery("Select Distinct s.insuranceCode, s.clientName from DtInsurances s where s.status='1'");
      q.setCacheable(true);
      List auth = q.list();
      this.insurance_id = new Object[auth.size() + 1];
      tmodel.addElement("Select Bank");
      this.insurance_id[i] = Integer.valueOf(0);
      for (Object o : auth)
      {
        Object[] scheme = (Object[])o;
        tmodel.addElement(scheme[1]);
        i++;
        this.insurance_id[i] = scheme[0];
      }
      session.getTransaction().commit();
    }
    catch (HibernateException he)
    {
      this.errorFields = new JLabel("<HTML><FONT COLOR = Blue>'" + he.getCause() + "'</FONT></HTML>");
      JOptionPane.showMessageDialog(null, this.errorFields, "Clinic Management System", 0);
    }
    finally
    {
      session.close();
    }
    return tmodel;
  }
  
  private DefaultComboBoxModel schemes(String insuranceCode)
  {
    int i = 0;
    
    DefaultComboBoxModel tmodel = new DefaultComboBoxModel();
    Session session = HibernateUtil.getSessionFactory().openSession();
    try
    {
      session.beginTransaction();
      Query q = session.createQuery("from DtInsurances s where s.insuranceCode=:insuranceCode");
      q.setParameter("insuranceCode", insuranceCode);
      q.setCacheable(true);
      List auth = q.list();
      Iterator iterator = auth.iterator();
      this.schemes_id = new Object[auth.size() + 1];
      tmodel.addElement("Select Branch");
      this.schemes_id[i] = Integer.valueOf(0);
      while (iterator.hasNext())
      {
        DtInsurances scheme = (DtInsurances)iterator.next();
        tmodel.addElement(scheme.getSchemeName());
        i++;
        this.schemes_id[i] = scheme.getId();
      }
      session.getTransaction().commit();
    }
    catch (HibernateException he)
    {
      this.errorFields = new JLabel("<HTML><FONT COLOR = Blue>'" + he.getMessage() + "'</FONT></HTML>");
      JOptionPane.showMessageDialog(null, this.errorFields, "Clinic Management System", 0);
    }
    finally
    {
      session.close();
    }
    return tmodel;
  }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser Dtto;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnNewPatient;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox cmbInState;
    private javax.swing.JComboBox cmbIncity;
    private javax.swing.JComboBox cmbIntype;
    private javax.swing.JComboBox cmbMstatus;
    private javax.swing.JComboBox cmbPatientcity;
    private javax.swing.JComboBox cmbfarmerstate;
    private javax.swing.JComboBox cmbsex;
    private com.toedter.calendar.JDateChooser dtInDOB;
    private com.toedter.calendar.JDateChooser dtfrom;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel200;
    private javax.swing.JLabel jLabel201;
    private javax.swing.JLabel jLabel203;
    private javax.swing.JLabel jLabel204;
    private javax.swing.JLabel jLabel205;
    private javax.swing.JLabel jLabel206;
    private javax.swing.JLabel jLabel207;
    private javax.swing.JLabel jLabel208;
    private javax.swing.JLabel jLabel209;
    private javax.swing.JLabel jLabel210;
    private javax.swing.JLabel jLabel211;
    private javax.swing.JLabel jLabel212;
    private javax.swing.JLabel jLabel213;
    private javax.swing.JLabel jLabel215;
    private javax.swing.JLabel jLabel216;
    private javax.swing.JLabel jLabel217;
    private javax.swing.JLabel jLabel218;
    private javax.swing.JLabel jLabel219;
    private javax.swing.JLabel jLabel220;
    private javax.swing.JLabel jLabel221;
    private javax.swing.JLabel jLabel222;
    private javax.swing.JLabel jLabel223;
    private javax.swing.JLabel jLabel224;
    private javax.swing.JLabel jLabel225;
    private javax.swing.JLabel jLabel226;
    private javax.swing.JLabel jLabel227;
    private javax.swing.JLabel jLabel228;
    private javax.swing.JLabel jLabel229;
    private javax.swing.JLabel jLabel230;
    private javax.swing.JLabel jLabel231;
    private javax.swing.JLabel jLabel246;
    private javax.swing.JLabel jLabel248;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel68;
    private javax.swing.JPanel jPanel71;
    private javax.swing.JPanel jPanel72;
    private javax.swing.JPanel jPanel73;
    private javax.swing.JPanel jPanel74;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lbl_instCode;
    private javax.swing.JTextField txtAdd;
    private javax.swing.JTextField txtAge;
    private javax.swing.JTextField txtInFirstname;
    private javax.swing.JTextField txtInGroup;
    private javax.swing.JTextField txtInLastname;
    private javax.swing.JTextField txtInPhone;
    private javax.swing.JTextField txtInemail;
    private javax.swing.JTextField txtRelation;
    private javax.swing.JTextField txtSpouseFname;
    private javax.swing.JTextField txtSpouseID;
    private javax.swing.JTextField txtSpuseLname;
    private javax.swing.JTextField txtZip;
    private javax.swing.JTextField txt_acc;
    private javax.swing.JTextField txtadd;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txtfid;
    private javax.swing.JTextField txtfirstname;
    private javax.swing.JTextField txtinId;
    private javax.swing.JTextField txtlastname;
    private javax.swing.JTextField txtmno;
    private javax.swing.JTextField txtpatientid;
    private javax.swing.JTextField txtphone;
    private javax.swing.JTextField txtplan;
    private javax.swing.JTextField txttype;
    private javax.swing.JTextField txtzip;
    // End of variables declaration//GEN-END:variables
}
