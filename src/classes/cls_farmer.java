/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import farm.entity.DtFarmerdetails;
import farmTech.util.HibernateUtil;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author KENT
 */
public class cls_farmer {
    
     private static SessionFactory factory; 
     private JLabel errorFields;
     public DefaultTableModel farmer_model;
     superCls sup=new superCls();
    
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
    
 public void FarmerSearch(String farmid){
     
 }
    public void farmer_display(int val,String name,int upperlimit) {
    try {
        String hql;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        switch(val){
            case 0:
                hql="from DtFarmerdetails F order by CAST(F.farmerId AS integer) asc";
                break;
            case 1:
                hql="from DtFarmerdetails F WHERE CONCAT_ws(' ',F.fname,F.lname) LIKE '%"+name+"%' order by CAST(F.farmerId AS integer) asc";
                break;
            default:
                hql="from DtFarmerdetails f order by CAST(f.farmerId AS integer) asc";
                break;        
    }
        Query q = session.createQuery(hql);
        q.setFirstResult(upperlimit);
        q.setMaxResults(30);
        List resultList = q.list();
        displayResult(resultList);
        session.getTransaction().commit();
    } catch (HibernateException he) {
        he.printStackTrace();
    }
}
    
    private void displayResult(List resultList) {
    Vector tableData = new Vector();
    String header[]={"FarmerID","MNO.","Names","DOB","Gender","Phone_No","Address"};
    Vector<String> tableHeaders= new Vector<>();
    tableHeaders.addAll(Arrays.asList(header));
    for(Object o : resultList) {
        DtFarmerdetails farmer = (DtFarmerdetails)o;
        Vector<Object> oneRow = new Vector<>();
        List col=Arrays.asList(farmer.getFarmerId(),
                               farmer.getMno(),
                               sup.Farmer_name(farmer.getFarmerId()),
                               farmer.getDob(),
                               farmer.getGender(),
                               farmer.getPphone(),
                               farmer.getPaddress());
        oneRow.addAll(col);
        
        tableData.add(oneRow);
    }
    if(resultList.isEmpty()){
       farmer_model=new DefaultTableModel(new String[20][7],header);   
    }else
        farmer_model=new DefaultTableModel(tableData, tableHeaders);
}
    
    public DtFarmerdetails FarmerID_Search(String farmerid ){
       Session session = HibernateUtil.getSessionFactory().openSession();
       Transaction tx = null;
       DtFarmerdetails details = new DtFarmerdetails();
      try{
         tx = session.beginTransaction();
         DtFarmerdetails farmer = (DtFarmerdetails)session.get(DtFarmerdetails.class, farmerid);
         if(farmer!=null){
             details=(DtFarmerdetails)farmer;         
         }
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
             errorFields = new JLabel("<HTML><FONT COLOR = BLUE>'"+e.getMessage()+"'</FONT></HTML>");
             JOptionPane.showMessageDialog(null,errorFields,"DMS",JOptionPane.ERROR_MESSAGE);
      }finally {
         session.close(); 
      }
      return details;
   }
    
    public DtFarmerdetails mno_Search(String mno ){
       Session session = HibernateUtil.getSessionFactory().openSession();
       Transaction tx = null;
       DtFarmerdetails details = new DtFarmerdetails();
      try{
           tx=session.beginTransaction();
           Query q = session.createQuery("from DtFarmerdetails farmer where farmer.Mno=:mno");
           q.setParameter("mno", mno);
           List auth=q.list();
           Iterator iterator=auth.iterator();
           if(iterator.hasNext()){  
               details=(DtFarmerdetails)iterator.next();         
           }
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
             errorFields = new JLabel("<HTML><FONT COLOR = BLUE>'"+e.getMessage()+"'</FONT></HTML>");
             JOptionPane.showMessageDialog(null,errorFields,"DMS",JOptionPane.ERROR_MESSAGE);
      }finally {
         session.close(); 
      }
      return details;
   }
    public DefaultTableModel farmerModel(){
       return farmer_model; 
    }
}
