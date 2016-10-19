/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

//import com.birosoft.liquid.LiquidLookAndFeel;
import classes.PulseDemo;
import classes.clsReport;
import com.digitprop.tonic.TonicLookAndFeel;
import com.jtattoo.plaf.acryl.AcrylLookAndFeel;
import com.jtattoo.plaf.aero.AeroLookAndFeel;
import com.jtattoo.plaf.aluminium.AluminiumLookAndFeel;
import com.jtattoo.plaf.bernstein.BernsteinLookAndFeel;
import com.jtattoo.plaf.fast.FastLookAndFeel;
import com.jtattoo.plaf.hifi.HiFiLookAndFeel;
import com.jtattoo.plaf.luna.LunaLookAndFeel;
import com.jtattoo.plaf.mcwin.McWinLookAndFeel;
import com.jtattoo.plaf.mint.MintLookAndFeel;
import com.jtattoo.plaf.noire.NoireLookAndFeel;
import com.jtattoo.plaf.smart.SmartLookAndFeel;
import com.jtattoo.plaf.texture.TextureLookAndFeel;
import com.lipstikLF.LipstikLookAndFeel;
import com.seaglasslookandfeel.SeaGlassLookAndFeel;
import custom_jPanels.RoundRectGradientPanel;
import custom_jPanels.SimpleGradientPanel;
import farm.entity.DtUserprivileges;
import farm.entity.DtUsers;
import farmTech.util.HibernateUtil;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ui.mdi.WindowManager;
import util.GUIUtils;

/**
 *
 * @author KENT
 */
public final class Application extends javax.swing.JFrame {

    /**
     * Creates new form Application
     */
    clsReport rpt;
    private String idno="",UserFname,lnf,UserLname,UserIdNo,Useraddress,Userresidence,Userphone;
    private WindowManager m_windowManager = null;
    private  New_FJFrame farmerPanel;
    public  panel_Search search;
    private  company_frame comp;
    private  jframe_collection collec;
    private  adminFrm admin;
    private  searchFrame sFrame;
    private  member_loanFrame loanFrm;
    private  Customer_Company com;
    private  Stores store;
    private  Factory_reg factory;
    private  route_frame route;
    private  Fixed_asset asset;
    private  individual_saleframe saleframe;
    private  company_saleframe comp_sale;
    private  constants_frame constant;
    private  farmer_payment payroll;
    private  signup edit;
    private  frame_collec_rpts collection;
    private  coffee_inputFrame c_collection;
    private  coffee_fixed_constants c_rate;
    private  depts_frame depts;
    private  Expense_income_frame expense;
    private  exp_code_frame codes;
    private  deductions_frame deductions;
    private  c_farmer_payment c_payment;
    private  farmer_creport crpt;
    private  paymentRpt p_rpt;
    private  sale_rpts msales;
    private  members_route routerpt;
    private  milk_invoice m_invoice;
    private  Frame_Payment_Journal journal;
    private  collec_Summary collec_summary ;
    private  reports report ;
    
    Image img;
    public Application() {
        initComponents();
    }
     public Application(String idno) {
       initComponents();
        this.idno=idno;
        UserDetails(idno);
        jProgressBar1.setVisible(true);
        initialize.execute();
        // display the default properties
         m_windowManager = new WindowManager(m_desktopPane, m_windowMenu);
        m_outlineDragModeMenuItem.setSelected(
        m_windowManager.getOutlineDragMode() );
        m_deiconifiablePolicyMenuItem.setSelected(
        m_windowManager.getDeiconifiablePolicy() );
        m_closePolicyMenuItem.setSelected( m_windowManager.getClosePolicy() );
        autoPositionMenuItem.setSelected(
        m_windowManager.getAutoPositionPolicy() );       
        this.setLnF();
        this.chckbxLnF();
//        img=Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/Background.jpg")); 
        GUIUtils.maximizeJFrame(Application.this );
        
        
    }

     
     
     SwingWorker initialize=new SwingWorker() {
      @Override
      protected Integer doInBackground() throws Exception{
         
        farmerPanel=new New_FJFrame();
         search=new panel_Search();
         comp=new company_frame();
         collec=new jframe_collection();
         admin=new adminFrm();
         sFrame=new searchFrame();
         loanFrm=new member_loanFrame();
         com=new Customer_Company();
         store=new Stores();
         factory=new Factory_reg();
         route=new route_frame();
         asset=new Fixed_asset();
         saleframe=new individual_saleframe();
         comp_sale=new company_saleframe();
         constant=new constants_frame();
         payroll=new farmer_payment();
         edit=new signup();
         collection=new frame_collec_rpts();
         c_collection=new coffee_inputFrame();
         c_rate=new coffee_fixed_constants();
         depts=new depts_frame();
         expense=new Expense_income_frame();
         codes=new exp_code_frame();
         deductions=new deductions_frame();
         c_payment=new c_farmer_payment();
         crpt=new farmer_creport();
         p_rpt=new paymentRpt();
         msales=new sale_rpts();
         routerpt=new members_route();
         m_invoice=new milk_invoice();
         journal=new Frame_Payment_Journal();
         collec_summary =new collec_Summary(); 
         report=new reports();
      return 0;
    }
    @Override
    protected void done(){
        m_desktopPane.add(comp, JLayeredPane.DEFAULT_LAYER);
        m_desktopPane.add(farmerPanel, JLayeredPane.DEFAULT_LAYER);
        m_desktopPane.add(collec, JLayeredPane.DEFAULT_LAYER);
        m_desktopPane.add(admin, JLayeredPane.DEFAULT_LAYER);
        m_desktopPane.add(loanFrm, JLayeredPane.DEFAULT_LAYER);
        m_desktopPane.add(search, JLayeredPane.POPUP_LAYER);  
        m_desktopPane.add(sFrame, JLayeredPane.DEFAULT_LAYER);  
        m_desktopPane.add(com, JLayeredPane.DEFAULT_LAYER);  
        m_desktopPane.add(store, JLayeredPane.DEFAULT_LAYER); 
        m_desktopPane.add(factory, JLayeredPane.DEFAULT_LAYER);
        m_desktopPane.add(route, JLayeredPane.DEFAULT_LAYER);
        m_desktopPane.add(asset, JLayeredPane.DEFAULT_LAYER);
        m_desktopPane.add(saleframe, JLayeredPane.DEFAULT_LAYER);
        m_desktopPane.add(comp_sale, JLayeredPane.DEFAULT_LAYER);
        m_desktopPane.add(constant, JLayeredPane.DEFAULT_LAYER);
        m_desktopPane.add(payroll, JLayeredPane.DEFAULT_LAYER);
        m_desktopPane.add(edit, JLayeredPane.DEFAULT_LAYER);
        m_desktopPane.add(collection, JLayeredPane.DEFAULT_LAYER);
        m_desktopPane.add(c_collection, JLayeredPane.DEFAULT_LAYER);
        m_desktopPane.add(c_rate, JLayeredPane.DEFAULT_LAYER);
        m_desktopPane.add(depts, JLayeredPane.DEFAULT_LAYER);
        m_desktopPane.add(expense, JLayeredPane.DEFAULT_LAYER);
        m_desktopPane.add(codes, JLayeredPane.DEFAULT_LAYER);
        m_desktopPane.add(deductions, JLayeredPane.DEFAULT_LAYER);
        m_desktopPane.add(c_payment, JLayeredPane.DEFAULT_LAYER);
        m_desktopPane.add(crpt, JLayeredPane.DEFAULT_LAYER);
        m_desktopPane.add(p_rpt, JLayeredPane.DEFAULT_LAYER);
        m_desktopPane.add(msales, JLayeredPane.DEFAULT_LAYER);
        m_desktopPane.add(journal, JLayeredPane.DEFAULT_LAYER);
        m_desktopPane.add(routerpt, JLayeredPane.DEFAULT_LAYER);
        m_desktopPane.add(m_invoice, JLayeredPane.DEFAULT_LAYER);
        m_desktopPane.add(collec_summary, JLayeredPane.DEFAULT_LAYER);
        m_desktopPane.add(report, JLayeredPane.DEFAULT_LAYER);
        edit.setVisible(false);
        jProgressBar1.setVisible(false);
    }
 };
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        m_desktopPane = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new RoundRectGradientPanel();
        jLabel8 = new javax.swing.JLabel();
        jSeparator19 = new javax.swing.JSeparator();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
        jRadioButton7 = new javax.swing.JRadioButton();
        jRadioButton8 = new javax.swing.JRadioButton();
        jRadioButton9 = new javax.swing.JRadioButton();
        jPanel2 = new SimpleGradientPanel(new Color(120,78,46),new Color(255,255,255));
        jLabel9 = new PulseDemo().buildPulsatingText("/images/Farmer-icon.png");
        jLabel10 = new PulseDemo().buildPulsatingText("/images/PatientFile.png");
        jLabel11 = new PulseDemo().buildPulsatingText("/images/Application.png");
        jLabel12 = new PulseDemo().buildPulsatingText("/images/ParameterReview.png");
        jLabel13 = new PulseDemo().buildPulsatingText("/images/Maintenance.png");
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jSeparator20 = new javax.swing.JSeparator();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jSeparator21 = new javax.swing.JSeparator();
        jProgressBar1 = new javax.swing.JProgressBar();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        cutMenuItem = new javax.swing.JMenuItem();
        copyMenuItem = new javax.swing.JMenuItem();
        pasteMenuItem = new javax.swing.JMenuItem();
        deleteMenuItem = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        cutMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem25 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenu9 = new javax.swing.JMenu();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenuItem20 = new javax.swing.JMenuItem();
        jMenu12 = new javax.swing.JMenu();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem26 = new javax.swing.JMenuItem();
        jMenuItem27 = new javax.swing.JMenuItem();
        jMenu13 = new javax.swing.JMenu();
        jMenuItem36 = new javax.swing.JMenuItem();
        jMenuItem37 = new javax.swing.JMenuItem();
        jMenuItem38 = new javax.swing.JMenuItem();
        jMenuItem39 = new javax.swing.JMenuItem();
        jMenuItem40 = new javax.swing.JMenuItem();
        jMenuItem41 = new javax.swing.JMenuItem();
        jMenu10 = new javax.swing.JMenu();
        jMenuItem24 = new javax.swing.JMenuItem();
        jMenuItem31 = new javax.swing.JMenuItem();
        jMenuItem28 = new javax.swing.JMenuItem();
        jMenuItem29 = new javax.swing.JMenuItem();
        jMenuItem30 = new javax.swing.JMenuItem();
        jMenuItem33 = new javax.swing.JMenuItem();
        jMenuItem35 = new javax.swing.JMenuItem();
        jMenuItem42 = new javax.swing.JMenuItem();
        jMenuItem44 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem21 = new javax.swing.JMenuItem();
        jMenuItem22 = new javax.swing.JMenuItem();
        jMenu11 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem32 = new javax.swing.JMenuItem();
        jMenuItem23 = new javax.swing.JMenuItem();
        jMenuItem34 = new javax.swing.JMenuItem();
        cutMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem43 = new javax.swing.JMenuItem();
        jMenuItem45 = new javax.swing.JMenuItem();
        jMenuItem46 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        chckbxAcryl = new javax.swing.JCheckBoxMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        jCheckBoxMenuItem2 = new javax.swing.JCheckBoxMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        jCheckBoxMenuItem3 = new javax.swing.JCheckBoxMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        jCheckBoxMenuItem4 = new javax.swing.JCheckBoxMenuItem();
        jSeparator8 = new javax.swing.JPopupMenu.Separator();
        jCheckBoxMenuItem5 = new javax.swing.JCheckBoxMenuItem();
        jSeparator9 = new javax.swing.JPopupMenu.Separator();
        jCheckBoxMenuItem6 = new javax.swing.JCheckBoxMenuItem();
        jSeparator10 = new javax.swing.JPopupMenu.Separator();
        jCheckBoxMenuItem7 = new javax.swing.JCheckBoxMenuItem();
        jSeparator11 = new javax.swing.JPopupMenu.Separator();
        jCheckBoxMenuItem8 = new javax.swing.JCheckBoxMenuItem();
        jSeparator12 = new javax.swing.JPopupMenu.Separator();
        chckbxMacWin = new javax.swing.JCheckBoxMenuItem();
        jSeparator13 = new javax.swing.JPopupMenu.Separator();
        jCheckBoxMenuItem10 = new javax.swing.JCheckBoxMenuItem();
        jSeparator14 = new javax.swing.JPopupMenu.Separator();
        jCheckBoxMenuItem11 = new javax.swing.JCheckBoxMenuItem();
        jSeparator15 = new javax.swing.JPopupMenu.Separator();
        jCheckBoxMenuItem12 = new javax.swing.JCheckBoxMenuItem();
        jSeparator16 = new javax.swing.JPopupMenu.Separator();
        jCheckBoxMenuItem13 = new javax.swing.JCheckBoxMenuItem();
        jSeparator17 = new javax.swing.JPopupMenu.Separator();
        jCheckBoxMenuItem14 = new javax.swing.JCheckBoxMenuItem();
        jSeparator18 = new javax.swing.JPopupMenu.Separator();
        jCheckBoxMenuItem15 = new javax.swing.JCheckBoxMenuItem();
        helpMenu = new javax.swing.JMenu();
        contentMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();
        m_windowMenu = new javax.swing.JMenu();
        m_outlineDragModeMenuItem = new javax.swing.JCheckBoxMenuItem();
        m_deiconifiablePolicyMenuItem = new javax.swing.JCheckBoxMenuItem();
        autoPositionMenuItem = new javax.swing.JCheckBoxMenuItem();
        m_closePolicyMenuItem = new javax.swing.JCheckBoxMenuItem();
        javax.swing.JSeparator jSeparator1 = new javax.swing.JSeparator();
        javax.swing.JMenuItem cascadeMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenuItem tileHMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenuItem tileVMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenuItem tileMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenuItem nextMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenuItem previousMenuItem = new javax.swing.JMenuItem();
        javax.swing.JSeparator jSeparator2 = new javax.swing.JSeparator();
        javax.swing.JMenuItem minimizeMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenuItem minimizeAllMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenuItem restoreMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenuItem restoreAllMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenuItem maximizeMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenuItem maximizeAllMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenuItem resetMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenuItem resetAllMenuItem = new javax.swing.JMenuItem();
        javax.swing.JSeparator jSeparator3 = new javax.swing.JSeparator();
        javax.swing.JMenuItem hideMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenuItem hideAllMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenuItem closeMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenuItem closeAllMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/ox-cow.png")));
        setMinimumSize(new java.awt.Dimension(891, 504));

