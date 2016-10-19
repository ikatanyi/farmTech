/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;


import farm.entity.DtFarmerdetails;
import farm.entity.DtInsurances;
import farm.entity.DtSuppliers;
import farm.entity.DtUsers;
import farmTech.util.HibernateUtil;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.persistence.Entity;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Months;
import org.joda.time.Weeks;
import org.joda.time.Years;

/**
 *
 * @author Kent
 */
public class superCls {
     private HelpSet hs;
    private HelpBroker hb;
    private JLabel errorFields;
    
    public String getIntValue(Object val){
        String cst="";
        cst=((val !=  null )? val.toString() :"");
        if(!"".equals(cst) && !cst.contains(".") && isNumeric(cst)){
           return cst;
         }else {
            return "0";
        }
      }
    
     public String getValue(Object val){
        return ((val !=  null )? val.toString() :"");
    }
    
    public String getCostValue(Object val){
        String cst="";
        cst=((val !=  null )? val.toString() :"");
        if(!"".equals(cst) && isNumeric(cst)){
        return cst;
         }else {
            return "0.00";
        }
      }
 public static boolean isNumeric(String str){
   NumberFormat formatter=NumberFormat.getInstance();
   ParsePosition pos=new ParsePosition(0);
   formatter.parse(str,pos);
   return str.length()==pos.getIndex();
} 
//     public static String getDateDiff(Date date1,Date date2){
//        TimeUnit timeunit = null;        
//        long diffinmillies=date1.getTime()-date2.getTime();
//        String newdate=String.valueOf(((timeunit.DAYS.convert(diffinmillies,TimeUnit.MILLISECONDS))/366));
//        return newdate;
//    }
 
 public Date String_date (String dateInString) {
 
   SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//   String dateInString = "01/01/2015";
    Date date=new Date();
    try{

    date = formatter.parse(dateInString);
    System.out.println(date);
    System.out.println(formatter.format(date)); 

    }catch(ParseException e){
        e.printStackTrace();
    }
    return date;
}
 public Date StringToDate(String date){
    Date covertedDate = null;
    SimpleDateFormat formatter = new SimpleDateFormat("E, MMM dd yyyy");
try {
    if(date!=null&&!date.equals("")){
        if(date.contains("/"))
           formatter=new SimpleDateFormat("dd/MM/yyyy");
        if(date.contains("-"))
           formatter=new SimpleDateFormat("dd-MM-yyyy");
        covertedDate=formatter.parse(date);
    }
    else{
        covertedDate=new Date();
    }
 } catch (ParseException ex){
        JLabel errorFields = new JLabel("<HTML><FONT COLOR = red>'"+ex.getMessage()+"'</FONT></HTML>");	
        JOptionPane.showMessageDialog(null,  errorFields,"Clinic Management System",JOptionPane.WARNING_MESSAGE);  
}
   return covertedDate;     
}
 
 public Date StringToJDate(String date){
    Date covertedDate = null;
try {
    if(date!=null&&!date.equals("")){
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        covertedDate=dateFormat.parse(date);
    }
    else{
        covertedDate=new Date();
    }
 } catch (ParseException ex){
        JLabel errorFields = new JLabel("<HTML><FONT COLOR = red>'"+ex.getMessage()+"'</FONT></HTML>");	
        JOptionPane.showMessageDialog(null,  errorFields,"Clinic Management System",JOptionPane.WARNING_MESSAGE);  
}
   return covertedDate;     
}
 public String getDrugname(String name){
     if(name.contains("'")){
          return name.replace("'","");
     }else{
         return name;
     }
 }
 public Object[] getElements(DefaultComboBoxModel model){
     Object[] item=new Object[model.getSize()];
     for(int i=0;i<item.length;i++){
         item[i]=(Object)model.getElementAt(i);
     }
     return item;
 }
 
public int getDateDiff(Date startdate,Date enddate,String type){
    int difference=0;
    DateTime start=new DateTime(startdate);
    DateTime end=new DateTime(enddate);
    if(startdate.after(enddate)){
        start=end;
        end=new DateTime(startdate);
    }
    
    if(type.equals("DAYS")){
        difference=Days.daysBetween(start, end).getDays();
    }
    if(type.equals("WEEKS")){
        difference=Weeks.weeksBetween(start, end).getWeeks();
    }
    if(type.equals("MONTHS")){
        difference=Months.monthsBetween(start,end).getMonths()+1;
    }
     if(type.equals("YEARS")){
        difference=Years.yearsBetween(start, end).getYears();
    }     
     
    return difference;
}
 public void openHelp() {
    // Identify the location of the .hs file 
    String pathToHS = "/classes/javahelp/appwithhelp/docs/appwithhelp-hs.xml";
    //Create a URL for the location of the help set
    try {
      URL hsURL = getClass().getResource(pathToHS);
        hs = new HelpSet(null, hsURL);
    } catch (Exception ee) {
        // Print info to the console if there is an exception
        System.out.println( "HelpSet " + ee.getMessage());
        System.out.println("Help Set "+ pathToHS +" not found");
        return;
    }
  
    // Create a HelpBroker object for manipulating the help set
    hb = hs.createHelpBroker();
    //Display help set
    hb.setDisplayed(true);
}
 
