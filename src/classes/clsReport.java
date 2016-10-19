package classes;


//import fileReader.ReadWithScanner;
import farm.entity.DtCompany;
import farmTech.util.HibernateUtil;
import java.awt.BorderLayout;
import java.awt.Container;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JRViewer;
import net.sf.jasperreports.view.JasperViewer;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kent
 */
public class clsReport {
    HashMap jasperParameter=new HashMap();
    JasperPrint jasperPrint;
//    private  ReadWithScanner parser;
    public String breed,class1,sire_no,dam_no;
    private String CompanyName;
    private String Phone;
    private String Address;
    private String Email;
    private String Web;
    private String State;        
    private String City;
//    String path;
    

    String date,session;
    Date from_date,to_date;
    int month=1,year=2014;
    
    public clsReport(String date){
      super();
      this.date=date;
      formload();
    }
    
    public clsReport(HashMap map){
      super();
      jasperParameter=map;
      formload();
    }
    
    public clsReport(String date, Date from_date,Date to_date){
      super();
      this.date=date;
      this.from_date=from_date;
      this.to_date=to_date;
      formload();
    }
    
     public clsReport(String date, int month,int year){
      super();
      this.date=date;
      this.month=month;
      this.year=year;
      formload();
    }
     
     public clsReport(String date, int month,int year,String session){
      super();
      this.date=date;
      this.month=month;
      this.year=year;
      this.session=session;
      formload();
    }
    
    
    public clsReport(){
        super();
        formload();
    }