        m_desktopPane.setOpaque(false);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo.png"))); // NOI18N
        jLabel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jLabel1.setOpaque(true);
        m_desktopPane.add(jLabel1);
        jLabel1.setBounds(80, 20, 140, 110);

        jLabel2.setBackground(new java.awt.Color(120, 78, 46));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Kabuboni FCS");
        jLabel2.setMaximumSize(new java.awt.Dimension(1366, 25));
        jLabel2.setMinimumSize(new java.awt.Dimension(1366, 25));
        jLabel2.setName(""); // NOI18N
        jLabel2.setOpaque(true);
        m_desktopPane.add(jLabel2);
        jLabel2.setBounds(20, 60, 1310, 50);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(61, 30, 3));
        jLabel3.setText("Our Partners :");
        m_desktopPane.add(jLabel3);
        jLabel3.setBounds(30, 600, 100, 70);

        jLabel8.setText("Main Functions :");

        jRadioButton1.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jRadioButton1.setForeground(new java.awt.Color(120, 78, 46));
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Registration of Farmers");
        jRadioButton1.setOpaque(false);

        jRadioButton2.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jRadioButton2.setForeground(new java.awt.Color(120, 78, 46));
        jRadioButton2.setSelected(true);
        jRadioButton2.setText("Registration of Customers");
        jRadioButton2.setOpaque(false);

        jRadioButton3.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jRadioButton3.setForeground(new java.awt.Color(120, 78, 46));
        jRadioButton3.setSelected(true);
        jRadioButton3.setText("Registration of Users");
        jRadioButton3.setOpaque(false);

        jRadioButton4.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jRadioButton4.setForeground(new java.awt.Color(120, 78, 46));
        jRadioButton4.setSelected(true);
        jRadioButton4.setText("Daily Milk Collection in sessions");
        jRadioButton4.setOpaque(false);

        jRadioButton5.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jRadioButton5.setForeground(new java.awt.Color(120, 78, 46));
        jRadioButton5.setSelected(true);
        jRadioButton5.setText("Monthly Famer Deductions");
        jRadioButton5.setOpaque(false);

        jRadioButton6.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jRadioButton6.setForeground(new java.awt.Color(120, 78, 46));
        jRadioButton6.setSelected(true);
        jRadioButton6.setText("Monthly Farmer Payment");
        jRadioButton6.setOpaque(false);

        jRadioButton7.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jRadioButton7.setForeground(new java.awt.Color(120, 78, 46));
        jRadioButton7.setSelected(true);
        jRadioButton7.setText("Stores Module");
        jRadioButton7.setOpaque(false);

        jRadioButton8.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jRadioButton8.setForeground(new java.awt.Color(120, 78, 46));
        jRadioButton8.setSelected(true);
        jRadioButton8.setText("Payment Receipt Generation");
        jRadioButton8.setOpaque(false);

        jRadioButton9.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jRadioButton9.setForeground(new java.awt.Color(120, 78, 46));
        jRadioButton9.setSelected(true);
        jRadioButton9.setText("Report Generation");
        jRadioButton9.setOpaque(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton9)
                    .addComponent(jRadioButton8)
                    .addComponent(jRadioButton7)
                    .addComponent(jRadioButton6)
                    .addComponent(jRadioButton5)
                    .addComponent(jRadioButton4)
                    .addComponent(jRadioButton3)
                    .addComponent(jRadioButton2)
                    .addComponent(jRadioButton1)
                    .addComponent(jSeparator19, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel8)
                .addGap(5, 5, 5)
                .addComponent(jSeparator19, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jRadioButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton9)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        m_desktopPane.add(jPanel1);
        jPanel1.setBounds(180, 210, 210, 330);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Farmer-icon.png"))); // NOI18N
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/PatientFile.png"))); // NOI18N
        jLabel10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Application.png"))); // NOI18N
        jLabel11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ParameterReview.png"))); // NOI18N
        jLabel12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Maintenance.png"))); // NOI18N
        jLabel13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Register Farmer");
        jLabel14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Milk Collection");
        jLabel15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Payment");
        jLabel16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Reports");
        jLabel17.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Settings");
        jLabel18.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("    Main Menu");

        jSeparator20.setForeground(new java.awt.Color(255, 255, 255));

        jLabel20.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel20.setText("This simple application was created with the intention of");

        jLabel21.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel21.setText("Easing The work of milk collection from the farmer");

        jLabel22.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel22.setText("distribution and sale and finally paying the farmer.");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 2, 11)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Creating Sustainable Livelihoods for one million people in Uganda, Kenya and Tanzania");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                            .addComponent(jSeparator20)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel20)
                                .addComponent(jLabel21)
                                .addComponent(jLabel22))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel23)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel14)
                                                .addComponent(jLabel9))
                                            .addGap(45, 45, 45)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel15)
                                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(55, 55, 55)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel11)
                                                .addComponent(jLabel16))
                                            .addGap(55, 55, 55)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel12)
                                                .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING))))
                                    .addGap(55, 55, 55)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel18)
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jSeparator21, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(16, 16, 16))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel10, jLabel11, jLabel12, jLabel13, jLabel16, jLabel17, jLabel18, jLabel9});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jSeparator20, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(19, 19, 19)
                .addComponent(jSeparator21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addGap(31, 31, 31)
                .addComponent(jLabel23)
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel10, jLabel11, jLabel12, jLabel13, jLabel9});

        m_desktopPane.add(jPanel2);
        jPanel2.setBounds(400, 210, 610, 330);

        jProgressBar1.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jProgressBar1.setForeground(new java.awt.Color(0, 204, 51));
        jProgressBar1.setToolTipText("");
        jProgressBar1.setIndeterminate(true);
        jProgressBar1.setString("Please Be patient Loading...");
        jProgressBar1.setStringPainted(true);
        m_desktopPane.add(jProgressBar1);
        jProgressBar1.setBounds(10, 670, 1330, 20);

        getContentPane().add(m_desktopPane, java.awt.BorderLayout.CENTER);

        fileMenu.setMnemonic('f');
        fileMenu.setText("File");
        fileMenu.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N

        openMenuItem.setMnemonic('o');
        openMenuItem.setText("Open");
        fileMenu.add(openMenuItem);

        saveMenuItem.setMnemonic('s');
        saveMenuItem.setText("Save");
        fileMenu.add(saveMenuItem);

        saveAsMenuItem.setMnemonic('a');
        saveAsMenuItem.setText("Save As ...");
        saveAsMenuItem.setDisplayedMnemonicIndex(5);
        fileMenu.add(saveAsMenuItem);

        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        editMenu.setMnemonic('e');
        editMenu.setText("Edit");
        editMenu.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N

        cutMenuItem.setMnemonic('t');
        cutMenuItem.setText("Search Farmer                        ");
        cutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cutMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(cutMenuItem);

        copyMenuItem.setMnemonic('y');
        copyMenuItem.setText("Copy");
        editMenu.add(copyMenuItem);

        pasteMenuItem.setMnemonic('p');
        pasteMenuItem.setText("Paste");
        editMenu.add(pasteMenuItem);

        deleteMenuItem.setMnemonic('d');
        deleteMenuItem.setText("Delete");
        editMenu.add(deleteMenuItem);

        menuBar.add(editMenu);

        jMenu2.setText("Company");
        jMenu2.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu2ActionPerformed(evt);
            }
        });
        menuBar.add(jMenu2);

        jMenu1.setText("Farmer Maintenance ");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N

        cutMenuItem2.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        cutMenuItem2.setMnemonic('t');
        cutMenuItem2.setText("Search Farmer                        ");
        cutMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cutMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(cutMenuItem2);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem1.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jMenuItem1.setText("Farmer Maintenance                                                               ");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem5.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jMenuItem5.setText("Farmer Loan");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuItem7.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jMenuItem7.setText("Share Trading");
        jMenuItem7.setEnabled(false);
        jMenu1.add(jMenuItem7);

        jMenuItem8.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jMenuItem8.setText("Stores");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem8);

        jMenuItem9.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jMenuItem9.setText("Factory");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem9);

        jMenuItem10.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jMenuItem10.setText("Vehicle & Transport");
        jMenuItem10.setEnabled(false);
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem10);

        jMenuItem11.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jMenuItem11.setText("Routes & collection centres");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem11);

        menuBar.add(jMenu1);

        jMenu3.setText("Data Entry");
        jMenu3.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N

        jMenuItem4.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jMenuItem4.setText("Daily Milk Collection      ");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuItem12.setText("Dairy Expenses");
        jMenuItem12.setEnabled(false);
        jMenu3.add(jMenuItem12);

        jMenuItem13.setText("Route Collection");
        jMenuItem13.setEnabled(false);
        jMenu3.add(jMenuItem13);

        jMenu7.setText("Sales");

        jMenuItem14.setText("Individual              ");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem14);

        jMenuItem15.setText("Company Sale");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem15);

        jMenu3.add(jMenu7);

        jMenuItem25.setText("coffee collection");
        jMenuItem25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem25ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem25);

        menuBar.add(jMenu3);

        jMenu8.setText("Fixed Data Maintenance   ");
        jMenu8.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N

        jMenuItem17.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jMenuItem17.setText("Register Companies");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem17);

        jMenu9.setText("Fixed Assets");
        jMenu9.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N

        jMenuItem18.setText("Fixed Assets");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem18);

        jMenuItem19.setText("Fixed Asset Register");
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem19);

        jMenuItem20.setText("Depreciation Chart        ");
        jMenu9.add(jMenuItem20);

        jMenu8.add(jMenu9);

        jMenu12.setText("Set Rate ");

        jMenuItem16.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jMenuItem16.setText("Milk rate");
        jMenuItem16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem16MouseClicked(evt);
            }
        });
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu12.add(jMenuItem16);

        jMenuItem26.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jMenuItem26.setText("Coffee rate");
        jMenuItem26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem26ActionPerformed(evt);
            }
        });
        jMenu12.add(jMenuItem26);

        jMenu8.add(jMenu12);

        jMenuItem27.setText("Register Departments");
        jMenuItem27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem27ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem27);

        menuBar.add(jMenu8);

        jMenu13.setText("Stores  ");
        jMenu13.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N

        jMenuItem36.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jMenuItem36.setText("POS");
        jMenuItem36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem36ActionPerformed(evt);
            }
        });
        jMenu13.add(jMenuItem36);

        jMenuItem37.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jMenuItem37.setText("Add New Item");
        jMenu13.add(jMenuItem37);

        jMenuItem38.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jMenuItem38.setText("Inventory");
        jMenu13.add(jMenuItem38);

        jMenuItem39.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jMenuItem39.setText("Sales");
        jMenu13.add(jMenuItem39);

        jMenuItem40.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jMenuItem40.setText("Customers                  ");
        jMenu13.add(jMenuItem40);

        jMenuItem41.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jMenuItem41.setText("Products");
        jMenu13.add(jMenuItem41);

        menuBar.add(jMenu13);

        jMenu10.setText("Accounts   ");
        jMenu10.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N

        jMenuItem24.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jMenuItem24.setText("Milk Farmer Monthly Income");
        jMenuItem24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem24ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem24);

        jMenuItem31.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jMenuItem31.setText("Coffee Farmer Monthly Income");
        jMenuItem31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem31ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem31);

        jMenuItem28.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jMenuItem28.setText("Income_Expense Account");
        jMenuItem28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem28ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem28);

        jMenuItem29.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jMenuItem29.setText("Enter I&E codes");
        jMenuItem29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem29ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem29);

        jMenuItem30.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jMenuItem30.setText("Farmer Deductions                    ");
        jMenuItem30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem30ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem30);

        jMenuItem33.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jMenuItem33.setText("Organizational payment");
        jMenuItem33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem33ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem33);

        jMenuItem35.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jMenuItem35.setText("Milk Sales & Invoices");
        jMenuItem35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem35ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem35);

        jMenuItem42.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jMenuItem42.setText("Farmer Payment Journal");
        jMenuItem42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem42ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem42);

        jMenuItem44.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jMenuItem44.setText("Sales Statement");
        jMenuItem44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem44ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem44);

        menuBar.add(jMenu10);

        jMenu5.setText("Administrator ");
        jMenu5.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N

        jMenuItem3.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jMenuItem3.setText("new User                                           ");
        jMenuItem3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem3MouseClicked(evt);
            }
        });
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem3);

        jMenuItem21.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jMenuItem21.setText("Database Backup");
        jMenu5.add(jMenuItem21);

        jMenuItem22.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jMenuItem22.setText("Database Restore");
        jMenu5.add(jMenuItem22);

        menuBar.add(jMenu5);

        jMenu11.setText("  Reports   ");
        jMenu11.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N

        jMenuItem6.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jMenuItem6.setText("Milk Collection Record       ");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem6);

        jMenuItem32.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jMenuItem32.setText("Coffee Collection Record");
        jMenuItem32.setEnabled(false);
        jMenuItem32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem32ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem32);

        jMenuItem23.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jMenuItem23.setText("View all registered members");
        jMenuItem23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem23ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem23);

        jMenuItem34.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jMenuItem34.setText("Registered Coffee farmers");
        jMenuItem34.setEnabled(false);
        jMenuItem34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem34ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem34);

        cutMenuItem1.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        cutMenuItem1.setMnemonic('t');
        cutMenuItem1.setText("Search Farmer                         ");
        cutMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cutMenuItem1ActionPerformed(evt);
            }
        });
        jMenu11.add(cutMenuItem1);

        jMenuItem43.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jMenuItem43.setText("Farmers Account Types");
        jMenuItem43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem43ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem43);

        jMenuItem45.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jMenuItem45.setText("Collection Summary (Gendered)");
        jMenuItem45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem45ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem45);

        jMenuItem46.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jMenuItem46.setText("Quarterly & Yearly Report");
        jMenuItem46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem46ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem46);

        menuBar.add(jMenu11);

        jMenu4.setText("Settings");
        jMenu4.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N

        jMenuItem2.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jMenuItem2.setText("Login settings");
        jMenuItem2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem2MouseClicked(evt);
            }
        });
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem2);

        jMenu6.setText("Change Win appearance");
        jMenu6.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N

        jCheckBoxMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        jCheckBoxMenuItem1.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jCheckBoxMenuItem1.setText("LipstikLF         ");
        jCheckBoxMenuItem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBoxMenuItem1MouseClicked(evt);
            }
        });
        jMenu6.add(jCheckBoxMenuItem1);
        jMenu6.add(jSeparator4);

        chckbxAcryl.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        chckbxAcryl.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        chckbxAcryl.setText("Acryl");
        chckbxAcryl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chckbxAcrylMouseClicked(evt);
            }
        });
        jMenu6.add(chckbxAcryl);
        jMenu6.add(jSeparator5);

        jCheckBoxMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK));
        jCheckBoxMenuItem2.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jCheckBoxMenuItem2.setText("Aero     ");
        jCheckBoxMenuItem2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBoxMenuItem2MouseClicked(evt);
            }
        });
        jMenu6.add(jCheckBoxMenuItem2);
        jMenu6.add(jSeparator6);

        jCheckBoxMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.SHIFT_MASK));
        jCheckBoxMenuItem3.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jCheckBoxMenuItem3.setText("Aluminium");
        jCheckBoxMenuItem3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBoxMenuItem3MouseClicked(evt);
            }
        });
        jMenu6.add(jCheckBoxMenuItem3);
        jMenu6.add(jSeparator7);

        jCheckBoxMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        jCheckBoxMenuItem4.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jCheckBoxMenuItem4.setText("Bernstein");
        jCheckBoxMenuItem4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBoxMenuItem4MouseClicked(evt);
            }
        });
        jMenu6.add(jCheckBoxMenuItem4);
        jMenu6.add(jSeparator8);

        jCheckBoxMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        jCheckBoxMenuItem5.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jCheckBoxMenuItem5.setText("Fast");
        jCheckBoxMenuItem5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBoxMenuItem5MouseClicked(evt);
            }
        });
        jMenu6.add(jCheckBoxMenuItem5);
        jMenu6.add(jSeparator9);

        jCheckBoxMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        jCheckBoxMenuItem6.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jCheckBoxMenuItem6.setText("Hifi");
        jCheckBoxMenuItem6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBoxMenuItem6MouseClicked(evt);
            }
        });
        jMenu6.add(jCheckBoxMenuItem6);
        jMenu6.add(jSeparator10);

        jCheckBoxMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        jCheckBoxMenuItem7.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jCheckBoxMenuItem7.setText("Luna");
        jCheckBoxMenuItem7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBoxMenuItem7MouseClicked(evt);
            }
        });
        jMenu6.add(jCheckBoxMenuItem7);
        jMenu6.add(jSeparator11);

        jCheckBoxMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        jCheckBoxMenuItem8.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jCheckBoxMenuItem8.setText("Mint");
        jCheckBoxMenuItem8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBoxMenuItem8MouseClicked(evt);
            }
        });
        jMenu6.add(jCheckBoxMenuItem8);
        jMenu6.add(jSeparator12);

        chckbxMacWin.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.ALT_MASK));
        chckbxMacWin.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        chckbxMacWin.setText("MacWindows");
        chckbxMacWin.setContentAreaFilled(false);
        chckbxMacWin.setRolloverEnabled(true);
        chckbxMacWin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chckbxMacWinMouseClicked(evt);
            }
        });
        jMenu6.add(chckbxMacWin);
        jMenu6.add(jSeparator13);

        jCheckBoxMenuItem10.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jCheckBoxMenuItem10.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jCheckBoxMenuItem10.setText("Noire");
        jCheckBoxMenuItem10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBoxMenuItem10MouseClicked(evt);
            }
        });
        jMenu6.add(jCheckBoxMenuItem10);
        jMenu6.add(jSeparator14);

        jCheckBoxMenuItem11.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jCheckBoxMenuItem11.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jCheckBoxMenuItem11.setText("Smart");
        jCheckBoxMenuItem11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBoxMenuItem11MouseClicked(evt);
            }
        });
        jMenu6.add(jCheckBoxMenuItem11);
        jMenu6.add(jSeparator15);

        jCheckBoxMenuItem12.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.ALT_MASK));
        jCheckBoxMenuItem12.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jCheckBoxMenuItem12.setText("Texture");
        jCheckBoxMenuItem12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBoxMenuItem12MouseClicked(evt);
            }
        });
        jMenu6.add(jCheckBoxMenuItem12);
        jMenu6.add(jSeparator16);

        jCheckBoxMenuItem13.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_K, java.awt.event.InputEvent.CTRL_MASK));
        jCheckBoxMenuItem13.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jCheckBoxMenuItem13.setText("Kunststoff");
        jCheckBoxMenuItem13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBoxMenuItem13MouseClicked(evt);
            }
        });
        jMenu6.add(jCheckBoxMenuItem13);
        jMenu6.add(jSeparator17);

        jCheckBoxMenuItem14.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jCheckBoxMenuItem14.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jCheckBoxMenuItem14.setText("Seaglass");
        jCheckBoxMenuItem14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBoxMenuItem14MouseClicked(evt);
            }
        });
        jMenu6.add(jCheckBoxMenuItem14);
        jMenu6.add(jSeparator18);

        jCheckBoxMenuItem15.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        jCheckBoxMenuItem15.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jCheckBoxMenuItem15.setText("Digiprop");
        jCheckBoxMenuItem15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBoxMenuItem15MouseClicked(evt);
            }
        });
        jMenu6.add(jCheckBoxMenuItem15);

        jMenu4.add(jMenu6);

        menuBar.add(jMenu4);

        helpMenu.setMnemonic('h');
        helpMenu.setText("Help");
        helpMenu.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N

        contentMenuItem.setMnemonic('c');
        contentMenuItem.setText("Contents");
        helpMenu.add(contentMenuItem);

        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("About us");
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        m_windowMenu.setText("Window");
        m_windowMenu.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N

        m_outlineDragModeMenuItem.setText("Outline Drag Mode");
        m_outlineDragModeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_outlineDragModeMenuItemActionPerformed(evt);
            }
        });
        m_windowMenu.add(m_outlineDragModeMenuItem);

        m_deiconifiablePolicyMenuItem.setText("De-iconify during cascade / tile");
        m_deiconifiablePolicyMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_deiconifiablePolicyMenuItemActionPerformed(evt);
            }
        });
        m_windowMenu.add(m_deiconifiablePolicyMenuItem);

        autoPositionMenuItem.setText("Auto Position Frames");
        autoPositionMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                autoPositionMenuItemActionPerformed(evt);
            }
        });
        m_windowMenu.add(autoPositionMenuItem);

        m_closePolicyMenuItem.setText("Default Close Operation vs Close");
        m_closePolicyMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_closePolicyMenuItemActionPerformed(evt);
            }
        });
        m_windowMenu.add(m_closePolicyMenuItem);
        m_windowMenu.add(jSeparator1);

        cascadeMenuItem.setText("Cascade");
        cascadeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cascadeMenuItemActionPerformed(evt);
            }
        });
        m_windowMenu.add(cascadeMenuItem);

        tileHMenuItem.setText("Tile Horizontal");
        tileHMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tileHMenuItemActionPerformed(evt);
            }
        });
        m_windowMenu.add(tileHMenuItem);

        tileVMenuItem.setText("Tile Vertical");
        tileVMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tileVMenuItemActionPerformed(evt);
            }
        });
        m_windowMenu.add(tileVMenuItem);

        tileMenuItem.setText("Tile");
        tileMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tileMenuItemActionPerformed(evt);
            }
        });
        m_windowMenu.add(tileMenuItem);

        nextMenuItem.setText("Next Window");
        nextMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextMenuItemActionPerformed(evt);
            }
        });
        m_windowMenu.add(nextMenuItem);

        previousMenuItem.setText("Previous Window");
        previousMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousMenuItemActionPerformed(evt);
            }
        });
        m_windowMenu.add(previousMenuItem);
        m_windowMenu.add(jSeparator2);

        minimizeMenuItem.setText("Minimize");
        minimizeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minimizeMenuItemActionPerformed(evt);
            }
        });
        m_windowMenu.add(minimizeMenuItem);

        minimizeAllMenuItem.setText("Minimize All");
        minimizeAllMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minimizeAllMenuItemActionPerformed(evt);
            }
        });
        m_windowMenu.add(minimizeAllMenuItem);

        restoreMenuItem.setText("Restore");
        restoreMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restoreMenuItemActionPerformed(evt);
            }
        });
        m_windowMenu.add(restoreMenuItem);

        restoreAllMenuItem.setText("Restore All");
        restoreAllMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restoreAllMenuItemActionPerformed(evt);
            }
        });
        m_windowMenu.add(restoreAllMenuItem);

        maximizeMenuItem.setText("Maximize");
        maximizeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maximizeMenuItemActionPerformed(evt);
            }
        });
        m_windowMenu.add(maximizeMenuItem);

        maximizeAllMenuItem.setText("Maximize All");
        maximizeAllMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maximizeAllMenuItemActionPerformed(evt);
            }
        });
        m_windowMenu.add(maximizeAllMenuItem);

        resetMenuItem.setText("Reset Window");
        resetMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetMenuItemActionPerformed(evt);
            }
        });
        m_windowMenu.add(resetMenuItem);

        resetAllMenuItem.setText("Reset All Windows");
        resetAllMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetAllMenuItemActionPerformed(evt);
            }
        });
        m_windowMenu.add(resetAllMenuItem);
        m_windowMenu.add(jSeparator3);

        hideMenuItem.setText("Hide");
        hideMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hideMenuItemActionPerformed(evt);
            }
        });
        m_windowMenu.add(hideMenuItem);

        hideAllMenuItem.setText("Hide All");
        hideAllMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hideAllMenuItemActionPerformed(evt);
            }
        });
        m_windowMenu.add(hideAllMenuItem);

        closeMenuItem.setText("Close");
        closeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeMenuItemActionPerformed(evt);
            }
        });
        m_windowMenu.add(closeMenuItem);

        closeAllMenuItem.setText("Close All");
        closeAllMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeAllMenuItemActionPerformed(evt);
            }
        });
        m_windowMenu.add(closeAllMenuItem);

        menuBar.add(m_windowMenu);

        setJMenuBar(menuBar);

        getAccessibleContext().setAccessibleName("WindowManager ");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void m_outlineDragModeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_outlineDragModeMenuItemActionPerformed

        m_windowManager.setOutlineDragMode(
            m_outlineDragModeMenuItem.isSelected() );
    }//GEN-LAST:event_m_outlineDragModeMenuItemActionPerformed

    private void m_deiconifiablePolicyMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_deiconifiablePolicyMenuItemActionPerformed

        m_windowManager.setDeiconifiablePolicy(
            m_deiconifiablePolicyMenuItem.isSelected() );
    }//GEN-LAST:event_m_deiconifiablePolicyMenuItemActionPerformed

    private void autoPositionMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_autoPositionMenuItemActionPerformed

        m_windowManager.setAutoPositionPolicy(
            autoPositionMenuItem.isSelected() );
    }//GEN-LAST:event_autoPositionMenuItemActionPerformed

    private void m_closePolicyMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_closePolicyMenuItemActionPerformed

        m_windowManager.setClosePolicy( m_closePolicyMenuItem.isSelected() );
    }//GEN-LAST:event_m_closePolicyMenuItemActionPerformed

    private void cascadeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cascadeMenuItemActionPerformed

        m_windowManager.cascade();
    }//GEN-LAST:event_cascadeMenuItemActionPerformed

    private void tileHMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tileHMenuItemActionPerformed

        m_windowManager.tileHorizontally();
    }//GEN-LAST:event_tileHMenuItemActionPerformed

    private void tileVMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tileVMenuItemActionPerformed

        m_windowManager.tileVertically();
    }//GEN-LAST:event_tileVMenuItemActionPerformed

    private void tileMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tileMenuItemActionPerformed

        m_windowManager.tile();
    }//GEN-LAST:event_tileMenuItemActionPerformed

    private void nextMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextMenuItemActionPerformed

        m_windowManager.selectNext();
    }//GEN-LAST:event_nextMenuItemActionPerformed

    private void previousMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousMenuItemActionPerformed

        m_windowManager.selectPrevious();
    }//GEN-LAST:event_previousMenuItemActionPerformed

    private void minimizeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minimizeMenuItemActionPerformed

        m_windowManager.minimize();
    }//GEN-LAST:event_minimizeMenuItemActionPerformed

    private void minimizeAllMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minimizeAllMenuItemActionPerformed

        m_windowManager.minimizeAll();
    }//GEN-LAST:event_minimizeAllMenuItemActionPerformed

    private void restoreMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restoreMenuItemActionPerformed

        m_windowManager.restore();
    }//GEN-LAST:event_restoreMenuItemActionPerformed

    private void restoreAllMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restoreAllMenuItemActionPerformed

        m_windowManager.restoreAll();
    }//GEN-LAST:event_restoreAllMenuItemActionPerformed

    private void maximizeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maximizeMenuItemActionPerformed

        m_windowManager.maximize();
    }//GEN-LAST:event_maximizeMenuItemActionPerformed

    private void maximizeAllMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maximizeAllMenuItemActionPerformed

        m_windowManager.maximizeAll();
    }//GEN-LAST:event_maximizeAllMenuItemActionPerformed

    private void resetMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetMenuItemActionPerformed

        m_windowManager.reset();
    }//GEN-LAST:event_resetMenuItemActionPerformed

    private void resetAllMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetAllMenuItemActionPerformed

        m_windowManager.resetAll();
    }//GEN-LAST:event_resetAllMenuItemActionPerformed

    private void hideMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hideMenuItemActionPerformed

        m_windowManager.hide();
    }//GEN-LAST:event_hideMenuItemActionPerformed

    private void hideAllMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hideAllMenuItemActionPerformed

        m_windowManager.hideAll();
    }//GEN-LAST:event_hideAllMenuItemActionPerformed

    private void closeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeMenuItemActionPerformed

        m_windowManager.close();
    }//GEN-LAST:event_closeMenuItemActionPerformed

    private void closeAllMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeAllMenuItemActionPerformed

        m_windowManager.closeAll();
    }//GEN-LAST:event_closeAllMenuItemActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
         if(isValid(idno,"UL2")){
            GUIUtils.centerJF(farmerPanel, m_desktopPane);
            farmerPanel.formload(idno);
            farmerPanel.setVisible( true );
         }else{
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = BLUE>Access Denied please contact administrator</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields,"Dairy management system",JOptionPane.WARNING_MESSAGE);  
        }
