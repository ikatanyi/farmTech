/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import classes.rowcount;
import classes.superCls;
import custom_jPanels.RoundRectGradientPanel;
import custom_jPanels.SimpleGradientPanel;
import farm.entity.DtInventoryItemlist;
import farmTech.util.HibernateUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Kent
 */
public class item_master extends javax.swing.JInternalFrame {

    private JLabel errorFields;
    private Object []productcode;
    private Object []itemId;
    private Object []supplierId;
    private DefaultComboBoxModel item_model=new DefaultComboBoxModel();
    private DefaultComboBoxModel package1_model=new DefaultComboBoxModel();
    private DefaultComboBoxModel category_model=new DefaultComboBoxModel();
    private DefaultComboBoxModel uom_model=new DefaultComboBoxModel();
    private DefaultComboBoxModel suppliers_model =new DefaultComboBoxModel();
    superCls sup=new superCls();
    DtInventoryItemlist inventory;
    /**
     * Creates new form item_master
     */
    public item_master() {
        initComponents();
    }

    public void formload(){
        
        SwingWorker doComputeRate=new SwingWorker() {
                  @Override
	          protected Integer doInBackground() throws Exception{
                    getItems ();
                    supplier ("active");
                    cmb_name.setModel(item_model);
                    cmb_category.setModel(category_model);
                    cmb_package.setModel(package1_model);
                    cmb_UOM.setModel(uom_model);
                    cmb_supplier.setModel(suppliers_model);
                  return 0;
	        }
                @Override
                protected void done(){
                    
                }
        };
        doComputeRate.execute();
        resetFields();
    }

    private void getItems () {                 
     int i=0;
     DefaultComboBoxModel tmodel =new DefaultComboBoxModel();
     org.hibernate.Session session = HibernateUtil.getSessionFactory().openSession();
     try {        
           session.beginTransaction();
           Query q = session.createQuery("SELECT DISTINCT d.productCode,d.id, d.itemName, d.category, d.package1, d.uom FROM DtInventoryItemlist d");
           q.setCacheable(true);
           List auth=q.list();
           productcode=new Object[auth.size()+1];
           itemId=new Object[auth.size()+1];
           Iterator iterator=auth.iterator();
           productcode[i]="0";
           itemId[i]=0;
           
           item_model.removeAllElements();
           package1_model.removeAllElements();
           category_model.removeAllElements();
           uom_model.removeAllElements();
           
           item_model.addElement("");
           package1_model.addElement("");
           category_model.addElement("");
           uom_model.addElement("");
           for(Object o:auth){
               Object[] val=(Object[])o; i++; 
               productcode[i]=val[0];
               itemId[i]=val[1];
               item_model.addElement(val[2]);
               category_model.addElement(val[3]);
               package1_model.addElement(val[4]);               
               uom_model.addElement(val[5]);                           
       }
       session.getTransaction().commit();
      }catch (HibernateException he) {
             errorFields = new JLabel("<HTML><FONT COLOR = Blue>'"+he.getMessage()+"'</FONT></HTML>");
             JOptionPane.showMessageDialog(null,errorFields,"RIM",JOptionPane.ERROR_MESSAGE);

      }finally {
         session.close(); 
      }
     }
    
     private void supplier (String type) {                 
       int i=0;
       Query q;
       DefaultComboBoxModel tmodel =new DefaultComboBoxModel();
       org.hibernate.Session session = HibernateUtil.getSessionFactory().openSession();
      try {        
           session.beginTransaction();
           if(type.equals("All"))
             q = session.createQuery("SELECT DISTINCT s.suppliername, s.supplierId from DtSuppliers s");
           else
              q = session.createQuery("SELECT DISTINCT s.suppliername, s.supplierId from DtSuppliers s where s.status='Active'"); 
           q.setCacheable(true);
           List auth=q.list();
           Iterator iterator=auth.iterator();
           supplierId=new Object[auth.size()+1];
           suppliers_model.addElement(""); 
           supplierId[i]="";
           for(Object o:auth){ 
                   Object[] supp=(Object[])o; i++;
                   suppliers_model.addElement(supp[0]); 
                   supplierId[i]=supp[1];                            
           }
           session.getTransaction().commit();
         }catch (HibernateException he) {
                 errorFields = new JLabel("<HTML><FONT COLOR = Blue>'"+he.getMessage()+"'</FONT></HTML>");
                 JOptionPane.showMessageDialog(null,errorFields,"Clinic Management System",JOptionPane.ERROR_MESSAGE);

         }finally {
             session.close(); 
         }
    }
     
