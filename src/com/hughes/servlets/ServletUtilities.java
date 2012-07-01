package com.hughes.servlets;

import javax.servlet.http.*;

public class ServletUtilities {

  public static String getCookieValue(Cookie[] cookies, String cookieName) {
    String defaultValue = null;
    
    for(int i=0; i<cookies.length; i++) {
      Cookie cookie = cookies[i];
      
      if (cookieName.equals(cookie.getName()))
        return cookie.getValue();
    }
    
    return defaultValue;
  }
  
}