 public DefaultComboBoxModel pagination (String table) {                 
       org.hibernate.Session session = HibernateUtil.getSessionFactory().openSession();
       DefaultComboBoxModel tmodel =new DefaultComboBoxModel();
       int avail=0;       
      try {        
           session.beginTransaction();
           Query q = session.createQuery("Select COUNT(*) from "+table);
           q.setCacheable(true);
           List auth=q.list();
           Iterator iterator=auth.iterator();
           if(iterator.hasNext()){ 
                   int values=Integer.parseInt(getIntValue((Object)auth.get(0)));
                   int num=values/30;
                   for(int i=0;i<=num; i++){
                         tmodel.addElement(i);
                   }  
           }
           session.getTransaction().commit();
         }catch (HibernateException he) {
                 JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>'"+he.getStackTrace()+"'</FONT></HTML>");
                 JOptionPane.showMessageDialog(null,errorFields,"DMS",JOptionPane.ERROR_MESSAGE);

         }finally {
             session.close(); 
         }
      return tmodel;
    }
 
 public DefaultComboBoxModel pagination2 (String query) {                 
       org.hibernate.Session session = HibernateUtil.getSessionFactory().openSession();
       DefaultComboBoxModel tmodel =new DefaultComboBoxModel();
       int avail=0;       
      try {        
           session.beginTransaction();
           Query q = session.createQuery(query);
           q.setCacheable(true);
           List auth=q.list();
           Iterator iterator=auth.iterator();
           if(iterator.hasNext()){ 
                   int values=Integer.parseInt(getIntValue((Object)auth.get(0)));
                   int num=values/30;
                   for(int i=0;i<=num; i++){
                         tmodel.addElement(i);
                   }  
           }
           session.getTransaction().commit();
         }catch (HibernateException he) {
                 JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>'"+he.getStackTrace()+"'</FONT></HTML>");
                 JOptionPane.showMessageDialog(null,errorFields,"DMS",JOptionPane.ERROR_MESSAGE);

         }finally {
             session.close(); 
         }
      return tmodel;
    }

    /**
     *
     * @param date
     * @return
     */
    public Date sqlDate(String date){
        java.sql.Date sqlDate = null;
         try {
             String myDate;
             if(date==null){
                 myDate=new SimpleDateFormat("dd-MM-yyyy").format(new Date());
             }  else{
                 myDate =  date;
             }
             SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");             
             sqlDate = new java.sql.Date(format.parse(myDate).getTime());
             
         } catch (ParseException ex) {
         }
         return sqlDate;
    }
    
    public void field_validation(JTextField field, KeyEvent evt){
     if(!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar()!=KeyEvent.VK_BACK_SPACE && evt.getKeyChar()!=KeyEvent.VK_PERIOD){
       field.setEditable(false);
       field.setBackground(Color.white);
       field.getToolkit().beep();
     }
     else{
        field.setEditable(true);
     }
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
         JLabel errorFields = new JLabel("<HTML><FONT COLOR = BLUE>'"+e.getMessage()+"'</FONT></HTML>");
         JOptionPane.showMessageDialog(null,errorFields,"DMS",JOptionPane.ERROR_MESSAGE);  
      }finally {
         session.close(); 
      }
      return farmername;
   }
     
    public int getCount(Entity table_name){
       return new rowcount().row_count(table_name.getClass());
    }
    
    
    public String getcode(int value,String val){
        String code="";
//        String val=String.valueOf(getCount(table_name));
        try{           
            int index=val.length();
            while(index<value){
                code+="0";
                value--;
            }
        }catch(Exception ex){
            
        }
        return code+val+"\\"+new SimpleDateFormat("YY").format(new Date());
    }
    