     public void resetFields(){
         String val=String.valueOf(new rowcount().row_count(DtInventoryItemlist.class)+1);
         String p_code="PC"+sup.getcode(5, val);
         cmb_name.setSelectedIndex(-1);
         txtProduct_code.setText(p_code);
         cmb_category.setSelectedIndex(-1);
         cmb_package.setSelectedIndex(-1);
         cmb_UOM.setSelectedIndex(-1);
         cmb_supplier.setSelectedIndex(-1);
         txt_limit.setText("0.00");
         txt_stock.setText("0.00");
         txtPO_cost.setText("0.00");
         txtUnit_cost.setText("0.00");
         txtUnit_Price.setText("0.00");
         txt_model.setText("0.00");
         txtEng_pin.getText();
         txt_revision.getText();
         txtPack_units.setText("0");
         txt_pallet.getText();
         txtNet_wt.setText("0.00");
         txtGross_wt.setText("0.00");
         txtLead_days.setText("0");
         txtEconomy_order.setText("0");
         txtMonthly_demand.setText("0");
         txt_vat.setText("0.0");
         txt_discount.setText("0");
         txt_description.setText("");
     }
     
     public void setFields(){
         cmb_name.setSelectedItem(sup.getValue(inventory.getItemName()));
         txtProduct_code.setText(sup.getValue(inventory.getProductCode()));
         cmb_category.setSelectedItem(sup.getValue(inventory.getCategory()));
         cmb_package.setSelectedItem(sup.getValue(inventory.getPackage1()));
         cmb_UOM.setSelectedItem(sup.getValue(inventory.getUom()));
         cmb_supplier.setSelectedItem(sup.getValue(inventory.getSupplierCode()));
         txt_limit.setText(sup.getCostValue(inventory.getOrderLimit()));
         txt_stock.setText(sup.getCostValue(inventory.getStock()));
         txtPO_cost.setText(sup.getCostValue(inventory.getPoCost()));
         txtUnit_cost.setText(sup.getCostValue(inventory.getUnitCost()));
         txtUnit_Price.setText(sup.getCostValue(inventory.getUnitPrice()));
         txt_model.setText(sup.getValue(inventory.getModel()));
         txtEng_pin.setText(sup.getValue(inventory.getEngPin()));
         txt_revision.setText(sup.getValue(inventory.getRevision()));
         txtPack_units.setText(sup.getIntValue(inventory.getPackUnits()));
         txt_pallet.setText(sup.getValue(inventory.getPallet()));
         txtNet_wt.setText(sup.getCostValue(inventory.getNetWt()));
         txtGross_wt.setText(sup.getCostValue(inventory.getGrossWt()));
         txtLead_days.setText(sup.getCostValue(inventory.getLeadDays()));
         txtEconomy_order.setText(sup.getIntValue(inventory.getEcoOrder()));
         txtMonthly_demand.setText(sup.getIntValue(inventory.getMonthlyDemand()));
         txt_vat.setText(sup.getIntValue(inventory.getVat()));
         txt_discount.setText(sup.getIntValue(inventory.getDiscount()));
         txt_description.setText(inventory.getDescription());
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
        cmb_name = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        txtProduct_code = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new SimpleGradientPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cmb_category = new javax.swing.JComboBox();
        cmb_package = new javax.swing.JComboBox();
        cmb_UOM = new javax.swing.JComboBox();
        cmb_supplier = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        txt_limit = new javax.swing.JTextField();
        txt_stock = new javax.swing.JTextField();
        txtPO_cost = new javax.swing.JTextField();
        txtUnit_cost = new javax.swing.JTextField();
        txtUnit_Price = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txt_model = new javax.swing.JTextField();
        txtEng_pin = new javax.swing.JTextField();
        txt_revision = new javax.swing.JTextField();
        txtPack_units = new javax.swing.JTextField();
        txt_pallet = new javax.swing.JTextField();
        txtNet_wt = new javax.swing.JTextField();
        txtGross_wt = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtLead_days = new javax.swing.JTextField();
        txtEconomy_order = new javax.swing.JTextField();
        txtMonthly_demand = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txt_vat = new javax.swing.JTextField();
        txt_discount = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_description = new javax.swing.JTextArea();
        jLabel29 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel4 = new SimpleGradientPanel();
        jLabel24 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Inventory New Item");

        jPanel1.setPreferredSize(new java.awt.Dimension(774, 420));

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel1.setText("Name :");

        cmb_name.setEditable(true);
        cmb_name.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel2.setText("Code");

        txtProduct_code.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 255));
        jLabel3.setText("Inventory Item Master List");

        jPanel2.setLayout(new java.awt.CardLayout());

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel5.setText("Category :");

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel6.setText("Package :");

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel7.setText("Base UOM");

        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel8.setText("Main Supplier :");

        jLabel9.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel9.setText("Reorder at :");

        jLabel10.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel10.setText("Fill to :");

        jLabel11.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel11.setText("PO Cost :");

        jLabel12.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel12.setText("Unit Cost (Ksh):");

        cmb_category.setEditable(true);
        cmb_category.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        cmb_package.setEditable(true);
        cmb_package.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        cmb_UOM.setEditable(true);
        cmb_UOM.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        cmb_supplier.setEditable(true);
        cmb_supplier.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel13.setText("Unit Price :");

        txt_limit.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        txt_limit.setText("0.0");
        txt_limit.setToolTipText("");

        txt_stock.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        txt_stock.setText("0.0");
        txt_stock.setToolTipText("");

        txtPO_cost.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        txtPO_cost.setText("0.0");
        txtPO_cost.setToolTipText("");

        txtUnit_cost.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        txtUnit_cost.setText("0.0");
        txtUnit_cost.setToolTipText("");

        txtUnit_Price.setBackground(new java.awt.Color(255, 0, 0));
        txtUnit_Price.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        txtUnit_Price.setForeground(new java.awt.Color(255, 255, 255));
        txtUnit_Price.setText("0.0");
        txtUnit_Price.setToolTipText("");

        jLabel14.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel14.setText("Manufacturer/Model :");

        jLabel15.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel15.setText("Engineering P/N :");

        jLabel16.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel16.setText("Revision :");

        jLabel17.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel17.setText("Units Per Pack :");

        jLabel18.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel18.setText("Pallet Ti/Hi :");

        jLabel19.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel19.setText("Unit Net Wt :");

        jLabel20.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel20.setText("Unit Gross Wt :");

        jLabel21.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel21.setText("Lead Days :");

        txt_model.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        txt_model.setToolTipText("");

        txtEng_pin.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        txtEng_pin.setToolTipText("");

        txt_revision.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        txt_revision.setToolTipText("");

        txtPack_units.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        txtPack_units.setText("0.0");
        txtPack_units.setToolTipText("");

        txt_pallet.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        txt_pallet.setText("0.0");
        txt_pallet.setToolTipText("");

        txtNet_wt.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        txtNet_wt.setText("0.0");
        txtNet_wt.setToolTipText("");

        txtGross_wt.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        txtGross_wt.setText("0.0");
        txtGross_wt.setToolTipText("");

        jLabel22.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel22.setText("Economic Order :");

        jLabel23.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel23.setText("Monthly Demand :");

        txtLead_days.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        txtLead_days.setText("0.0");
        txtLead_days.setToolTipText("");

        txtEconomy_order.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        txtEconomy_order.setText("0.0");
        txtEconomy_order.setToolTipText("");

        txtMonthly_demand.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        txtMonthly_demand.setText("0.0");
        txtMonthly_demand.setToolTipText("");

        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("New");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel25.setText("VAT");

        jLabel26.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel26.setText("Discount%");

        jLabel27.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel27.setText("Discription :");

        txt_vat.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        txt_vat.setText("0.0");

        txt_discount.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        txt_discount.setText("0.0");

        txt_description.setColumns(20);
        txt_description.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        txt_description.setRows(5);
        jScrollPane1.setViewportView(txt_description);

        jLabel29.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jButton4.setText("Pic");
        jButton4.setEnabled(false);

        jButton5.setText("Delete");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtUnit_cost, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPO_cost, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_stock, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_limit, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmb_supplier, javax.swing.GroupLayout.Alignment.LEADING, 0, 163, Short.MAX_VALUE)
                            .addComponent(cmb_UOM, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmb_package, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmb_category, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtUnit_Price)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel17))
                                .addGap(39, 39, 39))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jButton4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNet_wt, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                            .addComponent(txt_pallet)
                            .addComponent(txtPack_units)
                            .addComponent(txtGross_wt, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel25)
                                    .addComponent(jLabel26)
                                    .addComponent(jLabel27))
                                .addGap(139, 151, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jScrollPane1)
                                .addContainerGap())))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel15))
                                .addGap(28, 28, 28)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEng_pin)
                            .addComponent(txt_model)
                            .addComponent(txt_revision, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 5, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_discount, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                            .addComponent(txt_vat)
                            .addComponent(txtMonthly_demand)
                            .addComponent(txtEconomy_order)
                            .addComponent(txtLead_days, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addContainerGap())))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtEng_pin, txt_model, txt_revision});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel5)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmb_category, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(txt_model, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel21)
                        .addComponent(txtLead_days, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEconomy_order, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEng_pin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(cmb_UOM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16)
                        .addComponent(txt_revision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel23)
                        .addComponent(txtMonthly_demand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cmb_supplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel17)
                                    .addComponent(txtPack_units, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel25)
                                    .addComponent(txt_vat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel18)
                                    .addComponent(txt_pallet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel26)
                                    .addComponent(txt_discount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel19)
                                    .addComponent(txtNet_wt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel27))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel20)
                                    .addComponent(txtGross_wt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addComponent(jButton4)))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txt_limit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txt_stock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtPO_cost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtUnit_cost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtUnit_Price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2)
                            .addComponent(jButton5))
                        .addContainerGap())))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jLabel22))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jLabel15))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(cmb_package, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jLabel6)))
                .addGap(272, 272, 272))
        );

        jPanel2.add(jPanel3, "card2");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 749, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 331, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel4, "card3");

        jLabel24.setForeground(new java.awt.Color(255, 51, 51));
        jLabel24.setText("*");

        jButton3.setText("View All Items");
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
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addGap(3, 3, 3)
                        .addComponent(jLabel1)
                        .addGap(4, 4, 4)
                        .addComponent(cmb_name, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jLabel2)
                        .addGap(4, 4, 4)
                        .addComponent(txtProduct_code, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3)))
                .addGap(6, 6, 6))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel3)
                .addGap(1, 1, 1)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel24))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(cmb_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtProduct_code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3))))
                .addGap(7, 7, 7)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
         String itemName=sup.getValue(cmb_name.getSelectedItem());
         String productCode=txtProduct_code.getText();
         String category=sup.getValue(cmb_category.getSelectedItem());
         String package1=sup.getValue(cmb_package.getSelectedItem());
         String uom=sup.getValue(cmb_UOM.getSelectedItem());
         String supplierCode=sup.getValue(supplierId[cmb_supplier.getSelectedIndex()]);
         int limit=Integer.parseInt(sup.getIntValue(txt_limit.getText()));
         double stock=Double.parseDouble(sup.getCostValue(txt_stock.getText()));
         double poCost=Double.parseDouble(sup.getCostValue(txtPO_cost.getText()));
         double unitCost=Double.parseDouble(sup.getCostValue(txtUnit_cost.getText()));
         double unitPrice=Double.parseDouble(sup.getCostValue(txtUnit_Price.getText()));
         String model=txt_model.getText();
         String engPin=txtEng_pin.getText();
         String revision=txt_revision.getText();
         int packUnits=Integer.parseInt(sup.getIntValue(txtPack_units.getText()));
         String pallet=txt_pallet.getText();
         double netWt=Double.parseDouble(sup.getCostValue(txtNet_wt.getText()));
         double grossWt=Double.parseDouble(sup.getCostValue(txtGross_wt.getText()));
         String picName="";
         int leadDays=Integer.parseInt(sup.getIntValue(txtLead_days.getText()));
         double ecoOrder=Double.parseDouble(sup.getCostValue(txtEconomy_order.getText()));
         double monthlyDemand=Double.parseDouble(sup.getCostValue(txtMonthly_demand.getText()));
         double vat=Double.parseDouble(sup.getCostValue(txt_vat.getText()));
         double discount=Double.parseDouble(sup.getCostValue(txt_discount.getText()));
         String description=txt_description.getText();        
         String date=new SimpleDateFormat("dd-MM-yyyy").format(new Date());
         if(!itemName.equals("") && !productCode.equals("")){
             if(this.add_item(productCode, itemName, category, package1, uom, supplierCode, limit, stock, poCost, unitCost, unitPrice, model, engPin, revision, packUnits, pallet, netWt, grossWt, picName, leadDays, ecoOrder, monthlyDemand, vat, discount, description, date))
                 resetFields();
         }else{
             errorFields = new JLabel("<HTML><FONT COLOR = Blue>Please make sure item name and product code fields are filled!</FONT></HTML>");
             JOptionPane.showMessageDialog(null,errorFields,"Clinic Management System",JOptionPane.ERROR_MESSAGE);
         }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        medicine_table meds=new medicine_table();
        meds.formload();
        JOptionPane.showOptionDialog(null, meds.getContentPane(),"Inventory Item List", JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);
        String code=sup.getValue(meds.getProductCode());
        if(!code.equals("")){
            FindItem(code,0);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        String productcode=txtProduct_code.getText();
        if(!productcode.equals("")){
            if(FindItem(productcode,1)){
                this.formload();
                errorFields = new JLabel("<HTML><FONT COLOR = BLUE>Item Deleted Successfully</FONT></HTML>");
                JOptionPane.showMessageDialog(null,errorFields,"Clinic Management System",JOptionPane.INFORMATION_MESSAGE); 
            }            
        }else{
            errorFields = new JLabel("<HTML><FONT COLOR = BLUE>Please Make sure a product is selected</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields,"Clinic Management System",JOptionPane.ERROR_MESSAGE); 
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        resetFields();
    }//GEN-LAST:event_jButton2ActionPerformed

    private boolean FindItem(String productcode,int val){
       org.hibernate.Session session = HibernateUtil.getSessionFactory().openSession();
       Transaction tx = null;
       boolean status=false;
      try{
         tx = session.beginTransaction();
         Query q = session.getNamedQuery("DtInventoryItemlist.findByProductCode");
            q.setParameter("productCode", productcode);
            q.setCacheable(true);
            List<DtInventoryItemlist> item=q.list();           
            Iterator<DtInventoryItemlist> itr=item.iterator();             
         if(itr.hasNext()){
             inventory=(DtInventoryItemlist)itr.next();
             switch(val){
                 case 0:                  
                     this.setFields();
                     break;
                 case 1:
                     session.delete(inventory);
                     break;
                 default:
                     break;
             }
            
         }
         tx.commit();
         status=true;
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
             errorFields = new JLabel("<HTML><FONT COLOR = BLUE>'"+e.getMessage()+"'</FONT></HTML>");
             JOptionPane.showMessageDialog(null,errorFields,"csrim",JOptionPane.ERROR_MESSAGE); 
      }finally {
         session.close(); 
      }
      return status;
   }
    
    public boolean add_item(String productCode, String itemName, String category, String package1, String uom, String supplierCode, int limit, double stock, double poCost, double unitCost, double unitPrice, String model, String engPin, String revision, int packUnits, String pallet, double netWt, double grossWt, String picName, int leadDays, double ecoOrder, double monthlyDemand, double vat, double discount, String description, String date){
      Session session = HibernateUtil.getSessionFactory().openSession();      
      Transaction tx = null;
      boolean state=false;
      try{   
            tx=session.beginTransaction();
            DtInventoryItemlist inventory=new DtInventoryItemlist(productCode, itemName, category, package1, uom, supplierCode, limit, stock, poCost, unitCost, unitPrice, model, engPin, revision, packUnits, pallet, netWt,  grossWt, picName, leadDays, ecoOrder, monthlyDemand, vat, discount, description, sup.sqlDate(date));
           
            Query q = session.getNamedQuery("DtInventoryItemlist.findByProductCode");
            q.setParameter("productCode", productCode);
            q.setCacheable(true);
            List<DtInventoryItemlist> item=q.list();           
            Iterator<DtInventoryItemlist> itr=item.iterator();             
         if(!itr.hasNext()){
             session.save(inventory);
             errorFields = new JLabel("<HTML><FONT COLOR = BLUE>Item saved successfully</FONT></HTML>");
         }
         else{
             inventory=(DtInventoryItemlist)itr.next(); 
             inventory.setCategory(category);
             inventory.setDate(sup.sqlDate(date));
             inventory.setDescription(description);
             inventory.setDiscount(discount);
             inventory.setEcoOrder(ecoOrder);
             inventory.setEngPin(engPin);
             inventory.setGrossWt(grossWt);
             inventory.setItemName(itemName);
             inventory.setLeadDays(leadDays);
             inventory.setOrderLimit(limit);
             inventory.setModel(model);
             inventory.setMonthlyDemand(monthlyDemand);
             inventory.setNetWt(netWt);
             inventory.setPackUnits(packUnits);
             inventory.setPackage1(package1);
             inventory.setPallet(pallet);
             inventory.setPicName(picName);
             inventory.setPoCost(poCost);
             inventory.setProductCode(productCode);
             inventory.setRevision(revision);
             inventory.setStock(stock);
             inventory.setSupplierCode(supplierCode);
             inventory.setUnitCost(unitCost);
             inventory.setUnitPrice(unitPrice);
             inventory.setUom(uom);
             inventory.setVat(vat);
             session.update(inventory);  
             errorFields = new JLabel("<HTML><FONT COLOR = BLUE>Item updated successfully</FONT></HTML>");
         }
            tx.commit();
            JOptionPane.showMessageDialog(null,errorFields,"csrim1",JOptionPane.INFORMATION_MESSAGE);
            state=true;
            
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
      }finally {
         session.close(); 
      }
      return state;
   }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmb_UOM;
    private javax.swing.JComboBox cmb_category;
    private javax.swing.JComboBox cmb_name;
    private javax.swing.JComboBox cmb_package;
    private javax.swing.JComboBox cmb_supplier;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
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
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField txtEconomy_order;
    private javax.swing.JTextField txtEng_pin;
    private javax.swing.JTextField txtGross_wt;
    private javax.swing.JTextField txtLead_days;
    private javax.swing.JTextField txtMonthly_demand;
    private javax.swing.JTextField txtNet_wt;
    private javax.swing.JTextField txtPO_cost;
    private javax.swing.JTextField txtPack_units;
    private javax.swing.JTextField txtProduct_code;
    private javax.swing.JTextField txtUnit_Price;
    private javax.swing.JTextField txtUnit_cost;
    private javax.swing.JTextArea txt_description;
    private javax.swing.JTextField txt_discount;
    private javax.swing.JTextField txt_limit;
    private javax.swing.JTextField txt_model;
    private javax.swing.JTextField txt_pallet;
    private javax.swing.JTextField txt_revision;
    private javax.swing.JTextField txt_stock;
    private javax.swing.JTextField txt_vat;
    // End of variables declaration//GEN-END:variables
}
