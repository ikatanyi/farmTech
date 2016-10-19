import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.applet.*;
import java.net.*;

/** Creating a custom dialog using <code>JDialog</code>.<p>
*
* Although the static methods in <code>JOptionPane</code> are convenient
* and easy to use, they fall short if the goal is to
* collect together several input parameters in a single dialog.  For this,
* you need to roll your own -- create a custom
* dialog, by directly working with <code>JDialog</code>.
* <p>
*
* This demo includes a custom class -- for convenience,
* an inner class --
* that extends <code>JDialog</code>.  The dialog is popped up from
* the main application window to collect several input
* parameters relating to the
* properties of a displayed messages.  The properties solicited for
* input are the message font (i.e., family name, style, size),
* the message text color, and the background color.<p>

* The approach is much the same as with
* <code>JFileChooser</code> where the dialog
* class is first instantiated (without
* showing the dialog), and then is popped up on an as-needed basis
* using a "show" instance method on the dialog object.
* After the user closes the dialog, the show
* method returns an
* <code>int</code>, indicating whether the dialog was 
* closed by clicking "OK" or "Cancel".  Assuming the
* user clicked "OK", the parameters inputted via the dialog are then
* available to the main application using other instance methods.<p>
*
* The window for the custom dialog
* presents a standard close button in the top right-hand
* corner:<p>
*
* <center><img src="DemoCustomDialog-0.gif"></center><p>
*
* This button presents an interesting UI issue:
* What are the <i>users' expectations</i> if the dialog
* is closed by clicking this button?  Are the inputs
* saved, or are they discarded?  The "OK" button clearly suggests
* that the inputs are saved and available to the application
* upon closing the dialog.  The "Cancel" button, on the otherhand, clearly
* suggests the opposite; namely that the user's inputs are discarded.
* To avoid uncertainty on the effect of clicking the dialog window's close
* button, the button is disabled.  The user must close the dialog either
* by clicking "OK" or "Cancel".<p>
*
* <code>JDialog</code>, like <code>JFrame</code>, is a top-level container.
* Not surprisingly, designing a <code>JDialog</code> is much the
* same as designing a GUI application's main window, which is typically an
* extended <code>JFrame</code>.  Issues such as
* installing listeners, implementing listener interfaces, navigation,
* input validation, and component layout are pretty well the same.<p>
*
* There are some new issues to confront, however, such as initialization,
* positioning,
* making the dialog appear and disappear, and passing parameters back and
* forth between the main application and the dialog.  Let's discuss
* a few of these.  The constructor
* for the dialog begins as follows:<p>
*
* <pre>
*     CustomDialog(Frame owner)
*     {
*        super(owner, "Customize Message Properties", true);
*        this.setResizable(false);
*        this. setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
*        ...
* </pre>
*
* The boolean ("true") passed to the superclass constructor makes the dialog
* modal, effectively blocking the application thread until the dialog is
* closed.  We also make the dialog's size fixed and disable the close button,
* as noted earlier.<p>
*
* We noted above
* that parameters are passed from the dialog to the main application via
* instance methods.  That is, the main application invokes instance methods
* on the dialog object to get the values inputted by the user.
* Three instance method serve this purpose:<p>
*
* <pre>
*     public Font getFont()
*     public Color getForeColor() 
*     public Color getBackColor() 
* </pre>
*
* Passing parameters from the application to the dialog is often necessary
* to initialize the dialog with current settings.
* This is accomplished through arguments to the "show" method:<p>
*
* <pre>
*     public int showCustomDialog(Frame f, Font fontArg, 
*           Color foreColorArg, Color backColorArg)
* </pre>
*
* The <code>Frame</code> argument is simply a reference to the
* calling application's window.  Within the show method, it is used
* to make sure the dialog pops up on top of the application window:<p>
*
* <pre>
*        this.setLocationRelativeTo(f);
* </pre>
*
* The remaining arguments are the message properties as they currently
* exist within the application.  The dialog is initialized to show
* a message "Example" using the same properties.  The dialog's widgets
* are also initialized to reflect the
* current settings.  After initialization, the dialog is
* popped up:<p>
*
* <pre>
*     this.show();
* </pre>
*
* The user makes changes by manipulating the widgest
* and clicks "OK" or "Cancel" when finished.
* Whithin the event handler for the OK and Cancel buttons, the dialog
* is closed:<p>
*
* <pre>
*     this.hide();
* </pre>
*
* This effectively passes control back to the show method, which returns
* one of the following integers to the application:<p>
*
* <pre>
*     public static final int OK_OPTION = 0;
*     public static final int CANCEL_OPTION = 1;
* </pre>
*
* For further detials, consult the source code or the API or tutorial
* notes on <code>JDialog</code>.<p>
*
* Screen snap upon launch...<br>
* <center><img src="DemoCustomDialog-1.gif"></center><p>
*
* Screen snap showing "Customize Message Properties" dialog...<br>
* <center><img src="DemoCustomDialog-2.gif"></center><p>
*
* Screen snap showing dialog after a few changes...<br>
* <center><img src="DemoCustomDialog-3.gif"></center><p>
*
* @see <a href="DemoCustomDialog.java">source code</a>
* @author Scott MacKenzie, 2002
*/
public class DemoCustomDialog
{
   public static void main(String[] args)
   {
      // use look and feel for my system (Win32)
      try {
         UIManager.setLookAndFeel(
            UIManager.getSystemLookAndFeelClassName());
      } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {}

      DemoCustomDialogFrame frame = new DemoCustomDialogFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setTitle("DemoCustomDialog");
      frame.pack(); 
      frame.show();      
   }
}

