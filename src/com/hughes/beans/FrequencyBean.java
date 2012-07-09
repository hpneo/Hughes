package com.hughes.beans;

import java.util.List;

import javax.persistence.*;

import com.hughes.models.*;

public class FrequencyBean {
  
  public static List<Frequency> all(){
    EntityManagerFactory factory = null;
    EntityManager entity_manager = null;
    
    factory = Persistence.createEntityManagerFactory("Flights");
    entity_manager = factory.createEntityManager();
    
    return entity_manager.createQuery("SELECT f FROM Frequency f", Frequency.class).getResultList();
  }
  
  public static Frequency get(Integer id) {
    EntityManagerFactory factory = null;
    EntityManager entity_manager = null;
    
    factory = Persistence.createEntityManagerFactory("Flights");
    entity_manager = factory.createEntityManager();
    
    try {
      return entity_manager.find(Frequency.class, id);
    } catch (Exception e) {
      return null;
    }
  }
  
  public static String getIdentifier(Frequency frequency){
    return frequency.getRoute().getOrigin().getName() + " - " + 
        frequency.getRoute().getDestination().getName() + 
        " (" + DateBean.formatTime(frequency.getOutput()) + " - " + DateBean.formatTime(frequency.getArrival()) + ")";
  }
  
  public static void save(Frequency frequency){
    EntityManagerFactory factory = null;
    EntityManager entity_manager = null;
    
    factory = Persistence.createEntityManagerFactory("Flights");
    entity_manager = factory.createEntityManager();

    entity_manager.getTransaction().begin();
    entity_manager.persist(frequency);
    entity_manager.getTransaction().commit();
  }
  
  public static boolean update(Frequency frequency){
    EntityManagerFactory factory = null;
    EntityManager entity_manager = null;
    
    factory = Persistence.createEntityManagerFactory("Flights");
    entity_manager = factory.createEntityManager();
    
    try {
      Frequency frequency_to_update = entity_manager.find(Frequency.class, frequency.getId());
      
      entity_manager.getTransaction().begin();
      frequency_to_update.setDuration(frequency.getDuration());
      frequency_to_update.setArrival(frequency.getArrival());
      frequency_to_update.setOutput(frequency.getOutput());
      entity_manager.getTransaction().commit();
      
      return true;
    } catch (Exception e) {
      return false;
    }
  }
  
}
