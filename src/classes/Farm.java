/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;

import farm.entity.DtCompany;
import farm.entity.DtConstants;
import farm.entity.DtFarmerAccount;
import farm.entity.DtFarmerdetails;
import farm.entity.DtUsers;
import farmTech.util.HibernateUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Kent
 */
public class Farm {
    private JLabel errorFields;
    public void Farm(){
        
    }
     public DtFarmerdetails FarmerSearch(String farmerid ){
       Session session = HibernateUtil.getSessionFactory().openSession();
       Transaction tx = null;
       DtFarmerdetails details=null;
      try{
         tx = session.beginTransaction();
         DtFarmerdetails farmer = (DtFarmerdetails)session.get(DtFarmerdetails.class, farmerid);
         if(farmer!=null){
             details=farmer;                     
         }
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         errorFields = new JLabel("<HTML><FONT COLOR = BLUE>'"+e.getMessage()+"'</FONT></HTML>");
         JOptionPane.showMessageDialog(null,errorFields,"Dairy Management System",JOptionPane.ERROR_MESSAGE); 
      }finally {
         session.close(); 
      }
      return details; 
   }
     
     public String UserSearch(String farmerid ){
       Session session = HibernateUtil.getSessionFactory().openSession();
       Transaction tx = null;
       String name="";
      try{
         tx = session.beginTransaction();
         DtUsers user = (DtUsers)session.get(DtUsers.class, farmerid);
         if(user!=null)
             name=user.getFname()+" "+user.getLname();                     
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
             errorFields = new JLabel("<HTML><FONT COLOR = BLUE>'"+e.getMessage()+"'</FONT></HTML>");
             JOptionPane.showMessageDialog(null,errorFields,"Dairy Management System",JOptionPane.ERROR_MESSAGE); 
      }finally {
         session.close(); 
      }
      return name; 
   }
    public DtConstants constant(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        double rate=0.0;
        DtConstants constant=null;
    try {        
        session.beginTransaction();
        constant = (DtConstants)session.get(DtConstants.class, 1);
        session.getTransaction().commit();
        session.close();
    } catch (HibernateException | NumberFormatException he) {
         errorFields = new JLabel("<HTML><FONT COLOR = BLUE>'"+he.getMessage()+"'</FONT></HTML>");
         JOptionPane.showMessageDialog(null,errorFields,"Dairy Management System",JOptionPane.ERROR_MESSAGE); 
    }
    return constant;
        
    }
     
