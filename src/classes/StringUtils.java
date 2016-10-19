/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Kent
 */
public class StringUtils {
    
        public String makeProper(String theString){  
           
        java.io.StringReader in = new java.io.StringReader(theString.toLowerCase());  
         boolean precededBySpace = true; 
         StringBuilder properCase = new StringBuilder();   
         try {            
             while(true) {        
                   int i;  
            
                   i = in.read();
                  if (i == -1)  break;        
                    char c = (char)i;  
                    if (c == ' ' || c == '"' || c == '(' || c == '.' || c == '/' || c == '\\' || c == ',') {  
                      properCase.append(c);  
                      precededBySpace = true;  
                   } else {  
                      if (precededBySpace) {   
                     properCase.append(Character.toUpperCase(c));  
                   } else {   
                         properCase.append(c);   
                   }  
                    precededBySpace = false;  
                  }  
            }  
                 
          } catch (IOException ex) {
            Logger.getLogger(StringUtils.class.getName()).log(Level.SEVERE, null, ex);
        }        return properCase.toString();          
    }  
        
        //Method 2
        public static String toProperCase(String input) {  
        //A pattern for all (UNICODE-) lower case characters preceded by a word boundary  
        Pattern p = Pattern.compile("\\b([\\p{javaLowerCase}])",Pattern.UNICODE_CASE);  
        Matcher m = p.matcher(input);  
        StringBuffer sb = new StringBuffer(input.length());  
        while (m.find()) {  
            m.appendReplacement(sb, m.group(1).toUpperCase());  
        }  
        m.appendTail(sb);  
        return sb.toString();  
    }  
        
        
        //php
//            $a = 'foO BAr';  
//            $b = ucwords(strtolower($a));  
    
}