//    public boolean log_msg(String sender,String farm_no, String msg, boolean status,Date date){
//      Session session = HibernateUtil.getSessionFactory().openSession();      
//      Transaction tx = null;
//      boolean state=false;
//      try{          
//            DtMessages message=new DtMessages(sender,farm_no, msg, status, date);           
//            tx = session.beginTransaction();            
//            session.save(message);
//            tx.commit();
//            state=true;
//            
//      }catch (HibernateException e) {
//         if (tx!=null) tx.rollback();
//                e.printStackTrace(); 
//      }finally {
//         session.flush();
//         session.close(); 
//      }
//      return state;
//   }
    
    public String insurance_name(String insuranceCode)
  {
    int i = 0;
    
    String name = "";
    Session session = HibernateUtil.getSessionFactory().openSession();
    try
    {
      session.beginTransaction();
      Query q = session.getNamedQuery("DtInsurances.findByInsuranceCode");
      q.setParameter("insuranceCode", insuranceCode);
      q.setCacheable(true);
      List auth = q.list();
      Iterator iterator = auth.iterator();
      if (iterator.hasNext())
      {
        DtInsurances supp = (DtInsurances)iterator.next();
        name = supp.getClientName();
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
    return name;
  }
  
  public String branch_name(int id)
  {
    int i = 0;
    
    String name = "";
    Session session = HibernateUtil.getSessionFactory().openSession();
    try
    {
      session.beginTransaction();
      Query q = session.getNamedQuery("DtInsurances.findById");
      q.setParameter("id", Integer.valueOf(id));
      q.setCacheable(true);
      List auth = q.list();
      Iterator iterator = auth.iterator();
      if (iterator.hasNext())
      {
        DtInsurances supp = (DtInsurances)iterator.next();
        name = supp.getSchemeName();
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
    return name;
  }
  
  public String FindSupplierName(String supplierid)
  {
    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction tx = null;
    String suppliername = "UNDEFINED";
    try
    {
      Query q = session.getNamedQuery("DtSuppliers.findBySupplierId");
      q.setParameter("supplierId", supplierid);
      q.setCacheable(true);
      List<DtSuppliers> supplier = q.list();
      Iterator<DtSuppliers> itr = supplier.iterator();
      if (itr.hasNext())
      {
        DtSuppliers sup = (DtSuppliers)itr.next();
        suppliername = sup.getSuppliername();
      }
    }
    catch (HibernateException e)
    {
      if (tx != null) {
        tx.rollback();
      }
      JLabel errorFields = new JLabel("<HTML><FONT COLOR = BLUE>'" + e.getMessage() + "'</FONT></HTML>");
      JOptionPane.showMessageDialog(null, errorFields, "csrim", 0);
    }
    finally
    {
      session.close();
    }
    return suppliername;
  }
    
     public String getUsername(String idno){
       org.hibernate.Session session = HibernateUtil.getSessionFactory().openSession();
       Transaction tx = null;
       String user="";
      try{
         DtUsers users = (DtUsers)session.get(DtUsers.class, idno);
         if(users!=null){
             user=users.getFname().concat(" ").concat(users.getLname());
            }
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
             JLabel errorFields = new JLabel("<HTML><FONT COLOR = BLUE>'"+e.getMessage()+"'</FONT></HTML>");
             JOptionPane.showMessageDialog(null,errorFields,"csrim",JOptionPane.ERROR_MESSAGE); 
      }finally {
         session.close(); 
      }
      return user;
   }
    
   public boolean deleteById(Class<?> type, Serializable id) {
       org.hibernate.Session session = HibernateUtil.getSessionFactory().openSession();
       Transaction tx = null;
       boolean state=false;
      try{   
         Object persistentInstance = session.load(type, id);
         if (persistentInstance != null) {
             tx=session.beginTransaction();
             session.delete(persistentInstance);
             tx.commit();
             state= true;
         }
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
             errorFields = new JLabel("<HTML><FONT COLOR = BLUE>'"+e.getMessage()+"'</FONT></HTML>");
             JOptionPane.showMessageDialog(null,errorFields,"csrim",JOptionPane.ERROR_MESSAGE); 
      }finally {
         session.close(); 
      }
      return state;
   }
    
public String stackTraceToString(Throwable e) {
    StringBuilder sb = new StringBuilder();
    for (StackTraceElement element : e.getStackTrace()) {
        sb.append(element.toString());
        sb.append("\n");
    }
    return sb.toString();
}
public String doubleFormatter(Object value){
    String val="0.00";
     DecimalFormat formatter = new DecimalFormat( "##.00" ); 
     val= formatter.format((Number)Double.parseDouble(new superCls().getCostValue(value)));
     return val;
}

}