    public Double getFarmQty(String farmerid,String date_lo,String date_hi) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        double qty=0.0;
    try {        
        session.beginTransaction();
        Query q = session.createQuery("SELECT SUM(col.quantity) from DtDailycollection  AS col WHERE farmerId=:id AND col.date BETWEEN :date1 and  :date2");
        q.setParameter("id", farmerid);
        q.setParameter("date1", date_lo);
        q.setParameter("date2", date_hi);
        Object row = ((Object)q.uniqueResult()); 
        if(row!=null){            
            qty=Double.parseDouble(new superCls().getValue(row));
        }
        session.getTransaction().commit();
        session.close();
    } catch (HibernateException | NumberFormatException he) {
         errorFields = new JLabel("<HTML><FONT COLOR = BLUE>'"+he.getMessage()+"'</FONT></HTML>");
         JOptionPane.showMessageDialog(null,errorFields,"DMS",JOptionPane.ERROR_MESSAGE); 
    }
    return qty;
}
    
    public Double getOustLoan(String farmerid) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        double OL=0.0,debit=0.0,credit=0.0;
    try {        
        session.beginTransaction();
        Query q = session.createQuery("SELECT SUM(acc.credit),SUM(acc.debit) from DtFarmerAccount  AS acc WHERE acc.farmerId=:id");
        q.setParameter("id", farmerid);
        List results=q.list();
        Iterator iterator=results.iterator();  
        if(iterator.hasNext()){            
            Object[] val=(Object[])iterator.next();
            credit=Double.parseDouble(new superCls().getValue(val[0]));
            debit=Double.parseDouble(new superCls().getValue(val[1]));
            OL=credit-debit;
        }
        session.getTransaction().commit();
        session.close();
    } catch (HibernateException | NumberFormatException he) {
         errorFields = new JLabel("<HTML><FONT COLOR = BLUE>'"+he.getMessage()+"'</FONT></HTML>");
         JOptionPane.showMessageDialog(null,errorFields,"DMS",JOptionPane.ERROR_MESSAGE); 
    }
    return OL;
  }
    
    public Double getStoresCredit(String farmerid,String type) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        double stores_credit=0.0,credit=0.0,debit=0.0;
    try {        
        session.beginTransaction();
        Query q = session.createQuery("SELECT SUM(acc.credit), SUM(acc.debit) from DtFarmerAccount  AS acc WHERE acc.farmerId=:id AND acc.type=:type");
        q.setParameter("id", farmerid);
        q.setParameter("type", type);
        List results=q.list();
        Iterator iterator=results.iterator();  
        if(iterator.hasNext()){            
            Object[] val=(Object[])iterator.next();
            credit=Double.parseDouble(new superCls().getValue(val[0]));
            debit=Double.parseDouble(new superCls().getValue(val[1]));
            stores_credit=credit-debit;
        }
        session.getTransaction().commit();
        session.close();
    } catch (HibernateException | NumberFormatException he) {
         errorFields = new JLabel("<HTML><FONT COLOR = BLUE>'"+he.getMessage()+"'</FONT></HTML>");
        JOptionPane.showMessageDialog(null,errorFields,"DMS",JOptionPane.ERROR_MESSAGE); 
    }
    return stores_credit;
}
    
    public boolean save_loan(String id, Date date, String type, String description, double debit, double credit,String userId){
         org.hibernate.Session session = HibernateUtil.getSessionFactory().openSession();
         Transaction tx = null;
         boolean state=false;
         String date1=new SimpleDateFormat("dd-MM-yyyy").format( new Date());
         try{
            tx = session.beginTransaction();
            Query q = session.createQuery("FROM DtFarmerAccount loan WHERE loan.farmerId=:id AND loan.type=:type AND loan.date=:date");
            q.setParameter("id", id);
            q.setParameter("type", type);
            q.setParameter("date", date1);
            List results=q.list();
            Iterator iterator=results.iterator();  
            DtFarmerAccount loan=new DtFarmerAccount(id, date, type, description, debit, credit,userId);                
            if(!iterator.hasNext()) {                              
               session.save(loan);
               state=true;
//               errorFields = new JLabel("<HTML><FONT COLOR = BLUE>Loan Awarded successfully</FONT></HTML>");
            }
            else{
                 session.update(loan);
                 state=true;
//                 errorFields = new JLabel("<HTML><FONT COLOR = BLUE>Loan updated successfully</FONT></HTML>");
            }
            tx.commit();
//            JOptionPane.showMessageDialog(null,errorFields,"DMS",JOptionPane.INFORMATION_MESSAGE); 
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
            errorFields = new JLabel("<HTML><FONT COLOR = BLUE>'"+e.getMessage()+"'</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields,"DMS",JOptionPane.ERROR_MESSAGE); 
      }finally {
         session.flush();
         session.close(); 
      }    
         return state;
    }
    
    public String Farmer_name(String farmerid ){
       Session session = HibernateUtil.getSessionFactory().openSession();
       Transaction tx = null;
       String farmername="";
      try{
         tx = session.beginTransaction();
         DtFarmerdetails farmer = (DtFarmerdetails)session.get(DtFarmerdetails.class, farmerid);
         if(farmer!=null){
            DtFarmerdetails details=(DtFarmerdetails)farmer;  
            farmername=details.getFname()+" "+details.getLname();
         }
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         errorFields = new JLabel("<HTML><FONT COLOR = BLUE>'"+e.getMessage()+"'</FONT></HTML>");
         JOptionPane.showMessageDialog(null,errorFields,"DMS",JOptionPane.ERROR_MESSAGE);  
      }finally {
         session.close(); 
      }
      return farmername;
   }
}