class DemoCustomDialogFrame extends JFrame implements ActionListener
{
   CustomDialog cd;
   JTextField messageField;
   JButton customize;
   JButton exit;

   String message;
   Font messageFont;
   Color foreColor;
   Color backColor;

   public DemoCustomDialogFrame()
   {
      // initialize message properties

      message = "Hello Java World";
      messageFont = new Font("Arial", Font.PLAIN, 26);
      foreColor = Color.black;
      backColor = Color.white;

      // ----------------------------------
      // construct and configure components
      // ----------------------------------

      cd = new CustomDialog(this);

      messageField = new JTextField();
      messageField.setPreferredSize(new Dimension(400, 200));
      messageField.setEditable(false);
      messageField.setHorizontalAlignment(SwingConstants.CENTER);
      messageField.setAlignmentX(Component.CENTER_ALIGNMENT);
      messageField.setToolTipText("Right click to change message");
      updateMessage();

      customize = new JButton("Customize");
      customize.setMaximumSize(customize.getPreferredSize());
      customize.setAlignmentX(Component.CENTER_ALIGNMENT);

      exit = new JButton("Exit");
      exit.setMaximumSize(customize.getPreferredSize());
      exit.setAlignmentX(Component.CENTER_ALIGNMENT);
      
      // -------------
      // add listeners
      // -------------

      customize.addActionListener(this);
      exit.addActionListener(this);
      messageField.addMouseListener(new PopupListener());

      // -----------------
      // layout components
      // -----------------

      // add components to panels

      JPanel p = new JPanel();
      p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
      p.add(customize);
      p.add(Box.createRigidArea(new Dimension(0, 25)));
      p.add(messageField);
      p.add(Box.createRigidArea(new Dimension(0, 25)));
      p.add(exit);
      p.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

      // make panel this JFrame's content pane

      this.setContentPane(p);
   }

   // --------------------------------
   // implement ActionListener methods
   // --------------------------------

   public void actionPerformed(ActionEvent ae)
   {
      Object source = ae.getSource();

      if (source == customize)
      {
         // Pop up the custom dialog, and send the font and color
         // properties as arguments.  The dialog will use these
         // as initial properties in the dialog.

         int i = cd.showCustomDialog(this, messageFont, foreColor, backColor);
   
         // The returned int indicates whether the user closed the dialog
         // by pressing the OK button or the Cancel button.  If the OK
         // button was used, re-configure the message using the new
         // attibute values.  These are retrieved using instance methods.

         if (i == CustomDialog.OK_OPTION)
         {
            messageFont = cd.getFont();
            foreColor = cd.getForeColor();
            backColor = cd.getBackColor();
            updateMessage();
         }
      }

      else if (source == exit)
         System.exit(0);
   } 

   // -------------
   // Other methods
   // -------------

