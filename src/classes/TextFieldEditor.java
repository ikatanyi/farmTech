package classes;


import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


    
  
/**
 *
 * @author Kent
 */
public class TextFieldEditor extends DefaultCellEditor {
    
//    super(new JTextField);
      JTextField component;
      private String type="string";

    public TextFieldEditor(String type) {
        super(new JTextField());   
        component=new JTextField();
        component.setName("editor");
        this.type=type;
        
    }
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,boolean isSelected, int row, int column) {
      component.setName("editor");
      component.setFont(table.getFont()); 
      component.setBorder(null);
      component.setOpaque(true);  
      component.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if(!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar()!=KeyEvent.VK_PERIOD&& evt.getKeyChar()!=KeyEvent.VK_BACK_SPACE ){
                    switch(type){
                        case "string":
                            component.setEditable(true); 
                            break;
                        default:
                            component.getToolkit().beep();
                            component.setEditable(false);
                            break;
                    }                     
//                     return false;
                }else{
                  component.setEditable(true);  
                }
     
            }
        });
//      component.addFocusListener(new java.awt.event.FocusAdapter() {
//           @Override     
//            public void focusLost(java.awt.event.FocusEvent evt) {
//                boolean source=evt.isTemporary();
//                
//                if(source) 
//                    return;
//                stopCellEditing(); 
////             if(evt.getID()==FocusEvent.FOCUS_LOST ){               
////                        
////             }
//                
//            }
//        });
      
     
//        if(isSelected){
        SwingUtilities.invokeLater(new Runnable() {
                public void run() {                  
                 component.selectAll();
                }
        });  
//     }
       component.setText((value !=  null ) ?value.toString() :  "" );
       return component;
   }

     @Override
        public Object getCellEditorValue(){           
            return component.getText();           
        }
   
    public boolean startCellEditing(EventObject evt) {
        if(evt instanceof MouseEvent ){
            int clickCount;
              clickCount=1;
              return((MouseEvent)evt).getClickCount()>=clickCount;               
        }
        return true;
    }
       
     
      @Override
     public boolean isCellEditable(EventObject evt){
              if(evt instanceof MouseEvent){
                  int clickCount;
                  clickCount=1;                  
                  return((MouseEvent)evt).getClickCount()>=clickCount;
              }
              return true;
            }
               
//      @Override
//     public boolean stopCellEditing(){
//         return true;
//     }       
     public boolean isValid(char s){
          if(!Character.isDigit(s) && s!=KeyEvent.VK_PERIOD)
             {
               return false;
             }
             else
             {
               return true;
            }
    }   
     
//      @Override
//      public boolean stopCellEditing() {
//               fireEditingStopped();
//                return true;
//    }
}
  

