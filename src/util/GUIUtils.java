/*
 * GUIUtils.java
 *
 * Copyright (c) 2004-2013 kennedy ikatanyi
 */
package util;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.Window;
import java.lang.reflect.InvocationTargetException;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.table.TableColumn;

/**
 * GUI Helper class, contains static methods that are used all the time.
 * <p>
 * @author Gregory Kotsaftis
 * @since 1.0
 */
public final class GUIUtils {

    /**
     * Execute a task on EDT and block's it, until the task is finished.
     * If there is an exception, it is converted to a RuntimeException.
     *
     * @param task The process to run.
     */
    public static void invokeAndWait(final Runnable task)
    {
        if( EventQueue.isDispatchThread() )
        {
            task.run();
        }
        else
        {
            try
            {
                EventQueue.invokeAndWait( task );
            }
            catch(InterruptedException | InvocationTargetException ex)
            {
                throw new RuntimeException(ex.getMessage(), ex);
            }
        }
    }

    /**
     * Centers a window on screen.
     * <p>
     * @param w     The window to center.
     */
    public static void centerOnScreen(Window w)
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension splashSize = w.getPreferredSize();
        w.setLocation(screenSize.width / 2 - (splashSize.width / 2),
                      screenSize.height / 2 - (splashSize.height / 2));
    }

   public static void centerJF(Container f,JDesktopPane w)
    {
//      Dimension desktopsize=w.getSize();
//      Dimension framesize=f.getSize();
//      int width=(desktopsize.width/2-framesize.width)/2;
//      int height=(desktopsize.height/2-framesize.height)/2;
//      f.setLocation(width, height);
//      f.setSize((int) (desktopsize.width*0.60), (int) (desktopsize.height*0.8));
        Dimension screenSize = w.getSize();
        Dimension splashSize = f.getPreferredSize();
        f.setLocation(screenSize.width / 2 - (splashSize.width / 2),
                      screenSize.height / 2 - (splashSize.height / 2));
    }
    /**
     * Maximizes a JFrame, just like the 'maximize window' button does.
     * <p>
     * @param f     The frame to maximize.
     */
    public static void maximizeJFrame(JFrame f)
    {
        f.setExtendedState( Frame.MAXIMIZED_BOTH );
    }


    /**
     * Locks a Jtable's column width with 'pixels' size.
     * <p>
     * @param tc    	The table column.
     * @param pixels 	The desired pixels.
     */
    public static void lockJTableColumnWidth(TableColumn tc, int pixels)
    {
        if( tc!=null )
        {
            tc.setMinWidth( pixels );
            tc.setMaxWidth( pixels );
//            tc.setPreferredWidth( pixels );
            tc.setResizable( false );
        }
    }


    /**
     * Hides a specific column of a JTable.
     * <p>
     * @param tc    	The table column.
     */
    public static void hideJTableColumn(TableColumn tc)
    {
        lockJTableColumnWidth(tc, 0);
    }

}