   public void updateMessage()
   {
      messageField.setText(message);
      messageField.setFont(messageFont);
      messageField.setForeground(foreColor);
      messageField.setBackground(backColor);
   }

   // -------------
   // inner classes
   // -------------

   private class PopupListener extends MouseAdapter
   {
      public void mousePressed(MouseEvent me)
      {
         String tmp = JOptionPane.showInputDialog(messageField, "Enter new message");
         if (tmp != null && tmp.length() > 0)
         {
            message = tmp;
            updateMessage();
         }
      }
   }  

   /** Define a custom class for a dialog box to change the
   * message properties.
   */
   private class CustomDialog extends JDialog implements ActionListener, ItemListener
   {
      public static final int OK_OPTION = 0;
      public static final int CANCEL_OPTION = 1;
      int userResponse;

      final String[] SZ = { "10", "14", "18", "22", "26", "32", "38", "48" };

      JCheckBox italic;
      JCheckBox bold;
      JComboBox sizeCombo;
      JComboBox fontCombo;
      JTextField example;
      JButton ok;
      JButton cancel;
      JButton foreground;
      JButton background;

      CustomDialog(Frame owner)
      {
         super(owner, "Customize Message Properties", true);
         this.setResizable(false);
         this. setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

         GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
         String[] fontList = ge.getAvailableFontFamilyNames();
         fontCombo = new JComboBox(fontList);

         italic = new JCheckBox("Italic");
         bold = new JCheckBox("Bold");

         sizeCombo = new JComboBox(SZ);
         ((JLabel)sizeCombo.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
         sizeCombo.setSelectedIndex(4);
         sizeCombo.setPreferredSize(new Dimension(45, 21)); // tweek size

         example = new JTextField(" Example ");
         example.setHorizontalAlignment(SwingConstants.CENTER);
         example.setFont(new Font("sanserif", Font.PLAIN, 28));
         example.setEditable(false);

         ok = new JButton("OK");
         cancel = new JButton("Cancel");
         ok.setPreferredSize(cancel.getPreferredSize());

         foreground = new JButton("Text");
         background = new JButton("Background");
         foreground.setPreferredSize(background.getPreferredSize());

         // -------------
         // add listeners
         // -------------

         fontCombo.addActionListener(this);
         italic.addItemListener(this);
         bold.addItemListener(this);
         sizeCombo.addActionListener(this);
         ok.addActionListener(this);
         cancel.addActionListener(this);
         foreground.addActionListener(this);
         background.addActionListener(this);

         // -----------------
         // layout components
         // -----------------

         JPanel p0 = new JPanel();
         p0.add(fontCombo);
         p0.setBorder(new TitledBorder(new EtchedBorder(), "Font family"));

         JPanel p1a = new JPanel();
         p1a.add(italic);
         p1a.add(bold);
         p1a.setBorder(new TitledBorder(new EtchedBorder(), "Font style"));

         JPanel p1b = new JPanel();
         p1b.add(sizeCombo);
         p1b.add(new JLabel("pt."));
         p1b.setBorder(new TitledBorder(new EtchedBorder(), "Font size"));

         JPanel p1 = new JPanel();
         p1.setLayout(new BoxLayout(p1, BoxLayout.X_AXIS));
         p1.add(p1a);
         p1.add(p1b);
         p1.setAlignmentX(Component.CENTER_ALIGNMENT);
         
         JPanel p2 = new JPanel(); // use FlowLayout
         p2.add(foreground);
         p2.add(background);
         p2.setBorder(new TitledBorder(new EtchedBorder(), "Message color"));
         p2.setAlignmentX(Component.CENTER_ALIGNMENT);

         JPanel p3 = new JPanel();
         p3.setLayout(new BoxLayout(p3, BoxLayout.Y_AXIS));
         p3.add(example);
         p3.setPreferredSize(new Dimension(250, 60));
         p3.setMaximumSize(new Dimension(250, 60));
         p3.setAlignmentX(Component.CENTER_ALIGNMENT);

         JPanel p4 = new JPanel();
         p4.add(ok);
         p4.add(cancel);
         p4.setAlignmentX(Component.CENTER_ALIGNMENT);

         JPanel p = new JPanel();
         p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
         p.add(p0);
         p.add(Box.createRigidArea(new Dimension(0, 10)));
         p.add(p1);
         p.add(Box.createRigidArea(new Dimension(0, 10)));
         p.add(p2);
         p.add(Box.createRigidArea(new Dimension(0, 10)));
         p.add(p3);
         p.add(Box.createRigidArea(new Dimension(0, 10)));
         p.add(p4);
         p.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));           

