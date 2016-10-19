package classes;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import  javax.swing.*; 
public class  TextAreaEditor  extends  DefaultCellEditor { 
    
    public  TextAreaEditor() 
    {   
        super ( new  JTextField()); 
        final  JTextArea textArea =  new  JTextArea(); 
        textArea.setWrapStyleWord( true );  
        textArea.setLineWrap( true );  
        textArea.setForeground(Color.red);
        JScrollPane scrollPane =  new  JScrollPane(textArea);
        scrollPane.setBorder( null );  
        editorComponent = scrollPane; 
        delegate =  new  DefaultCellEditor.EditorDelegate() 
        {     
            public void  setValue(Object value)
            {      
                textArea.setText((value !=  null ) ?value.toString() :  "" );
            }     
            public  Object getCellEditorValue()
            {       
                return  textArea.getText();  
            }  
            
            public boolean isCellEditable(EventObject evt){
              if(evt instanceof MouseEvent){
                  int clickCount;
                  clickCount=1;
                  return((MouseEvent)evt).getClickCount()>=clickCount;
              }
              return true;
            }
         }; 
    }
} 