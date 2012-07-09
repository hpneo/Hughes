package com.hughes.beans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Time;

public class DateBean {
  
  public static String formatDate(Date date){
    SimpleDateFormat date_format = new SimpleDateFormat("dd/MM/yyyy");
    
    return date_format.format(date);
  }
  
  public static String formatTime(Date time){
    SimpleDateFormat time_format = new SimpleDateFormat("hh:mm a");
    
    return time_format.format(time);
  }
  
  public static Date parseDate(String date){
    SimpleDateFormat date_format = new SimpleDateFormat("dd/MM/yyyy");
    
    try {
      return date_format.parse(date);
    } catch (ParseException e) {
      return new Date();
    }
  }
  
  public static Time parseTime(String time){
    SimpleDateFormat time_format = new SimpleDateFormat("hh:mm a");
    
    try {
      return new Time(time_format.parse(time).getTime());
    } catch (ParseException e) {
      return new Time(0);
    }
  }

}