    public void report(final String value,String reportname){
      try{
        final InputStream rept=getClass().getResourceAsStream("/reports/"+reportname);  
        companyDetails();
        jasperParameter.put("farm_no", value);
        jasperParameter.put("date", date);
        jasperParameter.put("from_date", from_date);
        jasperParameter.put("to_date", to_date);
        jasperParameter.put("month", month);
        jasperParameter.put("year", year);
        jasperParameter.put("session", session);
        jasperParameter.put("SUBREPORT_DIR","reports/");
        jasperParameter.put("PIC_DIR","reports/");

        
        SwingWorker doComputeRate1= new SwingWorker() {
                JasperViewer viewer;
                @Override
                protected Integer doInBackground() throws Exception{
                    reports rpt=new reports(value,rept,jasperParameter);
                    viewer=new JasperViewer(rpt.displayReport(),false);
                    viewer.setDefaultLookAndFeelDecorated(true);        
                    viewer.setZoomRatio((float)0.7);//       
                    viewer.setSize(900, 653);
                    viewer.setVisible(true);
                    return 0;
                }
                @Override
                protected void done(){
                try{

                }catch(Exception ex){
                }
                }
                public void finished() {
                }
	};
        doComputeRate1.execute();
    }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex.getMessage());
    }
}
    
    public void report_viewer(String reportname,HashMap m){
      try{
        final InputStream rept=getClass().getResourceAsStream("/reports/"+reportname);  
        companyDetails();
        jasperParameter.putAll(m);

        
        SwingWorker doComputeRate1= new SwingWorker() {
                JasperViewer viewer;
                @Override
                protected Integer doInBackground() throws Exception{
                    reports rpt=new reports(rept,jasperParameter);
                    viewer=new JasperViewer(rpt.displayReport(),false);
                    viewer.setDefaultLookAndFeelDecorated(true);        
                    viewer.setZoomRatio((float)1.0);//       
                    viewer.setSize(900, 653);
                    viewer.setVisible(true);
                    return 0;
                }
                @Override
                protected void done(){
                try{

                }catch(Exception ex){
                }
                }
                public void finished() {
                }
	};
        doComputeRate1.execute();
    }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex.getMessage());
    }
}
    
    public void report_viewer_ws(String reportname,HashMap m){
      try{
        final InputStream rept=getClass().getResourceAsStream("/reports/"+reportname);  
        companyDetails();
        jasperParameter.putAll(m);
        JasperViewer viewer;
        reports rpt=new reports(rept,jasperParameter);
        viewer=new JasperViewer(rpt.displayReport(),false);
        viewer.setDefaultLookAndFeelDecorated(true);        
        viewer.setZoomRatio((float)1.0);//       
        viewer.setSize(900, 653);
        viewer.setVisible(true);
    }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex.getMessage());
    }
}
    
    public void reportPrint(final String value,String phys,String reportname){
       try{
            JasperViewer viewer;
            final InputStream rept=getClass().getResourceAsStream("/reports/"+reportname);
            companyDetails();
            jasperParameter.put("farm_no", value);
            jasperParameter.put("date", this.date);
            jasperParameter.put("Physician", phys);
            jasperParameter.put("SUBREPORT_DIR","reports/");
            jasperParameter.put("PIC_DIR","reports/");

    //        String name=this.getParent().getComponent(4).getName();
            SwingWorker doComputeRate=new SwingWorker() {
            @Override
	    protected Integer doInBackground() throws Exception{
             reports rpt=new reports(value,rept,jasperParameter);
             JasperPrintManager.printReport(rpt.displayReport(), true);
//            viewer=new JasperViewer(rpt.displayReport(),false);
//            viewer.setDefaultLookAndFeelDecorated(true);        
//            viewer.setZoomRatio((float)0.7);//       
//            viewer.setSize(900, 653);
//            viewer.setVisible(true);
    //        boolean state=instancevalue;
    //            if(instancevalue){
    //             WindowEvent wev=new WindowEvent(viewer,WindowEvent.WINDOW_CLOSING);
    //             Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
    //             viewer.dispose();
    //             instancevalue=false;
    //            }else{
    //                viewer.setVisible(true);
    //                instancevalue=true;
    //            }  
    //            this.validate();
             return 0;
	    }
            @Override
            protected void done(){
            try{

            }catch(Exception ex){
            }
            }
	    public void finished() {
	    }
	};
            doComputeRate.execute();
    }catch(Exception ex){
//        JOptionPane.showMessageDialog(null,ex.getMessage());
    }
}
    
    public JasperPrint table_Reporter(HashMap m,String reportname,JRTableModelDataSource tableDataSource)
    {    
       try {  
            JasperViewer viewer;
            InputStream rept=getClass().getResourceAsStream("/reports/"+reportname);
            companyDetails();
            jasperParameter.putAll(m);
            jasperParameter.put("SUBREPORT_DIR","reports/");
            reports rpt= new reports(jasperParameter,rept,tableDataSource);
            viewer=new JasperViewer(rpt.modelReporter(),false);
            viewer.setDefaultLookAndFeelDecorated(true);        
            viewer.setZoomRatio((float)0.7);//       
            viewer.setSize(900, 653);
            viewer.setVisible(true);
//            JasperPrintManager.printReport(rpt.Reporter(), true);
        } 
         catch (Exception ex) {
//            JOptionPane.showMessageDialog(null,ex.getMessage()+ex.getCause()); 
        }
       return jasperPrint;
   
    }
    
    public JasperPrint bean_Reporter(HashMap m,String reportname,JRBeanCollectionDataSource beanDataSource)
    {    
       try {  
            JasperViewer viewer;
            InputStream rept=getClass().getResourceAsStream("/reports/"+reportname);
            companyDetails();
            jasperParameter.putAll(m);
            jasperParameter.put("SUBREPORT_DIR","reports/");
            reports rpt= new reports(jasperParameter,rept,beanDataSource);
            viewer=new JasperViewer(rpt.beanReporter(),false);
            viewer.setDefaultLookAndFeelDecorated(true);        
            viewer.setZoomRatio((float)0.7);//       
            viewer.setSize(900, 653);
            viewer.setVisible(true);
//            JasperPrintManager.printReport(rpt.Reporter(), true);
        } 
         catch (Exception ex) {
//            JOptionPane.showMessageDialog(null,ex.getMessage()+ex.getCause()); 
        }
       return jasperPrint;
   
    }
     
     
     private static String getStringFromInputStream(InputStream is){
         BufferedReader br=null;
         StringBuilder sb=new StringBuilder();
         String line="";
         try{
             br=new BufferedReader(new InputStreamReader(is));
             while((line=br.readLine())!=null){
                 sb.append(line);}
         }catch(IOException ex){
                 
             }
         finally{
             if(br!=null){
                try{
                   br.close();
                }catch(IOException e){
                    
                } 
             }
             return sb.toString();
         }
         }
     
     
     
     public void report_scan(String patientid,String patientno,String name, String dob,String scan, double cost, String results,String reportname){
      try{
        JasperViewer viewer;
        InputStream rept=getClass().getResourceAsStream("/reports/"+reportname);
        companyDetails();
        jasperParameter.put("farmerid", patientid);
        jasperParameter.put("patientno", patientno);
        jasperParameter.put("pname", name);
        jasperParameter.put("DOB", dob);
        jasperParameter.put("scan", scan);
        jasperParameter.put("cost", cost);
        jasperParameter.put("results", results);
        jasperParameter.put("SUBREPORT_DIR","reports/");
        reports rpt=new reports(patientno,rept,jasperParameter);
        viewer=new JasperViewer(rpt.displayReport(),false);
        viewer.setDefaultLookAndFeelDecorated(true);        
        viewer.setZoomRatio((float)0.7);//       
        viewer.setSize(900, 653);
        viewer.setVisible(true);
//        viewer.setAutoRequestFocus(true);
//        boolean state=instancevalue;
//            if(instancevalue){
//             WindowEvent wev=new WindowEvent(viewer,WindowEvent.WINDOW_CLOSING);
//             Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
//             viewer.dispose();
//             instancevalue=false;
//            }else{
//                viewer.setVisible(true);
//                instancevalue=true;
//            }  
//            this.validate();
    }catch(Exception ex){
//        JOptionPane.showMessageDialog(null,ex.getMessage());
    }
}
     
   private void formload() {
       Session session = HibernateUtil.getSessionFactory().openSession();
    try {        
        session.beginTransaction();
        Query q = session.createQuery("from DtCompany");
        List resultList = q.list();
        Iterator iterator=resultList.iterator();
        if(iterator.hasNext()){            
            DtCompany details=(DtCompany)iterator.next();
            this.CompanyName=details.getCompanyName();
            this.Phone=details.getPhone();
            this.Address=details.getSaddress();
            this.Email=details.getEmail();
            this.Web=details.getWebsite();
            this.State=details.getState();        
            this.City=details.getCity();
        }
        session.getTransaction().commit();
    } catch (HibernateException he) {
          JLabel errorFields = new JLabel("<HTML><FONT COLOR = BLUE>'"+he.getMessage()+"'</FONT></HTML>");
          JOptionPane.showMessageDialog(null,errorFields,"DMS",JOptionPane.INFORMATION_MESSAGE);
    }
    finally {
         session.close(); 
      }
}
   public void companyDetails(){
        jasperParameter.put("CompanyName", CompanyName);
        jasperParameter.put("Phone", Phone);
        jasperParameter.put("Address", Address);
        jasperParameter.put("Email", Email);
        jasperParameter.put("Web", Web);
        jasperParameter.put("State", State);        
        jasperParameter.put("City", City);
        jasperParameter.put("PIC_DIR","reports/");
        jasperParameter.put("SUBREPORT_DIR","reports/");
   }
   
   
   public JPanel report_view(final String reportname,final HashMap m){
       companyDetails(); 
       jasperParameter.putAll(m);
       final JPanel pnlrpt=new JPanel();
       SwingWorker doComputeRate=new SwingWorker() {
            @Override
	    protected Integer doInBackground() throws Exception{
               try{        
                InputStream rept=getClass().getResourceAsStream("/reports/"+reportname);
                reports rpt=new reports(rept,jasperParameter);
                JRViewer viewer=new JRViewer(rpt.displayReport());
                Container container=new Container();
                pnlrpt.removeAll();
                pnlrpt.setLayout(new BorderLayout());
                viewer.setFitWidthZoomRatio();

                pnlrpt.add(viewer.getComponent(1));
                }catch(Exception ex){
//                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }
            return 0;
	    }
            @Override
            protected void done(){
            try{

            }catch(Exception ex){
            }
            }
	    public void finished() {
	    }
	};
            doComputeRate.execute();
            return pnlrpt;
   }
   
   public JPanel report_view1(final String reportname,final HashMap m){
       final JPanel pnlrpt=new JPanel();
       companyDetails(); 
       jasperParameter.putAll(m);                     
        try{        
            InputStream rept=getClass().getResourceAsStream("/reports/"+reportname);
            reports rpt=new reports(rept,jasperParameter);
            JRViewer viewer=new JRViewer(rpt.displayReport());
            Container container=new Container();
            pnlrpt.removeAll();
            pnlrpt.setLayout(new BorderLayout());
            viewer.setFitWidthZoomRatio();

            pnlrpt.add(viewer);
            }catch(Exception ex){
//                    JOptionPane.showMessageDialog(null,ex.getMessage());
            }
        return pnlrpt;
   }
}
    
    
