package com.hughes.beans;

import com.hughes.models.*;

import javax.persistence.*;

public class UserBean {

  public static boolean exists(String email) {
    boolean exists = true;
    
    EntityManagerFactory factory = null;
    EntityManager entity_manager = null;
    
    factory = Persistence.createEntityManagerFactory("Flights");
    entity_manager = factory.createEntityManager();
    
    try {
      User user = entity_manager.createQuery("SELECT u FROM User u WHERE u.email = '" + email + "'", User.class).getSingleResult();
      if(user == null)
        exists = false;
    } catch (Exception e) {
      exists = false;
    }
    
    return exists;
  }
  
  public static User getByEmail(String email) {
    EntityManagerFactory factory = null;
    EntityManager entity_manager = null;
    
    factory = Persistence.createEntityManagerFactory("Flights");
    entity_manager = factory.createEntityManager();
    
    try {
      return entity_manager.createQuery("SELECT u FROM User u WHERE email='" + email + "'", User.class).getSingleResult();
    } catch (Exception e) {
      return null;
    }
  }
}
