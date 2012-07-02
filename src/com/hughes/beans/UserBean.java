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
  
  public static User get(Integer id) {
    EntityManagerFactory factory = null;
    EntityManager entity_manager = null;
    
    factory = Persistence.createEntityManagerFactory("Flights");
    entity_manager = factory.createEntityManager();
    
    try {
      return entity_manager.find(User.class, id);
    } catch (Exception e) {
      return null;
    }
  }
  
  public static User getByEmail(String email) {
    EntityManagerFactory factory = null;
    EntityManager entity_manager = null;
    
    factory = Persistence.createEntityManagerFactory("Flights");
    entity_manager = factory.createEntityManager();
    
    try {
      return entity_manager.createQuery("SELECT u FROM User u WHERE u.email='" + email + "'", User.class).getSingleResult();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    }
  }
  
  public static void save(User user){
    EntityManagerFactory factory = null;
    EntityManager entity_manager = null;
    
    factory = Persistence.createEntityManagerFactory("Flights");
    entity_manager = factory.createEntityManager();
    
    entity_manager.getTransaction().begin();
    entity_manager.persist(user);
    entity_manager.getTransaction().commit();
  }
  
  public static boolean update(User user){
    EntityManagerFactory factory = null;
    EntityManager entity_manager = null;
    
    factory = Persistence.createEntityManagerFactory("Flights");
    entity_manager = factory.createEntityManager();
    
    try {
      User user_to_update = entity_manager.find(User.class, user.getId());
      
      entity_manager.getTransaction().begin();
      user_to_update.setFirstName(user.getFirstName());
      user_to_update.setLastName(user.getLastName());
      user_to_update.setDni(user.getDni());
      entity_manager.getTransaction().commit();
      
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
