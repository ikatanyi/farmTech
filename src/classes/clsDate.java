/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Kent
 */
public class clsDate {
    Calendar seekedDate;
    
    public String seekDateByDay(Date date,int daySeek){
    Object[] params = { date,daySeek };

    if(date == null){
        return null;
    }
    seekedDate = Calendar.getInstance();
    seekedDate.setTime(date);
    seekedDate.set(Calendar.DAY_OF_MONTH, seekedDate.get(Calendar.DAY_OF_MONTH)+daySeek);
    String dtfrmtted=new SimpleDateFormat("dd-MM-yyyy").format(seekedDate.getTime());
    return  dtfrmtted;
    }
    
    public Date seekDateByDay(int daySeek){
        seekedDate = Calendar.getInstance();
        seekedDate.setTime(new Date());
        seekedDate.set(Calendar.DAY_OF_MONTH, seekedDate.get(Calendar.DAY_OF_MONTH)-daySeek);  
        return  seekedDate.getTime();
    }
    
    public String seekDateByWeek(Date date,int daySeek){
    Object[] params = { date,daySeek };

    if(date == null){
        return null;
    }
    seekedDate = Calendar.getInstance();
    seekedDate.setTime(date);
    seekedDate.set(Calendar.DAY_OF_MONTH, seekedDate.get(Calendar.WEEK_OF_MONTH)+daySeek);
    String dtfrmtted=new SimpleDateFormat("dd-MM-yyyy").format(seekedDate.getTime());
    return  dtfrmtted;
    }
    
    public String seekDateByMonth(Date date,int daySeek){
    Object[] params = { date,daySeek };

    if(date == null){
        return null;
    }
    seekedDate = Calendar.getInstance();
    seekedDate.setTime(date);
    seekedDate.set(Calendar.DAY_OF_MONTH, seekedDate.get(Calendar.MONTH)+daySeek);
    String dtfrmtted=new SimpleDateFormat("dd-MM-yyyy").format(seekedDate.getTime());
    return  dtfrmtted;
    }
    
    public String seekDateByYear(Date date,int years){
    if(date == null){
        return null;
    }
    seekedDate = Calendar.getInstance();
    seekedDate.setTime(date);
    seekedDate.set(Calendar.YEAR, seekedDate.get(Calendar.YEAR)-years);
    String dtfrmtted=new SimpleDateFormat("dd-MM-yyyy").format(seekedDate.getTime());
    return  dtfrmtted;
    }
    
    public String seekDateByYear_add(Date date,int years){
    if(date == null){
        return null;
    }
    seekedDate = Calendar.getInstance();
    seekedDate.setTime(date);
    seekedDate.set(Calendar.YEAR, seekedDate.get(Calendar.YEAR)+years);
    String dtfrmtted=new SimpleDateFormat("dd-MM-yyyy").format(seekedDate.getTime());
    return  dtfrmtted;
    }
    
    public String seekMonth_Begin(Date date){
        seekedDate = Calendar.getInstance();
        seekedDate.setTime(date);
        seekedDate.set(Calendar.DAY_OF_MONTH, 1);
        String dtfrmtted=new SimpleDateFormat("dd-MM-yyyy").format(seekedDate.getTime());
        return  dtfrmtted;
    }
    
    public String seekMonth_End(Date date){
        seekedDate = Calendar.getInstance();
        seekedDate.setTime(date);
        seekedDate.set(Calendar.DAY_OF_MONTH, seekedDate.getActualMaximum(Calendar.DAY_OF_MONTH));
        String dtfrmtted=new SimpleDateFormat("dd-MM-yyyy").format(seekedDate.getTime());
        return  dtfrmtted;
    }
    
    private static void setTimeToBeginningOfDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    private static void setTimeToEndofDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
    }
    
}
