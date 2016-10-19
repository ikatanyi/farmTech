package classes;


import farmTech.util.HibernateUtil;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;
import org.hibernate.internal.SessionFactoryImpl;
public class reports extends SwingWorker<Integer, String>{
    /* JasperReport is the object
    that holds our compiled jrxml file */
    JasperReport jasperReport;
    /* JasperPrint is the object contains
    report after result filling process */
   JasperPrint jasperPrint;
   private String patientid = "";
   private InputStream jasperRpt;
   private String jRpt;
   private HashMap jasperParameter;
   private InputStream jrpt;
   private  JRBeanCollectionDataSource beanColDataSource;
   private  JRTableModelDataSource tableDataSource;
    
    public reports(String patientId,InputStream rpt,HashMap parameter) 
    {
      super();
      patientid = patientId;
      jasperRpt= rpt;
      jasperParameter=parameter;
    }
    
    public reports(InputStream rpt,HashMap parameter) 
    {
      super();
      jasperRpt= rpt;
      jasperParameter=parameter;
    }
    
     public reports(String patientId,String rpt,HashMap parameter) 
    {
      super();
      patientid = patientId;
      jRpt= rpt;
      jasperParameter=parameter;
    }
    
   
   public reports(HashMap jasperParameter,InputStream rpt,JRBeanCollectionDataSource beanColDataSource) 
    {
      super();
      this.jasperParameter=jasperParameter;
      jasperRpt= rpt;
      this.beanColDataSource=beanColDataSource;
    }
   
   public reports(HashMap jasperParameter,InputStream rpt,JRTableModelDataSource tableDataSource) 
    {
      super();
      this.jasperParameter=jasperParameter;
      jasperRpt= rpt;
      this.tableDataSource=tableDataSource;
    }
    
   public reports(InputStream rpt) 
    {
      super();
      jasperRpt= rpt;
    }
    
    public reports() 
    {
      super();
//      patientid = patientId;
    }
    public JasperPrint displayReport()
    {
//        org.hibernate.Session session = HibernateUtil.getSessionFactory().openSession();
         Connection conn;
       try {             
         conn=getConnection(); 
         jasperPrint = JasperFillManager.fillReport(jasperRpt,jasperParameter, conn);
        // exporting process
        // 1- export to PDF
//            JasperExportManager.exportReportToPdfFile(jasperPrint, "D://sample_report2.pdf");     
//            // 2- export to HTML
//            JasperExportManager.exportReportToHtmlFile(jasperPrint, "D://sample_report2.html" );
//            // 3- export to Excel sheet
//            JRXlsExporter exporter = new JRXlsExporter();
//            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
//            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "D://simple_report2.xls" );
          JasperViewer.setDefaultLookAndFeelDecorated(true);
//              JasperViewer.viewReport(jasperPrint);
//              exporter.exportReport();
          if(!conn.isClosed()){
              conn.close();
          }
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null,ex.getCause()+ex.getMessage()); 
        } catch (SQLException ex) {
//             JOptionPane.showMessageDialog(null,ex.getMessage()+ex.getMessage()); 
        }
       finally {
//         session.close(); 
      }       
       return jasperPrint;   
    }
    
    public JasperPrint beanReporter()
    {    
       try {
             jasperPrint = JasperFillManager.fillReport(jasperRpt, jasperParameter, beanColDataSource);  
             JasperViewer.setDefaultLookAndFeelDecorated(true);
        } 
         catch (JRException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage()+ex.getCause()); 
        }
       return jasperPrint;   
    }
    
    public JasperPrint modelReporter()
    {    
       try {
             jasperPrint = JasperFillManager.fillReport(jasperRpt, jasperParameter, tableDataSource);  
             JasperViewer.setDefaultLookAndFeelDecorated(true);
        } 
         catch (JRException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage()+ex.getCause()); 
        }
       return jasperPrint;   
    }
    
    public static Connection getConnection() {
        EntityManager em = null ;
        Connection connection = null;
        org.hibernate.Session ses= HibernateUtil.getSessionFactory().openSession();
//        Session ses = (Session) em.getDelegate();
        SessionFactoryImpl sessionFactory = (SessionFactoryImpl) ses.getSessionFactory();
    try{
       connection = sessionFactory.getConnectionProvider().getConnection();
    }catch(SQLException e){
//         ErrorMsgDialog.getInstance().setException(e);
    }
        return connection;
    }

    private static void failIfInterrupted() throws InterruptedException {
    if (Thread.currentThread().isInterrupted()) {
      throw new InterruptedException("Interrupted while searching files");
    }
  }
    
    @Override
    protected Integer doInBackground() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}