         // tweek sizes of panels to make the dialog look nice

         Dimension d1 = p3.getPreferredSize();
         Dimension d2 = p1.getPreferredSize();
         p1.setPreferredSize(new Dimension(d1.width, d2.height));
         p1.setMaximumSize(new Dimension(d1.width, d2.height));
         d2 = p2.getPreferredSize();
         p2.setPreferredSize(new Dimension(d1.width, d2.height));
         p2.setMaximumSize(new Dimension(d1.width, d2.height));

         this.setContentPane(p);
         this.pack();
      }

      // -------------------------------
      // implement ActionListener method
      // -------------------------------

      public void actionPerformed(ActionEvent ae)
      {
         Object source = ae.getSource();

         if (source == ok)
         {
            userResponse = OK_OPTION;
            this.hide();
         }

         else if (source == cancel)
         {
            userResponse = CANCEL_OPTION;
            this.hide();
         }

         else if (source == fontCombo)
         {
            JComboBox cb = (JComboBox)source;
            String s = (String)cb.getSelectedItem();
            Font tmp = example.getFont();
            example.setFont(new Font(s, tmp.getStyle(), tmp.getSize()));
         }

         else if (source == sizeCombo)
         {
            JComboBox cb = (JComboBox)source;
            String s = (String)cb.getSelectedItem();
            int newSize = Integer.parseInt(s);
            Font tmp = example.getFont();
            example.setFont(new Font(tmp.getFamily(), tmp.getStyle(), newSize)); 
         }

         else if (source == foreground)
         {
            Color tmp = JColorChooser.showDialog(this,
               "Choose text color", example.getForeground());
            if (tmp != null)
               example.setForeground(tmp);
         }

         else if (source == background)
         {
            Color tmp = JColorChooser.showDialog(this,
               "Choose background color", example.getBackground());
            if (tmp != null)
               example.setBackground(tmp);
         }
      }

      // -----------------------------
      // implement ItemListener method
      // -----------------------------

      public void itemStateChanged(ItemEvent ie)
      {
         Object source = ie.getSource();
         Font tmp = example.getFont();  
         int style = tmp.getStyle();

         if (source == italic)
            if (italic.isSelected())
               style = style | Font.ITALIC;  // turn italic on
            else
               style = style & ~Font.ITALIC; // turn italic off
         else if (source == bold)
            if (bold.isSelected())
               style = style | Font.BOLD;    // turn bold on
            else
               style = style & ~Font.BOLD;   // turn bold off

         example.setFont(new Font(tmp.getFamily(), style, tmp.getSize()));
      }

      // -------------
      // other methods
      // -------------

      public Font getFont() { return example.getFont(); }
      public Color getForeColor() { return example.getForeground(); }
      public Color getBackColor() { return example.getBackground(); }

      public int showCustomDialog(Frame f, Font fontArg, 
         Color foreColorArg, Color backColorArg)
      {
         this.setLocationRelativeTo(f);

         // set the font combobox to the current font family name

         String s = fontArg.getName();
         fontCombo.setSelectedItem((Object)s);

         // set the style checkboxes to the current font style

         int style = fontArg.getStyle();
         if ((style & Font.ITALIC) == 0)
            italic.setSelected(false);
         else
            italic.setSelected(true); 
         if ((style & Font.BOLD) == 0)
            bold.setSelected(false);
         else
            bold.setSelected(true);

         // set the size combobox to the current font size

         int size = fontArg.getSize();
         sizeCombo.setSelectedItem((Object)("" + size));

         // give the example text field the current message properties

         example.setFont(fontArg);
         example.setForeground(foreColorArg);
         example.setBackground(backColorArg);

         // show the dialog

         this.show();

         // When the user closes the dialog by clicking "OK" or "Cancel",
         // return a pre-defined integer indicating which button was
         // pressed.

         return userResponse;
      }
   }
}
