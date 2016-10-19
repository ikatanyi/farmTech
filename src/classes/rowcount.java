/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import farmTech.util.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;

/**
 *
 * @author KENT
 */
public class rowcount {
//    super();
   public int row_count(Class req){
      Session session = HibernateUtil.getSessionFactory().openSession();
      Transaction tx = null;
      Object count=0;
      try{
         tx = session.beginTransaction();
         Criteria cr = session.createCriteria(req);

         // To get total row count.
         cr.setProjection(Projections.rowCount());
         List rowCount = cr.list();

         count= rowCount.get(0);
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      } 
      return Integer.parseInt(new superCls().getIntValue(count));
   }
  
}