//        GUIUtils.centerOnScreen(farmerPanel);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu2ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jMenu2ActionPerformed

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
        // TODO add your handling code here:
        if(isValid(idno,"UL1")){
         GUIUtils.centerJF(comp, m_desktopPane);
         comp.setVisible(true);
        }else{
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = BLUE>Access Denied please contact administrator</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields,"Dairy management system",JOptionPane.WARNING_MESSAGE);  
        }
    }//GEN-LAST:event_jMenu2MouseClicked

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        if(isValid(idno,"UL3")){
            GUIUtils.centerJF(collec, m_desktopPane);
            collec.getUserId(UserIdNo);
            collec.formload();
            collec.setVisible( true );
        }else{
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = BLUE>Access Denied please contact administrator</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields,"Dairy management system",JOptionPane.WARNING_MESSAGE);  
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void cutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cutMenuItemActionPerformed
        // TODO add your handling code here:
          GUIUtils.centerJF(sFrame, m_desktopPane);
          sFrame.formload();
          sFrame.setVisible( true );
    }//GEN-LAST:event_cutMenuItemActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        if(isValid(idno,"UL1")){
            GUIUtils.centerJF(admin, m_desktopPane);
    //        admin.getUserId(UserIdNo);
    //        admin.formload();
            admin.setVisible( true );
        }else{
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = BLUE>Access Denied please contact administrator</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields,"Dairy management system",JOptionPane.WARNING_MESSAGE);  
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        if(isValid(idno,"UL2")){
            loanFrm.getUserid(idno);
            GUIUtils.centerJF(loanFrm, m_desktopPane);
            loanFrm.setVisible( true );
         }else{
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = BLUE>Access Denied please contact administrator</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields,"Dairy management system",JOptionPane.WARNING_MESSAGE);  
        }
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        // TODO add your handling code here:
        if(isValid(idno,"UL2")){
            com.getUserid(idno);
            GUIUtils.centerJF(com, m_desktopPane);
            com.setVisible( true );
         }else{
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = BLUE>Access Denied please contact administrator</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields,"Dairy management system",JOptionPane.WARNING_MESSAGE);  
        }
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // TODO add your handling code here:
        if(isValid(idno,"UL5")){
//            com.getUserid(idno);
            store.formload(idno);
            GUIUtils.centerJF(store, m_desktopPane);
            store.setVisible( true );
         }else{
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = BLUE>Access Denied please contact administrator</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields,"Dairy management system",JOptionPane.WARNING_MESSAGE);  
        }
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // TODO add your handling code here:
        if(isValid(idno,"UL1")){
//            com.getUserid(idno);
            factory.formload(idno);
            GUIUtils.centerJF(factory, m_desktopPane);
            factory.setVisible( true );
         }else{
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = BLUE>Access Denied please contact administrator</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields,"Dairy management system",JOptionPane.WARNING_MESSAGE);  
        }
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        // TODO add your handling code here:
        if(isValid(idno,"UL1")){
//            com.getUserid(idno);
            route.formload(idno);
            GUIUtils.centerJF(route, m_desktopPane);
            route.setVisible( true );
         }else{
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = BLUE>Access Denied please contact administrator</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields,"Dairy management system",JOptionPane.WARNING_MESSAGE);  
        }
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed
        // TODO add your handling code here:
         if(isValid(idno,"UL1")){
//            com.getUserid(idno);
            asset.formload(idno);
            GUIUtils.centerJF(asset, m_desktopPane);
            asset.setVisible( true );
         }else{
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = BLUE>Access Denied please contact administrator</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields,"Dairy management system",JOptionPane.WARNING_MESSAGE);  
        }
    }//GEN-LAST:event_jMenuItem19ActionPerformed

    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
        // TODO add your handling code here:
        if(isValid(idno,"UL1")){
             rpt=new clsReport(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
             rpt.report("", "rpt_assets.jasper");
         }else{
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = BLUE>Access Denied please contact administrator</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields,"Dairy management system",JOptionPane.WARNING_MESSAGE);  
        }
    }//GEN-LAST:event_jMenuItem18ActionPerformed

    private void jMenuItem23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem23ActionPerformed
        // TODO add your handling code here:
        if(isValid(idno,"UL1")){
            routerpt.formload();
            GUIUtils.centerJF(routerpt, m_desktopPane);
            routerpt.setVisible( true );
         }else{
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = BLUE>Access Denied please contact administrator</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields,"Dairy management system",JOptionPane.WARNING_MESSAGE);  
        }
    }//GEN-LAST:event_jMenuItem23ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        // TODO add your handling code here:
        if(isValid(idno,"UL4")){
//            com.getUserid(idno);
            saleframe.formload(idno);
            GUIUtils.centerJF(saleframe, m_desktopPane);
            saleframe.setVisible( true );
         }else{
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = BLUE>Access Denied please contact administrator</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields,"Dairy management system",JOptionPane.WARNING_MESSAGE);  
        }
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        // TODO add your handling code here:
        
        if(isValid(idno,"UL4")){
//            com.getUserid(idno);
            comp_sale.formload(idno);
            GUIUtils.centerJF(comp_sale, m_desktopPane);
            comp_sale.setVisible( true );
         }else{
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = BLUE>Access Denied please contact administrator</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields,"Dairy management system",JOptionPane.WARNING_MESSAGE);  
        }
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenuItem16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem16MouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jMenuItem16MouseClicked

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        // TODO add your handling code here:
        if(isValid(idno,"UL1")){
//            com.getUserid(idno);
            constant.formload(this.idno);
            GUIUtils.centerJF(constant, m_desktopPane);
            constant.setVisible( true );
         }else{
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = BLUE>Access Denied please contact administrator</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields,"Dairy management system",JOptionPane.WARNING_MESSAGE);  
        }
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void jMenuItem24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem24ActionPerformed
        // TODO add your handling code here:
        if(isValid(idno,"UL6")){
//            com.getUserid(idno);
            payroll.formload();
            GUIUtils.centerJF(payroll, m_desktopPane);
            payroll.setVisible( true );
         }else{
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = BLUE>Access Denied please contact administrator</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields,"Dairy management system",JOptionPane.WARNING_MESSAGE);  
        }
    }//GEN-LAST:event_jMenuItem24ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here: 
            edit.formload(idno);
            GUIUtils.centerJF(edit, m_desktopPane);
            edit.setVisible( true );
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem2MouseClicked
        // TODO add your handling code here:
           
    }//GEN-LAST:event_jMenuItem2MouseClicked

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        if(isValid(idno,"UL1")){
//            com.getUserid(idno);
            collection.formload();
            GUIUtils.centerJF(collection, m_desktopPane);
            collection.setVisible( true );
         }else{
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = BLUE>Access Denied please contact administrator</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields,"Dairy management system",JOptionPane.WARNING_MESSAGE);  
        }
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem25ActionPerformed
        // TODO add your handling code here:
        if(isValid(idno,"UL1")){
//            com.getUserid(idno);
            c_collection.formload(idno);
            GUIUtils.centerJF(c_collection, m_desktopPane);
            c_collection.setVisible( true );
         }else{
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = BLUE>Access Denied please contact administrator</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields,"Dairy management system",JOptionPane.WARNING_MESSAGE);  
        }
    }//GEN-LAST:event_jMenuItem25ActionPerformed

    private void jMenuItem26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem26ActionPerformed
        // TODO add your handling code here:
        if(isValid(idno,"UL1")){
//            com.getUserid(idno);
//            collection.formload();
            GUIUtils.centerJF(c_rate, m_desktopPane);
            c_rate.setVisible( true );
         }else{
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = BLUE>Access Denied please contact administrator</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields,"Dairy management system",JOptionPane.WARNING_MESSAGE);  
        }
    }//GEN-LAST:event_jMenuItem26ActionPerformed

    private void jMenuItem27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem27ActionPerformed
        // TODO add your handling code here:
        if(isValid(idno,"UL1")){
//            com.getUserid(idno);
            depts.formload();
            GUIUtils.centerJF(depts, m_desktopPane);
            depts.setVisible( true );
         }else{
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = BLUE>Access Denied please contact administrator</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields,"Dairy management system",JOptionPane.WARNING_MESSAGE);  
        }
    }//GEN-LAST:event_jMenuItem27ActionPerformed

    private void jMenuItem28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem28ActionPerformed
        // TODO add your handling code here:
        if(isValid(idno,"UL1")){
//            com.getUserid(idno);
            expense.formload();
            GUIUtils.centerJF(expense, m_desktopPane);
            expense.setVisible( true );
         }else{
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = BLUE>Access Denied please contact administrator</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields,"Dairy management system",JOptionPane.WARNING_MESSAGE);  
        }
    }//GEN-LAST:event_jMenuItem28ActionPerformed

    private void jMenuItem29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem29ActionPerformed
        // TODO add your handling code here:
        if(isValid(idno,"UL1")){
//            com.getUserid(idno);
            codes.formload();
            GUIUtils.centerJF(codes, m_desktopPane);
            codes.setVisible( true );
         }else{
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = BLUE>Access Denied please contact administrator</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields,"Dairy management system",JOptionPane.WARNING_MESSAGE);  
        }
    }//GEN-LAST:event_jMenuItem29ActionPerformed

    private void jMenuItem30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem30ActionPerformed
        // TODO add your handling code here:
         if(isValid(idno,"UL1")){
//            com.getUserid(idno);
            deductions.formload(idno);
            GUIUtils.centerJF(deductions, m_desktopPane);
            deductions.setVisible( true );
         }else{
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = BLUE>Access Denied please contact administrator</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields,"Dairy management system",JOptionPane.WARNING_MESSAGE);  
        }
    }//GEN-LAST:event_jMenuItem30ActionPerformed

    private void jMenuItem31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem31ActionPerformed
        // TODO add your handling code here:
        if(isValid(idno,"UL1")){
//            com.getUserid(idno);
            c_payment.formload();
            GUIUtils.centerJF(c_payment, m_desktopPane);
            c_payment.setVisible( true );
         }else{
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = BLUE>Access Denied please contact administrator</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields,"Dairy management system",JOptionPane.WARNING_MESSAGE);  
        }
    }//GEN-LAST:event_jMenuItem31ActionPerformed

    private void jMenuItem32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem32ActionPerformed
        // TODO add your handling code here:
        if(isValid(idno,"UL1")){
//            com.getUserid(idno);
            crpt.formload();
            GUIUtils.centerJF(crpt, m_desktopPane);
            crpt.setVisible( true );
         }else{
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = BLUE>Access Denied please contact administrator</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields,"Dairy management system",JOptionPane.WARNING_MESSAGE);  
        }
    }//GEN-LAST:event_jMenuItem32ActionPerformed

    private void jCheckBoxMenuItem1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem1MouseClicked
        // TODO add your handling code here:
         if(jCheckBoxMenuItem1.isSelected()){
            lnf="Lipstik";
            chckbxLnF();
            this.setLnF();
        }else{

        }
    }//GEN-LAST:event_jCheckBoxMenuItem1MouseClicked

    private void chckbxAcrylMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chckbxAcrylMouseClicked
        // TODO add your handling code here:
        if(chckbxAcryl.isSelected()){
            lnf="Acryl";
            chckbxLnF();
            this.setLnF();
        }else{

        }
    }//GEN-LAST:event_chckbxAcrylMouseClicked

    private void jCheckBoxMenuItem2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem2MouseClicked
        // TODO add your handling code here:
        if(jCheckBoxMenuItem2.isSelected()){
            lnf="Aero";
            chckbxLnF();
            this.setLnF();

        }else{

        }
    }//GEN-LAST:event_jCheckBoxMenuItem2MouseClicked

    private void jCheckBoxMenuItem3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem3MouseClicked
        // TODO add your handling code here:
        if(jCheckBoxMenuItem3.isSelected()){
            lnf="Aluminium";
            chckbxLnF();
            this.setLnF();
        }else{

        }
    }//GEN-LAST:event_jCheckBoxMenuItem3MouseClicked

    private void jCheckBoxMenuItem4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem4MouseClicked
        // TODO add your handling code here:
         if(jCheckBoxMenuItem4.isSelected()){
            lnf="Bernstein";
            chckbxLnF();
            this.setLnF();
        }else{

        }
    }//GEN-LAST:event_jCheckBoxMenuItem4MouseClicked

    private void jCheckBoxMenuItem5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem5MouseClicked
        // TODO add your handling code here:
        if(jCheckBoxMenuItem5.isSelected()){
            lnf="Fast";
            chckbxLnF();
            this.setLnF();
        }else{

        }
    }//GEN-LAST:event_jCheckBoxMenuItem5MouseClicked

    private void jCheckBoxMenuItem6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem6MouseClicked
        // TODO add your handling code here:
         if(jCheckBoxMenuItem6.isSelected()){
            lnf="Hifi";
            chckbxLnF();
            chckbxLnF();
            setLnF();
        }else{

        }
    }//GEN-LAST:event_jCheckBoxMenuItem6MouseClicked

    private void jCheckBoxMenuItem7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem7MouseClicked
        // TODO add your handling code here:
         if(jCheckBoxMenuItem7.isSelected()){
            lnf="Luna";
            this.setLnF();
            chckbxLnF();
        }else{

        }
    }//GEN-LAST:event_jCheckBoxMenuItem7MouseClicked

    private void jCheckBoxMenuItem8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem8MouseClicked
        // TODO add your handling code here:
         if(jCheckBoxMenuItem8.isSelected()){
            lnf="Mint";
            this.setLnF();
            chckbxLnF();
        }else{

        }
    }//GEN-LAST:event_jCheckBoxMenuItem8MouseClicked

    private void chckbxMacWinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chckbxMacWinMouseClicked
        // TODO add your handling code here:
        if(chckbxMacWin.isSelected()){
            lnf="MacWindows";
            this.setLnF();
            chckbxLnF();
        }else{

        }
    }//GEN-LAST:event_chckbxMacWinMouseClicked

    private void jCheckBoxMenuItem10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem10MouseClicked
        // TODO add your handling code here:
        if(jCheckBoxMenuItem10.isSelected()){
            lnf="Noire";
            this.setLnF();
            chckbxLnF();
        }else{

        }
    }//GEN-LAST:event_jCheckBoxMenuItem10MouseClicked

    private void jCheckBoxMenuItem11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem11MouseClicked
        // TODO add your handling code here:
         if(jCheckBoxMenuItem11.isSelected()){
            lnf="Smart";
            this.setLnF();
            chckbxLnF();
        }else{

        }
    }//GEN-LAST:event_jCheckBoxMenuItem11MouseClicked

    private void jCheckBoxMenuItem12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem12MouseClicked
        // TODO add your handling code here:
        if(jCheckBoxMenuItem12.isSelected()){
            lnf="Texture";
            this.setLnF();
            chckbxLnF();
        }else{

        }
    }//GEN-LAST:event_jCheckBoxMenuItem12MouseClicked

    private void jCheckBoxMenuItem13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem13MouseClicked
        // TODO add your handling code here:
         if(jCheckBoxMenuItem13.isSelected()){
            lnf="Kunststoff";
            this.setLnF();
            chckbxLnF();
        }else{

        }
    }//GEN-LAST:event_jCheckBoxMenuItem13MouseClicked

    private void jCheckBoxMenuItem14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem14MouseClicked
        // TODO add your handling code here:
         if(jCheckBoxMenuItem14.isSelected()){
            lnf="Seaglass";
            this.setLnF();
            chckbxLnF();
        }else{

        }
    }//GEN-LAST:event_jCheckBoxMenuItem14MouseClicked

    private void jCheckBoxMenuItem15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem15MouseClicked
        // TODO add your handling code here:
         if(jCheckBoxMenuItem15.isSelected()){
            lnf="Digitprop";
            this.setLnF();
            chckbxLnF();
        }else{

        }
    }//GEN-LAST:event_jCheckBoxMenuItem15MouseClicked

    private void jMenuItem33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem33ActionPerformed
        // TODO add your handling code here:
         if(isValid(idno,"UL1")){
//            com.getUserid(idno);
            p_rpt.formload();
            GUIUtils.centerJF(p_rpt, m_desktopPane);
            p_rpt.setVisible( true );
         }else{
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = BLUE>Access Denied please contact administrator</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields,"Dairy management system",JOptionPane.WARNING_MESSAGE);  
        }
    }//GEN-LAST:event_jMenuItem33ActionPerformed

    private void jMenuItem34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem34ActionPerformed
        // TODO add your handling code here:
        if(isValid(idno,"UL1")){
             rpt=new clsReport(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
             rpt.report("", "coffee_farmers.jasper");
         }else{
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = BLUE>Access Denied please contact administrator</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields,"Dairy management system",JOptionPane.WARNING_MESSAGE);  
        }
    }//GEN-LAST:event_jMenuItem34ActionPerformed

    private void cutMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cutMenuItem1ActionPerformed
        // TODO add your handling code here:
          GUIUtils.centerJF(sFrame, m_desktopPane);
          sFrame.formload();
          sFrame.setVisible( true );
    }//GEN-LAST:event_cutMenuItem1ActionPerformed

    private void cutMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cutMenuItem2ActionPerformed
        // TODO add your handling code here:
          GUIUtils.centerJF(sFrame, m_desktopPane);
          sFrame.formload();
          sFrame.setVisible( true );
    }//GEN-LAST:event_cutMenuItem2ActionPerformed

    private void jMenuItem35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem35ActionPerformed
        // TODO add your handling code here:
        if(isValid(idno,"UL1")){
//            com.getUserid(idno);
            m_invoice.formload();
            GUIUtils.centerJF(m_invoice, m_desktopPane);
            m_invoice.setVisible( true );
         }else{
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = BLUE>Access Denied please contact administrator</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields,"Dairy management system",JOptionPane.WARNING_MESSAGE);  
        }
    }//GEN-LAST:event_jMenuItem35ActionPerformed

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        // TODO add your handling code here:
        if(isValid(idno,"UL2")){
            GUIUtils.centerJF(farmerPanel, m_desktopPane);
            farmerPanel.formload(idno);
            farmerPanel.setVisible( true );
         }else{
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = BLUE>Access Denied please contact administrator</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields,"Dairy management system",JOptionPane.WARNING_MESSAGE);  
        }
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        // TODO add your handling code here:
         if(isValid(idno,"UL3")){
            GUIUtils.centerJF(collec, m_desktopPane);
            collec.getUserId(UserIdNo);
            collec.formload();
            collec.setVisible( true );
        }else{
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = BLUE>Access Denied please contact administrator</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields,"Dairy management system",JOptionPane.WARNING_MESSAGE);  
        }
    }//GEN-LAST:event_jLabel10MouseClicked

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        // TODO add your handling code here:
        if(isValid(idno,"UL1")){
//            com.getUserid(idno);
            p_rpt.formload();
            GUIUtils.centerJF(p_rpt, m_desktopPane);
            p_rpt.setVisible( true );
         }else{
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = BLUE>Access Denied please contact administrator</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields,"Dairy management system",JOptionPane.WARNING_MESSAGE);  
        }
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        // TODO add your handling code here:
        if(isValid(idno,"UL1")){
//            com.getUserid(idno);
            collection.formload();
            GUIUtils.centerJF(collection, m_desktopPane);
            collection.setVisible( true );
         }else{
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = BLUE>Access Denied please contact administrator</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields,"Dairy management system",JOptionPane.WARNING_MESSAGE);  
        }
    }//GEN-LAST:event_jLabel12MouseClicked

    private void jMenuItem3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3MouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        // TODO add your handling code here:
        if(isValid(idno,"UL1")){
            GUIUtils.centerJF(admin, m_desktopPane);
    //        admin.getUserId(UserIdNo);
    //        admin.formload();
            admin.setVisible( true );
        }else{
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = BLUE>Access Denied please contact administrator</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields,"Dairy management system",JOptionPane.WARNING_MESSAGE);  
        }
    }//GEN-LAST:event_jLabel13MouseClicked

    private void jMenuItem36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem36ActionPerformed
        // TODO add your handling code here:
         if(isValid(idno,"UL1")){
            GUIUtils.centerJF(store, m_desktopPane);
    //        admin.getUserId(UserIdNo);
            store.formload("");
            store.setVisible( true );
        }else{
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = BLUE>Access Denied please contact administrator</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields,"Dairy management system",JOptionPane.WARNING_MESSAGE);  
        }
    }//GEN-LAST:event_jMenuItem36ActionPerformed

    private void jMenuItem42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem42ActionPerformed
        // TODO add your handling code here:
        if(isValid(idno,"UL1")){
            GUIUtils.centerJF(journal, m_desktopPane);
    //        admin.getUserId(UserIdNo);
            journal.formload();
            journal.setVisible( true );
        }else{
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = BLUE>Access Denied please contact administrator</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields,"Dairy management system",JOptionPane.WARNING_MESSAGE);  
        }
    }//GEN-LAST:event_jMenuItem42ActionPerformed

    private void jMenuItem43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem43ActionPerformed
        // TODO add your handling code here:
        if(isValid(idno,"UL1")){
          rpt=new clsReport();
          rpt.report("", "rpt_farmer_payment_accounts.jasper");
        }else{
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = BLUE>Access Denied please contact administrator</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields,"Dairy management system",JOptionPane.WARNING_MESSAGE);  
        }
    }//GEN-LAST:event_jMenuItem43ActionPerformed

    private void jMenuItem44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem44ActionPerformed
        // TODO add your handling code here:
        if(isValid(idno,"UL1")){
            GUIUtils.centerJF(msales, m_desktopPane);
    //        admin.getUserId(UserIdNo);
            msales.formload();
            msales.setVisible( true );
        }else{
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = BLUE>Access Denied please contact administrator</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields,"Dairy management system",JOptionPane.WARNING_MESSAGE);  
        }
    }//GEN-LAST:event_jMenuItem44ActionPerformed

    private void jMenuItem45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem45ActionPerformed
        // TODO add your handling code here:
        if(isValid(idno,"UL1")){
            GUIUtils.centerJF(collec_summary, m_desktopPane);
    //        admin.getUserId(UserIdNo);
            collec_summary.setVisible( true );
        }else{
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = BLUE>Access Denied please contact administrator</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields,"Dairy management system",JOptionPane.WARNING_MESSAGE);  
        }
    }//GEN-LAST:event_jMenuItem45ActionPerformed

    private void jMenuItem46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem46ActionPerformed
        // TODO add your handling code here:
        if(isValid(idno,"UL1")){
            GUIUtils.centerJF(report, m_desktopPane);
            report.formload("");
    //        admin.getUserId(UserIdNo);
            report.setVisible( true );
        }else{
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = BLUE>Access Denied please contact administrator</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields,"Dairy management system",JOptionPane.WARNING_MESSAGE);  
        }
    }//GEN-LAST:event_jMenuItem46ActionPerformed
