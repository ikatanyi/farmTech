/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package farmTech.util;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Kent
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    
    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (HibernateException ex) {
            // Log the exception. 
             JLabel errorFields = new JLabel("<HTML><FONT COLOR = BLUE>ERROR:'"+ex.getMessage()+"'Please Verify That your Server is well setup and Running also IP is correctly Configured</FONT></HTML>");
             JOptionPane.showMessageDialog(null,errorFields,"DMS",JOptionPane.INFORMATION_MESSAGE);
//            System.err.println("Initial SessionFactory creation failed." + ex.getMessage());
             
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
