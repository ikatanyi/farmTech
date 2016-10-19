/*
 * JInternalFrameComparator.java
 */
package ui.mdi;

import java.util.Comparator;
import javax.swing.JInternalFrame;

/**
 * A simple comparator for <code>JInternalFrames</code>, based on their title.
 * <p>
 * @author Gregory Kotsaftis
 * @since 1.04
 */
public final class JInternalFrameComparator
    implements Comparator<JInternalFrame> {

    /**
     * Compares internal frames based on their title.
     * <p>
     * @param o1    First frame.
     * @param o2    Second frame.
     * <p>
     * @return      The comparison.
     */
    public int compare(JInternalFrame o1, JInternalFrame o2)
    {
        int ret = 0;

        if( o1!=null && o2!=null )
        {
            String t1 = o1.getTitle();
            String t2 = o2.getTitle();

            if( t1 != null && t2 != null )
            {
                ret = t1.compareTo(t2);
            }
            else if( t1 == null && t2 != null )
            {
                ret = -1;
            }
            else if( t1 != null && t2 == null )
            {
                ret = 1;
            }
            else
            {
                ret = 0;
            }
        }

        return( ret );
    }

}
