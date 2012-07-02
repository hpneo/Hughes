package com.hughes.beans;

import com.hughes.models.*;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

public class SessionBean {

  public static User signIn(String email, String password) {
    User signed_user = null;
    
    if(UserBean.exists(email)){
      User user = UserBean.getByEmail(email);
      
      if(user != null){
        if(user.getPassword().equals(encryptPassword(password)))
          signed_user = user;
      }
    }
    
    return signed_user;
  }
  
  private static String encryptPassword(String password) {
    String output = "";
    
    try {
      byte[] textBytes = password.getBytes(Charset.forName("UTF8"));
      MessageDigest md = MessageDigest.getInstance("MD5");
      md.update(textBytes);
      byte[] codigo = md.digest();
      output = new String(Hex.encodeHex(codigo));

    } catch (NoSuchAlgorithmException ex) {
    }
    return output;
  }
  
}