public String getLnF(){
     return lnf;
 }
    
     public void setLnF(){
//     final JFrame frame=(JFrame) this.getComponent(0).getParent();
      SwingUtilities.invokeLater(new Runnable(){          
            @Override
      public void run(){
         try {
             chckbxLnF();
             if(getLnF().equals("mac")){
//               LiquidLookAndFeel.setLiquidDecorations(true,"panther");
                 UIManager.setLookAndFeel(new  McWinLookAndFeel());
//               javax.swing.UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");  
             }   
             if(getLnF().equals("Lipstik")){
                 UIManager.setLookAndFeel(new  LipstikLookAndFeel());
//               javax.swing.UIManager.setLookAndFeel("com.lipstikLF.LipstikLookAndFeel");
             }
             if(getLnF().equals("Acryl")){
                 UIManager.setLookAndFeel(new  AcrylLookAndFeel());
//               javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
             }
             if(getLnF().equals("Aero")){
                 UIManager.setLookAndFeel(new  AeroLookAndFeel());
//               javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
             }
             if(getLnF().equals("Aluminium")){
                 UIManager.setLookAndFeel(new  AluminiumLookAndFeel());
//                 javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
             }
             if(getLnF().equals("Bernstein")){
                 UIManager.setLookAndFeel(new  BernsteinLookAndFeel());
//                javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
             }
             if(getLnF().equals("Fast")){
                 UIManager.setLookAndFeel(new  FastLookAndFeel());
//               javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.fast.FastLookAndFeel");
             }
             if(getLnF().equals("Hifi")){
                 UIManager.setLookAndFeel(new  HiFiLookAndFeel());
//                javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
             }
             if(getLnF().equals("Luna")){
                 UIManager.setLookAndFeel(new  LunaLookAndFeel());
//              javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel");
             }
             if(getLnF().equals("Mint")){
                 UIManager.setLookAndFeel(new  MintLookAndFeel());
//              javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
             }
             if(getLnF().equals("MacWindows")){
                 UIManager.setLookAndFeel(new  McWinLookAndFeel());
//              javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
             }
             if(getLnF().equals("Noire")){
                 UIManager.setLookAndFeel(new  NoireLookAndFeel());
//              javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
             }
             if(getLnF().equals("Smart")){
                 UIManager.setLookAndFeel(new  SmartLookAndFeel());
//              javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
             }
             if(getLnF().equals("Texture")){
                 UIManager.setLookAndFeel(new  TextureLookAndFeel());
//              javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
             }
             if(getLnF().equals("Kunststoff")){
//                 UIManager.setLookAndFeel(new  KunststoffLookAndFeel());
                 //incors.plaf.kunststoff.KunststoffLookAndFeel";
//              javax.swing.UIManager.setLookAndFeel("com.incors.plaf.kunststoff.KunststoffLookAndFeel");
             }
             if(getLnF().equals("Seaglass")){
                 UIManager.setLookAndFeel(new  SeaGlassLookAndFeel());
//              javax.swing.UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
             }
             if(getLnF().equals("Oyoaha")){
//                 lnf="com.jtattoo.plaf.mcwin.McWinLookAndFeel";
//                 lnf="com.oyoaha.swing.plaf.oyoaha.OyoahaLookAndFeel";
//              javax.swing.UIManager.setLookAndFeel("com.oyoaha.swing.plaf.oyoaha.OyoahaLookAndFeel");
             }
             if(getLnF().equals("Digitprop")){
                 UIManager.setLookAndFeel(new  TonicLookAndFeel());
//               javax.swing.UIManager.setLookAndFeel("com.digitprop.tonic.TonicLookAndFeel");
             }
             
             if(lnf==null){
                 UIManager.setLookAndFeel(new  AeroLookAndFeel());
             }else{UIManager.setLookAndFeel(new  TextureLookAndFeel());
             }  
//             new  AeroLookAndFeel();
                   
                   SwingUtilities.updateComponentTreeUI(Application.this); 
                   UIDefaults defaults = UIManager.getLookAndFeelDefaults();
                   if (defaults.get("Table.alternateRowColor") == null)
                       defaults.put("Table.alternateRowColor", new Color(240, 240, 240));
//               if(!UIManager.getLookAndFeel().getClass().getName().equals(lnf)){
//                                
//               }
               
               editLnF(idno, getLnF());
//               frame.pack();
      } catch (javax.swing.UnsupportedLookAndFeelException | NullPointerException ex) {
               JOptionPane.showMessageDialog(null,ex);
//            java.util.logging.Logger.getLogger(JDialog_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
      }});
}
// 
 public int chckbxLnF(){
    String val=lnf;
    if(val.equals("LipstikLF")){
       jCheckBoxMenuItem1.setSelected(true); 
    }else{
        jCheckBoxMenuItem1.setSelected(false);
    }
     if(val.equals("Acryl")){
       chckbxAcryl.setSelected(true); 
    }else{
       chckbxAcryl.setSelected(false);  
     }
    if(val.equals("Aero")){
       jCheckBoxMenuItem2.setSelected(true); 
    }else{
       jCheckBoxMenuItem2.setSelected(false); 
    }
    if(val.equals("Aluminium")){
       jCheckBoxMenuItem3.setSelected(true); 
    }else{
       jCheckBoxMenuItem3.setSelected(false); 
    }
    if(val.equals("Bernstein")){
       jCheckBoxMenuItem4.setSelected(true); 
    }else{
       jCheckBoxMenuItem4.setSelected(false); 
    }
    if(val.equals("Fast")){
       jCheckBoxMenuItem5.setSelected(true); 
    }else{
       jCheckBoxMenuItem5.setSelected(false); 
    }
    if(val.equals("Hifi")){
       jCheckBoxMenuItem6.setSelected(true); 
    }else{
       jCheckBoxMenuItem6.setSelected(false);  
    }
    if(val.equals("Luna")){
       jCheckBoxMenuItem7.setSelected(true); 
    }else{
       jCheckBoxMenuItem7.setSelected(false);  
    }
    if(val.equals("Mint")){
       jCheckBoxMenuItem8.setSelected(true); 
    }else{
        jCheckBoxMenuItem8.setSelected(false); 
    }
    if(val.equals("MacWindows")){
       chckbxMacWin.setSelected(true); 
    }else{
       chckbxMacWin.setSelected(false);  
    }
    if(val.equals("Noire")){
       jCheckBoxMenuItem10.setSelected(true); 
    }else{
      jCheckBoxMenuItem10.setSelected(false);   
    }
    if(val.equals("Smart")){
       jCheckBoxMenuItem11.setSelected(true); 
    }else{
       jCheckBoxMenuItem11.setSelected(false); 
    }
    if(val.equals("Texture")){
       jCheckBoxMenuItem12.setSelected(true); 
    }else{
       jCheckBoxMenuItem12.setSelected(false);  
    }
    if(val.equals("Kunststoff")){
       jCheckBoxMenuItem13.setSelected(true); 
    }else{
       jCheckBoxMenuItem13.setSelected(false); 
    }
    if(val.equals("Seaglass")){
       jCheckBoxMenuItem14.setSelected(true); 
    }else{
       jCheckBoxMenuItem14.setSelected(false);  
    }
    if(val.equals("Digitprop")){
       jCheckBoxMenuItem15.setSelected(true); 
    }else{
       jCheckBoxMenuItem15.setSelected(false);  
    }
    return 0;
}  
 
 
 public void UserDetails(String userid ){
       Session session = HibernateUtil.getSessionFactory().openSession();
       Transaction tx = null;
      try{
         tx = session.beginTransaction();
         DtUsers user = (DtUsers)session.get(DtUsers.class, userid);
         if(user!=null){
             DtUsers details=user;
             loggedin(details);          
         }else
             resetfields();
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
   }
 
 public void loggedin(DtUsers details) {      
   UserFname=details.getFname();
   lnf=details.getLooknFeel();
   UserLname=details.getLname();
   UserIdNo=details.getIdNo();
   Useraddress=details.getAddress();
   Userphone=details.getPhone();
   this.setTitle("Dairy Management System - Version:1.0");
 }
 
 public void resetfields(){
   UserFname="";
   UserLname="";
   UserIdNo="";
   Useraddress="";
   Userphone=""; 
 }
 
  public void editLnF(String userid, String lnf){
      Session session = HibernateUtil.getSessionFactory().openSession();
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         DtUsers user = (DtUsers)session.get(DtUsers.class,userid);
         if(user!=null){
            user.setLooknFeel(lnf);
            session.update(user);
         }
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
//           e.printStackTrace(); 
      }finally {
         session.close(); 
      }
   }
  
  public boolean isValid(String Idno,String userlevel){
         boolean value=false;
         Session session = HibernateUtil.getSessionFactory().openSession();
         Transaction tx = null;
         String level="";
        try{
            tx = session.beginTransaction();
            DtUserprivileges user = (DtUserprivileges)session.get(DtUserprivileges.class,Idno);
            if(user!=null){
                if(userlevel.equals("UL1")){
                    level=user.getUl1();
                }
                if(userlevel.equals("UL2")){
                    level=user.getUl2();
                }
                if(userlevel.equals("UL3")){
                    level=user.getUl3();
                }
                if(userlevel.equals("UL4")){
                    level=user.getUl4();
                }
                if(userlevel.equals("UL5")){
                    level=user.getUl5();
                }
                if(userlevel.equals("UL6")){
                    level=user.getUl6();
                }
                if(userlevel.equals("UL7")){
                    level=user.getUl7();
                }
                if(userlevel.equals("UL8")){
                    level=user.getUl8();
                }
                if(userlevel.equals("UL9")){
                    level=user.getUl9();
                }
                if(userlevel.equals("UL1")){
                    level=user.getUl1();
                }
                if(level.equalsIgnoreCase("GRANTED")){
                    value=true;
                    tx.commit();
                }     
            }
        }catch (HibernateException e) {
          if (tx!=null) tx.rollback();
        //           e.printStackTrace(); 
        }finally {
          session.close(); 
        }
       return value;
}
  
  
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
                if ("Nimbus".equals(info.getName())) {
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
                new Application().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JCheckBoxMenuItem autoPositionMenuItem;
    private javax.swing.JCheckBoxMenuItem chckbxAcryl;
    private javax.swing.JCheckBoxMenuItem chckbxMacWin;
    private javax.swing.JMenuItem contentMenuItem;
    private javax.swing.JMenuItem copyMenuItem;
    private javax.swing.JMenuItem cutMenuItem;
    private javax.swing.JMenuItem cutMenuItem1;
    private javax.swing.JMenuItem cutMenuItem2;
    private javax.swing.JMenuItem deleteMenuItem;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem10;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem11;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem12;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem13;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem14;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem15;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem2;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem3;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem4;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem5;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem6;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem7;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu12;
    private javax.swing.JMenu jMenu13;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem25;
    private javax.swing.JMenuItem jMenuItem26;
    private javax.swing.JMenuItem jMenuItem27;
    private javax.swing.JMenuItem jMenuItem28;
    private javax.swing.JMenuItem jMenuItem29;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem30;
    private javax.swing.JMenuItem jMenuItem31;
    private javax.swing.JMenuItem jMenuItem32;
    private javax.swing.JMenuItem jMenuItem33;
    private javax.swing.JMenuItem jMenuItem34;
    private javax.swing.JMenuItem jMenuItem35;
    private javax.swing.JMenuItem jMenuItem36;
    private javax.swing.JMenuItem jMenuItem37;
    private javax.swing.JMenuItem jMenuItem38;
    private javax.swing.JMenuItem jMenuItem39;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem40;
    private javax.swing.JMenuItem jMenuItem41;
    private javax.swing.JMenuItem jMenuItem42;
    private javax.swing.JMenuItem jMenuItem43;
    private javax.swing.JMenuItem jMenuItem44;
    private javax.swing.JMenuItem jMenuItem45;
    private javax.swing.JMenuItem jMenuItem46;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JRadioButton jRadioButton8;
    private javax.swing.JRadioButton jRadioButton9;
    private javax.swing.JPopupMenu.Separator jSeparator10;
    private javax.swing.JPopupMenu.Separator jSeparator11;
    private javax.swing.JPopupMenu.Separator jSeparator12;
    private javax.swing.JPopupMenu.Separator jSeparator13;
    private javax.swing.JPopupMenu.Separator jSeparator14;
    private javax.swing.JPopupMenu.Separator jSeparator15;
    private javax.swing.JPopupMenu.Separator jSeparator16;
    private javax.swing.JPopupMenu.Separator jSeparator17;
    private javax.swing.JPopupMenu.Separator jSeparator18;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JSeparator jSeparator20;
    private javax.swing.JSeparator jSeparator21;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JPopupMenu.Separator jSeparator8;
    private javax.swing.JPopupMenu.Separator jSeparator9;
    private javax.swing.JCheckBoxMenuItem m_closePolicyMenuItem;
    private javax.swing.JCheckBoxMenuItem m_deiconifiablePolicyMenuItem;
    private javax.swing.JDesktopPane m_desktopPane;
    private javax.swing.JCheckBoxMenuItem m_outlineDragModeMenuItem;
    private javax.swing.JMenu m_windowMenu;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenuItem pasteMenuItem;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    // End of variables declaration//GEN-END:variables
}
